package georgetsak.opcraft.common.generator;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.registry.OPBlocks;
import georgetsak.opcraft.common.generator.terrain.WorldGenAdamTree;
import georgetsak.opcraft.common.generator.terrain.WorldGenCherryTree;
import net.minecraft.block.Block;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class OPIWorldGenerator implements IWorldGenerator
{

	private void generateNether(World world, Random rand, int blockX, int blockZ) 
	{
	}

	private void generateOverworld(World world, Random rand, int blockX, int blockZ) 
	{
		WorldGenerator worldGenCherryTree = new WorldGenCherryTree();
		WorldGenerator worldGenAdamTree = new WorldGenAdamTree();
		WorldGenerator worldGenMinable;
		
		Biome biome = world.getBiomeForCoordsBody(new BlockPos(blockX, 64, blockZ));

		if(biome == Biomes.PLAINS && world.getWorldType() != WorldType.FLAT) {
            int MIN = 0;
            int MAX = 2;
            int numBushes = MIN + rand.nextInt(MAX - MIN);
            for (int i = 0; i < numBushes; i++) {
                int randX = blockX + rand.nextInt(16);
                int randZ = blockZ + rand.nextInt(16);

                if (rand.nextInt(700) == 0) {
                    worldGenAdamTree.generate(world, rand, new BlockPos(randX, getGroundFromAbove(world, randX + 2, randZ), randZ));
                } else {
                    worldGenCherryTree.generate(world, rand, new BlockPos(randX, getGroundFromAbove(world, randX, randZ), randZ));
                }
            }
        }

        if(biome == Biomes.PLAINS && world.getWorldType() != WorldType.FLAT && OPCraft.config.enableMorganFortress.getCurrentValue()){
            int randX = blockX + rand.nextInt(16);
            int randZ = blockZ + rand.nextInt(16);
//rand.nextInt(OPCraft.config.morganFortressSpawnChance)
            if(rand.nextInt(OPCraft.config.morganFortressSpawnChance.getCurrentValue()) == 0){

				Template morgan_fortress_stage1 = getTemplate("morgan_fortress_stage1", world);
				Template morgan_fortress_stage2 = getTemplate("morgan_fortress_stage2", world);
				Rotation rot = randomRot(rand);
																											//rot
				PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(true).setChunk((ChunkPos)null).setReplacedBlock((Block)null).setIgnoreStructureBlock(false);
				PlacementSettings placementsettingsTorch = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos)null).setReplacedBlock((Block)null).setIgnoreStructureBlock(false);

				int finalY =  getAverageHeight(world, randX, randZ - 1, 109, false) + 1;

				if (morgan_fortress_stage1 != null && morgan_fortress_stage2 != null){
                	clear(world, randX, finalY, randZ, 102, 42, 52, rot);
					System.out.println("Spawning fortress at " + randX + " // " + randZ);
					morgan_fortress_stage1.addBlocksToWorld(world, new BlockPos(randX, finalY, randZ), placementsettings);
					morgan_fortress_stage2.addBlocksToWorld(world, new BlockPos(randX, finalY, randZ), placementsettingsTorch);
				}
			}
        }


		
		if(biome == Biomes.OCEAN || biome == Biomes.DEEP_OCEAN || biome == Biomes.RIVER || biome == Biomes.FROZEN_RIVER || biome == Biomes.FROZEN_OCEAN){
			
			int kairosekiMIN = 1;
			int kairosekiMAX = 3;
			int kairoseki = kairosekiMIN + rand.nextInt(kairosekiMAX - kairosekiMIN);
			for(int x = 0; x < kairoseki; x++){
				int randX = blockX + rand.nextInt(16);
				int randZ = blockZ + rand.nextInt(16);
				int randY = rand.nextInt(15) + 5;
				
				worldGenMinable = new WorldGenMinable(OPBlocks.BlockKairosekiStone.getDefaultState(), 3);
				worldGenMinable.generate(world, rand, new BlockPos(randX, randY, randZ));
			}
			
		}
		
		if(true){//Just to keep organized.
			
			int steelMIN = 2;
			int steelMAX = 5;
			int steel = steelMIN + rand.nextInt(steelMAX - steelMIN);
			for(int x = 0; x < steel; x++){
				int randX = blockX + rand.nextInt(16);
				int randZ = blockZ + rand.nextInt(16);
				int randY = rand.nextInt(40) + 5;
				
				if(rand.nextInt(3) <= 1){
					worldGenMinable = new WorldGenMinable(OPBlocks.BlockSteelOre.getDefaultState(), 4);
				}
				else{
					worldGenMinable = new WorldGenMinable(OPBlocks.BlockDarkSteelOre.getDefaultState(), 3);
				}
				
				worldGenMinable.generate(world, rand, new BlockPos(randX, randY, randZ));
			}
			
		}
		
		
		
		
		
	}

	private void clear(World world, int x, int y, int z, int sizeX, int sizeY, int sizeZ, Rotation rot){

		for(int i = 0; i <= sizeX; i++){
			for(int j = 0; j <= sizeY; j++){
				for(int k = 0; k <= sizeZ; k++){
					world.setBlockToAir(new BlockPos(x, y, z).add(i, j, k));
				}
			}
		}

	}

	private void generateEnd(World world, Random rand, int blockX, int blockZ) 
	{
	}

    private Template getTemplate(String fileName, World world){

        WorldServer worldserver = (WorldServer)world;
        MinecraftServer minecraftserver = world.getMinecraftServer();
        TemplateManager templatemanager = worldserver.getStructureTemplateManager();
        return templatemanager.get(minecraftserver, new ResourceLocation(OPCraft.MODID, fileName));

    }

	public static int getGroundFromAbove(World world, int x, int z)
	{
		int y = 255;
		boolean foundGround = false;
		while(!foundGround && y >= 0)
		{
			Block blockAt = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			// "ground" for our bush is grass or dirt
			foundGround = blockAt == Blocks.DIRT || blockAt == Blocks.GRASS;
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
		for(int i = 0; i < heights.size(); i++){
			totalSum += heights.get(i);
		}
		if(lowestBlockMode){
			Collections.sort(heights);
			return heights.get(0);
		}
		else{
		return totalSum / (radius*2*radius*2);
	}
	}

	private Rotation randomRot(Random rand) {
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

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
				int blockX = chunkX * 16;
				int blockZ = chunkZ * 16;
				switch(world.provider.getDimension())
				{
				case -1: generateNether(world, random, blockX, blockZ);
				break;
				case 0: generateOverworld(world, random, blockX, blockZ);
				break;
				case 1: generateEnd(world, random, blockX, blockZ);
				break;
				}
		
	}
}