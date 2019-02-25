package georgetsak.opcraft.common.network.packets;

import georgetsak.opcraft.common.item.weapons.IExtendedReach;
import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import georgetsak.opcraft.common.registry.OPItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

public class RayTracePacket extends AbstractMessage<RayTracePacket> {

    private int entityId;

    public RayTracePacket() {
    }

    public RayTracePacket(int parEntityId) {
        entityId = parEntityId;
    }

    @Override
    protected void read(PacketBuffer buffer) {
        entityId = ByteBufUtils.readVarInt(buffer, 4);
    }

    @Override
    protected void write(PacketBuffer buffer) {
        ByteBufUtils.writeVarInt(buffer, entityId, 4);
    }

    @Override
    public void process(EntityPlayer thePlayer, Side side) {

        if(side.isServer()){
            Entity theEntity = thePlayer.world.getEntityByID(entityId);

            if (thePlayer.getHeldItemMainhand() == null) {
                return;
            }
            if (thePlayer.getHeldItemMainhand().getItem() instanceof IExtendedReach) {
                IExtendedReach theExtendedReachWeapon = (IExtendedReach) thePlayer.getHeldItemMainhand().getItem();
                double distanceSq = thePlayer.getDistanceSq(theEntity);
                double reachSq = theExtendedReachWeapon.getReach() * theExtendedReachWeapon.getReach();

                if (reachSq >= distanceSq) {
                    thePlayer.attackTargetEntityWithCurrentItem(theEntity);
                    theEntity.attackEntityFrom(DamageSource.causePlayerDamage(thePlayer), theExtendedReachWeapon.getDamage());
                }

                if(theExtendedReachWeapon.getItem() == OPItems.ItemBazooka){
                    thePlayer.world.createExplosion(thePlayer, theEntity.posX, theEntity.posY, theEntity.posZ, 8, true);
                }

            }
        }

    }


    public static class Handler implements IMessageHandler<RayTracePacket, IMessage> {

        public IMessage onMessage(RayTracePacket message, MessageContext ctx) {
            IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world; // or Minecraft.getMinecraft() on the client

            final EntityPlayerMP thePlayer = (EntityPlayerMP) OPCraft.proxy.getPlayerEntityFromContext(ctx);


            mainThread.addScheduledTask(new Runnable() {
                public void run() {


                }
            });
            return null;
        }
    }
    }