package georgetsak.opcraft.common.network.proxy;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.OPSoundEvent;
import georgetsak.opcraft.client.gui.GUIHandler;
import georgetsak.opcraft.common.OPCommonEventHooks;
import georgetsak.opcraft.common.capability.CapabilityHandler;
import georgetsak.opcraft.common.capability.bounty.BountyCap;
import georgetsak.opcraft.common.capability.bounty.BountyCapStorage;
import georgetsak.opcraft.common.capability.bounty.IBountyCap;
import georgetsak.opcraft.common.capability.devilfruits.DevilFruitsCap;
import georgetsak.opcraft.common.capability.devilfruits.DevilFruitsCapStorage;
import georgetsak.opcraft.common.capability.devilfruits.IDevilFruitsCap;
import georgetsak.opcraft.common.capability.haki.HakiCap;
import georgetsak.opcraft.common.capability.haki.HakiCapStorage;
import georgetsak.opcraft.common.capability.haki.IHakiCap;
import georgetsak.opcraft.common.capability.sixpowers.ISixPowersCap;
import georgetsak.opcraft.common.capability.sixpowers.SixPowersCap;
import georgetsak.opcraft.common.capability.sixpowers.SixPowersCapStorage;
import georgetsak.opcraft.common.capability.stats.normal.IStatsNormalCap;
import georgetsak.opcraft.common.capability.stats.normal.StatsNormalCap;
import georgetsak.opcraft.common.capability.stats.normal.StatsNormalCapStorage;
import georgetsak.opcraft.common.potioneffect.PotionOP;
import georgetsak.opcraft.common.registry.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.IThreadListener;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.awt.*;

import static georgetsak.opcraft.common.registry.OPArmor.LuffySimpleHelmet;

public class CommonProxy {

	public static MerchantRecipeList merchantRecipes;

	public static CreativeTabs OPTab;
	public static PotionOP effectInsideDome;

	OPCommonEventHooks serverEventHooks;

	public void preInit(FMLPreInitializationEvent event) {
		OPSoundEvent.registerSounds();

        registerCreativeTab();
		OPBlocks.registerBlocks();
		OPItems.registerItems();
		OPDevilFruits.registerDevilFruits();
		OPArmor.registerArmor();
		registerPotionEffects();
		OPRecipes.registerRecipes();
		OPEntities.registerEntities();
		OPGenerators.registerGenerators();
		OPMerchantTrades.registerMerchantTrades();
		OPLootTables.registerLootTables();
		registerGUI();
		registerCapabilities();

	}
	
	public void init(FMLInitializationEvent event) {
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		serverEventHooks = new OPCommonEventHooks();
		MinecraftForge.EVENT_BUS.register(serverEventHooks);
	}

	private void registerPotionEffects() {
		effectInsideDome = new PotionOP(false, Color.BLACK.getRGB(), "insideDome", 0);

		registerPotion(effectInsideDome);
	}

	private static void registerPotion(Potion potion) {
		ForgeRegistries.POTIONS.register(potion);
	}

	private void registerCreativeTab() {
			OPTab = new CreativeTabs("One-Piece Craft") {
		    @Override public ItemStack getTabIconItem() {
		        return new ItemStack(LuffySimpleHelmet);
		    }
		};
		
	}

	private void registerGUI(){
		NetworkRegistry.INSTANCE.registerGuiHandler(OPCraft.MODID, new GUIHandler());
	}

	private void registerCapabilities(){
		CapabilityManager.INSTANCE.register(IDevilFruitsCap.class, new DevilFruitsCapStorage(), DevilFruitsCap::new);
		CapabilityManager.INSTANCE.register(IBountyCap.class, new BountyCapStorage(), BountyCap::new);
		CapabilityManager.INSTANCE.register(IStatsNormalCap.class, new StatsNormalCapStorage(), StatsNormalCap::new);
		CapabilityManager.INSTANCE.register(IHakiCap.class, new HakiCapStorage(), HakiCap::new);
		CapabilityManager.INSTANCE.register(ISixPowersCap.class, new SixPowersCapStorage(), SixPowersCap::new);

		CapabilityHandler capabilityHandler = new CapabilityHandler();
		MinecraftForge.EVENT_BUS.register(capabilityHandler);

	}

	public EntityPlayer getPlayerEntityFromContext(MessageContext ctx)
	{
		return ctx.getServerHandler().player;
	}

	public IThreadListener getThreadFromContext(MessageContext ctx) {
		return ctx.getServerHandler().player.getServer();
	}

}
