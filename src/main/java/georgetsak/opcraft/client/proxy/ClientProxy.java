package georgetsak.opcraft.client.proxy;

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
import georgetsak.opcraft.common.entity.marine.*;
import georgetsak.opcraft.common.entity.other.*;
import georgetsak.opcraft.client.OPClientEventHooks;
import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.network.proxy.CommonProxy;
import georgetsak.opcraft.common.registry.OPDevilFruits;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.client.ClientCommandHandler;
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

import java.util.ArrayList;

import static georgetsak.opcraft.common.registry.OPArmor.*;
import static georgetsak.opcraft.common.registry.OPDevilFruits.*;
import static georgetsak.opcraft.common.registry.OPItems.*;

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
		RenderingRegistry.registerEntityRenderingHandler(EntitySlowBeamHighSpeed.class, RenderSlowBeam::new);
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
		RenderingRegistry.registerEntityRenderingHandler(EntityTamaito.class,RenderTamaito::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityOverheat.class,RenderOverheat::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityGoshikito.class,RenderGoshikito::new);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		registerItemRenderers();
        registerKeyBindings();
        //OPDevilFruits.registerPowers();
        OPBook.registerPages();
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
		OPClientEventHooks opClientEventHooks = new OPClientEventHooks();
		MinecraftForge.EVENT_BUS.register(opClientEventHooks);

		ClientCommandHandler.instance.registerCommand(new CommandJoinCrew());

	}

	@Override
	public EntityPlayer getPlayerEntityFromContext(MessageContext ctx)
	{
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
		powerUpgradeButton = new KeyBinding("key.power_upgrade_button",KeyConflictContext.IN_GAME,KeyModifier.SHIFT, Keyboard.KEY_C, "key.categories.onepiece");

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
		mir(ItemCherryTreeSapling, true);
		mir(ItemCherryTreeWood, false);
		mir(ItemCherryTreePlanks, false);
		mir(ItemCherryTreeLeavesNonDecayable, false);
		mir(ItemAdamTreeWood, false);
		mir(ItemAdamTreePlanks, false);
		mir(ItemAdamTreeLeavesNonDecayable, false);
		mir(ItemKairosekiStone, false);
		mir(ItemKairosekiGem, true);
		mir(ItemSteelOre, false);
		mir(ItemDarkSteelOre, false);
		mir(ItemSteelIngot, true);
		mir(ItemDarkSteelIngot, true);
		mir(ItemSteelBlock, false);
		mir(ItemDarkSteelBlock, false);
		mir(ItemDarkSteelNugget, true);
		mir(ItemWadoIchiMonjiCased, true);
		mir(ItemWadoIchiMonjiOpen, true);
		mir(ItemKitetsuCased, true);
		mir(ItemKitetsuOpen, true);
		mir(ItemShuusuiCased, true);
		mir(ItemShuusuiOpen, true);
		mir(ItemMihawkYoru, true);
		mir(ItemArlongKiribachi, true);
		mir(ItemSmokerJitte, true);
		mir(ItemBrookSwordCased, true);
		mir(ItemBrookSwordOpen, true);
		mir(ItemCrocodileHookCased, true);
		mir(ItemCrocodileHookOpen, true);
		mir(ItemKikokuCased, true);
		mir(ItemKikokuOpen, true);
		mir(ItemClimaSimple, true);
		mir(ItemClimaCompletedWater, true);
		mir(ItemClimaCompletedFire, true);
		mir(ItemClimaCompletedThunder, true);
		mir(ItemTemporaryIce, false);
		mir(ItemUssopKabuto, true);
		mir(ItemUssopKabutoBlack, true);
		mir(ItemUssopKabutoAmmo, true);
		mir(ItemFlintlock, true);
		mir(ItemFlintlockAmmo, true);
		mir(ItemSenriku, true);
		mir(ItemSenrikuAmmo, true);
		mir(ItemBazooka, true);
		mir(ItemBazookaAmmo, true);
		mir(ItemWaterDial, true);
		mir(ItemLavaDial, true);
		mir(ItemFireDial, true);
		mir(ItemImpactDial, true);
		mir(ItemThunderDial, true);
		mir(LuffySimpleHelmet, true);
		mir(LuffySimpleChestplate, true);
		mir(LuffySimpleLeggings, true);
		mir(LuffySimpleBoots, true);
		mir(ZoroSimpleHelmet, true);
		mir(ZoroSimpleChestplate, true);
		mir(ZoroSimpleLeggings, true);
		mir(ZoroSimpleBoots, true);
		mir(UsoppSimpleHelmet, true);
		mir(UsoppSimpleChestplate, true);
		mir(UsoppSimpleLeggings, true);
		mir(UsoppSimpleBoots, true);
		mir(SanjiSimpleHelmet, true);
		mir(SanjiSimpleChestplate, true);
		mir(SanjiSimpleLeggings, true);
		mir(SanjiSimpleBoots, true);
		mir(MarineSimpleHelmet, true);
		mir(MarineSimpleChestplate, true);
		mir(MarineSimpleLeggings, true);
		mir(MarineSimpleBoots, true);
		mir(PirateSimpleChestplate, true);
		mir(PirateSimpleLeggings, true);
		mir(PirateSimpleBoots, true);
		mir(ItemCutlass, true);
		mir(ItemLawDomeBlock, false);
		mir(ItemLawDomeCenterBlock, false);
		mir(ItemIceCageBlock, false);
		mir(ItemIceAgeBlock, false);
		mir(ItemBerryCoin, true);
		mir(ItemManualBook, true);
		mir(ItemDevilFruitPowerRemover, true);
		mir(ItemWeAreDisk, true);
		mir(ItemSake, true);
		mir(ItemSake, true);
        mir(ItemShipBuilder, false);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ItemSnail, 0, new ModelResourceLocation("onepiececraft:snail", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ItemAceBoat, 0, new ModelResourceLocation("onepiececraft:ace_boat", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ItemSailBoat, 0, new ModelResourceLocation("onepiececraft:sail_boat", "inventory"));
		mir(ItemKairosekiBlock, false);
		mir(ItemKairosekiBars, true);
		mir(ItemLawHeart, true);
		mir(ItemBisento, true);
		mir(ItemShigureCased, true);
		mir(ItemShigureOpen, true);
		mir(ItemYubashiriCased, true);
		mir(ItemYubashiriOpen, true);
		mir(ItemTerrySword, true);
		mir(ItemEisenWhip, true);
		mir(ItemDurandalCased, true);
		mir(ItemDurandalOpen, true);
		mir(ItemSamekiriBochoSword, true);
		mir(ItemPretzelSword, true);
		mir(ItemShirauoCased, true);
		mir(ItemShirauoOpen, true);
		mir(ItemBandage, true);
		mir(ItemSutures, true);
		mir(ItemFirstAidKit, true);

		mir(ItemDevilFruitGomu, true);
		mir(ItemDevilFruitMera, true);
		mir(ItemDevilFruitNoro, true);
		mir(ItemDevilFruitSuke, true);
		mir(ItemDevilFruitGiraffe, true);
		mir(ItemDevilFruitOpe, true);
		mir(ItemDevilFruitHie, true);
		mir(ItemDevilFruitNikyu, true);
		mir(ItemDevilFruitYomi, true);
		mir(ItemDevilFruitGoro, true);
		mir(ItemDevilFruitMoku, true);
		mir(ItemDevilFruitYami, true);
		mir(ItemDevilFruitIto, true);
	}

	//Minecraft Item Render (MIR)
	private void mir(Item item, boolean isItem) {
		if(isItem){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName().toString(), "inventory"));
		}
		else{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName().toString()));
		}
		
	}

}
