package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.util.MathUtils;
import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityIceSaber extends EntitySimpleProjectile {

    public EntityIceSaber(World world){
        super(world);
        this.setSize(0.5f, 0.5f);
    }

    public EntityIceSaber(World worldIn, double x, double y, double z, float yaw, float pitch, EntityPlayer owner) {
        super(worldIn, x, y, z, yaw, pitch, 0.5f, 0.5f, owner);
        this.setPositionAndRotation(x, y, z, yaw, pitch);
    }

    @Override
    public void onValidPlayerCollision(EntityPlayer player) {
        player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 1));
        player.attackEntityFrom(OPUtils.causePlayerCustomDamage(owner,true), MathUtils.calculateDamage(player, 6F, true));
        player.hurtResistantTime = 20;
    }

    @Override
    public int getMaxTicks() {
        return 80;
    }

    @Override
    public void onValidEntityCollision(Entity entity) {
        EntityLiving e = (EntityLiving)entity;
        e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 1));
        entity.attackEntityFrom(OPUtils.causePlayerCustomDamage(owner,true), 6F);
        entity.hurtResistantTime = 20;
    }

    @Override
    public void spawnParticles() {
        for(int i = 0; i < 25; i++){
            double offsetX = ((double)rand.nextInt(20)+ 1 -10) / 20;
            double offsetY = ((double)rand.nextInt(20)+ 1 -10) / 20 + 0.5;
            double offsetZ = ((double)rand.nextInt(20)+ 1 -10) / 20;

            this.world.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0, 0, 0);
        }
    }

    public float getCollisionBorderSize()
    {
        return 2.0F;
    }

}
