package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntitySimpleProjectile extends EntityFlying {

    private double startX;
    private double startY;

    private Vec3d direction = new Vec3d(0, 0, 0);
    EntityPlayer owner;

    public EntitySimpleProjectile(World world, double x, double y, double z, float yaw, float pitch, float width, float height, EntityPlayer owner){
        super(world);
        startX = x;
        startY = y;
        direction = OPUtils.convertRotation(yaw, pitch);
        setPositionAndRotation(x, y, z, yaw, pitch);
        setSize(width, height);
        this.owner = owner;
    }

    public EntitySimpleProjectile(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if(ticksExisted > getMaxTicks()){
            onExpired();
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if(Math.sqrt((startX - posX)*(startX - posX)) > getMaxDistance() || Math.sqrt((startY - posY)*(startY - posY)) > getMaxDistance()){
            onMaxDistanceCovered();
        }

        this.motionX = direction.x * getSpeedMultiplier();
        this.motionY = direction.y * getSpeedMultiplier();
        this.motionZ = direction.z * getSpeedMultiplier();
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
