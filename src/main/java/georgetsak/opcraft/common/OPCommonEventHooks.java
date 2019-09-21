package georgetsak.opcraft.common;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.capability.bounty.BountyCap;
import georgetsak.opcraft.common.capability.bounty.IBountyCap;
import georgetsak.opcraft.common.capability.devilfruitlevels.DevilFruitLevelsCap;
import georgetsak.opcraft.common.capability.devilfruitlevels.IDevilFruitLevelsCap;
import georgetsak.opcraft.common.capability.devilfruits.DevilFruitCap;
import georgetsak.opcraft.common.capability.devilfruits.IDevilFruitCap;
import georgetsak.opcraft.common.capability.haki.HakiCap;
import georgetsak.opcraft.common.capability.haki.IHakiCap;
import georgetsak.opcraft.common.capability.sixpowers.ISixPowersCap;
import georgetsak.opcraft.common.capability.sixpowers.SixPowersCap;
import georgetsak.opcraft.common.capability.stats.normal.StatsNormalCap;
import georgetsak.opcraft.common.crew.CrewSaveData;
import georgetsak.opcraft.common.crew.EnumRole;
import georgetsak.opcraft.common.crew.Member;
import georgetsak.opcraft.common.damagesource.OPDamageSource;
import georgetsak.opcraft.common.entity.marine.EntityMarine;
import georgetsak.opcraft.common.entity.other.EntityBandit;
import georgetsak.opcraft.common.item.weapons.swords.ItemSimpleSword;
import georgetsak.opcraft.common.item.weapons.swords.ItemSwordWithCase;
import georgetsak.opcraft.common.network.packets.client.PacketBountyClient;
import georgetsak.opcraft.common.network.packets.client.PacketDevilFruitClient;
import georgetsak.opcraft.common.network.packets.client.PacketSyncCrewClient;
import georgetsak.opcraft.common.network.packets.common.PacketConfig;
import georgetsak.opcraft.common.network.packets.common.PacketDevilFruitLevels;
import georgetsak.opcraft.common.network.packets.common.PacketSixPowers;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import georgetsak.opcraft.common.network.proxy.CommonProxy;
import georgetsak.opcraft.common.registry.OPDevilFruits;
import georgetsak.opcraft.common.registry.OPItems;
import georgetsak.opcraft.common.util.CrewUtils;
import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
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

    @SideOnly(Side.SERVER)
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {

        if (event.player != null) {
            PacketDispatcher.sendTo(new PacketSyncCrewClient(CrewSaveData.get(event.player.world).getCrews()), (EntityPlayerMP)event.player);
        }

    }

    @SubscribeEvent
    public void onPlayerLoggedIn2(PlayerEvent.PlayerLoggedInEvent event) {
        PacketDispatcher.sendTo(new PacketSyncCrewClient(CrewSaveData.get(event.player.world).getCrews()), (EntityPlayerMP)event.player);
        PacketDispatcher.sendTo(new PacketConfig(), (EntityPlayerMP) event.player);

        NBTTagCompound playerData = event.player.getEntityData();//Gives the manual on first login.
        NBTTagCompound data;
        if (!playerData.hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
            data = playerData.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
            if (!data.getBoolean(TAG_PLAYER_HAS_MANUAL)) {
                ItemHandlerHelper.giveItemToPlayer(event.player, new ItemStack(OPItems.MANUAL_BOOK, 1));
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
        //This is called from the victim's perspective
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
                    PacketDispatcher.sendTo(new PacketSixPowers(hurtSixPowersCap), (EntityPlayerMP) hurtPlayer);
                }
            }

            if ((hurtPlayer.getHealth() - event.getAmount() <= 0)) {//Prevents the player from dying if DF#9 is equipped. Also TP to random location
                IDevilFruitCap devilFruitsCap = DevilFruitCap.get(hurtPlayer);
                if (devilFruitsCap.getPower() == OPDevilFruits.YOMI_ID) {

                    hurtPlayer.sendMessage((new TextComponentString("You were revived by using Yomi Yomi no mi devil fruit power!")));
                    devilFruitsCap.setPower(0);
                    PacketDispatcher.sendTo(new PacketDevilFruitClient(devilFruitsCap), (EntityPlayerMP) hurtPlayer);

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

            Member member = CrewUtils.getMemberFromPlayer(CrewSaveData.get(hurtPlayer.world).getCrews(), hurtPlayer);//Reduces damage by 15% if player's role in a crew is Fighter
            if(member != null && member.getRole() == EnumRole.FIGHTER){
                event.setAmount(event.getAmount() - event.getAmount()*0.15f);
            }

        }

        if (attackerPlayer != null && !(attackerPlayer instanceof FakePlayer) ) {
            if (attackerPlayer.getHeldItem(EnumHand.MAIN_HAND).isEmpty()) {//Increases punchDamageGiven field for SixPowersCap.
                if (!attackerPlayer.world.isRemote) {
                    ISixPowersCap attackerSixPowersCap = SixPowersCap.get(attackerPlayer);
                    attackerSixPowersCap.addPunchDamage(1);
                    PacketDispatcher.sendTo(new PacketSixPowers(attackerSixPowersCap), (EntityPlayerMP) attackerPlayer);
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
            IDevilFruitCap targetDevilFruitsCap = DevilFruitCap.get(targetPlayer);

            if ((source.equals(DamageSource.IN_FIRE) || source.equals(DamageSource.ON_FIRE)) && targetDevilFruitsCap.getPower() == OPDevilFruits.MERA_ID) {//Cancels fire damage if Lightning DF is equipped.
                event.setCanceled(true);
            }

            if (source.equals(DamageSource.LIGHTNING_BOLT) && targetDevilFruitsCap.getPower() == OPDevilFruits.GORO_ID) {//Cancels lightning damage if Lightning DF is equipped.
                event.setCanceled(true);
            }

            if (sourcePlayer != null) {
                //Increases attack for Attack Haki Stat.
                IHakiCap sourceHakiCap = HakiCap.get(sourcePlayer);

                if (sourceHakiCap.getAttackLevel() > 0 && targetDevilFruitsCap.hasPower()) {
                    float newDamage = event.getAmount() * (float) sourceHakiCap.getAttackLevel() / 10f;
                    if (newDamage > 0) {
                        targetPlayer.attackEntityFrom(DamageSource.GENERIC, event.getAmount() + newDamage);
                    }
                }

                //Roles
                Member memberAttacker = CrewUtils.getMemberFromPlayer(CrewSaveData.get(sourcePlayer.world).getCrews(), sourcePlayer);
                if(memberAttacker != null){
                    EnumRole role = memberAttacker.getRole();
                    Item heldItem = sourcePlayer.getHeldItemMainhand().getItem();

                    if(role == EnumRole.SWORDSMAN){
                        if(heldItem instanceof ItemSword || heldItem instanceof ItemSwordWithCase || heldItem instanceof ItemSimpleSword){
                            float additionalDamage = event.getAmount() * 0.25f;
                            targetPlayer.attackEntityFrom(DamageSource.GENERIC, event.getAmount() + additionalDamage);
                        }
                    }
                    else if(role == EnumRole.ARCHER){
                        if(heldItem instanceof ItemBow){
                            float additionalDamage = event.getAmount() * 0.25f;
                            targetPlayer.attackEntityFrom(DamageSource.GENERIC, event.getAmount() + additionalDamage);
                        }
                    }
                    else if(role == EnumRole.FIGHTER){
                        if(heldItem == Items.AIR){
                            float additionalDamage = event.getAmount() * 0.25f;
                            targetPlayer.attackEntityFrom(DamageSource.GENERIC, event.getAmount() + additionalDamage);
                        }
                    }
                }

            }
        }

    }

    @SubscribeEvent
    public void onLivingEntityDeath(LivingDeathEvent event){
        Entity victimEntity = event.getEntity();
        Entity killerEntity = event.getSource().getTrueSource();
        EntityPlayer victimPlayer = null;
        EntityPlayer killerPlayer = null;
        IBountyCap victimBountyCap = null;
        IDevilFruitLevelsCap killerLevelCap;

        if (victimEntity instanceof EntityPlayer) {

            victimPlayer = (EntityPlayer) victimEntity;
            victimBountyCap = BountyCap.get(victimPlayer);
        }

        if (killerEntity instanceof EntityPlayer) {
            killerPlayer = (EntityPlayer) killerEntity;
        }

        if(killerPlayer != null){
            IDevilFruitLevelsCap dfl = DevilFruitLevelsCap.get(killerPlayer);

            if(victimEntity instanceof EntityPlayer) {

                IBountyCap sourceBountyCap = BountyCap.get(killerPlayer);

                sourceBountyCap.changeBountyBy((int) (victimBountyCap.getBounty() * 0.30f));
                victimBountyCap.changeBountyBy((int) (-victimBountyCap.getBounty() * 0.35f));

                if(event.getSource() instanceof OPDamageSource){
                    if(((OPDamageSource) event.getSource()).isDevilFruit()){
                        dfl.addXP(DevilFruitLevelsCap.PLAYER_KILLED_WITH_DF_XP);
                    }else{
                        dfl.addXP(DevilFruitLevelsCap.PLAYER_KILLED_WITHOUT_DF_XP);
                    }
                }else{
                    dfl.addXP(DevilFruitLevelsCap.PLAYER_KILLED_WITHOUT_DF_XP);
                }

                PacketDispatcher.sendTo(new PacketBountyClient(victimBountyCap), (EntityPlayerMP) victimPlayer);
                PacketDispatcher.sendTo(new PacketBountyClient(sourceBountyCap), (EntityPlayerMP) killerPlayer);
                PacketDispatcher.sendTo(new PacketDevilFruitLevels(dfl),(EntityPlayerMP)killerEntity);

            }else {
                if (event.getSource() instanceof OPDamageSource) {
                    if (((OPDamageSource) event.getSource()).isDevilFruit()) {
                        dfl.addXP(DevilFruitLevelsCap.ENTITY_KILLED_WITH_DF_XP);
                    } else {
                        dfl.addXP(DevilFruitLevelsCap.ENTITY_KILLED_WITHOUT_DF_XP);
                    }
                }else{
                    dfl.addXP(DevilFruitLevelsCap.ENTITY_KILLED_WITHOUT_DF_XP);
                }
                PacketDispatcher.sendTo(new PacketDevilFruitLevels(dfl),(EntityPlayerMP)killerEntity);
            }
        }else {
            if (victimEntity instanceof EntityPlayer) {
                victimBountyCap.changeBountyBy((int) (-victimBountyCap.getBounty() * 0.1f));
                PacketDispatcher.sendTo(new PacketBountyClient(victimBountyCap), (EntityPlayerMP) victimPlayer);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerInteractEvent(PlayerInteractEvent.EntityInteractSpecific event){
        EntityPlayer attacker = event.getEntityPlayer();
        if(DevilFruitCap.get(attacker).getPower() == OPDevilFruits.OPE_ID && event.getTarget() instanceof EntityPlayer){
            EntityPlayer target = (EntityPlayer)event.getTarget();
            if(attacker.isPotionActive(CommonProxy.effectInsideDome) && target.isPotionActive(CommonProxy.effectInsideDome) && attacker.inventory.getCurrentItem() != null
                    &&  attacker.inventory.getCurrentItem().getItem() == OPItems.KIKOKU_OPEN) {
                if (!attacker.world.isRemote) {
                    ItemStack heart = new ItemStack(OPItems.LAW_HEART);
                    heart = OPUtils.setOwner(heart, target);
                    boolean hasHeart = false;
                    for (ItemStack itemStack : attacker.inventory.mainInventory) {//Check if player has already a heart. Cannot use vanilla method because it takes durability into consideration.
                        if (itemStack != null && itemStack.getItem().equals(OPItems.LAW_HEART) && itemStack.hasTagCompound() && itemStack.getTagCompound().getString("owner").equals(target.getPersistentID().toString())) {
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
                    PacketDispatcher.sendToServer(new PacketSixPowers(sixPowersCap));
                }
                else if(player.moveForward == 0 && player.moveStrafing == 0) {//moveForward & moveStrafing fields only work in remote worlds, so sending a packet will sync the server to the correct value.
                    sixPowersCap.addStillJumps(1);
                    PacketDispatcher.sendToServer(new PacketSixPowers(sixPowersCap));
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
                        PacketDispatcher.sendToServer(new PacketSixPowers(sixPowersCap));
                    }
                }
                else {
                    distanceWalkedNormal += player.distanceWalkedModified - player.prevDistanceWalkedModified;
                    if (distanceWalkedNormal > 10d || distanceWalkedNormal < 0d) {
                        distanceWalkedNormal = 0d;
                        sixPowersCap.addDistanceRun(10);
                        PacketDispatcher.sendToServer(new PacketSixPowers(sixPowersCap));
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
        int prob = 2;
        if (flag) {
            final LootPool main = event.getTable().getPool("main");
            if (main != null && OPCraft.config.enableDevilFruitsSpawning.getCurrentValue()) {
                if(OPCraft.config.enableDevilFruitGomuSpawning.getCurrentValue())main.addEntry(new LootEntryItem(OPDevilFruits.GOMU, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPluffyLoot"));
                if(OPCraft.config.enableDevilFruitMeraSpawning.getCurrentValue())main.addEntry(new LootEntryItem(OPDevilFruits.MERA, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPaceLoot"));
                if(OPCraft.config.enableDevilFruitNoroSpawning.getCurrentValue())main.addEntry(new LootEntryItem(OPDevilFruits.NORO, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPslowLoot"));
                if(OPCraft.config.enableDevilFruitSukeSpawning.getCurrentValue())main.addEntry(new LootEntryItem(OPDevilFruits.SUKE, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPclearLoot"));
                if(OPCraft.config.enableDevilFruitUshiSpawning.getCurrentValue())main.addEntry(new LootEntryItem(OPDevilFruits.GIRAFFE, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPgiraffeLoot"));
                if(OPCraft.config.enableDevilFruitOpeSpawning.getCurrentValue())main.addEntry(new LootEntryItem(OPDevilFruits.OPE, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPlawLoot"));
                if(OPCraft.config.enableDevilFruitHieSpawning.getCurrentValue())main.addEntry(new LootEntryItem(OPDevilFruits.HIE, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPiceLoot"));
                if(OPCraft.config.enableDevilFruitNikyuSpawning.getCurrentValue())main.addEntry(new LootEntryItem(OPDevilFruits.NIKYU, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPpawLoot"));
                if(OPCraft.config.enableDevilFruitYomiSpawning.getCurrentValue())main.addEntry(new LootEntryItem(OPDevilFruits.YOMI, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPreviveLoot"));
                if(OPCraft.config.enableDevilFruitGoroSpawning.getCurrentValue())main.addEntry(new LootEntryItem(OPDevilFruits.GORO, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPthunderLoot"));
                if(OPCraft.config.enableDevilFruitMokuSpawning.getCurrentValue())main.addEntry(new LootEntryItem(OPDevilFruits.MOKU, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPsmokeLoot"));
                if(OPCraft.config.enableDevilFruitYamiSpawning.getCurrentValue())main.addEntry(new LootEntryItem(OPDevilFruits.YAMI, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPstringLoot"));
                if(OPCraft.config.enableDevilFruitItoSpawning.getCurrentValue())main.addEntry(new LootEntryItem(OPDevilFruits.ITO, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPitoLoot"));
                main.addEntry(new LootEntryItem(OPItems.DEVIL_FRUIT_POWER_REMOVER, prob, 1, new LootFunction[0], new LootCondition[0], OPCraft.MODID + ":OPdevilFruitRemoverLoot"));
            }
        }
    }

}
