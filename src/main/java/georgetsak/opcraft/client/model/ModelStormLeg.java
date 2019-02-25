package georgetsak.opcraft.client.model;

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelStormLeg extends ModelBase {
    // fields
    private ModelRenderer Cube;

    public ModelStormLeg() {
        textureWidth = 32;
        textureHeight = 32;

        Cube = new ModelRenderer(this, 0, 0);
        Cube.addBox(-12F, 0F, +12F, 24, 12, 24);
        Cube.setRotationPoint(0F, 0F, 0F);
        Cube.setTextureSize(32, 32);
        Cube.mirror = true;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        GlStateManager.enableNormalize();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Cube.render(f5);

        GlStateManager.disableBlend();
        GlStateManager.disableNormalize();

    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
