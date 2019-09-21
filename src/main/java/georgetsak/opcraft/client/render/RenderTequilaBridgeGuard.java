package georgetsak.opcraft.client.render;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.model.npc.ModelTequilaBridgeGuardAiming;
import georgetsak.opcraft.client.model.npc.ModelTequilaBridgeGuardRelaxed;
import georgetsak.opcraft.common.entity.marine.EntityTequilaBridgeGuard;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderTequilaBridgeGuard extends RenderLiving<EntityTequilaBridgeGuard>{

    private final ResourceLocation texture = new ResourceLocation(OPCraft.MODID, "textures/entity/marines/tequila_bridge_guard.png");

    private ModelBase relaxed;
    private ModelBase attacking;

    @SideOnly(Side.CLIENT)
    public RenderTequilaBridgeGuard(RenderManager manager) {
        super(manager, new ModelTequilaBridgeGuardRelaxed(), 0.0F);

        this.relaxed = new ModelTequilaBridgeGuardRelaxed();
        this.attacking = new ModelTequilaBridgeGuardAiming();
    }

    @Override
    public void doRender(EntityTequilaBridgeGuard entity, double x, double y, double z, float entityYaw, float partialTicks) {

        this.mainModel = entity.isAttacking() ? attacking : relaxed;

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityTequilaBridgeGuard entity)
    {
        return texture;
    }

}

