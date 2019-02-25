package georgetsak.opcraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelCrocodile extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer armUpR;
    public ModelRenderer armLowR;
    public ModelRenderer hook1;
    public ModelRenderer hook2_1;
    public ModelRenderer hook2_2;
    public ModelRenderer hook2_3;
    public ModelRenderer hook2_4;
    public ModelRenderer hook2_5;
    public ModelRenderer hook3_1;
    public ModelRenderer hook3_2;
    public ModelRenderer hook3_3;
    public ModelRenderer armUpL;
    public ModelRenderer armLowL;
    public ModelRenderer legUpR;
    public ModelRenderer legLowR;
    public ModelRenderer head;
    public ModelRenderer legUpL;
    public ModelRenderer legLowL;
    public ModelRenderer body2;
    public ModelRenderer body3;

    public ModelCrocodile() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.body = new ModelRenderer(this, 0, 47);
        this.body.setRotationPoint(-1.0F, 1.1F, 0.0F);
        this.body.addBox(-4.0F, 0.0F, -2.2F, 9, 12, 5);
        this.armUpR = new ModelRenderer(this, 50, 0);
        this.armUpR.setRotationPoint(-4.4F, 2.4F, 0.0F);
        this.armUpR.addBox(-3.0F, -2.0F, -2.0F, 3, 7, 4);
        this.armUpR.mirror = true;
        this.setRotationAngles(this.armUpR, -0.42446406875869874F, 0.0F, 0.10349802164720752F);
        this.body.addChild(this.armUpR);
        this.armLowR = new ModelRenderer(this, 54, 12);
        this.armLowR.setRotationPoint(0.1F, 5.0F, 1.5F);
        this.armLowR.addBox(-3.0F, 0.0F, -4.0F, 2, 6, 3);
        this.setRotationAngles(this.armLowR, -1.018923235956417F, 0.0F, 0.0F);
        this.armUpR.addChild(this.armLowR);
        this.hook1 = new ModelRenderer(this, 48, 24);
        this.hook1.setRotationPoint(-1.0F, 4.5F, -0.5F);
        this.hook1.addBox(-3.0F, 0.0F, -4.0F, 4, 2, 4);
        this.armLowR.addChild(this.hook1);
        this.hook2_1 = new ModelRenderer(this, 59, 48);
        this.hook2_1.setRotationPoint(-1.5F, 3.0F, -2.5F);
        this.hook2_1.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
        this.hook1.addChild(this.hook2_1);
        this.hook2_2 = new ModelRenderer(this, 60, 53);
        this.hook2_2.setRotationPoint(0.0F, 3.0F, -2.0F);
        this.hook2_2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
        this.setRotationAngles(this.hook2_2, 1.5707963267948966F, 0.0F, 0.0F);
        this.hook2_1.addChild(this.hook2_2);
        this.hook2_3 = new ModelRenderer(this, 60, 58);
        this.hook2_3.setRotationPoint(0.0F, 0.5F, -2.5F);
        this.hook2_3.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
        this.setRotationAngles(this.hook2_3, 1.5707963267948966F, 0.0F, 0.0F);
        this.hook2_2.addChild(this.hook2_3);
        this.hook2_4 = new ModelRenderer(this, 55, 60);
        this.hook2_4.setRotationPoint(0.0F, 0.5F, -2.5F);
        this.hook2_4.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
        this.setRotationAngles(this.hook2_4, 1.5707963267948966F, 0.0F, 0.0F);
        this.hook2_3.addChild(this.hook2_4);
        this.hook2_5 = new ModelRenderer(this, 51, 57);
        this.hook2_5.setRotationPoint(0.0F, 0.8F, -1.1F);
        this.hook2_5.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
        this.setRotationAngles(this.hook2_5, 1.5707963267948966F, 0.0F, 0.0F);
        this.hook2_4.addChild(this.hook2_5);
        this.hook3_1 = new ModelRenderer(this, 52, 32);
        this.hook3_1.setRotationPoint(2.0F, -1.5F, 3.0F);
        this.hook3_1.addBox(-3.0F, 0.0F, -4.0F, 3, 2, 3);
        this.hook2_1.addChild(this.hook3_1);
        this.hook3_2 = new ModelRenderer(this, 52, 38);
        this.hook3_2.setRotationPoint(0.5F, 0.5F, -0.5F);
        this.hook3_2.addBox(-3.0F, 0.0F, -4.0F, 2, 1, 4);
        this.hook3_1.addChild(this.hook3_2);
        this.hook3_3 = new ModelRenderer(this, 52, 44);
        this.hook3_3.setRotationPoint(-0.5F, 0.5F, 0.5F);
        this.hook3_3.addBox(-3.0F, 0.0F, -4.0F, 4, 1, 2);
        this.hook3_1.addChild(this.hook3_3);
        this.armUpL = new ModelRenderer(this, 50, 0);
        this.armUpL.setRotationPoint(4.5F, 2.4F, 0.0F);
        this.armUpL.addBox(1.0F, -2.0F, -2.0F, 3, 7, 4);
        this.setRotationAngles(this.armUpL, 0.11327186445969036F, 0.0F, -0.10000736647217022F);
        this.body.addChild(this.armUpL);
        this.armLowL = new ModelRenderer(this, 43, 12);
        this.armLowL.setRotationPoint(4.5F, 5.0F, 2.0F);
        this.armLowL.addBox(-3.0F, 0.0F, -4.0F, 2, 7, 3);
        this.setRotationAngles(this.armLowL, -0.7925540106674639F, 0.0F, 0.0F);
        this.armUpL.addChild(this.armLowL);
        this.legUpR = new ModelRenderer(this, 25, 40);
        this.legUpR.setRotationPoint(3.4F, 11.5F, 0.5F);
        this.legUpR.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
        this.legUpR.mirror = true;
        this.setRotationAngles(this.legUpR, -0.0F, 0.0F, -0.08726646259971647F);
        this.body.addChild(this.legUpR);
        this.legLowR = new ModelRenderer(this, 0, 30);
        this.legLowR.setRotationPoint(0.7F, 5.6F, 0.6F);
        this.legLowR.addBox(-2.0F, 0.0F, -2.0F, 3, 6, 3);
        this.legLowR.mirror = true;
        this.setRotationAngles(this.legLowR, -0.0F, 0.0F, 0.08726646259971647F);
        this.legUpR.addChild(this.legLowR);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(1.5F, 0.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 7, 8, 8);
        this.body.addChild(this.head);
        this.legUpL = new ModelRenderer(this, 25, 40);
        this.legUpL.setRotationPoint(-1.4F, 11.5F, 0.5F);
        this.legUpL.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
        this.setRotationAngles(this.legUpL, 0.0F, 0.0F, 0.08726646259971647F);
        this.body.addChild(this.legUpL);
        this.legLowL = new ModelRenderer(this, 0, 30);
        this.legLowL.setRotationPoint(0.3F, 5.6F, 0.6F);
        this.legLowL.addBox(-2.0F, 0.0F, -2.0F, 3, 6, 3);
        this.setRotationAngles(this.legLowL, 0.0F, 0.0F, -0.08726646259971647F);
        this.legUpL.addChild(this.legLowL);
        this.body2 = new ModelRenderer(this, 0, 17);
        this.body2.setRotationPoint(-0.4F, 0.5F, -1.0F);
        this.body2.addBox(-4.0F, 0.0F, -2.0F, 10, 6, 6);
        this.body.addChild(this.body2);
        this.body3 = new ModelRenderer(this, 14, 31);
        this.body3.setRotationPoint(2.0F, 10.0F, 0.0F);
        this.body3.addBox(-4.0F, 0.0F, -1.9F, 6, 4, 4);
        this.body.addChild(this.body3);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
        GL11.glScalef(1.2f, 1.2f, 1.2f);
        GL11.glTranslatef(0f, -0.25f, 0f);
        this.body.render(scale);
        GL11.glScalef(1f, 1f, 1f);
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
