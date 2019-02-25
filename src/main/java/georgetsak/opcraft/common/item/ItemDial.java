package georgetsak.opcraft.common.item;

import georgetsak.opcraft.common.network.packets.DamageEntityPacket;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Objects;

public class ItemDial extends Item {

	int type;
	private Block isFull;

	// 1:Water || 2:Lava || 3:Fire || 4:Impact || 5:Thunder
	public ItemDial(int type) {
		this.type = type;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			ItemStack stack = player.getHeldItem(hand);

			if (type == 3) {
				createFire(worldIn, player);
				stack.setCount(stack.getCount() - 1);
				if (stack.getCount() <= 0) {
					player.inventory.deleteStack(stack);
				}
			}

			if (type == 5) {
				worldIn.spawnEntity(new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false));
				stack.setCount(stack.getCount() - 1);
				if (stack.getCount() <= 0) {
					player.inventory.deleteStack(stack);
				}
			}
		}

		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}


	private void createFire(World world, EntityPlayer player) {
		int range = 4;
		double Px = player.posX;
		double Py = player.posY;
		double Pz = player.posZ;

		for (int x = -range; x <= range; x++) {
			for (int z = -range; z <= range; z++) {
				for (int y = -3; y <= 3; y++) {
					if (world.getBlockState(new BlockPos(Px, Py, Pz).add(x, y, z)).getBlock() == Blocks.AIR
							&& (world.getBlockState(new BlockPos(Px, Py, Pz).add(x, y - 1, z)).getBlock() != Blocks.AIR
									|| world.getBlockState(new BlockPos(Px, Py, Pz).add(x, y - 1, z))
											.getBlock() != Blocks.FIRE)) {

						boolean flag1 = (x == 0 && y == 0 && z == 0) || (x == 1 && y == 0 && z == 0)
								|| (x == 0 && y == 0 && z == 1) || (x == -1 && y == 0 && z == 0)
								|| (x == 0 && y == 0 && z == -1) || (x == 1 && y == 0 && z == 1)
								|| (x == -1 && y == 0 && z == -1) || (x == 1 && y == 0 && z == -1)
								|| (x == -1 && y == 0 && z == 1);
						boolean flag2 = (x == 0 && y == 1 && z == 0) || (x == 1 && y == 1 && z == 0)
								|| (x == 0 && y == 1 && z == 1) || (x == -1 && y == 1 && z == 0)
								|| (x == 0 && y == 1 && z == -1) || (x == 1 && y == 1 && z == 1)
								|| (x == -1 && y == 1 && z == -1) || (x == 1 && y == 1 && z == -1)
								|| (x == -1 && y == 1 && z == 1);

						if (flag1 || flag2) {
							continue;
						} else {
							world.setBlockState(new BlockPos(Px, Py, Pz).add(x, y, z), Blocks.FIRE.getDefaultState());
						}
					}
				}
			}
		}

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){
		ItemStack itemStack = playerIn.getHeldItem(handIn);

		if (type == 1 || type == 2) {
			boolean flag = false;
			RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, flag);
			ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerIn, worldIn,
					itemStack, raytraceresult);
			if (ret != null)
				return ret;

			if (raytraceresult == null) {
				return new ActionResult<>(EnumActionResult.PASS, itemStack);
			} else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
				return new ActionResult<>(EnumActionResult.PASS, itemStack);
			} else {
				BlockPos blockpos = raytraceresult.getBlockPos();

				if (!worldIn.isBlockModifiable(playerIn, blockpos)) {
					return new ActionResult<>(EnumActionResult.FAIL, itemStack);
				} else if (flag) {
					if (!playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit,
							itemStack)) {
						return new ActionResult<>(EnumActionResult.FAIL, itemStack);
					}
				} else {
					boolean flag1 = worldIn.getBlockState(blockpos).getBlock().isReplaceable(worldIn, blockpos);
					BlockPos blockpos1 = flag1 && raytraceresult.sideHit == EnumFacing.UP ? blockpos
							: blockpos.offset(raytraceresult.sideHit);

					if (!playerIn.canPlayerEdit(blockpos1, raytraceresult.sideHit, itemStack)) {
						return new ActionResult<>(EnumActionResult.FAIL, itemStack);
					} else if (this.tryPlaceContainedLiquid(playerIn, worldIn, blockpos1)) {
						playerIn.addStat(Objects.requireNonNull(StatList.getObjectUseStats(this)));
						itemStack.setCount(itemStack.getCount()-1);
						return !playerIn.capabilities.isCreativeMode
								? new ActionResult<>(EnumActionResult.SUCCESS, itemStack)
								: new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
					} else {
						return new ActionResult<>(EnumActionResult.FAIL, itemStack);
					}
				}
			}
			return ret;
		}

		if (type == 4) {

			Entity e = Minecraft.getMinecraft().pointedEntity;
			if (e instanceof EntityLiving || e instanceof EntityPlayer) {
				PacketDispatcher.sendToServer(new DamageEntityPacket(e, 4f));
                itemStack.setCount(itemStack.getCount()-1);
				return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
			}
		}
		return new ActionResult<>(EnumActionResult.PASS, itemStack);

	}

	public boolean tryPlaceContainedLiquid(EntityPlayer worldIn, World pos, BlockPos posIn) {

		if (type == 1) {
			this.isFull = Blocks.FLOWING_WATER;
		}
		if (type == 2) {
			this.isFull = Blocks.FLOWING_LAVA;
		}

		if (this.isFull == Blocks.AIR) {
			return false;
		} else {
			IBlockState iblockstate = pos.getBlockState(posIn);
			Material material = iblockstate.getMaterial();
			boolean flag = !material.isSolid();
			boolean flag1 = iblockstate.getBlock().isReplaceable(pos, posIn);

			if (!pos.isAirBlock(posIn) && !flag && !flag1) {
				return false;
			} else {
				if (pos.provider.doesWaterVaporize() && this.isFull == Blocks.FLOWING_WATER) {
					int l = posIn.getX();
					int i = posIn.getY();
					int j = posIn.getZ();
					pos.playSound(worldIn, posIn, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F,
							2.6F + (pos.rand.nextFloat() - pos.rand.nextFloat()) * 0.8F);

					for (int k = 0; k < 8; ++k) {
						pos.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double) l + Math.random(),
								(double) i + Math.random(), (double) j + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
					}
				} else {
					if (!pos.isRemote && (flag || flag1) && !material.isLiquid()) {
						pos.destroyBlock(posIn, true);
					}

					SoundEvent soundevent = this.isFull == Blocks.FLOWING_LAVA ? SoundEvents.ITEM_BUCKET_EMPTY_LAVA
							: SoundEvents.ITEM_BUCKET_EMPTY;
					pos.playSound(worldIn, posIn, soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
					pos.setBlockState(posIn, this.isFull.getDefaultState(), 11);
				}

				return true;
			}
		}
	}

}
