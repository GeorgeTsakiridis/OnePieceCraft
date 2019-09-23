package georgetsak.opcraft.client.registry;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.gui.overlay.DevilFruitRenderOverlay;
import georgetsak.opcraft.client.gui.overlay.SixPowersSelectionWheelRender;
import georgetsak.opcraft.client.render.*;
import georgetsak.opcraft.client.render.devilfruit.*;
import georgetsak.opcraft.common.entity.boat.EntityAceBoat;
import georgetsak.opcraft.common.entity.boat.EntitySailBoat;
import georgetsak.opcraft.common.entity.devilfruit.*;
import georgetsak.opcraft.common.entity.marine.EntityHardMarine;
import georgetsak.opcraft.common.entity.marine.EntityMarine;
import georgetsak.opcraft.common.entity.marine.EntityMorgan;
import georgetsak.opcraft.common.entity.marine.EntityTequilaBridgeGuard;
import georgetsak.opcraft.common.entity.other.*;
import georgetsak.opcraft.common.registry.OPArmor;
import georgetsak.opcraft.common.registry.OPBlocks;
import georgetsak.opcraft.common.registry.OPDevilFruits;
import georgetsak.opcraft.common.registry.OPItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import java.awt.*;
import java.util.ArrayList;

public class OPRender {

    public static final DevilFruitRenderOverlay devilFruitRenderOverlay = new DevilFruitRenderOverlay();
    public static final SixPowersSelectionWheelRender sixPowersSelectionWheelRender = new SixPowersSelectionWheelRender();

    public static void registerEntityRenderers() {
        //RenderingRegistry.registerEntityRenderingHandler(EntityChristos.class, RenderChristos::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityKabutoAmmo.class, RenderKabutoAmmo::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMarine.class, RenderMarine::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHardMarine.class, RenderHardMarine::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGomuPistol.class, RenderGomuPistol::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFirePunch.class, RenderFirePunch::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityEntei.class, RenderEntei::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySlowBeam.class, RenderSlowBeam::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityIceSaber.class, RenderIceSaber::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityIcePhoenix.class, RenderIcePhoenix::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityUrsusBubble.class, RenderUrsusBubble::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMorgan.class, RenderMorgan::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBandit.class, RenderBandit::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityOPVillager.class, RenderOPVillager::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityAceBoat.class, RenderAceBoat::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySailBoat.class, RenderSailBoat::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySmokePunch.class, RenderSmokePunch::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySmokeSnake.class, RenderSmokeSnake::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySeaKing.class, RenderSeaKing::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPirate.class, RenderPirate::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDark.class, RenderDark::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCrocodile.class, RenderCrocodile::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTonta.class, RenderTonta::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityLiberation.class, RenderLiberation::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRayleigh.class, RenderRayleigh::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityStormLeg.class, RenderStormLeg::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTamaito.class, RenderTamaito::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityOverheat.class, RenderOverheat::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGoshikito.class, RenderGoshikito::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySkypiean.class, RenderSkypiean::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityKuro.class, RenderKuro::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPeacekeeper.class, RenderPeacekeeper::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHomieTree.class, RenderHomieTree::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTequilaBridgeGuard.class, RenderTequilaBridgeGuard::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySlave.class, RenderSlave::new);
    }

    public static void registerBlockRenderers() {
        for(Block block : OPBlocks.BLOCKS_TO_RENDER){
            Item item = Item.getItemFromBlock(block);
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName().toString(), "normal"));
        }
    }

    public static void registerItemRenderers() {

        for (Item item : OPItems.ITEMS_TO_RENDER) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName().toString(), "inventory"));
        }

        for (Item item : OPDevilFruits.ITEMS_TO_RENDER) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName().toString(), "inventory"));
        }

        for (Item item : OPArmor.ITEMS_TO_RENDER) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName().toString(), "inventory"));
        }

    }

    public static void registerSpecialRenderersPreInit() {
        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(OPBlocks.WATER_CLOUD), stack -> new ModelResourceLocation(OPCraft.MODID + ":cloud_water", "cloud_water"));
        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(OPBlocks.MIRROR), stack -> new ModelResourceLocation(OPCraft.MODID + ":mirror", "normal"));
        ModelLoader.setCustomStateMapper(OPBlocks.WATER_CLOUD, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(OPCraft.MODID + ":cloud_water", "cloud_water");
            }
        });

    }

    private static int colors[];

    public static void registerSpecialRenderersInit() {
        colors = new int[200];
        for(int i = 0; i < 200; i++){
            float start = 0.66f;
            float end = 1f;

            colors[i] = Color.HSBtoRGB(end - (i/200f)*(end-start), 0.9f,0.7f);
        }

        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> {
            if(pos == null)return Color.MAGENTA.getRGB();

            int index = pos.getX()*2 + 200;
            if(index > 200)index = 400 - index;

            int colorA = colors[MathHelper.clamp(index, 0, 199)];
            int colorB = new Color(colorA).darker().getRGB();

            boolean flag1 = pos.getX() % 2 == 0;
            boolean flag2 = pos.getY() % 2 == 0;
            boolean flag3 = pos.getZ() % 2 == 0;

            if(flag1 && flag3)return flag2 ? colorA : colorB;
            if(flag1)return flag2 ? colorB : colorA;
            if(flag3)return flag2 ? colorB : colorA;
            else return flag2 ? colorA : colorB;
        }, OPBlocks.MIRROR_WALL_BLOCK);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(OPBlocks.KAIROSEKI_BARS), 0, new ModelResourceLocation(Item.getItemFromBlock(OPBlocks.KAIROSEKI_BARS).getRegistryName().toString(), "inventory"));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(OPBlocks.SNAIL), 0, new ModelResourceLocation(Item.getItemFromBlock(OPBlocks.SNAIL).getRegistryName().toString(), "inventory"));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(OPBlocks.MIRROR), 0, new ModelResourceLocation(Item.getItemFromBlock(OPBlocks.MIRROR).getRegistryName().toString(), "inventory"));
    }
}
