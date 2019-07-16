package georgetsak.opcraft.client.gui.overlay;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.capability.sixpowers.ISixPowersCap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class SixPowersWheelHandler {

    private ArrayList<Segment> segments;
    public final ResourceLocation icons = new ResourceLocation(OPCraft.MODID, "textures/gui/selection_wheel/icons.png");


    public SixPowersWheelHandler(){
        segments = new ArrayList<>();
        addSegment(new Segment("rokushiki.geppo.title", "rokushiki.geppo.description", EnumSixPowers.MOON_WALK));
        addSegment(new Segment("rokushiki.tekkai.title", "rokushiri.tekkai.description", EnumSixPowers.IRON_BUDDY));
        addSegment(new Segment("rokushiki.shigan.title", "rokushiki.shigan.description", EnumSixPowers.FINGER_PISTOL));
        addSegment(new Segment("rokushiki.rankyaku.title", "rokushiri.rankyaku.description", EnumSixPowers.STORM_LEG));
        addSegment(new Segment("rokushiki.soru.title", "rokushiki.soru.description", EnumSixPowers.SHAVE));
        addSegment(new Segment("rokushiki.kamie.title", "rokushiri.kamie.description", EnumSixPowers.PAPER_DRAWING));
        addSegment(new Segment("rokushiki.rokuogan.title", "rokushiri.rokuogan.description", EnumSixPowers.SIX_KING_GUN));
    }

    private double angle = 24D;

    private void addSegment(Segment segment){
        segment.setAngles(angle, angle + 51.7D);
        segments.add(segment);
        angle += 51.7D;
    }

    public Segment getSegment(int id){
        return segments.get(id);
    }

    public static class Segment {
        private String title;
        private String description;
        double startAngle;
        double endAngle;
        EnumSixPowers power;

        public Segment(String title, String description, EnumSixPowers power) {
            this.title = title;
            this.description = description;
            this.power = power;
        }

        public void setAngles(double startAngle, double endAngle) {
            this.startAngle = startAngle;
            this.endAngle = endAngle;
        }

        public boolean isMouseHovering(int mouseX, int mouseY, int centerX, int centerY) {
            ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
            int guiScale = scaledresolution.getScaleFactor();

            int sideGUpper = 64 * guiScale;
            int sideGLower = 32 * guiScale;

            double sideG = Math.sqrt(Math.pow(centerX - mouseX, 2) + Math.pow(centerY - mouseY, 2));
            double sideB = Math.sqrt(Math.pow(centerY - mouseY, 2));
            double rot = Math.asin(sideB / sideG);
            double rot2 = Math.acos(sideB / sideG);
            double finalRot;
            rot = Math.toDegrees(rot);
            rot2 = Math.toDegrees(rot2);
            finalRot = rot;

            if (mouseX > centerX && mouseY < centerY) {
                finalRot = rot2 + 90.D;
            }
            if (mouseX > centerX && mouseY > centerY) {
                finalRot = rot + 180.D;
            }
            if (mouseX < centerX && mouseY > centerY) {
                finalRot = rot2 + 270.D;
            }

            finalRot = 360 - finalRot;
            if(power == EnumSixPowers.SIX_KING_GUN){
                return ((finalRot >= startAngle && finalRot <= endAngle) || (finalRot >= 0 && finalRot < 24)) && sideG > sideGLower && sideG < sideGUpper;
            }
            return finalRot >= startAngle && finalRot <= endAngle && sideG > sideGLower && sideG < sideGUpper;

        }

        public String getTitle() {
            return title;
        }

        public String getDescription(){
            return description;
        }

        public String getLevelInfo(ISixPowersCap sixPowersCap) {
            if(power != EnumSixPowers.SIX_KING_GUN){
                return "Current Level: " + sixPowersCap.getPowerLevel(power) + "/5";
            }
            else{
                if (sixPowersCap.isSixKingGunUnlocked()) {
                    return "Status: Mastered!";
                } else {
                    return "Status: You need to master all other Six Powers in order to unlock this!";
                }
            }
        }

        public String getProgressInfo(ISixPowersCap sixPowersCap) {
            if(power != EnumSixPowers.SIX_KING_GUN) {
                if(sixPowersCap.getPowerLevel(power) == 5){
                    return "Mastered!";
                }
                return "Progress: " + sixPowersCap.getProgress(power) + "/" + sixPowersCap.getRequiredPointsForNextLevel(power);
            }
            return "";
        }

    }
}
