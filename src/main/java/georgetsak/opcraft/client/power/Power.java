package georgetsak.opcraft.client.power;

import net.minecraft.util.ResourceLocation;

public class Power {
    private int cooldownTime;
    private int consequenceWaitTime;
    private String actionMessage;
    private boolean hasConsequence;
    private String actionName;
    private ResourceLocation resourceLocation;

    private int devilFruitID, key;

    public Power(int cooldownTime, int consequenceWaitTime, String actionMessage, boolean hasConsequence, String actionName, int devilFruitID, int key, ResourceLocation resourceLocation) {
        this.cooldownTime = cooldownTime;
        this.consequenceWaitTime = consequenceWaitTime;
        this.actionMessage = actionMessage;
        this.hasConsequence = hasConsequence;
        this.actionName = actionName;
        this.devilFruitID = devilFruitID;
        this.key = key;
        this.resourceLocation = resourceLocation;
    }

    public int getCooldownTime() {
        return cooldownTime;
    }

    public int getConsequenceWaitTime() {
        return consequenceWaitTime;
    }

    public String getActionMessage() {
        return actionMessage;
    }

    public boolean getHasConsequence() {
        return hasConsequence;
    }

    public String getActionName() {
        return actionName;
    }

    public int getDevilFruitID() {
        return devilFruitID;
    }

    public int getKey() {
        return key;
    }

    public ResourceLocation getResourceLocation(){
        return resourceLocation;
    }

}
