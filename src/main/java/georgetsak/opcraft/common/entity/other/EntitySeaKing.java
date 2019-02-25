package georgetsak.opcraft.common.entity.other;

import com.google.common.base.Predicate;
import georgetsak.opcraft.common.entity.marine.EntityMarine;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntitySeaKing extends EntityMob {

    public EntitySeaKing(World worldIn) {
        super(worldIn);
        setSize(20, 2);
    }


    private EntityAIWander wander;
    private int attackTick = 0;
    protected void initEntityAI() {
        this.wander = new EntityAIWander(this, 1.0D, 70);
        this.wander.setMutexBits(3);
        this.tasks.addTask(1, this.wander);
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 30.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 10, false, false, new SeaKingTargetSelector(this)));
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn) {
        super.onCollideWithPlayer(entityIn);
        if(entityIn.hurtResistantTime == 0 && attackTick == 0){
            attackTick = 60;
            entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 10f);
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if(this.getAttackTarget() != null){
            this.rotationYaw = this.rotationYawHead;
        }
        if(attackTick > 0){
            attackTick--;
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (getAttackTarget() != null) {
            EntityLivingBase entitylivingbase = getAttackTarget();
            if (this.getDistance(entitylivingbase) > 20) {
                this.motionX = MathHelper.clamp(entitylivingbase.posX - this.posX, -0.25d, 0.25d);
                this.motionY = MathHelper.clamp(entitylivingbase.posY - this.posY, -0.25d, 0.25d);
                this.motionZ = MathHelper.clamp(entitylivingbase.posZ - this.posZ, -0.25d, 0.25d);
                this.getLookHelper().setLookPositionWithEntity(entitylivingbase, (float)this.getHorizontalFaceSpeed(), (float)this.getVerticalFaceSpeed());
            }
            System.out.println(this.getDistance(entitylivingbase));
        }
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public boolean getCanSpawnHere() {
        return true;
    }

    public boolean isNotColliding() {
        return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this);
    }

    public int getTalkInterval() {
        return 120;
    }

    protected boolean canDespawn() {
        return true;
    }

    protected int getExperiencePoints(EntityPlayer player) {
        return 10 + this.world.rand.nextInt(3);
    }

    public boolean isPushedByWater() {
        return false;
    }

    static class SeaKingTargetSelector implements Predicate<EntityLivingBase>{

        private final EntitySeaKing seaKing;

        public SeaKingTargetSelector(EntitySeaKing seaKing){
            this.seaKing = seaKing;
        }

        @Override
        public boolean apply(@Nullable EntityLivingBase input) {
            return (input instanceof EntityPlayer || input instanceof EntityBandit || input instanceof EntityMarine) && input.getDistance(seaKing) < 50.0D;
        }
    }

}
