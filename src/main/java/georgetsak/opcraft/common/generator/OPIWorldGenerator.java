package georgetsak.opcraft.common.generator;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.generator.terrain.WorldGenAdamTree;
import georgetsak.opcraft.common.generator.terrain.WorldGenCherryTree;
import georgetsak.opcraft.common.registry.OPBlocks;
import net.minecraft.init.Biomes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeHills;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

import static georgetsak.opcraft.common.util.WorldGenUtils.*;

public class OPIWorldGenerator implements IWorldGenerator
{

	private void generateNether(World world, Random rand, int blockX, int blockZ)
	{
	}

	private void generateOverworld(World world, Random rand, int blockX, int blockZ) {
		WorldGenerator worldGenCherryTree = new WorldGenCherryTree();
		WorldGenerator worldGenAdamTree = new WorldGenAdamTree();
		WorldGenerator worldGenMinable;

		Biome biome = world.getBiomeForCoordsBody(new BlockPos(blockX, 64, blockZ));

		if (biome == Biomes.PLAINS && world.getWorldType() != WorldType.FLAT) {
			int numBushes = rand.nextInt(2);
			for (int i = 0; i < numBushes; i++) {
				int randX = blockX + rand.nextInt(10);
				int randZ = blockZ + rand.nextInt(10);

				if (rand.nextInt(350) == 0) {
					worldGenAdamTree.generate(world, rand, new BlockPos(randX, getGroundFromAbove(world, randX + 2, randZ), randZ));
				} else {
					worldGenCherryTree.generate(world, rand, new BlockPos(randX, getGroundFromAbove(world, randX, randZ), randZ));
				}
			}
		}

		if (biome == Biomes.PLAINS && world.getWorldType() != WorldType.FLAT && OPCraft.config.enableMorganFortress.getCurrentValue()) {
			int randX = blockX + rand.nextInt(16);
			int randZ = blockZ + rand.nextInt(16);

			if (rand.nextInt(OPCraft.config.morganFortressSpawnChance.getCurrentValue()) == 0) {

				Template morgan_fortress_stage1 = getTemplate("morgan_fortress_stage1", world);
				Template morgan_fortress_stage2 = getTemplate("morgan_fortress_stage2", world);
				Rotation rot = getRandomRotation(rand);
				//rot
				PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(true).setChunk(null).setReplacedBlock(null);
				PlacementSettings placementsettingsTorch = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk(null).setReplacedBlock(null);

				int finalY = getAverageHeight(world, randX, randZ - 1, 109, false) + 1;

				if (morgan_fortress_stage1 != null && morgan_fortress_stage2 != null) {
					clear(world, randX, finalY, randZ, 102, 42, 52, Rotation.NONE);
					morgan_fortress_stage1.addBlocksToWorld(world, new BlockPos(randX, finalY, randZ), placementsettings);
					morgan_fortress_stage2.addBlocksToWorld(world, new BlockPos(randX, finalY, randZ), placementsettingsTorch);
				}
			}
		}

		if (OPCraft.config.enableSkypiea.getCurrentValue() && rand.nextInt(OPCraft.config.skypieaSpawnChance.getCurrentValue()) == 0 && !(biome instanceof BiomeHills)) {
			Template skypieaStage1 = getTemplate("skypiea_stage_1", world);
			Template skypieaStage2 = getTemplate("skypiea_stage_2", world);
			Rotation rotation = getRandomRotation(rand);
			PlacementSettings placementSettings = new PlacementSettings().setMirror(Mirror.NONE).setRotation(rotation).setIgnoreEntities(false).setChunk(null).setReplacedBlock(null);

			if (skypieaStage1 != null && skypieaStage2 != null) {
				skypieaStage1.addBlocksToWorld(world, new BlockPos(blockX, 200, blockZ), placementSettings);
				skypieaStage2.addBlocksToWorld(world, new BlockPos(blockX, 200, blockZ), placementSettings);
			}
		}

		if((biome == Biomes.OCEAN || biome == Biomes.DEEP_OCEAN) && rand.nextInt(2000) == 0){
			Template bridgeStage1 = getTemplate("tequila_wolf_bridge_stage_1", world);
			Template bridgeStage2 = getTemplate("tequila_wolf_bridge_stage_2", world);
			Rotation rotation = getRandomRotation(rand);
			PlacementSettings placementSettings = new PlacementSettings().setMirror(Mirror.NONE).setRotation(rotation).setIgnoreEntities(false).setChunk(null).setReplacedBlock(null);

			System.out.println("SPAWNED TEQUILA WOLF BRIDGE");

			if(bridgeStage1 != null && bridgeStage2 != null){
				int y = getSeaLevel(world, blockX,blockZ);
				bridgeStage1.addBlocksToWorld(world, new BlockPos(blockX, y+2, blockZ), placementSettings);
				bridgeStage2.addBlocksToWorld(world, new BlockPos(blockX, y+2, blockZ), placementSettings);
			}

		}
			//TODO enable me after boats are added
		if((biome == Biomes.OCEAN || biome == Biomes.DEEP_OCEAN) && false){
			int randX = blockX + rand.nextInt(16);
			int randZ = blockZ + rand.nextInt(16);

			if(rand.nextInt(10) == 0){

				Template boat = getTemplate("marine_boat", world);
				Rotation rot = getRandomRotation(rand);
				//rot
				PlacementSettings placementSettings = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk(null).setReplacedBlock(null);

				if (boat != null){
					System.out.println("Spawning boat at " + randX + " // " + randZ);
					boat.addBlocksToWorld(world, new BlockPos(randX, getSeaLevel(world, randX, randZ)-1, randZ), placementSettings);
				}
			}
		}
		
		if(biome == Biomes.OCEAN || biome == Biomes.DEEP_OCEAN || biome == Biomes.RIVER || biome == Biomes.FROZEN_RIVER || biome == Biomes.FROZEN_OCEAN){
			
			int kairosekiMIN = 1;
			int kairosekiMAX = 3;
			int kairoseki = kairosekiMIN + rand.nextInt(kairosekiMAX - kairosekiMIN);
			for(int x = 0; x < kairoseki; x++){
				int randX = blockX + rand.nextInt(10);
				int randZ = blockZ + rand.nextInt(10);
				int randY = rand.nextInt(15) + 5;
				
				worldGenMinable = new WorldGenMinable(OPBlocks.KAIROSEKI_STONE.getDefaultState(), 3);
				worldGenMinable.generate(world, rand, new BlockPos(randX, randY, randZ));
			}
			
		}
		
		if(true){//Just to keep organized.
			
			int steelMIN = 2;
			int steelMAX = 5;
			int steel = steelMIN + rand.nextInt(steelMAX - steelMIN);
			for(int x = 0; x < steel; x++){
				int randX = blockX + rand.nextInt(10);
				int randZ = blockZ + rand.nextInt(10);
				int randY = rand.nextInt(40) + 5;
				
				if(rand.nextInt(3) <= 1){
					worldGenMinable = new WorldGenMinable(OPBlocks.STEEL_ORE.getDefaultState(), 4);
				}
				else{
					worldGenMinable = new WorldGenMinable(OPBlocks.DARK_STEEL_ORE.getDefaultState(), 3);
				}
				
				worldGenMinable.generate(world, rand, new BlockPos(randX, randY, randZ));
			}
			
		}
		
		
		
		
		
	}

	private void generateEnd(World world, Random rand, int blockX, int blockZ) 
	{
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