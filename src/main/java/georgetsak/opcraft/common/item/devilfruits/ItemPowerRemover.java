package georgetsak.opcraft.common.item.devilfruits;

import georgetsak.opcraft.common.capability.devilfruits.DevilFruitsCap;
import georgetsak.opcraft.common.capability.devilfruits.DevilFruitsCapProvider;
import georgetsak.opcraft.common.capability.devilfruits.IDevilFruitsCap;
import georgetsak.opcraft.common.network.packets.client.DevilFruitCapClientPacket;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by GeorgeTsak on 7/12/2017.
 */
public class ItemPowerRemover extends ItemFood {

    public ItemPowerRemover() {
        super(0, 0, false);
        this.setAlwaysEdible();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if(!worldIn.isRemote && player.isCreative()){
            IDevilFruitsCap df = DevilFruitsCap.get(player);
            df.setPower(0);
            PacketDispatcher.sendTo(new DevilFruitCapClientPacket(df), (EntityPlayerMP) player);
        }
        return super.onItemRightClick(worldIn, player, hand);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        super.onFoodEaten(stack, worldIn, player);
        if(!worldIn.isRemote){
            IDevilFruitsCap df = DevilFruitsCap.get(player);
            df.setPower(0);
            PacketDispatcher.sendTo(new DevilFruitCapClientPacket(df), (EntityPlayerMP) player);
            player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 200, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200, 1));
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Eating this fruit will remove your current Devil Fruit.");
        //tooltip.add("It will also reset your Devil Fruit powers' levels."); //TODO uncomment this once the leveling system is implemented.
    }
}
