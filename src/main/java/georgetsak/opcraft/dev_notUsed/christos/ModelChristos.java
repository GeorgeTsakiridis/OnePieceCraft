package georgetsak.opcraft.dev_notUsed.christos;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelChristos extends ModelBase {
    // fields
    ModelRenderer Cube;

    ModelRenderer Body;
    ModelRenderer Head;
    ModelRenderer HandR;
    ModelRenderer HandL;
    ModelRenderer LegR;
    ModelRenderer LegL;

    public ModelChristos()
    {
        textureWidth = 256;
        textureHeight = 256;

        Body = new ModelRenderer(this, 0, 0);
        Body.addBox(-12.5F, 0F, -12.5F, 25, 25, 25);
        Body.setRotationPoint(0F, -8F, 0F);
        Body.setTextureSize(256, 256);
        Body.mirror = true;
        setRotation(Body, 0F, 0F, 0F);
        Head = new ModelRenderer(this, 0, 54);
        Head.addBox(-3F, 0F, -3F, 6, 6, 6);
        Head.setRotationPoint(0F, -14F, 0F);
        Head.setTextureSize(256, 256);
        Head.mirror = true;
        setRotation(Head, 0F, 0F, 0F);
        HandR = new ModelRenderer(this, 16, 69);
        HandR.addBox(-3F, 0F, -1.5F, 3, 13, 3);
        HandR.setRotationPoint(-12.5F, -1F, 0F);
        HandR.setTextureSize(256, 256);
        HandR.mirror = true;
        setRotation(HandR, 0F, 0F, 0F);
        HandL = new ModelRenderer(this, 0, 69);
        HandL.addBox(0F, 0F, -1.5F, 3, 13, 3);
        HandL.setRotationPoint(12.5F, 0F, 0F);
        HandL.setTextureSize(256, 256);
        HandL.mirror = true;
        setRotation(HandL, 0F, 0F, 0F);
        LegR = new ModelRenderer(this, 50, 54);
        LegR.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
        LegR.setRotationPoint(-6F, 17F, 0F);
        LegR.setTextureSize(256, 256);
        LegR.mirror = true;
        setRotation(LegR, 0F, 0F, 0F);
        LegL = new ModelRenderer(this, 31, 54);
        LegL.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
        LegL.setRotationPoint(6F, 17F, 0F);
        LegL.setTextureSize(256, 256);
        LegL.mirror = true;
        setRotation(LegL, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        GL11.glScaled(2,2,2);
        Body.render(f5);
        Head.render(f5);
        HandR.render(f5);
        HandL.render(f5);
        LegR.render(f5);
        LegL.render(f5);
    }


    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
