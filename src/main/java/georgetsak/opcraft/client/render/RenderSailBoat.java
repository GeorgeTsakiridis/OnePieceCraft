package georgetsak.opcraft.client.render;

import georgetsak.opcraft.client.model.boat.ModelSailBoat;
import georgetsak.opcraft.common.entity.boat.EntitySailBoat;
import georgetsak.opcraft.OPCraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSailBoat extends Render<EntitySailBoat>
{
    private static final ResourceLocation SAIL_BOAT = new ResourceLocation(OPCraft.MODID, "textures/entity/other/sailboat.png");
    protected ModelBase modelBoat = new ModelSailBoat();

    public RenderSailBoat(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.shadowSize = 0.5F;
    }

    public void doRender(EntitySailBoat entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        this.setupTranslation(x, y, z);
        this.setupRotation(entity, entityYaw, partialTicks);
        this.bindEntityTexture(entity);

        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        this.modelBoat.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        if (this.renderOutlines) {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public void setupRotation(EntitySailBoat entitySailBoat, float yaw, float partialTicks)
    {
        GlStateManager.rotate(180F, 0F, 1, 0F);
        GlStateManager.rotate(180.0F - yaw, 0.0F, 1.0F, 0.0F);
        float f = (float)entitySailBoat.getTimeSinceHit() - partialTicks;
        float f1 = entitySailBoat.getDamageTaken() - partialTicks;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        if (f > 0.0F)
        {
            GlStateManager.rotate(MathHelper.sin(f) * f * f1 / 10.0F * (float)entitySailBoat.getForwardDirection(), 1.0F, 0.0F, 0.0F);
        }
//-1 -1 1
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
    }

    public void setupTranslation(double p_188309_1_, double p_188309_3_, double p_188309_5_)
    {
        GlStateManager.translate((float)p_188309_1_, (float)p_188309_3_ + .375F + 0.3F, (float)p_188309_5_);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySailBoat entity)
    {
        return SAIL_BOAT;
    }
}