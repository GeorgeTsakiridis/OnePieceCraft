package georgetsak.opcraft.common.util;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.capability.devilfruitlevels.DevilFruitLevelsCap;
import georgetsak.opcraft.common.capability.devilfruitlevels.IDevilFruitLevelsCap;
import georgetsak.opcraft.common.registry.OPBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class PowerUtils {

    public static void createLightnings(EntityPlayer ep, int repeatTimes, double radius, int chance){

        double x = ep.posX;
        double z = ep.posZ;

        Random r = new Random();
        World world = ep.getServer().getEntityWorld();
        List<Entity> entities = OPUtils.getNearbyEntitiesExcluding(ep, radius, ep);

        for(int i = 0; i < repeatTimes; i++){
            //Has 1/chance to hit an entity or player per loop.
            if(r.nextInt(chance) == 1){
                if(r.nextBoolean()) {
                    int num = r.nextInt(entities.size());
                    if (entities.get(num) instanceof EntityLiving) {
                        EntityLiving entity = (EntityLiving)entities.get(num);
                        world.addWeatherEffect(new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ, OPCraft.config.disableGriefing.getValue()));
                    }
                }
                continue;
            }

            double finalX = x + (r.nextDouble() * (radius * 2D) - radius);
            double finalZ = z + (r.nextDouble() * (radius * 2D) - radius);
            double finalY = world.getTopSolidOrLiquidBlock(new BlockPos(finalX, 255, finalZ)).getY();

            world.addWeatherEffect(new EntityLightningBolt(world, finalX, finalY, finalZ, false));
        }
    }

    public static void createIceSeaRoad(EnumFacing ef, BlockPos startPoint, EntityPlayer ep){

        Block roadMat = OPBlocks.ICE_AGE;
        World world = ep.world;

        int level = DevilFruitLevelsCap.get(ep).getPowerLevel(3);

        switch(ef){
            case EAST:
                for(int i = 0; i < 200 + level*50; i++){
                    for(int h = -2; h<2; h++) {
                        if (isBlockWater(ep, startPoint.add(i, h, 0))) {
                            world.setBlockState(startPoint.add(i, h, 0), roadMat.getDefaultState());
                        }
                        if(isBlockWater(ep, startPoint.add(i, h, 1))) {
                            world.setBlockState(startPoint.add(i, h, 1), roadMat.getDefaultState());
                        }
                        if (isBlockWater(ep, startPoint.add(i, h, -1))) {
                            world.setBlockState(startPoint.add(i, h, -1), roadMat.getDefaultState());
                        }
                    }
                }
                break;
            case WEST:
                for(int i = 0; i < 200; i++){
                    for(int h = -2; h<2; h++) {
                        if (isBlockWater(ep, startPoint.add(-i, h, 0))) {
                            world.setBlockState(startPoint.add(-i, h, 0), roadMat.getDefaultState());
                        }
                        if (isBlockWater(ep, startPoint.add(-i, h, 1))) {
                            world.setBlockState(startPoint.add(-i, h, 1), roadMat.getDefaultState());
                        }
                        if (isBlockWater(ep, startPoint.add(-i, h, -1))) {
                            world.setBlockState(startPoint.add(-i, h, -1), roadMat.getDefaultState());
                        }
                    }
                }
                break;

            case SOUTH:
                for(int i = 0; i < 200; i++){
                    for(int h = -2; h<2; h++) {
                        if (isBlockWater(ep, startPoint.add(0, h, i))) {
                            world.setBlockState(startPoint.add(0, h, i), roadMat.getDefaultState());
                        }
                        if (isBlockWater(ep, startPoint.add(1, h, i))) {
                            world.setBlockState(startPoint.add(1, h, i), roadMat.getDefaultState());
                        }
                        if (isBlockWater(ep, startPoint.add(-1, h, i))) {
                            world.setBlockState(startPoint.add(-1, h, i), roadMat.getDefaultState());
                        }
                    }
                }
                break;

            case NORTH:
                for(int i = 0; i < 200; i++){
                    for(int h = -2; h<2; h++) {
                        if (isBlockWater(ep, startPoint.add(0, h, -i))) {
                            world.setBlockState(startPoint.add(0, h, -i), roadMat.getDefaultState());
                        }
                        if (isBlockWater(ep, startPoint.add(1, h, -i))) {
                            world.setBlockState(startPoint.add(1, h, -i), roadMat.getDefaultState());
                        }
                        if (isBlockWater(ep, startPoint.add(-1, h, -i))) {
                            world.setBlockState(startPoint.add(-1, h, -i), roadMat.getDefaultState());
                        }
                    }
                }
                break;
        }

    }

    public static boolean isBlockWater(EntityPlayer ep, BlockPos blockPos) {
        World world = ep.world;
        return world.getBlockState(blockPos).getBlock() == Blocks.WATER || world.getBlockState(blockPos).getBlock() == Blocks.FLOWING_WATER;
    }

    public static void destroyNearbyCropsAndGrass(EntityPlayer ep, int radius){
        BlockPos playerPos = new BlockPos(ep.posX, ep.posY, ep.posZ);
        World world = ep.getServer().getEntityWorld();

        for(int x = -radius; x < radius; x++){
            for(int y = -radius; y < radius; y++){
                for(int z = -radius; z < radius; z++){
                    BlockPos pos = playerPos.add(x, y, z);
                    Block blockFound = world.getBlockState(pos).getBlock();
                    if(blockFound == Blocks.DEADBUSH || blockFound == Blocks.DOUBLE_PLANT || blockFound == Blocks.YELLOW_FLOWER || blockFound == Blocks.RED_FLOWER
                            || blockFound == Blocks.SAPLING || blockFound == Blocks.WHEAT || blockFound == Blocks.CARROTS || blockFound == Blocks.POTATOES
                            || blockFound == Blocks.BEETROOTS || blockFound == Blocks.BROWN_MUSHROOM || blockFound == Blocks.RED_MUSHROOM || blockFound == Blocks.VINE
                            || blockFound == Blocks.WATERLILY){
                        world.destroyBlock(pos, false);
                    }
                }
            }
        }

    }

    public static BlockPos findCenterOfDome(EntityPlayer ep) {
        BlockPos domeCenter = new BlockPos(0, 0, 0);
        boolean foundCenter = false;

        BlockPos center = new BlockPos(ep.posX, ep.posY, ep.posZ);
        int x = center.getX();
        int y = center.getY();
        int z = center.getZ();

        int radius = 40;

        for (int i = x - radius; i < x + radius; i++) {
            for (int j = y - radius; j < y + radius; j++) {
                for (int k = z - radius; k < z + radius; k++) {
                    if (ep.getServer().getEntityWorld().getBlockState(new BlockPos(i, j, k)).getBlock() == OPBlocks.LAW_DOME_CENTER) {
                        foundCenter = true;
                        domeCenter = new BlockPos(i, j, k);
                        break;
                    }
                }
            }
        }
        if(foundCenter) return domeCenter;
        else{
            return null;
        }

    }

    public static void OPTakt(EntityPlayer ep){
        BlockPos DomeCenter = findCenterOfDome(ep);
        if(DomeCenter != null) {
            Random r = new Random();
            int radius = 6;
            int x = DomeCenter.getX();
            int y = DomeCenter.getY();
            int z = DomeCenter.getZ();

            World world = ep.getServer().getEntityWorld();

            for (int i = x - radius; i < x + radius; i++) {
                for (int j = y - 4; j < y + 4; j++) {
                    for (int k = z - radius; k < z + radius; k++) {
                        Block blockFound = world.getBlockState(new BlockPos(i, j, k)).getBlock();
                        boolean allowed = true;
                        for (Block block : OPUtils.nonMovableBlocks) {
                            if (block == blockFound) allowed = false;
                        }
                        if (allowed) {
                            if (canBlockSeeSky(new BlockPos(i, j, k), ep)) {


                                world.setBlockToAir(new BlockPos(i, j, k));
                                EntityFallingBlock entityFallingBlock = new EntityFallingBlock(world, i + (double) r.nextInt(radius * 5) - (double) r.nextInt(radius * 5), y + (double) r.nextInt(30), k + (double) r.nextInt(radius * 5) - (double) r.nextInt(radius * 5), blockFound.getBlockState().getBaseState());
                                entityFallingBlock.fallTime = 2;
                                // entityFallingBlock.addVelocity((r.nextInt(100) - 50) / 100, (r.nextInt(100) - 50) / 100, (r.nextInt(100) - 50) / 100);

                                world.spawnEntity(entityFallingBlock);

                            }
                        }
                    }
                }
            }

        }
    }

    public static boolean canBlockSeeSky(BlockPos blockPos, EntityPlayer ep) {
        int y = blockPos.getY();
        int x = blockPos.getX();
        int z = blockPos.getZ();
        World world = ep.getServer().getEntityWorld();

        for(int i = 256; i > y; i--){
            if(world.getBlockState(new BlockPos(x, i, z)) != Blocks.AIR.getDefaultState() && world.getBlockState(new BlockPos(x, i, z)) != Blocks.FIRE.getDefaultState() && world.getBlockState(new BlockPos(x, i, z)) != OPBlocks.LAW_DOME.getDefaultState() && world.getBlockState(new BlockPos(x, i, z)) != OPBlocks.LAW_DOME_CENTER.getDefaultState()){
                return false;
            }
        }
        return true;
    }

    public static void OPShambles(EntityPlayer ep){
        BlockPos DomeCenter = findCenterOfDome(ep);

        if(DomeCenter != null){
            double distance = 19.0D;
            Random r = new Random();
            List<Entity> entities = OPUtils.getNearbyEntitiesExcluding(ep, distance, ep);

            for(Entity entity : entities){
                int tpX = r.nextInt(37);
                int tpZ = r.nextInt(37);
                entity.setPosition(DomeCenter.getX() - distance + tpX, entity.getPosition().getY(), DomeCenter.getZ() - distance + tpZ);
            }
        }
    }

    public static void OPInjectionShot(EntityPlayer ep){

        BlockPos DomeCenter = findCenterOfDome(ep);
        if(DomeCenter != null){

            double distance = 19.0D;

            List<Entity> players = OPUtils.getNearbyEntities(ep, distance, EntityPlayer.class);

            if (!players.isEmpty()) {
                for(Entity entity : players){
                    EntityPlayer target = (EntityPlayer) entity;
                    if(target != ep){

                        IDevilFruitLevelsCap dfl = DevilFruitLevelsCap.get(ep);

                        ep.attemptTeleport(target.posX, target.posY, target.posZ);
                        target.attackEntityFrom(DamageSource.causePlayerDamage(ep), MathUtils.calculateDamage(target, 12F + dfl.getPowerLevel(3)*2F, true));
                        break;
                    }
                }
            }
        }
    }
}
