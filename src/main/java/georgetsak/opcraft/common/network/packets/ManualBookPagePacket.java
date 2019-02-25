package georgetsak.opcraft.common.network.packets;

import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

public class ManualBookPagePacket extends AbstractMessage.AbstractServerMessage<ManualBookPagePacket>{

    int page;

    public ManualBookPagePacket(){}

    public ManualBookPagePacket(int page){
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
