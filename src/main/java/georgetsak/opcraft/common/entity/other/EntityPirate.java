package georgetsak.opcraft.common.entity.other;

import georgetsak.opcraft.common.entity.EntityBounty;
import georgetsak.opcraft.common.entity.marine.EntityMarine;
import georgetsak.opcraft.common.registry.OPLootTables;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import java.util.Random;

public class EntityPirate extends EntityBounty
{
    private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityPirate.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> TEXTURE = EntityDataManager.createKey(EntityPirate.class, DataSerializers.VARINT);

    public static final int STRONG = 1;
    public static final int THIN = 2;
    public static final int FAT = 3;

    //1:Strong
    // 2:Thin
    // 3:Fat
    public EntityPirate(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
        setBounty(-200);
    }

    public void setTexture(int texture){
        this.getDataManager().set(TEXTURE, texture);
    }

    public int getTexture(){
        return this.getDataManager().get(TEXTURE);
    }

    public void setType(int type)
    {
        this.getDataManager().set(TYPE, type);
    }

    public int getType()
    {
        return this.getDataManager().get(TYPE);
    }

    @Override
    protected void entityInit() {
        int i = new Random().nextInt(3) + 1;
        int j = new Random().nextInt(2) + 1;

        this.getDataManager().register(TYPE, i);
        this.getDataManager().register(TEXTURE, j);
        setType(i);
        setTexture(j);
        super.entityInit();
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMarine.class, true));
    }

    public float getEyeHeight()
    {
        return 1.74F;
    }

    protected void applyEntityAttributes()
    {
        //default values
        double health = 26.0D;
        double speed = 0.35D;
        double attack = 3.0D;
        double knockback = 0D;

        switch (getType()){
            case STRONG:{
                health = 28;
                speed = 0.35;
                attack = 5;
                knockback = 0.2;
                break;
            }
            case THIN:{
                health = 20;
                speed = 0.4;
                attack = 3;
                knockback = 0;
                break;
            }
            case FAT:{
                health = 36;
                speed = 0.3;
                attack = 4;
                knockback = 0.5;
                break;
            }
        }

        double mult = 1;
        if(world.getDifficulty() == EnumDifficulty.NORMAL){
            mult = 1.3;
        }
        if(world.getDifficulty() == EnumDifficulty.HARD){
            mult = 1.8;
        }

        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health*mult);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(speed);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(attack*mult);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10);
        if(knockback > 0){
            this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(knockback*mult);
        }
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
        return OPLootTables.ENTITY_MARINE;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        setType(compound.getInteger("type"));
        setTexture(compound.getInteger("texture"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("type", this.getDataManager().get(TYPE));
        compound.setInteger("texture", this.getDataManager().get(TEXTURE));
    }

    public void onUpdate()
    {
        super.onUpdate();
        if (!world.isRemote && world.getDifficulty() == EnumDifficulty.PEACEFUL)
        {
            this.setDead();
        }
    }

    public double getYOffset()
    {
        return -0.35D;
    }

    protected boolean isValidLightLevel()
    {
        return true;
    }

    public boolean getCanSpawnHere()
    {
        if (super.getCanSpawnHere())
        {
            EntityPlayer entityplayer = world.getClosestPlayerToEntity(this, 5.0D);
            return entityplayer == null;
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