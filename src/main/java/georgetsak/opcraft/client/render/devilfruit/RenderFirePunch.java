package georgetsak.opcraft.client.render.devilfruit;

import georgetsak.opcraft.client.model.devilfruit.ModelFireFist;
import georgetsak.opcraft.common.entity.devilfruit.EntityFirePunch;
import georgetsak.opcraft.OPCraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderFirePunch extends RenderLiving<EntityFirePunch>{

	public RenderFirePunch(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
		super(rendermanagerIn, modelbaseIn, shadowsizeIn);
	}

	final ResourceLocation TEXTURE = new ResourceLocation(OPCraft.MODID, "textures/entity/power/meramera/firefist.png");

	@SideOnly(Side.CLIENT)
	public RenderFirePunch(RenderManager manager) {
		super(manager, new ModelFireFist(),0.0F);
	}

	protected ResourceLocation getEntityTexture(EntityFirePunch entity) {
		return TEXTURE;
	}

}
