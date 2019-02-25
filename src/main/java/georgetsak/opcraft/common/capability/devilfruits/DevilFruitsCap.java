package georgetsak.opcraft.common.capability.devilfruits;

import georgetsak.opcraft.common.network.packets.DevilFruitCapPacket;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import georgetsak.opcraft.common.registry.OPDevilFruits;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created by GeorgeProgramming on 7/11/2017.
 */
public class DevilFruitsCap implements IDevilFruitsCap {

    private int powerID = OPDevilFruits.NO_POWER;

    public static IDevilFruitsCap get(EntityPlayer player)
    {
        return player.getCapability(DevilFruitsCapProvider.DF_CAP, null);
    }

    @Override
    public boolean hasPower() {
        return powerID != OPDevilFruits.NO_POWER;
    }

    @Override
    public void setPower(int ID) {
        powerID = ID;
    }

    @Override
    public int getPower() {
        return powerID;
    }

    @Override
    public void copy(IDevilFruitsCap df, EntityPlayer ep) {
        this.setPower(df.getPower());
        PacketDispatcher.sendTo(new DevilFruitCapPacket(df), (EntityPlayerMP)ep);
    }
}
