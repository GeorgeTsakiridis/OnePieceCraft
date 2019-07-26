package georgetsak.opcraft.common.registry;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.power.PowerHandler;
import georgetsak.opcraft.common.item.devilfruits.DevilFruitAssetsManager;
import georgetsak.opcraft.common.item.devilfruits.ItemDevilFruit;
import georgetsak.opcraft.common.network.proxy.CommonProxy;
import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OPDevilFruits {

    public static Item ItemDevilFruitGomu;//Luffy
    public static Item ItemDevilFruitMera;//Ace
    public static Item ItemDevilFruitNoro;//Slow
    public static Item ItemDevilFruitSuke;//Clear
    public static Item ItemDevilFruitGiraffe;
    public static Item ItemDevilFruitOpe;//Law
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

    @SideOnly(Side.CLIENT)
    public static void registerPowers(){
        PowerHandler.addPower(60, "GomuPistol", "Gomu Gomu no: Pistol", GOMU,1, "pistol");
        PowerHandler.addPower(320, "GomuGear2", "Gomu Gomu no: Gear 2", GOMU, 2, "gear2");
        PowerHandler.addPower(300, "GomuGear3", "Gomu Gomu no: Gear 3", GOMU, 3, "gear3");
        PowerHandler.addPower(700, "GomuGear4", "Gomu Gomu no: Gear 4", GOMU, 4, "gear4");
        PowerHandler.addPower(60, "MeraHigan","Higan", MERA, 1, "fire_fist");
        PowerHandler.addPower(160, "MeraShinka","Shinka: Shiranui", MERA, 2, "fire_lance");
        PowerHandler.addPower(240, "MeraHiken","Hiken", MERA, 3, "hiken");
        PowerHandler.addPower(540, "MeraEntei","Dai Enkai: Entei",MERA, 4, "entei");
        PowerHandler.addPower(180, "SlowBeam","Slow Slow Beam", NORO, 1, "slow_beam");
        PowerHandler.addPower(360, "SlowBall","High Speed Rotation Mirror Ball", NORO, 2, "slow_ball");
        PowerHandler.addPower(380, "SlowMashi","Mashi Mashi", NORO, 3, "slow_mashi");
        PowerHandler.addPower(600, "ClearSkating","Skating (Invisibility)", SUKE, 1, "skating");
        PowerHandler.addPower(600, "Room","Room", OPE, 1, "room");
        PowerHandler.addPower(60, "IceSaber","Ice Saber", HIE, 1, "ice_saber");
        PowerHandler.addPower(400, "IceBall","Ice Ball", HIE, 2, "ice_ball");
        PowerHandler.addPower(1200, "IceAge","Ice Age", HIE, 3, "ice_age");
        PowerHandler.addPower(500, "IceBlockPhBeak","Ice Block: Pheasant Beak", HIE, 4, "ice_phoenix");
        PowerHandler.addPower(100, "PadHo","Pad Ho", NIKYU, 1, "pad_ho_small");
        PowerHandler.addPower(400, "TsuppariPadHo","Tsuppari Pad Ho", NIKYU, 2, "pad_ho_big");
        PowerHandler.addPower(1200, "UrsusShock","Ursus Shock", NIKYU, 3, "ursus_shock");
        PowerHandler.addPower(100, "ElThor","El Thor", GORO, 1, "el_thor");
        PowerHandler.addPower(200, "Sango","Sango", GORO, 2, "sango");
        PowerHandler.addPower(600, "Deathpiea","Deathpiea", GORO, 3, "deathpiea");
        PowerHandler.addPower(100, "WhiteBlow", "White Blow", MOKU, 1, "white_blow");
        PowerHandler.addPower(280, "WhiteSnake", "White Snake", MOKU, 2, "smoke_snake");
        PowerHandler.addPower(400, "WhiteOut", "White Out", MOKU, 3, "white_out");
        PowerHandler.addPower(700, "WhiteLauncher", "White Launcher", MOKU, 4, "white_launcher");
        PowerHandler.addPower(320, "BlackHole", "Black Hole", YAMI, 1, "dark");
        PowerHandler.addPower(400, "Kurouzu", "Kurouzu", YAMI, 2, "kurouzu");
        PowerHandler.addPower(1200, "Liberation", "Liberation", YAMI, 3, "liberation");
        PowerHandler.addPower(60, "Tamaito", "Tamaito", ITO, 1, "tamaito");
        PowerHandler.addPower(120, "Overheat", "Overheat", ITO, 2, "overheat");
        PowerHandler.addPower(100, "Goshikito", "Goshikito", ITO, 3, "goshikito");
        PowerHandler.addPower(700, "SoraNoMichi", "Sora no Michi", ITO, 4, "sora_no_michi");
    }


    static void registerItem(Item item){
        ForgeRegistries.ITEMS.register(item.setUnlocalizedName(item.getRegistryName().toString()).setCreativeTab(CommonProxy.OPTab));
    }

}
