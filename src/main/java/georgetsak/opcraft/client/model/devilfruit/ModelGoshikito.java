package georgetsak.opcraft.client.model.devilfruit;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelGoshikito extends ModelBase {
    public ModelRenderer redString;
    public ModelRenderer yellowString;
    public ModelRenderer greenString;
    public ModelRenderer blueString;
    public ModelRenderer purpleString;

    public ModelGoshikito() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.greenString = new ModelRenderer(this, 0, 32);
        this.greenString.setRotationPoint(-1.8F, 13.0F, -8.1F);
        this.greenString.addBox(0.0F, 0.0F, 0.0F, 2, 2, 30, 0.0F);
        this.yellowString = new ModelRenderer(this, 34, 2);
        this.yellowString.setRotationPoint(1.7F, 13.0F, -8.1F);
        this.yellowString.addBox(0.0F, 0.0F, 0.0F, 2, 2, 30, 0.0F);
        this.purpleString = new ModelRenderer(this, 0, 64);
        this.purpleString.setRotationPoint(-8.7F, 13.0F, -8.1F);
        this.purpleString.addBox(0.0F, 0.0F, 0.0F, 2, 2, 30, 0.0F);
        this.redString = new ModelRenderer(this, 0, 0);
        this.redString.setRotationPoint(5.0F, 13.0F, -8.1F);
        this.redString.addBox(0.0F, 0.0F, 0.0F, 2, 2, 30, 0.0F);
        this.blueString = new ModelRenderer(this, 34, 34);
        this.blueString.setRotationPoint(-5.2F, 13.0F, -8.1F);
        this.blueString.addBox(0.0F, 0.0F, 0.0F, 2, 2, 30, 0.0F);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
        GL11.glRotatef(rotationPitch, 1, 0, 0);

        this.greenString.render(scale);
        this.yellowString.render(scale);
        this.purpleString.render(scale);
        this.redString.render(scale);
        this.blueString.render(scale);
    }

}
