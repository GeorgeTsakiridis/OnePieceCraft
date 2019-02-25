package georgetsak.opcraft.common.registry;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.recipe.CrocodileHookRecipe;
import georgetsak.opcraft.dev_notUsed.JSONGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.GameRuleChangeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

import static georgetsak.opcraft.common.registry.OPArmor.*;
import static georgetsak.opcraft.common.registry.OPBlocks.*;
import static georgetsak.opcraft.common.registry.OPItems.*;

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

        GameRegistry.addSmelting(new ItemStack(BlockKairosekiStone, 1), new ItemStack(ItemKairosekiGem, 2), 1f);
        GameRegistry.addSmelting(new ItemStack(BlockSteelOre, 1), new ItemStack(ItemSteelIngot, 1), 1f);
        GameRegistry.addSmelting(new ItemStack(BlockDarkSteelOre, 1), new ItemStack(ItemDarkSteelIngot, 1), 1f);

        ForgeRegistries.RECIPES.register(new CrocodileHookRecipe().setRegistryName(new ResourceLocation(OPCraft.MODID, "crocodileHook")));

        ItemStack white = new ItemStack(Blocks.WOOL, 1, 0);

        addShipBuilderRecipe("aceBoat", new ItemStack(ItemAceBoat, 1), "  WWWSWWW ", "  WWWSWWW ", "     S    ", "  GGGGCGII", "GGPPPPPPII", 'W', white, 'S', new ItemStack(Items.STICK, 1), 'G', new ItemStack(Items.GOLD_INGOT, 1), 'C', new ItemStack(Blocks.STONE_STAIRS, 1), 'I', new ItemStack(Blocks.IRON_BLOCK, 1), 'P', new ItemStack(Blocks.PLANKS, 1));
        addShipBuilderRecipe("sailBoat", new ItemStack(ItemSailBoat, 1), "    SWW   ", "    SWWW  ", "    SWW   ", "P   S   PP", "PPPPPPPPP ", 'S', Items.STICK, 'W', white, 'P', new ItemStack(Blocks.PLANKS, 1));
    }

    public static ArrayList<ItemStack> ships = new ArrayList<>();

    public static void addShipBuilderRecipe(String name, ItemStack output, Object... params)
    {
        GameRegistry.addShapedRecipe(new ResourceLocation(OPCraft.MODID, name), null, output, params);
        ships.add(output);
    }

}
