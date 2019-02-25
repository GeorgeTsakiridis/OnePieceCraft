package georgetsak.opcraft.common.network.packets;

import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.core.jmx.Server;

import java.io.IOException;
import java.util.UUID;

public class DamageEntityPacket extends AbstractMessage.AbstractServerMessage<DamageEntityPacket> {

    int entityID;
    float amount;

    public DamageEntityPacket(){}

    public DamageEntityPacket(Entity entity, float amount) {
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
