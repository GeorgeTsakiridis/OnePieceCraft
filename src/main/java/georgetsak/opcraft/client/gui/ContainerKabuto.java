package georgetsak.opcraft.client.gui;

import georgetsak.opcraft.common.capability.kabuto.KabutoCap;
import georgetsak.opcraft.common.capability.kabuto.KabutoCapProvider;
import georgetsak.opcraft.common.item.ItemDial;
import georgetsak.opcraft.common.registry.OPItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerKabuto extends Container {

    public IInventory inventoryKabuto;
    World world;
    EntityPlayer player;

    public ContainerKabuto(InventoryPlayer playerInventory, ItemStack kabutoStack, World worldIn) {
        int index = 0;
        this.world = worldIn;
        this.player = playerInventory.player;
        this.inventoryKabuto = KabutoCap.get(kabutoStack).getInventory();

        this.addSlotToContainer(new KabutoItemSlot(inventoryKabuto,0,83,19));

        for (int l = 0; l < 9; ++l) {
            if(playerInventory.getSlotFor(kabutoStack) == index){
                this.addSlotToContainer(new LockedSlot(player.inventory, index, 11 + l * 18, 109));
            }else {
                this.addSlotToContainer(new Slot(player.inventory, index, 11 + l * 18, 109));
            }
            index++;
        }


        for (int k = 0; k < 3; ++k)
        {
            for (int i1 = 0; i1 < 9; ++i1)
            {
                this.addSlotToContainer(new Slot(player.inventory, index, 11 + i1 * 18, 51 + k * 18));
                index++;
            }
        }
    }

    /**
     * INDEX
     *
     * Kabuto Slot 0
     * Player Inventory 1 - 35
     */

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index > 0)
            {
                if (!this.mergeItemStack(itemstack1, 0, 0, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 1, 35, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        inventoryKabuto.setInventorySlotContents(0,inventoryKabuto.getStackInSlot(0));
        //super.onContainerClosed(playerIn);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    public class KabutoItemSlot extends Slot {

        public KabutoItemSlot(IInventory inventory, int index, int xPosition, int yPosition) {
            super(inventory, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack) {
            Item item = stack.getItem();
            return item instanceof ItemDial && item != OPItems.ItemEmptyDial || item == OPItems.ItemSmallRock;
        }

    }

    public class LockedSlot extends Slot {

        public LockedSlot(IInventory inventory, int index, int xPosition, int yPosition) {
            super(inventory, index, xPosition, yPosition);
        }

        @Override
        public boolean canTakeStack(EntityPlayer playerIn) {
            return false;
        }

        @Override
        public void putStack(ItemStack stack) {
            super.putStack(stack);
        }
    }


}
