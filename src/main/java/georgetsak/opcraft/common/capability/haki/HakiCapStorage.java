package georgetsak.opcraft.common.capability.haki;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class HakiCapStorage implements Capability.IStorage<IHakiCap>{

    @Override
    public NBTBase writeNBT(Capability<IHakiCap> capability, IHakiCap instance, EnumFacing side) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setInteger("dodge", instance.getDodgeLevel());
        compound.setInteger("emperor", instance.getEmperorLevel());
        compound.setInteger("attack", instance.getAttackLevel());
        compound.setInteger("defence", instance.getDefenceLevel());
        compound.setBoolean("unlocked_emperor_haki", instance.isEmperorHakiUnlocked());

        return compound;
    }

    @Override
    public void readNBT(Capability<IHakiCap> capability, IHakiCap instance, EnumFacing side, NBTBase nbt) {
        if(nbt instanceof NBTTagCompound){
            NBTTagCompound compound = (NBTTagCompound)nbt;
            instance.setDodgeLevel(compound.getInteger("dodge"));
            instance.setEmperorLevel(compound.getInteger("emperor"));
            instance.setAttackLevel(compound.getInteger("attack"));
            instance.setDefenceLevel(compound.getInteger("defence"));
            instance.setUnlockedEmperorHaki(compound.getBoolean("unlocked_emperor_haki"));
        }
    }}
