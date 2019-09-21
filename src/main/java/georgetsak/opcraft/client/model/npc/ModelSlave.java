package georgetsak.opcraft.client.model.npc;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class  ModelSlave extends ModelBase {
    private final ModelRenderer body;
    private final ModelRenderer armR;
    private final ModelRenderer handcuffs;
    private final ModelRenderer armL;
    private final ModelRenderer legR;
    private final ModelRenderer legL;
    private final ModelRenderer head;

    public ModelSlave(boolean hasChains) {
        textureWidth = 64;
        textureHeight = 64;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.cubeList.add(new ModelBox(body, 19, 20, -4.0F, -24.0F, 0.0F, 8, 12, 4, 0.0F, false));

        armR = new ModelRenderer(this);
        armR.setRotationPoint(-4.0F, 0.0F, 2.0F);
        armR.cubeList.add(new ModelBox(armR, 27, 2, -4.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

        handcuffs = new ModelRenderer(this);
        handcuffs.setRotationPoint(-2.0F, 10.0F, 0.0F);
        if (hasChains) {
            armR.addChild(handcuffs);
        }
        handcuffs.cubeList.add(new ModelBox(handcuffs, 1, 2, -3.0F, -1.0F, -3.0F, 6, 2, 6, 0.0F, false));
        handcuffs.cubeList.add(new ModelBox(handcuffs, 13, 43, -5.0F, -2.0F, -1.0F, 3, 1, 2, 0.0F, false));
        handcuffs.cubeList.add(new ModelBox(handcuffs, 9, 36, -5.0F, -2.0F, -1.0F, 1, 3, 2, 0.0F, false));
        handcuffs.cubeList.add(new ModelBox(handcuffs, 11, 47, -5.0F, 1.0F, -1.0F, 3, 1, 2, 0.0F, false));
        handcuffs.cubeList.add(new ModelBox(handcuffs, 12, 24, -4.0F, 0.0F, 1.0F, 1, 4, 1, 0.0F, false));
        handcuffs.cubeList.add(new ModelBox(handcuffs, 17, 37, -4.0F, 0.0F, -2.0F, 1, 1, 4, 0.0F, false));
        handcuffs.cubeList.add(new ModelBox(handcuffs, 7, 24, -4.0F, 0.0F, -3.0F, 1, 4, 1, 0.0F, false));
        handcuffs.cubeList.add(new ModelBox(handcuffs, 2, 51, -4.0F, 4.0F, -3.0F, 1, 1, 5, 0.0F, false));
        handcuffs.cubeList.add(new ModelBox(handcuffs, 2, 47, -5.0F, 3.0F, -1.0F, 3, 1, 1, 0.0F, false));
        handcuffs.cubeList.add(new ModelBox(handcuffs, 7, 30, -6.0F, 3.0F, -1.0F, 1, 4, 1, 0.0F, false));
        handcuffs.cubeList.add(new ModelBox(handcuffs, 1, 43, -6.0F, 7.0F, -1.0F, 4, 1, 1, 0.0F, false));
        handcuffs.cubeList.add(new ModelBox(handcuffs, 1, 38, -4.0F, 6.0F, -2.0F, 1, 1, 3, 0.0F, false));
        handcuffs.cubeList.add(new ModelBox(handcuffs, 1, 30, -4.0F, 6.0F, 1.0F, 1, 5, 1, 0.0F, false));
        handcuffs.cubeList.add(new ModelBox(handcuffs, 1, 24, -4.0F, 6.0F, -3.0F, 1, 4, 1, 0.0F, false));
        handcuffs.cubeList.add(new ModelBox(handcuffs, 7, 18, -4.0F, 10.0F, -3.0F, 1, 1, 2, 0.0F, false));
        handcuffs.cubeList.add(new ModelBox(handcuffs, 1, 17, -2.0F, 3.0F, -1.0F, 1, 5, 1, 0.0F, false));


        armL = new ModelRenderer(this);
        armL.setRotationPoint(4.0F, 0.0F, 2.0F);
        armL.cubeList.add(new ModelBox(armL, 45, 20, 0.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

        legR = new ModelRenderer(this);
        legR.setRotationPoint(-2.0F, 12.0F, 2.0F);
        legR.cubeList.add(new ModelBox(legR, 45, 2, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

        legL = new ModelRenderer(this);
        legL.setRotationPoint(2.0F, 12.0F, 2.0F);
        legL.cubeList.add(new ModelBox(legL, 45, 38, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, 2.0F);
        head.cubeList.add(new ModelBox(head, 14, 47, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        body.render(f5);
        armR.render(f5);
        armL.render(f5);
        legR.render(f5);
        legL.render(f5);
        head.render(f5);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        float f = MathHelper.sin(this.swingProgress * (float) Math.PI);
        float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float) Math.PI);
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
}