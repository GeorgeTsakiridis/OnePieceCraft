package georgetsak.opcraft.common.item;

import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GeorgeTsak on 7/13/2017.
 */

public class ItemOPRecord extends ItemRecord{

    private static final Map records = new HashMap();


    public ItemOPRecord(String name, SoundEvent soundIn) {
        super(name, soundIn);
        records.put(soundIn, this);
    }



}
