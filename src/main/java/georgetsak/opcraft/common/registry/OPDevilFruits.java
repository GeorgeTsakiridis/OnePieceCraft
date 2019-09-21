package georgetsak.opcraft.common.registry;

import georgetsak.opcraft.client.registry.OPRender;
import georgetsak.opcraft.common.power.PowerHandler;
import georgetsak.opcraft.common.item.devilfruits.DevilFruitAssetsManager;
import georgetsak.opcraft.common.item.devilfruits.ItemDevilFruit;
import georgetsak.opcraft.common.network.proxy.CommonProxy;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;

public class OPDevilFruits {

    public static Item GOMU;//Rubber
    public static Item MERA;//Fire
    public static Item NORO;//Slow
    public static Item SUKE;//Clear
    public static Item GIRAFFE;
    public static Item OPE;//Operation
    public static Item HIE;//Ice
    public static Item NIKYU;//Paw
    public static Item YOMI;//Revive
    public static Item GORO;//Thunder
    public static Item MOKU;//Smoke
    public static Item YAMI;//Dark
    public static Item ITO;//String

    public static final int NO_POWER_ID = 0;
    public static final int GOMU_ID = 1;
    public static final int MERA_ID = 2;
    public static final int NORO_ID = 3;
    public static final int SUKE_ID = 4;
    public static final int GIRAFFE_ID = 5;
    public static final int OPE_ID = 6;
    public static final int HIE_ID = 7;
    public static final int NIKYU_ID = 8;
    public static final int YOMI_ID = 9;
    public static final int GORO_ID = 10;
    public static final int MOKU_ID = 11;
    public static final int YAMI_ID = 12;
    public static final int ITO_ID = 13;

    public static void registerDevilFruits(){
        GOMU = new ItemDevilFruit(GOMU_ID).setRegistryName("devil_fruit_gomu");
        MERA = new ItemDevilFruit(MERA_ID).setRegistryName("devil_fruit_mera");
        NORO = new ItemDevilFruit(NORO_ID).setRegistryName("devil_fruit_noro");
        SUKE = new ItemDevilFruit(SUKE_ID).setRegistryName("devil_fruit_suke");
        GIRAFFE = new ItemDevilFruit(GIRAFFE_ID).setRegistryName("devil_fruit_giraffe");
        OPE = new ItemDevilFruit(OPE_ID).setRegistryName("devil_fruit_ope");
        HIE = new ItemDevilFruit(HIE_ID).setRegistryName("devil_fruit_hie");
        NIKYU = new ItemDevilFruit(NIKYU_ID).setRegistryName("devil_fruit_nikyu");
        YOMI = new ItemDevilFruit(YOMI_ID).setRegistryName("devil_fruit_yomi");
        GORO = new ItemDevilFruit(GORO_ID).setRegistryName("devil_fruit_goro");
        MOKU = new ItemDevilFruit(MOKU_ID).setRegistryName("devil_fruit_moku");
        YAMI = new ItemDevilFruit(YAMI_ID).setRegistryName("devil_fruit_yami");
        ITO = new ItemDevilFruit(ITO_ID).setRegistryName("devil_fruit_ito");

        registerItem(GOMU);
        registerItem(MERA);
        registerItem(NORO);
        registerItem(SUKE);
        registerItem(GIRAFFE);
        registerItem(OPE);
        registerItem(HIE);
        registerItem(NIKYU);
        registerItem(YOMI);
        registerItem(GORO);
        registerItem(MOKU);
        registerItem(YAMI);
        registerItem(ITO);

        DevilFruitAssetsManager.addDevilFruitAsset(NO_POWER_ID, null, null, "");//if a non existent fruit is requested this will be returned. This also stands for No power status.
        DevilFruitAssetsManager.addDevilFruitAsset(GOMU_ID, "gomu", "Gum-Gum Fruit", "Gomu Gomu no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(MERA_ID, "mera", "Flame-Flame Fruit", "Mera Mera no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(NORO_ID, "noro", "Slow-Slow Fruit", "Noro Noro no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(SUKE_ID, "suke", "Clear-Clear Fruit", "Suke Suke no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(GIRAFFE_ID, "giraffe", "Ox-Ox Fruit", "Ushi Ushi no Mi / Model:Giraffe");
        DevilFruitAssetsManager.addDevilFruitAsset(OPE_ID, "ope", "Op-Op Fruit", "Ope Ope no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(HIE_ID, "hie", "Ice-Ice Fruit", "Hie Hie no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(NIKYU_ID, "nikyu", "Paw-Paw Fruit", "Nikyu Nikyu no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(YOMI_ID, "yomi", "Revive-Revive Fruit", "Yomi Yomi no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(GORO_ID, "goro", "Thunder-Thunder Fruit", "Goro Goro no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(MOKU_ID, "moku", "Smoke-Smoke Fruit", "Moku Moku no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(YAMI_ID, "yami", "Dark-Dark Fruit", "Yami Yami no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(ITO_ID,"ito", "String-String Fruit", "Ito Ito no Mi");
    }

    public static void registerPowers(){
        PowerHandler.addPower(new int[]{60, 55, 50, 45, 40}, new int[]{260, 540, 850, 1200}, "GomuPistol", "Gomu Gomu no: Pistol", GOMU_ID,1, "pistol");
        PowerHandler.addPower(new int[]{320, 310, 300, 290, 280}, new int[]{50, 100, 150, 200}, "GomuGear2", "Gomu Gomu no: Gear 2", GOMU_ID, 2, "gear2");
        PowerHandler.addPower(new int[]{400, 360, 320, 280, 240}, new int[]{50, 100, 160, 230}, "GomuGear3", "Gomu Gomu no: Gear 3", GOMU_ID, 3, "gear3");
        PowerHandler.addPower(new int[]{2400, 2200, 2000, 1800, 1600}, new int[]{10, 20, 30, 45}, false, "GomuGear4", "Gomu Gomu no: Gear 4", GOMU_ID, 4, "gear4");
        PowerHandler.addPower(new int[]{80, 75, 70, 65, 60}, new int[]{150, 310, 480, 665}, "MeraHigan","Higan", MERA_ID, 1, "fire_fist");
        PowerHandler.addPower(new int[]{300, 290, 280, 270, 260}, new int[]{65, 130, 200, 270}, "MeraShinka","Shinka: Shiranui", MERA_ID, 2, "fire_lance");
        PowerHandler.addPower(new int[]{360, 340, 320, 300, 280}, new int[]{65, 135, 210, 290}, "MeraHiken","Hiken", MERA_ID, 3, "hiken");
        PowerHandler.addPower(new int[]{2000, 1800, 1600, 1400, 1200}, new int[]{10, 20, 30, 45}, "MeraEntei","Dai Enkai: Entei", MERA_ID, 4, "entei");
        PowerHandler.addPower(new int[]{180, 170, 160, 150, 140}, new int[]{45, 100, 150, 200}, "SlowBeam","Slow Slow Beam", NORO_ID, 1, "slow_beam");
        PowerHandler.addPower(new int[]{360, 340, 320, 300, 280}, new int[]{25, 50, 75, 100}, "SlowBall","High Speed Rotation Mirror Ball", NORO_ID, 2, "slow_ball");
        PowerHandler.addPower(new int[]{360, 340, 320, 300, 280}, new int[]{30, 70, 100, 145}, "SlowMashi","Mashi Mashi", NORO_ID, 3, "slow_mashi");
        PowerHandler.addPower(new int[]{700}, null, false, "ClearSkating","Skating (Invisibility)", SUKE_ID, 1, "skating");
        PowerHandler.addPower(new int[]{600}, null, false,"Room","Room", OPE_ID, 1, "room");
        PowerHandler.addPower(new int[]{60, 55, 50, 45, 40}, new int[]{25, 50, 90, 150}, false, "Shambles","Shambles", OPE_ID, 2, "shambles");
        PowerHandler.addPower(new int[]{120, 110, 100, 90, 80}, new int[]{50, 100, 150, 200}, "InjectionShot","Injection Shot", OPE_ID, 3, "injection_shot");
        PowerHandler.addPower(new int[]{140, 130, 120, 110, 100}, new int[]{40, 60, 85, 120}, false, "Takt","Takt", OPE_ID, 4, "takt");
        PowerHandler.addPower(new int[]{80, 75, 70, 65, 60}, new int[]{240, 500, 770, 1065}, "IceSaber","Ice Saber", HIE_ID, 1, "ice_saber");
        PowerHandler.addPower(new int[]{400, 360, 320, 280, 240}, new int[]{30, 65, 100, 140}, "IceBall","Ice Ball", HIE_ID, 2, "ice_ball");
        PowerHandler.addPower(new int[]{1200, 1100, 1000, 900, 800}, new int[]{10, 20, 30, 45}, "IceAge","Ice Age", HIE_ID, 3, "ice_age");
        PowerHandler.addPower(new int[]{700, 640, 580, 520, 460}, new int[]{30, 60, 90, 125}, "IceBlockPhBeak","Ice Block: Pheasant Beak", HIE_ID, 4, "ice_phoenix");
        PowerHandler.addPower(new int[]{100, 90, 80, 70, 60}, new int[]{155, 330, 525, 750}, "PadHo","Pad Ho", NIKYU_ID, 1, "pad_ho_small");
        PowerHandler.addPower(new int[]{100, 90, 80, 70, 60}, new int[]{190, 400, 645, 920}, "TsuppariPadHo","Tsuppari Pad Ho", NIKYU_ID, 2, "pad_ho_big");
        PowerHandler.addPower(new int[]{2800, 2600, 2400, 2200, 2000}, new int[]{10, 20, 30, 40}, "UrsusShock","Ursus Shock", NIKYU_ID, 3, "ursus_shock");
        PowerHandler.addPower(new int[]{100, 90, 80, 70, 60}, new int[]{120, 250, 400, 575}, "ElThor","El Thor", GORO_ID, 1, "el_thor");
        PowerHandler.addPower(new int[]{360, 340, 320, 300, 280}, new int[]{45, 90, 135, 190}, "Sango","Sango", GORO_ID, 2, "sango");
        PowerHandler.addPower(new int[]{2400, 2200, 2000, 1800, 1600}, new int[]{5, 15, 25, 30}, "Deathpiea","Deathpiea", GORO_ID, 3, "deathpiea");
        PowerHandler.addPower(new int[]{100, 90, 80, 70, 60}, new int[]{155, 330, 525, 750}, "WhiteBlow", "White Blow", MOKU_ID, 1, "white_blow");
        PowerHandler.addPower(new int[]{520, 500, 480, 460, 440}, new int[]{30, 60, 95, 130}, "WhiteSnake", "White Snake", MOKU_ID, 2, "smoke_snake");
        PowerHandler.addPower(new int[]{600, 550, 500, 450, 400}, new int[]{20, 40, 65, 90}, false, "WhiteOut", "White Out", MOKU_ID, 3, "white_out");
        PowerHandler.addPower(new int[]{2200, 2000, 1800, 1600, 1400}, new int[]{10, 15, 25, 40}, false, "WhiteLauncher", "White Launcher", MOKU_ID, 4, "white_launcher");
        PowerHandler.addPower(new int[]{600, 560, 520, 480, 440}, new int[]{30, 65, 100, 140}, "BlackHole", "Black Hole", YAMI_ID, 1, "dark");
        PowerHandler.addPower(new int[]{380, 360, 340, 320, 300}, new int[]{50, 100, 160, 220}, "Kurouzu", "Kurouzu", YAMI_ID, 2, "kurouzu");
        PowerHandler.addPower(new int[]{1000, 960, 920, 880, 840}, new int[]{25, 50, 75, 100}, "Liberation", "Liberation", YAMI_ID, 3, "liberation");
        PowerHandler.addPower(new int[]{100, 90, 80, 70, 60}, new int[]{190, 405, 645, 920}, "Tamaito", "Tamaito", ITO_ID, 1, "tamaito");
        PowerHandler.addPower(new int[]{2400, 2200, 2000, 1800, 1600}, new int[]{10, 20, 30, 45}, "Overheat", "Overheat", ITO_ID, 2, "overheat");
        PowerHandler.addPower(new int[]{480, 440, 400, 360, 320}, new int[]{50, 105, 165, 230}, "Goshikito", "Goshikito", ITO_ID, 3, "goshikito");
        PowerHandler.addPower(new int[]{2200, 2000, 1800, 1600, 1400}, new int[]{10, 15, 25, 40}, false, "SoraNoMichi", "Sora no Michi", ITO_ID, 4, "sora_no_michi");
    }


    static void registerItem(Item item){
        ForgeRegistries.ITEMS.register(item.setUnlocalizedName(item.getRegistryName().toString()).setCreativeTab(CommonProxy.OPTab));
        OPRender.ITEMS_TO_RENDER.add(item);
    }

}
