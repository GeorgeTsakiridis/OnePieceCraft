package georgetsak.opcraft.common.capability.stats.normal;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by GeorgeTsak on 7/15/2017.
 */
public interface IStatsNormalCap {

    void setHealthLevel(int level);
    void setAttackLevel(int level);
    void setDefenceLevel(int level);
    void setSpeedLevel(int level);

    int getHealthLevel();
    int getAttackLevel();
    int getDefenceLevel();
    int getSpeedLevel();

    int getUpgradeCost(int statID);
    boolean canUpgrade(int statID, EntityPlayer entityPlayer);

    void updateToSever(IStatsNormalCap stats);
    void copy(IStatsNormalCap b, EntityPlayer entityPlayer);
    void resetAll();
}
