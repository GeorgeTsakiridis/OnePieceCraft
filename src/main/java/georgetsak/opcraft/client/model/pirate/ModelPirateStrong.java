package georgetsak.opcraft.client.model.pirate;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Bandit by Unknown
 */
@SideOnly(Side.CLIENT)
public class ModelPirateStrong extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer armUpR;
    public ModelRenderer armLowR;
    public ModelRenderer armUpL;
    public ModelRenderer armLowL;
    public ModelRenderer legUpR;
    public ModelRenderer legLowR;
    public ModelRenderer head;
    public ModelRenderer legUpL;
    public ModelRenderer legLowL;
    public ModelRenderer body2;
    public ModelRenderer body3;

    public ModelPirateStrong() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.body = new ModelRenderer(this, 0, 32);
        this.body.setRotationPoint(-1.0F, 0.0F, 0.0F);
        this.body.addBox(-4.0F, 0.0F, -2.2F, 10, 12, 5);
        this.armUpR = new ModelRenderer(this, 48, 41);
        this.armUpR.setRotationPoint(7.9F, 2.4F, 0.0F);
        this.armUpR.addBox(-1.0F, -2.0F, -2.0F, 4, 6, 4);
        this.armUpR.mirror = true;
        this.setRotationAngles(this.armUpR, -0.0F, 0.10000000116728046F, -0.10000000116728046F);
        this.body.addChild(this.armUpR);
        this.armLowR = new ModelRenderer(this, 52, 11);
        this.armLowR.setRotationPoint(2.3F, 4.0F, 1.5F);
        this.armLowR.addBox(-3.0F, 0.0F, -4.0F, 3, 8, 3);
        this.setRotationAngles(this.armLowR, -0.7925540106674639F, 0.0F, 0.0F);
        this.armUpR.addChild(this.armLowR);
        this.armUpL = new ModelRenderer(this, 31, 41);
        this.armUpL.setRotationPoint(-5.7F, 2.4F, 0.0F);
        this.armUpL.addBox(-3.0F, -2.0F, -2.0F, 4, 6, 4);
        this.setRotationAngles(this.armUpL, 0.11327186445969036F, 0.0F, 0.10000000116728046F);
        this.body.addChild(this.armUpL);
        this.armLowL = new ModelRenderer(this, 37, 11);
        this.armLowL.setRotationPoint(0.5F, 4.0F, 1.5F);
        this.armLowL.addBox(-3.0F, 0.0F, -4.0F, 3, 8, 3);
        this.setRotationAngles(this.armLowL, -0.7925540106674639F, 0.0F, 0.0F);
        this.armUpL.addChild(this.armLowL);
        this.legUpR = new ModelRenderer(this, 48, 54);
        this.legUpR.setRotationPoint(3.4F, 11.5F, 0.5F);
        this.legUpR.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
        this.legUpR.mirror = true;
        this.setRotationAngles(this.legUpR, -0.0F, 0.0F, -0.08726646259971647F);
        this.body.addChild(this.legUpR);
        this.legLowR = new ModelRenderer(this, 36, 28);
        this.legLowR.setRotationPoint(0.5F, 5.6F, 0.1F);
        this.legLowR.addBox(-2.0F, 0.0F, -2.0F, 3, 6, 3);
        this.legLowR.mirror = true;
        this.setRotationAngles(this.legLowR, -0.0F, 0.0F, 0.08726646259971647F);
        this.legUpR.addChild(this.legLowR);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(1.5F, 0.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 7, 8, 8);
        this.body.addChild(this.head);
        this.legUpL = new ModelRenderer(this, 30, 54);
        this.legUpL.setRotationPoint(-1.4F, 11.5F, 0.5F);
        this.legUpL.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
        this.setRotationAngles(this.legUpL, 0.0F, 0.0F, 0.08726646259971647F);
        this.body.addChild(this.legUpL);
        this.legLowL = new ModelRenderer(this, 52, 28);
        this.legLowL.setRotationPoint(0.5F, 5.6F, 0.1F);
        this.legLowL.addBox(-2.0F, 0.0F, -2.0F, 3, 6, 3);
        this.setRotationAngles(this.legLowL, 0.0F, 0.0F, -0.08726646259971647F);
        this.legUpL.addChild(this.legLowL);
        this.body2 = new ModelRenderer(this, 0, 18);
        this.body2.setRotationPoint(-1.0F, 0.5F, -1.0F);
        this.body2.addBox(-4.0F, 0.0F, -2.0F, 12, 6, 6);
        this.body.addChild(this.body2);
        this.body3 = new ModelRenderer(this, 0, 54);
        this.body3.setRotationPoint(2.0F, 10.0F, 0.0F);
        this.body3.addBox(-4.0F, 0.0F, -1.9F, 6, 4, 4);
        this.body.addChild(this.body3);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, rotationYaw, rotationPitch, scale);
        this.body.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
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

    }

    public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
