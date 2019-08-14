package georgetsak.opcraft.common.command;

import georgetsak.opcraft.client.gui.overlay.EnumSixPowers;
import georgetsak.opcraft.common.capability.sixpowers.ISixPowersCap;
import georgetsak.opcraft.common.capability.sixpowers.SixPowersCapProvider;
import georgetsak.opcraft.common.network.packets.common.PacketSixPowers;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.command.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandSetSixPowerLevel extends CommandBase {
    @Override
    public String getName() {
        return "setsixpowerlevel";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/setsixpowerlevel <player> <power> <level>";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 3)
        {
            throw new WrongUsageException("/setsixpowerlevel <player> <power> <level>");
        }
        else{

            int newLevel = Integer.parseInt(args[2]);
            if(newLevel < 0 || newLevel > 5){
                throw new SyntaxErrorException("Level must be between 0 and 5");
            }

            Entity entity = getEntity(server, sender, args[0]);
            ISixPowersCap sixPowersCap = entity.getCapability(SixPowersCapProvider.SP_CAP, null);

            switch (args[1]){

                case "geppo":{
                    sixPowersCap.setStillJumps(sixPowersCap.getRequiredPointsForLevel(EnumSixPowers.MOON_WALK, newLevel));
                    break;
                }
                case "tekkai":{
                    sixPowersCap.setIronDamageReceived(sixPowersCap.getRequiredPointsForLevel(EnumSixPowers.IRON_BUDDY, newLevel));
                    break;
                }
                case "shigan":{
                    sixPowersCap.setPunchDamageGiven(sixPowersCap.getRequiredPointsForLevel(EnumSixPowers.FINGER_PISTOL, newLevel));
                    break;
                }
                case "rankyaku":{
                    sixPowersCap.setRunningJumps(sixPowersCap.getRequiredPointsForLevel(EnumSixPowers.STORM_LEG, newLevel));
                    break;
                }
                case "soru":{
                    sixPowersCap.setDistanceRun(sixPowersCap.getRequiredPointsForLevel(EnumSixPowers.SHAVE, newLevel));
                    break;
                }
                case "kamie":{
                    sixPowersCap.setDistanceRunInPlants(sixPowersCap.getRequiredPointsForLevel(EnumSixPowers.PAPER_DRAWING, newLevel));
                    break;
                }
                case "all":{
                    sixPowersCap.setStillJumps(sixPowersCap.getRequiredPointsForLevel(EnumSixPowers.MOON_WALK, newLevel));
                    sixPowersCap.setIronDamageReceived(sixPowersCap.getRequiredPointsForLevel(EnumSixPowers.IRON_BUDDY, newLevel));
                    sixPowersCap.setPunchDamageGiven(sixPowersCap.getRequiredPointsForLevel(EnumSixPowers.FINGER_PISTOL, newLevel));
                    sixPowersCap.setRunningJumps(sixPowersCap.getRequiredPointsForLevel(EnumSixPowers.STORM_LEG, newLevel));
                    sixPowersCap.setDistanceRun(sixPowersCap.getRequiredPointsForLevel(EnumSixPowers.SHAVE, newLevel));
                    sixPowersCap.setDistanceRunInPlants(sixPowersCap.getRequiredPointsForLevel(EnumSixPowers.PAPER_DRAWING, newLevel));
                    break;
                }

                default:{
                    throw new SyntaxErrorException("Invalid Six Power");
                }
            }

            PacketDispatcher.sendTo(new PacketSixPowers(sixPowersCap),(EntityPlayerMP)entity);
            notifyCommandListener(sender, this, "%s", "Done!");

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
            list2.add("geppo");
            list2.add("tekkai");
            list2.add("shigan");
            list2.add("rankyaku");
            list2.add("soru");
            list2.add("kamie");
            list2.add("all");
            return getListOfStringsMatchingLastWord(args, list2);
        }

        return Collections.emptyList();
    }


}
