package georgetsak.opcraft.client.render;

import georgetsak.opcraft.client.model.ModelBandit;
import georgetsak.opcraft.client.model.ModelSeaKing;
import georgetsak.opcraft.common.entity.other.EntityBandit;
import georgetsak.opcraft.OPCraft;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

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

