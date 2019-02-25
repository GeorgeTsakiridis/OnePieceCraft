package georgetsak.opcraft.client.registry;

import georgetsak.opcraft.client.gui.guidebook.PageManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OPBook {
    @SideOnly(Side.CLIENT)
    public static void registerPages(){
        PageManager.addPage("null");
        PageManager.addPage("devil_fruit/page_gomu");
        PageManager.addPage("devil_fruit/page_mera");
        PageManager.addPage("devil_fruit/page_noro");
        PageManager.addPage("devil_fruit/page_suke");
        //giraffe
        PageManager.addPage("devil_fruit/page_ope");
        PageManager.addPage("devil_fruit/page_hie");
        PageManager.addPage("devil_fruit/page_nikyu");
        PageManager.addPage("devil_fruit/page_yomi");
        PageManager.addPage("devil_fruit/page_goro");
        PageManager.addPage("devil_fruit/page_moku");
        PageManager.addPage("devil_fruit/page_yami");
        PageManager.addPage("simple_recipe/page_kairoseki");
        PageManager.addPage("weapon_recipe/page_weapons1");
        PageManager.addPage("weapon_recipe/page_weapons2");
        PageManager.addPage("weapon_recipe/page_weapons3");
        PageManager.addPage("weapon_recipe/page_weapons4");
        PageManager.addPage("weapon_recipe/page_weapons5");
        PageManager.addPage("weapon_recipe/page_weapons6");
        PageManager.addPage("weapon_recipe/page_weapons7");
        PageManager.addPage("weapon_recipe/page_weapons8");
        PageManager.addPage("weapon_recipe/page_weapons9");
        PageManager.addPage("weapon_recipe/page_weapons10");
        PageManager.addPage("weapon_recipe/page_kabuto");
        PageManager.addPage("weapon_recipe/page_kabuto_black");
        PageManager.addPage("weapon_recipe/page_flintlock");
        PageManager.addPage("weapon_recipe/page_senriku");
        PageManager.addPage("weapon_recipe/page_bazooka");
        PageManager.addPage("weapon_recipe/page_clima");
        PageManager.addPage("variety_recipe/page_dials");
        PageManager.addPage("simple_recipe/page_ship_builder");
        PageManager.addPage("ship_recipe/page_striker");
        PageManager.addPage("ship_recipe/page_sail_boat");

        PageManager.addPage("entity_info/page_pirate");
        PageManager.addPage("entity_info/page_bandit");
        PageManager.addPage("entity_info/page_marine");
        PageManager.addPage("entity_info/page_villager");
        PageManager.addPage("entity_info/page_morgan");
        PageManager.addPage("entity_info/page_crocodile");
        PageManager.addPage("entity_info/page_rayleigh");
        PageManager.addPage("entity_info/page_tonta");//TODO Finish entity manual book stuff
    }
}
