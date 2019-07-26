package georgetsak.opcraft.client.power;

import net.minecraft.util.ResourceLocation;

public class Power {
    private int cooldownTime;
    private int currentCooldown;
    private String actionMessage;
    private String actionName;
    private ResourceLocation resourceLocation;

    private int devilFruitID, key;

    public Power(int cooldownTime, String actionMessage, String actionName, int devilFruitID, int key, ResourceLocation resourceLocation) {
        this.cooldownTime = cooldownTime;
        this.actionMessage = actionMessage;
        this.actionName = actionName;
        this.devilFruitID = devilFruitID;
        this.key = key;
        this.resourceLocation = resourceLocation;
        currentCooldown = 0;
    }

    public int getCurrentCooldown(){return currentCooldown;}

    public void setCurrentCooldown(int cooldown){
        if(cooldown >= 0 && cooldown > cooldownTime) {
            currentCooldown = cooldown;
        }
    }

    public void decreaseCooldown(){
        if(currentCooldown > 0)currentCooldown--;
    }

    public int getCooldownTime() {
        return cooldownTime;
    }

    public String getActionMessage() {
        return actionMessage;
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
