package georgetsak.opcraft.common.capability.haki;

import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import georgetsak.opcraft.common.network.packets.HakiPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class HakiCap implements IHakiCap{

    private int dodge = 0;
    private int emperor = 0;
    private int attack = 0;
    private int defence = 0;
    private boolean isEmperorHakiUnlocked = false;

    public static IHakiCap get(EntityPlayer player)
    {
        return player.getCapability(HakiCapProvider.H_CAP, null);
    }

    @Override
    public void setDodgeLevel(int level) { dodge = level; }

    @Override
    public void setEmperorLevel(int level) { emperor = level;}

    @Override
    public void setAttackLevel(int level) { attack = level;}

    @Override
    public void setDefenceLevel(int level) { defence = level;}

    @Override
    public void setUnlockedEmperorHaki(boolean unlocked) {
        isEmperorHakiUnlocked = unlocked;
    }

    @Override
    public int getDodgeLevel() { return dodge;}

    @Override
    public int getEmperorLevel() { return emperor; }

    @Override
    public int getAttackLevel() { return attack; }

    @Override
    public int getDefenceLevel() { return defence; }

    public boolean isEmperorHakiUnlocked(){ return isEmperorHakiUnlocked;}

    @Override
    public int getUpgradeCost(int id) {

        switch (id){
            case 1: return 15 + dodge * 4;
            case 2: return 15 + emperor * 5;
            case 3: return 10 + attack * 4;
            case 4: return 10 + defence * 4;
        }

        return 0;
    }

    @Override
    public boolean canUpgrade(EntityPlayer entityPlayer, int id) {
        int xp = entityPlayer.experienceLevel;

        switch(id){
            case 1: return dodge < 5 && xp >= getUpgradeCost(1);
            case 2: return emperor < 5 && xp >= getUpgradeCost(2);
            case 3: return attack < 5 && xp >= getUpgradeCost(3);
            case 4: return defence < 5 && xp >= getUpgradeCost(4);
        }
        return false;
    }

    @Override
    public void copy(IHakiCap old_h, EntityPlayer ep) {
        this.setDodgeLevel(old_h.getDodgeLevel());
        this.setEmperorLevel(old_h.getEmperorLevel());
        this.setAttackLevel(old_h.getAttackLevel());
        this.setDefenceLevel(old_h.getDefenceLevel());
        this.setUnlockedEmperorHaki(old_h.isEmperorHakiUnlocked());
        PacketDispatcher.sendTo(new HakiPacket(this), (EntityPlayerMP) ep);
    }

    @Override
    public void resetAll() {
        setDodgeLevel(0);
        setEmperorLevel(0);
        setAttackLevel(0);
        setDefenceLevel(0);
        setUnlockedEmperorHaki(false);
    }

}
