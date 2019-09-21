package georgetsak.opcraft.client.render;

import georgetsak.opcraft.client.model.npc.ModelMarine;
import georgetsak.opcraft.common.entity.marine.EntityMarine;
import georgetsak.opcraft.OPCraft;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderMarine extends RenderBiped<EntityMarine> {

	@SideOnly(Side.CLIENT)
	public RenderMarine(RenderManager manager) {
		super(manager, new ModelMarine(), 0.5F);
        this.addLayer(new LayerHeldItem(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMarine entity) {
		return new ResourceLocation(OPCraft.MODID, "textures/entity/marines/marine" + entity.getMarineType() + ".png");
	}
    
}
