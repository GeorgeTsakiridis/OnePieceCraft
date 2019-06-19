package georgetsak.opcraft.common.entity;

import georgetsak.opcraft.common.capability.bounty.BountyCapProvider;
import georgetsak.opcraft.common.capability.bounty.IBountyCap;
import georgetsak.opcraft.common.network.packets.client.BountyClientPacket;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * Created by GeorgeTsak on 7/13/2017.
 */
public class EntityBounty extends EntityMob{

    private int bounty = 0;

    public EntityBounty(World worldIn) {
        super(worldIn);
    }

    public void setBounty(int bounty) {
        this.bounty = bounty;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if(cause.getTrueSource() instanceof EntityPlayer && !world.isRemote){
            IBountyCap b = cause.getTrueSource().getCapability(BountyCapProvider.B_CAP, null);
            b.changeBountyBy(bounty);
            PacketDispatcher.sendTo(new BountyClientPacket(b), (EntityPlayerMP)cause.getTrueSource());
        }
    }
}
