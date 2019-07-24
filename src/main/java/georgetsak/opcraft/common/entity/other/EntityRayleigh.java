package georgetsak.opcraft.common.entity.other;

import georgetsak.opcraft.common.capability.bounty.BountyCap;
import georgetsak.opcraft.common.capability.bounty.IBountyCap;
import georgetsak.opcraft.common.capability.haki.HakiCap;
import georgetsak.opcraft.common.capability.haki.IHakiCap;
import georgetsak.opcraft.common.network.packets.common.HakiPacket;
import georgetsak.opcraft.common.network.packetsdispacher.PacketDispatcher;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class EntityRayleigh extends EntityMob {

    int ticksCooldown = 0;

    public EntityRayleigh(World worldIn) {
        super(worldIn);
        setSize(0.7f, 2f);
    }

    public EntityRayleigh(World worldIn, BlockPos position){
        this(worldIn);
        this.setPosition(position.getX(), position.getY(), position.getZ());
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1D, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 0.6D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.tasks.addTask(4, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(5, new EntityAIWander(this, 0.6D));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(40D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(30.0D);
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

        if (flag) {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand) {

        if(ticksCooldown == 0) {
            ticksCooldown = 60;
            if (!world.isRemote) {

                IBountyCap bountyCap = BountyCap.get(player);
                IHakiCap hakiCap = HakiCap.get(player);

                int bounty = bountyCap.getBounty();
                System.out.println(bounty);
                if(hakiCap.isEmperorHakiUnlocked()){
                    player.sendStatusMessage((new TextComponentString(TextFormatting.GREEN + "You have already learned Emperor Haki!")), true);
                    return false;
                }
                if (bounty >= 50000) {
                    hakiCap.setUnlockedEmperorHaki(true);
                    PacketDispatcher.sendTo(new HakiPacket(hakiCap), (EntityPlayerMP)player);
                    player.sendStatusMessage((new TextComponentString(TextFormatting.GREEN + "You learned the Conqueror Haki!")), true);
                }else {
                    player.sendStatusMessage((new TextComponentString(TextFormatting.RED + "You are not worthy to learn this Haki yet!")), true);
                    player.sendMessage(new TextComponentString("Come back when you have at least 50000B bounty!"));
                }
            }
        }
        return false;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if(ticksCooldown > 0){
            ticksCooldown--;
        }
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public boolean canBeLeashedTo(EntityPlayer player) {
        return false;
    }
}
