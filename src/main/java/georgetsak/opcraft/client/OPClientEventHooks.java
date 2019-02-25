package georgetsak.opcraft.client;


import georgetsak.opcraft.client.gui.overlay.EnumSixPowers;
import georgetsak.opcraft.client.power.Power;
import georgetsak.opcraft.client.power.PowerHandler;
import georgetsak.opcraft.client.power.PowerSelector;
import georgetsak.opcraft.common.capability.devilfruits.DevilFruitsCap;
import georgetsak.opcraft.common.capability.sixpowers.ISixPowersCap;
import georgetsak.opcraft.common.capability.sixpowers.SixPowersCap;
import georgetsak.opcraft.common.entity.boat.EntityAceBoat;
import georgetsak.opcraft.common.entity.boat.EntitySailBoat;
import georgetsak.opcraft.common.item.weapons.IExtendedReach;
import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.registry.OPBlocks;
import georgetsak.opcraft.common.registry.OPDevilFruits;
import georgetsak.opcraft.common.registry.OPItems;
import georgetsak.opcraft.common.util.OPUtils;
import georgetsak.opcraft.common.capability.devilfruits.IDevilFruitsCap;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import georgetsak.opcraft.common.network.packets.*;
import georgetsak.opcraft.client.proxy.ClientProxy;
import georgetsak.opcraft.common.network.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OPClientEventHooks {

    private int id = OPDevilFruits.NO_POWER; //Represents the Devil Fruit Power player has.
    private String consequence = ""; //Keeps the consequence name e.g. "GomuGear4B".
    private int consequencesWaitTime; //How much time should be passed before giving negative effects.
    private boolean hasConsequences = false; //Whether or not a specific power has negative effects.
    private int cooldown = 0; //How many ticks left before a power can be used again.
    private int cooldownMax = 0; //Used for drawing the power bar.
    private String action = ""; //Keeps the power name e.g. "GomuGear4A".
    private int ticksPS = OPCraft.config.cooldownSpeed/2; //How many ticks correspond to 1 real life second.

    private boolean isGear4Active = false;
    private boolean isInRoom = false; //Whether or not the player is inside the Law Dome (Room)

    private int cooldownEmperor = 0; //How many ticks left before Emperor Haki can be used again.

    private float sixPowersEnergyBar = 200; //Energy in Six Powers Energy Bar.

    private int cooldownLaw = 0;
    private int cooldownFallDamage = 0;
    private boolean fallDamageDisabled = false;
    private int fallDamageDisabledDelay;
    private boolean fallDamageSendMessage = false;

    private boolean sendShaveDisableMessage = false;

    private int mouseX = 0;
    private int mouseY = 0;

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void playerTickEvent(PlayerTickEvent event) {
        Entity entity = event.player;

        if (entity == Minecraft.getMinecraft().player) {
            EntityPlayerSP mcPlayer = Minecraft.getMinecraft().player;
            IDevilFruitsCap df = DevilFruitsCap.get(mcPlayer);

            if(mcPlayer.getRidingEntity() != null){ //Check for boat riding
                Entity ridingEntity = mcPlayer.getRidingEntity();
                MovementInput mi = mcPlayer.movementInput;

                if(ridingEntity instanceof EntityAceBoat && id == OPDevilFruits.MERA){
                    ((EntityAceBoat)ridingEntity).updateInputs(mi.leftKeyDown, mi.rightKeyDown, mi.forwardKeyDown, mi.backKeyDown);
                }
                else if(ridingEntity instanceof EntitySailBoat){
                    ((EntitySailBoat)ridingEntity).updateInputs(mi.leftKeyDown, mi.rightKeyDown, mi.forwardKeyDown, mi.backKeyDown);
                }

            }

            isInRoom = mcPlayer.isPotionActive(CommonProxy.effectInsideDome);

            if (df.hasPower()) {
                if (mcPlayer.isInWater() && !mcPlayer.capabilities.isCreativeMode) {
                    mcPlayer.setVelocity(0, -0.1, 0);
                    if(cooldown < 40) {
                        cooldownMax = 40;
                        cooldown = 40;
                    }
                }


                Block standingBlock = Minecraft.getMinecraft().world.getBlockState(new BlockPos(mcPlayer.posX, mcPlayer.posY-1, mcPlayer.posZ)).getBlock();

                if(!mcPlayer.capabilities.isCreativeMode && (standingBlock == OPBlocks.BlockKairosekiBlock || standingBlock == OPBlocks.BlockKairosekiStone || standingBlock == OPBlocks.BlockKairosekiBars)){
                    sendMessage("KairosekiItem");
                    if(cooldown < 40) {
                        cooldownMax = cooldown = 40;
                    }
                }
                if((mcPlayer.inventory.hasItemStack(new ItemStack(OPBlocks.BlockKairosekiBlock)) || mcPlayer.inventory.hasItemStack(new ItemStack(OPBlocks.BlockKairosekiBars)) ||
                        mcPlayer.inventory.hasItemStack(new ItemStack(OPBlocks.BlockKairosekiStone)) || mcPlayer.inventory.hasItemStack(new ItemStack(OPItems.ItemKairosekiGem))) && !mcPlayer.isCreative()){
                    sendMessage("KairosekiItem");
                    if(cooldown < 20){
                        cooldownMax = cooldown = 20;
                    }
                }

            }

            this.id = df.getPower();
            PowerSelector.setFruitID(id);

            if (cooldown <= consequencesWaitTime) {
                if (hasConsequences) {
                    isGear4Active = false;
                    executeConsequence();
                }
            } else {
                executeAction(mcPlayer);
            }

            if(fallDamageSendMessage) {
                if (fallDamageDisabled || fallDamageDisabledDelay > 0) {
                    sendMessage("DISABLEDAMAGE");
                } else {
                    sendMessage("ENABLEDAMAGE");
                    fallDamageSendMessage = false;
                    if(sendShaveDisableMessage){
                        sendShaveDisableMessage = false;
                        sendMessage("SixPowersShaveDisable");
                    }
                }
            }
        }
    }


    private void executeAction(EntityPlayer ep) {
        if(checkForSendPacket(ep)){
            action = "";
        }

        if(!action.equals("")) {
            sendMessage(action);
            action = "";
        }

    }

    private boolean checkForSendPacket(EntityPlayer ep) {
        if (action.equals("GomuGear4A") || action.equals("WhiteLauncherA")) {//TODO does not give positive effects. Fix this by sending the OPMessage to server.
            isGear4Active = true;
            disableDamage();
            return true;
        }

        if (action.equals("IceBallA")) {
            Entity entity = OPUtils.findEntity(ep, 50);
            if (entity != null) {
                PacketDispatcher.sendToServer(new IceCageEntityServerPacket(entity));
                return true;
            }
        }

        if (action.equals("WhiteSnakeA")) {
            Entity entity = OPUtils.findEntity(ep, 50);
            if (entity != null) {
                PacketDispatcher.sendToServer(new SmokeSnakeServerPacket(entity));
                return true;
            }
        }

        if (action.equals("ElThorA")) {
            BlockPos spawnPosition = OPUtils.getBlockPlayerIsLooking(ep, 300);
            PacketDispatcher.sendToServer(new ElThorServerPacket(spawnPosition));
            return true;
        }

        if (action.equals("BlackHoleA")) {
            BlockPos spawnPosition = OPUtils.getBlockPlayerIsLooking(ep, 30);
            PacketDispatcher.sendToServer(new BlackHoleServerPacket(spawnPosition));
            sendMessage("BlackHoleA");
            return true;
        }

        if (action.equals("KurouzuA")) {
            Entity entity = OPUtils.findEntity(ep,50);
            if(entity != null){
                PacketDispatcher.sendToServer(new KurouzuServerPacket(entity));
            }
            return true;
        }

        if (action.equals("LiberationA")) {
            sendMessage("DISABLEDAMAGE");
            BlockPos spawnPosition = OPUtils.getBlockPlayerIsLooking(ep, 30);
            if (spawnPosition != null) {
                PacketDispatcher.sendToServer(new LiberationServerPacket(spawnPosition));
            }
            sendMessage("LiberationA");
            return true;
        }

        return false;
    }

    private void executeConsequence() {
        hasConsequences = false;

        if(consequence.equals("GomuGear4B") || consequence.equals("WhiteLauncherB")){
            enableDamage(100);
        }

        if(consequence.equals("LiberationB")){
            sendMessage("ENABLEDAMAGE");
        }

        if(!consequence.equals("")) {
            sendMessage(consequence);
        }

        consequencesWaitTime = 0;
        consequence = "";
    }


    @SubscribeEvent
    public void onKeyInput (InputEvent.KeyInputEvent event){
        GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;

        if(ClientProxy.sixPowersButton.isPressed()){
            EntityPlayerSP p = Minecraft.getMinecraft().player;
            EnumSixPowers selectedPower = ClientProxy.sixPowersSelectionWheelRender.getSelectedPower();

            ISixPowersCap sixpowersCap = SixPowersCap.get(p);
            if(selectedPower == EnumSixPowers.NONE)return;

            int sixPowerLevel = sixpowersCap.getPowerLevel(selectedPower);

            if(sixPowerLevel > 0) {

                switch (selectedPower) {
                    case MOON_WALK:
                        if(checkAndSetEnergyBar((10f/(float)sixPowerLevel)* 20f))break;

                        immediatelyDisableDamage();
                        performAction("Gear4Jump");
                        enableDamage(toTicks(5));
                        break;
                    case IRON_BUDDY:
                        if(checkAndSetEnergyBar(200f))break;

                        immediatelyDisableDamage();
                        enableDamage(sixPowerLevel * 40);
                        break;
                    case FINGER_PISTOL:
                        if(checkAndSetEnergyBar(100f))break;

                        Entity entity = OPUtils.findEntity(p, 5);
                        if (entity instanceof EntityLiving) {
                            System.out.println(sixPowerLevel * 5f);
                            PacketDispatcher.sendToServer(new DamageEntityPacket(entity, sixPowerLevel * 4f));
                        }
                        break;
                    case STORM_LEG:
                        if(checkAndSetEnergyBar((10f/(float)sixPowerLevel)* 20f))break;

                        sendMessage("StormLeg");
                        break;
                    case SHAVE:
                        if(checkAndSetEnergyBar(200f))break;

                        immediatelyDisableDamage();
                        sendMessage("SixPowersShaveEnable");
                        enableDamage(sixPowerLevel * 40);
                        sendShaveDisableMessage = true;
                        break;
                    case PAPER_DRAWING:
                        if(checkAndSetEnergyBar(100f))break;

                        immediatelyDisableDamage();
                        enableDamage(sixPowerLevel * 20);
                        break;
                    case SIX_KING_GUN:
                        if(checkAndSetEnergyBar(200f))break;
                        sendMessage("KingGun");
                        break;
                }
            }
            return;
        }

        if (isInRoom && id == OPDevilFruits.OPE) {
            if (ClientProxy.key1.isPressed()) {
                performAction("Shambles");
            }
            if (ClientProxy.key2.isPressed()) {
                performAction("InjectionShot");
            }
            if (ClientProxy.key3.isPressed()) {
                performAction("Takt");
            }
            return;
        }

        if(!gameSettings.keyBindSneak.isKeyDown() && id != OPDevilFruits.NO_POWER && id != OPDevilFruits.YOMI) {
            if (ClientProxy.key1.isPressed()) {//X
                if (cooldown <= 0) {
                    PowerSelector.buttonPressed(false, true);
                    cooldownTransparency = 200;
                }
            }

            if (ClientProxy.key2.isPressed()) {
                if (cooldown <= 0) {//C
                    PowerSelector.buttonPressed(true, false);
                    cooldownTransparency = 200;
                }
            }

            if (ClientProxy.key3.isPressed()) {
                if (cooldown <= 0) {//V
                    setVariables(PowerSelector.getSelectedPower().getKey());
                }
            }

        }

        if(ClientProxy.statsButton.isPressed()) {
            Minecraft.getMinecraft().player.openGui(OPCraft.MODID, 1, Minecraft.getMinecraft().world, 0, 0, 0);
        }

        if(ClientProxy.hakiButton.isPressed()){
            Minecraft.getMinecraft().player.openGui(OPCraft.MODID, 4, Minecraft.getMinecraft().world, 0, 0, 0);
        }

        //@!@!@!@!@!@!@!@!
        if (isGear4Active && gameSettings.keyBindJump.isPressed()) {
            performAction("Gear4Jump");
        }

        if(ClientProxy.emperorHakiButton.isPressed() && cooldownEmperor == 0) {
                cooldownEmperor = toTicks(60);
                PacketDispatcher.sendToServer(new EmperorPacket());
        }
    }

    boolean focused = true;

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (!Minecraft.getMinecraft().isGamePaused() && event.phase == TickEvent.Phase.START) {

            if (cooldownFallDamage > 0) {
                cooldownFallDamage--;
            }
            if (cooldownLaw > 0) {
                cooldownLaw--;
            }
            if (fallDamageDisabledDelay > 0) {
                fallDamageDisabledDelay--;
            }
            if (cooldownEmperor > 0) {
                cooldownEmperor--;
            }
            if (cooldown > 0) {
                cooldown--;
            }
            if (cooldownTransparency > 0){
                cooldownTransparency--;
            }
            if(sixPowersEnergyBar < 200){
                sixPowersEnergyBar+=0.5f;
            }

            if(ClientProxy.sixPowersMenuButton.isKeyDown()) {
                focused = false;
                Minecraft.getMinecraft().setIngameNotInFocus();
            } else {
                if (!focused) {
                    focused = true;
                    KeyBinding.unPressAllKeys();
                    Minecraft.getMinecraft().setIngameFocus();
                }
            }

        }
    }

    private boolean checkAndSetEnergyBar(float removeAmount){
        float tempCooldown = sixPowersEnergyBar - removeAmount;
        if(tempCooldown < 0)return true;

        sixPowersEnergyBar = tempCooldown;
        return false;
    }

    private void sendMessage(String message){
        PacketDispatcher.sendToServer(new OPMessage(message));
    }

    private void setVariables(int key) {
        Power power = PowerHandler.getPower(id, key);

        if (power != null) {
            String action = power.getActionMessage();
            this.cooldown = toTicks(power.getCooldownTime());
            this.cooldownMax = cooldown;
            this.action = action.concat("A");
            if (power.getHasConsequence()) {
                this.hasConsequences = true;
                this.consequence = action.concat("B");
                this.consequencesWaitTime = toTicks(power.getConsequenceWaitTime());
            }
        }
    }

    //KEYS ALWAYS ON
    private void performAction(String string) {
        if(string.equals("Gear4Jump") && cooldownFallDamage <= 0){
            cooldownFallDamage = 8;
            Vec3d a = OPUtils.convertRotation(Minecraft.getMinecraft().player.rotationYaw, Minecraft.getMinecraft().player.rotationPitch);
            a.scale(3);
            EntityPlayerSP entity = Minecraft.getMinecraft().player;
            entity.addVelocity(a.x , 1, a.z);
            //PacketDispatcher.sendToServer(new AllowFlyingPacket(entity.posX, entity.posY, entity.posZ));
        }

        if(string.equals("Shambles") && cooldownLaw <= 0){
            sendMessage("Shambles");
            cooldownLaw = 60;
        }
        if(string.equals("InjectionShot") && cooldownLaw <= 0){
            sendMessage("InjectionShot");
            cooldownLaw = 120;
        }
        if(string.equals("Takt") && cooldownLaw <= 0){
            sendMessage("Takt");
            cooldownLaw = 140;
        }
    }

    private void disableDamage(){
        this.fallDamageDisabled = true;
        this.fallDamageSendMessage = true;
    }

    private void immediatelyDisableDamage(){
        sendMessage("DISABLE DAMAGE");
    }

    private void enableDamage(int delay){
        this.fallDamageDisabled = false;
        fallDamageDisabledDelay = delay;
        this.fallDamageSendMessage = true;
    }

    private int toTicks(int seconds){
        int tickPStemp = ticksPS;
        if(Minecraft.getMinecraft().player.isCreative()){
            tickPStemp = 1;
        }
        return seconds * tickPStemp;
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onMouseEvent(MouseEvent event)
    {

        mouseX = event.getX();
        mouseY = event.getY();

        if (event.getButton() == 1 && event.isButtonstate()) {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayer player = mc.player;
            if (player != null) {
                ItemStack itemstack = player.getHeldItemMainhand();
                IExtendedReach iExtendedReach;
                if (itemstack != null) {
                    if (itemstack.getItem() instanceof IExtendedReach) {//This code seems crap. Won't delete it yet though
                        iExtendedReach = (IExtendedReach) itemstack.getItem();

                        boolean flag1 = !(iExtendedReach.getType() == 1 && player.inventory.hasItemStack(new ItemStack(OPItems.ItemFlintlockAmmo)));
                        boolean flag2 = !(iExtendedReach.getType() == 2 && player.inventory.hasItemStack(new ItemStack(OPItems.ItemSenrikuAmmo)));
                        boolean flag3 = !(iExtendedReach.getType() == 3 && player.inventory.hasItemStack(new ItemStack(OPItems.ItemBazookaAmmo)));

                        if (flag1 && flag2 && flag3) {
                            iExtendedReach = null;
                        }


                    } else {
                        iExtendedReach = null;
                    }

                    if (iExtendedReach != null) {
                        float reach = iExtendedReach.getReach();
                        RayTraceResult mov = OPUtils.getMouseOverExtended(reach);

                        if (mov != null) {

                            if(mov.typeOfHit == RayTraceResult.Type.BLOCK && player.getCooldownTracker().getCooldown(itemstack.getItem(), 0f) <= 0){
                                PacketDispatcher.sendToServer(new CreateExplosionPacket(mov.getBlockPos(), 8));
                            }

                            if (mov.entityHit != null && player.getCooldownTracker().getCooldown(iExtendedReach.getItem(), 0) <= 0F) {
                                {
                                    if (mov.entityHit != player) {
                                        PacketDispatcher.sendToServer(new RayTracePacket(
                                                mov.entityHit.getEntityId()));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event){
        OPCraft.config.restoreConfig();
        OPUtils.setFruitsCreativeTab();
    }

    private int cooldownTransparency = 0;
    private boolean once = true;

    @SubscribeEvent
    public void renderGameOverlayEvent(RenderGameOverlayEvent event) {

        if(event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE){
            if(sixPowersEnergyBar < 200) {
                event.setCanceled(true);
            }
        }

        if (!event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.VIGNETTE) {
            if(sixPowersEnergyBar < 200){
                ClientProxy.devilFruitRenderOverlay.drawEnergyBar(event.getResolution(), (int)sixPowersEnergyBar);
            }

            if (cooldownTransparency < 41) {
                ClientProxy.devilFruitRenderOverlay.decreaseTransparency();
            } else {
                ClientProxy.devilFruitRenderOverlay.resetTransparency();
            }

            if(DevilFruitsCap.get(Minecraft.getMinecraft().player).hasPower()){
                ClientProxy.devilFruitRenderOverlay.render(id, cooldown, cooldownMax, event.getResolution(), (int)sixPowersEnergyBar);
            }

            if (ClientProxy.sixPowersMenuButton.isKeyDown()) {
                if(once){
                    once = false;
                    ClientProxy.sixPowersSelectionWheelRender.createTextures();
                }
                ClientProxy.sixPowersSelectionWheelRender.render(event.getResolution(), mouseX, mouseY);
            }
        }
    }

}
