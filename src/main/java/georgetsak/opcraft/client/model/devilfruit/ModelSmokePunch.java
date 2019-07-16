package georgetsak.opcraft.client.model.devilfruit;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelSmokePunch extends ModelBase {
    public ModelRenderer armPart1;
    public ModelRenderer armPart2;
    public ModelRenderer hand;
    public ModelRenderer finger1Part1;
    public ModelRenderer finger1Part2;
    public ModelRenderer finger1Part3;
    public ModelRenderer finger1Part4;
    public ModelRenderer knuckel2;
    public ModelRenderer thumbpart1;
    public ModelRenderer thumbpart2;
    public ModelRenderer thumbpart3;
    public ModelRenderer knuckel1;
    public ModelRenderer finger2part1;
    public ModelRenderer finger2part2;
    public ModelRenderer finger2part3;
    public ModelRenderer finger2part4;
    public ModelRenderer knuckel3;
    public ModelRenderer finger3part1;
    public ModelRenderer finger3part2;
    public ModelRenderer finger3part3;
    public ModelRenderer finger3part4;
    public ModelRenderer knuckel4;
    public ModelRenderer finger4part1;
    public ModelRenderer finger4part2;
    public ModelRenderer finger4part3;
    public ModelRenderer finger4part4;
    public ModelRenderer knuckel5;
    
    public ModelSmokePunch() {
        this.textureWidth = 32;
        this.textureHeight = 32;

        this.armPart1 = new ModelRenderer(this, 0, 0);
        this.armPart1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.armPart1.addBox(0.0F, 0.0F, 0.0F, 4, 4, 10);
        this.armPart2 = new ModelRenderer(this, 0, 0);
        this.armPart2.setRotationPoint(0.5F, 0.2F, 10.0F);
        this.armPart2.addBox(0.0F, 0.0F, 0.0F, 3, 3, 2);
        this.armPart1.addChild(this.armPart2);
        this.hand = new ModelRenderer(this, 0, 0);
        this.hand.setRotationPoint(-0.5F, 0.0F, 1.8F);
        this.hand.addBox(0.0F, 0.0F, 0.0F, 4, 2, 3);
        this.setRotationAngles(this.hand, 0.05235987755982988F, 0.0F, 0.0F);
        this.armPart2.addChild(this.hand);
        this.finger1Part1 = new ModelRenderer(this, 0, 0);
        this.finger1Part1.setRotationPoint(-0.1F, 0.5F, 2.1F);
        this.finger1Part1.addBox(0.0F, -1.0F, 0.0F, 1, 1, 2);
        this.setRotationAngles(this.finger1Part1, -0.7925540106674639F, 0.0F, 0.0F);
        this.hand.addChild(this.finger1Part1);
        this.finger1Part2 = new ModelRenderer(this, 0, 0);
        this.finger1Part2.setRotationPoint(0.0F, -0.4F, 1.2F);
        this.finger1Part2.addBox(0.0F, -1.0F, 0.0F, 1, 1, 2);
        this.setRotationAngles(this.finger1Part2, -0.9056513382072132F, 0.0F, 0.0F);
        this.finger1Part1.addChild(this.finger1Part2);
        this.finger1Part3 = new ModelRenderer(this, 0, 0);
        this.finger1Part3.setRotationPoint(0.0F, -0.4F, 1.2F);
        this.finger1Part3.addBox(0.0F, -1.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.finger1Part3, -0.9906488541372094F, 0.0F, 0.0F);
        this.finger1Part2.addChild(this.finger1Part3);
        this.finger1Part4 = new ModelRenderer(this, 0, 0);
        this.finger1Part4.setRotationPoint(0.0F, -0.6F, 0.2F);
        this.finger1Part4.addBox(0.0F, -1.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.finger1Part4, -1.1604693823667744F, 0.0F, 0.0F);
        this.finger1Part3.addChild(this.finger1Part4);
        this.knuckel2 = new ModelRenderer(this, 0, 0);
        this.knuckel2.setRotationPoint(0.0F, -0.3F, -0.7F);
        this.knuckel2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.knuckel2, 0.7853981633974483F, 0.0F, 0.0F);
        this.finger1Part1.addChild(this.knuckel2);
        this.thumbpart1 = new ModelRenderer(this, 0, 0);
        this.thumbpart1.setRotationPoint(-0.6F, 1.0F, 0.8F);
        this.thumbpart1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
        this.setRotationAngles(this.thumbpart1, -0.9056513382072132F, 0.0F, 0.31136674121894947F);
        this.hand.addChild(this.thumbpart1);
        this.thumbpart2 = new ModelRenderer(this, 0, 0);
        this.thumbpart2.setRotationPoint(0.0F, 0.0F, 2.0F);
        this.thumbpart2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.thumbpart2, 0.0F, 1.3018410251467043F, 0.0F);
        this.thumbpart1.addChild(this.thumbpart2);
        this.thumbpart3 = new ModelRenderer(this, 0, 0);
        this.thumbpart3.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.thumbpart3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.thumbpart3, 0.0F, 0.5942845969882637F, 0.0F);
        this.thumbpart2.addChild(this.thumbpart3);
        this.knuckel1 = new ModelRenderer(this, 0, 0);
        this.knuckel1.setRotationPoint(0.7F, 0.0F, -0.7F);
        this.knuckel1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.knuckel1, 0.0F, -0.7641051252178287F, 0.0F);
        this.thumbpart1.addChild(this.knuckel1);
        this.finger2part1 = new ModelRenderer(this, 0, 0);
        this.finger2part1.setRotationPoint(1.0F, 0.3F, 2.4F);
        this.finger2part1.addBox(0.0F, -1.0F, 0.0F, 1, 1, 2);
        this.setRotationAngles(this.finger2part1, -0.7925540106674639F, 0.0F, 0.0F);
        this.hand.addChild(this.finger2part1);
        this.finger2part2 = new ModelRenderer(this, 0, 0);
        this.finger2part2.setRotationPoint(0.0F, -0.5F, 1.2F);
        this.finger2part2.addBox(0.0F, -1.0F, 0.0F, 1, 1, 2);
        this.setRotationAngles(this.finger2part2, -0.9056513382072132F, 0.0F, 0.0F);
        this.finger2part1.addChild(this.finger2part2);
        this.finger2part3 = new ModelRenderer(this, 0, 0);
        this.finger2part3.setRotationPoint(0.0F, -0.4F, 1.2F);
        this.finger2part3.addBox(0.0F, -1.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.finger2part3, -0.9906488541372094F, 0.0F, 0.0F);
        this.finger2part2.addChild(this.finger2part3);
        this.finger2part4 = new ModelRenderer(this, 0, 0);
        this.finger2part4.setRotationPoint(0.0F, -0.6F, 0.2F);
        this.finger2part4.addBox(0.0F, -1.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.finger2part4, -1.1604693823667744F, 0.0F, 0.0F);
        this.finger2part3.addChild(this.finger2part4);
        this.knuckel3 = new ModelRenderer(this, 0, 0);
        this.knuckel3.setRotationPoint(0.0F, -0.3F, -0.7F);
        this.knuckel3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.knuckel3, 0.7853981633974483F, 0.0F, 0.0F);
        this.finger2part1.addChild(this.knuckel3);
        this.finger3part1 = new ModelRenderer(this, 0, 0);
        this.finger3part1.setRotationPoint(2.2F, 0.6F, 2.2F);
        this.finger3part1.addBox(0.0F, -1.0F, 0.0F, 1, 1, 2);
        this.setRotationAngles(this.finger3part1, -0.7925540106674639F, 0.0F, 0.0F);
        this.hand.addChild(this.finger3part1);
        this.finger3part2 = new ModelRenderer(this, 0, 0);
        this.finger3part2.setRotationPoint(0.0F, -0.4F, 1.2F);
        this.finger3part2.addBox(0.0F, -1.0F, 0.0F, 1, 1, 2);
        this.setRotationAngles(this.finger3part2, -0.9056513382072132F, 0.0F, 0.0F);
        this.finger3part1.addChild(this.finger3part2);
        this.finger3part3 = new ModelRenderer(this, 0, 0);
        this.finger3part3.setRotationPoint(0.0F, -0.4F, 1.2F);
        this.finger3part3.addBox(0.0F, -1.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.finger3part3, -0.9906488541372094F, 0.0F, 0.0F);
        this.finger3part2.addChild(this.finger3part3);
        this.finger3part4 = new ModelRenderer(this, 0, 0);
        this.finger3part4.setRotationPoint(0.0F, -0.6F, 0.2F);
        this.finger3part4.addBox(0.0F, -1.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.finger3part4, -1.1604693823667744F, 0.0F, 0.0F);
        this.finger3part3.addChild(this.finger3part4);
        this.knuckel4 = new ModelRenderer(this, 0, 0);
        this.knuckel4.setRotationPoint(0.0F, -0.3F, -0.7F);
        this.knuckel4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.knuckel4, 0.7853981633974483F, 0.0F, 0.0F);
        this.finger3part1.addChild(this.knuckel4);
        this.finger4part1 = new ModelRenderer(this, 0, 0);
        this.finger4part1.setRotationPoint(3.3F, 0.7F, 2.6F);
        this.finger4part1.addBox(0.0F, -1.0F, 0.0F, 1, 1, 2);
        this.setRotationAngles(this.finger4part1, -1.5566592024643198F, 0.0F, 0.0F);
        this.hand.addChild(this.finger4part1);
        this.finger4part2 = new ModelRenderer(this, 0, 0);
        this.finger4part2.setRotationPoint(0.0F, -0.4F, 1.2F);
        this.finger4part2.addBox(0.0F, -1.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.finger4part2, -0.9339256534473936F, 0.0F, 0.0F);
        this.finger4part1.addChild(this.finger4part2);
        this.finger4part3 = new ModelRenderer(this, 0, 0);
        this.finger4part3.setRotationPoint(0.0F, -0.2F, 0.4F);
        this.finger4part3.addBox(0.0F, -1.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.finger4part3, -0.5942845969882637F, 0.0F, 0.0F);
        this.finger4part2.addChild(this.finger4part3);
        this.finger4part4 = new ModelRenderer(this, 0, 0);
        this.finger4part4.setRotationPoint(0.0F, -0.6F, 0.2F);
        this.finger4part4.addBox(0.0F, -1.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.finger4part4, -1.1604693823667744F, 0.0F, 0.0F);
        this.finger4part3.addChild(this.finger4part4);
        this.knuckel5 = new ModelRenderer(this, 0, 0);
        this.knuckel5.setRotationPoint(0.0F, -0.3F, -0.7F);
        this.knuckel5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.setRotationAngles(this.knuckel5, 0.7853981633974483F, 0.0F, 0.0F);
        this.finger4part1.addChild(this.knuckel5);
    }

    public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
        GL11.glRotatef(180f, 0f, 1f, 0f);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        GL11.glTranslatef(-0.1f, 0.3f, -0.4f);
        this.armPart1.render(scale);
        GL11.glScalef(1.0f, 1.0f, 1.0f);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
