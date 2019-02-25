package georgetsak.opcraft.client.gui.shipbuilder;

import georgetsak.opcraft.common.registry.OPBlocks;
import georgetsak.opcraft.common.registry.OPRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.network.play.server.SPacketSetSlot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by GeorgeTsak on 8/2/2017.
 */

public class ContainerCraftingShipBuilder extends Container
{

    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 10, 5);
    public InventoryCraftResult craftResult = new InventoryCraftResult();
    private final World world;

    private final BlockPos pos;
    private final EntityPlayer player;

    public ContainerCraftingShipBuilder(InventoryPlayer playerInventory, World worldIn, BlockPos posIn) {
        int index = 0;
        int index2 = 0;
        this.world = worldIn;
        this.pos = posIn;
        this.player = playerInventory.player;

        for(int i = 0; i < 5; i++) {
            for(int k = 0; k < 10; k++){
                this.addSlotToContainer(new SingleItemSlot(this.craftMatrix, index, 8 + 18*k, 17 + 18*i));
                index++;
            }
        }

        this.addSlotToContainer(new SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult, index, 228, 44));

        for (int l = 0; l < 9; ++l)
        {
            this.addSlotToContainer(new Slot(player.inventory, index2, 45 + l * 18, 180));
            index2++;
        }


        for (int k = 0; k < 3; ++k)
        {
            for (int i1 = 0; i1 < 9; ++i1)
            {
                this.addSlotToContainer(new Slot(player.inventory, index2, 45 + i1 * 18, 122 + k * 18));
                index2++;
            }
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        this.slotChangedCraftingGrid(this.world, this.player, this.craftMatrix, this.craftResult);
    }

    @Override
    protected void slotChangedCraftingGrid(World world, EntityPlayer player, InventoryCrafting inventoryCrafting, InventoryCraftResult craftResult)
    {
        if (!world.isRemote)
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)player;
            ItemStack itemstack = ItemStack.EMPTY;
            IRecipe irecipe = CraftingManager.findMatchingRecipe(inventoryCrafting, world);

            if (irecipe != null && OPRecipes.ships.contains(irecipe.getRecipeOutput()) &&(irecipe.isDynamic() || !world.getGameRules().getBoolean("doLimitedCrafting") || entityplayermp.getRecipeBook().isUnlocked(irecipe)))
            {
                craftResult.setRecipeUsed(irecipe);
                itemstack = irecipe.getCraftingResult(inventoryCrafting);
            }

            craftResult.setInventorySlotContents(50, itemstack);
            entityplayermp.connection.sendPacket(new SPacketSetSlot(this.windowId, 50, itemstack));
        }
    }

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);

        if (!this.world.isRemote) {
            this.clearContainer(playerIn, this.world, this.craftMatrix);
        }
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(EntityPlayer playerIn) {
        if (this.world.getBlockState(this.pos).getBlock() != OPBlocks.BlockShipBuilder) {
            return false;
        } else {
            return playerIn.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */

    /**
     * INDEX
     * Custom Slots 0-49
     * Result Slot 50
     * Player Inventory Big (Small): 51-77 (78-86)
     */

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 50) {
                itemstack1.getItem().onCreated(itemstack1, this.world, playerIn);

                if (!this.mergeItemStack(itemstack1, 51, 86, false)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index < 51) {
                if (!this.mergeItemStack(itemstack1, 51, 86, false)) {
                    return ItemStack.EMPTY;
                }
            } else{
                if(index <= 77) {
                    if (!this.mergeItemStack(itemstack1, 78, 86, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                else if(index <= 86){
                    if (!this.mergeItemStack(itemstack1, 51, 77, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);

            if (index == 50) {
                playerIn.dropItem(itemstack2, false);
            }
        }

        return itemstack;
    }

    /**
     * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in
     * is null for the initial slot that was double-clicked.
     */
    public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
        return slotIn.inventory != this.craftResult && super.canMergeSlot(stack, slotIn);
    }

    public class SingleItemSlot extends Slot {

        public SingleItemSlot(IInventory inventory, int index, int xPosition, int yPosition) {
            super(inventory, index, xPosition, yPosition);
        }

        @Override
        public int getSlotStackLimit() {
            return 1;
        }
    }


}