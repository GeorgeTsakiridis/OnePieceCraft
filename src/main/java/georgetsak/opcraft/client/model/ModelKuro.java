package georgetsak.opcraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelKuro extends ModelBase {
    private final ModelRenderer body;
    private final ModelRenderer clawsL;
    private final ModelRenderer clawsR;
    private final ModelRenderer legR;
    private final ModelRenderer legL;
    private final ModelRenderer armR;
    private final ModelRenderer armL;
    private final ModelRenderer head;

    public ModelKuro() {
        textureWidth = 128;
        textureHeight = 128;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 6.0F, 2.0F);
        body.cubeList.add(new ModelBox(body, 3, 2, -4.0F, -6.0F, -2.0F, 8, 12, 4, 0.0F, false));

        clawsL = new ModelRenderer(this);
        clawsL.setRotationPoint(6.0F, 12.0F, 1.0F);
        setRotationAngle(clawsL, 0.0F, 1.5708F, 0.0F);
        clawsL.cubeList.add(new ModelBox(clawsL, 4, 25, 0.0F, -1.0F, 1.0F, 1, 11, 0, 0.0F, false));
        clawsL.cubeList.add(new ModelBox(clawsL, 12, 25, 0.0F, -1.0F, -0.0F, 1, 11, 0, 0.0F, false));
        clawsL.cubeList.add(new ModelBox(clawsL, 21, 25, 0.0F, -1.0F, -1.0F, 1, 11, 0, 0.0F, false));

        clawsR = new ModelRenderer(this);
        clawsR.setRotationPoint(-6.0F, 12.0F, 1.0F);
        setRotationAngle(clawsR, 0.0F, 1.5708F, 0.0F);
        clawsR.cubeList.add(new ModelBox(clawsR, 4, 40, -0.0F, -1.0F, 1.0F, 1, 11, 0, 0.0F, false));
        clawsR.cubeList.add(new ModelBox(clawsR, 12, 40, -0.0F, -1.0F, -0.0F, 1, 11, 0, 0.0F, false));
        clawsR.cubeList.add(new ModelBox(clawsR, 21, 40, -0.0F, -1.0F, -1.0F, 1, 11, 0, 0.0F, false));

        legR = new ModelRenderer(this);
        legR.setRotationPoint(-2.0F, 12.0F, 2.0F);
        legR.cubeList.add(new ModelBox(legR, 34, 2, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

        legL = new ModelRenderer(this);
        legL.setRotationPoint(2.0F, 12.0F, 2.0F);
        legL.cubeList.add(new ModelBox(legL, 56, 3, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

        armR = new ModelRenderer(this);
        armR.setRotationPoint(-4.0F, 0.0F, 2.0F);
        armR.cubeList.add(new ModelBox(armR, 33, 24, -4.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

        armL = new ModelRenderer(this);
        armL.setRotationPoint(4.0F, 0.0F, 2.0F);
        armL.cubeList.add(new ModelBox(armL, 56, 24, 0.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, 2.0F);
        head.cubeList.add(new ModelBox(head, 39, 45, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false));
        armL.addChild(clawsL);
        armR.addChild(clawsR);
        clawsL.offsetX = -0.225f;
        clawsR.offsetX = 0.225f;

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        body.render(f5);
        legR.render(f5);
        legL.render(f5);
        armR.render(f5);
        armL.render(f5);
        head.render(f5);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {

        float f = MathHelper.sin(this.swingProgress * (float) Math.PI);
        float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float) Math.PI);

        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;

        this.armR.rotateAngleZ = 0.0F;
        this.armL.rotateAngleZ = 0.0F;
        this.armR.rotateAngleY = -(0.1F - f * 0.6F);
        this.armL.rotateAngleY = 0.1F - f * 0.6F;
        this.armR.rotateAngleX = -0F;
        this.armL.rotateAngleX = -0F;
        this.armR.rotateAngleX += f * 1.2F - f1 * 0.4F;
        this.armL.rotateAngleX += f * 1.2F - f1 * 0.4F;
        this.armR.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.armL.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.armR.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.armL.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.armR.rotateAngleX += (MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount) / 2;
        this.armL.rotateAngleX += (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount) / 2;

        this.legL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.legR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;


    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}