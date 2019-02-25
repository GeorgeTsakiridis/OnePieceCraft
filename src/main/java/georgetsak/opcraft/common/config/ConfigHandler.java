package georgetsak.opcraft.common.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler {

    public Configuration configuration;

    public boolean enableDevilFruitsSpawning;
    public boolean enableDevilFruitGomuSpawning;
    public boolean enableDevilFruitMeraSpawning;
    public boolean enableDevilFruitNoroSpawning;
    public boolean enableDevilFruitSukeSpawning;
    public boolean enableDevilFruitUshiSpawning;
    public boolean enableDevilFruitOpeSpawning;
    public boolean enableDevilFruitHieSpawning;
    public boolean enableDevilFruitNikyuSpawning;
    public boolean enableDevilFruitYomiSpawning;
    public boolean enableDevilFruitGoroSpawning;
    public boolean enableDevilFruitMokuSpawning;
    public boolean enableDevilFruitYamiSpawning;

    public boolean completelyDisableDevilFruitGomu;
    public boolean completelyDisableDevilFruitMera;
    public boolean completelyDisableDevilFruitNoro;
    public boolean completelyDisableDevilFruitSuke;
    public boolean completelyDisableDevilFruitUshi;
    public boolean completelyDisableDevilFruitOpe;
    public boolean completelyDisableDevilFruitHie;
    public boolean completelyDisableDevilFruitNikyu;
    public boolean completelyDisableDevilFruitYomi;
    public boolean completelyDisableDevilFruitGoro;
    public boolean completelyDisableDevilFruitMoku;
    public boolean completelyDisableDevilFruitYami;

    public int cooldownSpeed;
    public boolean enableSideEffects;

    public boolean enableMorganFortress;
    public int morganFortressSpawnChance;

    ///////////////////////////////
    public boolean enableDevilFruitsSpawningORIGINAL;
    public boolean enableDevilFruitGomuSpawningORIGINAL;
    public boolean enableDevilFruitMeraSpawningORIGINAL;
    public boolean enableDevilFruitNoroSpawningORIGINAL;
    public boolean enableDevilFruitSukeSpawningORIGINAL;
    public boolean enableDevilFruitUshiSpawningORIGINAL;
    public boolean enableDevilFruitOpeSpawningORIGINAL;
    public boolean enableDevilFruitHieSpawningORIGINAL;
    public boolean enableDevilFruitNikyuSpawningORIGINAL;
    public boolean enableDevilFruitYomiSpawningORIGINAL;
    public boolean enableDevilFruitGoroSpawningORIGINAL;
    public boolean enableDevilFruitMokuSpawningORIGINAL;
    public boolean enableDevilFruitYamiSpawningORIGINAL;

    public boolean completelyDisableDevilFruitGomuORIGINAL;
    public boolean completelyDisableDevilFruitMeraORIGINAL;
    public boolean completelyDisableDevilFruitNoroORIGINAL;
    public boolean completelyDisableDevilFruitSukeORIGINAL;
    public boolean completelyDisableDevilFruitUshiORIGINAL;
    public boolean completelyDisableDevilFruitOpeORIGINAL;
    public boolean completelyDisableDevilFruitHieORIGINAL;
    public boolean completelyDisableDevilFruitNikyuORIGINAL;
    public boolean completelyDisableDevilFruitYomiORIGINAL;
    public boolean completelyDisableDevilFruitGoroORIGINAL;
    public boolean completelyDisableDevilFruitMokuORIGINAL;
    public boolean completelyDisableDevilFruitYamiORIGINAL;

    public int cooldownSpeedORIGINAL;
    public boolean enableSideEffectsORIGINAL;

    public boolean enableMorganFortressORIGINAL;
    public int morganFortressSpawnChanceORIGINAL;


    ///////////////////////////////////


    public ConfigHandler(FMLPreInitializationEvent event){
        configuration = new Configuration(event.getSuggestedConfigurationFile());
        configuration.load();
        syncConfig();
        configuration.save();

    }

    private void syncConfig(){
        String CATEGORY_FRUITS = "devil_fruits";
        String CATEGORY_POWERS = "powers";
        String CATEGORY_GENERATION = "world_generation";
        configuration.addCustomCategoryComment(CATEGORY_FRUITS, "If you change spawning options, changes will apply only to the new generated chunks. Creating new world is recommended. Completely disabling a fruit will remove it from the world and the creative screen. It can still be obtained using the /give command.");
        configuration.addCustomCategoryComment(CATEGORY_FRUITS, "");

        enableDevilFruitsSpawning = enableDevilFruitsSpawningORIGINAL = configuration.getBoolean("allowDevilFruitsSpawning", CATEGORY_FRUITS, true, "");
        enableDevilFruitGomuSpawning = configuration.getBoolean("enableDevilFruitGomuSpawning", CATEGORY_FRUITS, true, "Rubber Devil Fruit");
        enableDevilFruitMeraSpawning = configuration.getBoolean("enableDevilFruitMeraSpawning", CATEGORY_FRUITS, true, "Fire Devil Fruit");
        enableDevilFruitNoroSpawning =  configuration.getBoolean("enableDevilNoroFruitSpawning", CATEGORY_FRUITS, true, "Slow Devil Fruit");
        enableDevilFruitSukeSpawning = configuration.getBoolean("enableDevilFruitSukeSpawning", CATEGORY_FRUITS, true, "Clear Devil Fruit");
        enableDevilFruitUshiSpawning = configuration.getBoolean("enableDevilFruitUshiSpawning", CATEGORY_FRUITS, true, "Giraffe Devil Fruit (WIP)");
        enableDevilFruitOpeSpawning = configuration.getBoolean("enableDevilFruitOpeSpawning", CATEGORY_FRUITS, true, "Operation (Law's) Devil Fruit");
        enableDevilFruitHieSpawning = configuration.getBoolean("enableDevilFruitHieSpawning", CATEGORY_FRUITS, true, "Ice Devil Fruit");
        enableDevilFruitNikyuSpawning = configuration.getBoolean("enableDevilFruitNikyuSpawning", CATEGORY_FRUITS, true, "Paw Devil Fruit");
        enableDevilFruitYomiSpawning = configuration.getBoolean("enableDevilFruitYomiSpawning", CATEGORY_FRUITS, true, "Revive Devil Fruit");
        enableDevilFruitGoroSpawning = configuration.getBoolean("enableDevilFruitGoroSpawning", CATEGORY_FRUITS, true, "Thunder Devil Fruit");
        enableDevilFruitMokuSpawning = configuration.getBoolean("enableDevilFruitMokuSpawning", CATEGORY_FRUITS, true, "Smoke Smoke Fruit");
        enableDevilFruitYamiSpawning = configuration.getBoolean("enableDevilFruitYamiSpawning", CATEGORY_FRUITS, true, "Dark Dark Fruit");

        completelyDisableDevilFruitGomu = configuration.getBoolean("completelyDisableDevilFruitGomu", CATEGORY_FRUITS, false, "Rubber Devil Fruit");
        completelyDisableDevilFruitMera = configuration.getBoolean("completelyDisableDevilFruitMera", CATEGORY_FRUITS, false, "Fire Devil Fruit");
        completelyDisableDevilFruitNoro = configuration.getBoolean("completelyDisableDevilFruitNoro", CATEGORY_FRUITS, false, "Slow Devil Fruit");
        completelyDisableDevilFruitSuke = configuration.getBoolean("completelyDisableDevilFruitSuke", CATEGORY_FRUITS, false, "Clear Devil Fruit");
        completelyDisableDevilFruitUshi = configuration.getBoolean("completelyDisableDevilFruitUshi", CATEGORY_FRUITS, false, "Giraffe Devil Fruit (WIP)");
        completelyDisableDevilFruitOpe = configuration.getBoolean("completelyDisableDevilFruitOpe", CATEGORY_FRUITS, false, "Operation (Law's) Devil Fruit");
        completelyDisableDevilFruitHie = configuration.getBoolean("completelyDisableDevilFruitHie", CATEGORY_FRUITS, false, "Ice Devil Fruit");
        completelyDisableDevilFruitNikyu = configuration.getBoolean("completelyDisableDevilFruitNikyu", CATEGORY_FRUITS, false, "Paw Devil Fruit");
        completelyDisableDevilFruitYomi = configuration.getBoolean("completelyDisableDevilFruitYomi", CATEGORY_FRUITS, false, "Revive Devil Fruit");
        completelyDisableDevilFruitGoro = configuration.getBoolean("completelyDisableDevilFruitGoro", CATEGORY_FRUITS, false, "Thunder Devil Fruit");
        completelyDisableDevilFruitMoku = configuration.getBoolean("completelyDisableDevilFruitMoku", CATEGORY_FRUITS, false, "Smoke Devil Fruit");
        completelyDisableDevilFruitYami = configuration.getBoolean("completelyDisableDevilFruitYami", CATEGORY_FRUITS, false, "Dark Devil Fruit");

        cooldownSpeed = configuration.getInt("cooldownSpeedMultiplier", CATEGORY_POWERS, 20, 5, 100, "Cooldown (wait time between powers) duration in ticks per second. 20 is normal. 40 is double, 10 is half");
        enableSideEffects = configuration.getBoolean("enableSideEffects", CATEGORY_FRUITS, false, "Enables side effects (Slowness, Weakness etc) after each power");

        enableMorganFortress = configuration.getBoolean("enableMorganFortressGeneration", CATEGORY_GENERATION, true, "Enables or Disables Morgan's fortress generation");
        morganFortressSpawnChance = configuration.getInt("morganFortressSpawnChange", CATEGORY_GENERATION, 800, 200, Integer.MAX_VALUE, "Chance per chunk (Only for Plain biome chunks)");
        ////////////////////////////////////
        enableDevilFruitsSpawningORIGINAL = enableDevilFruitsSpawning;
        enableDevilFruitGomuSpawningORIGINAL = enableDevilFruitGomuSpawning;
        enableDevilFruitMeraSpawningORIGINAL = enableDevilFruitMeraSpawning;
        enableDevilFruitNoroSpawningORIGINAL = enableDevilFruitNoroSpawning;
        enableDevilFruitSukeSpawningORIGINAL = enableDevilFruitSukeSpawning;
        enableDevilFruitUshiSpawningORIGINAL = enableDevilFruitUshiSpawning;
        enableDevilFruitOpeSpawningORIGINAL = enableDevilFruitOpeSpawning;
        enableDevilFruitHieSpawningORIGINAL = enableDevilFruitHieSpawning;
        enableDevilFruitNikyuSpawningORIGINAL = enableDevilFruitNikyuSpawning;
        enableDevilFruitYomiSpawningORIGINAL = enableDevilFruitYomiSpawning;
        enableDevilFruitGoroSpawningORIGINAL = enableDevilFruitGoroSpawning;
        enableDevilFruitMokuSpawningORIGINAL = enableDevilFruitMokuSpawning;
        enableDevilFruitYamiSpawningORIGINAL = enableDevilFruitYamiSpawning;

        completelyDisableDevilFruitGomuORIGINAL = completelyDisableDevilFruitGomu;
        completelyDisableDevilFruitMeraORIGINAL = completelyDisableDevilFruitMera;
        completelyDisableDevilFruitNoroORIGINAL = completelyDisableDevilFruitNoro;
        completelyDisableDevilFruitSukeORIGINAL = completelyDisableDevilFruitSuke;
        completelyDisableDevilFruitUshiORIGINAL = completelyDisableDevilFruitUshi;
        completelyDisableDevilFruitOpeORIGINAL = completelyDisableDevilFruitOpe;
        completelyDisableDevilFruitHieORIGINAL = completelyDisableDevilFruitHie;
        completelyDisableDevilFruitNikyuORIGINAL = completelyDisableDevilFruitNikyu;
        completelyDisableDevilFruitYomiORIGINAL = completelyDisableDevilFruitYomi;
        completelyDisableDevilFruitGoroORIGINAL = completelyDisableDevilFruitGoro;
        completelyDisableDevilFruitMokuORIGINAL = completelyDisableDevilFruitMoku;
        completelyDisableDevilFruitYamiORIGINAL = completelyDisableDevilFruitYami;

        cooldownSpeedORIGINAL = cooldownSpeed;
        enableSideEffectsORIGINAL = enableSideEffects;

        enableMorganFortressORIGINAL = enableMorganFortress;
        morganFortressSpawnChanceORIGINAL = morganFortressSpawnChance;

    }

    public void restoreConfig(){
        enableDevilFruitsSpawning = enableDevilFruitsSpawningORIGINAL;
        enableDevilFruitGomuSpawning = enableDevilFruitGomuSpawningORIGINAL;
        enableDevilFruitMeraSpawning = enableDevilFruitMeraSpawningORIGINAL;
        enableDevilFruitNoroSpawning = enableDevilFruitNoroSpawningORIGINAL;
        enableDevilFruitSukeSpawning = enableDevilFruitSukeSpawningORIGINAL;
        enableDevilFruitUshiSpawning = enableDevilFruitUshiSpawningORIGINAL;
        enableDevilFruitOpeSpawning = enableDevilFruitOpeSpawningORIGINAL;
        enableDevilFruitHieSpawning = enableDevilFruitHieSpawningORIGINAL;
        enableDevilFruitNikyuSpawning = enableDevilFruitNikyuSpawningORIGINAL;
        enableDevilFruitYomiSpawning = enableDevilFruitYomiSpawningORIGINAL;
        enableDevilFruitGoroSpawning = enableDevilFruitGoroSpawningORIGINAL;
        enableDevilFruitMokuSpawning = enableDevilFruitMokuSpawningORIGINAL;
        enableDevilFruitYamiSpawning = enableDevilFruitYamiSpawningORIGINAL;

        completelyDisableDevilFruitGomu = completelyDisableDevilFruitGomuORIGINAL;
        completelyDisableDevilFruitMera = completelyDisableDevilFruitMeraORIGINAL;
        completelyDisableDevilFruitNoro = completelyDisableDevilFruitNoroORIGINAL;
        completelyDisableDevilFruitSuke = completelyDisableDevilFruitSukeORIGINAL;
        completelyDisableDevilFruitUshi = completelyDisableDevilFruitUshiORIGINAL;
        completelyDisableDevilFruitOpe = completelyDisableDevilFruitOpeORIGINAL;
        completelyDisableDevilFruitHie = completelyDisableDevilFruitHieORIGINAL;
        completelyDisableDevilFruitNikyu = completelyDisableDevilFruitNikyuORIGINAL;
        completelyDisableDevilFruitYomi = completelyDisableDevilFruitYomiORIGINAL;
        completelyDisableDevilFruitGoro = completelyDisableDevilFruitGoroORIGINAL;
        completelyDisableDevilFruitMoku = completelyDisableDevilFruitMokuORIGINAL;
        completelyDisableDevilFruitYami = completelyDisableDevilFruitYamiORIGINAL;

        cooldownSpeed = cooldownSpeedORIGINAL;
        enableSideEffects = enableSideEffectsORIGINAL;
        enableMorganFortress = enableMorganFortressORIGINAL;
        morganFortressSpawnChance = morganFortressSpawnChanceORIGINAL;
    }

}
