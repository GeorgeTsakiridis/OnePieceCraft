package georgetsak.opcraft.common.capability;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.capability.bounty.BountyCap;
import georgetsak.opcraft.common.capability.bounty.BountyCapProvider;
import georgetsak.opcraft.common.capability.bounty.IBountyCap;
import georgetsak.opcraft.common.capability.devilfruits.DevilFruitsCap;
import georgetsak.opcraft.common.capability.devilfruits.DevilFruitsCapProvider;
import georgetsak.opcraft.common.capability.devilfruits.IDevilFruitsCap;
import georgetsak.opcraft.common.capability.haki.HakiCap;
import georgetsak.opcraft.common.capability.haki.HakiCapProvider;
import georgetsak.opcraft.common.capability.haki.IHakiCap;
import georgetsak.opcraft.common.capability.sixpowers.ISixPowersCap;
import georgetsak.opcraft.common.capability.sixpowers.SixPowersCap;
import georgetsak.opcraft.common.capability.sixpowers.SixPowersCapProvider;
import georgetsak.opcraft.common.capability.stats.normal.IStatsNormalCap;
import georgetsak.opcraft.common.capability.stats.normal.StatsNormalCap;
import georgetsak.opcraft.common.capability.stats.normal.StatsNormalCapProvider;
import georgetsak.opcraft.common.network.packets.*;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by GeorgeProgramming on 7/11/2017.
 */
public class CapabilityHandler {

    public static final ResourceLocation DF_CAP = new ResourceLocation(OPCraft.MODID, "devilfruits");
    public static final ResourceLocation B_CAP = new ResourceLocation(OPCraft.MODID, "bounty");
    public static final ResourceLocation SN_CAP = new ResourceLocation(OPCraft.MODID, "normalstats");
    public static final ResourceLocation H_CAP = new ResourceLocation(OPCraft.MODID, "haki");
    public static final ResourceLocation SP_CAP = new ResourceLocation(OPCraft.MODID, "sixpowers");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent event)
    {

        Object object = event.getObject();

        if (!(object instanceof EntityPlayer)) return;
        event.addCapability(DF_CAP, new DevilFruitsCapProvider());
        event.addCapability(B_CAP, new BountyCapProvider());
        event.addCapability(SN_CAP, new StatsNormalCapProvider());
        event.addCapability(H_CAP, new HakiCapProvider());
        event.addCapability(SP_CAP, new SixPowersCapProvider());
    }

    @SubscribeEvent
    public void playerLoggedIn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event)
    {
        EntityPlayer player = event.player;

        PacketDispatcher.sendTo(new DevilFruitCapPacket(DevilFruitsCap.get(player)), (EntityPlayerMP) player);
        PacketDispatcher.sendTo(new BountyPacket(BountyCap.get(player)), (EntityPlayerMP)player);
        PacketDispatcher.sendTo(new StatsNormalPacket(StatsNormalCap.get(player)), (EntityPlayerMP) player);
        PacketDispatcher.sendTo(new HakiPacket(HakiCap.get(player)), (EntityPlayerMP) player);
        PacketDispatcher.sendTo(new SixPowersPacket(SixPowersCap.get(player)), (EntityPlayerMP) player);

        if(!OPCraft.IS_RELEASE_VERSION){
            player.sendMessage(new TextComponentString("You are using an unreleased version of OPCraft. Some debug chat messages will be displayed when you do various things. Don't worry these will not be at the released version!"));
        }

    }

    @SubscribeEvent
    public void onPlayerSpawn(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntity();

            if (!player.world.isRemote)
            {
                IDevilFruitsCap df = DevilFruitsCap.get(player);
                IBountyCap b = BountyCap.get(player);
                IStatsNormalCap sn = StatsNormalCap.get(player);
                IHakiCap h = HakiCap.get(player);
                ISixPowersCap sp = SixPowersCap.get(player);

                PacketDispatcher.sendTo(new DevilFruitCapPacket(df), (EntityPlayerMP)player);
                PacketDispatcher.sendTo(new BountyPacket(b), (EntityPlayerMP)player);
                PacketDispatcher.sendTo(new StatsNormalPacket(sn), (EntityPlayerMP)player);
                PacketDispatcher.sendTo(new HakiPacket(h), (EntityPlayerMP)player);
                PacketDispatcher.sendTo(new SixPowersPacket(sp), (EntityPlayerMP)player);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        EntityPlayer player = event.getEntityPlayer();
        IDevilFruitsCap devilFruitsCap = DevilFruitsCap.get(player);
        IDevilFruitsCap oldDevilFruitCap = DevilFruitsCap.get(event.getOriginal());
        devilFruitsCap.copy(oldDevilFruitCap, player);

        IBountyCap bountyCap = BountyCap.get(player);
        IBountyCap oldBountyCap = BountyCap.get(event.getOriginal());
        bountyCap.copy(oldBountyCap, player);

        IStatsNormalCap statsNormalCap = StatsNormalCap.get(player);
        IStatsNormalCap oldStatsNormalCap = StatsNormalCap.get(event.getOriginal());
        statsNormalCap.copy(oldStatsNormalCap, player);

        IHakiCap hakiCap = HakiCap.get(player);
        IHakiCap oldHakiCap = HakiCap.get(event.getOriginal());
        hakiCap.copy(oldHakiCap, player);

        ISixPowersCap sixPowersCap = SixPowersCap.get(player);
        ISixPowersCap oldSixPowersCap = SixPowersCap.get(event.getOriginal());
        sixPowersCap.copy(oldSixPowersCap, player);

    }
}
