package georgetsak.opcraft.common.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

import java.util.Collections;

/**
 * Created by GeorgeTsak on 8/7/2017.
 */

public class OPMerchantTrades {
    public static MerchantRecipeList villagerRecipes;
    public static MerchantRecipeList skypieanRecipes;
    public static MerchantRecipeList skypieanBankerRecipes;

    public static void registerMerchantTrades(){
        villagerRecipes = new MerchantRecipeList();
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 12), new ItemStack(OPItems.KAIROSEKI_GEM)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 25), new ItemStack(OPBlocks.KAIROSEKI_STONE)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 6), new ItemStack(OPItems.STEEL_INGOT)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 6), new ItemStack(OPBlocks.STEEL_ORE)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 12), new ItemStack(OPItems.DARK_STEEL_INGOT)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 12), new ItemStack(OPBlocks.DARK_STEEL_ORE)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 25), new ItemStack(OPItems.WADO_ICHI_MONJI_OPEN)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 31), new ItemStack(OPItems.KITETSU_OPEN)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 37), new ItemStack(OPItems.SHUUSUI_OPEN)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 64), new ItemStack(OPItems.BERRY_COIN, 26), new ItemStack(OPItems.MIHAWK_YORU)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 43), new ItemStack(OPItems.ARLONG_KIRIBACHI)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 26), new ItemStack(OPItems.SMOKER_JITTE)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 16), new ItemStack(OPItems.BROOK_SWORD_OPEN)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 36), new ItemStack(OPItems.CROCODILE_HOOK_OPEN)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 48), new ItemStack(OPItems.KIKOKU_OPEN)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 12), new ItemStack(OPItems.CLIMA_SIMPLE)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 12), new ItemStack(OPItems.USOPP_KABUTO)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 17), new ItemStack(OPItems.USOPP_KABUTO_BLACK)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 3), new ItemStack(OPItems.SMALL_ROCK, 2)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 16), new ItemStack(OPItems.FLINTLOCK)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 27), new ItemStack(OPItems.FLINTLOCK_AMMO, 16)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 28), new ItemStack(OPItems.SENRIKU)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 3), new ItemStack(OPItems.SENRIKU_AMMO, 4)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 64), new ItemStack(OPItems.BERRY_COIN, 64), new ItemStack(OPItems.BAZOOKA)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 55), new ItemStack(OPItems.BAZOOKA_AMMO, 4)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 15), new ItemStack(OPItems.CUTLASS)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.LuffySimpleBoots)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.LuffySimpleLeggings)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.LuffySimpleChestplate)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.LuffySimpleHelmet)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.SanjiSimpleBoots)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.SanjiSimpleLeggings)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.SanjiSimpleChestplate)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.SanjiSimpleHelmet)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.ZoroSimpleBoots)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.ZoroSimpleLeggings)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.ZoroSimpleChestplate)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.ZoroSimpleHelmet)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.UsoppSimpleBoots)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.UsoppSimpleLeggings)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.UsoppSimpleChestplate)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.UsoppSimpleHelmet)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.MarineSimpleBoots)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.MarineSimpleLeggings)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.MarineSimpleChestplate)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.MarineSimpleHelmet)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.PirateSimpleBoots)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.PirateSimpleLeggings)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPArmor.PirateSimpleChestplate)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 1), new ItemStack(OPItems.MANUAL_BOOK)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 1), new ItemStack(OPItems.SAKE)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 1), new ItemStack(OPItems.DEVIL_FRUIT_POWER_REMOVER)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 10), new ItemStack(OPBlocks.SNAIL)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.KAIROSEKI_GEM), new ItemStack(OPItems.BERRY_COIN, 10)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPBlocks.KAIROSEKI_STONE), new ItemStack(OPItems.BERRY_COIN, 20)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.STEEL_INGOT), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPBlocks.STEEL_ORE), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.DARK_STEEL_INGOT), new ItemStack(OPItems.BERRY_COIN, 10)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPBlocks.DARK_STEEL_ORE), new ItemStack(OPItems.BERRY_COIN, 10)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.CLIMA_SIMPLE), new ItemStack(OPItems.BERRY_COIN, 7), new ItemStack(OPItems.CLIMA_COMPLETED_THUNDER, 1)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.USOPP_KABUTO), new ItemStack(OPItems.BERRY_COIN, 5), new ItemStack(OPItems.USOPP_KABUTO_BLACK)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.FLINTLOCK), new ItemStack(OPItems.BERRY_COIN, 13)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.SENRIKU), new ItemStack(OPItems.BERRY_COIN, 23)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.LuffySimpleBoots), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.LuffySimpleLeggings), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.LuffySimpleChestplate), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.LuffySimpleHelmet), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.SanjiSimpleBoots), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.SanjiSimpleLeggings), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.SanjiSimpleChestplate), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.SanjiSimpleHelmet), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.ZoroSimpleBoots), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.ZoroSimpleLeggings), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.ZoroSimpleChestplate), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.ZoroSimpleHelmet), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.UsoppSimpleBoots), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.UsoppSimpleLeggings), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.UsoppSimpleChestplate), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.UsoppSimpleHelmet), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.MarineSimpleBoots), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.MarineSimpleLeggings), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.MarineSimpleChestplate), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.MarineSimpleHelmet), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.PirateSimpleBoots), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.PirateSimpleLeggings), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(OPArmor.PirateSimpleChestplate), new ItemStack(OPItems.BERRY_COIN, 5)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(Items.DIAMOND, 2), new ItemStack(OPItems.KAIROSEKI_GEM)));
        villagerRecipes.add(new MerchantRecipe( new ItemStack(OPItems.KAIROSEKI_GEM), new ItemStack(Items.DIAMOND, 2)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(Items.IRON_INGOT, 5), new ItemStack(Items.COAL, 2), new ItemStack(OPItems.STEEL_INGOT)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(Items.IRON_INGOT, 10), new ItemStack(OPItems.DARK_STEEL_INGOT)));
        villagerRecipes.add(new MerchantRecipe(new ItemStack(Blocks.GOLD_BLOCK, 5), new ItemStack(OPItems.CROCODILE_HOOK_OPEN)));
        Collections.shuffle(villagerRecipes);

        skypieanRecipes = new MerchantRecipeList();
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(OPItems.EXTOL_COIN, 3), new ItemStack(OPItems.EMPTY_DIAL, 1)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(OPItems.EXTOL_COIN, 30), new ItemStack(OPItems.EMPTY_DIAL, 10)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(OPItems.EXTOL_COIN, 10), new ItemStack(OPItems.FIRE_DIAL, 1)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(OPItems.EXTOL_COIN, 15), new ItemStack(OPItems.WATER_DIAL, 1)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(OPItems.EXTOL_COIN, 15), new ItemStack(OPItems.LAVA_DIAL, 1)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(OPItems.EXTOL_COIN, 10), new ItemStack(OPItems.THUNDER_DIAL, 1)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(OPItems.EXTOL_COIN, 10), new ItemStack(OPItems.IMPACT_DIAL, 1)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(OPItems.EXTOL_COIN, 5), new ItemStack(Items.REDSTONE, 2), new ItemStack(OPItems.IMPACT_DIAL, 1)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(OPItems.EXTOL_COIN, 5), new ItemStack(Blocks.REDSTONE_BLOCK, 1), new ItemStack(OPItems.THUNDER_DIAL, 1)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(OPItems.IMPACT_DIAL, 1), new ItemStack(OPItems.EXTOL_COIN, 6)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(OPItems.THUNDER_DIAL, 1), new ItemStack(OPItems.EXTOL_COIN, 6)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(OPItems.LAVA_DIAL, 1), new ItemStack(OPItems.EXTOL_COIN, 10)));
        skypieanRecipes.add(new MerchantRecipe(new ItemStack(OPItems.WATER_DIAL, 1), new ItemStack(OPItems.EXTOL_COIN, 10)));

        Collections.shuffle(skypieanRecipes);

        skypieanBankerRecipes = new MerchantRecipeList();
        skypieanBankerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 1),new ItemStack(OPItems.EXTOL_COIN, 10)));
        skypieanBankerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 4),new ItemStack(OPItems.EXTOL_COIN, 40)));
        skypieanBankerRecipes.add(new MerchantRecipe(new ItemStack(OPItems.BERRY_COIN, 6),new ItemStack(OPItems.EXTOL_COIN, 60)));
        skypieanBankerRecipes.add(new MerchantRecipe(new ItemStack(Items.DIAMOND, 1),new ItemStack(OPItems.EXTOL_COIN, 50)));
        skypieanBankerRecipes.add(new MerchantRecipe(new ItemStack(Items.GOLD_INGOT, 1),new ItemStack(OPItems.EXTOL_COIN, 10)));
        skypieanBankerRecipes.add(new MerchantRecipe(new ItemStack(Items.GOLD_NUGGET, 1),new ItemStack(OPItems.EXTOL_COIN, 1)));

    }

}
