package georgetsak.opcraft.common.crew;

import georgetsak.opcraft.OPCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import java.io.*;
import java.util.ArrayList;

public class CrewSaveData extends WorldSavedData {

    private static final String DATA_NAME = OPCraft.MODID + "CrewData";

    private ArrayList<Crew> crews = new ArrayList<>();

    public CrewSaveData(){
        super(DATA_NAME);
    }

    public CrewSaveData(String name) {
        super(name);
    }

    public ArrayList<Crew> getCrews(){
        return crews;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        try {
            Object object = toObject(nbt.getByteArray("data"));
            if(object instanceof ArrayList){
                crews = (ArrayList<Crew>)object;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        try {
            compound.setByteArray("data", toByteArray(crews));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return compound;
    }

    public static CrewSaveData get(World world) {
        MapStorage storage = world.getMapStorage();
        CrewSaveData instance = (CrewSaveData) storage.getOrLoadData(CrewSaveData.class, DATA_NAME);

        if (instance == null) {
            instance = new CrewSaveData();
            storage.setData(DATA_NAME, instance);
        }
        return instance;
    }

    public static byte[] toByteArray(Object obj) throws IOException {
        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
        } finally {
            if (oos != null) {
                oos.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
        return bytes;
    }

    public static Object toObject(byte[] bytes) throws IOException, ClassNotFoundException {
        Object obj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (ois != null) {
                ois.close();
            }
        }
        return obj;
    }


}
