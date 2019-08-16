package georgetsak.opcraft.common.capability.devilfruitlevels;

import georgetsak.opcraft.client.power.Power;
import georgetsak.opcraft.client.power.PowerHandler;
import georgetsak.opcraft.common.network.packets.client.PacketDevilFruitLevelsClient;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import georgetsak.opcraft.common.registry.OPDevilFruits;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class DevilFruitLevelsCap implements IDevilFruitLevelsCap {

    private int[] uses = {};
    private int[] hits = {};
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
        Power power = PowerHandler.getPower(getDevilFruitID(), id);
        if(power == null)return 0;

        int level = 0;
        for(int requiredHits : power.getHitsToUpgradePower()){
            if(hits[id-1] < requiredHits){
                break;
            }
            level++;
        }

        return level;
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
    public int getPowerHits(int id) {
        return hits[id];
    }

    @Override
    public int[] getAllPowersHits(){
        return hits;
    }

    @Override
    public void addPowerHit(int id) {
        hits[id]++;
    }

    @Override
    public void setPowerHits(int[] hits){
        this.hits = hits;
    }

    @Override
    public int getPowerCooldown(int id) {
        Power power = PowerHandler.getPower(getDevilFruitID(), id);

        return power.getCooldownTime(getPowerCooldown(id)-1);
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
        hits = new int[PowerHandler.getTotalPowersForFruit(devilFruitID)];
    }

    @Override
    public void copy(IDevilFruitLevelsCap dfl, EntityPlayer ep) {
        this.setDevilFruitID(getDevilFruitID());
        this.setPowerUses(uses);
        this.setPowerHits(hits);
        PacketDispatcher.sendTo(new PacketDevilFruitLevelsClient(dfl), (EntityPlayerMP)ep);
    }

}
