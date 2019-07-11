package georgetsak.opcraft.common.crew;

import java.io.Serializable;
import java.util.UUID;

public class Member implements Serializable {

    private String name;
    private UUID uuid;
    private EnumRole role;

    public Member(String name, UUID uuid, EnumRole role){
        this.name = name;
        this.uuid = uuid;
        this.role = role;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setRole(EnumRole role){
        this.role = role;
    }

    public String getName(){
        return name;
    }

    public EnumRole getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Member[UUID: " + uuid.toString() + ", Role: " + role + "]";
    }
}
