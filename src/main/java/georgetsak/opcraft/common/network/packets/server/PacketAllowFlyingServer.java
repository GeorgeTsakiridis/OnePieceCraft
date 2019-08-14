package georgetsak.opcraft.common.network.packets.server;

import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

public class PacketAllowFlyingServer extends AbstractMessage.AbstractServerMessage<PacketAllowFlyingServer>{

    boolean allow;

    public PacketAllowFlyingServer(){}

    public PacketAllowFlyingServer(boolean allow){
        this.allow = allow;
    }

    @Override
    protected void read(PacketBuffer buffer){
        allow = buffer.readBoolean();
    }

    @Override
    protected void write(PacketBuffer buffer){
        buffer.writeBoolean(allow);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        if (!player.world.isRemote) {
            player.capabilities.allowFlying = allow;
        }
    }
}
