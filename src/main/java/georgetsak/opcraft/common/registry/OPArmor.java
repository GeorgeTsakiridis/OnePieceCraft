package georgetsak.opcraft.common.registry;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.registry.OPRender;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;

import static georgetsak.opcraft.common.network.proxy.CommonProxy.OPTab;

/**
 * Created by GeorgeTsak on 8/7/2017.
 */
public class OPArmor {

    public static ItemArmor.ArmorMaterial luffySimpleArmor;
    public static ItemArmor.ArmorMaterial zoroSimpleArmor;
    public static ItemArmor.ArmorMaterial usoppSimpleArmor;
    public static ItemArmor.ArmorMaterial sanjiSimpleArmor;
    public static ItemArmor.ArmorMaterial marineSimpleArmor;
    public static ItemArmor.ArmorMaterial pirateSimpleArmor;

    public static Item LuffySimpleHelmet;
    public static Item LuffySimpleChestplate;
    public static Item LuffySimpleLeggings;
    public static Item LuffySimpleBoots;
    public static Item ZoroSimpleHelmet;
    public static Item ZoroSimpleChestplate;
    public static Item ZoroSimpleLeggings;
    public static Item ZoroSimpleBoots;
    public static Item UsoppSimpleHelmet;
    public static Item UsoppSimpleChestplate;
    public static Item UsoppSimpleLeggings;
    public static Item UsoppSimpleBoots;
    public static Item SanjiSimpleHelmet;
    public static Item SanjiSimpleChestplate;
    public static Item SanjiSimpleLeggings;
    public static Item SanjiSimpleBoots;
    public static Item MarineSimpleHelmet;
    public static Item MarineSimpleChestplate;
    public static Item MarineSimpleLeggings;
    public static Item MarineSimpleBoots;
    public static Item PirateSimpleChestplate;
    public static Item PirateSimpleLeggings;
    public static Item PirateSimpleBoots;


    public static void registerArmor(){

        luffySimpleArmor = EnumHelper.addArmorMaterial("luffy_simple_armor", OPCraft.MODID + ":luffysimple", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
        zoroSimpleArmor = EnumHelper.addArmorMaterial("zoro_simple_armor", OPCraft.MODID + ":zorosimple", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
        usoppSimpleArmor = EnumHelper.addArmorMaterial("usopp_simple_armor", OPCraft.MODID + ":usoppsimple", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
        sanjiSimpleArmor = EnumHelper.addArmorMaterial("sanji_simple_armor", OPCraft.MODID + ":sanjisimple", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
        marineSimpleArmor = EnumHelper.addArmorMaterial("marine_simple_armor", OPCraft.MODID + ":marinesimple", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
        pirateSimpleArmor = EnumHelper.addArmorMaterial("pirate_simple_armor", OPCraft.MODID + ":piratesimple", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);

        LuffySimpleHelmet = new ItemArmor(luffySimpleArmor, 1, EntityEquipmentSlot.HEAD).setRegistryName("luffy_simple_helmet").setCreativeTab(OPTab);
        LuffySimpleChestplate = new ItemArmor (luffySimpleArmor, 1, EntityEquipmentSlot.CHEST).setRegistryName("luffy_simple_chestplate").setCreativeTab(OPTab);
        LuffySimpleLeggings = new ItemArmor (luffySimpleArmor, 2, EntityEquipmentSlot.LEGS).setRegistryName("luffy_simple_leggings").setCreativeTab(OPTab);
        LuffySimpleBoots = new ItemArmor (luffySimpleArmor, 1, EntityEquipmentSlot.FEET).setRegistryName("luffy_simple_boots").setCreativeTab(OPTab);
        ZoroSimpleHelmet = new ItemArmor (zoroSimpleArmor, 1, EntityEquipmentSlot.HEAD).setRegistryName("zoro_simple_helmet").setCreativeTab(OPTab);
        ZoroSimpleChestplate = new ItemArmor (zoroSimpleArmor, 1, EntityEquipmentSlot.CHEST).setRegistryName("zoro_simple_chestplate").setCreativeTab(OPTab);
        ZoroSimpleLeggings = new ItemArmor (zoroSimpleArmor, 2, EntityEquipmentSlot.LEGS).setRegistryName("zoro_simple_leggings").setCreativeTab(OPTab);
        ZoroSimpleBoots = new ItemArmor (zoroSimpleArmor, 1, EntityEquipmentSlot.FEET).setRegistryName("zoro_simple_boots").setCreativeTab(OPTab);
        UsoppSimpleHelmet = new ItemArmor (usoppSimpleArmor, 1, EntityEquipmentSlot.HEAD).setRegistryName("usopp_simple_helmet").setCreativeTab(OPTab);
        UsoppSimpleChestplate = new ItemArmor (usoppSimpleArmor, 1, EntityEquipmentSlot.CHEST).setRegistryName("usopp_simple_chestplate").setCreativeTab(OPTab);
        UsoppSimpleLeggings = new ItemArmor (usoppSimpleArmor, 2, EntityEquipmentSlot.LEGS).setRegistryName("usopp_simple_leggings").setCreativeTab(OPTab);
        UsoppSimpleBoots = new ItemArmor (usoppSimpleArmor, 1, EntityEquipmentSlot.FEET).setRegistryName("usopp_simple_boots").setCreativeTab(OPTab);
        SanjiSimpleHelmet = new ItemArmor (sanjiSimpleArmor, 1, EntityEquipmentSlot.HEAD).setRegistryName("sanji_simple_helmet").setCreativeTab(OPTab);
        SanjiSimpleChestplate = new ItemArmor (sanjiSimpleArmor, 1, EntityEquipmentSlot.CHEST).setRegistryName("sanji_simple_chestplate").setCreativeTab(OPTab);
        SanjiSimpleLeggings = new ItemArmor (sanjiSimpleArmor, 2, EntityEquipmentSlot.LEGS).setRegistryName("sanji_simple_leggings").setCreativeTab(OPTab);
        SanjiSimpleBoots = new ItemArmor (sanjiSimpleArmor, 1, EntityEquipmentSlot.FEET).setRegistryName("sanji_simple_boots").setCreativeTab(OPTab);
        MarineSimpleHelmet = new ItemArmor (marineSimpleArmor, 1, EntityEquipmentSlot.HEAD).setRegistryName("marine_simple_helmet").setCreativeTab(OPTab);
        MarineSimpleChestplate = new ItemArmor (marineSimpleArmor, 1, EntityEquipmentSlot.CHEST).setRegistryName("marine_simple_chestplate").setCreativeTab(OPTab);
        MarineSimpleLeggings = new ItemArmor (marineSimpleArmor, 2, EntityEquipmentSlot.LEGS).setRegistryName("marine_simple_leggings").setCreativeTab(OPTab);
        MarineSimpleBoots = new ItemArmor (marineSimpleArmor, 1, EntityEquipmentSlot.FEET).setRegistryName("marine_simple_boots").setCreativeTab(OPTab);
        PirateSimpleChestplate = new ItemArmor (pirateSimpleArmor, 1, EntityEquipmentSlot.CHEST).setRegistryName("pirate_simple_chestplate").setCreativeTab(OPTab);
        PirateSimpleLeggings = new ItemArmor (pirateSimpleArmor, 2, EntityEquipmentSlot.LEGS).setRegistryName("pirate_simple_leggings").setCreativeTab(OPTab);
        PirateSimpleBoots = new ItemArmor (pirateSimpleArmor, 1, EntityEquipmentSlot.FEET).setRegistryName("pirate_simple_boots").setCreativeTab(OPTab);

        registerItem(LuffySimpleHelmet);
        registerItem(LuffySimpleChestplate);
        registerItem(LuffySimpleLeggings);
        registerItem(LuffySimpleBoots);
        registerItem(ZoroSimpleHelmet);
        registerItem(ZoroSimpleChestplate);
        registerItem(ZoroSimpleLeggings);
        registerItem(ZoroSimpleBoots);
        registerItem(UsoppSimpleHelmet);
        registerItem(UsoppSimpleChestplate);
        registerItem(UsoppSimpleLeggings);
        registerItem(UsoppSimpleBoots);
        registerItem(SanjiSimpleHelmet);
        registerItem(SanjiSimpleChestplate);
        registerItem(SanjiSimpleLeggings);
        registerItem(SanjiSimpleBoots);
        registerItem(MarineSimpleHelmet);
        registerItem(MarineSimpleChestplate);
        registerItem(MarineSimpleLeggings);
        registerItem(MarineSimpleBoots);
        registerItem(PirateSimpleChestplate);
        registerItem(PirateSimpleLeggings);
        registerItem(PirateSimpleBoots);

    }

    static void registerItem(Item item){
        ForgeRegistries.ITEMS.register(item.setUnlocalizedName(item.getRegistryName().toString()));
        OPRender.ITEMS_TO_RENDER.add(item);
    }

}
