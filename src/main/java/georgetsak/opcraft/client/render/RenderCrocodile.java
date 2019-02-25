package georgetsak.opcraft.client.render;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.model.ModelCrocodile;
import georgetsak.opcraft.common.entity.other.EntityCrocodile;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderCrocodile extends RenderLiving<EntityCrocodile>{

    ResourceLocation texture = new ResourceLocation(OPCraft.MODID, "textures/entity/boss/crocodile.png");

    @SideOnly(Side.CLIENT)
    public RenderCrocodile(RenderManager manager) {
        super(manager, new ModelCrocodile(), 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCrocodile entity) {
        return texture;
    }

}

