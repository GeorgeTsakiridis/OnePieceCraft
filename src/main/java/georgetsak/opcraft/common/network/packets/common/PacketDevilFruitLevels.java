package georgetsak.opcraft.common.network.packets.common;

import georgetsak.opcraft.common.capability.devilfruitlevels.DevilFruitLevelsCap;
import georgetsak.opcraft.common.capability.devilfruitlevels.IDevilFruitLevelsCap;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;


public class PacketDevilFruitLevels extends AbstractMessage<PacketDevilFruitLevels>{

    int devilFruitID;
    int[] uses;
    int[] levels;

    public PacketDevilFruitLevels(){}
    public PacketDevilFruitLevels(IDevilFruitLevelsCap dfl){
        devilFruitID = dfl.getDevilFruitID();
        uses = dfl.getAllPowersUses();
        levels = dfl.getAllPowersLevels();
    }

    @Override
    protected void read(PacketBuffer buffer) {
        devilFruitID = buffer.readInt();
        uses = buffer.readVarIntArray();
        levels = buffer.readVarIntArray();
    }

    @Override
    protected void write(PacketBuffer buffer) {
        buffer.writeInt(devilFruitID);
        buffer.writeVarIntArray(uses);
        buffer.writeVarIntArray(levels);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
            IDevilFruitLevelsCap dfl = DevilFruitLevelsCap.get(player);

            dfl.setDevilFruitID(devilFruitID);
            dfl.setPowerUses(uses);
            dfl.setPowersLevels(levels);
    }
}
