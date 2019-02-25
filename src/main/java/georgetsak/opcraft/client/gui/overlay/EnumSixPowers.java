package georgetsak.opcraft.client.gui.overlay;

public enum EnumSixPowers {
    NONE(-1), MOON_WALK(0), IRON_BUDDY(1), FINGER_PISTOL(2), STORM_LEG(3), SHAVE(4), PAPER_DRAWING(5), SIX_KING_GUN(6);

    int value;

    EnumSixPowers(int value) {
        this.value = value;
    }

    public int id(){
        return value;
    }
}
