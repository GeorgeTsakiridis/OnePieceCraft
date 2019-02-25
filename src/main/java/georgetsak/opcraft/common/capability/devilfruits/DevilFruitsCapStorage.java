package georgetsak.opcraft.common.capability.devilfruits;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Created by GeorgeProgramming on 7/11/2017.
 */
public class DevilFruitsCapStorage implements Capability.IStorage<IDevilFruitsCap> {
    @Override
    public NBTBase writeNBT(Capability<IDevilFruitsCap> capability, IDevilFruitsCap instance, EnumFacing side) {
        NBTTagCompound compound = new NBTTagCompound();

        compound.setInteger("powerID", instance.getPower());
        return compound;
    }

    @Override
    public void readNBT(Capability<IDevilFruitsCap> capability, IDevilFruitsCap instance, EnumFacing side, NBTBase nbt) {
        if(nbt != null && nbt instanceof NBTTagCompound){
            NBTTagCompound compound = (NBTTagCompound)nbt;
            instance.setPower(compound.getInteger("powerID"));
        }

    }
}
