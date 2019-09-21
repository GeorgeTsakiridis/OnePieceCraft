package georgetsak.opcraft.client.render;

import georgetsak.opcraft.common.entity.other.EntityOPVillager;
import georgetsak.opcraft.client.model.npc.ModelBandit;
import georgetsak.opcraft.OPCraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderOPVillager extends RenderLiving<EntityOPVillager> {

    public RenderOPVillager(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
    }


    ResourceLocation texture1 = new ResourceLocation(OPCraft.MODID, "textures/entity/other/villager1.png");
    //ResourceLocation texture2 = new ResourceLocation(OPCraft.MODID, "textures/entity/other/villager2.png");


    @SideOnly(Side.CLIENT)
    public RenderOPVillager(RenderManager manager) {
        super(manager, new ModelBandit(), 0.0F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityOPVillager entity) {
        return texture1;
    }
}
