package georgetsak.opcraft.common.capability.devilfruitlevels;

import net.minecraft.entity.player.EntityPlayer;

public interface IDevilFruitLevelsCap {

    void setDevilFruitID(int id);
    int getDevilFruitID();
    int getPowerLevel(int id);

    int[] getAllPowersUses();
    void addPowerUses(int id);
    void setPowerUses(int id, int uses);
    void setPowersUses(int[] uses);
    int getPowerTotalUses(int id);

    int[] getAllPowersLevels();
    void addPowerLevel(int id);
    void setPowerLevel(int id, int level);
    void setPowersLevels(int[] levels);

    void setXP(int xp);
    void addXP(int xp);
    int getXP();

    void setPowerCooldown(int id, int level);
    int getPowerCooldown(int id);
    int getPowerCooldownLevel(int id);

    boolean canPowerLevelBeIncreased(int id);
    boolean canPowerCooldownBeReduced(int id);

    void resetAll();
    void copy(IDevilFruitLevelsCap dfl, EntityPlayer ep);

}
