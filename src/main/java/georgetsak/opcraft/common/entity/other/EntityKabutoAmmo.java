package georgetsak.opcraft.common.entity.other;

import georgetsak.opcraft.common.item.ItemDial;
import georgetsak.opcraft.common.registry.OPItems;
import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;

public class EntityKabutoAmmo extends EntityTippedArrow implements IThrowableEntity {

	private Item ammo;

	public EntityKabutoAmmo(World worldIn) {
		super(worldIn);
	}
	public EntityKabutoAmmo(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}
	public EntityKabutoAmmo(World worldIn, EntityLivingBase shooter, ItemStack ammo) {
		super(worldIn, shooter);
		this.ammo = ammo.getItem();
	}

	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(OPItems.SMALL_ROCK);
	}

	@Override
	protected void onHit(RayTraceResult raytraceResultIn) {
		super.onHit(raytraceResultIn);

		if(!(shootingEntity instanceof EntityPlayer))return;

		Entity entityHit = raytraceResultIn.entityHit;
		EntityPlayer shooter = (EntityPlayer)shootingEntity;

		boolean isFireDial = ammo == OPItems.FIRE_DIAL;
		boolean isWaterDial = ammo == OPItems.WATER_DIAL;
		boolean isLavaDial = ammo == OPItems.LAVA_DIAL;
		boolean isThunderDial = ammo == OPItems.THUNDER_DIAL;
		boolean isImpactDial = ammo == OPItems.IMPACT_DIAL;
		boolean isSmallRock = ammo == OPItems.SMALL_ROCK;

		if(entityHit != null){
			if(isFireDial) {
				entityHit.setFire(4);
				ItemDial.createFire(entityHit.getPosition(), world, (EntityPlayer) shootingEntity);
			}
			else if(isWaterDial){
				ItemDial.tryPlaceContainedLiquid(shooter, world, entityHit.getPosition(),false);
			}
			else if(isLavaDial){
				ItemDial.tryPlaceContainedLiquid(shooter, world, entityHit.getPosition(),true);
			}
			else if(isThunderDial){
				world.addWeatherEffect(new EntityLightningBolt(world, entityHit.posX, entityHit.posY, entityHit.posZ, false));
			}
			else if(isImpactDial){
				entityHit.attackEntityFrom(DamageSource.causePlayerDamage(shooter),8f);
			}
			else if(isSmallRock){
				return;
			}
		}
		else{
			BlockPos pos = raytraceResultIn.getBlockPos();
			Block block = world.getBlockState(pos).getBlock();
			if(block != Blocks.AIR) {
				if (isFireDial) {
					ItemDial.createFire(pos, world, shooter);
				}
				else if(isWaterDial){
					ItemDial.tryPlaceContainedLiquid(shooter, world, pos.up(),false);
				}
				else if(isLavaDial){
					ItemDial.tryPlaceContainedLiquid(shooter, world, pos.up(),true);
				}
				else if(isThunderDial){
					world.addWeatherEffect(new EntityLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), false));
				}
				else if(isImpactDial){
					OPUtils.newExplosion(shooter, world, pos.getX(), pos.getY(), pos.getZ(), 1.5f, false, true);
				}
				else if(isSmallRock){
					return;
				}
			}
		}
		setDead();
	}

	@Override
	public Entity getThrower() {
		return shootingEntity;
	}

	@Override
	public void setThrower(Entity entity) {
		shootingEntity = entity;
	}
}