package georgetsak.opcraft.common.capability.bounty;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Created by GeorgeProgramming on 7/11/2017.
 */
public class BountyCapStorage implements Capability.IStorage<IBountyCap>{
    @Override
    public NBTBase writeNBT(Capability<IBountyCap> capability, IBountyCap instance, EnumFacing side) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setInteger("bounty", instance.getBounty());

        return compound;
    }

    @Override
    public void readNBT(Capability<IBountyCap> capability, IBountyCap instance, EnumFacing side, NBTBase nbt) {
        if(nbt instanceof NBTTagCompound){
            NBTTagCompound compound = (NBTTagCompound)nbt;
            instance.setBounty(compound.getInteger("bounty"));
        }
    }
}
