package georgetsak.opcraft.common.capability.devilfruits;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by GeorgeProgramming on 7/11/2017.
 */
public interface IDevilFruitCap {

    boolean hasPower();

    void setPower(int powerID);

    int getPower();

    void copy(IDevilFruitCap df, EntityPlayer ep);

}
