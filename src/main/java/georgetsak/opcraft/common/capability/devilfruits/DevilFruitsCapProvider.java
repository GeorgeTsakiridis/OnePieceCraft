package georgetsak.opcraft.common.capability.devilfruits;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;

/**
 * Created by GeorgeProgramming on 7/11/2017.
 */
public class DevilFruitsCapProvider implements ICapabilitySerializable<NBTBase>{

    @CapabilityInject(IDevilFruitsCap.class)
    public static final Capability<IDevilFruitsCap> DF_CAP = null;

    private IDevilFruitsCap instance = DF_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == DF_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == DF_CAP ? DF_CAP.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return DF_CAP.getStorage().writeNBT(DF_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        DF_CAP.getStorage().readNBT(DF_CAP, this.instance, null, nbt);
    }
}
