package georgetsak.opcraft.common.entity.other;

import georgetsak.opcraft.common.entity.EntityBounty;
import georgetsak.opcraft.common.entity.other.EntityBandit;
import georgetsak.opcraft.common.registry.OPItems;
import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nullable;

public class EntityTonta extends EntityCreature
{
    public EntityTonta(World worldIn)
    {
        super(worldIn);
        this.setSize(0.8F, 1F);
    }

    public EntityTonta(World worldIn, BlockPos position){
        this(worldIn);
        this.setPosition(position.getX(), position.getY(), position.getZ());
    }

    protected boolean canDespawn()
    {
        return true;
    }

    public boolean canBeLeashedTo(EntityPlayer player)
    {
        return false;
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.5D, true));
        this.tasks.addTask(3, new EntityAIWander(this, 1.5D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 20.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityBandit.class, true));

    }

    public float getEyeHeight()
    {
        return 0.75F;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(14.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

        if (flag) {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    protected SoundEvent getAmbientSound()
    {
        return null;
    }

    protected SoundEvent getHurtSound()
    {
        return SoundEvents.ENTITY_GENERIC_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_GENERIC_DEATH;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.15F, 1.0F);
    }

    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_ENDERMITE;
    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
    }

    public void onUpdate()
    {
        super.onUpdate();
    }


    public double getYOffset()
    {
        return -0.35D;
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }

}