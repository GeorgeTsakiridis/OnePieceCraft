package georgetsak.opcraft.common.util;

import georgetsak.opcraft.common.crew.Crew;
import georgetsak.opcraft.common.crew.Member;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class CrewUtils {

    public static boolean isPlayerInACrew(ArrayList<Crew> crews, EntityPlayer player){
        for(Crew crew : crews){
            for(Member member : crew.getMemberList()){
                if(member.getUuid().equals(player.getPersistentID()))return true;
            }
        }
        return false;
    }

    public static boolean isPlayerInCrew(Crew crew, EntityPlayer player){
        for(Member member : crew.getMemberList()){
            if(member.getUuid().equals(player.getPersistentID()))return true;
        }
        return false;
    }

    public static boolean isCrewNameAvailable(ArrayList<Crew> crews, String name){
        for(Crew crew : crews){
            if(crew.getName().equals(name))return false;
        }
        return true;
    }

    @Nullable
    public static Member getMemberFromPlayer(ArrayList<Crew> crews, EntityPlayer player){
        Crew crew = getPlayerCrew(crews, player);
        if(crew != null){
            for(Member member : crew.getMemberList()){
                if(member.getUuid().equals(player.getPersistentID()))return member;
            }
        }

        return null;
    }

    @Nullable
    public static Crew getPlayerCrew(ArrayList<Crew> crews, EntityPlayer player){
        for(Crew crew : crews){
            if(isPlayerInCrew(crew, player))return crew;
        }
        return null;
    }

    public static ArrayList<String> getCrewNameList(ArrayList<Crew> crews){
        ArrayList<String> string = new ArrayList<>();

        for(Crew crew : crews){
            string.add(crew.getName());
        }

        return string;
    }

    public static String buildStringForMember(EntityPlayer player, Member member, Crew crew){
        EntityPlayer memberPlayerEntity = player.world.getPlayerEntityByUUID(member.getUuid());

        boolean isOwner = crew.getOwner().equals(member.getUuid());
        boolean isOnline = memberPlayerEntity != null;

        return (isOwner?"(Captain) ":"") + member.getName() + " / " + member.getRole().getName() + " (" + (isOnline?"Online":"Offline")+")";

    }

    public static TextComponentString buildTextComponentString(String message, @Nullable String fromPlayerName){
        return new TextComponentString(TextFormatting.GOLD + "[Crew" + (fromPlayerName!=null?"/"+fromPlayerName:"") + "]: " + message);
    }

    @Nullable
    public static Crew getCrewFromName(ArrayList<Crew> crews, String name){
        for(Crew crew : crews){
            if(crew.getName().equals(name)){
                return crew;
            }
        }
        return null;
    }

}
