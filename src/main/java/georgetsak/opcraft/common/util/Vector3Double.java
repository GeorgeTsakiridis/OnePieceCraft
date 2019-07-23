package georgetsak.opcraft.common.util;

import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.Vec3d;

public class Vector3Double {

    protected final double x;
    protected final double y;
    protected final double z;

    public Vector3Double(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3Double(Vec3d vec3d){
        x = vec3d.x;
        y = vec3d.y;
        z = vec3d.z;
    }

    public Vector3Double(NBTTagList nbt)
    {
        this(nbt.getDoubleAt(0), nbt.getDoubleAt(1), nbt.getDoubleAt(2));
    }

    public NBTTagList writeToNBT()
    {
        NBTTagList nbttaglist = new NBTTagList();
        nbttaglist.appendTag(new NBTTagDouble(this.x));
        nbttaglist.appendTag(new NBTTagDouble(this.y));
        nbttaglist.appendTag(new NBTTagDouble(this.z));
        return nbttaglist;
    }

    public boolean equals(Object vec3)
    {
        if (!(vec3 instanceof Vector3Double))
        {
            return false;
        }
        else
        {
            Vector3Double vec3_2 = (Vector3Double) vec3;
            return this.x == vec3_2.x && this.y == vec3_2.y && this.z == vec3_2.z;
        }
    }

    public double getX()
    {
        return this.x;
    }

    public double getY()
    {
        return this.y;
    }

    public double getZ()
    {
        return this.z;
    }

    public Vec3d toVec3D(){
        return new Vec3d(x,y,z);
    }
}
