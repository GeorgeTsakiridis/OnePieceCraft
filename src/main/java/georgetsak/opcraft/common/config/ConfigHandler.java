package georgetsak.opcraft.common.config;

import georgetsak.opcraft.common.registry.OPDevilFruits;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;

public class ConfigHandler {

    private ArrayList<ConfigEntry> configEntries = new ArrayList<>();

    private Configuration configuration;

    public ConfigEntryBoolean enableDevilFruitsSpawning;
    public ConfigEntryBoolean enableDevilFruitGomuSpawning;
    public ConfigEntryBoolean enableDevilFruitMeraSpawning;
    public ConfigEntryBoolean enableDevilFruitNoroSpawning;
    public ConfigEntryBoolean enableDevilFruitSukeSpawning;
    public ConfigEntryBoolean enableDevilFruitUshiSpawning;
    public ConfigEntryBoolean enableDevilFruitOpeSpawning;
    public ConfigEntryBoolean enableDevilFruitHieSpawning;
    public ConfigEntryBoolean enableDevilFruitNikyuSpawning;
    public ConfigEntryBoolean enableDevilFruitYomiSpawning;
    public ConfigEntryBoolean enableDevilFruitGoroSpawning;
    public ConfigEntryBoolean enableDevilFruitMokuSpawning;
    public ConfigEntryBoolean enableDevilFruitYamiSpawning;
    public ConfigEntryBoolean enableDevilFruitItoSpawning;

    public ConfigEntryBoolean disablePowersGomu;
    public ConfigEntryBoolean disablePowersMera;
    public ConfigEntryBoolean disablePowersNoro;
    public ConfigEntryBoolean disablePowersSuke;
    public ConfigEntryBoolean disablePowersUshi;
    public ConfigEntryBoolean disablePowersOpe;
    public ConfigEntryBoolean disablePowersHie;
    public ConfigEntryBoolean disablePowersNikyu;
    public ConfigEntryBoolean disablePowersYomi;
    public ConfigEntryBoolean disablePowersGoro;
    public ConfigEntryBoolean disablePowersMoku;
    public ConfigEntryBoolean disablePowersYami;
    public ConfigEntryBoolean disablePowersIto;

    public ConfigEntryInt cooldownSpeed;

    public ConfigEntryBoolean enableMorganFortress;
    public ConfigEntryInt morganFortressSpawnChance;

    public ConfigEntryBoolean disableGriefing;
    public ConfigEntryBoolean allowDevilFruitUsersToSwim;
    public ConfigEntryBoolean doesSeaStoneAffectDevilFruitUsers;

    public ConfigHandler(FMLPreInitializationEvent event){
        configuration = new Configuration(event.getSuggestedConfigurationFile());
        configuration.load();
        syncConfig();
        configuration.save();

    }

    private void syncConfig(){
        final String CATEGORY_GENERATION = "world_generation";
        final String CATEGORY_FRUITS = "devil_fruits";
        final String CATEGORY_POWERS = "powers";
        final String CATEGORY_MISC = "misc";
        configuration.addCustomCategoryComment(CATEGORY_FRUITS, "If you change spawning options, changes will apply only to the new generated chunks. Creating a new world is recommended.");

        configEntries.add(enableDevilFruitsSpawning = new ConfigEntryBoolean(configuration, "allowDevilFruitsSpawning", "", CATEGORY_GENERATION, true));
        configEntries.add(enableDevilFruitGomuSpawning = new ConfigEntryBoolean(configuration, "enableDevilFruitGomuSpawning", "Rubber Devil Fruit", CATEGORY_GENERATION, true));
        configEntries.add(enableDevilFruitMeraSpawning = new ConfigEntryBoolean(configuration, "enableDevilFruitMeraSpawning", "Fire Devil Fruit", CATEGORY_GENERATION, true));
        configEntries.add(enableDevilFruitNoroSpawning = new ConfigEntryBoolean(configuration, "enableDevilFruitNoroSpawning", "Slow Devil Fruit", CATEGORY_GENERATION, true));
        configEntries.add(enableDevilFruitSukeSpawning = new ConfigEntryBoolean(configuration, "enableDevilFruitSukeSpawning", "Clear Devil Fruit", CATEGORY_GENERATION, true));
        configEntries.add(enableDevilFruitUshiSpawning = new ConfigEntryBoolean(configuration, "enableDevilFruitUshiSpawning", "Giraffe Devil Fruit", CATEGORY_GENERATION, false));
        configEntries.add(enableDevilFruitOpeSpawning = new ConfigEntryBoolean(configuration, "enableDevilFruitOpeSpawning", "Operation (Law's) Devil Fruit", CATEGORY_GENERATION, true));
        configEntries.add(enableDevilFruitHieSpawning = new ConfigEntryBoolean(configuration, "enableDevilFruitHieSpawning", "Ice Devil Fruit", CATEGORY_GENERATION, true));
        configEntries.add(enableDevilFruitNikyuSpawning = new ConfigEntryBoolean(configuration, "enableDevilFruitNikyuSpawning", "Paw Devil Fruit", CATEGORY_GENERATION, true));
        configEntries.add(enableDevilFruitYomiSpawning = new ConfigEntryBoolean(configuration, "enableDevilFruitYomiSpawning", "Revive Devil Fruit", CATEGORY_GENERATION, true));
        configEntries.add(enableDevilFruitGoroSpawning = new ConfigEntryBoolean(configuration, "enableDevilFruitGoroSpawning", "Thunder Devil Fruit", CATEGORY_GENERATION, true));
        configEntries.add(enableDevilFruitMokuSpawning = new ConfigEntryBoolean(configuration, "enableDevilFruitMokuSpawning", "Smoke Devil Fruit", CATEGORY_GENERATION, true));
        configEntries.add(enableDevilFruitYamiSpawning = new ConfigEntryBoolean(configuration, "enableDevilFruitYamiSpawning", "Dark Devil Fruit", CATEGORY_GENERATION, true));
        configEntries.add(enableDevilFruitItoSpawning = new ConfigEntryBoolean(configuration, "enableDevilFruitItoSpawning", "String Devil Fruit", CATEGORY_GENERATION, true));

        configEntries.add(disablePowersGomu = new ConfigEntryBoolean(configuration, "disablePowersGomu", "Rubber Devil Fruit", CATEGORY_POWERS, false, OPDevilFruits.GOMU));
        configEntries.add(disablePowersMera = new ConfigEntryBoolean(configuration, "disablePowersMera", "Fire Devil Fruit", CATEGORY_POWERS, false, OPDevilFruits.MERA));
        configEntries.add(disablePowersNoro = new ConfigEntryBoolean(configuration, "disablePowersNoro", "Slow Devil Fruit", CATEGORY_POWERS, false, OPDevilFruits.NORO));
        configEntries.add(disablePowersSuke = new ConfigEntryBoolean(configuration, "disablePowersSuke", "Clear Devil Fruit", CATEGORY_POWERS, false, OPDevilFruits.SUKE));
        configEntries.add(disablePowersUshi = new ConfigEntryBoolean(configuration, "disablePowersUshi", "Giraffe Devil Fruit", CATEGORY_POWERS, false, OPDevilFruits.GIRAFFE));
        configEntries.add(disablePowersOpe = new ConfigEntryBoolean(configuration, "disablePowersOpe", "Operation (Law's) Devil Fruit", CATEGORY_POWERS, false, OPDevilFruits.OPE));
        configEntries.add(disablePowersHie = new ConfigEntryBoolean(configuration, "disablePowersHie", "Ice Devil Fruit", CATEGORY_POWERS, false, OPDevilFruits.HIE));
        configEntries.add(disablePowersNikyu = new ConfigEntryBoolean(configuration, "disablePowersNikyu", "Paw Devil Fruit", CATEGORY_POWERS, false, OPDevilFruits.NIKYU));
        configEntries.add(disablePowersYomi = new ConfigEntryBoolean(configuration, "disablePowersYomi", "Revive Devil Fruit", CATEGORY_POWERS, false, OPDevilFruits.YOMI));
        configEntries.add(disablePowersGoro = new ConfigEntryBoolean(configuration, "disablePowersGoro", "Thunder Devil Fruit", CATEGORY_POWERS, false, OPDevilFruits.GORO));
        configEntries.add(disablePowersMoku = new ConfigEntryBoolean(configuration, "disablePowersMoku", "Smoke Devil Fruit", CATEGORY_POWERS, false, OPDevilFruits.MOKU));
        configEntries.add(disablePowersYami = new ConfigEntryBoolean(configuration, "disablePowersYami", "Dark Devil Fruit", CATEGORY_POWERS, false, OPDevilFruits.YAMI));
        configEntries.add(disablePowersIto = new ConfigEntryBoolean(configuration, "disablePowersIto", "String Devil Fruit", CATEGORY_POWERS, false, OPDevilFruits.ITO));

        configEntries.add(cooldownSpeed = new ConfigEntryInt(configuration, "cooldownSpeedMultiplier","Cooldown (wait time between powers) duration in ticks per second. 20 is normal. 40 is double, 10 is half", CATEGORY_POWERS,20,5,100));

        configEntries.add(enableMorganFortress = new ConfigEntryBoolean(configuration, "enableMorganFortressGeneration", "Enables or Disables Morgan's fortress generation", CATEGORY_GENERATION, true));
        configEntries.add(morganFortressSpawnChance = new ConfigEntryInt(configuration, "morganFortressSpawnChance", "Chance 1/x per chunk (Only for plain biome chunks)", CATEGORY_GENERATION, 800,200,Integer.MAX_VALUE));

        configEntries.add(disableGriefing = new ConfigEntryBoolean(configuration,"disableGriefing","Disables the griefing from the mod. E.g. The explosion from Gear 3 won't destroy any blocks. WARNING! Enabling this will make some powers useless.", CATEGORY_MISC, false));
        configEntries.add(allowDevilFruitUsersToSwim = new ConfigEntryBoolean(configuration,"allowDevilFruitUsersToSwim","Allows the Devil Fruit users to swim and use their powers in water.", CATEGORY_MISC, false));
        configEntries.add(doesSeaStoneAffectDevilFruitUsers = new ConfigEntryBoolean(configuration,"doesSeaStoneAffectDevilFruitUsers","Whether or not Sea Stone (Kairoseki) items weaken Devil Fruit users", CATEGORY_MISC, true));
    }

    public void restoreConfig(){
        configEntries.forEach(ConfigEntry::restore);
    }

    public ArrayList<ConfigEntry> getConfigEntries(){
        return configEntries;
    }

    public boolean isPowerDisabled(int id){
        for(ConfigEntry entry : configEntries){
            if(entry instanceof ConfigEntryBoolean && entry.getID() == id){
                return ((ConfigEntryBoolean) entry).getCurrentValue();
            }
        }
        return false;
    }

}
