package georgetsak.opcraft.common;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.capability.bounty.BountyCap;
import georgetsak.opcraft.common.capability.bounty.IBountyCap;
import georgetsak.opcraft.common.capability.devilfruits.DevilFruitsCap;
import georgetsak.opcraft.common.capability.devilfruits.IDevilFruitsCap;
import georgetsak.opcraft.common.capability.haki.HakiCap;
import georgetsak.opcraft.common.capability.haki.IHakiCap;
import georgetsak.opcraft.common.capability.sixpowers.ISixPowersCap;
import georgetsak.opcraft.common.capability.sixpowers.SixPowersCap;
import georgetsak.opcraft.common.capability.stats.normal.StatsNormalCap;
import georgetsak.opcraft.common.crew.CrewSaveData;
import georgetsak.opcraft.common.entity.marine.EntityMarine;
import georgetsak.opcraft.common.entity.other.EntityBandit;
import georgetsak.opcraft.common.network.packets.client.BountyClientPacket;
import georgetsak.opcraft.common.network.packets.client.DevilFruitCapClientPacket;
import georgetsak.opcraft.common.network.packets.client.SyncCrewClientPacket;
import georgetsak.opcraft.common.network.packets.common.ConfigPacket;
import georgetsak.opcraft.common.network.packets.common.SixPowersPacket;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import georgetsak.opcraft.common.network.proxy.CommonProxy;
import georgetsak.opcraft.common.registry.OPDevilFruits;
import georgetsak.opcraft.common.registry.OPItems;
import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Random;

public class OPCommonEventHooks {


    public static final String TAG_PLAYER_HAS_MANUAL = "onepiececraft.hasBook";

    boolean once = true;


    @SideOnly(Side.SERVER)
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {

        if (event.player != null) {
            System.out.println("Player " + event.player.getDisplayName() + " joined. Sending Config.");
            PacketDispatcher.sendTo(new ConfigPacket(), (EntityPlayerMP) event.player);
            PacketDispatcher.sendTo(new SyncCrewClientPacket(CrewSaveData.get(event.player.world).getCrews()), (EntityPlayerMP)event.player);
        }

    }

    @SubscribeEvent
    public void onPlayerLoggedIn2(PlayerEvent.PlayerLoggedInEvent event) {
        PacketDispatcher.sendTo(new SyncCrewClientPacket(CrewSaveData.get(event.player.world).getCrews()), (EntityPlayerMP)event.player);

        NBTTagCompound playerData = event.player.getEntityData();//Gives the manual on first login.
        NBTTagCompound data;
        if (!playerData.hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
            data = playerData.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
            if (!data.getBoolean(TAG_PLAYER_HAS_MANUAL)) {
                ItemHandlerHelper.giveItemToPlayer(event.player, new ItemStack(OPItems.ItemManualBook, 1));
                data.setBoolean(TAG_PLAYER_HAS_MANUAL, true);
                playerData.setTag(EntityPlayer.PERSISTED_NBT_TAG, data);
            }
        }

    }

    @SubscribeEvent
    public void setAttackTargetEvent(LivingSetAttackTargetEvent event) {
        if (event.getEntity() instanceof EntityMarine && event.getTarget() instanceof EntityPlayer) {//Checks whether or not Marines should attack the player.
            EntityPlayer ep = (EntityPlayer) event.getTarget();

            if (!BountyCap.get(ep).isWanted()) {
                ((EntityLiving) event.getEntity()).setAttackTarget(null);
            }
        }

        if (event.getEntity() instanceof EntityBandit && event.getTarget() instanceof EntityPlayer) { //Checks whether or not Bandits should attack the player.
            EntityPlayer ep = (EntityPlayer) event.getTarget();
            if (BountyCap.get(ep).getBounty() > 5000) {
                ((EntityLiving) event.getEntity()).setAttackTarget(null);
            }
        }

    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void livingHurtEvent(LivingHurtEvent event) {
        Entity entity = event.getEntityLiving();
        EntityPlayer hurtPlayer = null;
        EntityPlayer attackerPlayer = null;

        if (entity instanceof EntityPlayer) {
            hurtPlayer = (EntityPlayer) entity;
        }

        if (event.getSource().getTrueSource() instanceof EntityPlayer) {
            attackerPlayer = (EntityPlayer) event.getSource().getTrueSource();
        }

        if (hurtPlayer != null) {

            if (event.getSource() == DamageSource.ANVIL) {//Increase ironDamageReceived for SixPowersCap
                if (!hurtPlayer.world.isRemote) {
                    ISixPowersCap hurtSixPowersCap = SixPowersCap.get(hurtPlayer);
                    hurtSixPowersCap.addIronDamage((int) event.getAmount());
                    PacketDispatcher.sendTo(new SixPowersPacket(hurtSixPowersCap), (EntityPlayerMP) hurtPlayer);
                }
            }

            if ((hurtPlayer.getHealth() - event.getAmount() <= 0)) {//Prevents the player from dying if DF#9 is equipped. Also TP to random location
                IDevilFruitsCap devilFruitsCap = DevilFruitsCap.get(hurtPlayer);
                if (devilFruitsCap.getPower() == OPDevilFruits.YOMI) {

                    hurtPlayer.sendMessage((new TextComponentString("You were revived by using Yomi Yomi no mi devil fruit power!")));
                    devilFruitsCap.setPower(0);
                    PacketDispatcher.sendTo(new DevilFruitCapClientPacket(devilFruitsCap), (EntityPlayerMP) hurtPlayer);

                    hurtPlayer.heal(20);

                    Random r = new Random();

                    BlockPos spawnpoint = hurtPlayer.world.getSpawnPoint();
                    double x = spawnpoint.getX() + (double) (r.nextInt(2000) - 1000);
                    double z = spawnpoint.getZ() + (double) (r.nextInt(2000) - 1000);
                    double y = hurtPlayer.world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY();

                    hurtPlayer.attemptTeleport(x, y, z);
                    event.setCanceled(true);
                }
            }

            if (!event.getSource().damageType.equals("outOfWorld")) {//Reduces damage according to defence stat.
                event.setAmount(event.getAmount() - event.getAmount() * StatsNormalCap.get(hurtPlayer).getDefenceLevel() * 5 / 100);
            }
        }

        if (attackerPlayer != null && !(attackerPlayer instanceof FakePlayer) ) {//TODO FIX: After Death user causes double damage to all Entities. Maybe an attribute is applied twice?
            if (attackerPlayer.getHeldItem(EnumHand.MAIN_HAND).isEmpty()) {//Increases punchDamageGiven field for SixPowersCap.
                if (!attackerPlayer.world.isRemote) {
                    ISixPowersCap attackerSixPowersCap = SixPowersCap.get(attackerPlayer);
                    attackerSixPowersCap.addPunchDamage(1);
                    PacketDispatcher.sendTo(new SixPowersPacket(attackerSixPowersCap), (EntityPlayerMP) attackerPlayer);
                }
            }
        }

    }

    @SubscribeEvent
    public void livingAttackEvent(LivingAttackEvent event) {
        //Used instead of LivingHurtEvent because it also cancels hit animation and sound. This is called from the attackers perspective.
        DamageSource source = event.getSource();

        Entity targetEntity = event.getEntity();
        Entity sourceEntity = source.getTrueSource();
        EntityPlayer targetPlayer = null;
        EntityPlayer sourcePlayer = null;

        if (targetEntity instanceof EntityPlayer) {
            targetPlayer = (EntityPlayer) targetEntity;
        }
        if (sourceEntity instanceof EntityPlayer) {
            sourcePlayer = (EntityPlayer) sourceEntity;
        }

        if (targetPlayer != null) {
            IDevilFruitsCap targetDevilFruitsCap = DevilFruitsCap.get(targetPlayer);

            if ((source.equals(DamageSource.IN_FIRE) || source.equals(DamageSource.ON_FIRE)) && targetDevilFruitsCap.getPower() == OPDevilFruits.MERA) {//Cancels fire damage if Lightning DF is equipped.
                event.setCanceled(true);
            }

            if (source.equals(DamageSource.LIGHTNING_BOLT) && targetDevilFruitsCap.getPower() == OPDevilFruits.GORO) {//Cancels lightning damage if Lightning DF is equipped.
                event.setCanceled(true);
            }

            if (sourcePlayer != null) {//Increases attack for Attack Haki Stat.
                IHakiCap sourceHakiCap = HakiCap.get(sourcePlayer);

                if (sourceHakiCap.getAttackLevel() > 0 && targetDevilFruitsCap.hasPower()) {
                    float newDamage = event.getAmount() * (float) sourceHakiCap.getAttackLevel() / 10f;
                    if (newDamage > 0) {
                        targetPlayer.attackEntityFrom(DamageSource.GENERIC, event.getAmount() + newDamage);
                    }
                }

            }
        }

    }

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event){
        Entity victimEntity = event.getEntity();
        Entity killerEntity = event.getSource().getTrueSource();
        EntityPlayer victimPlayer;
        EntityPlayer killerPlayer = null;
        IBountyCap victimBountyCap;

        if (victimEntity instanceof EntityPlayer) {

            victimPlayer = (EntityPlayer) victimEntity;
            victimBountyCap = BountyCap.get(victimPlayer);
        }else{
            return;
        }
        if (killerEntity instanceof EntityPlayer) {
            killerPlayer = (EntityPlayer) killerEntity;
        }

        if(killerPlayer != null){
            IBountyCap sourceBountyCap = BountyCap.get(killerPlayer);
            sourceBountyCap.changeBountyBy((int)(victimBountyCap.getBounty() * 0.30f));
            victimBountyCap.changeBountyBy((int)(-victimBountyCap.getBounty() * 0.35f));

            PacketDispatcher.sendTo(new BountyClientPacket(victimBountyCap), (EntityPlayerMP)victimPlayer);
            PacketDispatcher.sendTo(new BountyClientPacket(sourceBountyCap), (EntityPlayerMP)killerPlayer);

        }else {
            victimBountyCap.changeBountyBy((int) (-victimBountyCap.getBounty() * 0.1f));
            PacketDispatcher.sendTo(new BountyClientPacket(victimBountyCap), (EntityPlayerMP) victimPlayer);
        }
    }

    @SubscribeEvent
    public void onPlayerInteractEvent(PlayerInteractEvent.EntityInteractSpecific event){
        EntityPlayer attacker = event.getEntityPlayer();
        if(DevilFruitsCap.get(attacker).getPower() == OPDevilFruits.OPE && event.getTarget() instanceof EntityPlayer){
            EntityPlayer target = (EntityPlayer)event.getTarget();
            if(attacker.isPotionActive(CommonProxy.effectInsideDome) && target.isPotionActive(CommonProxy.effectInsideDome) && attacker.inventory.getCurrentItem() != null
                    &&  attacker.inventory.getCurrentItem().getItem() == OPItems.ItemKikokuOpen) {
                if (!attacker.world.isRemote) {
                    ItemStack heart = new ItemStack(OPItems.ItemLawHeart);
                    heart = OPUtils.setOwner(heart, target);
                    boolean hasHeart = false;
                    for (ItemStack itemStack : attacker.inventory.mainInventory) {//Check if player has already a heart. Cannot use vanilla method because it takes durability into consideration.
                        if (itemStack != null && itemStack.getItem().equals(OPItems.ItemLawHeart) && itemStack.hasTagCompound() && itemStack.getTagCompound().getString("owner").equals(target.getPersistentID().toString())) {
                            hasHeart = true;
                            break;
                        }
                    }
                    if (!hasHeart) attacker.inventory.addItemStackToInventory(OPUtils.setOwner(heart, target));
                }
            }
        }
    }

    @SubscribeEvent
    public void onJumpEvent(LivingEvent.LivingJumpEvent event){
        Entity entity = event.getEntity();
        if(entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            ISixPowersCap sixPowersCap = SixPowersCap.get(player);

            if (entity.world.isRemote) {
                if (player.isSprinting()) {
                    sixPowersCap.addRunningJumps(1);
                    PacketDispatcher.sendToServer(new SixPowersPacket(sixPowersCap));
                }
                else if(player.moveForward == 0 && player.moveStrafing == 0) {//moveForward & moveStrafing fields only work in remote worlds, so sending a packet will sync the server to the correct value.
                    sixPowersCap.addStillJumps(1);
                    PacketDispatcher.sendToServer(new SixPowersPacket(sixPowersCap));
                }
            }
        }
    }

    double distanceWalkedNormal = 0;
    double distanceWalkedInPlants = 0;
    @SubscribeEvent
    public void livingUpdateEvent(LivingEvent.LivingUpdateEvent event) {//Used to increase the six powers distanceRun and distanceRunInPlants fields.
        Entity entity = event.getEntity();
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            ISixPowersCap sixPowersCap = SixPowersCap.get(player);

            if (player.world.isRemote && !player.isCreative()) {
                BlockPos pos = player.getPosition();
                Block block = player.world.getBlockState(pos).getBlock();
                Block block2 = player.world.getBlockState(pos.down()).getBlock();
                if (block == Blocks.DOUBLE_PLANT || block == Blocks.REEDS || block2 == Blocks.DOUBLE_PLANT || block2 == Blocks.REEDS) {
                    distanceWalkedInPlants += player.distanceWalkedModified - player.prevDistanceWalkedModified;
                    if (distanceWalkedInPlants > 10d || distanceWalkedInPlants < 0d) {
                        distanceWalkedInPlants = 0d;
                        sixPowersCap.addDistanceRunInPlants(10);
                        PacketDispatcher.sendToServer(new SixPowersPacket(sixPowersCap));
                    }
                }
                else {
                    distanceWalkedNormal += player.distanceWalkedModified - player.prevDistanceWalkedModified;
                    if (distanceWalkedNormal > 10d || distanceWalkedNormal < 0d) {
                        distanceWalkedNormal = 0d;
                        sixPowersCap.addDistanceRun(10);
                        PacketDispatcher.sendToServer(new SixPowersPacket(sixPowersCap));
                    }
                }


            }
        }
    }

    @SubscribeEvent
    public void lootTableLoadEvent(LootTableLoadEvent event){
        boolean flag1 = event.getName() == LootTableList.CHESTS_ABANDONED_MINESHAFT;
        boolean flag2 = event.getName() == LootTableList.CHESTS_DESERT_PYRAMID;
        boolean flag3 = event.getName() == LootTableList.CHESTS_JUNGLE_TEMPLE;
        boolean flag4 = event.getName() == LootTableList.CHESTS_SIMPLE_DUNGEON;

        boolean flag = flag1 || flag2 || flag3 || flag4;
        int prob = 15;
        if (flag) {
            final LootPool main = event.getTable().getPool("main");
            if (main != null && OPCraft.config.enableDevilFruitsSpawning) {

                if(!OPCraft.config.completelyDisableDevilFruitGomu && OPCraft.config.enableDevilFruitGomuSpawning)main.addEntry(new LootEntryItem(OPDevilFruits.ItemDevilFruitGomu, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPluffyLoot"));
                if(!OPCraft.config.completelyDisableDevilFruitMera && OPCraft.config.enableDevilFruitMeraSpawning)main.addEntry(new LootEntryItem(OPDevilFruits.ItemDevilFruitMera, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPaceLoot"));
                if(!OPCraft.config.completelyDisableDevilFruitNoro && OPCraft.config.enableDevilFruitNoroSpawning)main.addEntry(new LootEntryItem(OPDevilFruits.ItemDevilFruitNoro, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPslowLoot"));
                if(!OPCraft.config.completelyDisableDevilFruitSuke && OPCraft.config.enableDevilFruitSukeSpawning)main.addEntry(new LootEntryItem(OPDevilFruits.ItemDevilFruitSuke, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPclearLoot"));
                if(!OPCraft.config.completelyDisableDevilFruitUshi && OPCraft.config.enableDevilFruitUshiSpawning)main.addEntry(new LootEntryItem(OPDevilFruits.ItemDevilFruitGiraffe, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPgiraffeLoot"));
                if(!OPCraft.config.completelyDisableDevilFruitOpe && OPCraft.config.enableDevilFruitOpeSpawning)main.addEntry(new LootEntryItem(OPDevilFruits.ItemDevilFruitOpe, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPlawLoot"));
                if(!OPCraft.config.completelyDisableDevilFruitHie && OPCraft.config.enableDevilFruitHieSpawning)main.addEntry(new LootEntryItem(OPDevilFruits.ItemDevilFruitHie, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPiceLoot"));
                if(!OPCraft.config.completelyDisableDevilFruitNikyu && OPCraft.config.enableDevilFruitNikyuSpawning)main.addEntry(new LootEntryItem(OPDevilFruits.ItemDevilFruitNikyu, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPpawLoot"));
                if(!OPCraft.config.completelyDisableDevilFruitYomi && OPCraft.config.enableDevilFruitYomiSpawning)main.addEntry(new LootEntryItem(OPDevilFruits.ItemDevilFruitYomi, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPreviveLoot"));
                if(!OPCraft.config.completelyDisableDevilFruitGoro && OPCraft.config.enableDevilFruitGoroSpawning)main.addEntry(new LootEntryItem(OPDevilFruits.ItemDevilFruitGoro, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPthunderLoot"));
                if(!OPCraft.config.completelyDisableDevilFruitMoku && OPCraft.config.enableDevilFruitMokuSpawning)main.addEntry(new LootEntryItem(OPDevilFruits.ItemDevilFruitMoku, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPsmokeLoot"));
                main.addEntry(new LootEntryItem(OPItems.ItemDevilFruitPowerRemover, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPdevilFruitRemoverLoot"));
            }
        }
    }

}
