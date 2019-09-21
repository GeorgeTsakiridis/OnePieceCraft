package georgetsak.opcraft.client.model.npc;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelTonta extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer armUpR;
    public ModelRenderer armLowR;
    public ModelRenderer armUpL;
    public ModelRenderer armLowL;
    public ModelRenderer legUpR;
    public ModelRenderer legLowR;
    public ModelRenderer feetR;
    public ModelRenderer legUpL;
    public ModelRenderer legLowL;
    public ModelRenderer feetL;
    public ModelRenderer body2;
    public ModelRenderer body3;
    public ModelRenderer belly;
    public ModelRenderer tail;
    public ModelRenderer tail2;
    public ModelRenderer tail3;
    public ModelRenderer tail4;
    public ModelRenderer tail5;
    public ModelRenderer tail6;
    public ModelRenderer tail7;
    public ModelRenderer neck;
    public ModelRenderer head;
    public ModelRenderer nose;
    public ModelRenderer nose2;
    public ModelRenderer nose3;

    public ModelTonta() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.body = new ModelRenderer(this, 0, 46);
        this.body.setRotationPoint(-1.0F, 3.0F, 0.0F);
        this.body.addBox(-4.0F, 0.0F, -3.2F, 8, 12, 6);
        this.armUpR = new ModelRenderer(this, 56, 0);
        this.armUpR.setRotationPoint(2.9F, 2.4F, 1.0F);
        this.armUpR.addBox(0.0F, 0.0F, -2.0F, 2, 7, 2);
        this.armUpR.mirror = true;
        this.setRotationAngles(this.armUpR, -0.17453292519943295F, 0.0F, -0.7108725963478071F);
        this.body.addChild(this.armUpR);
        this.armLowR = new ModelRenderer(this, 47, 0);
        this.armLowR.setRotationPoint(0.0F, 5.5F, -1.6F);
        this.armLowR.addBox(0.0F, 0.0F, 0.0F, 2, 5, 2);
        this.setRotationAngles(this.armLowR, -0.7925540106674639F, 0.0F, 0.0F);
        this.armUpR.addChild(this.armLowR);
        this.armUpL = new ModelRenderer(this, 56, 0);
        this.armUpL.setRotationPoint(-2.7F, 2.4F, 1.0F);
        this.armUpL.addBox(-2.0F, 0.0F, -2.0F, 2, 7, 2);
        this.setRotationAngles(this.armUpL, -0.17453292519943295F, 0.0F, 0.7075564947374677F);
        this.body.addChild(this.armUpL);
        this.armLowL = new ModelRenderer(this, 47, 0);
        this.armLowL.setRotationPoint(0.0F, 5.5F, -1.6F);
        this.armLowL.addBox(-2.0F, 0.0F, 0.0F, 2, 5, 2);
        this.setRotationAngles(this.armLowL, -0.7925540106674639F, 0.0F, 0.0F);
        this.armUpL.addChild(this.armLowL);
        this.legUpR = new ModelRenderer(this, 52, 10);
        this.legUpR.setRotationPoint(3.4F, 11.5F, 0.5F);
        this.legUpR.addBox(-2.0F, 0.0F, -2.0F, 3, 6, 3);
        this.legUpR.mirror = true;
        this.setRotationAngles(this.legUpR, -0.0F, 0.0F, -0.08726646259971647F);
        this.body.addChild(this.legUpR);
        this.legLowR = new ModelRenderer(this, 47, 7);
        this.legLowR.setRotationPoint(0.2F, 5.7F, 0.1F);
        this.legLowR.addBox(-2.0F, 0.0F, -2.0F, 2, 3, 2);
        this.legLowR.mirror = true;
        this.setRotationAngles(this.legLowR, -0.0F, 0.0F, 0.08726646259971647F);
        this.legUpR.addChild(this.legLowR);
        this.feetR = new ModelRenderer(this, 52, 19);
        this.feetR.setRotationPoint(0.0F, 1.6F, -1.9F);
        this.feetR.addBox(-2.0F, 0.0F, -2.0F, 2, 2, 4);
        this.legLowR.addChild(this.feetR);
        this.legUpL = new ModelRenderer(this, 52, 10);
        this.legUpL.setRotationPoint(-2.0F, 11.5F, 0.5F);
        this.legUpL.addBox(-2.0F, 0.0F, -2.0F, 3, 6, 3);
        this.setRotationAngles(this.legUpL, 0.0F, 0.0F, 0.08726646259971647F);
        this.body.addChild(this.legUpL);
        this.legLowL = new ModelRenderer(this, 47, 7);
        this.legLowL.setRotationPoint(0.5F, 5.7F, 0.1F);
        this.legLowL.addBox(-2.0F, 0.0F, -2.0F, 2, 3, 2);
        this.setRotationAngles(this.legLowL, 0.0F, 0.0F, -0.08726646259971647F);
        this.legUpL.addChild(this.legLowL);
        this.feetL = new ModelRenderer(this, 52, 19);
        this.feetL.setRotationPoint(0.0F, 1.6F, -1.9F);
        this.feetL.addBox(-2.0F, 0.0F, -2.0F, 2, 2, 4);
        this.legLowL.addChild(this.feetL);
        this.body2 = new ModelRenderer(this, 0, 13);
        this.body2.setRotationPoint(-0.5F, 0.5F, -1.0F);
        this.body2.addBox(-4.0F, 0.0F, -2.0F, 9, 7, 6);
        this.body.addChild(this.body2);
        this.body3 = new ModelRenderer(this, 32, 25);
        this.body3.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.body3.addBox(-4.0F, 0.0F, -1.9F, 6, 4, 4);
        this.body.addChild(this.body3);
        this.belly = new ModelRenderer(this, 28, 51);
        this.belly.setRotationPoint(-1.5F, 6.6F, -1.3F);
        this.belly.addBox(-4.0F, 0.0F, -2.2F, 11, 6, 7);
        this.body.addChild(this.belly);
        this.tail = new ModelRenderer(this, 32, 15);
        this.tail.setRotationPoint(0.0F, 0.0F, 4.0F);
        this.tail.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4);
        this.belly.addChild(this.tail);
        this.tail2 = new ModelRenderer(this, 0, 0);
        this.tail2.setRotationPoint(-1.0F, -1.0F, 1.5F);
        this.tail2.addBox(0.0F, 0.0F, 0.0F, 6, 6, 6);
        this.setRotationAngles(this.tail2, 0.33964105645913F, 0.0F, 0.0F);
        this.tail.addChild(this.tail2);
        this.tail3 = new ModelRenderer(this, 0, 27);
        this.tail3.setRotationPoint(-1.0F, -1.0F, 1.5F);
        this.tail3.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8);
        this.setRotationAngles(this.tail3, -0.05654866793106384F, 0.0F, 0.0F);
        this.tail2.addChild(this.tail3);
        this.tail4 = new ModelRenderer(this, 0, 0);
        this.tail4.setRotationPoint(1.0F, 1.0F, 4.0F);
        this.tail4.addBox(0.0F, 0.0F, 0.0F, 6, 6, 6);
        this.setRotationAngles(this.tail4, 0.08482300397719036F, 0.0F, 0.0F);
        this.tail3.addChild(this.tail4);
        this.tail5 = new ModelRenderer(this, 32, 15);
        this.tail5.setRotationPoint(1.0F, 1.0F, 3.5F);
        this.tail5.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4);
        this.setRotationAngles(this.tail5, 0.14154619634462767F, 0.0F, 0.0F);
        this.tail4.addChild(this.tail5);
        this.tail6 = new ModelRenderer(this, 35, 0);
        this.tail6.setRotationPoint(0.5F, 0.5F, 2.5F);
        this.tail6.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3);
        this.setRotationAngles(this.tail6, 0.14154619634462767F, 0.0F, 0.0F);
        this.tail5.addChild(this.tail6);
        this.tail7 = new ModelRenderer(this, 31, 9);
        this.tail7.setRotationPoint(0.5F, 0.5F, 2.5F);
        this.tail7.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
        this.setRotationAngles(this.tail7, 0.14154619634462767F, 0.0F, 0.0F);
        this.tail6.addChild(this.tail7);
        this.neck = new ModelRenderer(this, 56, 26);
        this.neck.setRotationPoint(-1.0F, -1.0F, -1.0F);
        this.neck.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
        this.body.addChild(this.neck);
        this.head = new ModelRenderer(this, 30, 35);
        this.head.setRotationPoint(0.5F, 0.0F, 1.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 9, 8, 8);
        this.neck.addChild(this.head);
        this.nose = new ModelRenderer(this, 40, 10);
        this.nose.setRotationPoint(0.0F, -4.0F, -7.0F);
        this.nose.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
        this.head.addChild(this.nose);
        this.nose2 = new ModelRenderer(this, 36, 7);
        this.nose2.setRotationPoint(-0.5F, 0.0F, 2.0F);
        this.nose2.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
        this.nose.addChild(this.nose2);
        this.nose3 = new ModelRenderer(this, 43, 7);
        this.nose3.setRotationPoint(0.0F, -0.8F, 2.7F);
        this.nose3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.nose3, -0.6227334824378989F, 0.0F, 0.0F);
        this.nose.addChild(this.nose3);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
        GL11.glScalef(0.4f, 0.4f, 0.4f);
        GL11.glTranslatef(0f, +2f, 0f);
        this.body.render(scale);
        GL11.glScalef(1f, 1f, 1f);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        float offset = 3.14f/8f;
        float f = MathHelper.sin(this.swingProgress * (float) Math.PI);
        float f2 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float) Math.PI);
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;

        this.armUpR.rotateAngleZ = 0.0F;
        this.armUpL.rotateAngleZ = 0.0F;
        this.armUpR.rotateAngleY = -(0.1F - f * 0.6F);
        this.armUpL.rotateAngleY = 0.1F - f * 0.6F;
        this.armUpR.rotateAngleX = 0F;
        this.armUpL.rotateAngleX = 0F;
        this.armUpR.rotateAngleX += f * 1.2F - f2 * 0.4F;
        this.armUpL.rotateAngleX += f * 1.2F - f2 * 0.4F;
        this.armUpR.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.armUpL.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.armUpR.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.armUpL.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.armUpR.rotateAngleX *= -1;
        this.armUpL.rotateAngleX *= -1;
        this.armUpR.rotateAngleX += (MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount) / 2;
        this.armUpL.rotateAngleX += (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount) / 2;

        this.legUpL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legUpR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.armUpR.rotateAngleX -= offset;
        this.armUpL.rotateAngleX -= offset;
    }

    public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
