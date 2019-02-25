package georgetsak.opcraft.common.block;

import georgetsak.opcraft.common.registry.OPItems;
import georgetsak.opcraft.common.capability.devilfruits.DevilFruitsCapProvider;
import georgetsak.opcraft.common.capability.devilfruits.IDevilFruitsCap;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class BlockKairosekiStone extends Block {

	public BlockKairosekiStone(Material materialIn) {
		super(materialIn);
		this.setResistance(15);
		this.setHardness(3);
		this.setHarvestLevel("pickaxe", 2);
		this.setSoundType(SoundType.STONE);
	}


	@Override
	public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {

		Random rand = world instanceof World ? ((World) world).rand : new Random();
		return MathHelper.getInt(rand, 3, 7);

	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return OPItems.ItemKairosekiGem;
	}

	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof EntityPlayer) {

			IDevilFruitsCap df = entityIn.getCapability(DevilFruitsCapProvider.DF_CAP, null);
			if(df.hasPower()) {
				((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20, 3));
				((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 20, 3));
				((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 20, 3));
			}
			}

		super.onEntityWalk(worldIn, pos, entityIn);
	}
}