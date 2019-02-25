package georgetsak.opcraft.common.network.packets;

import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

public class ItemStackChangeServerPacket extends AbstractMessage<ItemStackChangeServerPacket>{

    ItemStack itemStack;

    public ItemStackChangeServerPacket(){}

    public ItemStackChangeServerPacket(ItemStack itemStack){
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
            System.out.println("SERVER");

            player.inventory.setInventorySlotContents(9, itemStack);
            PacketDispatcher.sendTo(new ItemStackChangeServerPacket(itemStack), (EntityPlayerMP)player);
        }
        else if(side.isClient()){
            System.out.println("CLIENT");
            Minecraft.getMinecraft().player.inventory.setInventorySlotContents(9, itemStack);
        }
    }
}
