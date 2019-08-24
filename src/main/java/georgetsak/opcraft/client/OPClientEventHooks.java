package georgetsak.opcraft.client;


import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.gui.overlay.EnumSixPowers;
import georgetsak.opcraft.common.power.Power;
import georgetsak.opcraft.common.power.PowerHandler;
import georgetsak.opcraft.client.power.PowerSelector;
import georgetsak.opcraft.client.proxy.ClientProxy;
import georgetsak.opcraft.common.capability.devilfruitlevels.DevilFruitLevelsCap;
import georgetsak.opcraft.common.capability.devilfruitlevels.IDevilFruitLevelsCap;
import georgetsak.opcraft.common.capability.devilfruits.DevilFruitCap;
import georgetsak.opcraft.common.capability.devilfruits.IDevilFruitCap;
import georgetsak.opcraft.common.capability.sixpowers.ISixPowersCap;
import georgetsak.opcraft.common.capability.sixpowers.SixPowersCap;
import georgetsak.opcraft.common.crew.Crew;
import georgetsak.opcraft.common.crew.EnumRole;
import georgetsak.opcraft.common.crew.Member;
import georgetsak.opcraft.common.entity.boat.EntityAceBoat;
import georgetsak.opcraft.common.entity.boat.EntitySailBoat;
import georgetsak.opcraft.common.item.weapons.IExtendedReach;
import georgetsak.opcraft.common.network.packets.common.PacketDevilFruitLevels;
import georgetsak.opcraft.common.network.packets.server.*;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import georgetsak.opcraft.common.network.proxy.CommonProxy;
import georgetsak.opcraft.common.registry.OPBlocks;
import georgetsak.opcraft.common.registry.OPDevilFruits;
import georgetsak.opcraft.common.registry.OPItems;
import georgetsak.opcraft.common.util.CrewUtils;
import georgetsak.opcraft.common.util.MathUtils;
import georgetsak.opcraft.common.util.OPUtils;
import georgetsak.opcraft.common.util.RaytracingUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
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
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Client based logic. All these function are executed in each individual player.
 */
public class OPClientEventHooks {

    private int id = OPDevilFruits.NO_POWER; //Represents the Devil Fruit Power player has.

    private boolean isGear4Active = false;
    private int gear4RemainingTime = 0;
    private boolean isInRoom = false; //Whether or not the player is inside the Law Dome (Room)

    private int cooldownEmperor = 0; //How many ticks left before Emperor Haki can be used again.

    private float sixPowersEnergyBar = 200; //Energy in Six Powers Energy Bar.

    private int cooldownFallDamage = 0;
    private boolean fallDamageDisabled = false;
    private int fallDamageDisabledDelay;
    private boolean fallDamageSendMessage = false;

    private boolean sendShaveDisableMessage = false;

    private int mouseX = 0;
    private int mouseY = 0;

    /**
     * Executed in every tick. Used to execute the powers or the side effects. Also used for checking whether or not a Player is on a
     * Kairoseki block or when the Player rides a mod introduced boat.
     * @param event
     */
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void playerTickEvent(PlayerTickEvent event) {
        Entity entity = event.player;

        if (entity == Minecraft.getMinecraft().player) {
            EntityPlayerSP mcPlayer = Minecraft.getMinecraft().player;
            IDevilFruitCap df = DevilFruitCap.get(mcPlayer);
            IDevilFruitLevelsCap dfl = DevilFruitLevelsCap.get(mcPlayer);

            if(mcPlayer.getRidingEntity() != null){ //Check for boat riding
                Entity ridingEntity = mcPlayer.getRidingEntity();
                MovementInput mi = mcPlayer.movementInput; //Get the player's controls

                if(ridingEntity instanceof EntityAceBoat && id == OPDevilFruits.MERA){//Send the input to the boat player is riding if any.
                    ((EntityAceBoat)ridingEntity).updateInputs(mi.leftKeyDown, mi.rightKeyDown, mi.forwardKeyDown, mi.backKeyDown);
                }
                else if(ridingEntity instanceof EntitySailBoat){
                    ((EntitySailBoat)ridingEntity).updateInputs(mi.leftKeyDown, mi.rightKeyDown, mi.forwardKeyDown, mi.backKeyDown);
                }

            }



            isInRoom = mcPlayer.isPotionActive(CommonProxy.effectInsideDome);

            if(dfl.getDevilFruitID() != df.getPower()) {
                dfl.setDevilFruitID(df.getPower());
                PacketDispatcher.sendToServer(new PacketDevilFruitLevels(dfl));
            }

            if (df.hasPower()) {
                id = df.getPower();
                PowerSelector.setFruitID(id);

                if (!OPCraft.config.allowDevilFruitUsersToSwim.getCurrentValue() && mcPlayer.isInWater() && !mcPlayer.isCreative()) {
                    setAllPowersCooldown(adjustTicks(20));
                    if (OPUtils.isPlayerInOrOverDeepWater(mcPlayer)) {
                        mcPlayer.setVelocity(0, -0.1, 0);//TODO this movement normally should be done on the Server Side. Another alternative would be to disable Player's keys.
                    }
                }

                //Kairoseki blocks/items check section.
                if(!mcPlayer.isCreative() && OPCraft.config.doesSeaStoneAffectDevilFruitUsers.getCurrentValue()) {
                    Block standingBlock = Minecraft.getMinecraft().world.getBlockState(new BlockPos(mcPlayer.posX, mcPlayer.posY-1, mcPlayer.posZ)).getBlock();

                    if (standingBlock == OPBlocks.BlockKairosekiBlock || standingBlock == OPBlocks.BlockKairosekiStone || standingBlock == OPBlocks.BlockKairosekiBars) {
                        sendMessage("KairosekiItem");
                        setAllPowersCooldown(40);
                    }
                    if (mcPlayer.inventory.hasItemStack(new ItemStack(OPBlocks.BlockKairosekiBlock)) || mcPlayer.inventory.hasItemStack(new ItemStack(OPBlocks.BlockKairosekiBars)) ||
                            mcPlayer.inventory.hasItemStack(new ItemStack(OPBlocks.BlockKairosekiStone)) || mcPlayer.inventory.hasItemStack(new ItemStack(OPItems.ItemKairosekiGem))) {
                        sendMessage("KairosekiItem");
                        setAllPowersCooldown(20);
                    }
                }

            }else{
                id = OPDevilFruits.NO_POWER;
                PowerSelector.setFruitID(OPDevilFruits.NO_POWER);
            }

            if(fallDamageSendMessage) {//There was a problem where the "DISABLEDAMAGE" packet would occasionally be lost. This ensures the packet is sent multiple times.
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

    /**
     * Checks if the current action is a special action that requires a specific code to be executed. If not it sends the packet to the Server.
     */
    private void executeAction(EntityPlayer ep, String action) {
        action = action.concat("A");
        if(checkForSendPacket(ep, action)){
            action = "";
        }

        if(!action.equals("")) {
            sendMessage(action);
        }

    }

    /**
     * Checks if the current action is a special action.
     */
    private boolean checkForSendPacket(EntityPlayer ep, String action) {

        if (action.equals("GomuGear4A") || action.equals("WhiteLauncherA") || action.equals("SoraNoMichiA")) {
            isGear4Active = true;
            gear4RemainingTime = adjustTicks(200);
            disableAndEnableDamageAfter(adjustTicks(320));
            return !action.equals("GomuGear4A");//Gear 4 still needs the packet.
        }

        if (action.equals("IceBallA")) {
            Entity entity = RaytracingUtils.getLookingEntity(ep, 50);
            if (entity != null) {
                PacketDispatcher.sendToServer(new PacketIceCageEntityServer(entity));
                return true;
            }
        }

        if (action.equals("WhiteSnakeA")) {
            Entity entity = RaytracingUtils.getLookingEntity(ep, 50);
            if (entity != null) {
                PacketDispatcher.sendToServer(new PacketSmokeSnakeServer(entity));
                return true;
            }
        }

        if (action.equals("ElThorA")) {
            int level = DevilFruitLevelsCap.get(Minecraft.getMinecraft().player).getPowerLevel(1);
            BlockPos spawnPosition = RaytracingUtils.getBlockPlayerIsLooking(ep, 15 + level*15);
            if(spawnPosition != null) {
                PacketDispatcher.sendToServer(new PacketElThorServer(spawnPosition));
            }
            return true;
        }

        if (action.equals("BlackHoleA")) {
            BlockPos spawnPosition = RaytracingUtils.getBlockPlayerIsLooking(ep, 30);
            if(spawnPosition != null) {
                PacketDispatcher.sendToServer(new PacketBlackHoleServer(spawnPosition));
                sendMessage("BlackHoleA");
            }
            return true;
        }

        if (action.equals("KurouzuA")) {
            Entity entity = RaytracingUtils.getLookingEntity(ep,10 + DevilFruitLevelsCap.get(Minecraft.getMinecraft().player).getPowerLevel(2)*10);
            if(entity != null){
                PacketDispatcher.sendToServer(new PacketKurouzuServer(entity));
            }
            return true;
        }

        if (action.equals("LiberationA")) {
            disableAndEnableDamageAfter(220);
            BlockPos spawnPosition = RaytracingUtils.getBlockPlayerIsLooking(ep, 30);
            if (spawnPosition != null) {
                PacketDispatcher.sendToServer(new PacketLiberationServer(spawnPosition));
                sendMessage("LiberationA");
            }
            return true;
        }

        return false;
    }

    /**
     * Executed every time the Player pressed a key. Used to activate/change the powers, open menus etc.
     */
    @SubscribeEvent
    public void onKeyInput (InputEvent.KeyInputEvent event){
        GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;

        //Six Powers
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

                        performAction("Gear4Jump");
                        disableAndEnableDamageAfter(adjustTicks(100));
                        break;
                    case IRON_BUDDY:
                        if(checkAndSetEnergyBar(200f))break;

                        disableAndEnableDamageAfter(sixPowerLevel * 40);
                        break;
                    case FINGER_PISTOL:
                        if(checkAndSetEnergyBar(100f))break;

                        Entity entity = RaytracingUtils.getLookingEntity(p, 5);
                        if (entity instanceof EntityLiving) {

                            PacketDispatcher.sendToServer(new PacketDamageEntityServer(entity, sixPowerLevel * 4f));
                        }
                        break;
                    case STORM_LEG:
                        if(checkAndSetEnergyBar((10f/(float)sixPowerLevel)* 20f))break;

                        sendMessage("StormLeg");
                        break;
                    case SHAVE:
                        if(checkAndSetEnergyBar(200f))break;

                        sendMessage("SixPowersShaveEnable");
                        disableAndEnableDamageAfter(sixPowerLevel * 40);
                        sendShaveDisableMessage = true;
                        break;
                    case PAPER_DRAWING:
                        if(checkAndSetEnergyBar(100f))break;

                        disableAndEnableDamageAfter(sixPowerLevel * 20);
                        break;
                    case SIX_KING_GUN:
                        if(checkAndSetEnergyBar(200f))break;
                        sendMessage("KingGun");
                        break;
                }
            }
            return;
        }
        //Powers keys handler.
        if(!gameSettings.keyBindSneak.isKeyDown() && id != OPDevilFruits.NO_POWER && id != OPDevilFruits.YOMI) {
            if (ClientProxy.key1.isPressed()) {//X
                    PowerSelector.buttonPressed(false);
                    cooldownTransparency = 200;
            }

            if (ClientProxy.key2.isPressed()) {
                    PowerSelector.buttonPressed(true);
                    cooldownTransparency = 200;
            }

            if (ClientProxy.key3.isPressed()) {
                Power power = PowerSelector.getSelectedPower();
                if (power != null && power.getCurrentCooldown() == 0 && !OPCraft.config.isDFDisabled(id)) {//V

                    if(id == OPDevilFruits.OPE && !isInRoom && power != PowerHandler.getPower(OPDevilFruits.OPE,1))return;

                    IDevilFruitLevelsCap dfc = DevilFruitLevelsCap.get(Minecraft.getMinecraft().player);
                    power.setCurrentCooldown(adjustTicks(dfc.getPowerCooldown(power.getKey())));

                    dfc.addPowerUses(power.getKey());
                    PacketDispatcher.sendToServer(new PacketDevilFruitLevels(dfc));

                    if(!OPCraft.IS_RELEASE_VERSION) {
                        String message = "";
                        int k = 'A';
                        int j = 1;
                        for (int i : dfc.getAllPowersUses()) {
                            String color;
                            switch (j-1){
                                default:
                                case 0: color = TextFormatting.RED.toString(); break;
                                case 1: color = TextFormatting.GREEN.toString(); break;
                                case 2: color = TextFormatting.GOLD.toString(); break;
                                case 3: color = TextFormatting.BLUE.toString(); break;
                            }
                            message += color + (char)k++ + ":" + i + " (c:" + dfc.getPowerCooldown(j++) + ") | " + TextFormatting.RESET.toString();
                        }
                        Minecraft.getMinecraft().player.sendMessage(new TextComponentString(message));
                    }

                    executeAction(Minecraft.getMinecraft().player, power.getActionMessage());
                }
            }

        }
        //Normal Stats button
        if(ClientProxy.statsButton.isPressed()) {
            Minecraft.getMinecraft().player.openGui(OPCraft.MODID, 1, Minecraft.getMinecraft().world, 0, 0, 0);
        }

        //Haki Stats button
        if(ClientProxy.hakiButton.isPressed()){
            Minecraft.getMinecraft().player.openGui(OPCraft.MODID, 4, Minecraft.getMinecraft().world, 0, 0, 0);
        }

        //Power Upgrade button
        if(ClientProxy.powerUpgradeButton.isPressed()){
            Minecraft.getMinecraft().player.openGui(OPCraft.MODID,6,Minecraft.getMinecraft().world,0,0,0);
        }

        //Jump key for Gear 4
        if (isGear4Active && gameSettings.keyBindJump.isPressed()) {
            performAction("Gear4Jump");
        }
        //Emperor Haki key handler.
        if(ClientProxy.emperorHakiButton.isPressed() && cooldownEmperor == 0) {
                cooldownEmperor = adjustTicks(1200);
                PacketDispatcher.sendToServer(new PacketEmperorServer());
        }
    }

    boolean focused = true;

    /**
     * Executed in every tick. Used mainly as a timer. Also handles the Six Powers Wheel Button.
     */
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (!Minecraft.getMinecraft().isGamePaused() && event.phase == TickEvent.Phase.START) {
            decreaseAllPowersCooldown();

            if (cooldownFallDamage > 0) {
                cooldownFallDamage--;
            }
            if (fallDamageDisabledDelay > 0) {
                fallDamageDisabledDelay--;
            }
            if (cooldownEmperor > 0) {
                cooldownEmperor--;
            }
            if (cooldownTransparency > 0){
                cooldownTransparency--;
            }
            if(sixPowersEnergyBar < 200){
                sixPowersEnergyBar+=0.5f;
            }
            if(gear4RemainingTime > 0){
                gear4RemainingTime--;
                if(gear4RemainingTime == 0 && isGear4Active){
                    isGear4Active = false;
                }
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

    private void setAllPowersCooldown(int cooldown){
            for(int i = 0; i < PowerHandler.getTotalPowersForFruit(id); i++){
                Power power = PowerHandler.getPower(id, i+1);
                if(power != null){
                    power.setCurrentCooldown(cooldown);
                }
            }
    }

    private void decreaseAllPowersCooldown(){
        for(int i = 0; i < PowerHandler.getTotalPowersForFruit(id); i++){
            Power power = PowerHandler.getPower(id, i+1);
            if(power != null){
                power.decreaseCooldown();
            }
        }
    }

    /**
     * Checks if the Player has the given energy amount. If yes it removes it.
     * @return false if the energy is sufficient / true if the energy is not sufficient
     */
    private boolean checkAndSetEnergyBar(float removeAmount){
        float tempCooldown = sixPowersEnergyBar - removeAmount;
        if(tempCooldown < 0)return true;

        sixPowersEnergyBar = tempCooldown;
        return false;
    }

    /**
     * Sends the given message to the server in an OPServerMessage packet.
     */
    private void sendMessage(String message){
        PacketDispatcher.sendToServer(new PacketToServerCommandsServer(message));
    }

    /**
     * Performs the given action if the corresponding cooldown is 0.
     */
    private void performAction(String string) {
        if(string.equals("Gear4Jump") && cooldownFallDamage <= 0){
            cooldownFallDamage = 8;
            Vec3d a = MathUtils.convertRotation(Minecraft.getMinecraft().player.rotationYaw, Minecraft.getMinecraft().player.rotationPitch);
            a.scale(3);
            EntityPlayerSP entity = Minecraft.getMinecraft().player;
            entity.addVelocity(a.x , 1, a.z);
            //PacketDispatcher.sendToServer(new AllowFlyingServerPacket(entity.posX, entity.posY, entity.posZ));
        }
    }

    /**
     * Disables the damage for the Player and enables it after a delay.
     * @param delay
     */
    private void disableAndEnableDamageAfter(int delay){
        this.fallDamageDisabled = false;
        fallDamageDisabledDelay = delay;
        this.fallDamageSendMessage = true;
    }

    /**
     * Converts real-life seconds to ticks. If the player is in creative it returns the given seconds.
     */
    private int adjustTicks(int ticks){
        return Minecraft.getMinecraft().player.capabilities.isCreativeMode ? 20 : ticks;
    }

    /**
     * Executed every time Player pressed a mouse key. Used for the guns.
     */
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
                IExtendedReach iExtendedReach = null;
                if (itemstack != null) {
                    if (itemstack.getItem() instanceof IExtendedReach) {
                        iExtendedReach = (IExtendedReach) itemstack.getItem();

                        boolean flag1 = !(iExtendedReach.getType() == 1 && player.inventory.hasItemStack(new ItemStack(OPItems.ItemFlintlockAmmo)));
                        boolean flag2 = !(iExtendedReach.getType() == 2 && player.inventory.hasItemStack(new ItemStack(OPItems.ItemSenrikuAmmo)));
                        boolean flag3 = !(iExtendedReach.getType() == 3 && player.inventory.hasItemStack(new ItemStack(OPItems.ItemBazookaAmmo)));

                        if (flag1 && flag2 && flag3) {
                            iExtendedReach = null;
                        }


                    }

                    if (iExtendedReach != null) {
                        float reach = iExtendedReach.getReach();
                        RayTraceResult mov = RaytracingUtils.getMouseOverExtended(reach);

                        if (mov != null) {

                            if(mov.typeOfHit == RayTraceResult.Type.BLOCK && player.getCooldownTracker().getCooldown(itemstack.getItem(), 0f) <= 0 && itemstack.getItem().equals(OPItems.ItemBazooka)){
                                PacketDispatcher.sendToServer(new PacketCreateExplosionServer(mov.getBlockPos(), 8));
                            }

                            if (mov.entityHit != null && player.getCooldownTracker().getCooldown(iExtendedReach.getItem(), 0) <= 0F) {
                                {
                                    if (mov.entityHit != player) {
                                        PacketDispatcher.sendToServer(new PacketRayTraceServer(
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

    /**
     * Executed every time the player logs out of a server. It restores the default-client log file.
     */
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event){
        OPCraft.config.restoreConfig();
    }

    private int cooldownTransparency = 0;
    private boolean once = true;

    /**
     * Checks the rendered players' crew and displays a name tag above their name.
     */
    @SubscribeEvent
    public void renderPlayerEvent(RenderLivingEvent.Specials.Post event) {

        if(!(event.getEntity() instanceof AbstractClientPlayer))return;

        EntityPlayer player = (EntityPlayer) event.getEntity();

        if (CrewUtils.isPlayerInACrew(ClientProxy.crews, player)) {
            Crew playerCrew = CrewUtils.getPlayerCrew(ClientProxy.crews, player);
            Crew clientCrew = CrewUtils.getPlayerCrew(ClientProxy.crews, Minecraft.getMinecraft().player);
            Member playerMember = CrewUtils.getMemberFromPlayer(ClientProxy.crews, player);

            if (playerMember == null || playerCrew == null) return;

            EnumRole playerRole = playerMember.getRole();
            String crewName = playerCrew.getName();
            String roleName = playerRole.getName();

            if (clientCrew != null && crewName.equals(clientCrew.getName())) {//Same crew with client
                String string = roleName;
                if(playerCrew.getOwner().equals(playerMember.getUuid())){
                    string = "Captain / " + string;
                }
                renderNameTag("[" + string + "]", event.getX(), event.getY() + event.getEntity().height + 0.75D, event.getZ(), 5635925);
            } else {
                renderNameTag("[" + crewName + "]", event.getX(), event.getY() + event.getEntity().height + 0.75D, event.getZ(),16733525);
            }


        }

    }

    private void renderNameTag(String name, double x, double y, double z, int color){
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-Minecraft.getMinecraft().player.rotationYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.scale(-0.025F, -0.025F, 0.025F);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);

        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        int i = Minecraft.getMinecraft().fontRenderer.getStringWidth(name) / 2;
        GlStateManager.disableTexture2D();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        int verticalShift = 0;
        bufferbuilder.pos((double)(-i - 1), (double)(-1 + verticalShift), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
        bufferbuilder.pos((double)(-i - 1), (double)(8 + verticalShift), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
        bufferbuilder.pos((double)(i + 1), (double)(8 + verticalShift), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
        bufferbuilder.pos((double)(i + 1), (double)(-1 + verticalShift), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();

        GlStateManager.depthMask(true);
        Minecraft.getMinecraft().fontRenderer.drawString(name, -Minecraft.getMinecraft().fontRenderer.getStringWidth(name) / 2, verticalShift, color);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
    }

    /**
     * Called in every render tick. Draws the HUD on the screen.
     */
    @SubscribeEvent
    public void renderGameOverlayEvent(RenderGameOverlayEvent event) {

        if(event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE){
            if(sixPowersEnergyBar < 200) {
                //event.setCanceled(true);
            }
        }

        if (!event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE) {//HOTBAR
            if(sixPowersEnergyBar < 200){
                ClientProxy.devilFruitRenderOverlay.drawEnergyBar(event.getResolution(), (int)sixPowersEnergyBar);
            }

            if (cooldownTransparency < 41) {
                ClientProxy.devilFruitRenderOverlay.decreaseTransparency();
            } else {
                ClientProxy.devilFruitRenderOverlay.resetTransparency();
            }

            if(DevilFruitCap.get(Minecraft.getMinecraft().player).hasPower() || !OPCraft.IS_RELEASE_VERSION) {
                Power power = PowerSelector.getSelectedPower();
                if (power != null) {
                    ClientProxy.devilFruitRenderOverlay.render(id, power.getCurrentCooldown(), adjustTicks(DevilFruitLevelsCap.get(Minecraft.getMinecraft().player).getPowerCooldown(power.getKey())), event.getResolution(), (int) sixPowersEnergyBar);
                }else if(id == OPDevilFruits.YOMI || id == OPDevilFruits.GIRAFFE){
                    ClientProxy.devilFruitRenderOverlay.render(id,0,1,event.getResolution(),(int)sixPowersEnergyBar);
                }
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
