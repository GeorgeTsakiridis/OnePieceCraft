package georgetsak.opcraft.common.network.packets;

import georgetsak.opcraft.common.capability.bounty.BountyCapProvider;
import georgetsak.opcraft.common.capability.bounty.IBountyCap;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

/**
 * Created by GeorgeProgramming on 7/12/2017.
 */
public class BountyPacket extends AbstractMessage<BountyPacket>{

    int bounty;

    public BountyPacket(){}
    public BountyPacket(IBountyCap b){
        bounty = b.getBounty();
    }

    @Override
    protected void read(PacketBuffer buffer) {
        bounty = buffer.readInt();
    }

    @Override
    protected void write(PacketBuffer buffer) {
        buffer.writeInt(bounty);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        if(side.isClient()){
            IBountyCap b = player.getCapability(BountyCapProvider.B_CAP, null);
            b.setBounty(bounty);
            String str = I18n.format(TextFormatting.WHITE + "Your Bounty is ");
            String str2 = I18n.format(TextFormatting.GOLD + String.valueOf(bounty) + " B");
            String str3 = I18n.format(TextFormatting.WHITE + " !");
            player.sendMessage(new TextComponentString(str + str2 + str3));
        }
    }
}
