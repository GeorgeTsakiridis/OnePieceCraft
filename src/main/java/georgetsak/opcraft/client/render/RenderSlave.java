package georgetsak.opcraft.client.render;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.model.npc.ModelSlave;
import georgetsak.opcraft.client.model.npc.ModelTequilaBridgeGuardAiming;
import georgetsak.opcraft.client.model.npc.ModelTequilaBridgeGuardRelaxed;
import georgetsak.opcraft.common.entity.marine.EntityTequilaBridgeGuard;
import georgetsak.opcraft.common.entity.other.EntitySlave;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderSlave extends RenderLiving<EntitySlave>{

    private final ResourceLocation black_white = new ResourceLocation(OPCraft.MODID, "textures/entity/other/slave_black_white.png");
    private final ResourceLocation blue = new ResourceLocation(OPCraft.MODID, "textures/entity/other/slave_blue.png");

    ModelBase withChains;
    ModelBase withoutChains;

    @SideOnly(Side.CLIENT)
    public RenderSlave(RenderManager manager) {
        super(manager, new ModelSlave(true), 0.0F);
        this.withChains = new ModelSlave(true);
        this.withoutChains = new ModelSlave(false);
    }

    @Override
    public void doRender(EntitySlave entity, double x, double y, double z, float entityYaw, float partialTicks) {

        this.mainModel = (entity.getType() == 0 || entity.getType() == 2) ? withChains : withoutChains;

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySlave entity)
    {
        if(entity.getType() < 2)return black_white;

        return blue;

    }

}

