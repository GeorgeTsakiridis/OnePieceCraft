package georgetsak.opcraft.common.item;

import georgetsak.opcraft.common.network.packets.server.PacketDamageEntityServer;
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
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
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

	// 1:Water || 2:Lava || 3:Fire || 4:Impact || 5:Thunder
	public ItemDial(int type) {
		this.type = type;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			ItemStack stack = player.getHeldItem(hand);

			if (type == 3) {
				createFire(player.getPosition(), worldIn, player);
				stack.setCount(stack.getCount() - 1);
				if (stack.getCount() <= 0) {
					player.inventory.deleteStack(stack);
				}
			}

			if (type == 5) {
				worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false));
				stack.setCount(stack.getCount() - 1);
				if (stack.getCount() <= 0) {
					player.inventory.deleteStack(stack);
				}
			}
		}

		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}


	public static void createFire(BlockPos pos, World world, EntityPlayer player) {
		int range = 4;
		double Px = pos.getX();
		double Py = pos.getY();
		double Pz = pos.getZ();
		player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 40));

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
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
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
					} else if (ItemDial.tryPlaceContainedLiquid(playerIn, worldIn, blockpos1, type == 2)) {
						playerIn.addStat(Objects.requireNonNull(StatList.getObjectUseStats(this)));
						itemStack.setCount(itemStack.getCount() - 1);
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
				PacketDispatcher.sendToServer(new PacketDamageEntityServer(e, 8f));
				itemStack.setCount(itemStack.getCount() - 1);
				return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
			}
		}
		return new ActionResult<>(EnumActionResult.PASS, itemStack);

	}

	public static boolean tryPlaceContainedLiquid(EntityPlayer player, World world, BlockPos pos, boolean lava) {

		Block isFull = lava ? Blocks.FLOWING_LAVA : Blocks.FLOWING_WATER;

		IBlockState iblockstate = world.getBlockState(pos);
		Material material = iblockstate.getMaterial();
		boolean flag = !material.isSolid();
		boolean flag1 = iblockstate.getBlock().isReplaceable(world, pos);

		if (!world.isAirBlock(pos) && !flag && !flag1) {
			return false;
		} else {
			if (world.provider.doesWaterVaporize() && isFull == Blocks.FLOWING_WATER) {
				int l = pos.getX();
				int i = pos.getY();
				int j = pos.getZ();
				world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F,
						2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

				for (int k = 0; k < 8; ++k) {
					world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double) l + Math.random(),
							(double) i + Math.random(), (double) j + Math.random(), 0.0D, 0.0D, 0.0D);
				}
			} else {
				if (!world.isRemote && (flag || flag1) && !material.isLiquid()) {
					world.destroyBlock(pos, true);
				}

				SoundEvent soundevent = isFull == Blocks.FLOWING_LAVA ? SoundEvents.ITEM_BUCKET_EMPTY_LAVA
						: SoundEvents.ITEM_BUCKET_EMPTY;
				world.playSound(player, pos, soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
				world.setBlockState(pos, isFull.getDefaultState(), 11);
			}

			return true;
		}
	}


}
