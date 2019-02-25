package georgetsak.opcraft.common.network.packets;

import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

public class CreateExplosionPacket extends AbstractMessage.AbstractServerMessage<CreateExplosionPacket> {

    BlockPos pos;
    int strength;

    public CreateExplosionPacket(){}
    public CreateExplosionPacket(BlockPos pos, int strength){
        this.pos = pos;
        this.strength = strength;
    }

    @Override
    protected void read(PacketBuffer buffer) {
        pos = buffer.readBlockPos();
        strength = buffer.readInt();
    }

    @Override
    protected void write(PacketBuffer buffer) {
        buffer.writeBlockPos(pos);
        buffer.writeInt(strength);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        player.world.createExplosion(player, pos.getX(), pos.getY(), pos.getZ(), strength, true);
    }
}
