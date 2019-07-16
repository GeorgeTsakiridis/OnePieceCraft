package georgetsak.opcraft.client.model.devilfruit;

import georgetsak.opcraft.common.entity.devilfruit.EntityFirePunch;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFireFist extends ModelBase {
	// fields
	private ModelRenderer Cube;

	public ModelFireFist() {
		textureWidth = 64;
		textureHeight = 64;
		
		Cube = new ModelRenderer(this, 0, 0);
		Cube.addBox(0F, 7F, 0F, 32, 32, 32);
		Cube.setRotationPoint(0F, 0F, 0F);
		Cube.setTextureSize(64, 64);
		Cube.mirror = true;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		if(((EntityFirePunch)(entity)).getType() == 1){
		    GL11.glTranslatef(0, 0.5f, 0);
			new ModelPunch().render(entity, f, f1, f2, f3, f4, f5);
			return;
		}

		if(((EntityFirePunch)(entity)).getType() == 2){
			new ModelShinka().render(entity, f, f1, f2, f3, f4, f5);
			return;
		}

		GL11.glScalef(0.03F, 0.03F, 0.03F);
		GL11.glTranslated(-2.5F, 32, 0);
		GL11.glScalef(2.5F, 2.5F, 2.5F);
		Cube.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
