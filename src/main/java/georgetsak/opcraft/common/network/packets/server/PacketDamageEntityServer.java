package georgetsak.opcraft.common.network.packets.server;

import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.relauncher.Side;

public class PacketDamageEntityServer extends AbstractMessage.AbstractServerMessage<PacketDamageEntityServer> {

    int entityID;
    float amount;

    public PacketDamageEntityServer(){}

    public PacketDamageEntityServer(Entity entity, float amount) {
        this.entityID = entity.getEntityId();
        this.amount = amount;
    }

    @Override
    protected void read(PacketBuffer buffer) {
        entityID = buffer.readInt();
        amount = buffer.readFloat();
    }

    @Override
    protected void write(PacketBuffer buffer) {
        buffer.writeInt(entityID);
        buffer.writeFloat(amount);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        Entity entity = player.world.getEntityByID(entityID);
        if (entity != null) {
            entity.attackEntityFrom(DamageSource.causePlayerDamage(player), amount);
        }
    }
}
