package georgetsak.opcraft.client.render.devilfruit;

import georgetsak.opcraft.client.model.devilfruit.ModelEntei;
import georgetsak.opcraft.common.entity.devilfruit.EntityEntei;
import georgetsak.opcraft.OPCraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderEntei extends RenderLiving<EntityEntei>{

	public RenderEntei(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
		super(rendermanagerIn, modelbaseIn, shadowsizeIn);
	}

	ResourceLocation texture = new ResourceLocation(OPCraft.MODID, "textures/entity/power/meramera/firefist.png");
	
	@SideOnly(Side.CLIENT)
	public RenderEntei(RenderManager manager) {
		super(manager, new ModelEntei(), 0.0F);
	}

	 protected void preRenderCallback(EntityEntei entitylivingbaseIn, float partialTickTime)
	    {
	        GlStateManager.scale(1.5625, 1.5625, 1.5625);
	        GlStateManager.translate(-2.5, -5.3, -2.53);
	    }
	
	@Override
	protected ResourceLocation getEntityTexture(EntityEntei entity) {
		return texture;
	}

}
