package georgetsak.opcraft.common.capability.sixpowers;

import georgetsak.opcraft.client.gui.overlay.EnumSixPowers;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by GeorgeTsak on 24/7/2018.
 */
public interface ISixPowersCap {

    int getPowerLevel(EnumSixPowers power);
    int getProgress(EnumSixPowers power);

    boolean isSixKingGunUnlocked();

    void setStillJumps(int stillJumps);
    void setIronDamageReceived(int damageReceived);
    void setPunchDamageGiven(int damageGiven);
    void setRunningJumps(int runningJumps);
    void setDistanceRun(int distanceRun);
    void setDistanceRunInPlants(int distanceRunInPlants);

    void addStillJumps(int stillJumps);
    void addIronDamage(int ironDamage);
    void addPunchDamage(int punchDamage);
    void addRunningJumps(int runningJumps);
    void addDistanceRun(int distanceRun);
    void addDistanceRunInPlants(int distanceRunInPlants);

    int getStillJumps();
    int getIronDamageReceived();
    int getPunchDamageGiven();
    int getRunningJumps();
    int getDistanceRun();
    int getDistanceRunInPlants();

    int getRequiredPointsForLevel(EnumSixPowers sixPowers);

    void copy(ISixPowersCap b, EntityPlayer entityPlayer);
    void resetAll();
}
