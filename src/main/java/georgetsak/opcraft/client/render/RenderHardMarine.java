package georgetsak.opcraft.client.render;

import georgetsak.opcraft.common.entity.marine.EntityHardMarine;
import georgetsak.opcraft.client.model.ModelBandit;
import georgetsak.opcraft.OPCraft;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderHardMarine extends RenderBiped<EntityHardMarine> {

	int type;
	
	@SideOnly(Side.CLIENT)
	public RenderHardMarine(RenderManager manager) {
		super(manager, new ModelBandit(), 0.5F);
        this.addLayer(new LayerHeldItem(this));
	}

	public void doRender(EntityHardMarine entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }


	@Override
	protected ResourceLocation getEntityTexture(EntityHardMarine entity) {
		type = entity.getMarineType();
		return new ResourceLocation(OPCraft.MODID, "textures/entity/hardmarines/hardmarine" + String.valueOf(type) + ".png");
	}
    
}
