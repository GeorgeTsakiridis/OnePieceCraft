package georgetsak.opcraft.client.gui.overlay;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.capability.sixpowers.SixPowersCap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SixPowersSelectionWheelRender {
    private ArrayList<ResourceLocation> wheelSegmentsTextures;
    private ArrayList<ResourceLocation> wheelSelectionSegmentsTextures;
    private final ResourceLocation[]  ICONS = {
            new ResourceLocation("onepiececraft:textures/gui/selection_wheel/icon1.png"),
            new ResourceLocation("onepiececraft:textures/gui/selection_wheel/icon2.png"),
            new ResourceLocation("onepiececraft:textures/gui/selection_wheel/icon3.png"),
            new ResourceLocation("onepiececraft:textures/gui/selection_wheel/icon4.png"),
            new ResourceLocation("onepiececraft:textures/gui/selection_wheel/icon5.png"),
            new ResourceLocation("onepiececraft:textures/gui/selection_wheel/icon6.png"),
            new ResourceLocation("onepiececraft:textures/gui/selection_wheel/icon7.png")
    };

    private static final ResourceLocation BASE_TEXTURE = new ResourceLocation("onepiececraft:textures/gui/selection_wheel/segment.png");
    private static final ResourceLocation BASE_SELECTED_TEXTURE = new ResourceLocation("onepiececraft:textures/gui/selection_wheel/segment_selected.png");
    private static final ResourceLocation BAR_TEXTURE = new ResourceLocation("onepiececraft:textures/gui/cooldownbar.png");

    public static final SixPowersWheelHandler sixPowersWheelHandler = new SixPowersWheelHandler();

    private EnumSixPowers selectedPower = EnumSixPowers.NONE;

    public void render(ScaledResolution scaledResolution, int mouseX, int mouseY) {
        int scaledResolutionX = scaledResolution.getScaledWidth();
        int scaledResolutionY = scaledResolution.getScaledHeight();
        Minecraft mc = Minecraft.getMinecraft();

        int renderX = scaledResolutionX / 2 - 64;
        int renderY = scaledResolutionY / 2 - 64;

        int index = 0;

        for (ResourceLocation resourceLocation : wheelSegmentsTextures) {
            mc.getTextureManager().bindTexture(resourceLocation);
            //posX, posY, startPointImageX, startPointImageY, imageRectWidth, imageRectHeight, renderWidth, renderHeight, tileWidth, tileheight
            Gui.drawScaledCustomSizeModalRect(renderX, renderY, 0, 0, 512, 512, 128, 128, 512, 512);
            SixPowersWheelHandler.Segment segment = sixPowersWheelHandler.getSegment(index);
            if (segment.isMouseHovering(mouseX, mouseY, mc.displayWidth/2, mc.displayHeight/2)) {
                selectedPower = segment.power;

                mc.getTextureManager().bindTexture(wheelSelectionSegmentsTextures.get(index));
                Gui.drawScaledCustomSizeModalRect(renderX, renderY, 0, 0, 512, 512, 128, 128, 512, 512);

                String title = I18n.format(segment.getTitle());
                mc.fontRenderer.drawStringWithShadow(title, scaledResolutionX/2f - mc.fontRenderer.getStringWidth(title)/2f, scaledResolutionY / 2f - 80, Color.WHITE.getRGB());

                String description = I18n.format(segment.getDescription());
                List<String> descriptionSplit = mc.fontRenderer.listFormattedStringToWidth(description, scaledResolutionX);
                int line = 0;
                for(String string : descriptionSplit){
                    mc.fontRenderer.drawStringWithShadow(string, scaledResolutionX/2f - mc.fontRenderer.getStringWidth(string)/2f, scaledResolutionY / 2 + 70 + mc.fontRenderer.FONT_HEIGHT * line, Color.WHITE.getRGB());
                    line++;
                }
                String levelInfo = segment.getLevelInfo(SixPowersCap.get(mc.player));
                mc.fontRenderer.drawStringWithShadow(levelInfo, scaledResolutionX/2f - mc.fontRenderer.getStringWidth(levelInfo)/2f, scaledResolutionY / 2 + 70 + mc.fontRenderer.FONT_HEIGHT * line++, Color.WHITE.getRGB());

                String progressInfo = segment.getProgressInfo(SixPowersCap.get(mc.player));
                mc.fontRenderer.drawStringWithShadow(progressInfo, scaledResolutionX/2f - mc.fontRenderer.getStringWidth(levelInfo)/2f, scaledResolutionY / 2 + 70 + mc.fontRenderer.FONT_HEIGHT * line, Color.WHITE.getRGB());
            }
            index++;
        }
        mc.getTextureManager().bindTexture(sixPowersWheelHandler.icons);
        Gui.drawScaledCustomSizeModalRect(renderX, renderY, 0, 0, 512, 512, 128, 128, 512, 512);
    }

    public EnumSixPowers getSelectedPower(){
        return selectedPower;
    }

    public void createTextures() {
        wheelSegmentsTextures = new ArrayList<>();
        wheelSelectionSegmentsTextures = new ArrayList<>();
        Minecraft mc = Minecraft.getMinecraft();
        try {
            IResource res = mc.getResourceManager().getResource(BASE_TEXTURE);
            IResource resSelection = mc.getResourceManager().getResource(BASE_SELECTED_TEXTURE);

            InputStream stream = res.getInputStream();
            InputStream streamSelection = resSelection.getInputStream();

            BufferedImage baseImage = ImageIO.read(stream);
            BufferedImage baseSelectionImage = ImageIO.read(streamSelection);

            for (int i = 0; i < 7; i++) {
                wheelSegmentsTextures.add(mc.getTextureManager().getDynamicTextureLocation(OPCraft.MODID, new DynamicTexture(rotateBufferedImage(baseImage, i * 51.7d))));
                wheelSelectionSegmentsTextures.add(mc.getTextureManager().getDynamicTextureLocation(OPCraft.MODID, new DynamicTexture(rotateBufferedImage(baseSelectionImage, i * 51.7d))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage rotateBufferedImage(BufferedImage image, double angle){
        double rads = Math.toRadians(angle);
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage rotated = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.rotate(rads, width/2f, height/2f);
        g2d.setTransform(at);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return rotated;
    }

}
