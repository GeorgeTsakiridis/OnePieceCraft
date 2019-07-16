package georgetsak.opcraft.client.render;

import georgetsak.opcraft.client.model.ModelMorgan;
import georgetsak.opcraft.common.entity.marine.EntityMorgan;
import georgetsak.opcraft.OPCraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderMorgan extends RenderLiving<EntityMorgan>{

    ResourceLocation texture = new ResourceLocation(OPCraft.MODID, "textures/entity/boss/morgan.png");

    @SideOnly(Side.CLIENT)
    public RenderMorgan(RenderManager manager) {
        super(manager, new ModelMorgan(), 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityMorgan entity) {
        return texture;
    }

}

