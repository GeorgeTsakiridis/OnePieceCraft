package georgetsak.opcraft.client.model.devilfruit;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelShinka extends ModelBase {
    public ModelRenderer Body;
    public ModelRenderer Neck;
    public ModelRenderer Neck2;
    public ModelRenderer Neck22;
    public ModelRenderer CubeName;
    public ModelRenderer CubeName2;
    public ModelRenderer CubeName22;
    public ModelRenderer Neck3;
    public ModelRenderer CubeName3;
    public ModelRenderer CubeName32;
    public ModelRenderer Body3;
    public ModelRenderer Body32;

    public ModelShinka() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.Body = new ModelRenderer(this, 5, 12);
        this.Body.setRotationPoint(-1.5F, 5.5F, -7.0F);
        this.Body.addBox(-1.5F, -0.7F, 0.0F, 3, 3, 15);
        this.Neck = new ModelRenderer(this, 0, 0);
        this.Neck.setRotationPoint(-1.0F, -0.2F, -12.6F);
        this.Neck.addBox(0.0F, 0.0F, 0.0F, 2, 2, 39);
        this.Body.addChild(this.Neck);
        this.Neck2 = new ModelRenderer(this, 0, 0);
        this.Neck2.setRotationPoint(0.5F, -0.5F, 0.0F);
        this.Neck2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 35);
        this.Neck.addChild(this.Neck2);
        this.Neck22 = new ModelRenderer(this, 0, 0);
        this.Neck22.setRotationPoint(-0.5F, 0.5F, 0.0F);
        this.Neck22.addBox(0.0F, 0.0F, 0.0F, 3, 1, 35);
        this.Neck.addChild(this.Neck22);
        this.CubeName = new ModelRenderer(this, 0, 0);
        this.CubeName.setRotationPoint(0.5F, -0.5F, 0.0F);
        this.CubeName.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4);
        this.setRotationAngles(this.CubeName, 0.28309239268925535F, 0.0F, 0.0F);
        this.Neck.addChild(this.CubeName);
        this.CubeName2 = new ModelRenderer(this, 0, 0);
        this.CubeName2.setRotationPoint(0.5F, 0.6F, 0.8F);
        this.CubeName2.addBox(0.0F, 1.0F, -4.0F, 1, 1, 4);
        this.setRotationAngles(this.CubeName2, -0.2617993877991494F, 0.0F, 0.0F);
        this.Neck.addChild(this.CubeName2);
        this.CubeName22 = new ModelRenderer(this, 0, 0);
        this.CubeName22.setRotationPoint(0.5F, -0.5F, 1.8F);
        this.CubeName22.addBox(0.0F, 1.0F, -4.0F, 1, 1, 3);
        this.Neck.addChild(this.CubeName22);
        this.Neck3 = new ModelRenderer(this, 0, 0);
        this.Neck3.setRotationPoint(0.5F, 0.5F, 36.4F);
        this.Neck3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
        this.Neck.addChild(this.Neck3);
        this.CubeName3 = new ModelRenderer(this, 0, 0);
        this.CubeName3.setRotationPoint(1.5F, 0.5F, 0.4F);
        this.CubeName3.addBox(0.0F, 0.0F, -3.0F, 1, 1, 3);
        this.setRotationAngles(this.CubeName3, 0.0F, 0.2792526803190927F, 0.0F);
        this.Neck.addChild(this.CubeName3);
        this.CubeName32 = new ModelRenderer(this, 0, 0);
        this.CubeName32.setRotationPoint(-0.5F, 0.5F, 0.0F);
        this.CubeName32.addBox(0.0F, 0.0F, -3.0F, 1, 1, 3);
        this.setRotationAngles(this.CubeName32, 0.0F, -0.2792526803190927F, 0.0F);
        this.Neck.addChild(this.CubeName32);
        this.Body3 = new ModelRenderer(this, 5, 12);
        this.Body3.setRotationPoint(0.5F, -0.5F, 0.5F);
        this.Body3.addBox(-1.5F, -0.7F, 0.0F, 2, 4, 14);
        this.Body.addChild(this.Body3);
        this.Body32 = new ModelRenderer(this, 5, 12);
        this.Body32.setRotationPoint(-0.5F, 0.5F, 0.5F);
        this.Body32.addBox(-1.5F, -0.7F, 0.0F, 4, 2, 13);
        this.Body.addChild(this.Body32);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
        this.Body.render(scale);
        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, rotationYaw, rotationPitch, scale);
    }

    public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
