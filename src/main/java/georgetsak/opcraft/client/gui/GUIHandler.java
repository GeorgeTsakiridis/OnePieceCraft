package georgetsak.opcraft.client.gui;

import georgetsak.opcraft.client.gui.guidebook.ManualBookGUI;
import georgetsak.opcraft.client.gui.shipbuilder.ContainerCraftingShipBuilder;
import georgetsak.opcraft.client.gui.shipbuilder.ShipBuilderGUI;
import georgetsak.opcraft.client.gui.stat.HakiGUI;
import georgetsak.opcraft.client.gui.stat.StatsGUI;
import georgetsak.opcraft.common.capability.devilfruitlevels.DevilFruitLevelsCap;
import georgetsak.opcraft.common.crew.CrewSaveData;
import georgetsak.opcraft.common.network.packets.client.PacketSyncCrewClient;
import georgetsak.opcraft.common.network.packets.common.PacketDevilFruitLevels;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by GeorgeProgramming on 7/10/2017.
 */
public class GUIHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 2:
                return new ContainerCraftingShipBuilder(player.inventory, world, new BlockPos(x, y, z));
            case 5:
                PacketDispatcher.sendTo(new PacketSyncCrewClient(CrewSaveData.get(world).getCrews()), (EntityPlayerMP) player);
                return null;
            case 6:
                PacketDispatcher.sendTo(new PacketDevilFruitLevels(DevilFruitLevelsCap.get(player)),(EntityPlayerMP)player);
                return null;
            case 7:
                return new ContainerKabuto(player.inventory, player.getHeldItemMainhand(), player.world);
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 0:
                return new ManualBookGUI(player);
            case 1:
                return new StatsGUI();
            case 2:
                return new ShipBuilderGUI(player, world, x, y, z);
            case 3:
                //Reserved for testing purposes.
                return null;
            case 4:
                return new HakiGUI();
            case 5:
                return new CrewGUI();
            case 6:
                return new PowerUpgradeGUI();
            case 7:
                return new KabutoGUI(player.inventory, player.getHeldItemMainhand());
            default:
                return null;
        }
    }
}
