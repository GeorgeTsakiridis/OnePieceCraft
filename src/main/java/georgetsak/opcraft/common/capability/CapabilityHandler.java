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
import georgetsak.opcraft.common.network.packets.client.BountyClientPacket;
import georgetsak.opcraft.common.network.packets.client.DevilFruitCapClientPacket;
import georgetsak.opcraft.common.network.packets.client.StatsNormalClientPacket;
import georgetsak.opcraft.common.network.packets.common.HakiPacket;
import georgetsak.opcraft.common.network.packets.common.SixPowersPacket;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by GeorgeProgramming on 7/11/2017.
 */
public class CapabilityHandler {

    public static final ResourceLocation DEVIL_FRUITS_CAP = new ResourceLocation(OPCraft.MODID, "devilfruits");
    public static final ResourceLocation BOUNTY_CAP = new ResourceLocation(OPCraft.MODID, "bounty");
    public static final ResourceLocation NORMAL_STATS_CAP = new ResourceLocation(OPCraft.MODID, "normalstats");
    public static final ResourceLocation HAKI_CAP = new ResourceLocation(OPCraft.MODID, "haki");
    public static final ResourceLocation SIX_POWERS_CAP = new ResourceLocation(OPCraft.MODID, "sixpowers");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent event)
    {

        Object object = event.getObject();

        if (!(object instanceof EntityPlayer)) return;
        event.addCapability(DEVIL_FRUITS_CAP, new DevilFruitsCapProvider());
        event.addCapability(BOUNTY_CAP, new BountyCapProvider());
        event.addCapability(NORMAL_STATS_CAP, new StatsNormalCapProvider());
        event.addCapability(HAKI_CAP, new HakiCapProvider());
        event.addCapability(SIX_POWERS_CAP, new SixPowersCapProvider());
    }

    @SubscribeEvent
    public void playerLoggedIn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event)
    {
        EntityPlayer player = event.player;

        PacketDispatcher.sendTo(new DevilFruitCapClientPacket(DevilFruitsCap.get(player)), (EntityPlayerMP) player);
        PacketDispatcher.sendTo(new BountyClientPacket(BountyCap.get(player)), (EntityPlayerMP)player);
        PacketDispatcher.sendTo(new StatsNormalClientPacket(StatsNormalCap.get(player)), (EntityPlayerMP) player);
        PacketDispatcher.sendTo(new HakiPacket(HakiCap.get(player)), (EntityPlayerMP) player);
        PacketDispatcher.sendTo(new SixPowersPacket(SixPowersCap.get(player)), (EntityPlayerMP) player);


        if(!OPCraft.IS_RELEASE_VERSION){
            player.sendMessage(new TextComponentString(TextFormatting.GOLD + "==============================="));
            player.sendMessage(new TextComponentString(TextFormatting.RED + "Unreleased Version of OPCraft (V" + OPCraft.VERSION + ")"));
            player.sendMessage(new TextComponentString(TextFormatting.GOLD + "You are using an unreleased version of OPCraft. Some debug chat messages may be displayed. These will not be displayed in the released version."));
            player.sendMessage(new TextComponentString(TextFormatting.GOLD + "==============================="));
        }

    }

    @SubscribeEvent
    public void onPlayerSpawn(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntity();

            if (!player.world.isRemote) {
                IDevilFruitsCap devilFruitsCap = DevilFruitsCap.get(player);
                IBountyCap bountyCap = BountyCap.get(player);
                IStatsNormalCap statsNormalCap = StatsNormalCap.get(player);
                IHakiCap hakiCap = HakiCap.get(player);
                ISixPowersCap sixPowersCap = SixPowersCap.get(player);

                PacketDispatcher.sendTo(new DevilFruitCapClientPacket(devilFruitsCap), (EntityPlayerMP)player);
                PacketDispatcher.sendTo(new BountyClientPacket(bountyCap), (EntityPlayerMP)player);
                PacketDispatcher.sendTo(new StatsNormalClientPacket(statsNormalCap), (EntityPlayerMP)player);
                PacketDispatcher.sendTo(new HakiPacket(hakiCap), (EntityPlayerMP)player);
                PacketDispatcher.sendTo(new SixPowersPacket(sixPowersCap), (EntityPlayerMP)player);
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
