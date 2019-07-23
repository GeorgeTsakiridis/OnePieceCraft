package georgetsak.opcraft.common.util;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;

import java.io.IOException;

public class OPDataSerializers {
    public static final DataSerializer<Vector3Double> VECTOR3DOUBLE = new DataSerializer<Vector3Double>()
    {
        public void write(PacketBuffer buf, Vector3Double value)
        {
            buf.writeDouble(value.getX());
            buf.writeDouble(value.getY());
            buf.writeDouble(value.getZ());
        }
        public Vector3Double read(PacketBuffer buf) throws IOException
        {
            return new Vector3Double(buf.readDouble(), buf.readDouble(), buf.readDouble());
        }
        public DataParameter<Vector3Double> createKey(int id)
        {
            return new DataParameter<Vector3Double>(id, this);
        }
        public Vector3Double copyValue(Vector3Double value)
        {
            return value;
        }
    };

}
