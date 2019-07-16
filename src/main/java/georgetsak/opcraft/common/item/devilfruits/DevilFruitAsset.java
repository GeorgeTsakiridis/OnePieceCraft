package georgetsak.opcraft.common.item.devilfruits;

import net.minecraft.util.ResourceLocation;

public class DevilFruitAsset {
    private int id;
    private ResourceLocation resourceLocation;
    private String tooltip;
    private String name;

    public DevilFruitAsset(int id, ResourceLocation resourceLocation, String tooltip, String name) {
        this.id = id;
        this.resourceLocation = resourceLocation;
        this.tooltip = tooltip;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    public String getTooltip() {
        return tooltip;
    }

    public String getName() {
        return name;
    }

}
