package georgetsak.opcraft.common.capability.devilfruitlevels;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;

/**
 * Created by GeorgeProgramming on 7/11/2017.
 */
public class DevilFruitLevelsCapProvider implements ICapabilitySerializable<NBTBase>{

    @CapabilityInject(IDevilFruitLevelsCap.class)
    public static final Capability<IDevilFruitLevelsCap> DFL_CAP = null;

    private IDevilFruitLevelsCap instance = DFL_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == DFL_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == DFL_CAP ? DFL_CAP.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return DFL_CAP.getStorage().writeNBT(DFL_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        DFL_CAP.getStorage().readNBT(DFL_CAP, this.instance, null, nbt);
    }
}
