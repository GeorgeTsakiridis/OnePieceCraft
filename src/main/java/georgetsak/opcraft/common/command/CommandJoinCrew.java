package georgetsak.opcraft.common.command;

import georgetsak.opcraft.client.proxy.ClientProxy;
import georgetsak.opcraft.common.network.packets.server.EditCrewServerPacket;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandJoinCrew extends CommandBase {
    @Override
    public String getName() {
        return "joincrew";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/joincrew";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 0)
        {
            throw new WrongUsageException("/joincrew");
        }
        else {
            if (ClientProxy.crewLastInviteName != null) {
                PacketDispatcher.sendToServer(new EditCrewServerPacket("addMember", ClientProxy.crewLastInviteName, 0));
                ClientProxy.crewLastInviteName = null;
            }else{
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString("You have no pending invite!"));
            }
        }
    }

}
