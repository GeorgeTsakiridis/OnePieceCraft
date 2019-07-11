package georgetsak.opcraft.common.network.packets.server;

import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

public class ManualBookPageServerPacket extends AbstractMessage.AbstractServerMessage<ManualBookPageServerPacket>{

    int page;

    public ManualBookPageServerPacket(){}

    public ManualBookPageServerPacket(int page){
        this.page = page;
    }

    @Override
    protected void read(PacketBuffer buffer) throws IOException {
        page = buffer.readInt();
    }

    @Override
    protected void write(PacketBuffer buffer) throws IOException {
        buffer.writeInt(page);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        if(player != null){
            player.inventory.getCurrentItem().getTagCompound().setInteger("page", page);
        }
    }
}
