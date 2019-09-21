package georgetsak.opcraft.common.entity.other;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntitySlave extends EntityCreature {

    private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntitySkypiean.class, DataSerializers.VARINT);

    //Types: 0: Normal Uniform with chains / 1: Normal Uniform without chains / 2: Blue Uniform with chains / 3: Blue Uniform without chains

    public EntitySlave(World worldIn) {
        super(worldIn);
        dataManager.register(TYPE,rand.nextInt(4));
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIWander(this, 0.3D));
        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.4D));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    public void setType(int type)
    {
        this.getDataManager().set(TYPE, type);
    }
    public int getType()
    {
        return this.getDataManager().get(TYPE);
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("type", this.getDataManager().get(TYPE));
    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        setType(compound.getInteger("type"));
    }


}
