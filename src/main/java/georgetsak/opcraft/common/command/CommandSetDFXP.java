package georgetsak.opcraft.common.command;

import georgetsak.opcraft.common.capability.bounty.BountyCapProvider;
import georgetsak.opcraft.common.capability.bounty.IBountyCap;
import georgetsak.opcraft.common.capability.devilfruitlevels.DevilFruitLevelsCap;
import georgetsak.opcraft.common.capability.devilfruitlevels.IDevilFruitLevelsCap;
import georgetsak.opcraft.common.network.packets.client.PacketBountyClient;
import georgetsak.opcraft.common.network.packets.common.PacketDevilFruitLevels;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class CommandSetDFXP extends CommandBase {
    @Override
    public String getName() {
        return "setdfxp";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/setdfxp <player> <amount>";
    }

    public int getRequiredPermissionLevel()
    {
        return 2;
    }


    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 2)
        {
            throw new WrongUsageException("/setdfxp <player> <amount>");
        }
        else
        {
            Entity entity = getEntity(server, sender, args[0]);
            IDevilFruitLevelsCap dfl = DevilFruitLevelsCap.get((EntityPlayer)entity);

            String xpString = args[1];
            int xp = parseInt(xpString);
            dfl.setXP(xp);
            PacketDispatcher.sendTo(new PacketDevilFruitLevels(dfl),(EntityPlayerMP)entity);

            notifyCommandListener(sender, this, "Set %s's Devil Fruit XP to %s", entity.getName(), xpString);
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

