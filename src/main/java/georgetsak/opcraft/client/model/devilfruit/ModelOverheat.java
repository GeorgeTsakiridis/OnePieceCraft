package georgetsak.opcraft.client.model.devilfruit;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelOverheat extends ModelBase {
    private ModelRenderer Cube;

    public ModelOverheat() {
        textureWidth = 64;
        textureHeight = 32;

        Cube = new ModelRenderer(this, 0, 0);
        Cube.addBox(0F, 0F, -320F, 8, 8, 320);
        Cube.setRotationPoint(0F, 0F, 0F);
        Cube.setTextureSize(64, 64);
        Cube.mirror = true;
    }

    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        GL11.glRotatef(headPitch, 1, 0, 0);
        Cube.render(scale);

    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
    
}
