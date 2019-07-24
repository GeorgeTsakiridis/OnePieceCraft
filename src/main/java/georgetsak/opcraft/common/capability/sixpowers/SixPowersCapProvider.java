package georgetsak.opcraft.common.capability.sixpowers;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;

/**
 * Created by GeorgeProgramming on 7/11/2017.
 */
public class SixPowersCapProvider implements ICapabilitySerializable<NBTBase>{

    @CapabilityInject(ISixPowersCap.class)
    public static final Capability<ISixPowersCap> SP_CAP = null;

    private ISixPowersCap instance = SP_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == SP_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == SP_CAP ? SP_CAP.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return SP_CAP.getStorage().writeNBT(SP_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        SP_CAP.getStorage().readNBT(SP_CAP, this.instance, null, nbt);
    }
}
