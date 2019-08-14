package georgetsak.opcraft.common.network.packets.client;

import georgetsak.opcraft.client.proxy.ClientProxy;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

public class PacketCrewInviteClient extends AbstractMessage.AbstractClientMessage<PacketCrewInviteClient> {

    String name;

    public PacketCrewInviteClient(){}

    public PacketCrewInviteClient(String name) {
        this.name = name;
    }

    @Override
    protected void read(PacketBuffer buffer) throws IOException {
        name = buffer.readString(20);
    }

    @Override
    protected void write(PacketBuffer buffer) throws IOException {
        buffer.writeString(name);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        if(side.isClient()){
            ClientProxy.crewLastInviteName = name;
        }
    }

}
