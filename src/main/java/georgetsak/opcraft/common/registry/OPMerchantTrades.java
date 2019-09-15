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

/**
 * Created by GeorgeTsak on 8/7/2017.
 */

public class OPMerchantTrades {
    public static MerchantRecipeList villagerRecipes;
    public static MerchantRecipeList skypieanRecipes;
    public static MerchantRecipeList skypieanBankerRecipes;

    public static void registerMerchantTrades(){
        villagerRecipes = new MerchantRecipeList();
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 12), new ItemStack(ItemKairosekiGem)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 25), new ItemStack(BlockKairosekiStone)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 6), new ItemStack(ItemSteelIngot)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 6), new ItemStack(ItemSteelOre)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 12), new ItemStack(ItemDarkSteelIngot)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 12), new ItemStack(BlockDarkSteelOre)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 25), new ItemStack(ItemWadoIchiMonjiOpen)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 31), new ItemStack(ItemKitetsuOpen)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 37), new ItemStack(ItemShuusuiOpen)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 64), new ItemStack(ItemBerryCoin, 26), new ItemStack(ItemMihawkYoru)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 43), new ItemStack(ItemArlongKiribachi)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 26), new ItemStack(ItemSmokerJitte)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 16), new ItemStack(ItemBrookSwordOpen)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 36), new ItemStack(ItemCrocodileHookOpen)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 48), new ItemStack(ItemKikokuOpen)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 12), new ItemStack(ItemClimaSimple)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 23), new ItemStack(ItemClimaCompletedWater)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 12), new ItemStack(ItemUsoppKabuto)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 17), new ItemStack(ItemUsoppKabutoBlack)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 3), new ItemStack(ItemSmallRock, 2)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 16), new ItemStack(ItemFlintlock)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 27), new ItemStack(ItemFlintlockAmmo, 16)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 28), new ItemStack(ItemSenriku)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 3), new ItemStack(ItemSenrikuAmmo, 4)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 64), new ItemStack(ItemBerryCoin, 64), new ItemStack(ItemBazooka)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 55), new ItemStack(ItemBazookaAmmo, 4)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 15), new ItemStack(ItemCutlass)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(LuffySimpleBoots)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(LuffySimpleLeggings)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(LuffySimpleChestplate)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(LuffySimpleHelmet)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(SanjiSimpleBoots)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(SanjiSimpleLeggings)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(SanjiSimpleChestplate)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(SanjiSimpleHelmet)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(ZoroSimpleBoots)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(ZoroSimpleLeggings)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(ZoroSimpleChestplate)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(ZoroSimpleHelmet)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(UsoppSimpleBoots)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(UsoppSimpleLeggings)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(UsoppSimpleChestplate)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(UsoppSimpleHelmet)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(MarineSimpleBoots)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(MarineSimpleLeggings)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(MarineSimpleChestplate)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(MarineSimpleHelmet)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(PirateSimpleBoots)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(PirateSimpleLeggings)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 5), new ItemStack(PirateSimpleChestplate)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 1), new ItemStack(ItemManualBook)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 1), new ItemStack(ItemSake)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 1), new ItemStack(ItemDevilFruitPowerRemover)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 10), new ItemStack(ItemSnail)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemKairosekiGem), new ItemStack(ItemBerryCoin, 10)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemKairosekiStone), new ItemStack(ItemBerryCoin, 20)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemSteelIngot), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemSteelOre), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemDarkSteelIngot), new ItemStack(ItemBerryCoin, 10)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemDarkSteelOre), new ItemStack(ItemBerryCoin, 10)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemClimaSimple), new ItemStack(ItemBerryCoin, 7), new ItemStack(ItemClimaCompletedThunder, 1)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemUsoppKabuto), new ItemStack(ItemBerryCoin, 5), new ItemStack(ItemUsoppKabutoBlack)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemFlintlock), new ItemStack(ItemBerryCoin, 13)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ItemSenriku), new ItemStack(ItemBerryCoin, 23)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(LuffySimpleBoots), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(LuffySimpleLeggings), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(LuffySimpleChestplate), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(LuffySimpleHelmet), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(SanjiSimpleBoots), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(SanjiSimpleLeggings), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(SanjiSimpleChestplate), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(SanjiSimpleHelmet), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ZoroSimpleBoots), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ZoroSimpleLeggings), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ZoroSimpleChestplate), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(ZoroSimpleHelmet), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(UsoppSimpleBoots), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(UsoppSimpleLeggings), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(UsoppSimpleChestplate), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(UsoppSimpleHelmet), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(MarineSimpleBoots), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(MarineSimpleLeggings), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(MarineSimpleChestplate), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(MarineSimpleHelmet), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(PirateSimpleBoots), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(PirateSimpleLeggings), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(PirateSimpleChestplate), new ItemStack(ItemBerryCoin, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(Items.DIAMOND, 2), new ItemStack(ItemKairosekiGem)));
        villagerRecipes.add(new MerchantRecipe( new ItemStack(ItemKairosekiGem), new ItemStack(Items.DIAMOND, 2)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(Items.IRON_INGOT, 5), new ItemStack(Items.COAL, 2), new ItemStack(ItemSteelIngot)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(Items.IRON_INGOT, 10), new ItemStack(ItemDarkSteelIngot)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(Blocks.GOLD_BLOCK, 5), new ItemStack(ItemCrocodileHookOpen)));
        Collections.shuffle(villagerRecipes);

        skypieanRecipes = new MerchantRecipeList();
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(ItemExtolCoin, 3), new ItemStack(ItemEmptyDial, 1)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(ItemExtolCoin, 30), new ItemStack(ItemEmptyDial, 10)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(ItemExtolCoin, 10), new ItemStack(ItemFireDial, 1)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(ItemExtolCoin, 15), new ItemStack(ItemWaterDial, 1)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(ItemExtolCoin, 15), new ItemStack(ItemLavaDial, 1)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(ItemExtolCoin, 10), new ItemStack(ItemThunderDial, 1)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(ItemExtolCoin, 10), new ItemStack(ItemImpactDial, 1)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(ItemExtolCoin, 5), new ItemStack(Items.REDSTONE, 2), new ItemStack(ItemImpactDial, 1)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(ItemExtolCoin, 5), new ItemStack(Blocks.REDSTONE_BLOCK, 1), new ItemStack(ItemThunderDial, 1)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(ItemImpactDial, 1), new ItemStack(ItemExtolCoin, 6)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(ItemThunderDial, 1), new ItemStack(ItemExtolCoin, 6)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(ItemLavaDial, 1), new ItemStack(ItemExtolCoin, 10)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(ItemWaterDial, 1), new ItemStack(ItemExtolCoin, 10)));

        Collections.shuffle(skypieanRecipes);

        skypieanBankerRecipes = new MerchantRecipeList();
        skypieanBankerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 1),new ItemStack(ItemExtolCoin, 10)));
        skypieanBankerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 4),new ItemStack(ItemExtolCoin, 40)));
        skypieanBankerRecipes.add(new MerchantRecipe(new ItemStack(ItemBerryCoin, 6),new ItemStack(ItemExtolCoin, 60)));
        skypieanBankerRecipes.add(new MerchantRecipe(new ItemStack(Items.DIAMOND, 1),new ItemStack(ItemExtolCoin, 50)));
        skypieanBankerRecipes.add(new MerchantRecipe(new ItemStack(Items.GOLD_INGOT, 1),new ItemStack(ItemExtolCoin, 10)));
        skypieanBankerRecipes.add(new MerchantRecipe(new ItemStack(Items.GOLD_NUGGET, 1),new ItemStack(ItemExtolCoin, 1)));

    }

}
