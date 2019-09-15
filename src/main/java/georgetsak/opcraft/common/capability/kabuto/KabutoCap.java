package georgetsak.opcraft.common.capability.kabuto;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;

public class KabutoCap implements IKabutoCap{

    private InventoryBasic inventory = new InventoryBasic("Kabuto", true, 1);

    public static IKabutoCap get(ItemStack itemStack)
    {
        return itemStack.getCapability(KabutoCapProvider.K_CAP, null);
    }

    @Override
    public ItemStack getItemStackStored() {
        return inventory.getStackInSlot(0);
    }

    @Override
    public void setItemStack(ItemStack itemStack) {
        inventory.setInventorySlotContents(0, itemStack);
    }

    @Override
    public IInventory getInventory() {
        return inventory;
    }
}
