package georgetsak.opcraft.client.render;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.model.pirate.ModelPirateFat;
import georgetsak.opcraft.client.model.pirate.ModelPirateStrong;
import georgetsak.opcraft.client.model.pirate.ModelPirateThin;
import georgetsak.opcraft.common.entity.other.EntityPirate;
import georgetsak.opcraft.common.registry.OPItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderIronGolem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPirate extends RenderLiving<EntityPirate> {

    ModelPirateStrong modelPirateStrong;
    ModelPirateThin modelPirateThin;
    ModelPirateFat modelPirateFat;

    @SideOnly(Side.CLIENT)
    public RenderPirate(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelPirateStrong(), 0f);
        modelPirateStrong = new ModelPirateStrong();
        modelPirateThin = new ModelPirateThin();
        modelPirateFat = new ModelPirateFat();
        this.addLayer(new LayerSword(this));
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityPirate entity) {
        return new ResourceLocation(OPCraft.MODID, "textures/entity/pirates/pirate" + entity.getType() + "_" + entity.getTexture() + ".png");
    }

    @Override
    public void doRender(EntityPirate entity, double x, double y, double z, float entityYaw, float partialTicks) {
        changeModel(entity);
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    private void changeModel(EntityPirate pirate){
        switch (pirate.getType()){
            case EntityPirate.STRONG: this.mainModel = modelPirateStrong; break;
            case EntityPirate.THIN: this.mainModel = modelPirateThin; break;
            case EntityPirate.FAT: this.mainModel = modelPirateFat; break;
        }
    }

}

@SideOnly(Side.CLIENT)
 class LayerSword implements LayerRenderer<EntityPirate>
{
    private final RenderPirate pirateRenderer;

    public LayerSword(RenderPirate pirateRenderer)
    {
        this.pirateRenderer = pirateRenderer;
    }

    public void doRenderLayer(EntityPirate entityPirate, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        boolean flag = entityPirate.getPrimaryHand() == EnumHandSide.RIGHT;
        ItemStack itemstack = flag ? entityPirate.getHeldItemOffhand() : entityPirate.getHeldItemMainhand();
        //System.out.println(itemstack);
        if (itemstack != null)
        {
            ItemRenderer render = Minecraft.getMinecraft().getItemRenderer();
            GlStateManager.enableRescaleNormal();
            GlStateManager.pushMatrix();
            if(entityPirate.getType() == EntityPirate.STRONG) {
                GlStateManager.rotate(5.0F + 180.0F * ((ModelPirateStrong) this.pirateRenderer.getMainModel()).armLowR.rotateAngleX / (float) Math.PI, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(((ModelPirateStrong)this.pirateRenderer.getMainModel()).armUpR.rotateAngleZ*50*-1, 0.0F, 0.2F, 1.0F);
                GlStateManager.rotate(((ModelPirateStrong)this.pirateRenderer.getMainModel()).armUpR.rotateAngleX*50f, -1.0F, 0F, 0.0F);
                GlStateManager.translate(-0.14f, 0f, 0f);
            }
            if(entityPirate.getType() == EntityPirate.THIN) {
                GlStateManager.rotate(5.0F + 180.0F * ((ModelPirateThin) this.pirateRenderer.getMainModel()).armLowR.rotateAngleX / (float) Math.PI, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(((ModelPirateThin)this.pirateRenderer.getMainModel()).armUpR.rotateAngleZ*50*-1, 0.0F, 0.2F, -1.0F);
                GlStateManager.rotate(((ModelPirateThin)this.pirateRenderer.getMainModel()).armUpR.rotateAngleX*50f, 1.0F, 0F, 0.0F);
            }
            if(entityPirate.getType() == EntityPirate.FAT) {
                GlStateManager.rotate(5.0F + 180.0F * ((ModelPirateFat) this.pirateRenderer.getMainModel()).armLowR.rotateAngleX / (float) Math.PI, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(((ModelPirateFat)this.pirateRenderer.getMainModel()).armUpR.rotateAngleZ*50*-1, 0.0F, 0.2F, 1.0F);
                GlStateManager.rotate(((ModelPirateFat)this.pirateRenderer.getMainModel()).armUpR.rotateAngleX*50f, -1.0F, 0F, 0.0F);
                GlStateManager.translate(-0.14f, 0f, 0f);
            }
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.translate(-0.46F, 0.1F, -0.55F);
            float f = 0.5F;
            GlStateManager.scale(1F, -1F, 1F);
            int i = entityPirate.getBrightnessForRender();
            int j = i % 65536;
            int k = i / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.pirateRenderer.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            render.renderItem(entityPirate, new ItemStack(OPItems.ItemCutlass), ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND);

            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
        }
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }
}