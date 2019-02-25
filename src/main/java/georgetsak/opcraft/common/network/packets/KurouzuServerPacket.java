package georgetsak.opcraft.common.network.packets;

import georgetsak.opcraft.client.OPSoundEvent;
import georgetsak.opcraft.common.entity.devilfruit.EntitySmokeSnake;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

public class KurouzuServerPacket extends AbstractMessage.AbstractServerMessage<KurouzuServerPacket> {

    int targetID;

    public KurouzuServerPacket(){}

    public KurouzuServerPacket(Entity target){
        this.targetID = target.getEntityId();
    }

    @Override
    protected void read(PacketBuffer buffer) {
        targetID = buffer.readInt();
    }

    @Override
    protected void write(PacketBuffer buffer) {
        buffer.writeInt(targetID);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        Entity target = player.world.getEntityByID(targetID);
        if (side.isServer()) {
            if(target != null) {
                if (target instanceof EntityLiving) {
                    target.setPosition(player.posX, player.posY + 0.5D, player.posZ);
                    ((EntityLiving) (target)).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 4));
                    ((EntityLiving) (target)).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, 4));
                }
                else if(target instanceof EntityPlayer){
                    target.setPosition(player.posX, player.posY + 0.5D, player.posZ);
                    ((EntityPlayer) (target)).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 4));
                    ((EntityPlayer) (target)).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, 4));
                }
            }
        }
    }
}
