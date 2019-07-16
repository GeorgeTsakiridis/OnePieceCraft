package georgetsak.opcraft.common.capability.devilfruits;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by GeorgeProgramming on 7/11/2017.
 */
public interface IDevilFruitsCap {

    boolean hasPower();

    void setPower(int powerID);

    int getPower();

    void copy(IDevilFruitsCap df, EntityPlayer ep);

}
