package georgetsak.opcraft.common.entity.other;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

import java.util.Random;

public class EntitySkypiean extends EntityCreature {

    private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntitySkypiean.class, DataSerializers.VARINT);

    public EntitySkypiean(World worldIn) {
        super(worldIn);
        setSize(0.8f,1.9f);
        int i = new Random().nextInt(3);
        this.getDataManager().register(TYPE, i);

    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(9, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));

    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }

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
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        setType(compound.getInteger("type"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("type", this.getDataManager().get(TYPE));
    }

    @Override
    public boolean canBeLeashedTo(EntityPlayer player) {
        return false;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {

    }
}
