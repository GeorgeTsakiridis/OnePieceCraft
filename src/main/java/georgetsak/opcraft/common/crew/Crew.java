package georgetsak.opcraft.common.crew;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class Crew implements Serializable {



    private String name;
    private UUID owner;
    private ArrayList<Member> members = new ArrayList<Member>();

    public Crew(String name, UUID owner){
        this.name = name;
        this.owner = owner;
    }

    public void addMember(Member member){

        for(Member m : members){
            if(m.getUuid() == member.getUuid())return;
        }

        members.add(member);

    }

    public void removeMember(UUID uuid){
             Member member = getMemberFromUUID(uuid);

             if(member != null){
                 members.remove(member);
             }
    }

    public ArrayList<Member> getMemberList(){
        return members;
    }

    @Nullable
    private Member getMemberFromUUID(UUID uuid){
        for(Member member : members){
            if(member.getUuid().equals(uuid)){
                return member;
            }
        }

        return null;
    }

    public String getName(){
        return name;
    }

    public UUID getOwner(){
        return owner;
    }

    @Override
    public String toString() {
        return "Crew[Name: " + name + ", Members:" + Arrays.toString(members.toArray()) + "]";
    }
}
