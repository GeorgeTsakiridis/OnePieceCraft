package georgetsak.opcraft.client.model.boat;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by GeorgeTsak on 7/31/2017.
 */
    @SideOnly(Side.CLIENT)
    public class ModelAceBoat extends ModelBase {

        ModelRenderer Front4;
        ModelRenderer Front1;
        ModelRenderer Front2;
        ModelRenderer Front3;
        ModelRenderer Enginefront;
        ModelRenderer engineInput;
        ModelRenderer Shape2;
        ModelRenderer Enginefront1;
        ModelRenderer Enginefront2;
        ModelRenderer Enginefront3;
        ModelRenderer MastVer;
        ModelRenderer MastHor1;
        ModelRenderer MastHor2;

        public ModelRenderer noWater;

        public ModelAceBoat() {


            textureWidth = 256;
            textureHeight = 128;

            this.Front4 = new ModelRenderer(this, 0, 94);
            this.Front4.addBox(0.0F, 0.0F, 0.0F, 20, 3, 18);
            this.Front4.setRotationPoint(-4.0F, 3.0F, -9.0F);
            this.Front4.setTextureSize(256, 128);
            this.Front4.mirror = true;
            setRotation(this.Front4, 0.0F, 0.0F, 0.0F);

            this.Front1 = new ModelRenderer(this, 56, 58);
            this.Front1.addBox(0.0F, 0.0F, 0.0F, 10, 3, 14);
            this.Front1.setRotationPoint(-14.0F, 3.0F, -7.0F);
            this.Front1.setTextureSize(256, 128);
            this.Front1.mirror = true;
            setRotation(this.Front1, 0.0F, 0.0F, 0.0F);

            this.Front2 = new ModelRenderer(this, 58, 0);
            this.Front2.addBox(0.0F, 0.0F, 0.0F, 10, 3, 10);
            this.Front2.setRotationPoint(-24.0F, 3.0F, -5.0F);
            this.Front2.setTextureSize(256, 128);
            this.Front2.mirror = true;
            setRotation(this.Front2, 0.0F, 0.0F, 0.0F);

            this.Front3 = new ModelRenderer(this, 63, 21);
            this.Front3.addBox(0.0F, 0.0F, 0.0F, 8, 3, 6);
            this.Front3.setRotationPoint(-32.0F, 3.0F, -3.0F);
            this.Front3.setTextureSize(256, 128);
            this.Front3.mirror = true;
            setRotation(this.Front3, 0.0F, 0.0F, 0.0F);

            this.Enginefront = new ModelRenderer(this, 0, 31);
            this.Enginefront.addBox(0.0F, 0.0F, 0.0F, 12, 13, 16);
            this.Enginefront.setRotationPoint(8.0F, -4.0F, -8.0F);
            this.Enginefront.setTextureSize(256, 128);
            this.Enginefront.mirror = true;
            setRotation(this.Enginefront, 0.0F, 0.0F, 0.0F);

            this.engineInput = new ModelRenderer(this, 0, 116);
            this.engineInput.addBox(0.0F, 0.0F, 0.0F, 2, 4, 8);
            this.engineInput.setRotationPoint(6.0F, -1.0F, -4.0F);
            this.engineInput.setTextureSize(256, 128);
            this.engineInput.mirror = true;
            setRotation(this.engineInput, 0.0F, 0.0F, 0.0F);

            this.Shape2 = new ModelRenderer(this, 207, 0);
            this.Shape2.addBox(0.0F, 0.0F, 0.0F, 2, 3, 4);
            this.Shape2.setRotationPoint(-9.0F, 0.0F, -2.0F);
            this.Shape2.setTextureSize(256, 128);
            this.Shape2.mirror = true;
            setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);

            this.Enginefront1 = new ModelRenderer(this, 79, 87);
            this.Enginefront1.addBox(0.0F, 0.0F, 0.0F, 3, 11, 14);
            this.Enginefront1.setRotationPoint(20.0F, -3.0F, -7.0F);
            this.Enginefront1.setTextureSize(256, 128);
            this.Enginefront1.mirror = true;
            setRotation(this.Enginefront1, 0.0F, 0.0F, 0.0F);

            this.Enginefront2 = new ModelRenderer(this, 0, 65);
            this.Enginefront2.addBox(0.0F, 0.0F, 0.0F, 6, 13, 16);
            this.Enginefront2.setRotationPoint(23.0F, -4.0F, -8.0F);
            this.Enginefront2.setTextureSize(256, 128);
            this.Enginefront2.mirror = true;
            setRotation(this.Enginefront2, 0.0F, 0.0F, 0.0F);

            this.Enginefront3 = new ModelRenderer(this, 59, 32);
            this.Enginefront3.addBox(0.0F, 0.0F, 0.0F, 6, 11, 14);
            this.Enginefront3.setRotationPoint(29.0F, -3.0F, -7.0F);
            this.Enginefront3.setTextureSize(256, 128);
            this.Enginefront3.mirror = true;
            setRotation(this.Enginefront3, 0.0F, 0.0F, 0.0F);

            this.MastVer = new ModelRenderer(this, 98, 0);
            this.MastVer.addBox(0.0F, 0.0F, 0.0F, 2, 46, 2);
            this.MastVer.setRotationPoint(13.0F, -50.0F, -1.0F);
            this.MastVer.setTextureSize(256, 128);
            this.MastVer.mirror = true;
            setRotation(this.MastVer, 0.0F, 0.0F, 0.0F);

            this.MastHor1 = new ModelRenderer(this, 115, 66);
            this.MastHor1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 48);
            this.MastHor1.setRotationPoint(11.0F, -40.0F, -24.0F);
            this.MastHor1.setTextureSize(256, 128);
            this.MastHor1.mirror = true;
            setRotation(this.MastHor1, 0.0F, 0.0F, 0.0F);

            this.MastHor2 = new ModelRenderer(this, 106, 0);
            this.MastHor2.addBox(0.0F, 0.0F, 0.0F, 1, 8, 48);
            this.MastHor2.setRotationPoint(10.0F, -38.0F, -24.0F);
            this.MastHor2.setTextureSize(256, 128);
            this.MastHor2.mirror = true;
            setRotation(this.MastHor2, 0.0F, 0.0F, 0.0F);

            this.noWater = (new ModelRenderer(this, 0, 0)).setTextureSize(128, 64);
            this.noWater.addBox(-14.0F, -9.0F, -3.0F, 28, 16, 3, 0.0F);
            this.noWater.setRotationPoint(.0F, -3.0F, 1.0F);
            this.noWater.rotateAngleX = ((float) Math.PI / 2F);
        }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    /**
         * Sets the models various rotation angles then renders the model.
         */
        public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
            GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
            this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

            this.Front4.render(scale);
            this.Front1.render(scale);
            this.Front2.render(scale);
            this.Front3.render(scale);
            this.Enginefront.render(scale);
            this.engineInput.render(scale);
            this.Shape2.render(scale);
            this.Enginefront1.render(scale);
            this.Enginefront2.render(scale);
            this.Enginefront3.render(scale);
            this.MastVer.render(scale);
            this.MastHor1.render(scale);
            this.MastHor2.render(scale);
            
        }

        public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
            super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        }

    }
