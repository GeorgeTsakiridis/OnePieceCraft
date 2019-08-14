package georgetsak.opcraft.common.network.packets.client;

import georgetsak.opcraft.common.capability.devilfruitlevels.DevilFruitLevelsCap;
import georgetsak.opcraft.common.capability.devilfruitlevels.IDevilFruitLevelsCap;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;


public class DevilFruitLevelsClientPacket extends AbstractMessage.AbstractClientMessage<DevilFruitLevelsClientPacket>{

    int devilFruitID;
    int[] uses;
    int[] hits;

    public DevilFruitLevelsClientPacket(){}
    public DevilFruitLevelsClientPacket(IDevilFruitLevelsCap dfl){
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
        if(side.isClient()){
            IDevilFruitLevelsCap dfl = DevilFruitLevelsCap.get(player);

            dfl.setDevilFruitID(devilFruitID);
            dfl.setPowerUses(uses);
            dfl.setPowerHits(hits);

        }
    }
}
