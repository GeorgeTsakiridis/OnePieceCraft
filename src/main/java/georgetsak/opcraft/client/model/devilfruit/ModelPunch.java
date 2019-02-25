package georgetsak.opcraft.client.model.devilfruit;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPunch extends ModelBase {
    public ModelRenderer Armpart1;
    public ModelRenderer Armpart2;
    public ModelRenderer Hand;
    public ModelRenderer Finger1part1;
    public ModelRenderer Finger1part2;
    public ModelRenderer Finger1part3;
    public ModelRenderer Finger1part4;
    public ModelRenderer thumbpart1;
    public ModelRenderer thumbpart2;
    public ModelRenderer thumbpart3;
    public ModelRenderer Finger2part1;
    public ModelRenderer Finger2part2;
    public ModelRenderer Finger2part3;
    public ModelRenderer Finger2part4;
    public ModelRenderer Finger3part1;
    public ModelRenderer Finger3part2;
    public ModelRenderer Finger3part3;
    public ModelRenderer Finger3part4;
    public ModelRenderer Finger4part1;
    public ModelRenderer Finger4part2;
    public ModelRenderer Finger4part3;
    public ModelRenderer Finger4part4;

    public ModelPunch(){
        this.textureWidth = 32;
        this.textureHeight = 32;

        this.Armpart1 = new ModelRenderer(this, 0, 0);
        this.Armpart1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Armpart1.addBox(0.0F, 0.0F, 0.0F, 4, 4, 10);
        this.Armpart2 = new ModelRenderer(this, 0, 0);
        this.Armpart2.setRotationPoint(0.5F, 0.2F, 10.0F);
        this.Armpart2.addBox(0.0F, 0.0F, 0.0F, 3, 3, 2);
        this.Armpart1.addChild(this.Armpart2);
        this.Hand = new ModelRenderer(this, 0, 0);
        this.Hand.setRotationPoint(-0.5F, 0.0F, 1.8F);
        this.Hand.addBox(0.0F, 0.0F, 0.0F, 4, 2, 3);
        this.setRotationAngles(this.Hand, 0.05235987755982988F, 0.0F, 0.0F);
        this.Armpart2.addChild(this.Hand);
        this.Finger1part1 = new ModelRenderer(this, 0, 0);
        this.Finger1part1.setRotationPoint(-0.1F, 0.5F, 2.1F);
        this.Finger1part1.addBox(0.0F, -1.0F, 0.0F, 1, 1, 2);
        this.setRotationAngles(this.Finger1part1, -0.7925540106674639F, 0.0F, 0.0F);
        this.Hand.addChild(this.Finger1part1);
        this.Finger1part2 = new ModelRenderer(this, 0, 0);
        this.Finger1part2.setRotationPoint(0.0F, -0.4F, 1.2F);
        this.Finger1part2.addBox(0.0F, -1.0F, 0.0F, 1, 1, 2);
        this.setRotationAngles(this.Finger1part2, -0.9056513382072132F, 0.0F, 0.0F);
        this.Finger1part1.addChild(this.Finger1part2);
        this.Finger1part3 = new ModelRenderer(this, 0, 0);
        this.Finger1part3.setRotationPoint(0.0F, -0.4F, 1.2F);
        this.Finger1part3.addBox(0.0F, -1.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.Finger1part3, -0.9906488541372094F, 0.0F, 0.0F);
        this.Finger1part2.addChild(this.Finger1part3);
        this.Finger1part4 = new ModelRenderer(this, 0, 0);
        this.Finger1part4.setRotationPoint(0.0F, -0.6F, 0.2F);
        this.Finger1part4.addBox(0.0F, -1.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.Finger1part4, -1.1604693823667744F, 0.0F, 0.0F);
        this.Finger1part3.addChild(this.Finger1part4);
        this.thumbpart1 = new ModelRenderer(this, 0, 0);
        this.thumbpart1.setRotationPoint(-0.6F, 1.0F, 0.8F);
        this.thumbpart1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
        this.setRotationAngles(this.thumbpart1, -0.9056513382072132F, 0.0F, 0.31136674121894947F);
        this.Hand.addChild(this.thumbpart1);
        this.thumbpart2 = new ModelRenderer(this, 0, 0);
        this.thumbpart2.setRotationPoint(0.0F, 0.0F, 2.0F);
        this.thumbpart2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.thumbpart2, 0.0F, 0.5942845969882637F, 0.0F);
        this.thumbpart1.addChild(this.thumbpart2);
        this.thumbpart3 = new ModelRenderer(this, 0, 0);
        this.thumbpart3.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.thumbpart3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.thumbpart3, 0.0F, 0.5942845969882637F, 0.0F);
        this.thumbpart2.addChild(this.thumbpart3);
        this.Finger2part1 = new ModelRenderer(this, 0, 0);
        this.Finger2part1.setRotationPoint(1.0F, 0.0F, 1.7F);
        this.Finger2part1.addBox(0.0F, -1.0F, 0.0F, 1, 1, 3);
        this.setRotationAngles(this.Finger2part1, -0.7925540106674639F, 0.0F, 0.0F);
        this.Hand.addChild(this.Finger2part1);
        this.Finger2part2 = new ModelRenderer(this, 0, 0);
        this.Finger2part2.setRotationPoint(0.0F, -0.4F, 2.2F);
        this.Finger2part2.addBox(0.0F, -1.0F, 0.0F, 1, 1, 2);
        this.setRotationAngles(this.Finger2part2, -0.9056513382072132F, 0.0F, 0.0F);
        this.Finger2part1.addChild(this.Finger2part2);
        this.Finger2part3 = new ModelRenderer(this, 0, 0);
        this.Finger2part3.setRotationPoint(0.0F, -0.4F, 1.2F);
        this.Finger2part3.addBox(0.0F, -1.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.Finger2part3, -0.9906488541372094F, 0.0F, 0.0F);
        this.Finger2part2.addChild(this.Finger2part3);
        this.Finger2part4 = new ModelRenderer(this, 0, 0);
        this.Finger2part4.setRotationPoint(0.0F, -0.6F, 0.2F);
        this.Finger2part4.addBox(0.0F, -1.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.Finger2part4, -1.1604693823667744F, 0.0F, 0.0F);
        this.Finger2part3.addChild(this.Finger2part4);
        this.Finger3part1 = new ModelRenderer(this, 0, 0);
        this.Finger3part1.setRotationPoint(2.2F, 0.5F, 2.1F);
        this.Finger3part1.addBox(0.0F, -1.0F, 0.0F, 1, 1, 2);
        this.setRotationAngles(this.Finger3part1, -0.7925540106674639F, 0.0F, 0.0F);
        this.Hand.addChild(this.Finger3part1);
        this.Finger3part2 = new ModelRenderer(this, 0, 0);
        this.Finger3part2.setRotationPoint(0.0F, -0.4F, 1.2F);
        this.Finger3part2.addBox(0.0F, -1.0F, 0.0F, 1, 1, 2);
        this.setRotationAngles(this.Finger3part2, -0.9056513382072132F, 0.0F, 0.0F);
        this.Finger3part1.addChild(this.Finger3part2);
        this.Finger3part3 = new ModelRenderer(this, 0, 0);
        this.Finger3part3.setRotationPoint(0.0F, -0.4F, 1.2F);
        this.Finger3part3.addBox(0.0F, -1.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.Finger3part3, -0.9906488541372094F, 0.0F, 0.0F);
        this.Finger3part2.addChild(this.Finger3part3);
        this.Finger3part4 = new ModelRenderer(this, 0, 0);
        this.Finger3part4.setRotationPoint(0.0F, -0.6F, 0.2F);
        this.Finger3part4.addBox(0.0F, -1.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.Finger3part4, -1.1604693823667744F, 0.0F, 0.0F);
        this.Finger3part3.addChild(this.Finger3part4);
        this.Finger4part1 = new ModelRenderer(this, 0, 0);
        this.Finger4part1.setRotationPoint(3.3F, 0.8F, 2.0F);
        this.Finger4part1.addBox(0.0F, -1.0F, 0.0F, 1, 1, 2);
        this.setRotationAngles(this.Finger4part1, -0.7925540106674639F, 0.0F, 0.0F);
        this.Hand.addChild(this.Finger4part1);
        this.Finger4part2 = new ModelRenderer(this, 0, 0);
        this.Finger4part2.setRotationPoint(0.0F, -0.4F, 1.2F);
        this.Finger4part2.addBox(0.0F, -1.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.Finger4part2, -0.9056513382072132F, 0.0F, 0.0F);
        this.Finger4part1.addChild(this.Finger4part2);
        this.Finger4part3 = new ModelRenderer(this, 0, 0);
        this.Finger4part3.setRotationPoint(0.0F, -0.2F, 0.4F);
        this.Finger4part3.addBox(0.0F, -1.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.Finger4part3, -0.5942845969882637F, 0.0F, 0.0F);
        this.Finger4part2.addChild(this.Finger4part3);
        this.Finger4part4 = new ModelRenderer(this, 0, 0);
        this.Finger4part4.setRotationPoint(0.0F, -0.6F, 0.2F);
        this.Finger4part4.addBox(0.0F, -1.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.Finger4part4, -1.1604693823667744F, 0.0F, 0.0F);
        this.Finger4part3.addChild(this.Finger4part4);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
        this.Armpart1.rotateAngleY = (float)Math.PI;
        this.Armpart1.render(scale);
    }

    public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
