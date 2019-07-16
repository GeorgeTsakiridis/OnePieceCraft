package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.capability.devilfruits.DevilFruitsCap;
import georgetsak.opcraft.common.capability.devilfruits.DevilFruitsCapProvider;
import georgetsak.opcraft.common.capability.devilfruits.IDevilFruitsCap;
import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class EntityDark extends EntityLiving {

    EntityPlayer owner;

    public EntityDark(World worldIn) {
        super(worldIn);
        setSize(9f, 2.7f);
    }

    public EntityDark(World world, BlockPos pos, EntityPlayer owner){
        this(world);
        this.setPosition(pos.getX(), pos.getY() - 1f, pos.getZ());
        this.owner = owner;
        setEntityInvulnerable(true);
    }


    @Override
    public void applyEntityCollision(Entity entityIn) {
        return;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        Random r = new Random();
        if(world.isRemote) {
            for(double x = -4; x < 4; x++){
                for(double z = - 4; z < 4; z++) {
                    if (r.nextBoolean()) {
                        double offsetX = (r.nextDouble() * 32 - 16d) / 16d;
                        double offsetZ = (r.nextDouble() * 32 - 16d) / 16d;
                        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX + x + offsetX, posY + 2d, posZ + z + offsetZ, 0, 0d, 0);
                    }
                }
            }
        }

        if (ticksExisted >= 160 && !world.isRemote){//8 seconds
            setDead();
        }
    }

    @Override
    protected void collideWithEntity(Entity entityIn) {
        if (!world.isRemote) {
            entityIn.motionX = 0;
            entityIn.motionY = -0.01f;
            entityIn.motionZ = 0;

            if (owner != null && entityIn == owner || entityIn instanceof EntityDark) {
                return;
            }

            if (owner != null && entityIn.hurtResistantTime == 0) {
                if (entityIn instanceof EntityLiving) {
                    ((EntityLiving) (entityIn)).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 80, 9));
                    ((EntityLiving) (entityIn)).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 80, 9));
                    ((EntityLiving) (entityIn)).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 80, 9));
                    entityIn.attackEntityFrom(DamageSource.causePlayerDamage(owner), 12f);

                } else if (entityIn instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) entityIn;
                    player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 80, 9));
                    player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 80, 9));
                    player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 80, 9));

                    entityIn.attackEntityFrom(DamageSource.causePlayerDamage(owner), OPUtils.damageCalculation(player, 12.0f, true));
                }
                entityIn.hurtResistantTime = 60;
            }

        }

    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player) {
    }

}

