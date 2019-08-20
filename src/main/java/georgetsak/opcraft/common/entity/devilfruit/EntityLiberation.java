package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.util.MathUtils;
import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class EntityLiberation extends EntityFlying {

    EntityPlayer owner;
    Random r;
    ArrayList<IBlockState> savedBlocks;
    public EntityLiberation(World worldIn) {
        super(worldIn);
        savedBlocks = new ArrayList<>();
        r = new Random();
        setEntityInvulnerable(true);
    }


    public EntityLiberation(World world, BlockPos position, EntityPlayer owner){
        this(world);
        setPosition(position.getX(), position.getY(), position.getZ());
        this.owner = owner;
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn) {
    }

    @Override
    protected void collideWithEntity(Entity entityIn) {
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if(world.isRemote) {
            for(double x = -2; x < 2; x++){
                for(double z = - 2; z < 2; z++) {
                    if (r.nextBoolean()) {
                        double offsetX = (r.nextDouble() * 4 - 2d) / 16d;
                        double offsetZ = (r.nextDouble() * 4 - 2d) / 16d;
                        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX + x + offsetX, posY + 2d, posZ + z + offsetZ, 0, 0d, 0);
                    }
                }
            }
        }

        if (ticksExisted >= 160 && !world.isRemote){//8 seconds
            setDead();
        }

        if (!world.isRemote) {
            if (owner == null) {
                setDead();
                System.out.println("Owner was null");
            }
            boolean grief = !OPCraft.config.disableGriefing.getCurrentValue();

            if (!isDead) {
                if (ticksExisted < 60 && grief) {
                    for (int i = 0; i < 6; i++) {
                        int triesLeft = 10;
                        boolean done = false;
                        while (!done && triesLeft > 0) {

                            int x = getPosition().getX() + (r.nextInt(20) - 10);
                            int y = getPosition().getY() + (r.nextInt(10) - 5);
                            int z = getPosition().getZ() + (r.nextInt(20) - 10);
                            BlockPos positionTarget = new BlockPos(x, y, z);
                            IBlockState stateTarget = world.getBlockState(positionTarget);
                            Block blockTarget = stateTarget.getBlock();
                            boolean allowed = true;

                            for (Block block : OPUtils.nonMovableBlocks) {
                                if (block == blockTarget) {
                                    allowed = false;
                                    break;
                                }
                            }

                            if (allowed) {
                                savedBlocks.add(stateTarget);
                                world.setBlockToAir(positionTarget);
                                done = true;
                            } else {
                                triesLeft--;
                            }
                        }
                    }
                }

                if (ticksExisted > 80) {
                    if(grief) {
                        for (IBlockState blockState : savedBlocks) {
                            EntityFallingBlock fallingBlock = new EntityFallingBlock(world, posX, posY, posZ, blockState);
                            fallingBlock.addVelocity(r.nextDouble() * 2 - 1D, r.nextDouble() * 4, r.nextDouble() * 2 - 1D);
                            fallingBlock.setHurtEntities(true);
                            fallingBlock.fallTime = 1;
                            fallingBlock.shouldDropItem = true;
                            fallingBlock.hurtResistantTime = 40;
                            world.spawnEntity(fallingBlock);
                        }
                    }
                    for (Entity entity : OPUtils.getNearbyEntitiesExcluding(owner, 20, owner)) {
                        if(entity == this)continue;
                        if (entity instanceof EntityPlayer) {
                            entity.attackEntityFrom(OPUtils.causePlayerCustomDamage(owner,true), MathUtils.calculateDamage((EntityPlayer) entity, 16f, true));
                            continue;
                        }
                        entity.attackEntityFrom(OPUtils.causePlayerCustomDamage(owner,true), 16f);
                    }
                    setDead();
                }
            }
        }
    }

}
