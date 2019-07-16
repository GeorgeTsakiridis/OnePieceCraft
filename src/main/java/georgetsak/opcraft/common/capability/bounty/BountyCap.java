package georgetsak.opcraft.common.capability.bounty;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by GeorgeProgramming on 7/11/2017.
 */
public class BountyCap implements IBountyCap {

    private int bounty;

    @Override
    public void setBounty(int bounty) {
        this.bounty = bounty;
    }

    @Override
    public void changeBountyBy(int value) {
        if(bounty + value < 0) setBounty(0);
        else{
            setBounty(this.bounty + value);
        }
    }

    @Override
    public int getBounty() {
        return this.bounty;
    }

    @Override
    public boolean isWanted() {
        return this.bounty > 1000;
    }

    @Override
    public void copy(IBountyCap old_b, EntityPlayer ep) {

        this.setBounty(old_b.getBounty());
       // OPCraft.network.sendTo(new OPBountyCapClientSync(old_b), (EntityPlayerMP)ep);
    }

    public static IBountyCap get(EntityPlayer player)
    {
        return player.getCapability(BountyCapProvider.B_CAP, null);
    }


}
