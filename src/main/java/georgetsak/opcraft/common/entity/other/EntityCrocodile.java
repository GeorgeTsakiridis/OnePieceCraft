package georgetsak.opcraft.common.entity.other;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.OPSoundEvent;
import georgetsak.opcraft.common.entity.EntityBounty;
import georgetsak.opcraft.common.registry.OPLootTables;
import georgetsak.opcraft.common.util.MathUtils;
import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Blocks;
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

public class EntityCrocodile extends EntityBounty
{
    public EntityCrocodile(World worldIn)
    {
        super(worldIn);
        this.setSize(0.8F, 2.4F);
        setBounty(10000);
    }

    public EntityCrocodile(World worldIn, BlockPos position){
        this(worldIn);
        this.setPosition(position.getX(), position.getY(), position.getZ());
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (super.attackEntityAsMob(entityIn))
        {
            if (entityIn instanceof EntityLivingBase)
            {
                int i = 0;
                
                if (world.getDifficulty() == EnumDifficulty.EASY){
                    i = 4;
                }
                if (world.getDifficulty() == EnumDifficulty.NORMAL)
                {
                    i = 7;
                }
                else if (world.getDifficulty() == EnumDifficulty.HARD)
                {
                    i = 15;
                }

                if (i > 0)
                {
                    ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, i * 20, 0));
                }
            }

            return true;
        }
        else
        {
            return false;
        }

    }

    protected boolean canDespawn()
    {
        return false;
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.2D, false));
        this.tasks.addTask(3, new EntityAIWander(this, 1.2D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 15.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    public float getEyeHeight()
    {
        return 2F;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(38D);
    }

    protected SoundEvent getAmbientSound()
    {
        return OPSoundEvent.crocodile_laugh;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
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
        return OPLootTables.ENTITY_CROCODILE;
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
        if(ticksExisted % 5 == 0) {
            EntityLargeFireball f = new EntityLargeFireball(world, posX, posY + 10, posZ, 0d, 4d, 0d);
            world.spawnEntity(f);
        }
        if(!world.isRemote && !OPCraft.config.disableGriefing.getCurrentValue()){
            Block block = world.getBlockState(getPosition().down()).getBlock();
            if(block == Blocks.DIRT || block == Blocks.GRASS || block == Blocks.GRASS_PATH){
                world.setBlockState(getPosition().down(), Blocks.SAND.getDefaultState());
            }
        }
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    public double getYOffset()
    {
        return -0.35D;
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }


    protected boolean isValidLightLevel()
    {
        return true;
    }

    public boolean getCanSpawnHere()
    {
        if (super.getCanSpawnHere())
        {
            return world.canBlockSeeSky(getPosition());
        }
        else
        {
            return false;
        }
    }

    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }
}