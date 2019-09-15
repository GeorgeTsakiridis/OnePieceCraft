package georgetsak.opcraft.common.capability.kabuto;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class KabutoCapStorage implements Capability.IStorage<IKabutoCap>{

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IKabutoCap> capability, IKabutoCap instance, EnumFacing side) {
        NBTTagCompound compound = new NBTTagCompound();
        ItemStack itemStack = instance.getItemStackStored();

        compound.setInteger("itemID", Item.getIdFromItem(itemStack.getItem()));
        compound.setInteger("itemCount", itemStack.getCount());

        return compound;
    }

    @Override
    public void readNBT(Capability<IKabutoCap> capability, IKabutoCap instance, EnumFacing side, NBTBase nbt) {
        if(nbt instanceof NBTTagCompound){
            NBTTagCompound compound = (NBTTagCompound)nbt;

            ItemStack itemStack = new ItemStack(Item.getItemById(compound.getInteger("itemID")), compound.getInteger("itemCount"));
            instance.setItemStack(itemStack);
        }
    }
}
