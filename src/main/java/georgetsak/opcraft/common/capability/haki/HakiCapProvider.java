package georgetsak.opcraft.common.capability.haki;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;

public class HakiCapProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IHakiCap.class)
    public static final Capability<IHakiCap> H_CAP = null;

    private IHakiCap instance = H_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == H_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == H_CAP ? H_CAP.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return H_CAP.getStorage().writeNBT(H_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        H_CAP.getStorage().readNBT(H_CAP, this.instance, null, nbt);
    }
}
