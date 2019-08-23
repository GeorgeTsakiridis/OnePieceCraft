package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.util.MathUtils;
import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.List;

public class EntityOverheat extends EntityLongLine{

    public EntityOverheat(World worldIn) {
        super(worldIn);
    }

    public EntityOverheat(World world, double x, double y, double z, float yaw, float pitch, boolean extend, EntityPlayer owner) {
        super(world, x, y, z, yaw, pitch, 300, owner, extend);
    }

    @Override
    public int getMaxTicks() {
        return 60;
    }

    @Override
    public void extendTo(double x, double y, double z, float yaw, float pitch, EntityPlayer owner) {
        world.spawnEntity(new EntityOverheat(world, x, y, z, yaw, pitch,false, owner));
    }

    @Override
    public void touchedAt(double x, double y, double z, EntityPlayer owner) {
        OPUtils.newExplosion(owner,world,x,y,z,3f + getLevel(2)*0.5f,true,true);

        List<Entity> entities = OPUtils.getNearbyEntitiesExcluding(owner,6, owner);
        for(Entity entity : entities){
            if(entity instanceof EntityPlayer){
                entity.attackEntityFrom(DamageSource.causePlayerDamage(owner), MathUtils.calculateDamage(owner, 14,true));
                entity.setFire(5);
            }else if(entity instanceof EntityLiving && !(entity instanceof EntitySimpleProjectile)){
                entity.attackEntityFrom(DamageSource.causePlayerDamage(owner),6 + getLevel(2)*2);
                entity.setFire(5);
            }
        }
    }
}
