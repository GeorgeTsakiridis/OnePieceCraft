package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.common.util.MathUtils;
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

    public void onCollideWithPlayer(EntityPlayer player) {
        if (!isCollisionWithPlayerValid(player)) return;
        player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 1));
        player.attackEntityFrom(DamageSource.causePlayerDamage(owner), MathUtils.calculateDamage(player, 6F, true));
        player.hurtResistantTime = 20;
    }

    @Override
    public int getMaxTicks() {
        return 80;
    }

    public void collideWithEntity(Entity entityIn){
        if(!isCollisionWithEntityValid(entityIn))return;
            EntityLiving e = (EntityLiving)entityIn;
            e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 1));
            entityIn.attackEntityFrom(DamageSource.causePlayerDamage(owner), 6F);
            entityIn.hurtResistantTime = 20;
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
