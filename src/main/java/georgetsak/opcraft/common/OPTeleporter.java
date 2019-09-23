package georgetsak.opcraft.common;

import georgetsak.opcraft.common.dimension.mirror.MirrorWorldProvider;
import georgetsak.opcraft.common.registry.OPBlocks;
import georgetsak.opcraft.common.registry.OPDimensions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ITeleporter;

public class OPTeleporter implements ITeleporter {

    private int targetDimensionID;
    private float rads;

    public OPTeleporter(int targetDimensionID, float rads){
        this.targetDimensionID = targetDimensionID;
        this.rads = rads;
    }

    @Override
    public void placeEntity(World world, Entity entity, float yaw) {



        if (targetDimensionID == OPDimensions.MIRROR.getId() && world.provider instanceof MirrorWorldProvider) {
            MirrorWorldProvider mirrorWorldProvider = (MirrorWorldProvider)world.provider;

            BlockPos pos = getCirclePosForRads(rads, 80f);

            int xOffset = (int) (Math.sin(rads * 10d + (2 * Math.PI) / 2d + mirrorWorldProvider.randX) * 10d);
            int zOffset = (int) (Math.cos(rads * 3d + mirrorWorldProvider.randZ) * 10d);

            pos = pos.add(xOffset, 90, zOffset);

            for(int i = 0; i < 30; i++){
                if(world.getBlockState(pos.down(i)).getBlock() == OPBlocks.MIRROR_WALL_BLOCK){
                    pos = pos.down(i - 2);
                }
            }

            System.out.println(pos);
            entity.moveToBlockPosAndAngles(pos, yaw, entity.rotationPitch);
        }
    }

    private BlockPos getCirclePosForRads(float rads, float radius){

        int posX = (int)(Math.cos(rads)*radius);
        int posZ = (int)(Math.sin(rads)*radius);

        return new BlockPos(posX, 0, posZ);
    }

    @Override
    public boolean isVanilla() {
        return false;
    }
}
