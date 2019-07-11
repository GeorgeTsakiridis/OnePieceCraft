package georgetsak.opcraft.common.crew;

public enum EnumRole {

    NO_ROLE(0), SWORDSMAN(1), ARCHER(2), FIGHTER(3), DOCTOR(4);

    int id;

    EnumRole(int id){
        this.id = id;
    }

    int getId(){
        return id;
    }

    public static EnumRole getRoleFromId(int id){
        switch (id){
            default:
            case 0: return NO_ROLE;
            case 1: return SWORDSMAN;
            case 2: return ARCHER;
            case 3: return FIGHTER;
            case 4: return DOCTOR;
        }
    }

    public String getName(){
        switch (id){
            default:
            case 0: return "No Role";
            case 1: return "Swordsman";
            case 2: return "Archer";
            case 3: return "Fighter";
            case 4: return "Doctor";
        }
    }

}
