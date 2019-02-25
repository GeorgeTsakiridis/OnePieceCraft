package georgetsak.opcraft.common.entity.other;

import georgetsak.opcraft.common.registry.OPItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;

public class EntityKabutoAmmo extends EntityTippedArrow implements IThrowableEntity {
	public EntityKabutoAmmo(World worldIn) {
		super(worldIn);
	}

	public EntityKabutoAmmo(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityKabutoAmmo(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public void setPotionEffect(ItemStack stack) {
		super.setPotionEffect(new ItemStack(Items.ARROW)); // Mod arrows can't have potion effects
	}

	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(OPItems.ItemUssopKabutoAmmo);
	}

	/**
	 * Gets the entity that threw/created this entity.
	 *
	 * @return The owner instance, Null if none.
	 */
	@Override
	public Entity getThrower() {
		return shootingEntity;
	}

	/**
	 * Sets the entity that threw/created this entity.
	 *
	 * @param entity The new thrower/creator.
	 */
	@Override
	public void setThrower(Entity entity) {
		shootingEntity = entity;
	}
}