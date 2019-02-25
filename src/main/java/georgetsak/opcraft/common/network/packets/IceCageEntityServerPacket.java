package georgetsak.opcraft.common.network.packets;

import georgetsak.opcraft.client.OPSoundEvent;
import georgetsak.opcraft.common.generator.terrain.WorldGenIceBall;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

/**
 * Created by GeorgeTsak on 7/29/2017.
 */
public class IceCageEntityServerPacket extends AbstractMessage.AbstractServerMessage<IceCageEntityServerPacket>{

    int targetID;

    public IceCageEntityServerPacket(){}
    public IceCageEntityServerPacket(Entity target){
        this.targetID = target.getEntityId();
    }

    @Override
    protected void read(PacketBuffer buffer){
        this.targetID = buffer.readInt();
    }

    @Override
    protected void write(PacketBuffer buffer){
        buffer.writeInt(targetID);
    }

    @Override
    public void process(EntityPlayer user, Side side) {

        Entity target = user.world.getEntityByID(targetID);

        if(side.isServer()){
            if (target != null) {
                user.world.playSound((EntityPlayer) null, user.getPosition(), OPSoundEvent.ice_ball, SoundCategory.NEUTRAL, 20, 1.0F);
                target.world.playSound((EntityPlayer) null, target.getPosition(), OPSoundEvent.ice_ball, SoundCategory.NEUTRAL, 20, 1.0F);
                if(target instanceof EntityPlayer) {
                    EntityPlayer entityPlayer = (EntityPlayer)target;
                    entityPlayer.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 3));
                }
                int posX = (int)target.posX;
                int posY = (int)target.posY;
                int posZ = (int)target.posZ;

                WorldGenIceBall gib = new WorldGenIceBall();
                gib.generate(user.world, new BlockPos(posX, posY, posZ));
                target.setPosition(posX + 0.5, posY, posZ + 0.5);
            }

        }
    }
}
