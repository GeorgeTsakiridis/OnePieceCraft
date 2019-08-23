package georgetsak.opcraft.common.block.tile;

import georgetsak.opcraft.common.capability.devilfruits.DevilFruitCap;
import georgetsak.opcraft.common.capability.devilfruits.IDevilFruitCap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;
import java.util.Random;

public class SmokeCloudTileEntity extends TileEntity implements ITickable {

    int ticksExisted = 0;
    Random r = new Random();

    @Override
    public void update() {
        double distance = 12.0D;
        double x1 = pos.getX() - distance;
        double x2 = pos.getX() + distance;
        double y1 = pos.getY() - distance;
        double y2 = pos.getY() + distance;
        double z1 = pos.getZ() - distance;
        double z2 = pos.getZ() + distance;

        List<Entity> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(x1, y1, z1, x2, y2, z2));
        List<Entity> players = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(x1, y1, z1, x2, y2, z2));

        for(int i = 0; i < 400; i++) {
                world.spawnParticle(EnumParticleTypes.CLOUD, pos.getX() + (r.nextDouble()*20d - 10), pos.getY() + (r.nextDouble()*20d - 10), pos.getZ() + (r.nextDouble()*20d - 10), 0, 0, 0);
        }

        if (!players.isEmpty()) {
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i) != null && players.get(i) instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) players.get(i);

                    IDevilFruitCap devilFruitsCap = DevilFruitCap.get(player);
                    if (devilFruitsCap.getPower() != 11) {//Players with moku moku (smoke) won't be affected
                        player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 3));
                    }

                }
            }
        }

        if (!entities.isEmpty()) {
            for (int i = 0; i < entities.size(); i++) {
                if (entities.get(i) != null && entities.get(i) instanceof EntityLiving) {
                    EntityLiving el = (EntityLiving) entities.get(i);
                    el.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 3));
                }
            }
        }

        if(ticksExisted > 100){
            //this.world.playSound((EntityPlayer)null,  this.getPos(), OPSoundEvent.dome_disappear, SoundCategory.NEUTRAL, 40.0F, 1.0F);
            world.setBlockToAir(this.pos);
        }
        ticksExisted++;

    }

}
