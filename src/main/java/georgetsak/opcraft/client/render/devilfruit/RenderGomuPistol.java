package georgetsak.opcraft.client.render.devilfruit;

import georgetsak.opcraft.client.model.devilfruit.ModelPunch;
import georgetsak.opcraft.common.entity.devilfruit.EntityGomuPistol;
import georgetsak.opcraft.OPCraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

public class RenderGomuPistol extends RenderLiving<EntityGomuPistol>{

	ResourceLocation texture = new ResourceLocation(OPCraft.MODID, "textures/entity/power/gomugomuno/punch.png");
	
	@SideOnly(Side.CLIENT)
	public RenderGomuPistol(RenderManager manager) {
		super(manager, new ModelPunch(), 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityGomuPistol entity) {
		return texture;
	}

    @Override
    protected void preRenderCallback(EntityGomuPistol entity, float partialTickTime) {
	    if (entity.isGear3()) {
            GL11.glScalef(5f, 5f, 5f);
            GL11.glTranslatef(0.127f, 1.23f, 0.1f);
	    }else {
	        GL11.glTranslatef(0f, 0.2f, 0f);
	        GL11.glTranslatef(0f, 0.5f, 0f);
        }
        super.preRenderCallback(entity, partialTickTime);
    }

}
