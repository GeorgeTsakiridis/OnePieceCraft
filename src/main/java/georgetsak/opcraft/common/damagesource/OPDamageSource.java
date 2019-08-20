package georgetsak.opcraft.common.damagesource;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

import javax.annotation.Nullable;

public class OPDamageSource extends EntityDamageSource {

    private boolean isDevilFruit;

    public OPDamageSource(String damageTypeIn, @Nullable Entity damageSourceEntityIn, boolean isDevilFruit) {
        super(damageTypeIn, damageSourceEntityIn);
        this.isDevilFruit = isDevilFruit;
    }

    public boolean isDevilFruit(){
        return isDevilFruit;
    }

}
