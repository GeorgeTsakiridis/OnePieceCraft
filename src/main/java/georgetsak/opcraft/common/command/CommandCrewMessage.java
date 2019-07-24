package georgetsak.opcraft.common.command;

import georgetsak.opcraft.client.proxy.ClientProxy;
import georgetsak.opcraft.common.crew.CrewSaveData;
import georgetsak.opcraft.common.crew.Member;
import georgetsak.opcraft.common.util.CrewUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;

public class CommandCrewMessage extends CommandBase {

    @Override
    public String getName() {
        return "msgc";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/msgc <message>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1) {
            throw new WrongUsageException("/msgc <message>");
        } else {
            EntityPlayer player = getCommandSenderAsPlayer(sender);
            CrewSaveData crewSaveData = CrewSaveData.get(player.world);
            if (CrewUtils.isPlayerInACrew(crewSaveData.getCrews(), player)) {

                ITextComponent itextcomponent = getChatComponentFromNthArg(sender, args, 0, !(sender instanceof EntityPlayer));
                String message = itextcomponent.getUnformattedText();
                ITextComponent text = CrewUtils.buildTextComponentString(message, player.getName());

                for (Member member : CrewUtils.getPlayerCrew(crewSaveData.getCrews(), player).getMemberList()) {
                    EntityPlayer target = player.world.getPlayerEntityByUUID(member.getUuid());
                    if (target != null) {
                        target.sendMessage(text);
                    }
                }
            } else {
                notifyCommandListener(sender, this, "You are not in a crew to use that!");
            }
        }
    }

}
