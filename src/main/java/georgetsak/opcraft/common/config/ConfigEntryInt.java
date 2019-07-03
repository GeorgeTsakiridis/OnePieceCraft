package georgetsak.opcraft.common.config;

import net.minecraftforge.common.config.Configuration;

public class ConfigEntryInt extends ConfigEntry{

    private int currentValue = 0;
    private int originalValue = 0;

    public ConfigEntryInt(Configuration configuration, String name, String comment, String category, int defaultValue, int minValue, int maxValue) {
        super(configuration, name, comment, category);
        originalValue = currentValue = configuration.getInt(name,category,defaultValue,minValue, maxValue,comment);
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void restore(){
        currentValue = originalValue;
    }

}
