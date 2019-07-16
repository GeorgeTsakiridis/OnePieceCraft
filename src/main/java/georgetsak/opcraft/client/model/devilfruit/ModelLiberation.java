package georgetsak.opcraft.client.model.devilfruit;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelLiberation extends ModelBase {
    public ModelRenderer Cube;
    public ModelRenderer Cube2;
    public ModelRenderer Cube3;
    public ModelRenderer Cube4;
    public ModelRenderer Cube5;
    public ModelRenderer Cube7;
    public ModelRenderer Cube8;
    public ModelRenderer Cube9;
    public ModelRenderer Cube10;
    public ModelRenderer Cube11;
    public ModelRenderer Cube12;
    public ModelRenderer Cube13;
    public ModelRenderer Cube14;

    public ModelLiberation() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.Cube = new ModelRenderer(this, 0, 0);
        this.Cube.setRotationPoint(-8.0F, -16.0F, -8.0F);
        this.Cube.addBox(0.0F, 0.0F, 0.0F, 32, 32, 32);
        this.Cube2 = new ModelRenderer(this, 0, 0);
        this.Cube2.setRotationPoint(-8.0F, 12.0F, 8.0F);
        this.Cube2.addBox(0.1F, 0.0F, 0.0F, 48, 8, 16);
        this.Cube.addChild(this.Cube2);
        this.Cube3 = new ModelRenderer(this, 0, 0);
        this.Cube3.setRotationPoint(-8.0F, 8.0F, 12.0F);
        this.Cube3.addBox(0.0F, 0.0F, 0.0F, 48, 16, 8);
        this.Cube.addChild(this.Cube3);
        this.Cube4 = new ModelRenderer(this, 0, 0);
        this.Cube4.setRotationPoint(-4.0F, 0.0F, 4.0F);
        this.Cube4.addBox(0.0F, 0.0F, 0.0F, 40, 32, 24);
        this.Cube.addChild(this.Cube4);
        this.Cube5 = new ModelRenderer(this, 0, 0);
        this.Cube5.setRotationPoint(0.0F, -4.0F, 4.0F);
        this.Cube5.addBox(0.0F, 0.0F, 0.0F, 32, 40, 24);
        this.Cube.addChild(this.Cube5);
        this.Cube7 = new ModelRenderer(this, 0, 0);
        this.Cube7.setRotationPoint(12.0F, 8.0F, -8.0F);
        this.Cube7.addBox(0.0F, 0.0F, 0.0F, 8, 16, 48);
        this.Cube.addChild(this.Cube7);
        this.Cube8 = new ModelRenderer(this, 0, 0);
        this.Cube8.setRotationPoint(8.0F, 12.0F, -8.0F);
        this.Cube8.addBox(0.0F, 0.0F, 0.1F, 16, 8, 48);
        this.Cube.addChild(this.Cube8);
        this.Cube9 = new ModelRenderer(this, 0, 0);
        this.Cube9.setRotationPoint(4.0F, 0.0F, -4.0F);
        this.Cube9.addBox(0.0F, 0.0F, 0.0F, 24, 32, 40);
        this.Cube.addChild(this.Cube9);
        this.Cube10 = new ModelRenderer(this, 0, 0);
        this.Cube10.setRotationPoint(12.0F, -8.0F, 8.0F);
        this.Cube10.addBox(0.0F, -0.1F, 0.0F, 8, 48, 16);
        this.Cube.addChild(this.Cube10);
        this.Cube11 = new ModelRenderer(this, 0, 0);
        this.Cube11.setRotationPoint(8.0F, -8.0F, 12.0F);
        this.Cube11.addBox(0.0F, 0.0F, 0.0F, 16, 48, 8);
        this.Cube.addChild(this.Cube11);
        this.Cube12 = new ModelRenderer(this, 0, 0);
        this.Cube12.setRotationPoint(4.0F, -4.0F, 0.0F);
        this.Cube12.addBox(0.0F, -0.1F, 0.0F, 24, 40, 32);
        this.Cube.addChild(this.Cube12);
        this.Cube13 = new ModelRenderer(this, 0, 0);
        this.Cube13.setRotationPoint(0.0F, 5.0F, -4.0F);
        this.Cube13.addBox(0.0F, 0.0F, 0.1F, 32, 24, 40);
        this.Cube.addChild(this.Cube13);
        this.Cube14 = new ModelRenderer(this, 0, 0);
        this.Cube14.setRotationPoint(-4.0F, 5.0F, 0.0F);
        this.Cube14.addBox(0.1F, 0.0F, 0.0F, 40, 24, 32);
        this.Cube.addChild(this.Cube14);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, rotationYaw, rotationPitch, scale);
        this.Cube.render(scale);
    }

    public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

