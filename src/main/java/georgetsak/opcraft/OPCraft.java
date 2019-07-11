package georgetsak.opcraft;

import georgetsak.opcraft.common.command.*;
import georgetsak.opcraft.common.config.ConfigHandler;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import georgetsak.opcraft.common.network.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkCheckHandler;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Map;

@Mod(modid = OPCraft.MODID, version = OPCraft.VERSION, name = OPCraft.NAME)
public class OPCraft {
    public static final String MODID = "onepiececraft";
    public static final String VERSION = "2.3.1";
    public static final String NAME = "One Piece Craft";
    public static final boolean IS_RELEASE_VERSION = true;

    @SidedProxy(clientSide = "georgetsak.opcraft.client.proxy.ClientProxy", serverSide = "georgetsak.opcraft.common.network.proxy.CommonProxy")
    public static CommonProxy proxy;
    public static ConfigHandler config;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PacketDispatcher.registerPackets();
        config = new ConfigHandler(event);
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandSetBounty());
        event.registerServerCommand(new CommandAddBounty());
        event.registerServerCommand(new CommandResetStats());
        event.registerServerCommand(new CommandGetBounty());
        event.registerServerCommand(new CommandSetSixPowerLevel());
        event.registerServerCommand(new CommandCrewMessage());
    }

    @NetworkCheckHandler()
    public boolean matchModVersions(Map<String, String> remoteVersions, Side side) {
        if (side == Side.CLIENT) {
            return remoteVersions.containsKey(MODID);
        }
        return !remoteVersions.containsKey(MODID) || VERSION.equals(remoteVersions.get(MODID));

    }
}
