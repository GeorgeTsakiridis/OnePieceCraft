package georgetsak.opcraft.common.capability;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.capability.bounty.BountyCap;
import georgetsak.opcraft.common.capability.bounty.BountyCapProvider;
import georgetsak.opcraft.common.capability.bounty.IBountyCap;
import georgetsak.opcraft.common.capability.devilfruitlevels.DevilFruitLevelsCap;
import georgetsak.opcraft.common.capability.devilfruitlevels.DevilFruitLevelsCapProvider;
import georgetsak.opcraft.common.capability.devilfruitlevels.IDevilFruitLevelsCap;
import georgetsak.opcraft.common.capability.devilfruits.DevilFruitCap;
import georgetsak.opcraft.common.capability.devilfruits.DevilFruitCapProvider;
import georgetsak.opcraft.common.capability.devilfruits.IDevilFruitCap;
import georgetsak.opcraft.common.capability.haki.HakiCap;
import georgetsak.opcraft.common.capability.haki.HakiCapProvider;
import georgetsak.opcraft.common.capability.haki.IHakiCap;
import georgetsak.opcraft.common.capability.sixpowers.ISixPowersCap;
import georgetsak.opcraft.common.capability.sixpowers.SixPowersCap;
import georgetsak.opcraft.common.capability.sixpowers.SixPowersCapProvider;
import georgetsak.opcraft.common.capability.stats.normal.IStatsNormalCap;
import georgetsak.opcraft.common.capability.stats.normal.StatsNormalCap;
import georgetsak.opcraft.common.capability.stats.normal.StatsNormalCapProvider;
import georgetsak.opcraft.common.network.packets.client.PacketBountyClient;
import georgetsak.opcraft.common.network.packets.client.PacketDevilFruitClient;
import georgetsak.opcraft.common.network.packets.common.PacketDevilFruitLevels;
import georgetsak.opcraft.common.network.packets.client.PacketStatsNormalClient;
import georgetsak.opcraft.common.network.packets.common.PacketHaki;
import georgetsak.opcraft.common.network.packets.common.PacketSixPowers;
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

    private static final ResourceLocation DEVIL_FRUIT_CAP = new ResourceLocation(OPCraft.MODID, "devilfruits");
    private static final ResourceLocation BOUNTY_CAP = new ResourceLocation(OPCraft.MODID, "bounty");
    private static final ResourceLocation NORMAL_STATS_CAP = new ResourceLocation(OPCraft.MODID, "normalstats");
    private static final ResourceLocation HAKI_CAP = new ResourceLocation(OPCraft.MODID, "haki");
    private static final ResourceLocation SIX_POWERS_CAP = new ResourceLocation(OPCraft.MODID, "sixpowers");
    private static final ResourceLocation DEVIL_FRUIT_LEVELS_CAP = new ResourceLocation(OPCraft.MODID, "devilfruitlevels");
    
    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent event)
    {

        Object object = event.getObject();

        if (!(object instanceof EntityPlayer)) return;
        event.addCapability(DEVIL_FRUIT_CAP, new DevilFruitCapProvider());
        event.addCapability(BOUNTY_CAP, new BountyCapProvider());
        event.addCapability(NORMAL_STATS_CAP, new StatsNormalCapProvider());
        event.addCapability(HAKI_CAP, new HakiCapProvider());
        event.addCapability(SIX_POWERS_CAP, new SixPowersCapProvider());
        event.addCapability(DEVIL_FRUIT_LEVELS_CAP,new DevilFruitLevelsCapProvider());
    }

    @SubscribeEvent
    public void playerLoggedIn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event)
    {
        EntityPlayer player = event.player;
        EntityPlayerMP playerMP = (EntityPlayerMP)player;
        
        PacketDispatcher.sendTo(new PacketDevilFruitClient(DevilFruitCap.get(player)), playerMP);
        PacketDispatcher.sendTo(new PacketBountyClient(BountyCap.get(player)), (EntityPlayerMP)player);
        PacketDispatcher.sendTo(new PacketStatsNormalClient(StatsNormalCap.get(player)), playerMP);
        PacketDispatcher.sendTo(new PacketHaki(HakiCap.get(player)), playerMP);
        PacketDispatcher.sendTo(new PacketSixPowers(SixPowersCap.get(player)), playerMP);
        PacketDispatcher.sendTo(new PacketDevilFruitLevels(DevilFruitLevelsCap.get(player)), playerMP);

        if(!OPCraft.IS_RELEASE_VERSION){
            player.sendMessage(new TextComponentString(TextFormatting.GOLD + "==============================="));
            player.sendMessage(new TextComponentString(TextFormatting.RED + "Unreleased Version of OPCraft (V" + OPCraft.VERSION + ")"));
            player.sendMessage(new TextComponentString(TextFormatting.GOLD + "You are using an unreleased version of OPCraft! Some features may not work or even crash the game!"));
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
                IDevilFruitCap devilFruitsCap = DevilFruitCap.get(player);
                IBountyCap bountyCap = BountyCap.get(player);
                IStatsNormalCap statsNormalCap = StatsNormalCap.get(player);
                IHakiCap hakiCap = HakiCap.get(player);
                ISixPowersCap sixPowersCap = SixPowersCap.get(player);
                IDevilFruitLevelsCap devilFruitLevelsCap = DevilFruitLevelsCap.get(player);

                EntityPlayerMP playerMP = (EntityPlayerMP)player;

                PacketDispatcher.sendTo(new PacketDevilFruitClient(devilFruitsCap), playerMP);
                PacketDispatcher.sendTo(new PacketBountyClient(bountyCap), playerMP);
                PacketDispatcher.sendTo(new PacketStatsNormalClient(statsNormalCap), playerMP);
                PacketDispatcher.sendTo(new PacketHaki(hakiCap), playerMP);
                PacketDispatcher.sendTo(new PacketSixPowers(sixPowersCap), playerMP);
                PacketDispatcher.sendTo(new PacketDevilFruitLevels(devilFruitLevelsCap), playerMP);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        EntityPlayer player = event.getEntityPlayer();

        IDevilFruitCap devilFruitsCap = DevilFruitCap.get(player);
        IDevilFruitCap oldDevilFruitCap = DevilFruitCap.get(event.getOriginal());
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

        IDevilFruitLevelsCap devilFruitLevelsCap = DevilFruitLevelsCap.get(player);
        IDevilFruitLevelsCap oldDevilFruitLevelsCap = DevilFruitLevelsCap.get(event.getOriginal());
        devilFruitLevelsCap.copy(oldDevilFruitLevelsCap,player);

    }
}
