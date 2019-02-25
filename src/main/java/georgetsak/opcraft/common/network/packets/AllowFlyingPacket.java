package georgetsak.opcraft.common.network.packets;

import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

public class AllowFlyingPacket extends AbstractMessage.AbstractServerMessage<AllowFlyingPacket>{

    boolean allow;

    public AllowFlyingPacket(){}

    public AllowFlyingPacket(boolean allow){
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
