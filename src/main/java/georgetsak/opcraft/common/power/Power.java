package georgetsak.opcraft.common.power;

import net.minecraft.util.ResourceLocation;

public class Power {
    private int[] cooldownTimes;
    private int[] usesToReduceCooldown;
    private int currentCooldown;
    private boolean canCooldownBeUpgraded;
    private boolean canPowerBeUpgraded;

    private String actionMessage;
    private String actionName;
    private ResourceLocation resourceLocation;

    private int devilFruitID, key;

    public Power(int[] cooldownTimes, int[] usesToReduceCooldown, String actionMessage, String actionName, int devilFruitID, int key, ResourceLocation resourceLocation) {
        this.cooldownTimes = cooldownTimes;
        this.usesToReduceCooldown = usesToReduceCooldown;
        this.actionMessage = actionMessage;
        this.actionName = actionName;
        this.devilFruitID = devilFruitID;
        this.key = key;
        this.resourceLocation = resourceLocation;
        this.canCooldownBeUpgraded = cooldownTimes.length == 5;
        this.canPowerBeUpgraded = true;
        currentCooldown = 0;
    }

    public Power(int[] cooldownTimes, int[] usesToReduceCooldown, boolean canPowerBeUpgraded, String actionMessage, String actionName, int devilFruitID, int key, ResourceLocation resourceLocation){
        this(cooldownTimes, usesToReduceCooldown, actionMessage, actionName, devilFruitID, key, resourceLocation);
        this.canPowerBeUpgraded = canPowerBeUpgraded;
    }

    public int getCurrentCooldown(){return currentCooldown;}

    public void setCurrentCooldown(int cooldown){
        if(cooldown >= 0 && cooldown > currentCooldown) {
            currentCooldown = cooldown;
        }
    }

    public void decreaseCooldown(){
        if(currentCooldown > 0)currentCooldown--;
    }

    public int getCooldownTime(int level) {
        if(level-1 < 0 || level-1 >= cooldownTimes.length)return 0;

        if(!canCooldownBeUpgraded)return cooldownTimes[0];

        return cooldownTimes[level-1];
    }

    public int[] getUsesToReduceCooldown() {
        if(!canCooldownBeUpgraded)return new int[]{-1};
        if(usesToReduceCooldown == null)return new int[]{10,20,30,40};
        return usesToReduceCooldown;
    }

    public boolean canCooldownBeUpgraded(){
        return canCooldownBeUpgraded;
    }

    public boolean canPowerBeUpgraded(){
        return canPowerBeUpgraded;
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
