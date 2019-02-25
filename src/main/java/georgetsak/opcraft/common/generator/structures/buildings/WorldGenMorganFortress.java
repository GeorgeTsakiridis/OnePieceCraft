package georgetsak.opcraft.common.generator.structures.buildings;

import georgetsak.opcraft.OPCraft;
import net.minecraft.block.Block;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class WorldGenMorganFortress extends WorldGenerator
{

    public void setBlock(World world, int x, int y, int z, Block block, int metadata)
    {

        world.setBlockState(new BlockPos(x,y,z), block.getDefaultState());

    }

    public boolean generate(World world, Random rand, int i, int j, int k) {

        WorldServer worldServer = (WorldServer)world;
        MinecraftServer minecraftServer = world.getMinecraftServer();
        TemplateManager templateManager = worldServer.getStructureTemplateManager();
        Template template = templateManager.getTemplate(minecraftServer, new ResourceLocation(OPCraft.MODID, "buildings/morgan"));
        System.out.println(new ResourceLocation(OPCraft.MODID, "path.schematic"));
        if(template == null)
        {
            return false;
        }
        else
        {
            template.addBlocksToWorld(world, new BlockPos(i, j, k), new PlacementSettings());
        }
        return true;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        generate(worldIn, rand, position.getX(), position.getY(), position.getZ());
        return false;
    }
}