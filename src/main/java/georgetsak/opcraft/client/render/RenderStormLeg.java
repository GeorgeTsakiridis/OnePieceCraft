package georgetsak.opcraft.client.render;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.model.ModelStormLeg;
import georgetsak.opcraft.common.entity.other.EntityStormLeg;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderStormLeg extends RenderLiving<EntityStormLeg>{

    ResourceLocation texture = new ResourceLocation(OPCraft.MODID, "textures/entity/other/stormleg.png");

    @SideOnly(Side.CLIENT)
    public RenderStormLeg(RenderManager manager) {
        super(manager, new ModelStormLeg(), 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityStormLeg entity) {
        return texture;
    }

}

