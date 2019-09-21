package georgetsak.opcraft.common.entity.marine;

import georgetsak.opcraft.client.OPSoundEvent;
import georgetsak.opcraft.common.entity.EntityBounty;
import georgetsak.opcraft.common.entity.other.EntityBandit;
import georgetsak.opcraft.common.entity.other.EntityPirate;
import georgetsak.opcraft.common.registry.OPItems;
import georgetsak.opcraft.common.registry.OPLootTables;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityTequilaBridgeGuard extends EntityBounty {

    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.<Boolean>createKey(EntityTequilaBridgeGuard.class, DataSerializers.BOOLEAN);

    public EntityTequilaBridgeGuard(World worldIn) {
        super(worldIn);
        setBounty(300);
        dataManager.register(ATTACKING, false);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(1, new AIFireballAttack(this));
        this.tasks.addTask(3, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(5, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityBandit.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityPirate.class, true));

    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.38D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.3D);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if(getAttackTarget() != null){
            Entity entity = getAttackTarget();
            this.getLookHelper().setLookPosition(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ, (float)this.getHorizontalFaceSpeed(), (float)this.getVerticalFaceSpeed());
            this.getNavigator().setPath(getNavigator().getPathToEntityLiving(entity), 0.5f);

        }
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return OPLootTables.ENTITY_TEQUILA_BRIDGE_GUARD;
    }

    @SideOnly(Side.CLIENT)
    public boolean isAttacking()
    {
        return this.dataManager.get(ATTACKING);
    }

    public void setAttacking(boolean attacking)
    {
        this.dataManager.set(ATTACKING, attacking);
    }

    protected boolean canDespawn()
    {
        return false;
    }

    public float getEyeHeight()
    {
        return 1.74F;
    }

    private class AIFireballAttack extends EntityAIBase
    {
        private final EntityTequilaBridgeGuard parentEntity;
        public int attackTimer;

        public AIFireballAttack(EntityTequilaBridgeGuard guard)
        {
            this.parentEntity = guard;
        }

        public boolean shouldExecute()
        {
            return this.parentEntity.getAttackTarget() != null;
        }

        public void startExecuting()
        {
            this.attackTimer = 0;
        }

        public void resetTask()
        {
            this.parentEntity.setAttacking(false);
        }

        public void updateTask()
        {
            EntityLivingBase entitylivingbase = this.parentEntity.getAttackTarget();

            if (entitylivingbase.getDistanceSq(this.parentEntity) < 200D && this.parentEntity.canEntityBeSeen(entitylivingbase)) //&& this.parentEntity.canEntityBeSeen(entitylivingbase))
            {
                World world = this.parentEntity.world;
                ++this.attackTimer;

                if (this.attackTimer == 20)
                {

                    entitylivingbase.attackEntityFrom(DamageSource.causeMobDamage(parentEntity),4);
                    world.playSound(null, parentEntity.getPosition(), OPSoundEvent.flintlock_fire, SoundCategory.HOSTILE, 30f, 1f);
                    for (int i = 0; i < 10; i++) {
                        world.spawnParticle(EnumParticleTypes.FLAME, parentEntity.posX + parentEntity.getLookVec().x, parentEntity.posY + parentEntity.getLookVec().y, parentEntity.posZ + parentEntity.getLookVec().z,0d,0.1d,0d);
                    }

                    this.attackTimer = -40;
                }
            }
            else if (this.attackTimer > 0)
            {
                --this.attackTimer;
            }

            this.parentEntity.setAttacking(this.attackTimer > 10);
        }
    }

}
