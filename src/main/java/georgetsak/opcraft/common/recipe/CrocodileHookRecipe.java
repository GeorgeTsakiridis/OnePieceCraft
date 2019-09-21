package georgetsak.opcraft.common.recipe;

import georgetsak.opcraft.common.registry.OPItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

	public class CrocodileHookRecipe extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

		@Override
		public boolean matches(InventoryCrafting inv, World worldIn) {

			ItemStack goldIngot = new ItemStack(Items.GOLD_INGOT, 1);
			ItemStack goldBlock = new ItemStack(Blocks.GOLD_BLOCK, 1);
			ItemStack ironIngot = new ItemStack(Items.IRON_INGOT, 1);
			ItemStack poison = new ItemStack(Items.POTIONITEM);
			PotionUtils.addPotionToItemStack(poison, PotionTypes.POISON);

			boolean flag1 = inv.getStackInRowAndColumn(0, 1) != null;
			boolean flag2 = inv.getStackInRowAndColumn(0, 2) != null;
			boolean flag3 = inv.getStackInRowAndColumn(1, 0) != null;
			boolean flag4 = inv.getStackInRowAndColumn(1, 1) != null;
			boolean flag5 = inv.getStackInRowAndColumn(2, 0) != null;
			boolean flag6 = inv.getStackInRowAndColumn(1, 2) != null;

			ItemStack is1 = inv.getStackInRowAndColumn(0, 1);
			ItemStack is2 = inv.getStackInRowAndColumn(0, 2);
			ItemStack is3 = inv.getStackInRowAndColumn(1, 0);
			ItemStack is4 = inv.getStackInRowAndColumn(1, 1);
			ItemStack is5 = inv.getStackInRowAndColumn(2, 0);
			ItemStack is6 = inv.getStackInRowAndColumn(1, 2);

			if (flag1 && flag2 && flag3 && flag4 && flag5 && flag6) {
				if (is1.getItem() == goldBlock.getItem() && is6.getItem() == goldBlock.getItem() && is2.getItem() == goldIngot.getItem() && is4.getItem() == goldIngot.getItem() && is5.getItem() == ironIngot.getItem() && is3.getItem() == poison.getItem()) {

					boolean flag1a = PotionUtils.getEffectsFromStack(is3).equals(PotionTypes.POISON.getEffects());
					boolean flag2a = PotionUtils.getEffectsFromStack(is3).equals(PotionTypes.LONG_POISON.getEffects());
					boolean flag3a = PotionUtils.getEffectsFromStack(is3).equals(PotionTypes.STRONG_POISON.getEffects());

					return flag1a || flag2a || flag3a;
				}

			}
			return false;
		}

		@Override
		public ItemStack getCraftingResult(InventoryCrafting inv) {
			return new ItemStack(OPItems.CROCODILE_HOOK_CASED);
		}

		@Override
		public boolean canFit(int width, int height) {
			return width >= 3 && height >= 3;
		}

		@Override
		public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
			NonNullList<ItemStack> nonnulllist = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

			for (int i = 0; i < nonnulllist.size(); ++i) {
				ItemStack itemstack = inv.getStackInSlot(i);
				nonnulllist.set(i, net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack));
			}

			return nonnulllist;
		}

		@Override
		public ItemStack getRecipeOutput() {
			return new ItemStack(OPItems.CROCODILE_HOOK_CASED);
		}

	}
