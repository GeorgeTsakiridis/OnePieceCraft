package georgetsak.opcraft.common.network.packets.server;

import georgetsak.opcraft.common.crew.CrewSaveData;
import georgetsak.opcraft.common.network.packets.client.SyncCrewClientPacket;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

public class RequestCrewUpdateServerPacket extends AbstractMessage.AbstractServerMessage<RequestCrewUpdateServerPacket> {

    public RequestCrewUpdateServerPacket(){}

    @Override
    protected void read(PacketBuffer buffer) throws IOException {

    }

    @Override
    protected void write(PacketBuffer buffer) throws IOException {

    }

    @Override
    public void process(EntityPlayer player, Side side) {
        if (side.isServer()) {
            PacketDispatcher.sendToAll(new SyncCrewClientPacket(CrewSaveData.get(player.world).getCrews()));
        }
    }
}
