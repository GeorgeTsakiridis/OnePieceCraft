package georgetsak.opcraft.common.capability.stats.normal;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Created by GeorgeTsak on 7/15/2017.
 */
public class StatsNormalCapStorage implements Capability.IStorage<IStatsNormalCap>{

    @Override
    public NBTBase writeNBT(Capability<IStatsNormalCap> capability, IStatsNormalCap instance, EnumFacing side) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setInteger("health", instance.getHealthLevel());
        compound.setInteger("attack", instance.getAttackLevel());
        compound.setInteger("defence", instance.getDefenceLevel());
        compound.setInteger("speed", instance.getSpeedLevel());


        return compound;
    }

    @Override
    public void readNBT(Capability<IStatsNormalCap> capability, IStatsNormalCap instance, EnumFacing side, NBTBase nbt) {
        if(nbt instanceof NBTTagCompound){
            NBTTagCompound compound = (NBTTagCompound)nbt;
            instance.setHealthLevel(compound.getInteger("health"));
            instance.setAttackLevel(compound.getInteger("attack"));
            instance.setDefenceLevel(compound.getInteger("defence"));
            instance.setSpeedLevel(compound.getInteger("speed"));
        }
    }


}
