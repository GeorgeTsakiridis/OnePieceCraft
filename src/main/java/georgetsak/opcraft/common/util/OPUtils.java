package georgetsak.opcraft.common.util;

import com.google.common.base.Predicates;
import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.proxy.ClientProxy;
import georgetsak.opcraft.common.capability.haki.HakiCap;
import georgetsak.opcraft.common.capability.haki.IHakiCap;
import georgetsak.opcraft.common.capability.stats.normal.IStatsNormalCap;
import georgetsak.opcraft.common.capability.stats.normal.StatsNormalCap;
import georgetsak.opcraft.common.crew.CrewSaveData;
import georgetsak.opcraft.common.crew.EnumRole;
import georgetsak.opcraft.common.crew.Member;
import georgetsak.opcraft.common.network.proxy.CommonProxy;
import georgetsak.opcraft.common.registry.OPBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.vecmath.Point3d;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class OPUtils {
    private static UUID speedID = UUID.fromString("b36cb6dd-d15a-40a9-98b2-ec704a6ad645");
    private static UUID attackID = UUID.fromString("579c2c4c-ed00-4c57-b0b4-fac886c192b4");
    private static Random random = new Random();

    public static float calculateDamage(EntityPlayer target, float baseDamage, boolean isDFdamage) {
        if(target == null)return 0;

        float defensePoints = (float) target.getTotalArmorValue();
        float toughness = 2;

        float damage = baseDamage;
        damage = damage * (1 - Math.min(20, Math.max(defensePoints / 5, defensePoints - damage / (2 + toughness / 4))) / 10);

        if (isDFdamage) {
            IHakiCap hakiCap = HakiCap.get(target);
            int random = OPUtils.random.nextInt(100);

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

    public static void refreshStats(EntityPlayer ep){
        updateStats(ep, StatsNormalCap.get(ep));
    }

    public static void updateStats(EntityPlayer ep, IStatsNormalCap stats) {
        AttributeModifier mod = new AttributeModifier(speedID, "OPSpeedMod", 0.1D * (double) stats.getSpeedLevel() * 0.3D, 2);
        IAttributeInstance attribute = ep.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);

        if (attribute.hasModifier(mod)) {
            attribute.removeModifier(mod);
        }
        attribute.applyModifier(mod);

        //ATTACK

        mod = new AttributeModifier(attackID, "OPAttackMod",1.0D * stats.getAttackLevel() * 0.25, 2);
        attribute = ep.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);

        if (attribute.hasModifier(mod)) {
            attribute.removeModifier(mod);
        }
        attribute.applyModifier(mod);

        //HEALTH

        attribute = ep.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
        attribute.setBaseValue(20 + stats.getHealthLevel() * 2);

        CrewSaveData saveData = CrewSaveData.get(ep.world);
        Member member = CrewUtils.getMemberFromPlayer(saveData.getCrews(), ep);
        if(member != null && member.getRole() == EnumRole.DOCTOR){
            attribute.setBaseValue(attribute.getBaseValue() + attribute.getBaseValue()*0.15D);
        }
    }

    public static RayTraceResult getMouseOverExtended(float dist) {
        Minecraft mc = FMLClientHandler.instance().getClient();
        Entity theRenderViewEntity = mc.getRenderViewEntity();
        AxisAlignedBB theViewBoundingBox = new AxisAlignedBB(
                theRenderViewEntity.posX - 0.5D,
                theRenderViewEntity.posY - 0.0D,
                theRenderViewEntity.posZ - 0.5D,
                theRenderViewEntity.posX + 0.5D,
                theRenderViewEntity.posY + 1.5D,
                theRenderViewEntity.posZ + 0.5D
        );
        RayTraceResult returnMOP = null;
        if (mc.world != null) {
            double var2 = dist;
            returnMOP = theRenderViewEntity.rayTrace(var2, 0);
            double calcdist = var2;
            Vec3d pos = theRenderViewEntity.getPositionEyes(0);
            var2 = calcdist;
            if (returnMOP != null) {
                calcdist = returnMOP.hitVec.distanceTo(pos);
            }

            Vec3d lookvec = theRenderViewEntity.getLook(0);
            Vec3d var8 = pos.addVector(lookvec.x * var2,
                    lookvec.y * var2,
                    lookvec.z * var2);
            Entity pointedEntity = null;
            float var9 = 1.0F;
            @SuppressWarnings("unchecked")
            List<Entity> list = mc.world.getEntitiesWithinAABBExcludingEntity(
                    theRenderViewEntity,
                    theViewBoundingBox.expand(
                            lookvec.x * var2,
                            lookvec.y * var2,
                            lookvec.z * var2).expand(var9, var9, var9));
            double d = calcdist;

            for (Entity entity : list) {
                if (entity.canBeCollidedWith()) {
                    float bordersize = entity.getCollisionBorderSize();
                    AxisAlignedBB aabb = new AxisAlignedBB(
                            entity.posX - entity.width / 2,
                            entity.posY,
                            entity.posZ - entity.width / 2,
                            entity.posX + entity.width / 2,
                            entity.posY + entity.height,
                            entity.posZ + entity.width / 2);
                    aabb.expand(bordersize, bordersize, bordersize);
                    RayTraceResult mop0 = aabb.calculateIntercept(pos, var8);

                    if (aabb.contains(pos)) {
                        if (0.0D < d || d == 0.0D) {
                            pointedEntity = entity;
                            d = 0.0D;
                        }
                    } else if (mop0 != null) {
                        double d1 = pos.distanceTo(mop0.hitVec);

                        if (d1 < d || d == 0.0D) {
                            pointedEntity = entity;
                            d = d1;
                        }
                    }
                }
            }

            if (pointedEntity != null && (d < calcdist || returnMOP == null)) {
                returnMOP = new RayTraceResult(pointedEntity);
            }
        }
        return returnMOP;
    }

    @SideOnly(Side.CLIENT)
    public static Entity getLookingEntity(EntityPlayer ep, int range) {
        int partialTicks = 5;
        Entity pointedEntity = null;
        Entity entity = Minecraft.getMinecraft().getRenderViewEntity();

        if (entity != null) {
            if (Minecraft.getMinecraft().world != null) {
                Minecraft.getMinecraft().mcProfiler.startSection("pick");
                Minecraft.getMinecraft().pointedEntity = null;
                double d0 = range;
                Minecraft.getMinecraft().objectMouseOver = entity.rayTrace(d0, partialTicks);
                double d1 = d0;
                Vec3d vec3d = entity.getPositionEyes(partialTicks);
                boolean flag = false;

                if (Minecraft.getMinecraft().playerController.extendedReach()) {
                    d0 = range;
                    d1 = range;
                } else {
                    if (d0 > range) {
                        flag = true;
                    }
                }

                if (Minecraft.getMinecraft().objectMouseOver != null) {
                    d1 = Minecraft.getMinecraft().objectMouseOver.hitVec.distanceTo(vec3d);
                }

                Vec3d vec3d1 = entity.getLook(partialTicks);
                Vec3d vec3d2 = vec3d.addVector(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0);
                pointedEntity = null;
                Vec3d vec3d3 = null;
                float f = 1.0F;
                List<Entity> list = Minecraft.getMinecraft().world.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().expand(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0).expand((double) f, (double) f, (double) f), Predicates.and(EntitySelectors.NOT_SPECTATING, p_apply_1_ -> p_apply_1_ != null && p_apply_1_.canBeCollidedWith()));
                double d2 = d1;

                for (int j = 0; j < list.size(); ++j) {
                    Entity entity1 = (Entity) list.get(j);
                    AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expand((double) entity1.getCollisionBorderSize(), (double) entity1.getCollisionBorderSize(), (double) entity1.getCollisionBorderSize());
                    RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(vec3d, vec3d2);

                    if (axisalignedbb.contains(vec3d)) {
                        if (d2 >= 0.0D) {
                            pointedEntity = entity1;
                            vec3d3 = raytraceresult == null ? vec3d : raytraceresult.hitVec;
                            d2 = 0.0D;
                        }
                    } else if (raytraceresult != null) {
                        double d3 = vec3d.distanceTo(raytraceresult.hitVec);

                        if (d3 < d2 || d2 == 0.0D) {
                            if (entity1.getLowestRidingEntity() == entity.getLowestRidingEntity() && !entity.canRiderInteract()) {
                                if (d2 == 0.0D) {
                                    pointedEntity = entity1;
                                    vec3d3 = raytraceresult.hitVec;
                                }
                            } else {
                                pointedEntity = entity1;
                                vec3d3 = raytraceresult.hitVec;
                                d2 = d3;
                            }
                        }
                    }
                }

                if (pointedEntity != null && flag && vec3d.distanceTo(vec3d3) > range) {
                    pointedEntity = null;
                    Minecraft.getMinecraft().objectMouseOver = new RayTraceResult(RayTraceResult.Type.MISS, vec3d3, (EnumFacing) null, new BlockPos(vec3d3));
                }

                if (pointedEntity != null && (d2 < d1 || Minecraft.getMinecraft().objectMouseOver == null)) {
                    Minecraft.getMinecraft().objectMouseOver = new RayTraceResult(pointedEntity, vec3d3);

                    if (pointedEntity instanceof EntityLivingBase || pointedEntity instanceof EntityItemFrame) {
                        Minecraft.getMinecraft().pointedEntity = pointedEntity;
                    }
                }

                Minecraft.getMinecraft().mcProfiler.endSection();
            }
        }
        if (pointedEntity != null) {
            return ep.world.getEntityByID(pointedEntity.getEntityId());
        }
        return null;
    }

    public static ItemStack setOwner(ItemStack stack, EntityPlayer owner) {
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.getTagCompound().setString("owner", owner.getPersistentID().toString());
        stack.getTagCompound().setString("ownerDisplayName", owner.getDisplayNameString());
        return stack;
    }

    @SideOnly(Side.CLIENT)
    public static BlockPos getBlockPlayerIsLooking(EntityPlayer entityPlayer, int distance) {
        RayTraceResult rayTraceResult;
        rayTraceResult = entityPlayer.rayTrace(distance, 1);

        if (rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
            return rayTraceResult.getBlockPos();
        }
        return null;
    }

    public static List<Entity> getNearbyEntities(EntityPlayer player, double radius, Class get) {
        return getNearbyEntities(player, player.getPosition(),radius,get);
    }

    public static List<Entity> getNearbyEntities(EntityPlayer player, BlockPos center,  double radius, Class get) {
        World world = player.getServer().getEntityWorld();
        double[] coords = getRadiusCoords(center.getX(), center.getY(), center.getZ(), radius);

        List<Entity> entities = world.getEntitiesWithinAABB(get, new AxisAlignedBB(coords[0], coords[2], coords[4], coords[1], coords[3], coords[5]));

        return entities;
    }

    public static List<Entity> getNearbyEntitiesExcluding(EntityPlayer player, double radius, Entity excluding) {
        World world = player.getServer().getEntityWorld();
        double[] coords = getRadiusCoords(player.posX, player.posY + 0.5D, player.posZ,radius);

        List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(excluding, new AxisAlignedBB(coords[0], coords[2], coords[4], coords[1], coords[3], coords[5]));

        return entities;
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

    public static void drawEntityOnScreen(int posX, int posY, int scale, int deg, EntityLivingBase ent)
    {
        ent.rotationYawHead = 0;
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX, (float)posY, 50.0F);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(135.0F - deg*3, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        rendermanager.setRenderShadow(true);
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
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

    /**
     * Used to check if Griefing is allowed from the Config to adjust the explosion.
     */
    public static void newExplosion(Entity entity, World world, double posX, double posY, double posZ, float strength, boolean isFlaming, boolean isSmoking){
        boolean flag = OPCraft.config.disableGriefing.getCurrentValue();

        world.newExplosion(entity, posX, posY, posZ, strength, !flag && isFlaming, !flag && isSmoking);
    }

    public static void createExplosion(Entity entity, World world, double posX, double posY, double posZ, float strength, boolean isSmoking){
        boolean flag = OPCraft.config.disableGriefing.getCurrentValue();

        world.createExplosion(entity, posX, posY, posZ, strength, !flag && isSmoking);
    }

    public static Block[] nonMovableBlocks = new Block[]{OPBlocks.BlockLawDomeCenter, OPBlocks.BlockLawDome, Blocks.AIR, Blocks.BEDROCK, Blocks.PORTAL, Blocks.END_GATEWAY, Blocks.END_PORTAL, Blocks.END_PORTAL_FRAME,
            Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.ENDER_CHEST, Blocks.WATER, Blocks.FLOWING_WATER, Blocks.LAVA, Blocks.FLOWING_LAVA, Blocks.WHEAT, Blocks.CARROTS, Blocks.POTATOES, Blocks.PUMPKIN_STEM, Blocks.MELON_STEM,
            Blocks.RAIL, Blocks.ACTIVATOR_RAIL, Blocks.DETECTOR_RAIL, Blocks.GOLDEN_RAIL, Blocks.CHORUS_FLOWER, Blocks.RED_FLOWER, Blocks.YELLOW_FLOWER, Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM, Blocks.TALLGRASS, Blocks.SAPLING,
            Blocks.REDSTONE_WIRE, Blocks.BEETROOTS, Blocks.DOUBLE_PLANT, Blocks.TRIPWIRE, Blocks.TRIPWIRE_HOOK, Blocks.MOB_SPAWNER, Blocks.FIRE};

}