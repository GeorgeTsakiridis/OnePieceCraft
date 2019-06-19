package georgetsak.opcraft.common.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

import java.util.Collections;

import static georgetsak.opcraft.common.registry.OPArmor.*;
import static georgetsak.opcraft.common.registry.OPBlocks.BlockDarkSteelOre;
import static georgetsak.opcraft.common.registry.OPBlocks.BlockKairosekiStone;
import static georgetsak.opcraft.common.registry.OPItems.*;
import static georgetsak.opcraft.common.network.proxy.CommonProxy.merchantRecipes;

/**
 * Created by GeorgeTsak on 8/7/2017.
 */
public class OPMerchantTrades {

    public static void registerMerchantTrades(){
        merchantRecipes = new MerchantRecipeList();

        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 12), new ItemStack(ItemKairosekiGem)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 25), new ItemStack(BlockKairosekiStone)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 6), new ItemStack(ItemSteelIngot)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 6), new ItemStack(ItemSteelOre)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 12), new ItemStack(ItemDarkSteelIngot)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 12), new ItemStack(BlockDarkSteelOre)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 25), new ItemStack(ItemWadoIchiMonjiOpen)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 31), new ItemStack(ItemKitetsuOpen)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 37), new ItemStack(ItemShuusuiOpen)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 64), new ItemStack(ItemBerryCoin, 26), new ItemStack(ItemMihawkYoru)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 43), new ItemStack(ItemArlongKiribachi)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 26), new ItemStack(ItemSmokerJitte)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 16), new ItemStack(ItemBrookSwordOpen)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 36), new ItemStack(ItemCrocodileHookOpen)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 48), new ItemStack(ItemKikokuOpen)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 12), new ItemStack(ItemClimaSimple)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 23), new ItemStack(ItemClimaCompletedWater)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 3), new ItemStack(ItemFireDial)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 3), new ItemStack(ItemImpactDial)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 3), new ItemStack(ItemLavaDial)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 3), new ItemStack(ItemFireDial)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 3), new ItemStack(ItemThunderDial)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 12), new ItemStack(ItemUssopKabuto)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 17), new ItemStack(ItemUssopKabutoBlack)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 3), new ItemStack(ItemUssopKabutoAmmo, 2)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 16), new ItemStack(ItemFlintlock)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 27), new ItemStack(ItemFlintlockAmmo, 16)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 28), new ItemStack(ItemSenriku)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 3), new ItemStack(ItemSenrikuAmmo, 4)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 64), new ItemStack(ItemBerryCoin, 64), new ItemStack(ItemBazooka)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 55), new ItemStack(ItemBazookaAmmo, 4)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 15), new ItemStack(ItemCutlass)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(LuffySimpleBoots)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(LuffySimpleLeggings)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(LuffySimpleChestplate)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(LuffySimpleHelmet)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(SanjiSimpleBoots)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(SanjiSimpleLeggings)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(SanjiSimpleChestplate)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(SanjiSimpleHelmet)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(ZoroSimpleBoots)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(ZoroSimpleLeggings)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(ZoroSimpleChestplate)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(ZoroSimpleHelmet)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(UsoppSimpleBoots)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(UsoppSimpleLeggings)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(UsoppSimpleChestplate)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(UsoppSimpleHelmet)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(MarineSimpleBoots)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(MarineSimpleLeggings)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(MarineSimpleChestplate)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(MarineSimpleHelmet)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(PirateSimpleBoots)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(PirateSimpleLeggings)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(PirateSimpleChestplate)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 1), new ItemStack(ItemManualBook)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 1), new ItemStack(ItemSake)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 1), new ItemStack(ItemDevilFruitPowerRemover)));

        //TODO add item for item trades not only for money

        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemKairosekiGem), new ItemStack(ItemBerryCoin, 10)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemKairosekiStone), new ItemStack(ItemBerryCoin, 20)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemSteelIngot), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemSteelOre), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemDarkSteelIngot), new ItemStack(ItemBerryCoin, 10)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemDarkSteelOre), new ItemStack(ItemBerryCoin, 10)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemClimaSimple), new ItemStack(ItemBerryCoin, 7), new ItemStack(ItemClimaCompletedThunder, 1)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemUssopKabuto), new ItemStack(ItemBerryCoin, 5), new ItemStack(ItemUssopKabutoBlack)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemFlintlock), new ItemStack(ItemBerryCoin, 13)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemSenriku), new ItemStack(ItemBerryCoin, 23)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin), new ItemStack(ItemBerryCoin)));

        merchantRecipes.add(new MerchantRecipe(new ItemStack(LuffySimpleBoots), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(LuffySimpleLeggings), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(LuffySimpleChestplate), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(LuffySimpleHelmet), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(SanjiSimpleBoots), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(SanjiSimpleLeggings), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(SanjiSimpleChestplate), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(SanjiSimpleHelmet), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ZoroSimpleBoots), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ZoroSimpleLeggings), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ZoroSimpleChestplate), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(ZoroSimpleHelmet), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(UsoppSimpleBoots), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(UsoppSimpleLeggings), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(UsoppSimpleChestplate), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(UsoppSimpleHelmet), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(MarineSimpleBoots), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(MarineSimpleLeggings), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(MarineSimpleChestplate), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(MarineSimpleHelmet), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(PirateSimpleBoots), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(PirateSimpleLeggings), new ItemStack(ItemBerryCoin, 5)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(PirateSimpleChestplate), new ItemStack(ItemBerryCoin, 5)));

        merchantRecipes.add(new MerchantRecipe(new ItemStack(Items.DIAMOND, 2), new ItemStack(ItemKairosekiGem)));
        merchantRecipes.add(new MerchantRecipe( new ItemStack(ItemKairosekiGem), new ItemStack(Items.DIAMOND, 2)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(Items.IRON_INGOT, 5), new ItemStack(Items.COAL, 2), new ItemStack(ItemSteelIngot)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(Items.IRON_INGOT, 10), new ItemStack(ItemDarkSteelIngot)));
        merchantRecipes.add(new MerchantRecipe(new ItemStack(Blocks.GOLD_BLOCK, 5), new ItemStack(ItemCrocodileHookOpen)));

        Collections.shuffle(merchantRecipes);

    }

}
