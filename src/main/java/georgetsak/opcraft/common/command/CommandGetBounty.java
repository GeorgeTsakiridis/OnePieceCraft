package georgetsak.opcraft.common.command;

import georgetsak.opcraft.common.capability.bounty.BountyCapProvider;
import georgetsak.opcraft.common.capability.bounty.IBountyCap;
import net.minecraft.command.*;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class CommandGetBounty extends CommandBase {
    @Override
    public String getName() {
        return "getbounty";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/getbounty <player>";
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
        if(args.length < 1){
            throw new WrongUsageException("/getbounty <player>", new Object[0]);
        }

        Entity entity = getEntity(server, sender, args[0]);
        IBountyCap bountyCap = entity.getCapability(BountyCapProvider.B_CAP, null);
        notifyCommandListener(sender, this, "%s's bounty is %s", new Object[] {entity.getName(), bountyCap.getBounty()});
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.<String>emptyList();
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index)
    {
        return index == 1;
    }

}
