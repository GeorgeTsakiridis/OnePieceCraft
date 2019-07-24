package georgetsak.opcraft.common.network.packets.common;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.config.ConfigEntry;
import georgetsak.opcraft.common.config.ConfigEntryBoolean;
import georgetsak.opcraft.common.config.ConfigEntryInt;
import georgetsak.opcraft.common.util.OPUtils;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

public class ConfigPacket extends AbstractMessage<ConfigPacket> {

    public ConfigPacket() {
        System.out.println("CONFIG PACKET");
    }

    @Override
    protected void read(PacketBuffer buffer) throws IOException {

        for(ConfigEntry configEntry : OPCraft.config.getConfigEntries()){
            if(configEntry instanceof ConfigEntryBoolean){
                ((ConfigEntryBoolean) configEntry).setCurrentValue(buffer.readBoolean());
            }

            else if(configEntry instanceof ConfigEntryInt){
                ((ConfigEntryInt) configEntry).setCurrentValue(buffer.readInt());
            }

        }

    }

    @Override
    protected void write(PacketBuffer buffer) throws IOException {
        for(ConfigEntry configEntry : OPCraft.config.getConfigEntries()){
            if(configEntry instanceof ConfigEntryBoolean){
                buffer.writeBoolean(((ConfigEntryBoolean) configEntry).getCurrentValue());
            }

            else if(configEntry instanceof ConfigEntryInt){
                buffer.writeInt(((ConfigEntryInt) configEntry).getCurrentValue());
            }

        }
    }

    @Override
    public void process(EntityPlayer player, Side side) {

    }

}