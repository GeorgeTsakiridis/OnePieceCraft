package georgetsak.opcraft.common.capability.sixpowers;

import georgetsak.opcraft.client.gui.overlay.EnumSixPowers;
import georgetsak.opcraft.common.network.packets.SixPowersPacket;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created by GeorgeProgramming on 7/11/2017.
 */
public class SixPowersCap implements ISixPowersCap {

    private int stillJumps = 0;
    private int ironDamageReceived = 0;
    private int punchDamageGiven = 0;
    private int runningJumps = 0;
    private int distanceRun = 0;
    private int distanceRunInPlants = 0;

    final int[][] allLevels = {
            {250, 500, 750, 1000, 1250}, //Moon Walk
            {100, 200, 300, 400, 500}, //Iron Body
            {100, 200, 300, 400, 500}, //Finger Pistol
            {200, 400, 650, 900, 1200}, //Storm Leg
            {500, 1500, 2500, 5000, 10000}, //Shave
            {200, 500, 800, 1100, 1400}//Paper Drawing
    };

    public static ISixPowersCap get(EntityPlayer player)
    {
        return player.getCapability(SixPowersCapProvider.SP_CAP, null);
    }

    @Override
    public int getPowerLevel(EnumSixPowers power){
         if(power != EnumSixPowers.SIX_KING_GUN) {
            int progress = getProgress(power);

            int[] levels = allLevels[power.id()];

            if (progress < levels[0]) {
                return 0;
            } else if (progress < levels[1]) {
                return 1;
            } else if (progress < levels[2]) {
                return 2;
            } else if (progress < levels[3]) {
                return 3;
            } else if (progress < levels[4]) {
                return 4;
            } else {
                return 5;
            }
        }
        return isSixKingGunUnlocked()?1:0;
    }

    @Override
    public int getProgress(EnumSixPowers power){
        switch (power){
            case MOON_WALK: return stillJumps;
            case IRON_BUDDY: return ironDamageReceived;
            case FINGER_PISTOL: return punchDamageGiven;
            case STORM_LEG: return runningJumps;
            case SHAVE: return distanceRun;
            case PAPER_DRAWING: return distanceRunInPlants;
            default: return 0;
        }
    }

    @Override
    public boolean isSixKingGunUnlocked() {
        return getPowerLevel(EnumSixPowers.MOON_WALK) == 5 && getPowerLevel(EnumSixPowers.IRON_BUDDY) == 5 && getPowerLevel(EnumSixPowers.FINGER_PISTOL) == 5 &&
                getPowerLevel(EnumSixPowers.STORM_LEG) == 5 && getPowerLevel(EnumSixPowers.SHAVE) == 5 && getPowerLevel(EnumSixPowers.PAPER_DRAWING) == 5;
    }

    @Override
    public void setStillJumps(int stillJumps) {
        this.stillJumps = stillJumps;
    }

    @Override
    public void setIronDamageReceived(int damageReceived) {
        ironDamageReceived = damageReceived;
    }

    @Override
    public void setPunchDamageGiven(int damageGiven) {
        punchDamageGiven = damageGiven;
    }

    @Override
    public void setRunningJumps(int runningJumps) {
        this.runningJumps = runningJumps;

    }

    @Override
    public void setDistanceRun(int distanceRun) {
        this.distanceRun = distanceRun;
    }

    @Override
    public void setDistanceRunInPlants(int distanceRunInPlants) {
        this.distanceRunInPlants = distanceRunInPlants;
    }

    @Override
    public void addStillJumps(int stillJumps) {
        this.stillJumps += stillJumps;
    }

    @Override
    public void addIronDamage(int ironDamage) {
        ironDamageReceived += ironDamage;
    }

    @Override
    public void addPunchDamage(int punchDamage) {
        punchDamageGiven += punchDamage;
    }

    @Override
    public void addRunningJumps(int runningJumps) {
        this.runningJumps += runningJumps;
    }

    @Override
    public void addDistanceRun(int distanceRun) {
        this.distanceRun += distanceRun;
    }

    @Override
    public void addDistanceRunInPlants(int distanceRunInPlants) {
        this.distanceRunInPlants += distanceRunInPlants;
    }

    @Override
    public int getStillJumps() {
        return stillJumps;
    }

    @Override
    public int getIronDamageReceived() {
        return ironDamageReceived;
    }

    @Override
    public int getPunchDamageGiven() {
        return punchDamageGiven;
    }

    @Override
    public int getRunningJumps() {
        return runningJumps;
    }

    @Override
    public int getDistanceRun() {
        return distanceRun;
    }

    @Override
    public int getDistanceRunInPlants() {
        return distanceRunInPlants;
    }

    @Override
    public int getRequiredPointsForLevel(EnumSixPowers power) {
        return allLevels[power.id()][getPowerLevel(power)];
    }

    @Override
    public void copy(ISixPowersCap oldCap, EntityPlayer entityPlayer) {
        this.setStillJumps(oldCap.getStillJumps());
        this.setIronDamageReceived(oldCap.getIronDamageReceived());
        this.setPunchDamageGiven(oldCap.getPunchDamageGiven());
        this.setRunningJumps(oldCap.getRunningJumps());
        this.setDistanceRun(oldCap.getDistanceRun());
        this.setDistanceRunInPlants(oldCap.getDistanceRunInPlants());
        PacketDispatcher.sendTo(new SixPowersPacket(this), (EntityPlayerMP) entityPlayer);
    }

    @Override
    public void resetAll(){
        setStillJumps(0);
        setIronDamageReceived(0);
        setPunchDamageGiven(0);
        setRunningJumps(0);
        setDistanceRun(0);
        setDistanceRunInPlants(0);
    }

}
