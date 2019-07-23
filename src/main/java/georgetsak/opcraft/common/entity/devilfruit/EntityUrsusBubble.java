package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

public class EntityUrsusBubble extends EntityFlying {

    double x, y, z;
    float yaw, pitch;
    EntityPlayer ep;

    public EntityUrsusBubble(World world){
        super(world);
        this.setSize(1F, 1F);
    }

    public EntityUrsusBubble(World worldIn, double x, double y, double z, float yaw, float pitch, EntityPlayer owner) {
        super(worldIn);
        this.ep = owner;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.motionX = 0;
        this.motionY = 0;
        this.motionZ = 0;
        this.setPositionAndRotation(x, y, z, yaw, pitch);
    }

    public void onLivingUpdate(){
        super.onLivingUpdate();
        this.motionX = 0;
        this.motionY = 0;
        this.motionZ = 0;
    }

    public void onUpdate() {
        super.onUpdate();

        if ((ep != null) && ticksExisted > 100) {
            OPUtils.createExplosion(ep, world, posX, posY, posZ, 15, true);
            double range = 30;
            float damage = 12F;
            double x1 = posX - range;
            double x2 = posX + range;
            double y1 = posY - range;
            double y2 = posY + range;
            double z1 = posZ - range;
            double z2 = posZ + range;

            List<Entity> entities = ep.world.getEntitiesWithinAABBExcludingEntity(ep, new AxisAlignedBB(x1, y1, z1, x2, y2, z2));

            for (int i = 0; i < entities.size(); i++) {
                if (entities.get(i) != null) {
                    if (entities.get(i) instanceof EntityPlayer) {
                        EntityPlayer entityPlayer = (EntityPlayer) entities.get(i);
                        entityPlayer.attackEntityFrom(DamageSource.causePlayerDamage(ep), OPUtils.calculateDamage(entityPlayer, damage, true));

                    } else if (entities.get(i) instanceof EntityLiving) {
                        Entity e = entities.get(i);
                        e.attackEntityFrom(DamageSource.causePlayerDamage(ep), damage);
                    }

                }
            }
            this.setDead();
        }
    }

    public void onCollideWithPlayer(EntityPlayer entityIn){
    }


    public void collideWithEntity(Entity entityIn){
    }

    public void applyEntityCollision(Entity entityIn){
    }

    //@#@#@#@#@#@#@#@#@#@#@##@#@#@#@


    public boolean isEntityInvulnerable(DamageSource source){
        return true;
    }

    public boolean canBePushed(){
        return false;
    }

    public boolean canBeCollidedWith()
    {
        return false;
    }


}
