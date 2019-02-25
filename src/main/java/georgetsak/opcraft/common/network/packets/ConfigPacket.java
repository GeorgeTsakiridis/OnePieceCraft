package georgetsak.opcraft.common.network.packets;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.util.OPUtils;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

public class ConfigPacket extends AbstractMessage<ConfigPacket> {

    public ConfigPacket() {
    }

    @Override
    protected void read(PacketBuffer buffer) throws IOException {
        OPCraft.config.enableDevilFruitsSpawning = buffer.readBoolean();
        OPCraft.config.enableDevilFruitGomuSpawning = buffer.readBoolean();
        OPCraft.config.enableDevilFruitMeraSpawning = buffer.readBoolean();
        OPCraft.config.enableDevilFruitNoroSpawning = buffer.readBoolean();
        OPCraft.config.enableDevilFruitSukeSpawning = buffer.readBoolean();
        OPCraft.config.enableDevilFruitUshiSpawning = buffer.readBoolean();
        OPCraft.config.enableDevilFruitOpeSpawning = buffer.readBoolean();
        OPCraft.config.enableDevilFruitHieSpawning = buffer.readBoolean();
        OPCraft.config.enableDevilFruitNikyuSpawning = buffer.readBoolean();
        OPCraft.config.enableDevilFruitYomiSpawning = buffer.readBoolean();
        OPCraft.config.enableDevilFruitGoroSpawning = buffer.readBoolean();
        OPCraft.config.enableDevilFruitMokuSpawning = buffer.readBoolean();
        OPCraft.config.enableDevilFruitYamiSpawning = buffer.readBoolean();

        OPCraft.config.completelyDisableDevilFruitGomu = buffer.readBoolean();
        OPCraft.config.completelyDisableDevilFruitMera = buffer.readBoolean();
        OPCraft.config.completelyDisableDevilFruitNoro = buffer.readBoolean();
        OPCraft.config.completelyDisableDevilFruitSuke = buffer.readBoolean();
        OPCraft.config.completelyDisableDevilFruitUshi = buffer.readBoolean();
        OPCraft.config.completelyDisableDevilFruitOpe = buffer.readBoolean();
        OPCraft.config.completelyDisableDevilFruitHie = buffer.readBoolean();
        OPCraft.config.completelyDisableDevilFruitNikyu = buffer.readBoolean();
        OPCraft.config.completelyDisableDevilFruitYomi = buffer.readBoolean();
        OPCraft.config.completelyDisableDevilFruitGoro = buffer.readBoolean();
        OPCraft.config.completelyDisableDevilFruitMoku = buffer.readBoolean();
        OPCraft.config.completelyDisableDevilFruitYami = buffer.readBoolean();

        OPCraft.config.cooldownSpeed = buffer.readInt();
        OPCraft.config.enableSideEffects = buffer.readBoolean();
        OPCraft.config.enableMorganFortress = buffer.readBoolean();
        OPCraft.config.morganFortressSpawnChance = buffer.readInt();
    }

    @Override
    protected void write(PacketBuffer buffer) throws IOException {
        buffer.writeBoolean(OPCraft.config.enableDevilFruitsSpawning);
        buffer.writeBoolean(OPCraft.config.enableDevilFruitGomuSpawning);
        buffer.writeBoolean(OPCraft.config.enableDevilFruitMeraSpawning);
        buffer.writeBoolean(OPCraft.config.enableDevilFruitNoroSpawning);
        buffer.writeBoolean(OPCraft.config.enableDevilFruitSukeSpawning);
        buffer.writeBoolean(OPCraft.config.enableDevilFruitUshiSpawning);
        buffer.writeBoolean(OPCraft.config.enableDevilFruitOpeSpawning);
        buffer.writeBoolean(OPCraft.config.enableDevilFruitHieSpawning);
        buffer.writeBoolean(OPCraft.config.enableDevilFruitNikyuSpawning);
        buffer.writeBoolean(OPCraft.config.enableDevilFruitYomiSpawning);
        buffer.writeBoolean(OPCraft.config.enableDevilFruitGoroSpawning);
        buffer.writeBoolean(OPCraft.config.enableDevilFruitMokuSpawning);
        buffer.writeBoolean(OPCraft.config.enableDevilFruitYamiSpawning);

        buffer.writeBoolean(OPCraft.config.completelyDisableDevilFruitGomu);
        buffer.writeBoolean(OPCraft.config.completelyDisableDevilFruitMera);
        buffer.writeBoolean(OPCraft.config.completelyDisableDevilFruitNoro);
        buffer.writeBoolean(OPCraft.config.completelyDisableDevilFruitSuke);
        buffer.writeBoolean(OPCraft.config.completelyDisableDevilFruitUshi);
        buffer.writeBoolean(OPCraft.config.completelyDisableDevilFruitOpe);
        buffer.writeBoolean(OPCraft.config.completelyDisableDevilFruitHie);
        buffer.writeBoolean(OPCraft.config.completelyDisableDevilFruitNikyu);
        buffer.writeBoolean(OPCraft.config.completelyDisableDevilFruitYomi);
        buffer.writeBoolean(OPCraft.config.completelyDisableDevilFruitGoro);
        buffer.writeBoolean(OPCraft.config.completelyDisableDevilFruitMoku);
        buffer.writeBoolean(OPCraft.config.completelyDisableDevilFruitYami);

        buffer.writeInt(OPCraft.config.cooldownSpeed);
        buffer.writeBoolean(OPCraft.config.enableSideEffects);
        buffer.writeBoolean(OPCraft.config.enableMorganFortress);
        buffer.writeInt(OPCraft.config.morganFortressSpawnChance);

    }

    @Override
    public void process(EntityPlayer player, Side side) {

        if(side.isClient()){
            OPUtils.setFruitsCreativeTab();
        }

    }

}