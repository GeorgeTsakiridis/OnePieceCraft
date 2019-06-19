package georgetsak.opcraft.common.capability.stats.normal;

import georgetsak.opcraft.common.util.OPUtils;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import georgetsak.opcraft.common.network.packets.client.StatsNormalClientPacket;
import georgetsak.opcraft.common.network.packets.server.StatsNormalServerPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created by GeorgeTsak on 7/15/2017.
 */
public class StatsNormalCap implements IStatsNormalCap{

    private int health = 0;
    private int attack = 0;
    private int defence = 0;
    private int speed = 0;

    @Override
    public void setHealthLevel(int level) {
        this.health = level;
    }

    @Override
    public void setAttackLevel(int level) {
        this.attack = level;
    }

    @Override
    public void setDefenceLevel(int level) {
        this.defence = level;
    }

    @Override
    public void setSpeedLevel(int level) {
        this.speed = level;
    }

    @Override
    public int getHealthLevel() {
        return health;
    }

    @Override
    public int getAttackLevel() {
        return attack;
    }

    @Override
    public int getDefenceLevel() {
        return defence;
    }

    @Override
    public int getSpeedLevel() {
        return speed;
    }

    @Override
    public int getUpgradeCost(int statID) {

        switch (statID){
            case 1: return 10 + health * 2;
            case 2: return 15 + attack * 4;
            case 3: return 15 + defence * 4;
            case 4: return 10 + speed * 2;
        }

        return 0;
    }

    @Override
    public boolean canUpgrade(int statID, EntityPlayer entityPlayer) {
       int xp = entityPlayer.experienceLevel;

        switch(statID){
            case 1: return health < 10 && xp >= getUpgradeCost(1);
            case 2: return attack < 10 && xp >= getUpgradeCost(2);
            case 3: return defence < 10 && xp >= getUpgradeCost(3);
            case 4: return speed < 10 && xp >= getUpgradeCost(4);
        }
        return false;

    }

    @Override
    public void resetAll(){
        setHealthLevel(0);
        setAttackLevel(0);
        setDefenceLevel(0);
        setSpeedLevel(0);
    }

    public static IStatsNormalCap get(EntityPlayer player)
    {
        return player.getCapability(StatsNormalCapProvider.SN_CAP, null);
    }

    public void updateToSever(IStatsNormalCap stats){
        PacketDispatcher.sendToServer(new StatsNormalServerPacket(stats));
    }

    public void copy(IStatsNormalCap old_ns, EntityPlayer ep) {
        this.setHealthLevel(old_ns.getHealthLevel());
        this.setAttackLevel(old_ns.getAttackLevel());
        this.setDefenceLevel(old_ns.getDefenceLevel());
        this.setSpeedLevel(old_ns.getSpeedLevel());
        OPUtils.updateStats(ep, this);

        PacketDispatcher.sendTo(new StatsNormalClientPacket(old_ns), (EntityPlayerMP) ep);
    }


}
