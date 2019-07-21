package georgetsak.opcraft.common.entity.other;

import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityStormLeg extends EntityFlying {

    private Vec3d direction = new Vec3d(0,0,0);
    private EntityPlayer ep;

    public EntityStormLeg(World world){
        super(world);
        this.setSize(1.5F, 1.5F);
    }

    public EntityStormLeg(World worldIn, double x, double y, double z, float yaw, float pitch, EntityPlayer owner) {
        this(worldIn);

        this.ep = owner;
        this.posX = x;
        this.posY = y+1.8;
        this.posZ = z;
        this.direction = OPUtils.convertRotation(yaw, pitch);
        this.setPositionAndRotation(x, y, z, yaw, pitch);
        this.setSize(0.5F, 0.5F);

    }

    public void onLivingUpdate(){
        super.onLivingUpdate();
        if (this.movedDistance <= 80){
            this.motionX = direction.x*1.5;
            this.motionY = direction.y*1.5;
            this.motionZ = direction.z*1.5;
        }
    }

    public void onUpdate()
    {
        super.onUpdate();
        if(this.ticksExisted >= 80)
        {
            this.setDead();
        }

    }

    public void onCollideWithPlayer(EntityPlayer entityIn)
    {
        if(ep != entityIn && ep != null){
            entityIn.attackEntityFrom(DamageSource.causePlayerDamage(ep), OPUtils.calculateDamage(entityIn, 6, false));
        }
    }

    public void collideWithEntity(Entity entityIn){
        if(entityIn instanceof EntityLiving){
            entityIn.attackEntityFrom(DamageSource.causePlayerDamage(ep), 8);
        }
    }



    //@#@#@#@#@#@#@#@#@#@#@##@#@#@#@

    @Override
    public void applyEntityCollision(Entity entityIn)
    {
    }

    public float getCollisionBorderSize()
    {
        return 1.0F;
    }

    public boolean isEntityInvulnerable(DamageSource source){
        return true;
    }

    public boolean canBePushed(){
        return false;
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return false;
    }


}
