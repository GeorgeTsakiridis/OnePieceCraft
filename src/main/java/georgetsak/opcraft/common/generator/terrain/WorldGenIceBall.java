package georgetsak.opcraft.common.generator.terrain;

import georgetsak.opcraft.common.registry.OPBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenIceBall {

    private Block b = OPBlocks.ICE_CAGE;

    public WorldGenIceBall(){

    }

    public void generate(World w, BlockPos pos){
        int offsetX = pos.getX();
        int offsetY = pos.getY();
        int offsetZ = pos.getZ();

        for(int x = -1; x <= 1; x ++){
            for(int y = -1; y <= 1; y ++){
                for(int z = -1; z <= 1; z ++){
                    if(x != 0 || z != 0){
                        setBlock(w, offsetX + x, offsetY + y, offsetZ + z);
                    }
                }
            }

        }

            setBlock(w, offsetX - 2, offsetY, offsetZ);
            setBlock(w, offsetX + 2, offsetY, offsetZ);
            setBlock(w, offsetX, offsetY, offsetZ - 2);
            setBlock(w, offsetX, offsetY, offsetZ + 2);
            setBlock(w, offsetX, offsetY - 2, offsetZ);
            setBlock(w, offsetX, offsetY + 2, offsetZ);

    }

    private void setBlock(World w, int x, int y, int z)
    {
        if(w.getBlockState(new BlockPos(x, y , z)).getBlock() == Blocks.AIR) {
            w.setBlockState(new BlockPos(x, y, z), b.getDefaultState());
        }
    }

}
