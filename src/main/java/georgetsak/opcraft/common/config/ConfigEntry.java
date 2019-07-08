package georgetsak.opcraft.common.config;

import net.minecraftforge.common.config.Configuration;

public class ConfigEntry {
    final Configuration configuration;
    final int ID;

    String name;
    String comment;
    String category;

    public ConfigEntry(Configuration configuration, String name, String comment, String category, int ID) {
        this.configuration = configuration;
        this.name = name;
        this.comment = comment;
        this.category = category;
        this.ID = ID;
    }

    public void restore(){

    }

    public int getID(){
        return ID;
    }

}
