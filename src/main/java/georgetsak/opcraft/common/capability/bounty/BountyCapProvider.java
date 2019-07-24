package georgetsak.opcraft.common.capability.bounty;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;

/**
 * Created by GeorgeProgramming on 7/11/2017.
 */
public class BountyCapProvider implements ICapabilitySerializable<NBTBase>{

    @CapabilityInject(IBountyCap.class)
    public static final Capability<IBountyCap> B_CAP = null;

    private IBountyCap instance = B_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == B_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == B_CAP ? B_CAP.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return B_CAP.getStorage().writeNBT(B_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        B_CAP.getStorage().readNBT(B_CAP, this.instance, null, nbt);
    }
}
