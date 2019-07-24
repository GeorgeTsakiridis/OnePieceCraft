package georgetsak.opcraft.common.network.packets.client;

import georgetsak.opcraft.common.util.OPUtils;
import georgetsak.opcraft.common.capability.stats.normal.IStatsNormalCap;
import georgetsak.opcraft.common.capability.stats.normal.StatsNormalCapProvider;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

/**
 * Created by GeorgeTsak on 7/15/2017.
 */
public class StatsNormalClientPacket extends AbstractMessage.AbstractClientMessage<StatsNormalClientPacket> {

    int health, attack, defence, speed;


    public StatsNormalClientPacket(){}
    public StatsNormalClientPacket(IStatsNormalCap stats){
        health = stats.getHealthLevel();
        attack = stats.getAttackLevel();
        defence = stats.getDefenceLevel();
        speed = stats.getSpeedLevel();
    }

    @Override
    protected void read(PacketBuffer buffer) throws IOException {
        health = buffer.readInt();
        attack = buffer.readInt();
        defence = buffer.readInt();
        speed = buffer.readInt();
    }

    @Override
    protected void write(PacketBuffer buffer) throws IOException {
        buffer.writeInt(health);
        buffer.writeInt(attack);
        buffer.writeInt(defence);
        buffer.writeInt(speed);
    }

    @Override
    public void process(EntityPlayer player, Side side) {

        if(side.isClient()){
            IStatsNormalCap stats = player.getCapability(StatsNormalCapProvider.SN_CAP, null);
            stats.setHealthLevel(health);
            stats.setAttackLevel(attack);
            stats.setDefenceLevel(defence);
            stats.setSpeedLevel(speed);

            OPUtils.updateStats(player, stats);
        }

    }
}
