package georgetsak.opcraft.client.model.devilfruit;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelTamaito extends ModelBase {
    private ModelRenderer Cube;

    public ModelTamaito() {
        textureWidth = 8;
        textureHeight = 8;

        Cube = new ModelRenderer(this, 0, 0);
        Cube.addBox(0F, 0F, 0F, 4, 4, 4);
        Cube.setRotationPoint(0F, 0F, 0F);
        Cube.setTextureSize(8, 8);
        Cube.mirror = true;
    }

    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        GL11.glTranslatef(-0.1f, 1.15f, 0f);
        GL11.glRotatef(headPitch, 1, 0, 0);
        GL11.glScalef(0.6F, 0.6F, 2.0F);
        Cube.render(scale);

    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    }

}
