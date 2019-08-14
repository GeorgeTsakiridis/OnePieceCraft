package georgetsak.opcraft.common.network.packets.client;

import georgetsak.opcraft.common.capability.devilfruits.DevilFruitCap;
import georgetsak.opcraft.common.capability.devilfruits.IDevilFruitCap;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by GeorgeProgramming on 7/11/2017.
 */
public class DevilFruitClientPacket extends AbstractMessage.AbstractClientMessage<DevilFruitClientPacket>{

    private int powerID;

    public DevilFruitClientPacket(){}

    public DevilFruitClientPacket(IDevilFruitCap df) {
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
            IDevilFruitCap devilFruitsCap = DevilFruitCap.get(player);
            devilFruitsCap.setPower(powerID);
        }
    }
}



