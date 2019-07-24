package georgetsak.opcraft.common.network.packets.client;

import georgetsak.opcraft.client.proxy.ClientProxy;
import georgetsak.opcraft.common.crew.Crew;
import georgetsak.opcraft.common.crew.CrewSaveData;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;
import java.util.ArrayList;

public class SyncCrewClientPacket extends AbstractMessage.AbstractClientMessage<SyncCrewClientPacket> {

    ArrayList<Crew> crew;

    public SyncCrewClientPacket(){}

    public SyncCrewClientPacket(ArrayList<Crew> crew){
        this.crew = crew;
    }

    @Override
    protected void read(PacketBuffer buffer) throws IOException {
        try {

            crew = (ArrayList<Crew>) CrewSaveData.toObject(buffer.readByteArray());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void write(PacketBuffer buffer) throws IOException {
        buffer.writeByteArray(CrewSaveData.toByteArray(crew));
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        if (side.isClient()) {
            ClientProxy.crews = crew;
            OPUtils.refreshStats(player);
        }
    }
}
