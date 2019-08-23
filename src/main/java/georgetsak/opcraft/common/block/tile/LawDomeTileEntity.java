package georgetsak.opcraft.common.block.tile;

import georgetsak.opcraft.common.block.GenerateDome;
import georgetsak.opcraft.common.network.proxy.CommonProxy;
import georgetsak.opcraft.client.OPSoundEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

public class LawDomeTileEntity extends TileEntity implements ITickable {

    int ticksExisted = 0;

    @Override
    public void update() {
        double distance = 19.0D;
        double x1 = pos.getX() - distance;
        double x2 = pos.getX() + distance;
        double y1 = pos.getY() - distance;
        double y2 = pos.getY() + distance;
        double z1 = pos.getZ() - distance;
        double z2 = pos.getZ() + distance;

        List<Entity> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(x1, y1, z1, x2, y2, z2));
        List<Entity> players = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(x1, y1, z1, x2, y2, z2));

        if (!players.isEmpty()) {
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i) != null && players.get(i) instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) players.get(i);
                    player.addPotionEffect(new PotionEffect(CommonProxy.effectInsideDome, 10, 0));
                }
            }
        }

        if (!entities.isEmpty()) {
            for (int i = 0; i < entities.size(); i++) {
                if (entities.get(i) != null && entities.get(i) instanceof EntityLiving) {
                    EntityLiving el = (EntityLiving) entities.get(i);
                    el.addPotionEffect(new PotionEffect(CommonProxy.effectInsideDome, 10, 0));
                }
            }
        }

        if(ticksExisted > 300){
            this.world.playSound(null,  this.getPos(), OPSoundEvent.dome_disappear, SoundCategory.NEUTRAL, 40.0F, 1.0F);
            GenerateDome gd = new GenerateDome();
            gd.generate(world, pos.getX() - 25, pos.getY() - 15, pos.getZ() -20, true);

            world.setBlockToAir(this.pos);
        }
        ticksExisted++;

    }

}
