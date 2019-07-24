package georgetsak.opcraft.common.command;

import georgetsak.opcraft.common.capability.bounty.BountyCap;
import georgetsak.opcraft.common.capability.bounty.IBountyCap;
import georgetsak.opcraft.common.network.packets.client.BountyClientPacket;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.command.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class CommandAddBounty extends CommandBase {
    @Override
    public String getName() {
        return "addbounty";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/addbounty <player> <amount>";
    }

    public int getRequiredPermissionLevel()
    {
        return 2;
    }


    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 2)
        {
            throw new WrongUsageException("/addbounty <player> <amount>");
        }
        else
        {
            Entity entity = getEntity(server, sender, args[0]);
            if(entity instanceof EntityPlayer) {
                IBountyCap bountyCap = BountyCap.get((EntityPlayer) entity);

                int bounty = parseInt(args[1]);
                bountyCap.changeBountyBy(bounty);
                PacketDispatcher.sendTo(new BountyClientPacket(bountyCap),(EntityPlayerMP)entity);
                notifyCommandListener(sender, this, "Added %s to %s's bounty", String.valueOf(bounty), entity.getName());
            }
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
