package georgetsak.opcraft.client.render;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.model.ModelSeaKing;
import georgetsak.opcraft.common.entity.other.EntitySeaKing;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderSeaKing extends RenderLiving<EntitySeaKing> {

	@SideOnly(Side.CLIENT)
	public RenderSeaKing(RenderManager manager) {
		super(manager, new ModelSeaKing(), 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySeaKing entity) {
		return new ResourceLocation(OPCraft.MODID, "textures/entity/other/seaking.png");
	}
    
}
