package georgetsak.opcraft.common.command;

import georgetsak.opcraft.common.capability.bounty.BountyCapProvider;
import georgetsak.opcraft.common.capability.bounty.IBountyCap;
import georgetsak.opcraft.common.network.packets.client.BountyClientPacket;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

/**
 * Created by GeorgeTsak on 8/11/2017.
 */
public class CommandSetBounty extends CommandBase {
    @Override
    public String getName() {
        return "setbounty";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/setbounty <player> <amount>";
    }

    public int getRequiredPermissionLevel()
    {
        return 2;
    }


    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 2)
        {
            throw new WrongUsageException("/setbounty <player> <amount>");
        }
        else
        {
            Entity entity = getEntity(server, sender, args[0]);
            IBountyCap bountyCap = entity.getCapability(BountyCapProvider.B_CAP, null);

            String bountyString = args[1];
            int bounty = parseInt(bountyString);
            bountyCap.setBounty(bounty);
            PacketDispatcher.sendTo(new BountyClientPacket(bountyCap),(EntityPlayerMP)entity);

            notifyCommandListener(sender, this, "Set %s's bounty to %s", entity.getName(), bountyString);
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index)
    {
        return index == 1;
    }



}

