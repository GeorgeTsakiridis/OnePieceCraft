package georgetsak.opcraft.client.model.devilfruit;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSlowBeam extends ModelBase {
	// fields
	private ModelRenderer Cube;

	 public ModelSlowBeam() {
		textureWidth = 64;
		textureHeight = 64;
		
		Cube = new ModelRenderer(this, 0, 0);
		Cube.addBox(0F, 0F, 0F, 8, 8, 8);
		Cube.setRotationPoint(0F, 0F, 0F);
		Cube.setTextureSize(64, 64);
		Cube.mirror = true;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		GL11.glScalef(1F, 1F, 8.0F);
		Cube.render(f5);
	
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
