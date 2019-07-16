package georgetsak.opcraft.client.model.devilfruit;

import georgetsak.opcraft.common.entity.devilfruit.EntityUrsusBubble;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelUrsusBubble extends ModelBase
{
    ModelRenderer Shape1;
    ModelRenderer Finger1;
    ModelRenderer Finger2;
    ModelRenderer Finger3;
    ModelRenderer Finger4;

    public ModelUrsusBubble()
    {
        textureWidth = 256;
        textureHeight = 128;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(-20F, -20F, -20F, 40, 40, 40);
        Shape1.setRotationPoint(0F, 4F, 0F);
        Shape1.setTextureSize(256, 128);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Finger1 = new ModelRenderer(this, 0, 82);
        Finger1.addBox(-5F, -10F, -5F, 10, 10, 10);
        Finger1.setRotationPoint(-20.6F, -19.33333F, 0F);
        Finger1.setTextureSize(256, 128);
        Finger1.mirror = true;
        setRotation(Finger1, 0F, 0F, -0.3717861F);
        Finger2 = new ModelRenderer(this, 0, 104);
        Finger2.addBox(-5F, -10F, -5F, 10, 10, 10);
        Finger2.setRotationPoint(-7F, -21.3F, 0F);
        Finger2.setTextureSize(256, 128);
        Finger2.mirror = true;
        setRotation(Finger2, 0F, 0F, -0.0371786F);
        Finger3 = new ModelRenderer(this, 42, 82);
        Finger3.addBox(-5F, -10F, -5F, 10, 10, 10);
        Finger3.setRotationPoint(7F, -21.3F, 0F);
        Finger3.setTextureSize(256, 128);
        Finger3.mirror = true;
        setRotation(Finger3, 0F, 0F, 0.0371755F);
        Finger4 = new ModelRenderer(this, 42, 104);
        Finger4.addBox(-5F, -10F, -5F, 10, 10, 10);
        Finger4.setRotationPoint(20.7F, -19.3F, 0F);
        Finger4.setTextureSize(256, 128);
        Finger4.mirror = true;
        setRotation(Finger4, 0F, 0F, 0.37179F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);

        EntityUrsusBubble e = (EntityUrsusBubble) entity;
        int ticks = e.ticksExisted;
        double scale = -(double)ticks / 20D + 5.4D;
        float color = -(float)ticks / 100F + 1F;

        GlStateManager.color(1.0F, color, color, 1.0F);
        GlStateManager.enableNormalize();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.scale(scale, scale, scale);

        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Shape1.render(f5);
        Finger1.render(f5);
        Finger2.render(f5);
        Finger3.render(f5);
        Finger4.render(f5);
        GlStateManager.disableBlend();
        GlStateManager.disableNormalize();

    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
