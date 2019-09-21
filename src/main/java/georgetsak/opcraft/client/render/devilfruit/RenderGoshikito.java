package georgetsak.opcraft.client.render.devilfruit;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.model.devilfruit.ModelGoshikito;
import georgetsak.opcraft.common.entity.devilfruit.EntityGoshikito;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderGoshikito extends RenderLiving<EntityGoshikito>{

	ResourceLocation texture = new ResourceLocation(OPCraft.MODID, "textures/entity/power/ito/goshikito.png");
	
	@SideOnly(Side.CLIENT)
	public RenderGoshikito(RenderManager manager) {
		super(manager, new ModelGoshikito(), 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityGoshikito entity) {
		return texture;
	}

}

