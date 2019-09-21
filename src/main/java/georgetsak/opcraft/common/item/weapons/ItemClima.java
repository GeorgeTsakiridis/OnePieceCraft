package georgetsak.opcraft.common.item.weapons;

import com.google.common.collect.Multimap;
import georgetsak.opcraft.common.registry.OPBlocks;
import georgetsak.opcraft.common.registry.OPItems;
import georgetsak.opcraft.client.OPSoundEvent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemClima extends Item
{	
    private final float attackDamage;
    
    int type = 0; //0 stands for null or climaBasic, 1 for Water, 2 for Fire, 3 for Thunder, ...
    boolean completed;
    
    public ItemClima(int durability, float attackDamage, int type, boolean completed)
    {
    	this.type = type;
        this.maxStackSize = 1;
        this.setMaxDamage(durability);
        this.attackDamage = attackDamage;
        this.completed = completed;
    }

	boolean killed = false;

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if(!world.isRemote) {
			ItemStack stack = player.getHeldItem(hand);
			if (completed) {
				if (player.isSneaking()) {
					ItemStack stack2;
					switch (type) {
						case 1:
							stack2 = new ItemStack(OPItems.CLIMA_COMPLETED_FIRE, 1);
							stack2.damageItem(this.getDamage(stack), player);
							player.inventory.setInventorySlotContents(player.inventory.currentItem, stack2);
							return super.onItemRightClick(world, player, hand);

						case 2:
							stack2 = new ItemStack(OPItems.CLIMA_COMPLETED_THUNDER, 1);
							stack2.damageItem(this.getDamage(stack), player);
							player.inventory.setInventorySlotContents(player.inventory.currentItem, stack2);
							return super.onItemRightClick(world, player, hand);

						case 3:
							stack2 = new ItemStack(OPItems.CLIMA_COMPLETED_WATER, 1);
							stack2.damageItem(this.getDamage(stack), player);
							player.inventory.setInventorySlotContents(player.inventory.currentItem, stack2);
							return super.onItemRightClick(world, player, hand);

					}
				} else {

					double distance = 10.0D;
					double x1 = player.posX - distance;
					double x2 = player.posX + distance;
					double y1 = player.posY - distance;
					double y2 = player.posY + distance;
					double z1 = player.posZ - distance;
					double z2 = player.posZ + distance;

					List<Entity> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(x1, y1, z1, x2, y2, z2));
					List<Entity> players = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(x1, y1, z1, x2, y2, z2));

					PotionEffect pe = null;
					boolean fire = false;
					boolean thunder = false;
					killed = false;

					switch (type) {
						case 1:
							world.playSound(player, new BlockPos(player.posX, player.posY, player.posZ), OPSoundEvent.clima_ice, SoundCategory.NEUTRAL, 1.0F, 1.0F);
							pe = new PotionEffect(MobEffects.SLOWNESS, 10 * 20, 5);
							freezeBlocks(world, player, 5);
							break;
						case 2:
							fire = true;
							world.playSound(player, new BlockPos(player.posX, player.posY, player.posZ), OPSoundEvent.clima_fire, SoundCategory.NEUTRAL, 1.0F, 1.0F);
							break;
						case 3:
							thunder = true;
							break;
					}

					if (!entities.isEmpty()) {
						for (int i = 0; i < entities.size(); i++) {
							if (entities.get(i) instanceof EntityLiving) {
								EntityLivingBase entitylivingbase = (EntityLivingBase) entities.get(i);
								killed = true;
								if (!fire && !thunder) {
									entitylivingbase.addPotionEffect(pe);
								} else if (fire) {
									entitylivingbase.setFire(5);
								} else if (thunder) {
									world.addWeatherEffect(new EntityLightningBolt(world, entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, false));
								}
							}
						}
					}
					if (!players.isEmpty()) {
						for (int i = 0; i < players.size(); i++) {
							if (players.get(i) instanceof EntityPlayer) {

								EntityPlayer players2 = (EntityPlayer) players.get(i);

								if (players2.inventory.getCurrentItem() == null) {
									killed = true;
									if (!fire && !thunder) {
										players2.addPotionEffect(pe);
									} else if (fire) {
										players2.setFire(5);
									} else if (thunder) {
										world.spawnEntity(new EntityLightningBolt(world, players2.posX, players2.posY, players2.posZ, false));
									}
								} else if (players2.inventory.getCurrentItem().getItem() != this) {
									killed = true;
									if (!fire && !thunder) {
										players2.addPotionEffect(pe);
									} else if (fire) {
										players2.setFire(5);
									} else if (thunder) {
										world.spawnEntity(new EntityLightningBolt(world, players2.posX, players2.posY, players2.posZ, false));
									}
								}
							}
						}
					}

				}


			}
			if (killed) {
				if (type == 3) {
					stack.damageItem(10, player);
				} else {
					stack.damageItem(1, player);
				}
			}
			player.getCooldownTracker().setCooldown(this, 20);
		}

		return super.onItemRightClick(world, player, hand);
	}


    private void freezeBlocks(World world, EntityPlayer player, int i) {
    	
    	int invertedI = -i;
    	BlockPos center = new BlockPos(player.posX, player.posY, player.posZ);
    	
    	for(int x = invertedI; x <= i; x++){
    		for(int y = -3; y <= 3; y++){
    			for(int z =invertedI; z <= i; z++){
    				if(world.getBlockState(center.add(x, y, z)).getBlock() == Blocks.WATER){
    					killed = true;
    					world.setBlockState(center.add(x,y,z), OPBlocks.TEMPORARY_ICE.getDefaultState());
    				}
    			}
    		}
    	}
    	
	}

	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
        return true;
    }

    public boolean canHarvestBlock(IBlockState blockIn)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    public int getItemEnchantability()
    {
        return 0;
    }


    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return false;
    }

    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.attackDamage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
        }

        return multimap;
    }
}