package georgetsak.opcraft.common.network.packets.server;

import georgetsak.opcraft.common.network.packets.client.PacketUpdateXPLevelsClient;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

/**
 * Created by GeorgeTsak on 7/15/2017.
 */
public class PacketUpdateXPLevelsServer extends AbstractMessage.AbstractServerMessage<PacketUpdateXPLevelsServer>{

    int xp;

    public PacketUpdateXPLevelsServer(){}
    public PacketUpdateXPLevelsServer(int xp){
        this.xp = xp;
    }

    @Override
    protected void read(PacketBuffer buffer) throws IOException {
        this.xp = buffer.readInt();
    }

    @Override
    protected void write(PacketBuffer buffer) throws IOException {
        buffer.writeInt(this.xp);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        if(side.isServer()){
            player.experienceLevel = this.xp;
            PacketDispatcher.sendTo(new PacketUpdateXPLevelsClient(this.xp), (EntityPlayerMP)player);
        }
    }

}
