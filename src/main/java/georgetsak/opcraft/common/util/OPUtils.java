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
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class OPUtils {
    private static UUID speedID = UUID.fromString("b36cb6dd-d15a-40a9-98b2-ec704a6ad645");
    private static UUID attackID = UUID.fromString("579c2c4c-ed00-4c57-b0b4-fac886c192b4");

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


    public static ItemStack setOwner(ItemStack stack, EntityPlayer owner) {
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.getTagCompound().setString("owner", owner.getPersistentID().toString());
        stack.getTagCompound().setString("ownerDisplayName", owner.getDisplayNameString());
        return stack;
    }

    public static List<Entity> getNearbyEntities(EntityPlayer player, double radius, Class get) {
        return getNearbyEntities(player, player.getPosition(),radius,get);
    }

    public static List<Entity> getNearbyEntities(EntityPlayer player, BlockPos center,  double radius, Class get) {
        World world = player.getServer().getEntityWorld();
        double[] coords = MathUtils.getRadiusCoords(center.getX(), center.getY(), center.getZ(), radius);

        List<Entity> entities = world.getEntitiesWithinAABB(get, new AxisAlignedBB(coords[0], coords[2], coords[4], coords[1], coords[3], coords[5]));

        return entities;
    }

    public static List<Entity> getNearbyEntitiesExcluding(EntityPlayer player, double radius, Entity excluding) {
        World world = player.getServer().getEntityWorld();
        double[] coords = MathUtils.getRadiusCoords(player.posX, player.posY + 0.5D, player.posZ,radius);

        List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(excluding, new AxisAlignedBB(coords[0], coords[2], coords[4], coords[1], coords[3], coords[5]));

        return entities;
    }

    public static void damageNearbyPlayers(EntityPlayer ep, int range, float damage, float velMul){
        List<Entity> entities = OPUtils.getNearbyEntitiesExcluding(ep, range, ep);

        for(int i = 0; i < entities.size(); i++) {
            if (entities.get(i) != null) {
                if (entities.get(i) instanceof EntityPlayer) {
                    EntityPlayer entityPlayer = (EntityPlayer) entities.get(i);
                    entityPlayer.attackEntityFrom(DamageSource.causePlayerDamage(ep), MathUtils.calculateDamage(entityPlayer, damage, true));

                }

                else if(entities.get(i) instanceof EntityLiving){
                    Entity e = entities.get(i);
                    e.attackEntityFrom(DamageSource.causePlayerDamage(ep), damage);
                }

                if(entities.get(i) instanceof EntityLiving || entities.get(i) instanceof EntityPlayer) {

                    double distanceX = entities.get(i).posX - ep.posX;
                    double distanceY = entities.get(i).posY - ep.posY;
                    double distanceZ = entities.get(i).posZ - ep.posZ;

                    double velocityX = (range / distanceX) * velMul;
                    double velocityY = (range / distanceY) * velMul;
                    double velocityZ = (range / distanceZ) * velMul;

                    if (distanceX == 0D) {
                        velocityX = 0;
                    }
                    if (distanceY == 0D) {
                        velocityY = 0;
                    }
                    if (distanceZ == 0D) {
                        velocityZ = 0;
                    }
                    if (velocityX > range) {
                        velocityX = range;
                    }
                    if (velocityX < -range) {
                        velocityX = -range;
                    }
                    if (velocityY > range) {
                        velocityY = range;
                    }
                    if (velocityY < -range) {
                        velocityY = -range;
                    }
                    if (velocityZ > range) {
                        velocityZ = range;
                    }
                    if (velocityZ < -range) {
                        velocityZ = -range;
                    }

                    entities.get(i).addVelocity(velocityX, velocityY, velocityZ);

                }
            }
        }
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

    public static boolean isPlayerInOrOverDeepWater(EntityPlayer player){
        BlockPos start = new BlockPos(player.posX, player.posY, player.posZ);
        Block playerAboveBlock = player.world.getBlockState(start.up(2)).getBlock();
        Block playerEyeBlock = player.world.getBlockState(start.up(1)).getBlock();
        Block playerFeetBlock = player.world.getBlockState(start).getBlock();
        Block playerBellowBlock = player.world.getBlockState(start.down()).getBlock();

        boolean flag1 = playerAboveBlock == Blocks.WATER || playerAboveBlock == Blocks.FLOWING_WATER;
        boolean flag2 = playerEyeBlock == Blocks.WATER || playerEyeBlock == Blocks.FLOWING_WATER;
        boolean flag3 = playerFeetBlock == Blocks.WATER || playerFeetBlock == Blocks.FLOWING_WATER;
        boolean flag4 = playerBellowBlock == Blocks.WATER || playerBellowBlock == Blocks.FLOWING_WATER;

        return flag3 && (flag2 || (flag1 && flag2) || flag4);

    }

    public static Block[] nonMovableBlocks = new Block[]{OPBlocks.BlockLawDomeCenter, OPBlocks.BlockLawDome, Blocks.AIR, Blocks.BEDROCK, Blocks.PORTAL, Blocks.END_GATEWAY, Blocks.END_PORTAL, Blocks.END_PORTAL_FRAME,
            Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.ENDER_CHEST, Blocks.WATER, Blocks.FLOWING_WATER, Blocks.LAVA, Blocks.FLOWING_LAVA, Blocks.WHEAT, Blocks.CARROTS, Blocks.POTATOES, Blocks.PUMPKIN_STEM, Blocks.MELON_STEM,
            Blocks.RAIL, Blocks.ACTIVATOR_RAIL, Blocks.DETECTOR_RAIL, Blocks.GOLDEN_RAIL, Blocks.CHORUS_FLOWER, Blocks.RED_FLOWER, Blocks.YELLOW_FLOWER, Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM, Blocks.TALLGRASS, Blocks.SAPLING,
            Blocks.REDSTONE_WIRE, Blocks.BEETROOTS, Blocks.DOUBLE_PLANT, Blocks.TRIPWIRE, Blocks.TRIPWIRE_HOOK, Blocks.MOB_SPAWNER, Blocks.FIRE};

}