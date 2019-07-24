package georgetsak.opcraft.common.capability.stats.normal;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;

/**
 * Created by GeorgeTsak on 7/15/2017.
 */
public class StatsNormalCapProvider implements ICapabilitySerializable<NBTBase>{

    @CapabilityInject(IStatsNormalCap.class)
    public static final Capability<IStatsNormalCap> SN_CAP = null;

    private IStatsNormalCap instance = SN_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == SN_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == SN_CAP ? SN_CAP.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return SN_CAP.getStorage().writeNBT(SN_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        SN_CAP.getStorage().readNBT(SN_CAP, this.instance, null, nbt);
    }
}
