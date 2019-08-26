package georgetsak.opcraft.client.model;

import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class  ModelHomieTree extends ModelBase {

    private final ModelRenderer body;
    private final ModelRenderer legL;
    private final ModelRenderer legR;
    private final ModelRenderer root;
    private final ModelRenderer branch1;
    private final ModelRenderer branch2;
    private final ModelRenderer branch3;
    private final ModelRenderer branch4;
    private final ModelRenderer leaves1;
    private final ModelRenderer leaves2;
    private final ModelRenderer leaves3;

    public ModelHomieTree() {
        textureWidth = 256;
        textureHeight = 256;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 9.0F, 0.0F);
        body.cubeList.add(new ModelBox(body, 74, 83, -5.0F, -17.0F, -5.0F, 10, 32, 9, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 51, 92, 4.0F, -4.0F, -1.0F, 6, 1, 1, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 56, 84, 4.0F, -4.0F, -2.0F, 2, 1, 3, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 6, 77, -4.0F, -17.0F, -6.0F, 8, 32, 11, 0.0F, false));

        legL = new ModelRenderer(this);
        legL.setRotationPoint(0.0F, 17.0F, 2.0F);
        setRotationAngle(legL, -0.7854F, 0.0F, 0.0F);
        legL.cubeList.add(new ModelBox(legL, 105, 4, -3.0F, -2.636F, 0.5355F, 6, 6, 7, 0.0F, false));
        legL.cubeList.add(new ModelBox(legL, 62, 3, -2.0F, -1.636F, 2.5355F, 4, 4, 8, 0.0F, false));

        legR = new ModelRenderer(this);
        legR.setRotationPoint(1.0F, 20.0F, -2.0F);
        setRotationAngle(legR, 0.6981F, -1.0472F, 0.0F);
        legR.cubeList.add(new ModelBox(legR, 42, 23, -2.7679F, -2.7364F, -8.1417F, 6, 5, 7, 0.0F, false));
        legR.cubeList.add(new ModelBox(legR, 6, 21, -0.7679F, -1.7364F, -12.1417F, 3, 3, 11, 0.0F, false));

        root = new ModelRenderer(this);
        root.setRotationPoint(-5.0F, 22.0F, 0.0F);
        root.cubeList.add(new ModelBox(root, 33, 5, -3.0F, -2.0F, -3.0F, 3, 4, 6, 0.0F, false));
        root.cubeList.add(new ModelBox(root, 6, 4, -7.0F, -1.0F, -2.0F, 7, 3, 4, 0.0F, false));

        branch1 = new ModelRenderer(this);
        branch1.setRotationPoint(0.0F, 2.0F, 4.0F);
        setRotationAngle(branch1, 0.6109F, 0.0F, 0.0F);
        branch1.cubeList.add(new ModelBox(branch1, 196, 1, -2.0F, -2.273F, 0.1047F, 4, 4, 14, 0.0F, false));

        branch2 = new ModelRenderer(this);
        branch2.setRotationPoint(1.0F, -2.0F, -3.0F);
        setRotationAngle(branch2, -0.6981F, -0.6109F, 0.0F);
        branch2.cubeList.add(new ModelBox(branch2, 153, 4, -2.0984F, -2.0312F, -13.9656F, 4, 4, 14, 0.0F, false));

        branch3 = new ModelRenderer(this);
        branch3.setRotationPoint(-3.0F, -3.0F, 0.0F);
        setRotationAngle(branch3, 0.0F, -1.5708F, 0.9599F);
        branch3.cubeList.add(new ModelBox(branch3, 125, 18, -2.0F, -1.9709F, 0.1622F, 4, 4, 14, 0.0F, false));

        branch4 = new ModelRenderer(this);
        branch4.setRotationPoint(3.0F, -5.0F, -1.0F);
        setRotationAngle(branch4, 0.0F, -1.5708F, 2.7925F);
        branch4.cubeList.add(new ModelBox(branch4, 76, 19, -2.0F, -2.225F, 0.2623F, 4, 4, 14, 0.0F, false));

        leaves1 = new ModelRenderer(this);
        leaves1.setRotationPoint(1.0F, -2.0F, -3.0F);
        setRotationAngle(leaves1, -0.4363F, -3.0543F, 0.0F);
        leaves1.cubeList.add(new ModelBox(leaves1, 123, 86, -14.7423F, -25.2098F, -16.6415F, 32, 17, 29, 0.0F, false));

        leaves2 = new ModelRenderer(this);
        leaves2.setRotationPoint(0.0F, 2.0F, 4.0F);
        setRotationAngle(leaves2, 0.7854F, -3.0543F, 0.0F);
        leaves2.cubeList.add(new ModelBox(leaves2, 87, 41, -11.3486F, -24.626F, -7.7387F, 23, 12, 24, 0.0F, false));

        leaves3 = new ModelRenderer(this);
        leaves3.setRotationPoint(3.0F, -5.0F, -1.0F);
        setRotationAngle(leaves3, 0.7854F, 1.4835F, 1.6581F);
        leaves3.cubeList.add(new ModelBox(leaves3, 4, 43, -9.083F, -23.5785F, -7.7017F, 19, 12, 17, 0.0F, false));

        branch1RotX = branch1.rotateAngleX;
        branch1RotY = branch1.rotateAngleY;
        branch1RotZ = branch1.rotateAngleZ;
        branch2RotX = branch2.rotateAngleX;
        branch2RotY = branch2.rotateAngleY;
        branch2RotZ = branch2.rotateAngleZ;
        branch3RotX = branch3.rotateAngleX;
        branch3RotY = branch3.rotateAngleY;
        branch3RotZ = branch3.rotateAngleZ;
        branch4RotX = branch4.rotateAngleX;
        branch4RotY = branch4.rotateAngleY;
        branch4RotZ = branch4.rotateAngleZ;
        leaves1RotX = leaves1.rotateAngleX;
        leaves1RotY = leaves1.rotateAngleY;
        leaves1RotZ = leaves1.rotateAngleZ;
        leaves2RotX = leaves2.rotateAngleX;
        leaves2RotY = leaves2.rotateAngleY;
        leaves2RotZ = leaves2.rotateAngleZ;
        leaves3RotX = leaves3.rotateAngleX;
        leaves3RotY = leaves3.rotateAngleY;
        leaves3RotZ = leaves3.rotateAngleZ;
        legLRotY = legL.rotateAngleY;
        legLRotZ = legL.rotateAngleZ;
        legRRotY = legR.rotateAngleY;
        legRRotZ = legR.rotateAngleZ;
    }

    private float branch1RotX;
    private float branch1RotY;
    private float branch1RotZ;
    private float branch2RotX;
    private float branch2RotY;
    private float branch2RotZ;
    private float branch3RotX;
    private float branch3RotY;
    private float branch3RotZ;
    private float branch4RotX;
    private float branch4RotY;
    private float branch4RotZ;
    private float leaves1RotX;
    private float leaves1RotY;
    private float leaves1RotZ;
    private float leaves2RotX;
    private float leaves2RotY;
    private float leaves2RotZ;
    private float leaves3RotX;
    private float leaves3RotY;
    private float leaves3RotZ;

    private float legLRotY;
    private float legLRotZ;
    private float legRRotY;
    private float legRRotZ;

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
        float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);

        //this.body.rotateAngleY = netHeadYaw * 0.017453292F;

        branch1.rotateAngleX = branch1RotX + MathHelper.sin(ageInTicks * 0.02f)*0.05f;
        branch1.rotateAngleY = branch1RotY + MathHelper.cos(ageInTicks * 0.04f)*0.05f;
        branch1.rotateAngleZ = branch1RotZ + MathHelper.sin(ageInTicks * 0.01f + 10f)*0.05f;

        leaves2.rotateAngleX = leaves2RotX - MathHelper.sin(ageInTicks * 0.02f)*0.05f;
        leaves2.rotateAngleY = leaves2RotY + MathHelper.cos(ageInTicks * 0.04f)*0.05f;
        leaves2.rotateAngleZ = leaves2RotZ + MathHelper.sin(ageInTicks * 0.01f + 10f)*0.05f;

        branch2.rotateAngleX = branch2RotX - MathHelper.cos(ageInTicks * 0.02f + 20f)*0.05f;
        branch2.rotateAngleY = branch2RotY - MathHelper.sin(ageInTicks * 0.04f + 20f)*0.05f;
        branch2.rotateAngleZ = branch2RotZ - MathHelper.cos(ageInTicks * 0.01f + 30f)*0.05f;

        leaves1.rotateAngleX = leaves1RotX + MathHelper.cos(ageInTicks * 0.02f + 20f)*0.05f;
        leaves1.rotateAngleY = leaves1RotY - MathHelper.sin(ageInTicks * 0.04f + 20f)*0.05f;
        leaves1.rotateAngleZ = leaves1RotZ - MathHelper.cos(ageInTicks * 0.01f + 30f)*0.05f;

        branch3.rotateAngleX = branch3RotX - MathHelper.cos(ageInTicks * 0.02f + 30f)*0.05f;
        branch3.rotateAngleY = branch3RotY - MathHelper.sin(ageInTicks * 0.04f + 30f)*0.05f;
        branch3.rotateAngleZ = branch3RotZ - MathHelper.cos(ageInTicks * 0.01f + 40f)*0.05f;

        branch4.rotateAngleX = branch4RotX - MathHelper.cos(ageInTicks * 0.02f + 40f)*0.05f;
        branch4.rotateAngleY = branch4RotY - MathHelper.sin(ageInTicks * 0.04f + 40f)*0.05f;
        branch4.rotateAngleZ = branch4RotZ - MathHelper.cos(ageInTicks * 0.01f + 50f)*0.05f;

        leaves3.rotateAngleX = leaves3RotX + MathHelper.cos(ageInTicks * 0.02f + 40f)*0.05f;
        leaves3.rotateAngleY = leaves3RotY - MathHelper.sin(ageInTicks * 0.04f + 40f)*0.05f;
        leaves3.rotateAngleZ = leaves3RotZ - MathHelper.cos(ageInTicks * 0.01f + 50f)*0.05f;

        float f3 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F) * 0.2F) * limbSwingAmount;
        float f7 = Math.abs(MathHelper.sin(limbSwing * 0.6662F) * 0.2F) * limbSwingAmount;

        legL.rotateAngleY = legLRotY + f3;
        legL.rotateAngleZ = legLRotZ + f7;

        legR.rotateAngleY = legRRotY - f3;
        legR.rotateAngleZ = legRRotZ - f7;

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GlStateManager.scale(3f, 3f, 3f);
        GlStateManager.translate(0f,-1f,0f);
        GlStateManager.rotate(90f,0f,1f,0f);
        body.render(f5);
        legL.render(f5);
        legR.render(f5);
        root.render(f5);
        branch1.render(f5);
        branch2.render(f5);
        branch3.render(f5);
        branch4.render(f5);
        leaves1.render(f5);
        leaves2.render(f5);
        leaves3.render(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

}