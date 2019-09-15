package georgetsak.opcraft.client.render;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.model.ModelHomieTree;
import georgetsak.opcraft.common.entity.other.EntityHomieTree;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHomieTree extends RenderLiving<EntityHomieTree> {

    public RenderHomieTree(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelHomieTree(), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityHomieTree entity) {
        if(entity.getAttackTarget() != null){
            return new ResourceLocation(OPCraft.MODID, "textures/entity/homies/homie_tree_angry.png");
        }
        return new ResourceLocation(OPCraft.MODID, "textures/entity/homies/homie_tree.png");
    }
}
