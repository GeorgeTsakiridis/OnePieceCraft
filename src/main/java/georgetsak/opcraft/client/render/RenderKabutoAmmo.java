package georgetsak.opcraft.client.render;

import georgetsak.opcraft.OPCraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderTippedArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderKabutoAmmo extends RenderTippedArrow {
	private static final ResourceLocation ENTITY_TEXTURE = new ResourceLocation(OPCraft.MODID, "textures/entity/kabutoammo.png");

	public RenderKabutoAmmo(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityTippedArrow entity) {
		return ENTITY_TEXTURE;
	}
	
}