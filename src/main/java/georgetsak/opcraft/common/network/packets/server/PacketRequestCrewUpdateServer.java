package georgetsak.opcraft.common.network.packets.server;

import georgetsak.opcraft.common.crew.CrewSaveData;
import georgetsak.opcraft.common.network.packets.client.PacketSyncCrewClient;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

public class PacketRequestCrewUpdateServer extends AbstractMessage.AbstractServerMessage<PacketRequestCrewUpdateServer> {

    public PacketRequestCrewUpdateServer(){}

    @Override
    protected void read(PacketBuffer buffer) throws IOException {

    }

    @Override
    protected void write(PacketBuffer buffer) throws IOException {

    }

    @Override
    public void process(EntityPlayer player, Side side) {
        if (side.isServer()) {
            PacketDispatcher.sendToAll(new PacketSyncCrewClient(CrewSaveData.get(player.world).getCrews()));
        }
    }
}
