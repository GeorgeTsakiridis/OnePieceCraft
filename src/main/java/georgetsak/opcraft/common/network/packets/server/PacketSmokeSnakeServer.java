package georgetsak.opcraft.common.network.packets.server;

import georgetsak.opcraft.common.entity.devilfruit.EntitySmokeSnake;
import georgetsak.opcraft.client.OPSoundEvent;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

public class PacketSmokeSnakeServer extends AbstractMessage.AbstractServerMessage<PacketSmokeSnakeServer> {

    int targetID;

    public PacketSmokeSnakeServer(){}

    public PacketSmokeSnakeServer(Entity target){
        this.targetID = target.getEntityId();
    }

    @Override
    protected void read(PacketBuffer buffer) throws IOException {
        targetID = buffer.readInt();
    }

    @Override
    protected void write(PacketBuffer buffer) throws IOException {
        buffer.writeInt(targetID);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        Entity target = player.world.getEntityByID(targetID);
        if (side.isServer()) {
            if (target != null) {
                EntitySmokeSnake smokeSnake = new EntitySmokeSnake(player, target);
                player.world.spawnEntity(smokeSnake);
                player.world.playSound(null, player.getPosition(), OPSoundEvent.smoke_whoosh, SoundCategory.NEUTRAL, 10.0F, 1.0F);
            }
        }
    }
}
