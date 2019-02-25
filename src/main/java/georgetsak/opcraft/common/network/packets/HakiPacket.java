package georgetsak.opcraft.common.network.packets;

import georgetsak.opcraft.common.capability.haki.HakiCap;
import georgetsak.opcraft.common.capability.haki.IHakiCap;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

public class HakiPacket extends AbstractMessage<HakiPacket>{

    int dodge;
    int emperor;
    int attack;
    int defence;
    boolean unlockedEmperorHaki;

    public HakiPacket(){}

    public HakiPacket(IHakiCap hakiCap){
        dodge = hakiCap.getDodgeLevel();
        emperor = hakiCap.getEmperorLevel();
        attack = hakiCap.getAttackLevel();
        defence = hakiCap.getDefenceLevel();
        unlockedEmperorHaki = hakiCap.isEmperorHakiUnlocked();
    }

    @Override
    protected void read(PacketBuffer buffer) {
        dodge = buffer.readInt();
        emperor = buffer.readInt();
        attack = buffer.readInt();
        defence = buffer.readInt();
        unlockedEmperorHaki = buffer.readBoolean();
    }

    @Override
    protected void write(PacketBuffer buffer) {
        buffer.writeInt(dodge);
        buffer.writeInt(emperor);
        buffer.writeInt(attack);
        buffer.writeInt(defence);
        buffer.writeBoolean(unlockedEmperorHaki);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
            IHakiCap hakiCap = HakiCap.get(player);
            hakiCap.setDodgeLevel(dodge);
            hakiCap.setEmperorLevel(emperor);
            hakiCap.setAttackLevel(attack);
            hakiCap.setDefenceLevel(defence);
            hakiCap.setUnlockedEmperorHaki(unlockedEmperorHaki);

    }
}
