package georgetsak.opcraft.common.command;

import georgetsak.opcraft.common.capability.sixpowers.ISixPowersCap;
import georgetsak.opcraft.common.capability.sixpowers.SixPowersCap;
import georgetsak.opcraft.common.network.packets.common.SixPowersPacket;
import georgetsak.opcraft.common.util.OPUtils;
import georgetsak.opcraft.common.capability.haki.HakiCap;
import georgetsak.opcraft.common.capability.haki.IHakiCap;
import georgetsak.opcraft.common.capability.stats.normal.IStatsNormalCap;
import georgetsak.opcraft.common.capability.stats.normal.StatsNormalCap;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import georgetsak.opcraft.common.network.packets.common.HakiPacket;
import georgetsak.opcraft.common.network.packets.client.StatsNormalClientPacket;
import net.minecraft.command.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by GeorgeTsak on 8/11/2017.
 */
public class CommandResetStats extends CommandBase {
    @Override
    public String getName() {
        return "resetstats";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/resetstats <player> <stat>";
    }

    public int getRequiredPermissionLevel()
    {
        return 2;
    }


    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(args.length < 2){
            throw new WrongUsageException("/resetstats <player> <stat>");
        }else{
            Entity entity = getEntity(server, sender, args[0]);

            switch (args[1]){
                case "all":{
                    resetAll(entity, sender);
                    break;
                }
                case "stats":{
                    resetStats(entity, sender, true);
                    break;
                }
                case "haki":{
                    resetHaki(entity, sender, true);
                    break;
                }
                case "six_powers":{
                    resetSixPowers(entity, sender, true);
                    break;
                }
                default:
                    throw new SyntaxErrorException("Invalid stat");
            }
        }


    }

    private void resetAll(Entity entity, ICommandSender sender){
        resetStats(entity, sender, false);
        resetHaki(entity, sender, false);
        resetSixPowers(entity, sender, false);
        notifyCommandListener(sender, this, "Reset all %s's stats", entity.getName());
    }

    private void resetStats(Entity entity, ICommandSender sender, boolean notify) {
        IStatsNormalCap stats = StatsNormalCap.get((EntityPlayer) entity);
        stats.resetAll();
        PacketDispatcher.sendTo(new StatsNormalClientPacket(stats), (EntityPlayerMP) entity);
        OPUtils.updateStats((EntityPlayer) entity, stats);
        if (notify) {
            notifyCommandListener(sender, this, "Reset %s's Stats", entity.getName());
        }
    }

    private void resetHaki(Entity entity, ICommandSender sender, boolean notify) {
        IHakiCap haki = HakiCap.get((EntityPlayer) entity);
        haki.resetAll();
        PacketDispatcher.sendTo(new HakiPacket(haki), (EntityPlayerMP)entity);
        if (notify) {
            notifyCommandListener(sender, this, "Reset %s's Haki", entity.getName());
        }
    }

    private void resetSixPowers(Entity entity, ICommandSender sender, boolean notify){
        ISixPowersCap sixPowers = SixPowersCap.get((EntityPlayer) entity);
        sixPowers.resetAll();
        PacketDispatcher.sendTo(new SixPowersPacket(sixPowers), (EntityPlayerMP)entity);
        if (notify) {
            notifyCommandListener(sender, this, "Reset %s's Six Powers", entity.getName());
        }
    }


    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {

        List<String> list = new ArrayList<>();

        if(args.length == 1){
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        }
        else if(args.length == 2){
            List<String> list2 = new ArrayList<>();
            list2.add("all");
            list2.add("stats");
            list2.add("haki");
            list2.add("six_powers");
            return getListOfStringsMatchingLastWord(args, list2);
        }

        return Collections.emptyList();
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index)
    {
        return index == 1;
    }



}

