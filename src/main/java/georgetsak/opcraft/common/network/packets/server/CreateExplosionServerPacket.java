package georgetsak.opcraft.common.network.packets.server;

import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;

public class CreateExplosionServerPacket extends AbstractMessage.AbstractServerMessage<CreateExplosionServerPacket> {

    BlockPos pos;
    int strength;

    public CreateExplosionServerPacket(){}
    public CreateExplosionServerPacket(BlockPos pos, int strength){
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
        OPUtils.createExplosion(player, player.world, pos.getX(), pos.getY(), pos.getZ(), strength, true);
    }
}
