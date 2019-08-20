package georgetsak.opcraft.common.network.packets.common;

import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

public class PacketItemStackChange extends AbstractMessage<PacketItemStackChange>{

    ItemStack itemStack;

    public PacketItemStackChange(){}

    public PacketItemStackChange(ItemStack itemStack){
        this.itemStack = itemStack;
    }

    @Override
    protected void read(PacketBuffer buffer) throws IOException {
        itemStack = buffer.readItemStack();
    }

    @Override
    protected void write(PacketBuffer buffer) throws IOException {
        buffer.writeItemStack(itemStack);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        if(side.isServer()){
            player.inventory.setInventorySlotContents(9, itemStack);
            PacketDispatcher.sendTo(new PacketItemStackChange(itemStack), (EntityPlayerMP)player);
        }
        else if(side.isClient()){
            Minecraft.getMinecraft().player.inventory.setInventorySlotContents(9, itemStack);
        }
    }
}
