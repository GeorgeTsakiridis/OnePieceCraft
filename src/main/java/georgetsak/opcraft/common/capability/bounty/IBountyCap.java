package georgetsak.opcraft.common.capability.bounty;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by GeorgeTsak on 7/11/2017.
 */
public interface IBountyCap {

    void changeBountyBy(int value);

    int getBounty();

    boolean isWanted();

    void copy(IBountyCap b, EntityPlayer entityPlayer);

    void setBounty(int bounty);
}
