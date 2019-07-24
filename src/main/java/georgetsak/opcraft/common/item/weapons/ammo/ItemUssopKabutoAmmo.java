package georgetsak.opcraft.common.item.weapons.ammo;

import georgetsak.opcraft.common.entity.other.EntityKabutoAmmo;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemUssopKabutoAmmo extends Item
{
    public ItemUssopKabutoAmmo()
    {
    }

    public EntityKabutoAmmo createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
    {
        EntityKabutoAmmo entitytippedarrow = new EntityKabutoAmmo(worldIn, shooter);
        return entitytippedarrow;
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.EntityPlayer player)
    {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.INFINITY, bow);
        return enchant > 0 && this.getClass() == ItemUssopKabutoAmmo.class;
    }
}