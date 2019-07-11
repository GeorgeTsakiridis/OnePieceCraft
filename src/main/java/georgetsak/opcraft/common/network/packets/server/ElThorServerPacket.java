package georgetsak.opcraft.common.network.packets.server;

import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

public class ElThorServerPacket extends AbstractMessage.AbstractServerMessage<ElThorServerPacket> {

    BlockPos spawnPosition;

    public ElThorServerPacket(){}

    public ElThorServerPacket(BlockPos spawnPosition){
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
                double x = spawnPosition.getX();
                double y = spawnPosition.getY();
                double z = spawnPosition.getZ();
                for (int i = 0; i < 6; i++) {
                    EntityLightningBolt lightningBolt = new EntityLightningBolt(player.world, x, y, z, false);
                    player.world.addWeatherEffect(lightningBolt);
                }
            }
        }
    }
}
