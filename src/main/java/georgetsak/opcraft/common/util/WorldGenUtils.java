package georgetsak.opcraft.common.util;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.entity.marine.EntityMarine;
import georgetsak.opcraft.common.entity.other.EntityBandit;
import georgetsak.opcraft.common.entity.other.EntityOPVillager;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class WorldGenUtils {

    public static Template getTemplate(String fileName, World world) {

        WorldServer worldserver = (WorldServer) world;
        MinecraftServer minecraftserver = world.getMinecraftServer();
        TemplateManager templatemanager = worldserver.getStructureTemplateManager();

        return templatemanager.get(minecraftserver, new ResourceLocation(OPCraft.MODID, fileName));

    }

    public static int getGroundFromAbove(World world, int x, int z) {
        int y = 255;
        boolean foundGround = false;
        while (!foundGround && y >= 0) {
            Block blockAt = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            foundGround = blockAt == Blocks.DIRT || blockAt == Blocks.GRASS;
            y--;
        }

        return y;
    }

    public static int getSeaLevel(World world, int x, int z) {
        int y = 255;
        boolean foundWater = false;
        while (!foundWater && y > 0) {
            Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            foundWater = block == Blocks.WATER;
            y--;
        }

        return y;
    }

    public static int getAverageHeight(World world, int x, int z, int radius, boolean lowestBlockMode) {
        ArrayList<Integer> heights = new ArrayList<>();

        for (int i = x - radius; i < x + radius; i++) {
            for (int k = z - radius; k < z + radius; k++) {
                int y = 255;
                boolean foundGround = false;
                while (!foundGround && y >= 0) {
                    Block blockAt = world.getBlockState(new BlockPos(i, y, k)).getBlock();
                    foundGround = blockAt != Blocks.AIR;
                    y--;
                }
                heights.add(y);
            }
        }
        int totalSum = 0;
        for (int i = 0; i < heights.size(); i++) {
            totalSum += heights.get(i);
        }
        if (lowestBlockMode) {
            Collections.sort(heights);
            return heights.get(0);
        } else {
            return totalSum / (radius * 2 * radius * 2);
        }
    }

    public static Rotation getRandomRotation(Random rand) {
        int i = rand.nextInt(4);
        switch (i) {
            case 0:
                return Rotation.NONE;
            case 1:
                return Rotation.CLOCKWISE_90;
            case 2:
                return Rotation.CLOCKWISE_180;
            case 3:
                return Rotation.COUNTERCLOCKWISE_90;
        }
        return Rotation.NONE;

    }

    // TODO: 9/18/2019 Implement the rot parameter
    public static void clear(World world, int x, int y, int z, int sizeX, int sizeY, int sizeZ, Rotation rot) {

        for (int i = 0; i <= sizeX; i++) {
            for (int j = 0; j <= sizeY; j++) {
                for (int k = 0; k <= sizeZ; k++) {
                    world.setBlockToAir(new BlockPos(x, y, z).add(i, j, k));
                }
            }
        }
    }

    public static void spawnEntities(World worldIn, StructureComponent component, int x, int y, int z, int count, String entity) {
        if(worldIn.isRemote)return;

        for (int i = 0; i < count; i++) {
            int j = getXWithOffset(component,x + i, z);
            int k = getYWithOffset(component, y);
            int l = getZWithOffset(component, x + i, z);

            if (!component.getBoundingBox().isVecInside(new BlockPos(j, k, l))) {
                break;
            }

            if(entity.equals("villager")) {
                EntityOPVillager opVillager = new EntityOPVillager(worldIn);
                opVillager.setLocationAndAngles((double) j + 0.5D, (double) k, (double) l + 0.5D, 0.0F, 0.0F);
                worldIn.spawnEntity(opVillager);
            }
            else if(entity.equals("bandit")){
                EntityBandit bandit = new EntityBandit(worldIn);
                bandit.setLocationAndAngles((double) j + 0.5D, (double) k, (double) l + 0.5D, 0.0F, 0.0F);
                worldIn.spawnEntity(bandit);
            }
            else if(entity.equals("marine")){
                EntityMarine marine = new EntityMarine(worldIn);
                marine.setLocationAndAngles((double) j + 0.5D, (double) k, (double) l + 0.5D, 0.0F, 0.0F);
                worldIn.spawnEntity(marine);
            }
        }
    }

    private static int getXWithOffset(StructureComponent component, int x, int z)
    {
        EnumFacing enumfacing = component.getCoordBaseMode();

        if (enumfacing == null)
        {
            return x;
        }
        else
        {
            switch (enumfacing)
            {
                case NORTH:
                case SOUTH:
                    return component.getBoundingBox().minX + x;
                case WEST:
                    return component.getBoundingBox().maxX - z;
                case EAST:
                    return component.getBoundingBox().minX + z;
                default:
                    return x;
            }
        }
    }

    private static int getYWithOffset(StructureComponent component, int y)
    {
        return component.getCoordBaseMode() == null ? y : y + component.getBoundingBox().minY;
    }

    private static int getZWithOffset(StructureComponent component, int x, int z)
    {
        EnumFacing enumfacing = component.getCoordBaseMode();

        if (enumfacing == null)
        {
            return z;
        }
        else
        {
            switch (enumfacing)
            {
                case NORTH:
                    return component.getBoundingBox().maxZ - z;
                case SOUTH:
                    return component.getBoundingBox().minZ + z;
                case WEST:
                case EAST:
                    return component.getBoundingBox().minZ + x;
                default:
                    return z;
            }
        }
    }

}
