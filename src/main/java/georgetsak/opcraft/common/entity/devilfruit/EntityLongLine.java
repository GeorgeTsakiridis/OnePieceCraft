package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.vecmath.Point3d;
import java.util.ArrayList;

public class EntityLongLine extends EntitySimpleProjectile{

    private static final DataParameter<Integer> LENGTH = EntityDataManager.<Integer>createKey(EntityLongLine.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> EXTEND = EntityDataManager.createKey(EntityLongLine.class,DataSerializers.BOOLEAN);

    public EntityLongLine(World world, double x, double y, double z, int length, EntityPlayer owner, boolean extend) {
        super(world, x, y, z, 1, 1, owner);
        this.ignoreFrustumCheck = true;
        dataManager.register(LENGTH, length);
        dataManager.register(EXTEND, extend);
    }

    public EntityLongLine(World worldIn) {
        super(worldIn);
        this.ignoreFrustumCheck = true;
        dataManager.register(LENGTH, 0);
        dataManager.register(EXTEND, false);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if(!world.isRemote && shouldExtend() && ticksExisted == 1) {
            Vec3d vec = getDirection().scale(getLength());
            Vec3d posVec = new Vec3d(posX, posY, posZ);
            ArrayList<Point3d> points = OPUtils.getIntermediatePoints(posVec, new Vec3d(posVec.x + vec.x, posVec.y + vec.y, posVec.z + vec.z), (int)(getLength()/20f));

            for (Point3d point : points) {
                extendTo(point.x,point.y,point.z, owner);
            }
        }
    }

    public int getLength(){
        return dataManager.get(LENGTH);
    }

    public void setLength(int length){
        dataManager.set(LENGTH, length);
    }

    public boolean shouldExtend(){
        return dataManager.get(EXTEND);
    }

    public void setShouldExtend(boolean bool){
        dataManager.set(EXTEND, bool);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("LineLength", getLength());
        compound.setBoolean("Extend", shouldExtend());
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        setLength(compound.getInteger("LineLength"));
        setShouldExtend(compound.getBoolean("Extend"));
    }

    @Override
    public float getSpeedMultiplier() {
        return 0f;
    }

    public void extendTo(double x, double y, double z, EntityPlayer owner){
        world.spawnEntity(new EntityLongLine(world, x, y, z, 0, owner, false));
    }

}
