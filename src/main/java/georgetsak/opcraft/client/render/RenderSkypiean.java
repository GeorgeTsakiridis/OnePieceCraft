package georgetsak.opcraft.client.render;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.model.npc.ModelSkypieanAntennae;
import georgetsak.opcraft.client.model.npc.ModelSkypieanSoldier;
import georgetsak.opcraft.common.entity.other.EntitySkypiean;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSkypiean extends RenderLiving<EntitySkypiean> {

	private ModelBase modelAntennae;
	private ModelBase modelHat;

	public RenderSkypiean(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelSkypieanAntennae(), 0.5f);
		this.modelAntennae = new ModelSkypieanAntennae();
		this.modelHat = new ModelSkypieanSoldier();
	}

	@Override
	public void doRender(EntitySkypiean entity, double x, double y, double z, float entityYaw, float partialTicks) {
		if(entity.getType() == 2){
			this.mainModel = modelHat;
		}else{
			this.mainModel = modelAntennae;
		}
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySkypiean entity) {
		return new ResourceLocation(OPCraft.MODID, "textures/entity/skypieans/skypiean" + (entity.getType()+1) + ".png");
	}


}
