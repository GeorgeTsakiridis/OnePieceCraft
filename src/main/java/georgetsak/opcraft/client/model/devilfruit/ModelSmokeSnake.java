package georgetsak.opcraft.client.model.devilfruit;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelSmokeSnake extends ModelBase {
public ModelRenderer body;
public ModelRenderer body2;
public ModelRenderer body3;
public ModelRenderer body32;
public ModelRenderer neck;
public ModelRenderer neck2;
public ModelRenderer head;
public ModelRenderer head2;

public ModelSmokeSnake() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 8.0F, 4.0F);
        this.body.addBox(-8.0F, 8.0F, -8.0F, 13, 8, 8);
        this.body2 = new ModelRenderer(this, 0, 13);
        this.body2.setRotationPoint(4.0F, 0.5F, 0.5F);
        this.body2.addBox(0.0F, 8.0F, -8.0F, 15, 7, 7);
        this.setRotationAngles(this.body2, 0.0F, 0.02827433396553192F, 0.0F);
        this.body.addChild(this.body2);
        this.body3 = new ModelRenderer(this, 2, 52);
        this.body3.setRotationPoint(12.0F, 0.5F, 0.5F);
        this.body3.addBox(0.0F, 8.0F, -8.0F, 13, 6, 6);
        this.setRotationAngles(this.body3, 0.0F, -0.20943951023931953F, 0.0F);
        this.body2.addChild(this.body3);
        this.body32 = new ModelRenderer(this, 0, 48);
        this.body32.setRotationPoint(12.0F, 0.5F, 0.5F);
        this.body32.addBox(0.0F, 8.0F, -8.0F, 13, 5, 5);
        this.setRotationAngles(this.body32, 0.0F, 0.40142572795869574F, 0.0F);
        this.body3.addChild(this.body32);
        this.neck = new ModelRenderer(this, 0, 50);
        this.neck.setRotationPoint(-15.0F, 2.0F, -7.5F);
        this.neck.addBox(0.0F, 0.0F, 0.0F, 13, 7, 7);
        this.setRotationAngles(this.neck, 0.0F, 0.0F, 0.5660102817480832F);
        this.body.addChild(this.neck);
        this.neck2 = new ModelRenderer(this, 0, 52);
        this.neck2.setRotationPoint(-5.8F, -2.3F, 0.5F);
        this.neck2.addBox(0.0F, 0.0F, 0.0F, 9, 6, 6);
        this.setRotationAngles(this.neck2, 0.0F, 0.0F, 0.39618975351851826F);
        this.neck.addChild(this.neck2);
        this.head = new ModelRenderer(this, 22, 28);
        this.head.setRotationPoint(2.0F, 0.5F, -1.5F);
        this.head.addBox(0.0F, 0.0F, 0.0F, 12, 5, 9);
        this.setRotationAngles(this.head, 0.0F, 0.0F, 2.1792181146927643F);
        this.neck2.addChild(this.head);
        this.head2 = new ModelRenderer(this, 0, 0);
        this.head2.setRotationPoint(1.0F, 1.0F, 1.5F);
        this.head2.addBox(-10.0F, 0.0F, 0.0F, 10, 3, 6);
        this.setRotationAngles(this.head2, 0.0F, 0.0F, 2.660405250983224F);
        this.head.addChild(this.head2);
        }

@Override
public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
        GL11.glScalef(0.8f, 0.8f, 0.8f);
        GL11.glTranslatef(0.0f, 0.0f, 0.0f);
        this.body.render(scale);
        GL11.glScalef(1f, 1f, 1f);
        }

public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
        }
        }
