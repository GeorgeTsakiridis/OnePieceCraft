package georgetsak.opcraft.common.network.packets.client;

import georgetsak.opcraft.common.capability.devilfruits.DevilFruitsCap;
import georgetsak.opcraft.common.capability.devilfruits.IDevilFruitsCap;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by GeorgeProgramming on 7/11/2017.
 */
public class DevilFruitCapClientPacket extends AbstractMessage.AbstractClientMessage<DevilFruitCapClientPacket>{

    private int powerID;

    public DevilFruitCapClientPacket(){}

    public DevilFruitCapClientPacket(IDevilFruitsCap df) {
        this.powerID = df.getPower();
    }


    @Override
    protected void read(PacketBuffer buffer) {
        powerID = buffer.readInt();
    }

    @Override
    protected void write(PacketBuffer buffer) {
        buffer.writeInt(powerID);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        if(side.isClient()){
            IDevilFruitsCap devilFruitsCap = DevilFruitsCap.get(player);
            devilFruitsCap.setPower(powerID);
        }
    }
}



