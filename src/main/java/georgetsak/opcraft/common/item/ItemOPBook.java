package georgetsak.opcraft.common.item;

import georgetsak.opcraft.OPCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemOPBook extends Item{

    public ItemOPBook(){
        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack item = playerIn.getHeldItem(handIn);

        if(!item.hasTagCompound()){
            item.setTagCompound(new NBTTagCompound());
            item.getTagCompound().setInteger("page", 0);
        }
        playerIn.openGui(OPCraft.MODID, 0, worldIn, 0, 0, 0);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}

