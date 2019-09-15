package georgetsak.opcraft.common.registry;

import georgetsak.opcraft.common.power.PowerHandler;
import georgetsak.opcraft.common.item.devilfruits.DevilFruitAssetsManager;
import georgetsak.opcraft.common.item.devilfruits.ItemDevilFruit;
import georgetsak.opcraft.common.network.proxy.CommonProxy;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class OPDevilFruits {

    public static Item ItemDevilFruitGomu;//Rubber
    public static Item ItemDevilFruitMera;//Fire
    public static Item ItemDevilFruitNoro;//Slow
    public static Item ItemDevilFruitSuke;//Clear
    public static Item ItemDevilFruitGiraffe;
    public static Item ItemDevilFruitOpe;//Operation
    public static Item ItemDevilFruitHie;//Ice
    public static Item ItemDevilFruitNikyu;//Paw
    public static Item ItemDevilFruitYomi;//Revive
    public static Item ItemDevilFruitGoro;//Thunder
    public static Item ItemDevilFruitMoku;//Smoke
    public static Item ItemDevilFruitYami;//Dark
    public static Item ItemDevilFruitIto;//String

    public static final int NO_POWER = 0;
    public static final int GOMU = 1;
    public static final int MERA = 2;
    public static final int NORO = 3;
    public static final int SUKE = 4;
    public static final int GIRAFFE = 5;
    public static final int OPE = 6;
    public static final int HIE = 7;
    public static final int NIKYU = 8;
    public static final int YOMI = 9;
    public static final int GORO = 10;
    public static final int MOKU = 11;
    public static final int YAMI = 12;
    public static final int ITO = 13;

    public static void registerDevilFruits(){
        ItemDevilFruitGomu = new ItemDevilFruit(GOMU).setRegistryName("devil_fruit_gomu");
        ItemDevilFruitMera = new ItemDevilFruit(MERA).setRegistryName("devil_fruit_mera");
        ItemDevilFruitNoro = new ItemDevilFruit(NORO).setRegistryName("devil_fruit_noro");
        ItemDevilFruitSuke = new ItemDevilFruit(SUKE).setRegistryName("devil_fruit_suke");
        ItemDevilFruitGiraffe = new ItemDevilFruit(GIRAFFE).setRegistryName("devil_fruit_giraffe");
        ItemDevilFruitOpe = new ItemDevilFruit(OPE).setRegistryName("devil_fruit_ope");
        ItemDevilFruitHie = new ItemDevilFruit(HIE).setRegistryName("devil_fruit_hie");
        ItemDevilFruitNikyu = new ItemDevilFruit(NIKYU).setRegistryName("devil_fruit_nikyu");
        ItemDevilFruitYomi = new ItemDevilFruit(YOMI).setRegistryName("devil_fruit_yomi");
        ItemDevilFruitGoro = new ItemDevilFruit(GORO).setRegistryName("devil_fruit_goro");
        ItemDevilFruitMoku = new ItemDevilFruit(MOKU).setRegistryName("devil_fruit_moku");
        ItemDevilFruitYami = new ItemDevilFruit(YAMI).setRegistryName("devil_fruit_yami");
        ItemDevilFruitIto = new ItemDevilFruit(ITO).setRegistryName("devil_fruit_ito");

        registerItem(ItemDevilFruitGomu);
        registerItem(ItemDevilFruitMera);
        registerItem(ItemDevilFruitNoro);
        registerItem(ItemDevilFruitSuke);
        registerItem(ItemDevilFruitGiraffe);
        registerItem(ItemDevilFruitOpe);
        registerItem(ItemDevilFruitHie);
        registerItem(ItemDevilFruitNikyu);
        registerItem(ItemDevilFruitYomi);
        registerItem(ItemDevilFruitGoro);
        registerItem(ItemDevilFruitMoku);
        registerItem(ItemDevilFruitYami);
        registerItem(ItemDevilFruitIto);

        DevilFruitAssetsManager.addDevilFruitAsset(NO_POWER, null, null, "");//if a non existent fruit is requested this will be returned. This also stands for No power status.
        DevilFruitAssetsManager.addDevilFruitAsset(GOMU, "gomu", "Gum-Gum Fruit", "Gomu Gomu no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(MERA, "mera", "Flame-Flame Fruit", "Mera Mera no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(NORO, "noro", "Slow-Slow Fruit", "Noro Noro no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(SUKE, "suke", "Clear-Clear Fruit", "Suke Suke no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(GIRAFFE, "giraffe", "Ox-Ox Fruit", "Ushi Ushi no Mi / Model:Giraffe");
        DevilFruitAssetsManager.addDevilFruitAsset(OPE, "ope", "Op-Op Fruit", "Ope Ope no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(HIE, "hie", "Ice-Ice Fruit", "Hie Hie no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(NIKYU, "nikyu", "Paw-Paw Fruit", "Nikyu Nikyu no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(YOMI, "yomi", "Revive-Revive Fruit", "Yomi Yomi no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(GORO, "goro", "Thunder-Thunder Fruit", "Goro Goro no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(MOKU, "moku", "Smoke-Smoke Fruit", "Moku Moku no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(YAMI, "yami", "Dark-Dark Fruit", "Yami Yami no Mi");
        DevilFruitAssetsManager.addDevilFruitAsset(ITO,"ito", "String-String Fruit", "Ito Ito no Mi");
    }

    public static void registerPowers(){
        PowerHandler.addPower(new int[]{60, 55, 50, 45, 40}, new int[]{260, 540, 850, 1200}, "GomuPistol", "Gomu Gomu no: Pistol", GOMU,1, "pistol");
        PowerHandler.addPower(new int[]{320, 310, 300, 290, 280}, new int[]{50, 100, 150, 200}, "GomuGear2", "Gomu Gomu no: Gear 2", GOMU, 2, "gear2");
        PowerHandler.addPower(new int[]{400, 360, 320, 280, 240}, new int[]{50, 100, 160, 230}, "GomuGear3", "Gomu Gomu no: Gear 3", GOMU, 3, "gear3");
        PowerHandler.addPower(new int[]{2400, 2200, 2000, 1800, 1600}, new int[]{10, 20, 30, 45}, false, "GomuGear4", "Gomu Gomu no: Gear 4", GOMU, 4, "gear4");
        PowerHandler.addPower(new int[]{80, 75, 70, 65, 60}, new int[]{150, 310, 480, 665}, "MeraHigan","Higan", MERA, 1, "fire_fist");
        PowerHandler.addPower(new int[]{300, 290, 280, 270, 260}, new int[]{65, 130, 200, 270}, "MeraShinka","Shinka: Shiranui", MERA, 2, "fire_lance");
        PowerHandler.addPower(new int[]{360, 340, 320, 300, 280}, new int[]{65, 135, 210, 290}, "MeraHiken","Hiken", MERA, 3, "hiken");
        PowerHandler.addPower(new int[]{2000, 1800, 1600, 1400, 1200}, new int[]{10, 20, 30, 45}, "MeraEntei","Dai Enkai: Entei",MERA, 4, "entei");
        PowerHandler.addPower(new int[]{180, 170, 160, 150, 140}, new int[]{45, 100, 150, 200}, "SlowBeam","Slow Slow Beam", NORO, 1, "slow_beam");
        PowerHandler.addPower(new int[]{360, 340, 320, 300, 280}, new int[]{25, 50, 75, 100}, "SlowBall","High Speed Rotation Mirror Ball", NORO, 2, "slow_ball");
        PowerHandler.addPower(new int[]{360, 340, 320, 300, 280}, new int[]{30, 70, 100, 145}, "SlowMashi","Mashi Mashi", NORO, 3, "slow_mashi");
        PowerHandler.addPower(new int[]{700}, null, false, "ClearSkating","Skating (Invisibility)", SUKE, 1, "skating");
        PowerHandler.addPower(new int[]{600}, null, false,"Room","Room", OPE, 1, "room");
        PowerHandler.addPower(new int[]{60, 55, 50, 45, 40}, new int[]{25, 50, 90, 150}, false, "Shambles","Shambles", OPE, 2, "shambles");
        PowerHandler.addPower(new int[]{120, 110, 100, 90, 80}, new int[]{50, 100, 150, 200}, "InjectionShot","Injection Shot", OPE, 3, "injection_shot");
        PowerHandler.addPower(new int[]{140, 130, 120, 110, 100}, new int[]{40, 60, 85, 120}, false, "Takt","Takt", OPE, 4, "takt");
        PowerHandler.addPower(new int[]{80, 75, 70, 65, 60}, new int[]{240, 500, 770, 1065}, "IceSaber","Ice Saber", HIE, 1, "ice_saber");
        PowerHandler.addPower(new int[]{400, 360, 320, 280, 240}, new int[]{30, 65, 100, 140}, "IceBall","Ice Ball", HIE, 2, "ice_ball");
        PowerHandler.addPower(new int[]{1200, 1100, 1000, 900, 800}, new int[]{10, 20, 30, 45}, "IceAge","Ice Age", HIE, 3, "ice_age");
        PowerHandler.addPower(new int[]{700, 640, 580, 520, 460}, new int[]{30, 60, 90, 125}, "IceBlockPhBeak","Ice Block: Pheasant Beak", HIE, 4, "ice_phoenix");
        PowerHandler.addPower(new int[]{100, 90, 80, 70, 60}, new int[]{155, 330, 525, 750}, "PadHo","Pad Ho", NIKYU, 1, "pad_ho_small");
        PowerHandler.addPower(new int[]{100, 90, 80, 70, 60}, new int[]{190, 400, 645, 920}, "TsuppariPadHo","Tsuppari Pad Ho", NIKYU, 2, "pad_ho_big");
        PowerHandler.addPower(new int[]{2800, 2600, 2400, 2200, 2000}, new int[]{10, 20, 30, 40}, "UrsusShock","Ursus Shock", NIKYU, 3, "ursus_shock");
        PowerHandler.addPower(new int[]{100, 90, 80, 70, 60}, new int[]{120, 250, 400, 575}, "ElThor","El Thor", GORO, 1, "el_thor");
        PowerHandler.addPower(new int[]{360, 340, 320, 300, 280}, new int[]{45, 90, 135, 190}, "Sango","Sango", GORO, 2, "sango");
        PowerHandler.addPower(new int[]{2400, 2200, 2000, 1800, 1600}, new int[]{5, 15, 25, 30}, "Deathpiea","Deathpiea", GORO, 3, "deathpiea");
        PowerHandler.addPower(new int[]{100, 90, 80, 70, 60}, new int[]{155, 330, 525, 750}, "WhiteBlow", "White Blow", MOKU, 1, "white_blow");
        PowerHandler.addPower(new int[]{520, 500, 480, 460, 440}, new int[]{30, 60, 95, 130}, "WhiteSnake", "White Snake", MOKU, 2, "smoke_snake");
        PowerHandler.addPower(new int[]{600, 550, 500, 450, 400}, new int[]{20, 40, 65, 90}, false, "WhiteOut", "White Out", MOKU, 3, "white_out");
        PowerHandler.addPower(new int[]{2200, 2000, 1800, 1600, 1400}, new int[]{10, 15, 25, 40}, false, "WhiteLauncher", "White Launcher", MOKU, 4, "white_launcher");
        PowerHandler.addPower(new int[]{600, 560, 520, 480, 440}, new int[]{30, 65, 100, 140}, "BlackHole", "Black Hole", YAMI, 1, "dark");
        PowerHandler.addPower(new int[]{380, 360, 340, 320, 300}, new int[]{50, 100, 160, 220}, "Kurouzu", "Kurouzu", YAMI, 2, "kurouzu");
        PowerHandler.addPower(new int[]{1000, 960, 920, 880, 840}, new int[]{25, 50, 75, 100}, "Liberation", "Liberation", YAMI, 3, "liberation");
        PowerHandler.addPower(new int[]{100, 90, 80, 70, 60}, new int[]{190, 405, 645, 920}, "Tamaito", "Tamaito", ITO, 1, "tamaito");
        PowerHandler.addPower(new int[]{2400, 2200, 2000, 1800, 1600}, new int[]{10, 20, 30, 45}, "Overheat", "Overheat", ITO, 2, "overheat");
        PowerHandler.addPower(new int[]{480, 440, 400, 360, 320}, new int[]{50, 105, 165, 230}, "Goshikito", "Goshikito", ITO, 3, "goshikito");
        PowerHandler.addPower(new int[]{2200, 2000, 1800, 1600, 1400}, new int[]{10, 15, 25, 40}, false, "SoraNoMichi", "Sora no Michi", ITO, 4, "sora_no_michi");
    }


    static void registerItem(Item item){
        ForgeRegistries.ITEMS.register(item.setUnlocalizedName(item.getRegistryName().toString()).setCreativeTab(CommonProxy.OPTab));
    }

}
