package georgetsak.opcraft.common.item.devilfruits;

import georgetsak.opcraft.common.capability.devilfruits.DevilFruitsCapProvider;
import georgetsak.opcraft.common.capability.devilfruits.IDevilFruitsCap;
import georgetsak.opcraft.common.registry.OPDevilFruits;
import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemDevilFruit extends ItemFood {

	int id;
	
	public ItemDevilFruit(int id) {
		super(1, false);
		this.id = id;
		this.setMaxStackSize(1);
	}

	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entityLiving)
    {
        stack.setCount(stack.getCount()-1);

        if (entityLiving instanceof EntityPlayer)
        {
        	EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            IDevilFruitsCap df = entityplayer.getCapability(DevilFruitsCapProvider.DF_CAP, null);

            if(df.hasPower()) {
				entityplayer.attackEntityFrom(new EntityDamageSource("ate more than one Devil Fruit", entityplayer), Float.MAX_VALUE);
				if (!world.isRemote) {
					OPUtils.createExplosion(entityLiving, world, entityLiving.posX, entityLiving.posY, entityLiving.posZ, 1, true);
				}
				if (world.isRemote && Minecraft.getMinecraft().player.isCreative()) {
					entityplayer.sendMessage(new TextComponentString("You cannot eat more than one devil fruit!"));
					entityplayer.sendMessage(new TextComponentString("Try eating a \"Devil Fruit Power Remover\" !"));
					stack.setCount(stack.getCount() - 1);
					return stack;
				}
			}
            else{
            	if(id != OPDevilFruits.GIRAFFE) {
					df.setPower(id);
				}
			}
        }

        return stack;
    }

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(DevilFruitAssetsManager.getDevilFruitAsset(id).getTooltip());
	}


	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
            playerIn.setActiveHand(hand);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(hand));
    }

}
