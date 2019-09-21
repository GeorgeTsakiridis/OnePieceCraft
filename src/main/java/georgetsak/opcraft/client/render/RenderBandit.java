package georgetsak.opcraft.client.render;

import georgetsak.opcraft.client.model.npc.ModelBandit;
import georgetsak.opcraft.common.entity.other.EntityBandit;
import georgetsak.opcraft.OPCraft;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderBandit extends RenderBiped<EntityBandit> {

    @SideOnly(Side.CLIENT)
    public RenderBandit(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelBandit(), 0.5F);
    }


    ResourceLocation texture = new ResourceLocation(OPCraft.MODID, "textures/entity/other/bandit.png");

    @Override
    protected ResourceLocation getEntityTexture(EntityBandit entity) {
        return texture;
    }

}

