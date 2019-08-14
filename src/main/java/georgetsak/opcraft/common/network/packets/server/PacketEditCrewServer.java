package georgetsak.opcraft.common.network.packets.server;

import georgetsak.opcraft.common.capability.stats.normal.StatsNormalCap;
import georgetsak.opcraft.common.crew.Crew;
import georgetsak.opcraft.common.crew.CrewSaveData;
import georgetsak.opcraft.common.crew.EnumRole;
import georgetsak.opcraft.common.crew.Member;
import georgetsak.opcraft.common.network.packets.client.PacketStatsNormalClient;
import georgetsak.opcraft.common.network.packets.client.PacketSyncCrewClient;
import georgetsak.opcraft.common.network.packets.client.PacketCrewInviteClient;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import georgetsak.opcraft.common.util.CrewUtils;
import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.UUID;

public class PacketEditCrewServer extends AbstractMessage.AbstractServerMessage<PacketEditCrewServer> {

    String operation;
    String param1;
    int param2;

    public PacketEditCrewServer(){}

    public PacketEditCrewServer(String operation, String param1, int param2){
        this.operation = operation;
        this.param1 = param1;
        this.param2 = param2;
    }

    @Override
    protected void read(PacketBuffer buffer) throws IOException {
        operation = buffer.readString(20);
        param1 = buffer.readString(100);
        param2 = buffer.readInt();
    }

    @Override
    protected void write(PacketBuffer buffer) throws IOException {
        buffer.writeString(operation);
        buffer.writeString(param1);
        buffer.writeInt(param2);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        CrewSaveData crewSaveData = CrewSaveData.get(player.world);

        if(operation.equals("create") && !param1.isEmpty() &&CrewUtils.isCrewNameAvailable(crewSaveData.getCrews(), param1)) {
            Crew crew = new Crew(param1, player.getPersistentID());
            crew.addMember(new Member(player.getName(), player.getPersistentID(), EnumRole.NO_ROLE));
            crewSaveData.getCrews().add(crew);
            crewSaveData.markDirty();
        }
        else if(operation.equals("setRole")) {
            EntityPlayer target = getPlayerEntityByPersistentUUID(player.world, UUID.fromString(param1));
            if (target != null) {
                Member member = CrewUtils.getMemberFromPlayer(crewSaveData.getCrews(), target);
                if (member != null) {
                    member.setRole(EnumRole.getRoleFromId(param2));
                    crewSaveData.markDirty();
                }
            }

        }
        else if(operation.equals("removeMember")){
            EntityPlayer target = getPlayerEntityByPersistentUUID(player.world, UUID.fromString(param1));
            if(target != null){
                Crew crew = CrewUtils.getPlayerCrew(crewSaveData.getCrews(), target);
                if(crew != null) {
                    if (crew.getOwner().equals(target.getPersistentID())) {
                        crewSaveData.getCrews().remove(crew);
                        crewSaveData.markDirty();
                    } else {
                        crew.removeMember(UUID.fromString(param1));
                        if (crew.getMemberList().isEmpty()) {
                            crewSaveData.getCrews().remove(crew);
                        }
                        crewSaveData.markDirty();
                    }
                }

            }
        }
        else if(operation.equals("inviteMember")){
            EntityPlayer target = getPlayerEntityByPersistentUUID(player.world, UUID.fromString(param1));
            Crew crew = CrewUtils.getPlayerCrew(crewSaveData.getCrews(), player);

            if(target != null && crew != null){
                target.sendMessage(new TextComponentString(TextFormatting.GOLD + player.getName() + TextFormatting.RESET + " invite you to join " + TextFormatting.GOLD + CrewUtils.getPlayerCrew(crewSaveData.getCrews(), player).getName() + TextFormatting.RESET + " crew! Type /joincrew to join!"));
                PacketDispatcher.sendTo(new PacketCrewInviteClient(crew.getName()), (EntityPlayerMP)target);
            }
        }
        else if(operation.equals("addMember")){
            Crew crew = CrewUtils.getCrewFromName(crewSaveData.getCrews(), param1);
            if(crew != null) {
                if (!CrewUtils.isPlayerInACrew(crewSaveData.getCrews(), player)){
                    crew.addMember(new Member(player.getName(), player.getPersistentID(), EnumRole.NO_ROLE));
                    crewSaveData.markDirty();
                    player.sendMessage(new TextComponentString(TextFormatting.GOLD + "You joined the crew!"));

                    for(Member member : crew.getMemberList()){
                        EntityPlayer target = player.world.getPlayerEntityByUUID(member.getUuid());
                        if(target != null){
                            target.sendMessage(CrewUtils.buildTextComponentString(player.getName() + " joined your crew!", null));
                        }
                    }

                }else{
                    player.sendMessage(new TextComponentString("You cannot be a member at two crews at the same time!"));
                }
            }
        }
        OPUtils.refreshStats(player); //Refresh the stats in order to update the Doctor increased life perk.
        PacketDispatcher.sendToAll(new PacketSyncCrewClient(crewSaveData.getCrews()));

        PacketDispatcher.sendTo(new PacketStatsNormalClient(StatsNormalCap.get(player)),(EntityPlayerMP) player);//Make the stats to refresh in client in order to update the Doctor increased life perk.
    }

    @Nullable
    public EntityPlayer getPlayerEntityByPersistentUUID(World world, UUID uuid)
    {
        for (int i = 0; i < world.playerEntities.size(); ++i)
        {
            EntityPlayer entityplayer = world.playerEntities.get(i);

            if (uuid.equals(entityplayer.getPersistentID()))
            {
                return entityplayer;
            }
        }

        return null;
    }


}
