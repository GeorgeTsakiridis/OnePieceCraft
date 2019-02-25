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
public class ModelSailBoat extends ModelBase {

    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
    ModelRenderer Shape20;
    ModelRenderer Shape21;
    ModelRenderer Shape22;
    ModelRenderer Shape23;
    ModelRenderer Shape24;
    ModelRenderer Shape25;
    ModelRenderer Shape26;
    ModelRenderer Shape27;
    ModelRenderer Shape28;
    ModelRenderer Shape29;
    ModelRenderer Shape30;

    public ModelRenderer noWater;

    public ModelSailBoat() {
        textureWidth = 256;
        textureHeight = 256;

        Shape1 = new ModelRenderer(this, 96, 0);
        Shape1.addBox(-4F, 3F, -16F, 48, 4, 32);
        Shape1.setRotationPoint(0F, 0F, 0F);
        Shape1.setTextureSize(256, 256);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 134, 115);
        Shape2.addBox(-4F, -3F, -18F, 48, 6, 2);
        Shape2.setRotationPoint(0F, 0F, 0F);
        Shape2.setTextureSize(256, 256);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 134, 115);
        Shape3.addBox(-4F, -3F, 16F, 48, 6, 2);
        Shape3.setRotationPoint(0F, 0F, 0F);
        Shape3.setTextureSize(256, 256);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 45, 75);
        Shape4.addBox(-12F, 3F, -14F, 8, 4, 28);
        Shape4.setRotationPoint(0F, 0F, 0F);
        Shape4.setTextureSize(256, 256);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 45, 47);
        Shape5.addBox(0F, 0F, 0F, 8, 4, 24);
        Shape5.setRotationPoint(-20F, 3F, -12F);
        Shape5.setTextureSize(256, 256);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 0, 0);
        Shape6.addBox(-12F, -3F, -16F, 8, 6, 2);
        Shape6.setRotationPoint(0F, 0F, 0F);
        Shape6.setTextureSize(256, 256);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 0, 0);
        Shape7.addBox(0F, 0F, 0F, 8, 6, 2);
        Shape7.setRotationPoint(-20F, -3F, -14F);
        Shape7.setTextureSize(256, 256);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
        Shape8 = new ModelRenderer(this, 0, 0);
        Shape8.addBox(-12F, -3F, 14F, 8, 6, 2);
        Shape8.setRotationPoint(0F, 0F, 0F);
        Shape8.setTextureSize(256, 256);
        Shape8.mirror = true;
        setRotation(Shape8, 0F, 0F, 0F);
        Shape9 = new ModelRenderer(this, 0, 0);
        Shape9.addBox(-20F, -3F, 12F, 8, 6, 2);
        Shape9.setRotationPoint(0F, 0F, 0F);
        Shape9.setTextureSize(256, 256);
        Shape9.mirror = true;
        setRotation(Shape9, 0F, 0F, 0F);
        Shape10 = new ModelRenderer(this, 134, 88);
        Shape10.addBox(-26F, -3F, -10F, 2, 6, 20);
        Shape10.setRotationPoint(0F, 0F, 0F);
        Shape10.setTextureSize(256, 256);
        Shape10.mirror = true;
        setRotation(Shape10, 0F, 0F, 0F);
        Shape11 = new ModelRenderer(this, 33, 0);
        Shape11.addBox(-29F, -5.5F, -1.5F, 3, 7, 3);
        Shape11.setRotationPoint(0F, 0F, 0F);
        Shape11.setTextureSize(256, 256);
        Shape11.mirror = true;
        setRotation(Shape11, 0F, 0F, 0F);
        Shape12 = new ModelRenderer(this, 87, 45);
        Shape12.addBox(-24F, 3F, -10F, 4, 4, 20);
        Shape12.setRotationPoint(0F, 0F, 0F);
        Shape12.setTextureSize(256, 256);
        Shape12.mirror = true;
        setRotation(Shape12, 0F, 0F, 0F);
        Shape13 = new ModelRenderer(this, 29, 15);
        Shape13.addBox(-31F, -9.5F, -1.5F, 5, 4, 3);
        Shape13.setRotationPoint(0F, 0F, 0F);
        Shape13.setTextureSize(256, 256);
        Shape13.mirror = true;
        setRotation(Shape13, 0F, 0F, 0F);
        Shape14 = new ModelRenderer(this, 13, 9);
        Shape14.addBox(-24F, -3F, 10F, 4, 6, 2);
        Shape14.setRotationPoint(0F, 0F, 0F);
        Shape14.setTextureSize(256, 256);
        Shape14.mirror = true;
        setRotation(Shape14, 0F, 0F, 0F);
        Shape15 = new ModelRenderer(this, 13, 9);
        Shape15.addBox(-24F, -3F, -12F, 4, 6, 2);
        Shape15.setRotationPoint(0F, 0F, 0F);
        Shape15.setTextureSize(256, 256);
        Shape15.mirror = true;
        setRotation(Shape15, 0F, 0F, 0F);
        Shape16 = new ModelRenderer(this, 110, 45);
        Shape16.addBox(44F, 1F, -14F, 8, 6, 28);
        Shape16.setRotationPoint(0F, 0F, 0F);
        Shape16.setTextureSize(256, 256);
        Shape16.mirror = true;
        setRotation(Shape16, 0F, 0F, 0F);
        Shape17 = new ModelRenderer(this, 0, 0);
        Shape17.addBox(44F, -3F, -16F, 8, 6, 2);
        Shape17.setRotationPoint(0F, 0F, 0F);
        Shape17.setTextureSize(256, 256);
        Shape17.mirror = true;
        setRotation(Shape17, 0F, 0F, 0F);
        Shape18 = new ModelRenderer(this, 0, 0);
        Shape18.addBox(44F, -3F, 14F, 8, 6, 2);
        Shape18.setRotationPoint(0F, 0F, 0F);
        Shape18.setTextureSize(256, 256);
        Shape18.mirror = true;
        setRotation(Shape18, 0F, 0F, 0F);
        Shape19 = new ModelRenderer(this, 187, 50);
        Shape19.addBox(52F, -3F, -14F, 2, 6, 28);
        Shape19.setRotationPoint(0F, 0F, 0F);
        Shape19.setTextureSize(256, 256);
        Shape19.mirror = true;
        setRotation(Shape19, 0F, 0F, 0F);
        Shape20 = new ModelRenderer(this, 49, 0);
        Shape20.addBox(18F, -32F, -2F, 4, 35, 4);
        Shape20.setRotationPoint(0F, 0F, 0F);
        Shape20.setTextureSize(256, 256);
        Shape20.mirror = true;
        setRotation(Shape20, 0F, 0F, 0F);
        Shape21 = new ModelRenderer(this, 66, 0);
        Shape21.addBox(19F, -49F, -1F, 2, 16, 2);
        Shape21.setRotationPoint(0F, 0F, 0F);
        Shape21.setTextureSize(256, 256);
        Shape21.mirror = true;
        setRotation(Shape21, 0F, 0F, 0F);
        Shape22 = new ModelRenderer(this, 0, 9);
        Shape22.addBox(18F, -33F, -1F, 4, 1, 2);
        Shape22.setRotationPoint(0F, 0F, 0F);
        Shape22.setTextureSize(256, 256);
        Shape22.mirror = true;
        setRotation(Shape22, 0F, 0F, 0F);
        Shape23 = new ModelRenderer(this, 0, 9);
        Shape23.addBox(19F, -33F, -2F, 2, 1, 4);
        Shape23.setRotationPoint(0F, 0F, 0F);
        Shape23.setTextureSize(256, 256);
        Shape23.mirror = true;
        setRotation(Shape23, 0F, 0F, 0F);
        Shape24 = new ModelRenderer(this, 136, 37);
        Shape24.addBox(18F, -10F, -24F, 1, 1, 48);
        Shape24.setRotationPoint(0F, 0F, 0F);
        Shape24.setTextureSize(256, 256);
        Shape24.mirror = true;
        setRotation(Shape24, 0F, 0F, 0F);
        Shape25 = new ModelRenderer(this, 0, 0);
        Shape25.addBox(17F, -11F, -23F, 1, 1, 46);
        Shape25.setRotationPoint(0F, 0F, 0F);
        Shape25.setTextureSize(256, 256);
        Shape25.mirror = true;
        setRotation(Shape25, 0F, 0F, 0F);
        Shape26 = new ModelRenderer(this, 87, 80);
        Shape26.addBox(16F, -15F, -22F, 1, 4, 44);
        Shape26.setRotationPoint(0F, 0F, 0F);
        Shape26.setTextureSize(256, 256);
        Shape26.mirror = true;
        setRotation(Shape26, 0F, 0F, 0F);
        Shape27 = new ModelRenderer(this, 87, 80);
        Shape27.addBox(16F, -39F, -22F, 1, 4, 44);
        Shape27.setRotationPoint(0F, 0F, 0F);
        Shape27.setTextureSize(256, 256);
        Shape27.mirror = true;
        setRotation(Shape27, 0F, 0F, 0F);
        Shape28 = new ModelRenderer(this, 0, 66);
        Shape28.addBox(15F, -35F, -21F, 1, 20, 42);
        Shape28.setRotationPoint(0F, 0F, 0F);
        Shape28.setTextureSize(256, 256);
        Shape28.mirror = true;
        setRotation(Shape28, 0F, 0F, 0F);
        Shape29 = new ModelRenderer(this, 0, 0);
        Shape29.addBox(17F, -40F, -23F, 1, 1, 46);
        Shape29.setRotationPoint(0F, 0F, 0F);
        Shape29.setTextureSize(256, 256);
        Shape29.mirror = true;
        setRotation(Shape29, 0F, 0F, 0F);
        Shape30 = new ModelRenderer(this, 136, 37);
        Shape30.addBox(18F, -41F, -24F, 1, 1, 48);
        Shape30.setRotationPoint(0F, 0F, 0F);
        Shape30.setTextureSize(256, 256);
        Shape30.mirror = true;
        setRotation(Shape30, 0F, 0F, 0F);
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

        Shape1.render(scale);
        Shape2.render(scale);
        Shape3.render(scale);
        Shape4.render(scale);
        Shape5.render(scale);
        Shape6.render(scale);
        Shape7.render(scale);
        Shape8.render(scale);
        Shape9.render(scale);
        Shape10.render(scale);
        Shape11.render(scale);
        Shape12.render(scale);
        Shape13.render(scale);
        Shape14.render(scale);
        Shape15.render(scale);
        Shape16.render(scale);
        Shape17.render(scale);
        Shape18.render(scale);
        Shape19.render(scale);
        Shape20.render(scale);
        Shape21.render(scale);
        Shape22.render(scale);
        Shape23.render(scale);
        Shape24.render(scale);
        Shape25.render(scale);
        Shape26.render(scale);
        Shape27.render(scale);
        Shape28.render(scale);
        Shape29.render(scale);
        Shape30.render(scale);
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    }

}
