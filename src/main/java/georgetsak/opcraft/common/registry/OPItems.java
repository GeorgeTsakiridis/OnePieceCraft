package georgetsak.opcraft.common.registry;

import georgetsak.opcraft.client.OPSoundEvent;
import georgetsak.opcraft.common.item.*;
import georgetsak.opcraft.common.item.devilfruits.ItemPowerRemover;
import georgetsak.opcraft.common.item.weapons.ItemClima;
import georgetsak.opcraft.common.item.weapons.ItemCrocodileHook;
import georgetsak.opcraft.common.item.weapons.ItemGun;
import georgetsak.opcraft.common.item.weapons.ItemUsoppKabuto;
import georgetsak.opcraft.common.item.weapons.ammo.ItemGunAmmo;
import georgetsak.opcraft.common.item.weapons.swords.ItemSimpleSword;
import georgetsak.opcraft.common.item.weapons.swords.ItemSmokerJitte;
import georgetsak.opcraft.common.item.weapons.swords.ItemSwordWithCase;
import georgetsak.opcraft.common.item.weapons.swords.SwordPairsManager;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;

import static georgetsak.opcraft.common.network.proxy.CommonProxy.OPTab;

/**
 * Created by GeorgeTsak on 8/7/2017.
 */
public class OPItems {

    public static ArrayList<Item> ITEMS_TO_RENDER;

    public static Item KAIROSEKI_GEM;
    public static Item STEEL_INGOT;
    public static Item DARK_STEEL_INGOT;
    public static Item DARK_STEEL_NUGGET;
    public static Item WADO_ICHI_MONJI_CASED;
    public static Item WADO_ICHI_MONJI_OPEN;
    public static Item KITETSU_CASED;
    public static Item KITETSU_OPEN;
    public static Item SHUUSUI_CASED;
    public static Item SHUUSUI_OPEN;
    public static Item MIHAWK_YORU;
    public static Item ARLONG_KIRIBACHI;
    public static Item SMOKER_JITTE;
    public static Item BROOK_SWORD_CASED;
    public static Item BROOK_SWORD_OPEN;
    public static Item CROCODILE_HOOK_CASED;
    public static Item CROCODILE_HOOK_OPEN;
    public static Item KIKOKU_CASED;
    public static Item KIKOKU_OPEN;
    public static Item CLIMA_SIMPLE;
    public static Item CLIMA_COMPLETED_WATER;
    public static Item CLIMA_COMPLETED_FIRE;
    public static Item CLIMA_COMPLETED_THUNDER;
    public static Item USOPP_KABUTO;
    public static Item USOPP_KABUTO_BLACK;
    public static Item SMALL_ROCK;
    public static Item FLINTLOCK;
    public static Item FLINTLOCK_AMMO;
    public static Item SENRIKU;
    public static Item SENRIKU_AMMO;
    public static Item BAZOOKA;
    public static Item BAZOOKA_AMMO;
    public static Item WATER_DIAL;
    public static Item LAVA_DIAL;
    public static Item FIRE_DIAL;
    public static Item IMPACT_DIAL;
    public static Item THUNDER_DIAL;
    public static Item CUTLASS;
    public static Item BERRY_COIN;
    public static Item MANUAL_BOOK;
    public static Item DEVIL_FRUIT_POWER_REMOVER;
    public static Item WE_ARE_DISK;
    public static Item SAKE;
    public static Item ACE_BOAT;
    public static Item SAIL_BOAT;
    public static Item LAW_HEART;
    public static Item BISENTO;
    public static Item SHIGURE_CASED;
    public static Item SHIGURE_OPEN;
    public static Item YUBASHIRI_CASED;
    public static Item YUBASHIRI_OPEN;
    public static Item TERRY_SWORD;
    public static Item EISEN_WHIP;
    public static Item DURANDAL_CASED;
    public static Item DURANDAL_OPEN;
    public static Item SAMEKIRI_BOCHO_SWORD;
    public static Item PRETZEL_SWORD;
    public static Item SHIRAUO_CASED;
    public static Item SHIRAUO_OPEN;
    public static Item BANDAGE;
    public static Item SUTURES;
    public static Item FIRST_AID_KIT;
    public static Item EXTOL_COIN;
    public static Item EMPTY_DIAL;

    public static void registerItems(){
        ITEMS_TO_RENDER = new ArrayList<>();
        STEEL_INGOT = new ItemSteelIngot().setRegistryName("steel_ingot").setCreativeTab(OPTab);
        KAIROSEKI_GEM = new ItemKairosekiGem().setRegistryName("kairoseki_gem").setCreativeTab(OPTab);
        DARK_STEEL_INGOT = new ItemSteelIngot().setRegistryName("dark_steel_ingot").setCreativeTab(OPTab);
        DARK_STEEL_NUGGET = new Item().setRegistryName("dark_steel_nugget").setCreativeTab(OPTab);

        WADO_ICHI_MONJI_CASED = new ItemSwordWithCase(3000, 0).setRegistryName("wado_ichi_monji_cased").setCreativeTab(OPTab);
        WADO_ICHI_MONJI_OPEN = new ItemSwordWithCase(3000, 7).setRegistryName("wado_ichi_monji_open");
        KITETSU_CASED = new ItemSwordWithCase(3000, 0).setRegistryName("kitetsu_cased").setCreativeTab(OPTab);
        KITETSU_OPEN = new ItemSwordWithCase(3000, 6).setRegistryName("kitetsu_open");
        SHUUSUI_CASED = new ItemSwordWithCase(3000, 0).setRegistryName("shuusui_cased").setCreativeTab(OPTab);
        SHUUSUI_OPEN = new ItemSwordWithCase(3000, 9).setRegistryName("shuusui_open");
        MIHAWK_YORU = new ItemSimpleSword(10000, 14).setRegistryName("mihawk_yoru").setCreativeTab(OPTab);
        ARLONG_KIRIBACHI = new ItemSimpleSword(2000, 10).setRegistryName("arlong_kiribachi").setCreativeTab(OPTab);
        SMOKER_JITTE = new ItemSmokerJitte(2000, 9).setRegistryName("smoker_jitte").setCreativeTab(OPTab);
        BROOK_SWORD_CASED = new ItemSwordWithCase(3000, 0).setRegistryName("brook_sword_cased").setCreativeTab(OPTab);
        BROOK_SWORD_OPEN = new ItemSwordWithCase(3000, 8).setRegistryName("brook_sword_open");
        CROCODILE_HOOK_CASED = new ItemCrocodileHook(4000, 0, 1).setRegistryName("crocodile_hook_cased").setCreativeTab(OPTab);
        CROCODILE_HOOK_OPEN = new ItemCrocodileHook(4000, 5, 2).setRegistryName("crocodile_hook_open");
        KIKOKU_CASED = new ItemSwordWithCase(4000, 0).setRegistryName("kikoku_cased").setCreativeTab(OPTab);
        KIKOKU_OPEN = new ItemSwordWithCase(4000, 8).setRegistryName("kikoku_open");
        CLIMA_SIMPLE = new ItemClima(100, 1, 0, false).setRegistryName("clima_simple").setCreativeTab(OPTab);
        CLIMA_COMPLETED_WATER = new ItemClima(100, 2, 1, true).setRegistryName("clima_water").setCreativeTab(OPTab);
        CLIMA_COMPLETED_FIRE = new ItemClima(100, 2, 2, true).setRegistryName("clima_fire");
        CLIMA_COMPLETED_THUNDER = new ItemClima(100, 2, 3, true).setRegistryName("clima_thunder");
        USOPP_KABUTO = new ItemUsoppKabuto(0).setRegistryName("usopp_kabuto").setCreativeTab(OPTab);
        USOPP_KABUTO_BLACK = new ItemUsoppKabuto(1).setRegistryName("usopp_kabuto_black").setCreativeTab(OPTab);
        SMALL_ROCK = new Item().setRegistryName("small_rock").setCreativeTab(OPTab);
        FLINTLOCK = new ItemGun(50, 10, 30, 20, 1).setRegistryName("flintlock").setCreativeTab(OPTab);
        SENRIKU = new ItemGun(300, 6, 80, 10, 2).setRegistryName("senriku").setCreativeTab(OPTab);
        BAZOOKA = new ItemGun(500, 18, 20, 60, 3).setRegistryName("bazooka").setCreativeTab(OPTab);
        FLINTLOCK_AMMO = new ItemGunAmmo().setRegistryName("flintlock_ammo").setCreativeTab(OPTab);
        SENRIKU_AMMO = new ItemGunAmmo().setRegistryName("senriku_ammo").setCreativeTab(OPTab);
        BAZOOKA_AMMO = new ItemGunAmmo().setRegistryName("bazooka_ammo").setCreativeTab(OPTab);
        WATER_DIAL = new ItemDial(1).setRegistryName("water_dial").setCreativeTab(OPTab);
        LAVA_DIAL = new ItemDial(2).setRegistryName("lava_dial").setCreativeTab(OPTab);
        FIRE_DIAL = new ItemDial(3).setRegistryName("fire_dial").setCreativeTab(OPTab);
        IMPACT_DIAL = new ItemDial(4).setRegistryName("impact_dial").setCreativeTab(OPTab);
        THUNDER_DIAL = new ItemDial(5).setRegistryName("thunder_dial").setCreativeTab(OPTab);
        CUTLASS = new ItemSimpleSword(251, 6).setRegistryName("cutlass").setCreativeTab(OPTab);
        BERRY_COIN = new Item().setRegistryName("berry_coin").setCreativeTab(OPTab);
        MANUAL_BOOK = new ItemOPBook().setRegistryName("manual_book").setCreativeTab(OPTab);
        DEVIL_FRUIT_POWER_REMOVER = new ItemPowerRemover().setRegistryName("devil_fruit_power_remover").setCreativeTab(OPTab);
        WE_ARE_DISK = new ItemOPRecord("disc_we_are", OPSoundEvent.we_are).setRegistryName("disc_we_are").setCreativeTab(OPTab);
        SAKE = new ItemFood(2, 0.3F, false).setAlwaysEdible().setPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200, 0), 0.5F).setRegistryName("sake").setCreativeTab(OPTab);
        ACE_BOAT = new ItemOPBoat(0).setRegistryName("ace_boat").setCreativeTab(OPTab).setMaxStackSize(1);
        SAIL_BOAT = new ItemOPBoat(1).setRegistryName("sail_boat").setCreativeTab(OPTab).setMaxStackSize(1);
        LAW_HEART = new ItemLawHeart().setRegistryName("law_heart").setCreativeTab(OPTab).setMaxStackSize(1);
        BISENTO = new ItemSimpleSword(3000, 11).setRegistryName("bisento").setCreativeTab(OPTab).setMaxStackSize(1);
        SHIGURE_CASED = new ItemSwordWithCase(251, 0).setRegistryName("shigure_cased").setCreativeTab(OPTab);
        SHIGURE_OPEN = new ItemSwordWithCase(251, 6).setRegistryName("shigure_open");
        YUBASHIRI_CASED = new ItemSwordWithCase(502, 0).setRegistryName("yubashiri_cased").setCreativeTab(OPTab);
        YUBASHIRI_OPEN = new ItemSwordWithCase(502, 7).setRegistryName("yubashiri_open");
        TERRY_SWORD = new ItemSimpleSword(264, 4).setRegistryName("terry_sword").setCreativeTab(OPTab);
        EISEN_WHIP = new ItemSimpleSword(502, 7).setRegistryName("eisen_whip").setCreativeTab(OPTab);
        DURANDAL_CASED = new ItemSwordWithCase(2500, 0).setRegistryName("durandal_cased").setCreativeTab(OPTab);
        DURANDAL_OPEN = new ItemSwordWithCase(2500, 8).setRegistryName("durandal_open");
        SAMEKIRI_BOCHO_SWORD = new ItemSimpleSword(502, 7).setRegistryName("samekiri_bocho_sword").setCreativeTab(OPTab);
        PRETZEL_SWORD = new ItemSimpleSword(2500, 8).setRegistryName("pretzel_sword").setCreativeTab(OPTab);
        SHIRAUO_CASED = new ItemSwordWithCase(3000, 0).setRegistryName("shirauo_cased").setCreativeTab(OPTab);
        SHIRAUO_OPEN = new ItemSwordWithCase(3000, 9).setRegistryName("shirauo_open");
        BANDAGE = new ItemHeal(4).setRegistryName("bandage").setCreativeTab(OPTab);
        SUTURES = new ItemHeal(6).setRegistryName("sutures").setCreativeTab(OPTab);
        FIRST_AID_KIT = new ItemHeal(10).setRegistryName("first_aid_kit").setCreativeTab(OPTab);
        EXTOL_COIN = new Item().setRegistryName("extol_coin").setCreativeTab(OPTab);
        EMPTY_DIAL = new Item().setRegistryName("empty_dial").setCreativeTab(OPTab);

        SwordPairsManager.addPair(WADO_ICHI_MONJI_CASED, WADO_ICHI_MONJI_OPEN);
        SwordPairsManager.addPair(KITETSU_CASED, KITETSU_OPEN);
        SwordPairsManager.addPair(SHUUSUI_CASED, SHUUSUI_OPEN);
        SwordPairsManager.addPair(BROOK_SWORD_CASED, BROOK_SWORD_OPEN);
        SwordPairsManager.addPair(KIKOKU_CASED, KIKOKU_OPEN);
        SwordPairsManager.addPair(SHIGURE_CASED, SHIGURE_OPEN);
        SwordPairsManager.addPair(YUBASHIRI_CASED, YUBASHIRI_OPEN);
        SwordPairsManager.addPair(DURANDAL_CASED, DURANDAL_OPEN);
        SwordPairsManager.addPair(SHIRAUO_CASED, SHIRAUO_OPEN);

        registerItem(STEEL_INGOT);
        registerItem(DARK_STEEL_INGOT);
        registerItem(DARK_STEEL_NUGGET);
        registerItem(KAIROSEKI_GEM);
        registerItem(WADO_ICHI_MONJI_CASED);
        registerItem(WADO_ICHI_MONJI_OPEN);
        registerItem(KITETSU_CASED);
        registerItem(KITETSU_OPEN);
        registerItem(SHUUSUI_CASED);
        registerItem(SHUUSUI_OPEN);
        registerItem(MIHAWK_YORU);
        registerItem(ARLONG_KIRIBACHI);
        registerItem(SMOKER_JITTE);
        registerItem(BROOK_SWORD_CASED);
        registerItem(BROOK_SWORD_OPEN);
        registerItem(CROCODILE_HOOK_CASED);
        registerItem(CROCODILE_HOOK_OPEN);
        registerItem(KIKOKU_CASED);
        registerItem(KIKOKU_OPEN);
        registerItem(CLIMA_SIMPLE);
        registerItem(CLIMA_COMPLETED_WATER);
        registerItem(CLIMA_COMPLETED_FIRE);
        registerItem(CLIMA_COMPLETED_THUNDER);
        registerItem(USOPP_KABUTO);
        registerItem(USOPP_KABUTO_BLACK);
        registerItem(SMALL_ROCK);
        registerItem(FLINTLOCK);
        registerItem(SENRIKU);
        registerItem(BAZOOKA);
        registerItem(FLINTLOCK_AMMO);
        registerItem(SENRIKU_AMMO);
        registerItem(BAZOOKA_AMMO);
        registerItem(WATER_DIAL);
        registerItem(LAVA_DIAL);
        registerItem(FIRE_DIAL);
        registerItem(THUNDER_DIAL);
        registerItem(IMPACT_DIAL);
        registerItem(CUTLASS);
        registerItem(BERRY_COIN);
        registerItem(MANUAL_BOOK);
        registerItem(DEVIL_FRUIT_POWER_REMOVER);
        registerItem(WE_ARE_DISK);
        registerItem(SAKE);
        registerItem(ACE_BOAT);
        registerItem(SAIL_BOAT);
        registerItem(LAW_HEART);
        registerItem(BISENTO);
        registerItem(SHIGURE_CASED);
        registerItem(SHIGURE_OPEN);
        registerItem(YUBASHIRI_CASED);
        registerItem(YUBASHIRI_OPEN);
        registerItem(TERRY_SWORD);
        registerItem(EISEN_WHIP);
        registerItem(DURANDAL_CASED);
        registerItem(DURANDAL_OPEN);
        registerItem(SAMEKIRI_BOCHO_SWORD);
        registerItem(PRETZEL_SWORD);
        registerItem(SHIRAUO_CASED);
        registerItem(SHIRAUO_OPEN);
        registerItem(BANDAGE);
        registerItem(SUTURES);
        registerItem(FIRST_AID_KIT);
        registerItem(EXTOL_COIN);
        registerItem(EMPTY_DIAL);

    }

    static void registerItem(Item item){
        registerItem(item, true);
    }

    static void registerItem(Item item, boolean normalItem){
        ForgeRegistries.ITEMS.register(item.setUnlocalizedName(item.getRegistryName().toString()));
        if(normalItem){
            ITEMS_TO_RENDER.add(item);
        }
    }

}
