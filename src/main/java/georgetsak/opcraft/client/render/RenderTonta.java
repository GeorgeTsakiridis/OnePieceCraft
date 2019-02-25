package georgetsak.opcraft.client.render;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.model.ModelTonta;
import georgetsak.opcraft.common.entity.other.EntityTonta;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderTonta extends RenderLiving<EntityTonta>{

    ResourceLocation texture = new ResourceLocation(OPCraft.MODID, "textures/entity/other/tonta.png");

    @SideOnly(Side.CLIENT)
    public RenderTonta(RenderManager manager) {
        super(manager, new ModelTonta(), 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityTonta entity) {
        return texture;
    }

}

