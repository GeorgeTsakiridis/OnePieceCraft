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
        PowerHandler.addPower(3, 0, "GomuPistol", false, "Gomu Gomu no: Pistol", GOMU,1, "pistol");
        PowerHandler.addPower(16, 6, "GomuGear2", true, "Gomu Gomu no: Gear 2", GOMU, 2, "gear2");
        PowerHandler.addPower(15, 13, "GomuGear3", true, "Gomu Gomu no: Gear 3", GOMU, 3, "gear3");
        PowerHandler.addPower(35, 15, "GomuGear4", true, "Gomu Gomu no: Gear 4", GOMU, 4, "gear4");
        PowerHandler.addPower(3, 0, "MeraHigan", false,"Higan", MERA, 1, "fire_fist");
        PowerHandler.addPower(8, 8, "MeraShinka", true,"Shinka: Shiranui", MERA, 2, "fire_lance");
        PowerHandler.addPower(12, 8, "MeraHiken", true,"Hiken", MERA, 3, "hiken");
        PowerHandler.addPower(27, 19, "MeraEntei", true,"Dai Enkai: Entei",MERA, 4, "entei");
        PowerHandler.addPower(9, 0, "SlowBeam", false,"Slow Slow Beam", NORO, 1, "slow_beam");
        PowerHandler.addPower(18, 15, "SlowBall", true,"High Speed Rotation Mirror Ball", NORO, 2, "slow_ball");
        PowerHandler.addPower(19, 15, "SlowMashi", true,"Mashi Mashi", NORO, 3, "slow_mashi");
        PowerHandler.addPower(30, 0, "ClearSkating", false,"Skating (Invisibility)", SUKE, 1, "skating");
        PowerHandler.addPower(20, 0, "Room", false,"Room", OPE, 1, "room");
        PowerHandler.addPower(3, 0, "IceSaber", false,"Ice Saber", HIE, 1, "ice_saber");
        PowerHandler.addPower(20, 19, "IceBall", true,"Ice Ball", HIE, 2, "ice_ball");
        PowerHandler.addPower(60, 0, "IceAge", true,"Ice Age", HIE, 3, "ice_age");
        PowerHandler.addPower(25, 10, "IceBlockPhBeak", true,"Ice Block: Pheasant Beak", HIE, 4, "ice_phoenix");
        PowerHandler.addPower(5, 0, "PadHo", false,"Pad Ho", NIKYU, 1, "pad_ho_small");
        PowerHandler.addPower(20, 10, "TsuppariPadHo", true,"Tsuppari Pad Ho", NIKYU, 2, "pad_ho_big");
        PowerHandler.addPower(60, 20, "UrsusShock", true,"Ursus Shock", NIKYU, 3, "ursus_shock");
        PowerHandler.addPower(5, 0, "ElThor", false,"El Thor", GORO, 1, "el_thor");
        PowerHandler.addPower(10, 4, "Sango", true,"Sango", GORO, 2, "sango");
        PowerHandler.addPower(30, 15, "Deathpiea", true,"Deathpiea", GORO, 3, "deathpiea");
        PowerHandler.addPower(5, 0, "WhiteBlow", false, "White Blow", MOKU, 1, "white_blow");
        PowerHandler.addPower(14, 6, "WhiteSnake", true, "White Snake", MOKU, 2, "smoke_snake");
        PowerHandler.addPower(20, 5, "WhiteOut", true, "White Out", MOKU, 3, "white_out");
        PowerHandler.addPower(35, 15, "WhiteLauncher", true, "White Launcher", MOKU, 4, "white_launcher");
        PowerHandler.addPower(16, 0, "BlackHole", false, "Black Hole", YAMI, 1, "dark");
        PowerHandler.addPower(20, 15, "Kurouzu", true, "Kurouzu", YAMI, 2, "kurouzu");
        PowerHandler.addPower(60, 20, "Liberation", true, "Liberation", YAMI, 3, "liberation");

        PowerHandler.addPower(5, 0, "Tamaito", false, "Tamaito", ITO, 1);
        PowerHandler.addPower(60, 0, "Overheat", true, "Overheat", ITO, 2);
        PowerHandler.addPower(50, 0, "Goshikito", true, "Goshikito", ITO, 3);
        PowerHandler.addPower(35, 0, "Sora no Michi", true, "Sora no Michi", ITO, 4);
    }


    static void registerItem(Item item){
        ForgeRegistries.ITEMS.register(item.setUnlocalizedName(item.getRegistryName().toString()).setCreativeTab(CommonProxy.OPTab));
    }

}
