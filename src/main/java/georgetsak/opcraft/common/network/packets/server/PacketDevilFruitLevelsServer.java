package georgetsak.opcraft.common.network.packets.server;

import georgetsak.opcraft.common.capability.devilfruitlevels.DevilFruitLevelsCap;
import georgetsak.opcraft.common.capability.devilfruitlevels.IDevilFruitLevelsCap;
import georgetsak.opcraft.common.capability.stats.normal.IStatsNormalCap;
import georgetsak.opcraft.common.capability.stats.normal.StatsNormalCapProvider;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

/**
 * Created by GeorgeTsak on 7/15/2017.
 */
public class PacketDevilFruitLevelsServer extends AbstractMessage.AbstractServerMessage<PacketDevilFruitLevelsServer> {

    int devilFruitID;
    int[] uses;
    int[] hits;

    public PacketDevilFruitLevelsServer(){}
    public PacketDevilFruitLevelsServer(IDevilFruitLevelsCap dfl){
        devilFruitID = dfl.getDevilFruitID();
        uses = dfl.getAllPowersUses();
        hits = dfl.getAllPowersHits();
    }

    @Override
    protected void read(PacketBuffer buffer) {
        devilFruitID = buffer.readInt();
        uses = buffer.readVarIntArray();
        hits = buffer.readVarIntArray();
    }

    @Override
    protected void write(PacketBuffer buffer) {
        buffer.writeInt(devilFruitID);
        buffer.writeVarIntArray(uses);
        buffer.writeVarIntArray(hits);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
            IDevilFruitLevelsCap dfl = DevilFruitLevelsCap.get(player);

            dfl.setDevilFruitID(devilFruitID);
            dfl.setPowerUses(uses);
            dfl.setPowerHits(hits);
    }
}
