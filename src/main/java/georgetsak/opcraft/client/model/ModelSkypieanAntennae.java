package georgetsak.opcraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class  ModelSkypieanAntennae extends ModelBase {
    private final ModelRenderer wingR;
    private final ModelRenderer body;
    private final ModelRenderer wingL;
    private final ModelRenderer legL;
    private final ModelRenderer legR;
    private final ModelRenderer armL;
    private final ModelRenderer armR;
    private final ModelRenderer head;
    private final ModelRenderer rodL;
    private final ModelRenderer rodR;

    public ModelSkypieanAntennae() {
        textureWidth = 128;
        textureHeight = 128;

        wingR = new ModelRenderer(this);
        wingR.setRotationPoint(0.0F, 5.0F, 3.0F);
        setRotationAngle(wingR, 0.0F, 0.2618F, 0.1745F);
        wingR.cubeList.add(new ModelBox(wingR, 49, 0, -14.7114F, -3.2734F, -1.2247F, 14, 1, 1, 0.0F, false));
        wingR.cubeList.add(new ModelBox(wingR, 51, 4, -13.7114F, -2.2734F, -1.2247F, 13, 1, 1, 0.0F, false));
        wingR.cubeList.add(new ModelBox(wingR, 53, 8, -12.7114F, -1.2734F, -1.2247F, 12, 1, 1, 0.0F, false));
        wingR.cubeList.add(new ModelBox(wingR, 55, 12, -11.7114F, -0.2734F, -1.2247F, 11, 1, 1, 0.0F, false));
        wingR.cubeList.add(new ModelBox(wingR, 57, 16, -10.7114F, 0.7266F, -1.2247F, 10, 1, 1, 0.0F, false));

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 6.0F, 0.0F);
        body.cubeList.add(new ModelBox(body, 0, 48, -4.0F, -6.0F, -1.0F, 8, 12, 4, 0.0F, false));

        wingL = new ModelRenderer(this);
        wingL.setRotationPoint(0.0F, 5.0F, 3.0F);
        setRotationAngle(wingL, 0.0F, 2.8798F, -0.1745F);
        wingL.cubeList.add(new ModelBox(wingL, 0, 0, -14.7114F, -3.2734F, 0.2247F, 14, 1, 1, 0.0F, false));
        wingL.cubeList.add(new ModelBox(wingL, 0, 4, -13.7114F, -2.2734F, 0.2247F, 13, 1, 1, 0.0F, false));
        wingL.cubeList.add(new ModelBox(wingL, 0, 8, -12.7114F, -1.2734F, 0.2247F, 12, 1, 1, 0.0F, false));
        wingL.cubeList.add(new ModelBox(wingL, 0, 12, -11.7114F, -0.2734F, 0.2247F, 11, 1, 1, 0.0F, false));
        wingL.cubeList.add(new ModelBox(wingL, 0, 16, -10.7114F, 0.7266F, 0.2247F, 10, 1, 1, 0.0F, false));

        legL = new ModelRenderer(this);
        legL.setRotationPoint(2.0F, 13.0F, 1.0F);
        legL.cubeList.add(new ModelBox(legL, 10, 29, -2.0F, -1.0F, -2.0F, 4, 12, 4, 0.0F, false));

        legR = new ModelRenderer(this);
        legR.setRotationPoint(-2.0F, 13.0F, 1.0F);
        legR.cubeList.add(new ModelBox(legR, 28, 10, -2.0F, -1.0F, -2.0F, 4, 12, 4, 0.0F, false));

        armL = new ModelRenderer(this);
        armL.setRotationPoint(4.0F, 0.0F, 1.0F);
        armL.cubeList.add(new ModelBox(armL, 29, 29, 0.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

        armR = new ModelRenderer(this);
        armR.setRotationPoint(-4.0F, 0.0F, 1.0F);
        armR.cubeList.add(new ModelBox(armR, 48, 29, -4.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 1.0F, 1.0F);
        head.cubeList.add(new ModelBox(head, 32, 48, -4.0F, -9.0F, -4.0F, 8, 8, 8, 0.0F, false));

        rodL = new ModelRenderer(this);
        rodL.setRotationPoint(-2.0F, -7.0F, 0.0F);
        setRotationAngle(rodL, 0.0F, 0.0F, 0.6109F);
        head.addChild(rodL);
        rodL.cubeList.add(new ModelBox(rodL, 46, 11, 2.9928F, -6.9343F, -1.0F, 1, 5, 1, 0.0F, false));
        rodL.cubeList.add(new ModelBox(rodL, 33, 2, 1.9928F, -8.9343F, -2.0F, 3, 2, 3, 0.0F, false));

        rodR = new ModelRenderer(this);
        rodR.setRotationPoint(2.0F, -7.0F, -1.0F);
        setRotationAngle(rodR, 0.0F, 0.0F, -0.6109F);
        head.addChild(rodR);
        rodR.cubeList.add(new ModelBox(rodR, 2, 30, -3.9928F, -6.9343F, 0.0F, 1, 5, 1, 0.0F, false));
        rodR.cubeList.add(new ModelBox(rodR, 2, 21, -4.9928F, -8.9343F, -1.0F, 3, 2, 3, 0.0F, false));
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
        float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);

        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;

        this.armR.rotateAngleZ = 0.0F;
        this.armL.rotateAngleZ = 0.0F;
        this.armR.rotateAngleY = -(0.1F - f * 0.6F);
        this.armL.rotateAngleY = 0.1F - f * 0.6F;
        this.armR.rotateAngleX = -0F;
        this.armL.rotateAngleX =  -0F;
        this.armR.rotateAngleX += f * 1.2F - f1 * 0.4F;
        this.armL.rotateAngleX += f * 1.2F - f1 * 0.4F;
        this.armR.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.armL.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.armR.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.armL.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.armR.rotateAngleX += (MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount) / 2;
        this.armL.rotateAngleX += (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount) / 2;

        this.legL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        float speed = 0.1f;
        float distance = 0.25f;

        if(!entityIn.onGround) {
            speed = 1.5f;
            distance = 0.5f;
        }

        this.wingL.rotateAngleZ = MathHelper.cos(ageInTicks*speed)*distance;
        this.wingR.rotateAngleZ = -MathHelper.cos(ageInTicks*speed)*distance;

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        wingR.render(f5);
        body.render(f5);
        wingL.render(f5);
        legL.render(f5);
        legR.render(f5);
        armL.render(f5);
        armR.render(f5);
        head.render(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}