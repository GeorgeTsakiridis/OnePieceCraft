package georgetsak.opcraft.client.proxy;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.OPClientEventHooks;
import georgetsak.opcraft.client.gui.overlay.DevilFruitRenderOverlay;
import georgetsak.opcraft.client.gui.overlay.SixPowersSelectionWheelRender;
import georgetsak.opcraft.client.registry.OPBook;
import georgetsak.opcraft.client.registry.OPKeys;
import georgetsak.opcraft.client.registry.OPRender;
import georgetsak.opcraft.common.command.CommandJoinCrew;
import georgetsak.opcraft.common.crew.Crew;
import georgetsak.opcraft.common.network.proxy.CommonProxy;
import georgetsak.opcraft.common.registry.OPBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.model.b3d.B3DLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	public static ArrayList<Crew> crews;
	public static String crewLastInviteName;

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

		OBJLoader.INSTANCE.addDomain(OPCraft.MODID);
		B3DLoader.INSTANCE.addDomain(OPCraft.MODID);
		OPRender.registerEntityRenderers();
		OPRender.registerSpecialRenderersPreInit();

	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);

		OPRender.registerBlockRenderers();
		OPRender.registerItemRenderers();
		OPKeys.registerKeyBindings();
		OPBook.registerPages();
		OPRender.registerSpecialRenderersInit();
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

}
