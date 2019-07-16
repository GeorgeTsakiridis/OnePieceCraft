package georgetsak.opcraft.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSeaKing extends ModelBase {
    public ModelRenderer tail2;
    public ModelRenderer tail3;
    public ModelRenderer tail4;
    public ModelRenderer body1;
    public ModelRenderer body2;
    public ModelRenderer body3;
    public ModelRenderer head1;
    public ModelRenderer head2;
    public ModelRenderer head3;
    public ModelRenderer eye2;
    public ModelRenderer eye1;

    public ModelSeaKing() {
        this.textureWidth = 512;
        this.textureHeight = 512;
        this.body3 = new ModelRenderer(this, 169, 129);
        this.body3.setRotationPoint(-25.0F, -15.0F, -138.0F);
        this.body3.addBox(0.0F, 0.0F, 0.0F, 38, 38, 85, 0.0F);
        this.setRotateAngle(body3, 0.0F, -0.03490658503988659F, 0.0F);
        this.body1 = new ModelRenderer(this, 177, 1);
        this.body1.setRotationPoint(-27.0F, -15.0F, 31.0F);
        this.body1.addBox(0.0F, 0.0F, 0.0F, 38, 38, 85, 0.0F);
        this.setRotateAngle(body1, 0.0F, -0.05235987755982988F, 0.0F);
        this.body2 = new ModelRenderer(this, 1, 65);
        this.body2.setRotationPoint(-28.0F, -15.0F, -53.0F);
        this.body2.addBox(0.0F, 0.0F, 0.0F, 38, 38, 85, 0.0F);
        this.setRotateAngle(body2, 0.0F, 0.017453292519943295F, 0.0F);
        this.tail2 = new ModelRenderer(this, 1, 1);
        this.tail2.setRotationPoint(-13.5F, 0.5F, 179.0F);
        this.tail2.addBox(0.0F, 0.0F, 0.0F, 8, 8, 22, 0.0F);
        this.setRotateAngle(tail2, 0.0F, 0.10471975511965977F, 0.0F);
        this.head1 = new ModelRenderer(this, 345, 1);
        this.head1.setRotationPoint(-27.0F, -19.0F, -164.0F);
        this.head1.addBox(0.0F, 0.0F, 0.0F, 44, 44, 26, 0.0F);
        this.setRotateAngle(head1, 0.0F, -0.03490658503988659F, 0.0F);
        this.head2 = new ModelRenderer(this, 1, 209);
        this.head2.setRotationPoint(-5.4F, -5.0F, -158.0F);
        this.head2.addBox(-21.0F, -13.5F, 0.0F, 42, 27, 46, 0.0F);
        this.setRotateAngle(head2, 0.091106186954104F, -3.141592653589793F, 0.0F);
        this.tail4 = new ModelRenderer(this, 137, 1);
        this.tail4.setRotationPoint(-23.0F, -10.0F, 115.0F);
        this.tail4.addBox(0.0F, 0.0F, 0.0F, 28, 28, 34, 0.0F);
        this.setRotateAngle(tail4, 0.0F, -0.03490658503988659F, 0.0F);
        this.eye2 = new ModelRenderer(this, 105, 1);
        this.eye2.setRotationPoint(5.0F, -26.0F, -151.0F);
        this.eye2.addBox(0.0F, 1.0F, 0.0F, 6, 6, 6, 0.0F);
        this.setRotateAngle(eye2, 0.0F, 0.0F, -0.19198621771937624F);
        this.eye1 = new ModelRenderer(this, 137, 1);
        this.eye1.setRotationPoint(-22.0F, -26.0F, -151.0F);
        this.eye1.addBox(0.0F, 0.0F, 0.0F, 6, 6, 6, 0.0F);
        this.setRotateAngle(eye1, 0.0F, 0.0F, 0.19198621771937624F);
        this.head3 = new ModelRenderer(this, 137, 257);
        this.head3.setRotationPoint(-5.4F, 16.0F, -158.0F);
        this.head3.addBox(-21.0F, -6.0F, 0.0F, 42, 12, 46, 0.0F);
        this.setRotateAngle(head3, 0.0F, -3.14f, 0.0F);
        this.tail3 = new ModelRenderer(this, 33, 1);
        this.tail3.setRotationPoint(-19.0F, -5.0F, 148.0F);
        this.tail3.addBox(0.0F, 0.0F, 0.0F, 19, 19, 31, 0.0F);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.body1.rotateAngleY = MathHelper.sin(ageInTicks/10f + 30) / 20f;
        this.body2.rotateAngleY = MathHelper.sin(ageInTicks/10f + 25) / 20f;
        this.body3.rotateAngleY = MathHelper.sin(ageInTicks/10f + 20) / 20f;
        this.body3.render(scale);
        this.body1.render(scale);
        this.body2.render(scale);
        this.head1.render(scale);
        this.head2.rotateAngleX = 0.15f + MathHelper.sin(ageInTicks/10f) / 10f;
        this.head3.rotateAngleX = - 0.15f -MathHelper.sin(ageInTicks/10f) / 10f;
        this.head2.render(scale);
        this.head3.render(scale);
        this.tail2.rotateAngleY = MathHelper.sin(ageInTicks/10f + 15) / 10f;
        this.tail3.rotateAngleY = MathHelper.sin(ageInTicks/10f + 10) / 10f;
        this.tail4.rotateAngleY = MathHelper.sin(ageInTicks/10f + 5) / 10f;
        this.tail2.render(scale);
        this.tail3.render(scale);
        this.tail4.render(scale);
        this.eye2.render(scale);
        this.eye1.render(scale);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
