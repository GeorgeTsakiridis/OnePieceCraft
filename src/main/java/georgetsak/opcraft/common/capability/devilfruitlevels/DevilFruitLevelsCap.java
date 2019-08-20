package georgetsak.opcraft.common.capability.devilfruitlevels;

import georgetsak.opcraft.client.power.Power;
import georgetsak.opcraft.client.power.PowerHandler;
import georgetsak.opcraft.common.network.packets.common.PacketDevilFruitLevels;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import georgetsak.opcraft.common.registry.OPDevilFruits;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class DevilFruitLevelsCap implements IDevilFruitLevelsCap {

    final public static int PLAYER_KILLED_WITH_DF_XP = 15;
    final public static int PLAYER_KILLED_WITHOUT_DF_XP = 10;
    final public static int ENTITY_KILLED_WITH_DF_XP = 10;
    final public static int ENTITY_KILLED_WITHOUT_DF_XP = 5;

    private int[] uses = {};
    private int[] powersLevels = {};
    private int xp = 0;
    private int devilFruitID = OPDevilFruits.NO_POWER;

    public static IDevilFruitLevelsCap get(EntityPlayer player)
    {
        return player.getCapability(DevilFruitLevelsCapProvider.DFL_CAP, null);
    }


    @Override
    public void setDevilFruitID(int devilFruitID) {
        if(devilFruitID != this.devilFruitID) {
            this.devilFruitID = devilFruitID;
            resetAll();
        }
    }

    public int getDevilFruitID(){
        return devilFruitID;
    }

    @Override
    public int getPowerLevel(int id) {
        return powersLevels[id-1];
    }

    @Override
    public int getPowerTotalUses(int id) {
        return uses[id];
    }

    @Override
    public int[] getAllPowersUses(){
        return uses;
    }

    @Override
    public void addPowerUses(int id) {
        uses[id]++;
    }

    @Override
    public void setPowerUses(int[] uses){
        this.uses = uses;
    }

    @Override
    public int[] getAllPowersLevels(){
        return powersLevels;
    }

    @Override
    public void addPowerLevel(int id) {
        powersLevels[id-1]++;
    }

    @Override
    public void setPowersLevels(int[] powersLevels){
        this.powersLevels = powersLevels;
    }

    @Override
    public void setXP(int xp) {
        this.xp = xp;
    }

    @Override
    public void addXP(int xp) {
        this.xp += xp;
    }

    @Override
    public int getXP() {
        return xp;
    }

    @Override
    public int getPowerCooldown(int id) {
        Power power = PowerHandler.getPower(getDevilFruitID(), id);

        return power.getCooldownTime(getPowerCooldownLevel(id)-1);
    }

    @Override
    public int getPowerCooldownLevel(int id) {
        Power power = PowerHandler.getPower(getDevilFruitID(), id);
        if(power == null)return 0;

        int i = 0;
        for(int requiredUses : power.getUsesToReduceCooldown()) {
            if (uses[id-1] < requiredUses){
                break;
            }
            i++;
        }
        return i + 1;
    }

    @Override
    public boolean canPowerLevelBeIncreased(int id) {
        Power power = PowerHandler.getPower(getDevilFruitID(),id);
        if(power == null)return false;

        return power.getHitsToUpgradePower() != null;
    }

    @Override
    public boolean canPowerCooldownBeReduced(int id) {
        Power power = PowerHandler.getPower(getDevilFruitID(),id);
        if(power == null)return false;

        return power.getUsesToReduceCooldown() != null;
    }

    @Override
    public void resetAll() {
        uses = new int[PowerHandler.getTotalPowersForFruit(devilFruitID)];
        powersLevels = new int[PowerHandler.getTotalPowersForFruit(devilFruitID)];
    }

    @Override
    public void copy(IDevilFruitLevelsCap dfl, EntityPlayer ep) {
        this.setDevilFruitID(getDevilFruitID());
        this.setPowerUses(uses);
        this.setPowersLevels(powersLevels);
        this.setXP(xp);
        PacketDispatcher.sendTo(new PacketDevilFruitLevels(dfl), (EntityPlayerMP)ep);
    }

}
