package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.capability.devilfruitlevels.DevilFruitLevelsCap;
import georgetsak.opcraft.common.util.MathUtils;
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
            OPUtils.createExplosion(ep, world, posX, posY, posZ, 10 + getLevel(), true);
            this.setDead();
        }
    }

    int getLevel(){
        if(ep != null){
            return DevilFruitLevelsCap.get(ep).getPowerLevel(3);
        }
        return 0;
    }

    public void onCollideWithPlayer(EntityPlayer entityIn){
    }


    public void collideWithEntity(Entity entityIn){
    }

    public void applyEntityCollision(Entity entityIn){
    }

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
