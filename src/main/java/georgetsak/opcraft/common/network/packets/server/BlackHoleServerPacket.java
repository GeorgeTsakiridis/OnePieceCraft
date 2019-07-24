package georgetsak.opcraft.common.network.packets.server;

import georgetsak.opcraft.common.entity.devilfruit.EntityDark;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

public class BlackHoleServerPacket extends AbstractMessage.AbstractServerMessage<BlackHoleServerPacket> {

    BlockPos spawnPosition;

    public BlackHoleServerPacket(){}

    public BlackHoleServerPacket(BlockPos spawnPosition){
        this.spawnPosition = spawnPosition;
    }

    @Override
    protected void read(PacketBuffer buffer) throws IOException {
        this.spawnPosition = buffer.readBlockPos();
    }

    @Override
    protected void write(PacketBuffer buffer) throws IOException {
        buffer.writeBlockPos(spawnPosition);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        if (side.isServer()) {
            if (spawnPosition != null) {
                player.world.spawnEntity(new EntityDark(player.world, spawnPosition, player));
            }
        }
    }
}
