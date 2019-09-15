package georgetsak.opcraft.common.capability.kabuto;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface IKabutoCap {

    ItemStack getItemStackStored();
    void setItemStack(ItemStack itemStack);

    IInventory getInventory();

}
