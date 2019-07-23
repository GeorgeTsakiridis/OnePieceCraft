package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.util.OPDataSerializers;
import georgetsak.opcraft.common.util.OPUtils;
import georgetsak.opcraft.common.util.Vector3Double;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntitySimpleProjectile extends EntityFlying {

    public static final DataParameter<Vector3Double> DIRECTION = EntityDataManager.createKey(EntitySimpleProjectile.class, OPDataSerializers.VECTOR3DOUBLE);
    public static final DataParameter<Vector3Double> START_POS = EntityDataManager.createKey(EntitySimpleProjectile.class, OPDataSerializers.VECTOR3DOUBLE);
    public static final DataParameter<Float> PITCH = EntityDataManager.createKey(EntitySimpleProjectile.class, DataSerializers.FLOAT);
    public static final DataParameter<Float> YAW = EntityDataManager.createKey(EntitySimpleProjectile.class, DataSerializers.FLOAT);

    EntityPlayer owner;

    public EntitySimpleProjectile(World world, double x, double y, double z, float yaw, float pitch, float width, float height, EntityPlayer owner){
        super(world);
        this.owner = owner;
        this.dataManager.register(START_POS, new Vector3Double(new Vec3d(x, y, z)));
        this.dataManager.register(DIRECTION, new Vector3Double(OPUtils.convertRotation(yaw, pitch)));
        this.dataManager.register(YAW, yaw);
        this.dataManager.register(PITCH, pitch);
        setPositionAndRotation(x, y, z, yaw, pitch);
        setSize(width, height);
    }

    public EntitySimpleProjectile(World worldIn) {
        super(worldIn);
        this.dataManager.register(START_POS, new Vector3Double(0, 0, 0));
        this.dataManager.register(DIRECTION, new Vector3Double(0, 0, 0));
        this.dataManager.register(YAW, 0f);
        this.dataManager.register(PITCH, 0f);
    }

    public Vec3d getDirection() {
        return dataManager.get(DIRECTION).toVec3D();
    }

    public Vec3d getStartPos(){
        return dataManager.get(START_POS).toVec3D();
    }

    public float getYaw(){
        return dataManager.get(YAW);
    }

    public float getPitch(){
        return dataManager.get(PITCH);
    }

    public void setYaw(float f){
        dataManager.set(YAW, f);
    }

    public void setPitch(float f){
        dataManager.set(PITCH,f);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("StartPos", dataManager.get(START_POS).writeToNBT());
        compound.setTag("Direction", dataManager.get(DIRECTION).writeToNBT());
        compound.setFloat("Yaw", getYaw());
        compound.setFloat("Pitch", getPitch());
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        dataManager.set(START_POS,new Vector3Double(compound.getTagList("StartPos",6)));
        dataManager.set(DIRECTION,new Vector3Double(compound.getTagList("Direction",6)));
        setYaw(compound.getFloat("Yaw"));
        setPitch(compound.getFloat("Pitch"));

    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        spawnParticles();
        setRotation(getYaw(),getPitch());
        if(ticksExisted > getMaxTicks()){
            onExpired();
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if(Math.sqrt((getStartPos().x - posX)*(getStartPos().x - posX)) > getMaxDistance() || Math.sqrt((getStartPos().z - posZ)*(getStartPos().z - posZ)) > getMaxDistance()){
            onMaxDistanceCovered();
        }else {
            setVelocity(getDirection().x * getSpeedMultiplier(), getDirection().y * getSpeedMultiplier(), getDirection().z * getSpeedMultiplier());
        }
    }

    public int getMaxTicks(){
        return 40;
    }

    public double getMaxDistance(){
        return 100d;
    }

    public float getSpeedMultiplier(){
        return 1f;
    }

    public void onMaxDistanceCovered(){

    }

    public void onExpired(){
        this.setDead();
    }

    public void spawnParticles(){

    }

    boolean isCollisionWithPlayerValid(EntityPlayer entityPlayer){
        return owner != null && entityPlayer != owner && entityPlayer.hurtResistantTime <= 0;
    }

    boolean isCollisionWithEntityValid(Entity entity){
        return !world.isRemote && entity instanceof EntityLiving;
    }

    @Override
    public boolean isEntityInvulnerable(DamageSource source) {
        return true;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

}
