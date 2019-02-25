package georgetsak.opcraft.common.network.packets;

import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

/**
 * Created by GeorgeTsak on 7/15/2017.
 */
public class UpdateXPLevelsPacket extends AbstractMessage<UpdateXPLevelsPacket>{

    int xp;

    public UpdateXPLevelsPacket(){}
    public UpdateXPLevelsPacket(int xp ){
        this.xp = xp;
    }

    @Override
    protected void read(PacketBuffer buffer) throws IOException {
        this.xp = buffer.readInt();
    }

    @Override
    protected void write(PacketBuffer buffer) throws IOException {
        buffer.writeInt(xp);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        if(side.isClient()){
            Minecraft.getMinecraft().player.experienceLevel = xp;
        }
    }

}
