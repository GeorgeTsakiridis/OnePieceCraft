package georgetsak.opcraft.common.dimension.mirror;

import georgetsak.opcraft.common.registry.OPBlocks;
import georgetsak.opcraft.dev_notUsed.OPLog;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class MirrorChunkGenerator implements IChunkGenerator {

    private final World world;

    private double randX;
    private double randY;
    private double randZ;


    public MirrorChunkGenerator(World world, double randX, double randY, double randZ) {
        this.world = world;
        this.randX = randX;
        this.randY = randY;
        this.randZ = randZ;
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        Chunk chunk = new Chunk(world, x, z);
        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public void populate(int x, int z) {

        int R = 80;
        int r = 20;

        if (x == 0 && z == 0) {

            double twoPi = 2 * Math.PI;
            double resolution = 0.01d;
            double[] tCos = new double[(int) (twoPi / resolution) + 1];
            double[] tSin = new double[(int) (twoPi / resolution) + 1];

            OPLog.logInfo("Creating Torus... Will need " + (int) ((twoPi / resolution) * (twoPi / resolution)) + " iterations.");
            long millis = System.currentTimeMillis();

            int index = 0;
            for (double i = 0; i < twoPi; i += resolution) {
                tCos[index] = Math.cos(i);
                tSin[index++] = Math.sin(i);
            }

            for (double f = 0; f < twoPi; f += resolution) {

                double d1 = Math.cos(f);
                double d2 = Math.sin(f);
                int xOffset = (int)(Math.sin(f * 10d + twoPi/2d + randX) * 10d);
                int yOffset = (int)(Math.sin(f * 6d + randY) * 6d);
                int zOffset = (int)(Math.cos(f * 3d + randZ) * 10d);


                index = 0;
                for (double t = 0; t < twoPi; t += resolution) {

                    double d = R + r * tCos[index];
                    int posX = (int) (d * d1 + xOffset);
                    int posY = (int) (r * tSin[index++]) + yOffset;
                    int posZ = (int) (d * d2 + zOffset);

                    world.setBlockState(new BlockPos(posX, posY, posZ).up(90), OPBlocks.MIRROR_WALL_BLOCK.getDefaultState());
                    world.setBlockState(new BlockPos(posX+1, posY, posZ).up(90), OPBlocks.MIRROR_WALL_BLOCK.getDefaultState());
                    world.setBlockState(new BlockPos(posX-1, posY, posZ).up(90), OPBlocks.MIRROR_WALL_BLOCK.getDefaultState());
                    world.setBlockState(new BlockPos(posX, posY, posZ+1).up(90), OPBlocks.MIRROR_WALL_BLOCK.getDefaultState());
                    world.setBlockState(new BlockPos(posX, posY, posZ-1).up(90), OPBlocks.MIRROR_WALL_BLOCK.getDefaultState());
                }
            }
            millis = System.currentTimeMillis() - millis;
            OPLog.logInfo("Finished. Took " + (millis/1000f) + " seconds.");
        }
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        return null;
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {

    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }

}
