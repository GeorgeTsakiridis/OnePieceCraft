package georgetsak.opcraft.client.render;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.model.ModelPeacekeeper;
import georgetsak.opcraft.common.entity.other.EntityPeacekeeper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPeacekeeper extends RenderLiving<EntityPeacekeeper> {


	public RenderPeacekeeper(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelPeacekeeper(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPeacekeeper entity) {
		return new ResourceLocation(OPCraft.MODID, "textures/entity/other/peacekeeper" + (entity.getType()+1) + ".png");
	}


}
