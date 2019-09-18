package georgetsak.opcraft.common.block;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

import java.awt.*;

public class FluidWaterCloud extends Fluid {
    public FluidWaterCloud(String name, ResourceLocation stillTexture, ResourceLocation flowingTexture, Color color) {
        super(name, stillTexture, flowingTexture, color);
    }
}
