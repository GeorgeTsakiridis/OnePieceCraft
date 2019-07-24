package georgetsak.opcraft.common.util;

import georgetsak.opcraft.common.capability.haki.HakiCap;
import georgetsak.opcraft.common.capability.haki.IHakiCap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

import javax.vecmath.Point3d;
import java.util.ArrayList;
import java.util.Random;

public class MathUtils {

    private static Random random = new Random();

    public static float calculateDamage(EntityPlayer target, float baseDamage, boolean isDFdamage) {
        if(target == null)return 0;

        float defensePoints = (float) target.getTotalArmorValue();
        float toughness = 2;

        float damage = baseDamage;
        damage = damage * (1 - Math.min(20, Math.max(defensePoints / 5, defensePoints - damage / (2 + toughness / 4))) / 10);

        if (isDFdamage) {
            IHakiCap hakiCap = HakiCap.get(target);
            int random = MathUtils.random.nextInt(100);

            int dodge = hakiCap.getDodgeLevel();
            int defence = hakiCap.getDefenceLevel();

            float damageReduced = damage * (1f - (float) defence / 10f); //reduce damage according to defence level

            boolean dodged = (random < 10 * dodge);
            if (dodged) {
                damageReduced = 0f;
            }

            return damageReduced;
        }

        return damage;
    }

    public static double[] getRadiusCoords(double x, double y, double z, double radius){
        double [] coords = new double[6];
        coords[0] = x - radius;
        coords[1] = x + radius;
        coords[2] = y - radius;
        coords[3] = y + radius;
        coords[4] = z - radius;
        coords[5] = z + radius;

        return coords;
    }

    //Converts Yaw and Pitch to Vec3d. Used by projectiles for example to multiply speed.
    public static Vec3d convertRotation(float entityYaw, float entityPitch) {
        double pitch = ((entityPitch + 90) * Math.PI) / 180;
        double yaw = ((entityYaw + 90) * Math.PI) / 180;

        double x = Math.sin(pitch) * Math.cos(yaw);
        double y = Math.sin(pitch) * Math.sin(yaw);
        double z = Math.cos(pitch);

        return new Vec3d(x, z, y);
    }

    public static float getPercentage(int n, int total) {
        return ((float) n) / ((float) total);
    }

    public static ArrayList<Point3d> getIntermediatePoints(Vec3d pos1, Vec3d pos2, int points){

        double diffX = pos2.x - pos1.x;
        double diffY = pos2.y - pos1.y;
        double diffZ = pos2.z - pos1.z;

        double intervalX = diffX / ((double)points + 1d);
        double intervalY = diffY / ((double)points + 1d);
        double intervalZ = diffZ / ((double)points + 1d);

        ArrayList<Point3d> pointList = new ArrayList<>();
        for (int i = 1; i <= points; i++)
        {
            pointList.add(new Point3d(pos1.x + intervalX * i, pos1.y + intervalY*i, pos1.z + intervalZ*i));
        }
        return pointList;
    }

}
