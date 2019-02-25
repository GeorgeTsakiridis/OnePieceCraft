package georgetsak.opcraft.common.network.packets;

import georgetsak.opcraft.common.capability.sixpowers.ISixPowersCap;
import georgetsak.opcraft.common.capability.sixpowers.SixPowersCap;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

public class SixPowersPacket extends AbstractMessage<SixPowersPacket>{

    private int stillJumps;
    private int ironDamageReceived;
    private int punchDamageGiven;
    private int runningJumps;
    private int distanceRun;
    private int distanceRunInPlants;

    public SixPowersPacket(){}

    public SixPowersPacket(ISixPowersCap sixPowersCap){

        stillJumps = sixPowersCap.getStillJumps();
        ironDamageReceived = sixPowersCap.getIronDamageReceived();
        punchDamageGiven = sixPowersCap.getPunchDamageGiven();
        runningJumps = sixPowersCap.getRunningJumps();
        distanceRun = sixPowersCap.getDistanceRun();
        distanceRunInPlants = sixPowersCap.getDistanceRunInPlants();
    }

    @Override
    protected void read(PacketBuffer buffer) {
        stillJumps = buffer.readInt();
        ironDamageReceived = buffer.readInt();
        punchDamageGiven = buffer.readInt();
        runningJumps = buffer.readInt();
        distanceRun = buffer.readInt();
        distanceRunInPlants = buffer.readInt();
    }

    @Override
    protected void write(PacketBuffer buffer) {
        buffer.writeInt(stillJumps);
        buffer.writeInt(ironDamageReceived);
        buffer.writeInt(punchDamageGiven);
        buffer.writeInt(runningJumps);
        buffer.writeInt(distanceRun);
        buffer.writeInt(distanceRunInPlants);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        ISixPowersCap sixPowersCap = SixPowersCap.get(player);

        sixPowersCap.setStillJumps(stillJumps);
        sixPowersCap.setIronDamageReceived(ironDamageReceived);
        sixPowersCap.setPunchDamageGiven(punchDamageGiven);
        sixPowersCap.setRunningJumps(runningJumps);
        sixPowersCap.setDistanceRun(distanceRun);
        sixPowersCap.setDistanceRunInPlants(distanceRunInPlants);

        if(side.isServer()){
            PacketDispatcher.sendTo(new SixPowersPacket(sixPowersCap), (EntityPlayerMP)player);
        }
    }
}
