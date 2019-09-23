package georgetsak.opcraft.common.entity.devilfruit;

import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.capability.devilfruitlevels.DevilFruitLevelsCap;
import georgetsak.opcraft.common.util.MathUtils;
import georgetsak.opcraft.common.util.OPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;

public class EntityIcePhoenix extends EntityFlying {

    double x, y, z, startY;
    float yaw, pitch;
    Vec3d direction = new Vec3d(0,0,0);
    EntityPlayer ep;

    public EntityIcePhoenix(World world){
        super(world);
        this.setSize(1.5F, 1.5F);
    }

    public EntityIcePhoenix(World worldIn, double x, double y, double z, float yaw, float pitch, EntityPlayer owner) {
        this(worldIn);
        this.ep = owner;
        this.x = x;
        this.y = this.startY = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.direction = MathUtils.convertRotation(yaw, pitch);
        direction.scale(0.8F);
        this.setPositionAndRotation(x, y, z, yaw, pitch);
        this.motionX = direction.x;
        this.motionY = direction.y;
        this.motionZ = direction.z;
        this.setSize(1.5F, 1.5F);
    }

    public void onLivingUpdate(){
        super.onLivingUpdate();

        spawnParticles(rand);

        this.motionX = direction.x;
        this.motionY = direction.y;
        this.motionZ = direction.z;
    }

    public void onUpdate()
    {
        super.onUpdate();
        if(this.ticksExisted >= 240)
        {
            this.setDead();
        }
        if(!OPCraft.config.disableGriefing.getValue()) {
            for (int x = -4; x <= 4; x++) {
                for (int y = 4; y >= -4; y--) {
                    for (int z = -4; z <= 4; z++) {
                        BlockPos pos = new BlockPos(this.posX, this.posY, this.posZ);
                        if (world.getBlockState(pos.add(x, y + 1, z)).getBlock() == Blocks.AIR && world.getBlockState(pos.add(x, y, z)).getBlock() != Blocks.AIR && world.getBlockState(pos.add(x, y, z)).getBlock() != Blocks.SNOW_LAYER) {
                            world.setBlockState(pos.add(x, y + 1, z), Blocks.SNOW_LAYER.getDefaultState());
                        }
                        if (world.getBlockState(pos.add(x, y, z)).getBlock() == Blocks.WATER) {
                            world.setBlockState(pos.add(x, y, z), Blocks.ICE.getDefaultState());
                        }
                    }
                }
            }
        }

    }

    public void onCollideWithPlayer(EntityPlayer entityIn)
    {
        if(ep != entityIn && ep != null && entityIn.hurtResistantTime == 0){
            entityIn.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40 + getLevel()*20, 2));
            entityIn.attackEntityFrom(OPUtils.causePlayerCustomDamage(ep,true), MathUtils.calculateDamage(entityIn, 10f + getLevel()*2f, true));
            entityIn.hurtResistantTime = 20;
        }
    }

    public void collideWithEntity(Entity entityIn){
        if(entityIn instanceof EntityLiving){
            EntityLiving e = (EntityLiving)entityIn;
            e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40 + getLevel()*20, 2));
            entityIn.attackEntityFrom(OPUtils.causePlayerCustomDamage(ep,true), 10f + getLevel()*2f);
            entityIn.hurtResistantTime = 20;
        }
    }

    int getLevel(){

        if(ep != null){
            return DevilFruitLevelsCap.get(ep).getPowerLevel(4);
        }

        return 0;
    }

    public void applyEntityCollision(Entity entityIn)
    {

    }

    //@#@#@#@#@#@#@#@#@#@#@##@#@#@#@

    private void spawnParticles(Random r) {
        for(int i = 0; i < 10; i++){
            double offsetX = ((double)r.nextInt(20)+ 1 -10) / 20;
            double offsetY = ((double)r.nextInt(20)+ 1 -10) / 20 + 0.5;
            double offsetZ = ((double)r.nextInt(20)+ 1 -10) / 20;

            this.world.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0, 0, 0);
        }
    }

    public float getCollisionBorderSize()
    {
        return 2.0F;
    }

    public boolean isEntityInvulnerable(DamageSource source){
        return true;
    }

    public boolean canBePushed(){
        return false;
    }

    public boolean canBeCollidedWith()
    {
        return true;
    }


}
