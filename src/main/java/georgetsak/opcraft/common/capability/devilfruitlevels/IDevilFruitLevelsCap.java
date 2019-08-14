package georgetsak.opcraft.common.capability.devilfruitlevels;

import net.minecraft.entity.player.EntityPlayer;

public interface IDevilFruitLevelsCap {

    void setDevilFruitID(int id);
    int getDevilFruitID();
    int getPowerLevel(int id);

    int getPowerTotalUses(int id);
    int[] getAllPowersUses();
    void addPowerUses(int id);
    void setPowerUses(int[] uses);

    int getPowerHits(int id);
    int[] getAllPowersHits();
    void addPowerHit(int id);
    void setPowerHits(int[] hits);

    int getPowerCooldown(int id);

    boolean canPowerLevelBeIncreased(int id);
    boolean canPowerCooldownBeReduced(int id);

    void resetAll();
    void copy(IDevilFruitLevelsCap dfl, EntityPlayer ep);

}
