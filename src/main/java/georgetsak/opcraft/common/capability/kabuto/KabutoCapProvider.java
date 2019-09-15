package georgetsak.opcraft.common.capability.kabuto;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;

public class KabutoCapProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IKabutoCap.class)
    public static final Capability<IKabutoCap> K_CAP = null;

    private IKabutoCap instance = K_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == K_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == K_CAP ? K_CAP.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return K_CAP.getStorage().writeNBT(K_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        K_CAP.getStorage().readNBT(K_CAP, this.instance, null, nbt);
    }
}
