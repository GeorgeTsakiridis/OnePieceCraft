package georgetsak.opcraft.common.config;

import net.minecraftforge.common.config.Configuration;

public class ConfigEntryBoolean extends ConfigEntry{

    private boolean currentValue;
    private boolean originalValue;

    public ConfigEntryBoolean(Configuration configuration, String name, String comment, String category, boolean defaultValue) {
        super(configuration, name, comment, category);
        originalValue = currentValue = configuration.getBoolean(name, category, defaultValue, comment);
    }

    public void setCurrentValue(boolean currentValue) {
        this.currentValue = currentValue;
    }

    public boolean getCurrentValue(){
        return currentValue;
    }

    public void restore(){
        currentValue = originalValue;
    }

}
