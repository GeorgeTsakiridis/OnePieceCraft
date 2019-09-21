package georgetsak.opcraft.client.model.npc;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelMorgan extends ModelBase {
        public ModelRenderer body;
        public ModelRenderer armUpR;
        public ModelRenderer armLowR;
        public ModelRenderer armLowR2;
        public ModelRenderer woodpart;
        public ModelRenderer armLowR3;
        public ModelRenderer axeblade1;
        public ModelRenderer axeblade2;
        public ModelRenderer axeblade3;
        public ModelRenderer axeblade4;
        public ModelRenderer armLowR4;
        public ModelRenderer armUpL;
        public ModelRenderer armLowL;
        public ModelRenderer legUpR;
        public ModelRenderer legLowR;
        public ModelRenderer head;
        public ModelRenderer metalMouth;
        public ModelRenderer legUpL;
        public ModelRenderer legLowL;
        public ModelRenderer body2;
        public ModelRenderer body3;

        public ModelMorgan() {
                this.textureWidth = 64;
                this.textureHeight = 64;

                this.body = new ModelRenderer(this, 0, 47);
                this.body.setRotationPoint(-1.0F, 1.0F, 0.0F);
                this.body.addBox(-4.0F, 0.0F, -2.2F, 10, 12, 5);
                this.armUpR = new ModelRenderer(this, 48, 8);
                this.armUpR.setRotationPoint(-5.7F, 2.4F, 0.0F);
                this.armUpR.addBox(-3.0F, -2.0F, -2.0F, 4, 7, 4);
                this.armUpR.mirror = true;
                this.setRotationAngles(this.armUpR, -0.67928211291826F, -0.09302604779971722F, 0.10349802164720752F);
                this.body.addChild(this.armUpR);
                this.armLowR = new ModelRenderer(this, 37, 36);
                this.armLowR.setRotationPoint(0.5F, 5.0F, 1.5F);
                this.armLowR.addBox(-3.0F, 0.0F, -4.0F, 3, 5, 4);
                this.setRotationAngles(this.armLowR, -1.2170181460051899F, 0.0F, 0.0F);
                this.armUpR.addChild(this.armLowR);
                this.armLowR2 = new ModelRenderer(this, 31, 47);
                this.armLowR2.setRotationPoint(0.5F, -3.6F, 0.0F);
                this.armLowR2.addBox(-3.0F, 0.0F, -4.0F, 2, 13, 4);
                this.armLowR.addChild(this.armLowR2);
                this.woodpart = new ModelRenderer(this, 60, 47);
                this.woodpart.setRotationPoint(-2.5F, -1.4F, -2.5F);
                this.woodpart.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1);
                this.armLowR2.addChild(this.woodpart);
                this.armLowR3 = new ModelRenderer(this, 43, 49);
                this.armLowR3.setRotationPoint(0.0F, -2.6F, 0.5F);
                this.armLowR3.addBox(-3.0F, 0.0F, -4.0F, 3, 12, 3);
                this.armLowR.addChild(this.armLowR3);
                this.axeblade1 = new ModelRenderer(this, 31, 4);
                this.axeblade1.setRotationPoint(-2.0F, 8.7F, -0.8F);
                this.axeblade1.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2);
                this.armLowR3.addChild(this.axeblade1);
                this.axeblade2 = new ModelRenderer(this, 24, 0);
                this.axeblade2.setRotationPoint(-0.5F, -0.5F, 0.9F);
                this.axeblade2.addBox(0.0F, 0.0F, 0.0F, 2, 3, 2);
                this.axeblade1.addChild(this.axeblade2);
                this.axeblade3 = new ModelRenderer(this, 31, 11);
                this.axeblade3.setRotationPoint(0.5F, -0.8F, 0.4F);
                this.axeblade3.addBox(0.0F, 0.0F, 0.0F, 1, 4, 2);
                this.axeblade2.addChild(this.axeblade3);
                this.axeblade4 = new ModelRenderer(this, 0, 0);
                this.axeblade4.setRotationPoint(0.0F, -0.5F, 0.4F);
                this.axeblade4.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
                this.axeblade3.addChild(this.axeblade4);
                this.armLowR4 = new ModelRenderer(this, 49, 0);
                this.armLowR4.setRotationPoint(-0.5F, 4.4F, 0.9F);
                this.armLowR4.addBox(-3.0F, 0.0F, -4.0F, 4, 5, 2);
                this.armLowR.addChild(this.armLowR4);
                this.armUpL = new ModelRenderer(this, 48, 8);
                this.armUpL.setRotationPoint(5.7F, 2.4F, 0.0F);
                this.armUpL.addBox(1.0F, -2.0F, -2.0F, 4, 7, 4);
                this.setRotationAngles(this.armUpL, 0.11327186445969036F, 0.0F, -0.10000736647217022F);
                this.body.addChild(this.armUpL);
                this.armLowL = new ModelRenderer(this, 52, 35);
                this.armLowL.setRotationPoint(4.5F, 5.0F, 2.0F);
                this.armLowL.addBox(-3.0F, 0.0F, -4.0F, 3, 7, 3);
                this.setRotationAngles(this.armLowL, -0.7925540106674639F, 0.0F, 0.0F);
                this.armUpL.addChild(this.armLowL);
                this.legUpR = new ModelRenderer(this, 16, 32);
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
                this.metalMouth = new ModelRenderer(this, 40, 20);
                this.metalMouth.setRotationPoint(-0.5F, 4.5F, 0.7F);
                this.metalMouth.addBox(-4.0F, -8.0F, -4.0F, 8, 3, 2);
                this.setRotationAngles(this.metalMouth, 0.2546435405291338F, 0.0F, 0.0F);
                this.head.addChild(this.metalMouth);
                this.legUpL = new ModelRenderer(this, 16, 32);
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
                this.body2.setRotationPoint(-1.0F, 0.5F, -1.0F);
                this.body2.addBox(-4.0F, 0.0F, -2.0F, 12, 6, 6);
                this.body.addChild(this.body2);
                this.body3 = new ModelRenderer(this, 43, 26);
                this.body3.setRotationPoint(2.0F, 10.0F, 0.0F);
                this.body3.addBox(-4.0F, 0.0F, -1.9F, 6, 4, 4);
                this.body.addChild(this.body3);
        }

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


        @Override
        public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
                super.render(entity, limbSwing, limbSwingAmount, ageInTicks, rotationYaw, rotationPitch, scale);
                GL11.glScalef(1.2f, 1.2f, 1.2f);
                GL11.glTranslatef(0.0f, -0.25f, 0.0f);
                this.body.render(scale);
                GL11.glScalef(1f, 1f, 1f);
        }

        public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
                modelRenderer.rotateAngleX = x;
                modelRenderer.rotateAngleY = y;
                modelRenderer.rotateAngleZ = z;
        }
}
