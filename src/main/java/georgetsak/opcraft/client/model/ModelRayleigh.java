package georgetsak.opcraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelRayleigh extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer legUpR;
    public ModelRenderer legLowR;
    public ModelRenderer legUpL;
    public ModelRenderer legLowL;
    public ModelRenderer body2;
    public ModelRenderer neck;
    public ModelRenderer head;
    public ModelRenderer hair1;
    public ModelRenderer hair2;
    public ModelRenderer hair3;
    public ModelRenderer hair5;
    public ModelRenderer hair4;
    public ModelRenderer beard;
    public ModelRenderer shoulderL;
    public ModelRenderer upperArmL;
    public ModelRenderer lowerArmL;
    public ModelRenderer shoulderR;
    public ModelRenderer upperArmR;
    public ModelRenderer lowerArmR;
    public ModelRenderer body3;

    public ModelRayleigh() {
        this.textureWidth = 128;
        this.textureHeight = 128;

        this.body = new ModelRenderer(this, 0, 32);
        this.body.setRotationPoint(-2.0F, -16.0F, 0.0F);
        this.body.addBox(-4.1F, 0.0F, -2.2F, 20, 27, 10);
        this.legUpR = new ModelRenderer(this, 64, 52);
        this.legUpR.setRotationPoint(9.2F, 26.9F, 1.0F);
        this.legUpR.addBox(-2.0F, 0.0F, -2.0F, 8, 9, 8);
        this.legUpR.mirror = true;
        this.setRotationAngles(this.legUpR, -0.0F, 0.0F, -0.08726646259971647F);
        this.body.addChild(this.legUpR);
        this.legLowR = new ModelRenderer(this, 104, 59);
        this.legLowR.setRotationPoint(1.0F, 8.0F, 0.5F);
        this.legLowR.addBox(-2.0F, 0.0F, -2.0F, 6, 10, 6);
        this.legLowR.mirror = true;
        this.setRotationAngles(this.legLowR, -0.0F, 0.0F, 0.08726646259971647F);
        this.legUpR.addChild(this.legLowR);
        this.legUpL = new ModelRenderer(this, 64, 52);
        this.legUpL.setRotationPoint(-1.2F, 26.4F, 1.0F);
        this.legUpL.addBox(-2.0F, 0.0F, -2.0F, 8, 9, 8);
        this.setRotationAngles(this.legUpL, 0.0F, 0.0F, 0.08726646259971647F);
        this.body.addChild(this.legUpL);
        this.legLowL = new ModelRenderer(this, 104, 59);
        this.legLowL.setRotationPoint(1.0F, 8.0F, 0.5F);
        this.legLowL.addBox(-2.0F, 0.0F, -2.0F, 6, 10, 6);
        this.setRotationAngles(this.legLowL, 0.0F, 0.0F, -0.08726646259971647F);
        this.legUpL.addChild(this.legLowL);
        this.body2 = new ModelRenderer(this, 0, 83);
        this.body2.setRotationPoint(-2.0F, 1.0F, -1.0F);
        this.body2.addBox(-4.0F, 0.0F, -2.0F, 24, 11, 12);
        this.body.addChild(this.body2);
        this.neck = new ModelRenderer(this, 44, 0);
        this.neck.setRotationPoint(6.0F, 0.0F, 2.0F);
        this.neck.addBox(-4.0F, -3.0F, -4.0F, 8, 6, 8);
        this.body.addChild(this.neck);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, -3.0F, -1.0F);
        this.head.addBox(-7.0F, -16.0F, -8.0F, 14, 16, 16);
        this.neck.addChild(this.head);
        this.hair1 = new ModelRenderer(this, 0, 109);
        this.hair1.setRotationPoint(-6.3F, -17.0F, -6.0F);
        this.hair1.addBox(0.0F, 0.0F, 0.0F, 13, 7, 15);
        this.head.addChild(this.hair1);
        this.hair2 = new ModelRenderer(this, 90, 75);
        this.hair2.setRotationPoint(1.5F, 1.8F, 16.1F);
        this.hair2.addBox(0.0F, 0.0F, 0.0F, 10, 4, 9);
        this.setRotationAngles(this.hair2, -1.2452923946663432F, 0.0F, 0.0F);
        this.hair1.addChild(this.hair2);
        this.hair3 = new ModelRenderer(this, 82, 88);
        this.hair3.setRotationPoint(-0.5F, 0.0F, 9.0F);
        this.hair3.addBox(0.0F, 0.0F, 0.0F, 11, 5, 12);
        this.setRotationAngles(this.hair3, -0.3679154049888241F, 0.0F, 0.0F);
        this.hair2.addChild(this.hair3);
        this.hair5 = new ModelRenderer(this, 61, 8);
        this.hair5.setRotationPoint(1.0F, 3.0F, 0.3F);
        this.hair5.addBox(-3.0F, 0.0F, 0.0F, 17, 10, 15);
        this.hair1.addChild(this.hair5);
        this.hair4 = new ModelRenderer(this, 0, 0);
        this.hair4.setRotationPoint(3.0F, -16.6F, -9.0F);
        this.hair4.addBox(0.0F, 0.0F, 0.0F, 2, 5, 2);
        this.head.addChild(this.hair4);
        this.beard = new ModelRenderer(this, 80, 0);
        this.beard.setRotationPoint(-2.5F, -1.0F, -8.0F);
        this.beard.addBox(0.0F, 0.0F, 0.0F, 5, 5, 3);
        this.setRotationAngles(this.beard, -0.39618975351851826F, 0.0F, 0.0F);
        this.head.addChild(this.beard);
        this.shoulderL = new ModelRenderer(this, 66, 69);
        this.shoulderL.setRotationPoint(14.5F, -1.0F, -1.0F);
        this.shoulderL.addBox(0.0F, 0.0F, 0.0F, 7, 7, 7);
        this.body.addChild(this.shoulderL);
        this.upperArmL = new ModelRenderer(this, 88, 46);
        this.upperArmL.setRotationPoint(6.2F, 1.0F, 0.5F);
        this.upperArmL.addBox(0.0F, 0.0F, 0.0F, 14, 7, 6);
        this.setRotationAngles(this.upperArmL, 0.0F, 0.0F, 1.3089969389957472F);
        this.shoulderL.addChild(this.upperArmL);
        this.lowerArmL = new ModelRenderer(this, 0, 69);
        this.lowerArmL.setRotationPoint(12.0F, 3.6F, 3.5F);
        this.lowerArmL.addBox(0.0F, -3.0F, -3.0F, 14, 6, 5);
        this.upperArmL.addChild(this.lowerArmL);
        this.shoulderR = new ModelRenderer(this, 66, 69);
        this.shoulderR.setRotationPoint(-2.5F, -1.0F, -1.0F);
        this.shoulderR.addBox(-7.0F, 0.0F, 0.0F, 7, 7, 7);
        this.body.addChild(this.shoulderR);
        this.upperArmR = new ModelRenderer(this, 88, 46);
        this.upperArmR.setRotationPoint(-6.5F, 2.0F, 0.5F);
        this.upperArmR.addBox(-13.0F, 0.0F, 0.0F, 14, 7, 6);
        this.setRotationAngles(this.upperArmR, 0.0F, 0.0F,-1.3089969389957472F);
        this.shoulderR.addChild(this.upperArmR);
        this.lowerArmR = new ModelRenderer(this, 57, 108);
        this.lowerArmR.setRotationPoint(-11.0F,3.5F, 3.5F);
        this.lowerArmR.addBox(-13.0F, -3.0F, -3.0F,14,6,  5);
        this.upperArmR.addChild(this.lowerArmR);
        this.body3 = new ModelRenderer(this, 60, 33);
        this.body3.setRotationPoint(0.0F, 26.0F, -1.5F);
        this.body3.addBox(0.0F, 0.0F, 0.0F, 11, 4, 9);
        this.body.addChild(this.body3);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glTranslatef(0f, 1.20f, 0f);
        this.body.render(scale);
        GL11.glScalef(1f, 1f, 1f);
    }

    public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        float offset = 3.14f/8f;
        float f = MathHelper.sin(this.swingProgress * (float) Math.PI);
        float f2 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float) Math.PI);
        this.neck.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;

        this.shoulderR.rotateAngleZ = 0.0F;
        this.shoulderL.rotateAngleZ = 0.0F;
        this.shoulderR.rotateAngleY = -(0.1F - f * 0.6F);
        this.shoulderL.rotateAngleY = 0.1F - f * 0.6F;
        this.shoulderR.rotateAngleX = 0F;
        this.shoulderL.rotateAngleX = 0F;
        this.shoulderR.rotateAngleX += f * 1.2F - f2 * 0.4F;
        this.shoulderL.rotateAngleX += f * 1.2F - f2 * 0.4F;
        this.shoulderR.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.shoulderL.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.shoulderR.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.shoulderL.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.shoulderR.rotateAngleX *= -1;
        this.shoulderL.rotateAngleX *= -1;
        this.shoulderR.rotateAngleX += (MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount) / 2;
        this.shoulderL.rotateAngleX += (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount) / 2;

        this.legUpL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legUpR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.shoulderR.rotateAngleX -= offset;
        this.shoulderL.rotateAngleX -= offset;
        this.upperArmR.rotateAngleZ = -1.45f;
        this.upperArmL.rotateAngleZ = 1.45f;
        //this.lowerArmR.rotateAngleZ = 0f;
        //System.out.println(this.lowerArmR.rotateAngleX);
    }

}
