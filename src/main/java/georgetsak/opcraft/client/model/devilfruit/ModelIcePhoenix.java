package georgetsak.opcraft.client.model.devilfruit;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * onepiececraft.IcePhoenix by Unknown
 */
@SideOnly(Side.CLIENT)
public class ModelIcePhoenix extends ModelBase {
public ModelRenderer Body;
public ModelRenderer Body2;
public ModelRenderer Body3;
public ModelRenderer WingL1;
public ModelRenderer WingL2;
public ModelRenderer WingBigL2;
public ModelRenderer WingL3;
public ModelRenderer WingBigL3;
public ModelRenderer WingBigL1;
public ModelRenderer WingR1;
public ModelRenderer WingR2;
public ModelRenderer WingBigR2;
public ModelRenderer WingR3;
public ModelRenderer WingBigR3;
public ModelRenderer WingBigR;
public ModelRenderer Tail;
public ModelRenderer Tail2;
public ModelRenderer Neck;
public ModelRenderer Head;
public ModelRenderer Beak;
public ModelRenderer Beak2;

public ModelIcePhoenix() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.Body = new ModelRenderer(this, 3, 21);
        this.Body.setRotationPoint(-1.5F, 5.5F, -7.0F);
        this.Body.addBox(-1.5F, -0.7F, 0.0F, 3, 2, 11);
        this.Body2 = new ModelRenderer(this, 18, 9);
        this.Body2.setRotationPoint(-1.0F, -0.7F, 1.0F);
        this.Body2.addBox(-1.5F, -1.0F, 0.0F, 5, 4, 6);
        this.setRotationAngles(this.Body2, 0.08726646259971647F, 0.0F, 0.0F);
        this.Body.addChild(this.Body2);
        this.Body3 = new ModelRenderer(this, 20, 1);
        this.Body3.setRotationPoint(0.2F, 0.0F, 5.7F);
        this.Body3.addBox(-1.5F, -1.0F, 0.0F, 5, 4, 3);
        this.setRotationAngles(this.Body3, -0.17453292519943295F, 0.0F, 0.0F);
        this.Body2.addChild(this.Body3);
        this.WingL1 = new ModelRenderer(this, 37, 31);
        this.WingL1.setRotationPoint(-2.0F, -0.1F, 1.2F);
        this.WingL1.addBox(0.0F, -0.5F, 0.0F, 7, 1, 1);
        this.setRotationAngles(this.WingL1, -0.17453292519943295F, 0.0F, -2.96705972839036F);
        this.Body.addChild(this.WingL1);
        this.WingL2 = new ModelRenderer(this, 37, 31);
        this.WingL2.setRotationPoint(6.5F, 0.0F, 0.0F);
        this.WingL2.addBox(0.0F, -0.5F, 0.0F, 6, 1, 1);
        this.setRotationAngles(this.WingL2, 0.0F, -0.4363323129985824F, 0.0F);
        this.WingL1.addChild(this.WingL2);
        this.WingBigL2 = new ModelRenderer(this, 32, 12);
        this.WingBigL2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.WingBigL2.addBox(0.0F, -0.03333334F, 0.0F, 6, 0, 9);
        this.WingL2.addChild(this.WingBigL2);
        this.WingL3 = new ModelRenderer(this, 37, 31);
        this.WingL3.setRotationPoint(6.5F, 0.0F, 0.0F);
        this.WingL3.addBox(0.0F, -0.5F, 0.0F, 7, 1, 1);
        this.setRotationAngles(this.WingL3, 0.0F, -0.4363323129985824F, 0.0F);
        this.WingL2.addChild(this.WingL3);
        this.WingBigL3 = new ModelRenderer(this, 32, 12);
        this.WingBigL3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.WingBigL3.addBox(0.0F, -0.03333334F, 0.0F, 6, 0, 9);
        this.WingL3.addChild(this.WingBigL3);
        this.WingBigL1 = new ModelRenderer(this, 32, 12);
        this.WingBigL1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.WingBigL1.addBox(0.0F, 0.0F, 0.0F, 7, 0, 9);
        this.WingL1.addChild(this.WingBigL1);
        this.WingR1 = new ModelRenderer(this, 37, 31);
        this.WingR1.setRotationPoint(2.0F, -0.2F, 1.2F);
        this.WingR1.addBox(0.0F, -0.5F, 0.0F, 7, 1, 1);
        this.setRotationAngles(this.WingR1, 0.17453292519943295F, 0.0F, -0.17453292519943295F);
        this.Body.addChild(this.WingR1);
        this.WingR2 = new ModelRenderer(this, 37, 31);
        this.WingR2.setRotationPoint(7.0F, 0.0F, 0.0F);
        this.WingR2.addBox(0.0F, -0.5F, 0.0F, 6, 1, 1);
        this.setRotationAngles(this.WingR2, 0.0F, -0.4363323129985824F, 0.0F);
        this.WingR1.addChild(this.WingR2);
        this.WingBigR2 = new ModelRenderer(this, 32, 0);
        this.WingBigR2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.WingBigR2.addBox(0.0F, -0.03333334F, 0.0F, 6, 0, 9);
        this.WingR2.addChild(this.WingBigR2);
        this.WingR3 = new ModelRenderer(this, 37, 31);
        this.WingR3.setRotationPoint(7.0F, 0.0F, 0.0F);
        this.WingR3.addBox(0.0F, -0.5F, 0.0F, 7, 1, 1);
        this.setRotationAngles(this.WingR3, 0.0F, -0.4363323129985824F, 0.0F);
        this.WingR2.addChild(this.WingR3);
        this.WingBigR3 = new ModelRenderer(this, 32, 0);
        this.WingBigR3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.WingBigR3.addBox(0.0F, -0.03333334F, 0.0F, 6, 0, 9);
        this.WingR3.addChild(this.WingBigR3);
        this.WingBigR = new ModelRenderer(this, 32, 0);
        this.WingBigR.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.WingBigR.addBox(0.0F, -0.03333334F, 0.0F, 7, 0, 9);
        this.WingR1.addChild(this.WingBigR);
        this.Tail = new ModelRenderer(this, 0, 11);
        this.Tail.setRotationPoint(-1.0F, 0.0F, 8.0F);
        this.Tail.addBox(0.0F, 0.0F, 0.0F, 2, 1, 6);
        this.Body.addChild(this.Tail);
        this.Tail2 = new ModelRenderer(this, 22, 23);
        this.Tail2.setRotationPoint(-1.0F, 0.2F, 5.4F);
        this.Tail2.addBox(0.0F, 0.0F, 0.0F, 4, 1, 3);
        this.setRotationAngles(this.Tail2, 0.4363323129985824F, 0.0F, 0.0F);
        this.Tail.addChild(this.Tail2);
        this.Neck = new ModelRenderer(this, 0, 0);
        this.Neck.setRotationPoint(-1.0F, -0.2F, -1.6F);
        this.Neck.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
        this.setRotationAngles(this.Neck, 0.169820528229565F, 0.0F, 0.0F);
        this.Body.addChild(this.Neck);
        this.Head = new ModelRenderer(this, 6, 4);
        this.Head.setRotationPoint(-0.5F, 1.0F, 1.8F);
        this.Head.addBox(0.0F, -1.5F, -4.0F, 3, 3, 3);
        this.setRotationAngles(this.Head, 0.08482300397719036F, 0.0F, 0.0F);
        this.Neck.addChild(this.Head);
        this.Beak = new ModelRenderer(this, 10, 0);
        this.Beak.setRotationPoint(1.0F, 0.9F, -4.0F);
        this.Beak.addBox(0.0F, -0.5F, -2.0F, 1, 1, 2);
        this.setRotationAngles(this.Beak, -0.14154619634462767F, 0.0F, 0.0F);
        this.Head.addChild(this.Beak);
        this.Beak2 = new ModelRenderer(this, 10, 0);
        this.Beak2.setRotationPoint(0.0F, -0.8F, 0.1F);
        this.Beak2.addBox(0.0F, -0.5F, -2.0F, 1, 1, 2);
        this.setRotationAngles(this.Beak2, 0.4363323129985824F, 0.0F, 0.0F);
        this.Beak.addChild(this.Beak2);
        }

@Override
public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, rotationYaw, rotationPitch, scale);

        this.WingL1.rotateAngleZ = -2.96705972839036F - (float)Math.sin(ageInTicks/3f)/2f;
        this.WingR1.rotateAngleZ = -0.17453292519943295F + (float)Math.sin(ageInTicks/3f)/2f;

        this.Body.render(scale);
        }

public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
        }
        }
