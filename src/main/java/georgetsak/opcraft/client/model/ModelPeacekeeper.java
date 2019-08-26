package georgetsak.opcraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelPeacekeeper extends ModelBase {
    private final ModelRenderer body;
    private final ModelRenderer legR;
    private final ModelRenderer legL;
    private final ModelRenderer armR;
    private final ModelRenderer armL;
    private final ModelRenderer head;

    public ModelPeacekeeper() {
        textureWidth = 64;
        textureHeight = 64;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 15.0F, -2.0F);
        body.cubeList.add(new ModelBox(body, 0, 2, -4.0F, 5.0F, -4.0F, 8, 1, 8, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 0, 12, -3.0F, 4.0F, -3.0F, 6, 2, 6, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 2, 21, -2.0F, -4.0F, -2.0F, 4, 9, 4, 0.0F, false));

        legR = new ModelRenderer(this);
        legR.setRotationPoint(1.0F, 20.0F, -2.0F);
        legR.cubeList.add(new ModelBox(legR, 20, 36, -1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F, false));

        legL = new ModelRenderer(this);
        legL.setRotationPoint(-1.0F, 20.0F, -2.0F);
        legL.cubeList.add(new ModelBox(legL, 29, 36, -1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F, false));

        armR = new ModelRenderer(this);
        armR.setRotationPoint(2.0F, 12.0F, -2.0F);
        armR.cubeList.add(new ModelBox(armR, 2, 35, 0.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F, false));

        armL = new ModelRenderer(this);
        armL.setRotationPoint(-2.0F, 12.0F, -2.0F);
        armL.cubeList.add(new ModelBox(armL, 11, 35, -2.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F, false));

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 11.0F, -2.0F);
        head.cubeList.add(new ModelBox(head, 20, 26, -3.0F, -5.0F, -3.0F, 6, 2, 6, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 2, 43, -2.0F, -6.0F, -2.0F, 4, 1, 4, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 3, 49, -3.0F, -3.0F, -3.0F, 1, 2, 6, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 19, 49, 2.0F, -3.0F, -3.0F, 1, 2, 6, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 33, 2, -2.0F, -3.0F, -2.0F, 4, 2, 5, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 27, 14, -3.0F, -1.0F, -3.0F, 6, 1, 6, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GlStateManager.scale(1.5f, 1.5f,1.5f);
        GlStateManager.translate(0f,-0.5,0f);
        body.render(f5);
        legR.render(f5);
        legL.render(f5);
        armR.render(f5);
        armL.render(f5);
        head.render(f5);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        float f = -MathHelper.sin(this.swingProgress * (float)Math.PI);
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

    }

}