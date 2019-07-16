package georgetsak.opcraft.common.capability.haki;

import net.minecraft.entity.player.EntityPlayer;

public interface IHakiCap {

    void setUnlockedEmperorHaki(boolean unlocked);
    void setDodgeLevel(int level);
    void setEmperorLevel(int level);
    void setAttackLevel(int level);
    void setDefenceLevel(int level);

    int getDodgeLevel();
    int getEmperorLevel();
    int getAttackLevel();
    int getDefenceLevel();
    boolean isEmperorHakiUnlocked();

    int getUpgradeCost(int id);
    boolean canUpgrade(EntityPlayer entityPlayer, int id);

    void copy(IHakiCap h, EntityPlayer entityPlayer);
    void resetAll();
}
