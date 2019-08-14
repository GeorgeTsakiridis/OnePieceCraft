package georgetsak.opcraft.client.gui;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.proxy.ClientProxy;
import georgetsak.opcraft.common.capability.bounty.BountyCap;
import georgetsak.opcraft.common.capability.bounty.IBountyCap;
import georgetsak.opcraft.common.crew.Crew;
import georgetsak.opcraft.common.crew.EnumRole;
import georgetsak.opcraft.common.crew.Member;
import georgetsak.opcraft.common.network.packets.server.PacketEditCrewServer;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import georgetsak.opcraft.common.util.CrewUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;

public class CrewGUI extends GuiScreen {

    static ResourceLocation crewRoleIcons = new ResourceLocation(OPCraft.MODID, "textures/gui/crew/crew.png");

    private GuiButton createCrewButton;
    private GuiTextField createCrewNameTextField;

    //SWORDSMAN, ARCHER, FIGHTER, DOCTOR

    private GuiButton swordsmanButton;
    private GuiButton archerButton;
    private GuiButton fighterButton;
    private GuiButton doctorButton;
    private int space;

    private GuiButton changeRoleButton;
    private GuiButton leaveCrewButton;

    private GuiButton invitePlayerButton;
    private GuiButton kickPlayerButton;
    private GuiTextField invitePlayerNameTextField;

    @Override
    public void initGui() {
        createCrewButton = this.addButton(new GuiButton(1, 100, 100, "Create Crew"));

        createCrewNameTextField = new GuiTextField(2, fontRenderer, 0, 0, 150, 20);
        createCrewNameTextField.setMaxStringLength(20);
        createCrewNameTextField.setVisible(false);

        int h = (int)(height*0.6f);
        space = (width-40) / 7;
        swordsmanButton = this.addButton(new GuiButton(3, 20 + space - 80, h, 80, 20, "Swordsman"));
        archerButton = this.addButton(new GuiButton(4, 20 + space*3 - 80, h, 80, 20, "Archer"));
        fighterButton = this.addButton(new GuiButton(5, 20 + space*5 - 80, h, 80, 20, "Fighter"));
        doctorButton = this.addButton(new GuiButton(6, 20 + space*7 - 80, h, 80, 20, "Doctor"));

        changeRoleButton = this.addButton(new GuiButton(7, 20, height-55, 80, 20, "Change Role"));
        leaveCrewButton = this.addButton(new GuiButton(8, 20,height-25, 80, 20, "Leave Crew"));

        invitePlayerNameTextField = new GuiTextField(10,fontRenderer,20, 20, 120, 20);
        invitePlayerButton = this.addButton(new GuiButton(9,20, 50,60, 20, "Invite"));
        kickPlayerButton = this.addButton(new GuiButton(11,20, 80,60, 20, "Kick"));

        invitePlayerButton.visible = false;
        kickPlayerButton.visible = false;
        invitePlayerNameTextField.setVisible(false);

        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();//Make it more dark
        drawDefaultBackground();

        if (ClientProxy.crews != null) {
            EntityPlayer player = Minecraft.getMinecraft().player;
            IBountyCap bountyCap = BountyCap.get(player);

            boolean isInCrew = CrewUtils.isPlayerInACrew(ClientProxy.crews, player);

            int startY = (int)(height*0.2f)-20;

            createCrewNameTextField.setVisible(!isInCrew);
            createCrewNameTextField.setEnabled(!isInCrew);
            createCrewButton.enabled = !isInCrew;

            changeRoleButton.visible = false;
            leaveCrewButton.visible = false;

            invitePlayerButton.visible = false;
            kickPlayerButton.visible = false;
            invitePlayerNameTextField.setVisible(false);
            invitePlayerNameTextField.setEnabled(false);

            if(!isInCrew){
                GlStateManager.pushMatrix();
                GlStateManager.scale(2f, 2f, 2f);
                drawCenteredString(fontRenderer, "Create a Crew!", width/4, 5, Color.WHITE.getRGB());
                GlStateManager.popMatrix();


                swordsmanButton.visible = false;
                archerButton.visible = false;
                fighterButton.visible = false;
                doctorButton.visible = false;

                drawCenteredString(fontRenderer, "You are not currently in a crew :(", width/2, startY+=20, Color.RED.getRGB());
                drawCenteredString(fontRenderer, "You can either create one or ask the Captain of another crew to invite you!", width/2, startY+=20, Color.RED.getRGB());

                drawString(fontRenderer, "Crew name:", width/2 - 150, startY+=20, Color.WHITE.getRGB());
                createCrewNameTextField.x = width/2 - 180;
                createCrewNameTextField.y = startY;
                createCrewButton.x = width/2;
                createCrewButton.y = startY;
                createCrewNameTextField.drawTextBox();
                createCrewButton.drawButton(mc, mouseX, mouseY, partialTicks);
                createCrewNameTextField.setFocused(true);
                drawCenteredString(fontRenderer, "Creating a new crew will consume 50000 bounty.", width/2, startY+=30, Color.WHITE.getRGB());
                drawCenteredString(fontRenderer, "Crew name cannot exceed 20 characters.", width/2, startY+=15, Color.WHITE.getRGB());
                createCrewButton.enabled = !CrewUtils.isPlayerInACrew(ClientProxy.crews, Minecraft.getMinecraft().player) && CrewUtils.isCrewNameAvailable(ClientProxy.crews, createCrewNameTextField.getText()) && !createCrewNameTextField.getText().isEmpty() && bountyCap.getBounty() >= 50000;

            }
            else{
                createCrewButton.visible = false;
                //if player is in a crew.
                Member playerMember = CrewUtils.getMemberFromPlayer(ClientProxy.crews, player);
                if(playerMember != null){
                    boolean hasRole = playerMember.getRole() != EnumRole.NO_ROLE;

                    swordsmanButton.visible = !hasRole;
                    archerButton.visible = !hasRole;
                    fighterButton.visible = !hasRole;
                    doctorButton.visible = !hasRole;

                    if(!hasRole){

                        GlStateManager.enableNormalize();
                        GlStateManager.enableBlend();
                        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

                        Minecraft.getMinecraft().getTextureManager().bindTexture(crewRoleIcons);
                        drawModalRectWithCustomSizedTexture(0, 0, 0, 0, width, height, width, height);
                        GlStateManager.disableNormalize();
                        GlStateManager.disableBlend();

                        drawCenteredString(fontRenderer, "Please select a role:", width/2, startY, Color.WHITE.getRGB());
                        drawString(fontRenderer, "Swordsman: Deals 25% more damage when using swords.", space/2, (int)(height*0.6f) + 30, Color.WHITE.getRGB());
                        drawString(fontRenderer, "Archer: Deals 20% more damage when using bows, guns as well as kabuto.", space/2, (int)(height*0.6f) + 45, Color.WHITE.getRGB());
                        drawString(fontRenderer, "Fighter: Deals 25% more damage when hitting with punch and gets 15% damage reduction.", space/2, (int)(height*0.6f) + 60, Color.WHITE.getRGB());
                        drawString(fontRenderer, "Doctor: Gets 15% more life and heals the double hearts when using medical items.", space/2, (int)(height*0.6f) + 75, Color.WHITE.getRGB());
                    }
                    else{
                        //If player has role and is in crew.
                        Crew crew = CrewUtils.getPlayerCrew(ClientProxy.crews,player);

                        changeRoleButton.visible = true;
                        leaveCrewButton.visible = true;

                        if(crew != null){
                            boolean isOwner = crew.getOwner().equals(player.getPersistentID());
                            //Common GUI
                            GlStateManager.pushMatrix();
                            GlStateManager.scale(2f,2f, 2f);
                            drawCenteredString(fontRenderer, crew.getName(), width/4, 5, Color.WHITE.getRGB());
                            GlStateManager.popMatrix();

                            drawCenteredString(fontRenderer, "Crew Members", width/2, startY+=5, Color.WHITE.getRGB());
                            startY += 6;
                            for(Member member : crew.getMemberList()){
                                int color = crew.getOwner().equals(member.getUuid()) ? Color.ORANGE.getRGB() : Color.WHITE.getRGB();
                                drawCenteredString(fontRenderer, CrewUtils.buildStringForMember(player, member, crew), width/2, startY+=15, color);
                            }

                            if(isOwner){
                                //Owner GUI
                                EntityPlayer target = player.world.getPlayerEntityByName(invitePlayerNameTextField.getText());
                                Crew targetCrew = null;
                                if(target != null) {
                                    targetCrew = CrewUtils.getPlayerCrew(ClientProxy.crews, target);
                                }
                                boolean isInvitePlayerValid = !invitePlayerNameTextField.getText().isEmpty() && target != null && !CrewUtils.isPlayerInACrew(ClientProxy.crews, target);
                                boolean isKickPlayerValid = target != null && targetCrew != null && targetCrew.getName().equals(crew.getName());

                                invitePlayerButton.visible = true;
                                invitePlayerButton.enabled = isInvitePlayerValid;
                                kickPlayerButton.visible = true;
                                kickPlayerButton.enabled = isKickPlayerValid;
                                invitePlayerNameTextField.setVisible(true);
                                invitePlayerNameTextField.setEnabled(true);
                                invitePlayerNameTextField.drawTextBox();
                                invitePlayerNameTextField.setFocused(true);

                            }
                            else{
                                //Simple member GUI

                            }

                        }

                    }
                }

            }

        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id){
            case 1:{
                PacketDispatcher.sendToServer(new PacketEditCrewServer("create", createCrewNameTextField.getText(), 0));
                break;
            }
            case 3:{
                PacketDispatcher.sendToServer(new PacketEditCrewServer("setRole", mc.player.getPersistentID().toString(), 1));
                break;
            }
            case 4:{
                PacketDispatcher.sendToServer(new PacketEditCrewServer("setRole", mc.player.getPersistentID().toString(), 2));
                break;
            }
            case 5:{
                PacketDispatcher.sendToServer(new PacketEditCrewServer("setRole", mc.player.getPersistentID().toString(), 3));
                break;
            }
            case 6:{
                PacketDispatcher.sendToServer(new PacketEditCrewServer("setRole", mc.player.getPersistentID().toString(), 4));
                break;
            }
            case 7:{
                PacketDispatcher.sendToServer(new PacketEditCrewServer("setRole", mc.player.getPersistentID().toString(), 0));
                break;
            }
            case 8:{
                PacketDispatcher.sendToServer(new PacketEditCrewServer("removeMember", mc.player.getPersistentID().toString(), 0));
                break;
            }
            case 9: {
                EntityPlayer player = mc.world.getPlayerEntityByName(invitePlayerNameTextField.getText());
                if (player != null) {
                    PacketDispatcher.sendToServer(new PacketEditCrewServer("inviteMember", player.getPersistentID().toString(), 0));
                }
                break;
            }
            case 11: {
                EntityPlayer player = mc.world.getPlayerEntityByName(invitePlayerNameTextField.getText());
                if(player != null && !player.getPersistentID().equals(mc.player.getPersistentID())){
                    PacketDispatcher.sendToServer(new PacketEditCrewServer("removeMember", player.getPersistentID().toString(), 0));
                }
            }
        }

        super.actionPerformed(button);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if(createCrewNameTextField.isFocused()) {
            createCrewNameTextField.textboxKeyTyped(typedChar, keyCode);
        }
        else if(invitePlayerNameTextField.isFocused()){
            invitePlayerNameTextField.textboxKeyTyped(typedChar, keyCode);
        }
        super.keyTyped(typedChar, keyCode);

    }
}

