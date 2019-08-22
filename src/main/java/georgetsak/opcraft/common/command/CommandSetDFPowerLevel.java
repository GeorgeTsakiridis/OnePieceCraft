package georgetsak.opcraft.common.command;

import georgetsak.opcraft.common.power.PowerHandler;
import georgetsak.opcraft.common.capability.devilfruitlevels.DevilFruitLevelsCap;
import georgetsak.opcraft.common.capability.devilfruitlevels.IDevilFruitLevelsCap;
import georgetsak.opcraft.common.network.packets.common.PacketDevilFruitLevels;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandSetDFPowerLevel extends CommandBase {
    @Override
    public String getName() {
        return "setdfpowerlevel";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/setdfpowerlevel <player> <power #> <power/cooldown> <level>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 4) {
            throw new WrongUsageException("/setdfpowerlevel <player> <power #> <power/cooldown> <level>");
        }else{
            EntityPlayer player = (EntityPlayer) getEntity(server, sender, args[0]);
            int powerID = parseInt(args[1]);
            String mode = args[2];
            int level = parseInt(args[3]);

            IDevilFruitLevelsCap dfl = DevilFruitLevelsCap.get(player);
            int totalPowers = dfl.getAllPowersUses().length;//This is used because server has no access to the registered powers, so PowerHandler#getTotalPowersForFruit() would return always 0.

            System.out.println(totalPowers + " // " + powerID);
            if(totalPowers < 1){
                throw new WrongUsageException(player.getName() + " has not any upgradable powers.");
            }
            if(powerID > totalPowers || powerID < 1){
                throw new WrongUsageException("Not a valid power #.");
            }
            if(!mode.equals("power") && !mode.equals("cooldown")){
                throw new WrongUsageException("Not a valid mode. Valid modes are 'power' and 'cooldown'");
            }
            if(level < 1 || level > 5){
                throw new WrongUsageException("Level must be between 1 and 5");
            }

            switch (mode){
                case "power":
                    dfl.setPowerLevel(powerID,level-1);
                    break;
                case "cooldown":
                    dfl.setPowerCooldown(powerID,level-1);
            }
            notifyCommandListener(sender,this,"Set %s %s level to %s for %s", PowerHandler.getPower(dfl.getDevilFruitID(),powerID).getActionName(), mode, String.valueOf(level), player.getName());
            PacketDispatcher.sendTo(new PacketDevilFruitLevels(dfl),(EntityPlayerMP)player);

        }

    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {

        if(args.length == 1){
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        }else if(args.length == 3){
            List<String> list = new ArrayList<>();
            list.add("power");
            list.add("cooldown");
            return list;
        }

        return Collections.emptyList();
    }
}
