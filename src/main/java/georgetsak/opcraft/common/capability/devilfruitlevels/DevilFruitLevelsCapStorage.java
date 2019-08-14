package georgetsak.opcraft.common.capability.devilfruitlevels;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Created by GeorgeProgramming on 7/11/2017.
 */
public class DevilFruitLevelsCapStorage implements Capability.IStorage<IDevilFruitLevelsCap>{
    @Override
    public NBTBase writeNBT(Capability<IDevilFruitLevelsCap> capability, IDevilFruitLevelsCap instance, EnumFacing side) {
        NBTTagCompound compound = new NBTTagCompound();

        compound.setInteger("devilFruitID", instance.getDevilFruitID());
        compound.setIntArray("uses", instance.getAllPowersUses());
        compound.setIntArray("hits", instance.getAllPowersHits());

        return compound;
    }

    @Override
    public void readNBT(Capability<IDevilFruitLevelsCap> capability, IDevilFruitLevelsCap instance, EnumFacing side, NBTBase nbt) {
        if(nbt instanceof NBTTagCompound){
            NBTTagCompound compound = (NBTTagCompound)nbt;

            instance.setDevilFruitID(compound.getInteger("devilFruitID"));
            instance.setPowerUses(compound.getIntArray("uses"));
            instance.setPowerHits(compound.getIntArray("hits"));

        }
    }
}
