package georgetsak.opcraft.common.network.packets;

import georgetsak.opcraft.client.OPSoundEvent;
import georgetsak.opcraft.common.util.OPUtils;
import georgetsak.opcraft.common.capability.haki.HakiCap;
import georgetsak.opcraft.common.capability.haki.IHakiCap;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;
import java.util.List;

public class EmperorPacket extends AbstractMessage.AbstractServerMessage<EmperorPacket>{

    public EmperorPacket(){}

    @Override
    protected void read(PacketBuffer buffer) throws IOException {

    }

    @Override
    protected void write(PacketBuffer buffer) throws IOException {

    }

    @Override
    public void process(EntityPlayer player, Side side) {
        if(!player.world.isRemote){
            IHakiCap hakiCap = HakiCap.get(player);
            int level = hakiCap.getEmperorLevel();
            if(level > 0){
                emperor(player, level);
            }
        }
    }

    private void emperor(EntityPlayer player, int level) {
        BlockPos center = player.getPosition();
        double range = 10 + level*2;

        player.getServer().getEntityWorld().playSound((EntityPlayer)null, center, OPSoundEvent.emperor_haki, SoundCategory.NEUTRAL, (float)(10 + level*2) + 10f, 1f);

        List<Entity> entities = OPUtils.getNearbyEntitiesExcluding(player, range, player);

        if(!entities.isEmpty()){
            for(Entity entity : entities){
                if(entity instanceof EntityPlayer){
                    entity.attackEntityFrom(DamageSource.causePlayerDamage(player), OPUtils.damageCalculation((EntityPlayer)entity, 8F * level, true));
                }else{
                    entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 4F * level);
                }
            }
        }
    }

}
