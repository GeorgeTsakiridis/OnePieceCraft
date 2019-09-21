package georgetsak.opcraft.common.registry;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.recipe.CrocodileHookRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

/**
 * Created by GeorgeTsak on 8/7/2017.
 */
public class OPRecipes {

    public static void registerRecipes(){
        
        //JSONGenerator.addSmelting(BlockCherryTreeWood, new ItemStack(Items.COAL, 1, 1), 0.7F);
        //JSONGenerator.addSmelting(BlockAdamTreeWood, new ItemStack(Items.COAL, 1, 1), 0.7F);
        //JSONGenerator.addSmelting(BlockKairosekiStone, new ItemStack(ItemKairosekiGem, 2), 1.0F);
        //JSONGenerator.addSmelting(BlockSteelOre, new ItemStack(ItemSteelIngot, 1), 0.7F);
        //JSONGenerator.addSmelting(BlockDarkSteelOre, new ItemStack(ItemDarkSteelIngot, 1), 0.7F);

        GameRegistry.addSmelting(new ItemStack(OPBlocks.KAIROSEKI_STONE, 1), new ItemStack(OPItems.KAIROSEKI_GEM, 2), 1f);
        GameRegistry.addSmelting(new ItemStack(OPBlocks.STEEL_ORE, 1), new ItemStack(OPItems.STEEL_INGOT, 1), 1f);
        GameRegistry.addSmelting(new ItemStack(OPBlocks.DARK_STEEL_ORE, 1), new ItemStack(OPItems.DARK_STEEL_INGOT, 1), 1f);

        ForgeRegistries.RECIPES.register(new CrocodileHookRecipe().setRegistryName(new ResourceLocation(OPCraft.MODID, "crocodileHook")));

        ItemStack white = new ItemStack(Blocks.WOOL, 1, 0);

        addShipBuilderRecipe("aceBoat", new ItemStack(OPItems.ACE_BOAT, 1), "  WWWSWWW ", "  WWWSWWW ", "     S    ", "  GGGGCGII", "GGPPPPPPII", 'W', white, 'S', new ItemStack(Items.STICK, 1), 'G', new ItemStack(Items.GOLD_INGOT, 1), 'C', new ItemStack(Blocks.STONE_STAIRS, 1), 'I', new ItemStack(Blocks.IRON_BLOCK, 1), 'P', new ItemStack(Blocks.PLANKS, 1));
        addShipBuilderRecipe("sailBoat", new ItemStack(OPItems.SAIL_BOAT, 1), "    SWW   ", "    SWWW  ", "    SWW   ", "P   S   PP", "PPPPPPPPP ", 'S', Items.STICK, 'W', white, 'P', new ItemStack(Blocks.PLANKS, 1));
    }

    public static ArrayList<ItemStack> ships = new ArrayList<>();

    public static void addShipBuilderRecipe(String name, ItemStack output, Object... params)
    {
        GameRegistry.addShapedRecipe(new ResourceLocation(OPCraft.MODID, name), null, output, params);
        ships.add(output);
    }

}
