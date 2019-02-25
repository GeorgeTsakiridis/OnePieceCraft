package georgetsak.opcraft.dev_notUsed;

import georgetsak.opcraft.OPCraft;
import net.minecraftforge.fml.common.FMLLog;

/**
 * Created by GeorgeProgramming on 4/21/2017.
 */
public class OPLog {

     private static final String prefix = "[" + OPCraft.NAME + "]";

     public static void logWarning(Object message){FMLLog.warning(prefix + String.valueOf(message));}
     public static void logInfo(Object message){FMLLog.info(prefix + String.valueOf(message));}

}
