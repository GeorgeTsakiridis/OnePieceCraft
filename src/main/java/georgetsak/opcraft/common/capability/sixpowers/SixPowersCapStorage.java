package georgetsak.opcraft.common.capability.sixpowers;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Created by GeorgeProgramming on 7/11/2017.
 */
public class SixPowersCapStorage implements Capability.IStorage<ISixPowersCap>{
    @Override
    public NBTBase writeNBT(Capability<ISixPowersCap> capability, ISixPowersCap instance, EnumFacing side) {
        NBTTagCompound compound = new NBTTagCompound();

        compound.setInteger("stillJumps", instance.getStillJumps());
        compound.setInteger("ironDamageReceived", instance.getIronDamageReceived());
        compound.setInteger("punchDamageGiven", instance.getPunchDamageGiven());
        compound.setInteger("runningJumps", instance.getRunningJumps());
        compound.setInteger("distanceRun", instance.getDistanceRun());
        compound.setInteger("distanceRunInPlants", instance.getDistanceRunInPlants());

        return compound;
    }

    @Override
    public void readNBT(Capability<ISixPowersCap> capability, ISixPowersCap instance, EnumFacing side, NBTBase nbt) {
        if(nbt instanceof NBTTagCompound){
            NBTTagCompound compound = (NBTTagCompound)nbt;

            instance.setStillJumps(compound.getInteger("stillJumps"));
            instance.setIronDamageReceived(compound.getInteger("ironDamageReceived"));
            instance.setPunchDamageGiven(compound.getInteger("punchDamageGiven"));
            instance.setRunningJumps(compound.getInteger("runningJumps"));
            instance.setDistanceRun(compound.getInteger("distanceRun"));
            instance.setDistanceRunInPlants(compound.getInteger("distanceRunInPlants"));

        }
    }
}
