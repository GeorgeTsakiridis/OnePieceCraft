package georgetsak.opcraft.common.config;

import net.minecraftforge.common.config.Configuration;

public class ConfigEntry {
    final Configuration configuration;
    String name;
    String comment;
    String category;

    public ConfigEntry(Configuration configuration, String name, String comment, String category) {
        this.configuration = configuration;
        this.name = name;
        this.comment = comment;
        this.category = category;
    }

    public void restore(){

    }

}
