package georgetsak.opcraft.client.model.npc;

//Made with Blockbench
//Paste this code into your mod.

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTequilaBridgeGuardRelaxed extends ModelBase {
        private final ModelRenderer body;
        private final ModelRenderer head;
        private final ModelRenderer hatHeadTop;
        private final ModelRenderer hatHeadSideL;
        private final ModelRenderer hatHeadSideR;
        private final ModelRenderer legR;
        private final ModelRenderer legL;
        private final ModelRenderer armR;
        private final ModelRenderer armL;
        private final ModelRenderer gun;
        private float[] initialRot = new float[12];

        public ModelTequilaBridgeGuardRelaxed() {
                textureWidth = 128;
                textureHeight = 128;

                body = new ModelRenderer(this);
                body.setRotationPoint(0.0F, 7.0F, 0.0F);
                body.cubeList.add(new ModelBox(body, 0, 14, -3.0F, -6.0F, -5.0F, 6, 12, 10, 0.0F, false));

                head = new ModelRenderer(this);
                head.setRotationPoint(0.0F, 1.0F, 0.0F);
                head.cubeList.add(new ModelBox(head, 24, 28, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false));

                hatHeadTop = new ModelRenderer(this);
                hatHeadTop.setRotationPoint(0.0F, 0.0F, 0.0F);
                head.addChild(hatHeadTop);
                hatHeadTop.cubeList.add(new ModelBox(hatHeadTop, 30, 0, -4.0F, -9.0F, -4.0F, 8, 1, 8, 0.0F, false));
                hatHeadTop.cubeList.add(new ModelBox(hatHeadTop, 0, 0, -5.0F, -12.0F, -5.0F, 10, 3, 10, 0.0F, false));
                hatHeadTop.cubeList.add(new ModelBox(hatHeadTop, 22, 14, -4.0F, -13.0F, -4.0F, 8, 1, 8, 0.0F, false));

                hatHeadSideL = new ModelRenderer(this);
                hatHeadSideL.setRotationPoint(0.0F, -9.5F, 4.0F);
                setRotationAngle(hatHeadSideL, 0.0873F, 0.0F, 0.0F);
                hatHeadTop.addChild(hatHeadSideL);
                hatHeadSideL.cubeList.add(new ModelBox(hatHeadSideL, 46, 9, -5.0F, 0.5F, 0.0F, 10, 9, 1, 0.0F, false));

                hatHeadSideR = new ModelRenderer(this);
                hatHeadSideR.setRotationPoint(0.0F, -9.5F, -4.0F);
                setRotationAngle(hatHeadSideR, -0.0873F, 0.0F, 0.0F);
                hatHeadTop.addChild(hatHeadSideR);
                hatHeadSideR.cubeList.add(new ModelBox(hatHeadSideR, 44, 44, -5.0F, 0.5F, -1.0F, 10, 9, 1, 0.0F, false));

                legR = new ModelRenderer(this);
                legR.setRotationPoint(-1.0F, 13.0F, -2.0F);
                legR.cubeList.add(new ModelBox(legR, 22, 44, -2.0F, 0.0F, -3.0F, 6, 11, 5, 0.0F, false));

                legL = new ModelRenderer(this);
                legL.setRotationPoint(-1.0F, 13.0F, 3.0F);
                legL.cubeList.add(new ModelBox(legL, 0, 36, -2.0F, 0.0F, -3.0F, 6, 11, 5, 0.0F, false));

                armR = new ModelRenderer(this);
                armR.setRotationPoint(-1.0F, 1.0F, -4.0F);
                setRotationAngle(armR, 0.0F, 0.0F, 0.9599F);
                armR.cubeList.add(new ModelBox(armR, 0, 52, -1.4264F, -0.8192F, -4.0F, 4, 12, 4, 0.0F, false));

                armL = new ModelRenderer(this);
                armL.setRotationPoint(-1.0F, 1.0F, 4.0F);
                setRotationAngle(armL, -0.6981F, -0.6109F, 1.1345F);
                armL.cubeList.add(new ModelBox(armL, 50, 19, -1.6538F, -0.5385F, -0.7683F, 4, 12, 4, 0.0F, false));

                gun = new ModelRenderer(this);
                gun.setRotationPoint(2.7207F, 8.4204F, 3.8825F);
                setRotationAngle(gun, -0.0873F, -0.1745F, -0.6981F);
                armL.addChild(gun);
                gun.cubeList.add(new ModelBox(gun, 32, 23, -8.1563F, -0.8842F, -3.3101F, 5, 2, 2, 0.0F, false));
                gun.cubeList.add(new ModelBox(gun, 54, 0, -16.1563F, -0.8842F, -3.3101F, 4, 1, 2, 0.0F, false));
                gun.cubeList.add(new ModelBox(gun, 0, 0, -10.1563F, -0.8842F, -3.3101F, 1, 2, 2, 0.0F, false));
                gun.cubeList.add(new ModelBox(gun, 44, 54, -19.1563F, -1.8842F, -3.3101F, 12, 1, 2, 0.0F, false));

                initialRot[0] = armR.rotateAngleX;
                initialRot[1] = armR.rotateAngleY;
                initialRot[2] = armR.rotateAngleZ;
                initialRot[3] = armL.rotateAngleX;
                initialRot[4] = armL.rotateAngleY;
                initialRot[5] = armL.rotateAngleZ;
                initialRot[6] = hatHeadSideR.rotateAngleX;
                initialRot[7] = hatHeadSideR.rotateAngleY;
                initialRot[8] = hatHeadSideR.rotateAngleZ;
                initialRot[9] = hatHeadSideL.rotateAngleX;
                initialRot[10] = hatHeadSideL.rotateAngleY;
                initialRot[11] = hatHeadSideL.rotateAngleZ;

        }


        @Override
        public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
                GlStateManager.rotate(-90f,0f,1f,0f);
                body.render(f5);
                head.render(f5);
                legR.render(f5);
                legL.render(f5);
                armR.render(f5);
                armL.render(f5);
        }

        public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
                modelRenderer.rotateAngleX = x;
                modelRenderer.rotateAngleY = y;
                modelRenderer.rotateAngleZ = z;
        }


        @Override
        public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
                super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

                this.head.rotateAngleY = netHeadYaw * 0.017453292F;
                this.head.rotateAngleX = headPitch * 0.017453292F;

                this.hatHeadSideR.rotateAngleX = initialRot[6] + MathHelper.sin(ageInTicks*0.1f)*0.03f;
                this.hatHeadSideL.rotateAngleX = initialRot[9] - MathHelper.sin(ageInTicks*0.1f)*0.03f;

                this.armR.rotateAngleZ = initialRot[2];
                this.armL.rotateAngleZ = initialRot[5];
                this.armR.rotateAngleY = initialRot[1];
                this.armL.rotateAngleY = initialRot[4];
                this.armR.rotateAngleX = initialRot[0];
                this.armL.rotateAngleX = initialRot[3];
                this.armR.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.025F + 0.05F;
                this.armL.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.025F + 0.05F;
                this.armR.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.025F;
                this.armL.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.025F;

                this.legL.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
                this.legR.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        }
}