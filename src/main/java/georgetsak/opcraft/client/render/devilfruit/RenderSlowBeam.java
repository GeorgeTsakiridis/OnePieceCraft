package georgetsak.opcraft.client.render.devilfruit;

import georgetsak.opcraft.client.model.devilfruit.ModelSlowBeam;
import georgetsak.opcraft.common.entity.devilfruit.EntitySlowBeam;
import georgetsak.opcraft.OPCraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderSlowBeam extends RenderLiving<EntitySlowBeam>{

	ResourceLocation texture = new ResourceLocation(OPCraft.MODID, "textures/entity/power/noronoro/beam.png");
	
	@SideOnly(Side.CLIENT)
	public RenderSlowBeam(RenderManager manager) {
		super(manager, new ModelSlowBeam(), 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySlowBeam entity) {
		return texture;
	}

}

