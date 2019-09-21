package georgetsak.opcraft.client.proxy;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.OPClientEventHooks;
import georgetsak.opcraft.client.gui.overlay.DevilFruitRenderOverlay;
import georgetsak.opcraft.client.gui.overlay.SixPowersSelectionWheelRender;
import georgetsak.opcraft.client.registry.OPBook;
import georgetsak.opcraft.client.render.*;
import georgetsak.opcraft.client.render.devilfruit.*;
import georgetsak.opcraft.common.command.CommandJoinCrew;
import georgetsak.opcraft.common.crew.Crew;
import georgetsak.opcraft.common.entity.boat.EntityAceBoat;
import georgetsak.opcraft.common.entity.boat.EntitySailBoat;
import georgetsak.opcraft.common.entity.devilfruit.*;
import georgetsak.opcraft.common.entity.marine.EntityHardMarine;
import georgetsak.opcraft.common.entity.marine.EntityMarine;
import georgetsak.opcraft.common.entity.marine.EntityMorgan;
import georgetsak.opcraft.common.entity.marine.EntityTequilaBridgeGuard;
import georgetsak.opcraft.common.entity.other.*;
import georgetsak.opcraft.common.network.proxy.CommonProxy;
import georgetsak.opcraft.common.registry.OPArmor;
import georgetsak.opcraft.common.registry.OPBlocks;
import georgetsak.opcraft.common.registry.OPDevilFruits;
import georgetsak.opcraft.common.registry.OPItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.b3d.B3DLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	public static final DevilFruitRenderOverlay devilFruitRenderOverlay = new DevilFruitRenderOverlay();
	public static final SixPowersSelectionWheelRender sixPowersSelectionWheelRender = new SixPowersSelectionWheelRender();

	public static ArrayList<Crew> crews;
	public static String crewLastInviteName;

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

		OBJLoader.INSTANCE.addDomain(OPCraft.MODID);
		B3DLoader.INSTANCE.addDomain(OPCraft.MODID);
		RenderingRegistry.registerEntityRenderingHandler(EntityKabutoAmmo.class, RenderKabutoAmmo::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityMarine.class, RenderMarine::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityHardMarine.class, RenderHardMarine::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityGomuPistol.class, RenderGomuPistol::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityFirePunch.class, RenderFirePunch::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityEntei.class, RenderEntei::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySlowBeam.class, RenderSlowBeam::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityIceSaber.class, RenderIceSaber::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityIcePhoenix.class, RenderIcePhoenix::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityUrsusBubble.class, RenderUrsusBubble::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityMorgan.class, RenderMorgan::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityBandit.class, RenderBandit::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityOPVillager.class, RenderOPVillager::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityAceBoat.class, RenderAceBoat::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySailBoat.class, RenderSailBoat::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySmokePunch.class, RenderSmokePunch::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySmokeSnake.class, RenderSmokeSnake::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySeaKing.class, RenderSeaKing::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPirate.class, RenderPirate::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDark.class, RenderDark::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCrocodile.class, RenderCrocodile::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTonta.class, RenderTonta::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityLiberation.class, RenderLiberation::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityRayleigh.class, RenderRayleigh::new);
		//RenderingRegistry.registerEntityRenderingHandler(EntityChristos.class, RenderChristos::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityStormLeg.class, RenderStormLeg::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTamaito.class, RenderTamaito::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityOverheat.class, RenderOverheat::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityGoshikito.class, RenderGoshikito::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySkypiean.class, RenderSkypiean::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityKuro.class, RenderKuro::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeacekeeper.class, RenderPeacekeeper::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityHomieTree.class, RenderHomieTree::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTequilaBridgeGuard.class, RenderTequilaBridgeGuard::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySlave.class, RenderSlave::new);

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(OPBlocks.WATER_CLOUD), stack -> new ModelResourceLocation(OPCraft.MODID + ":cloud_water", "cloud_water"));
		ModelLoader.setCustomStateMapper(OPBlocks.WATER_CLOUD, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(OPCraft.MODID + ":cloud_water", "cloud_water");
			}
		});

	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		registerItemRenderers();
		registerKeyBindings();
		OPBook.registerPages();
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((IBlockColor) OPBlocks.MIRROR_BLOCK, OPBlocks.MIRROR_BLOCK);

	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
		OPClientEventHooks opClientEventHooks = new OPClientEventHooks();
		MinecraftForge.EVENT_BUS.register(opClientEventHooks);

		ClientCommandHandler.instance.registerCommand(new CommandJoinCrew());

	}

	@Override
	public EntityPlayer getPlayerEntityFromContext(MessageContext ctx) {
		return (ctx.side.isClient() ? Minecraft.getMinecraft().player : super.getPlayerEntityFromContext(ctx));
	}

	@Override
	public IThreadListener getThreadFromContext(MessageContext ctx) {
		return (ctx.side.isClient() ? Minecraft.getMinecraft() : super.getThreadFromContext(ctx));
	}

	public static KeyBinding key1;
	public static KeyBinding key2;
	public static KeyBinding key3;
	public static KeyBinding statsButton;
	public static KeyBinding hakiButton;
	public static KeyBinding emperorHakiButton;
	public static KeyBinding sixPowersMenuButton;
	public static KeyBinding sixPowersButton;
	public static KeyBinding powerUpgradeButton;


	private void registerKeyBindings() {
		key1 = new KeyBinding("key.power_decrease", KeyConflictContext.IN_GAME, Keyboard.KEY_X, "key.categories.onepiece");
		key2 = new KeyBinding("key.power_increase", KeyConflictContext.IN_GAME, Keyboard.KEY_C, "key.categories.onepiece");
		key3 = new KeyBinding("key.power_execute", KeyConflictContext.IN_GAME, Keyboard.KEY_V, "key.categories.onepiece");
		statsButton = new KeyBinding("key.statsButton", KeyConflictContext.IN_GAME, Keyboard.KEY_Z, "key.categories.onepiece");
		hakiButton = new KeyBinding("key.hakiButton", KeyConflictContext.IN_GAME, KeyModifier.SHIFT, Keyboard.KEY_Z, "key.categories.onepiece");
		emperorHakiButton = new KeyBinding("key.emperorHakiButton", KeyConflictContext.IN_GAME, KeyModifier.SHIFT, Keyboard.KEY_X, "key.categories.onepiece");
		sixPowersMenuButton = new KeyBinding("key.six_powers_menu_button", KeyConflictContext.IN_GAME, KeyModifier.CONTROL, Keyboard.KEY_Z, "key.categories.onepiece");
		sixPowersButton = new KeyBinding("key.six_powers_button", KeyConflictContext.IN_GAME, KeyModifier.CONTROL, Keyboard.KEY_V, "key.categories.onepiece");
		powerUpgradeButton = new KeyBinding("key.power_upgrade_button", KeyConflictContext.IN_GAME, KeyModifier.SHIFT, Keyboard.KEY_C, "key.categories.onepiece");

		ClientRegistry.registerKeyBinding(key1);
		ClientRegistry.registerKeyBinding(key2);
		ClientRegistry.registerKeyBinding(key3);
		ClientRegistry.registerKeyBinding(statsButton);
		ClientRegistry.registerKeyBinding(hakiButton);
		ClientRegistry.registerKeyBinding(emperorHakiButton);
		ClientRegistry.registerKeyBinding(sixPowersMenuButton);
		ClientRegistry.registerKeyBinding(sixPowersButton);
		ClientRegistry.registerKeyBinding(powerUpgradeButton);
	}

	private void registerItemRenderers() {
		//blocks
		mir(Item.getItemFromBlock(OPBlocks.CHERRY_TREE_SAPLING));
		mir(OPBlocks.CHERRY_TREE_WOOD);
		mir(OPBlocks.CHERRY_TREE_PLANKS);
		mir(OPBlocks.CHERRY_TREE_LEAVES_NON_DECAYABLE);
		mir(OPBlocks.ADAM_TREE_WOOD);
		mir(OPBlocks.ADAM_TREE_PLANKS);
		mir(OPBlocks.ADAM_TREE_LEAVES_NON_DECAYABLE);
		mir(OPBlocks.KAIROSEKI_STONE);
		mir(OPBlocks.STEEL_ORE);
		mir(OPBlocks.DARK_STEEL_ORE);
		mir(OPBlocks.STEEL_BLOCK);
		mir(OPBlocks.DARK_STEEL_BLOCK);
		mir(OPBlocks.LAW_DOME);
		mir(OPBlocks.ICE_CAGE);
		mir(OPBlocks.ICE_AGE);
		mir(OPBlocks.SHIP_BUILDER);
		mir(OPBlocks.KAIROSEKI_BLOCK);
		mir(Item.getItemFromBlock(OPBlocks.KAIROSEKI_BARS));
		mir(OPBlocks.TEMPORARY_ICE);
		mir(OPBlocks.THIN_CLOUD);
		mir(OPBlocks.DENSE_CLOUD);
		mir(OPBlocks.MIRROR_BLOCK);


		//items
		mir(OPItems.KAIROSEKI_GEM);
		mir(OPItems.STEEL_INGOT);
		mir(OPItems.DARK_STEEL_INGOT);
		mir(OPItems.DARK_STEEL_NUGGET);
		mir(OPItems.WADO_ICHI_MONJI_CASED);
		mir(OPItems.WADO_ICHI_MONJI_OPEN);
		mir(OPItems.KITETSU_CASED);
		mir(OPItems.KITETSU_OPEN);
		mir(OPItems.SHUUSUI_CASED);
		mir(OPItems.SHUUSUI_OPEN);
		mir(OPItems.MIHAWK_YORU);
		mir(OPItems.ARLONG_KIRIBACHI);
		mir(OPItems.SMOKER_JITTE);
		mir(OPItems.BROOK_SWORD_CASED);
		mir(OPItems.BROOK_SWORD_OPEN);
		mir(OPItems.CROCODILE_HOOK_CASED);
		mir(OPItems.CROCODILE_HOOK_OPEN);
		mir(OPItems.KIKOKU_CASED);
		mir(OPItems.KIKOKU_OPEN);
		mir(OPItems.CLIMA_SIMPLE);
		mir(OPItems.CLIMA_COMPLETED_WATER);
		mir(OPItems.CLIMA_COMPLETED_FIRE);
		mir(OPItems.CLIMA_COMPLETED_THUNDER);
		mir(OPItems.USOPP_KABUTO);
		mir(OPItems.USOPP_KABUTO_BLACK);
		mir(OPItems.SMALL_ROCK);
		mir(OPItems.FLINTLOCK);
		mir(OPItems.FLINTLOCK_AMMO);
		mir(OPItems.SENRIKU);
		mir(OPItems.SENRIKU_AMMO);
		mir(OPItems.BAZOOKA);
		mir(OPItems.BAZOOKA_AMMO);
		mir(OPItems.WATER_DIAL);
		mir(OPItems.LAVA_DIAL);
		mir(OPItems.FIRE_DIAL);
		mir(OPItems.IMPACT_DIAL);
		mir(OPItems.THUNDER_DIAL);
		mir(OPItems.CUTLASS);
		mir(OPItems.BERRY_COIN);
		mir(OPItems.MANUAL_BOOK);
		mir(OPItems.DEVIL_FRUIT_POWER_REMOVER);
		mir(OPItems.WE_ARE_DISK);
		mir(OPItems.SAKE);
		mir(OPItems.SAKE);
		mir(OPItems.LAW_HEART);
		mir(OPItems.BISENTO);
		mir(OPItems.SHIGURE_CASED);
		mir(OPItems.SHIGURE_OPEN);
		mir(OPItems.YUBASHIRI_CASED);
		mir(OPItems.YUBASHIRI_OPEN);
		mir(OPItems.TERRY_SWORD);
		mir(OPItems.EISEN_WHIP);
		mir(OPItems.DURANDAL_CASED);
		mir(OPItems.DURANDAL_OPEN);
		mir(OPItems.SAMEKIRI_BOCHO_SWORD);
		mir(OPItems.PRETZEL_SWORD);
		mir(OPItems.SHIRAUO_CASED);
		mir(OPItems.SHIRAUO_OPEN);
		mir(OPItems.BANDAGE);
		mir(OPItems.SUTURES);
		mir(OPItems.FIRST_AID_KIT);
		mir(OPItems.EXTOL_COIN);
		mir(OPItems.EMPTY_DIAL);

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(OPBlocks.SNAIL), 0, new ModelResourceLocation("onepiececraft:snail", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(OPItems.ACE_BOAT, 0, new ModelResourceLocation("onepiececraft:ace_boat", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(OPItems.SAIL_BOAT, 0, new ModelResourceLocation("onepiececraft:sail_boat", "inventory"));

		mir(OPDevilFruits.GOMU);
		mir(OPDevilFruits.MERA);
		mir(OPDevilFruits.NORO);
		mir(OPDevilFruits.SUKE);
		mir(OPDevilFruits.GIRAFFE);
		mir(OPDevilFruits.OPE);
		mir(OPDevilFruits.HIE);
		mir(OPDevilFruits.NIKYU);
		mir(OPDevilFruits.YOMI);
		mir(OPDevilFruits.GORO);
		mir(OPDevilFruits.MOKU);
		mir(OPDevilFruits.YAMI);
		mir(OPDevilFruits.ITO);

		mir(OPArmor.LuffySimpleHelmet);
		mir(OPArmor.LuffySimpleChestplate);
		mir(OPArmor.LuffySimpleLeggings);
		mir(OPArmor.LuffySimpleBoots);
		mir(OPArmor.ZoroSimpleHelmet);
		mir(OPArmor.ZoroSimpleChestplate);
		mir(OPArmor.ZoroSimpleLeggings);
		mir(OPArmor.ZoroSimpleBoots);
		mir(OPArmor.UsoppSimpleHelmet);
		mir(OPArmor.UsoppSimpleChestplate);
		mir(OPArmor.UsoppSimpleLeggings);
		mir(OPArmor.UsoppSimpleBoots);
		mir(OPArmor.SanjiSimpleHelmet);
		mir(OPArmor.SanjiSimpleChestplate);
		mir(OPArmor.SanjiSimpleLeggings);
		mir(OPArmor.SanjiSimpleBoots);
		mir(OPArmor.MarineSimpleHelmet);
		mir(OPArmor.MarineSimpleChestplate);
		mir(OPArmor.MarineSimpleLeggings);
		mir(OPArmor.MarineSimpleBoots);
		mir(OPArmor.PirateSimpleChestplate);
		mir(OPArmor.PirateSimpleLeggings);
		mir(OPArmor.PirateSimpleBoots);

	}

	//Minecraft Item Render (MIR)
	private void mir(Block block){
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName().toString(), "normal"));
	}
	
	private void mir(Item item) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName().toString(), "inventory"));

	}

}
