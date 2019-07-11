package georgetsak.opcraft.common.network.packets.server;


import georgetsak.opcraft.common.entity.devilfruit.*;
import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.common.entity.other.EntityStormLeg;
import georgetsak.opcraft.common.registry.OPBlocks;
import georgetsak.opcraft.client.OPSoundEvent;
import georgetsak.opcraft.common.util.OPUtils;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.WorldWorkerManager;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;

import javax.vecmath.Point3d;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class OPServerMessage extends AbstractMessage.AbstractServerMessage<OPServerMessage> {

    private String text = null;

    public OPServerMessage() { }

    public OPServerMessage(String text) {
        this.text = text;
    }

    @Override
    protected void read(PacketBuffer buffer) {
        text = ByteBufUtils.readUTF8String(buffer);
    }

    @Override
    protected void write(PacketBuffer buffer) {
        ByteBufUtils.writeUTF8String(buffer, text);
    }

    @Override
    public void process(EntityPlayer ep, Side side) {
        if (side.isServer()) {
            int multiplier = OPCraft.config.cooldownSpeed.getCurrentValue();
            World world = ep.world;

            if (text.equals("GomuPistolA")) {
                EntityGomuPistol elp = new EntityGomuPistol(world, ep.posX, ep.posY + 0.6f, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep, false);
                world.spawnEntity(elp);
                world.playSound((EntityPlayer) null, ep.posX, ep.posY, ep.posZ, OPSoundEvent.gomu_stretch, SoundCategory.NEUTRAL, 10.0F, 1.0F);
            }
            if (text.equals("GomuGear2A")) {
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.gomu_gear2, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                ep.addPotionEffect(new PotionEffect(MobEffects.SPEED, (int) (10F * multiplier), 2));
                ep.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, (int) (10F * multiplier), 1));
                ep.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, (int) (10F * multiplier), 1));
                ep.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, (int) (10F * multiplier), 1));
                for (int i = 0; i < 50; i++) {
                    world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ep.posX, ep.posY + 1, ep.posZ, (Math.random() - 0.5) * 0.2, (Math.random() - 0.5) * 0.5, (Math.random() - 0.5) * 0.2, new int[0]);
                }
            }
            if (text.equals("GomuGear3A")) {
                EntityGomuPistol elp = new EntityGomuPistol(world, ep.posX, ep.posY + 0.5 + 0.6f, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep, true);
                world.spawnEntity(elp);
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.gomu_stretch, SoundCategory.NEUTRAL, 10.0F, 1.0F);
            }
            if (text.equals("GomuGear4A")) {
                ep.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, (int) (20F * multiplier), 2));
                ep.addPotionEffect(new PotionEffect(MobEffects.SPEED, (int) (20F * multiplier), 2));
                ep.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, (int) (20F * multiplier), 2));
            }

            if (text.equals("MeraHiganA")) {
                EntityFirePunch eff = new EntityFirePunch(world, ep.posX, ep.posY + 0.6f, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep, 1);
                world.spawnEntity(eff);
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.fire_fist, SoundCategory.NEUTRAL, 10.0F, 1.0F);

            }
            if (text.equals("MeraShinkaA")) {
                world.spawnEntity(new EntityFirePunch(world, ep.posX, ep.posY + 2, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep, 2));
                world.spawnEntity(new EntityFirePunch(world, ep.posX + 0.5, ep.posY + 0.5 + 2, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep, 2));
                world.spawnEntity(new EntityFirePunch(world, ep.posX, ep.posY + 0.5 + 2, ep.posZ + 1, ep.rotationYaw, ep.rotationPitch, ep, 2));
                world.spawnEntity(new EntityFirePunch(world, ep.posX - 0.5, ep.posY - 0.5 + 2, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep, 2));
                world.spawnEntity(new EntityFirePunch(world, ep.posX, ep.posY - 0.5 + 2, ep.posZ - 1, ep.rotationYaw, ep.rotationPitch, ep, 2));
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.fire_fist, SoundCategory.NEUTRAL, 10.0F, 1.0F);
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.fire_fist, SoundCategory.NEUTRAL, 10.0F, 1.0F);
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.fire_fist, SoundCategory.NEUTRAL, 10.0F, 1.0F);
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.fire_fist, SoundCategory.NEUTRAL, 10.0F, 1.0F);
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.fire_fist, SoundCategory.NEUTRAL, 10.0F, 1.0F);

            }
            if (text.equals("MeraHikenA")) {
                EntityFirePunch eff = new EntityFirePunch(world, ep.posX, ep.posY + 0.6f, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep, 4);
                world.spawnEntity(eff);
            }
            if (text.equals("MeraEnteiA")) {
                EntityEntei ee = new EntityEntei(world, ep.posX, ep.posY + 5, ep.posZ, ep);
                world.spawnEntity(ee);
                world.playSound((EntityPlayer) null, ep.posX, ep.posY + 5, ep.posZ, OPSoundEvent.entei_charge, SoundCategory.NEUTRAL, 15.0F, 1.0F);

            }
            if (text.equals("SlowBeamA")) {
                EntitySlowBeam esb = new EntitySlowBeam(world, ep.posX, ep.posY + 0.6f, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep);

                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.slow_beam, SoundCategory.NEUTRAL, 10.0F, 1.0F);
                world.spawnEntity(esb);

            }
            if (text.equals("SlowBallA")) {
                EntitySlowBeamSpawner esb = new EntitySlowBeamSpawner(world, ep.posX, ep.posY + 0.6f, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep, true, 60, 10);
                List<Entity> entities = OPUtils.getNearbyEntitiesExcluding(ep, 20, ep);
                for (Entity entity : entities) {
                    if (entity instanceof EntityLiving) {
                        ((EntityLiving) (entity)).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 300, 9));
                        ((EntityLiving) (entity)).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 300, 9));
                        ((EntityLiving) (entity)).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 300, 9));
                    }
                    if (entity instanceof EntityPlayer) {
                        ((EntityPlayer) (entity)).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 300, 9));
                        ((EntityPlayer) (entity)).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 300, 9));
                        ((EntityPlayer) (entity)).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 300, 9));
                    }
                }
                world.spawnEntity(esb);
            }

            if (text.equals("SlowMashiA")) {
                EntitySlowBeamSpawner esb = new EntitySlowBeamSpawner(world, ep.posX, ep.posY + 0.6f, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep, false, 120, 5);
                world.spawnEntity(esb);
            }

            if (text.equals("ClearSkatingA")) {
                ep.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, (int) (25F * multiplier), 0));

            }

            if (text.equals("RoomA")) {
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.dome_appear, SoundCategory.NEUTRAL, 40.0F, 1.0F);
                world.setBlockState(new BlockPos((int) ep.getPosition().getX(), (int) ep.getPosition().getY(), (int) ep.getPosition().getZ()), OPBlocks.BlockLawDomeCenter.getDefaultState());
            }

            if (text.equals("Shambles")) {
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.shambles, SoundCategory.NEUTRAL, 40.0F, 1.0F);
                OPShambles(ep);
            }

            if (text.equals("InjectionShot")) {
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.shambles, SoundCategory.NEUTRAL, 40.0F, 1.0F);
                OPInjectionShot(ep);
            }

            if (text.equals("Takt")) {
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.takt, SoundCategory.NEUTRAL, 40.0F, 1.0F);
                OPTakt(ep);
            }

            if (text.equals("IceSaberA")) {
                destroyNearbyCropsAndGrass(ep, 5);
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.ice_saber, SoundCategory.NEUTRAL, 20, 1.0F);
                EntityIceSaber e = new EntityIceSaber(world, ep.posX, ep.posY + 0.6f, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep);
                EntityIceSaber e1 = new EntityIceSaber(world, ep.posX, ep.posY + 2.6f, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep);
                EntityIceSaber e2 = new EntityIceSaber(world, ep.posX + 1, ep.posY + 0.6f, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep);
                EntityIceSaber e3 = new EntityIceSaber(world, ep.posX, ep.posY + 0.6f, ep.posZ + 1, ep.rotationYaw, ep.rotationPitch, ep);
                EntityIceSaber e4 = new EntityIceSaber(world, ep.posX - 1, ep.posY + 0.6f, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep);
                EntityIceSaber e5 = new EntityIceSaber(world, ep.posX, ep.posY + 0.6f, ep.posZ - 1, ep.rotationYaw, ep.rotationPitch, ep);

                world.spawnEntity(e);
                world.spawnEntity(e1);
                world.spawnEntity(e2);
                world.spawnEntity(e3);
                world.spawnEntity(e4);
                world.spawnEntity(e5);

            }

            if (text.equals("IceAgeA")) {

                createIceSeaRoad(ep.getHorizontalFacing(), new BlockPos(ep.posX, ep.posY, ep.posZ), ep);
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.ice_age, SoundCategory.NEUTRAL, 50.0F, 1.0F);
            }

            if (text.equals("IceBlockPhBeakA")) {
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.ice_phoenix, SoundCategory.NEUTRAL, 20.0F, 1.0F);
                EntityIcePhoenix e = new EntityIcePhoenix(world, ep.posX, ep.posY + 1, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep);
                world.spawnEntity(e);
            }

            if (text.equals("PadHoA")) {
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.pad_ho, SoundCategory.NEUTRAL, 15, 1.0F);
                damageNearbyPlayers(ep, 15, 6F, 0.2F);
            }

            if (text.equals("TsuppariPadHoA")) {
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.pad_ho, SoundCategory.NEUTRAL, 30, 1.0F);
                damageNearbyPlayers(ep, 30, 12F, 0.5F);
            }

            if (text.equals("UrsusShockA")) {
                EntityUrsusBubble e = new EntityUrsusBubble(world, ep.posX, ep.posY + 1, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep);
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.ursus_shock, SoundCategory.NEUTRAL, 90, 1.0F);
                world.spawnEntity(e);
            }

            if (text.equals("SangoA")) {
                createLightnings(ep, 150, 80D);
            }

            if (text.equals("DeathpieaA")) {
                createLightnings(ep, 400, 100D);
            }

            if (text.equals("WhiteBlowA")) {
                EntitySmokePunch smokePunch = new EntitySmokePunch(world, ep.posX, ep.posY + 0.6, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep);
                world.spawnEntity(smokePunch);
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.smoke_whoosh, SoundCategory.NEUTRAL, 10.0F, 1.0F);
            }

            if (text.equals("WhiteOutA")) {
                world.setBlockState(ep.getPosition(), OPBlocks.BlockSmokeCloud.getDefaultState());
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.smoke_ambient, SoundCategory.NEUTRAL, 20.0F, 1.0F);
            }

            if (text.equals("BlackHoleA") || text.equals("LiberationA")) {
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.dark, SoundCategory.NEUTRAL, 30f, 1f);
            }

            //CONSEQUENCES
            if (OPCraft.config.enableSideEffects.getCurrentValue()) {
                if (text.equals("GomuGear2B")) {
                    ep.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (5F * multiplier), 1));
                    ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (5F * multiplier), 1));
                    world.playSound((EntityPlayer) null, ep.getPosition(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.NEUTRAL, 3.0F, 1.0F);
                }
                if (text.equals("GomuGear3B")) {
                    ep.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (10F * multiplier), 1));
                    ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (10F * multiplier), 2));
                }
                if (text.equals("GomuGear4B")) {
                    ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (17F * multiplier), 2));
                    ep.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (17F * multiplier), 1));
                    ep.addPotionEffect(new PotionEffect(MobEffects.HUNGER, (int) (17F * multiplier), 1));
                    world.playSound((EntityPlayer) null, ep.getPosition(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.NEUTRAL, 3.0F, 1.0F);
                }

                if (text.equals("MeraShinkaB")) {
                    ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (8F * multiplier), 0));
                    ep.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (8F * multiplier), 0));
                }
                if (text.equals("MeraHikenB")) {
                    ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (8F * multiplier), 1));
                    ep.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (8F * multiplier), 1));

                }
                if (text.equals("MeraEnteiB")) {
                    ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (19F * multiplier), 2));
                    ep.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (19F * multiplier), 2));
                    ep.addPotionEffect(new PotionEffect(MobEffects.HUNGER, (int) (19F * multiplier), 0));

                }
                if (text.equals("SlowBallB")) {
                    ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (15F * multiplier), 0));
                }
                if (text.equals("SlowMashiB")) {
                    ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (15F * multiplier), 0));

                }
                if (text.equals("IceBallB")) {
                    ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (10F * multiplier), 0));
                    ep.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (10F * multiplier), 0));

                }
                if (text.equals("IceBlockPhBeakB")) {
                    ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (10F * multiplier), 0));
                    ep.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (10F * multiplier), 0));
                    ep.addPotionEffect(new PotionEffect(MobEffects.HUNGER, (int) (10F * multiplier), 1));
                }
                if (text.equals("TsuppariPadHoB")) {
                    ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (5F * multiplier), 1));
                    ep.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (5F * multiplier), 0));
                }
                if (text.equals("UrsusShockB")) {
                    ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (20F * multiplier), 2));
                    ep.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (20F * multiplier), 2));
                    ep.addPotionEffect(new PotionEffect(MobEffects.HUNGER, (int) (20F * multiplier), 2));
                }
                if (text.equals("SangoB")) {
                    ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (4F * multiplier), 1));
                    ep.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (4F * multiplier), 1));
                }
                if (text.equals("DeathpieaB")) {
                    ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (15F * multiplier), 2));
                    ep.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (15F * multiplier), 2));
                    ep.addPotionEffect(new PotionEffect(MobEffects.HUNGER, (int) (15F * multiplier), 2));
                }

                if (text.equals("WhiteSnakeB")) {
                    ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (6F * multiplier), 1));
                    ep.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (6F * multiplier), 1));
                }

                if (text.equals("WhiteOutB")) {
                    ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (15F * multiplier), 1));
                    ep.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (15F * multiplier), 1));
                    ep.addPotionEffect(new PotionEffect(MobEffects.HUNGER, (int) (15F * multiplier), 1));
                }

                if (text.equals("WhiteLauncherB")) {
                    ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (17F * multiplier), 2));
                    ep.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (17F * multiplier), 1));
                    ep.addPotionEffect(new PotionEffect(MobEffects.HUNGER, (int) (17F * multiplier), 1));
                    //world.playSound((EntityPlayer) null, ep.getPosition(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.NEUTRAL, 3.0F, 1.0F);
                }

                if (text.equals("KurouzuB")) {
                    ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (7F * multiplier), 1));
                    ep.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (7F * multiplier), 0));
                }

                if (text.equals("LiberationB")) {
                    ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (18F * multiplier), 2));
                    ep.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (18F * multiplier), 1));
                    ep.addPotionEffect(new PotionEffect(MobEffects.HUNGER, (int) (18F * multiplier), 1));
                }

            }
            if (text.equals("DISABLEDAMAGE")) {
                ep.setEntityInvulnerable(true);
                if (!OPCraft.IS_RELEASE_VERSION) {
                    ep.sendMessage(new TextComponentString("DISABLED DAMAGE"));
                }
            }
            if (text.equals("ENABLEDAMAGE")) {
                ep.setEntityInvulnerable(false);
                if (!OPCraft.IS_RELEASE_VERSION) {
                    ep.sendMessage(new TextComponentString("ENABLED DAMAGE"));
                }
            }

            if (text.equals("KairosekiItem")) {
                ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 3));
                ep.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 40, 1));
            }

            if (text.equals("StormLeg")) {
                EntityStormLeg entityStormLeg = new EntityStormLeg(world, ep.posX, ep.posY, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep);
                world.spawnEntity(entityStormLeg);
                world.playSound((EntityPlayer) null, ep.getPosition(), OPSoundEvent.shambles, SoundCategory.PLAYERS, 1f, 1f);
            }

            if (text.equals("KingGun")) {
                world.playSound(null, ep.getPosition(), OPSoundEvent.air_blast, SoundCategory.NEUTRAL, 50f, 1f);
                //50 blocks range
                Vec3d vec3d = ep.getPositionEyes(0);
                Vec3d vec3d1 = ep.getLook(0);
                Vec3d vec3d2 = vec3d.addVector(vec3d1.x * 50, vec3d1.y * 50, vec3d1.z * 50);
                int l = MathHelper.floor(vec3d2.x);
                int i1 = MathHelper.floor(vec3d2.y);
                int j1 = MathHelper.floor(vec3d2.z);
                BlockPos blockpos = new BlockPos(l, i1, j1);

                ArrayList<Point3d> points = OPUtils.getIntermediatePoints(ep.getPosition().up(), blockpos, 100);
                Random r = new Random();
                for (Point3d point : points) {
                    List<Entity> players = OPUtils.getNearbyEntities(ep, new BlockPos(point.x, point.y, point.z), 2d, EntityPlayer.class);
                    List<Entity> living = OPUtils.getNearbyEntities(ep, new BlockPos(point.x, point.y, point.z), 2d, EntityLiving.class);
                    for (Entity player : players) {
                        if (player != ep) {
                            player.attackEntityFrom(DamageSource.causePlayerDamage(ep), OPUtils.damageCalculation(ep, 32, false));
                        }
                    }
                    for (Entity entity : living) {
                        entity.attackEntityFrom(DamageSource.causePlayerDamage(ep), 32);
                    }
                    for (int i = 0; i < 5; i++) {
                        double offsetX = r.nextDouble()/2d-0.5d;
                        double offsetY = r.nextDouble()/2d-0.5d;
                        double offsetZ = r.nextDouble()/2d-0.5d;
                        world.spawnParticle(EnumParticleTypes.CLOUD, point.x + offsetX, point.y + offsetY, point.z + offsetZ, 0, 0.05d, 0);
                    }

                }
            }


            final UUID attackUUID = UUID.fromString("9c19efd8-ba2e-424a-b5a8-09b088914185");
            final UUID speedUUID = UUID.fromString("3f0f7b35-e15d-4fee-ad48-db943a74170d");

            IAttributeInstance attackSpeedAttribute = ep.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED);
            IAttributeInstance moveSpeedAttribute = ep.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);

            AttributeModifier attackSpeedMod = new AttributeModifier(attackUUID, "OPShaveAttackSpeedMod", attackSpeedAttribute.getBaseValue() * 4, 2);
            AttributeModifier moveSpeedMod = new AttributeModifier(speedUUID, "OPMoveShaveSpeedMod", moveSpeedAttribute.getBaseValue() * 4, 2);

            if (text.equals("SixPowersShaveEnable")) {
                attackSpeedAttribute.applyModifier(attackSpeedMod);
                moveSpeedAttribute.applyModifier(moveSpeedMod);
            }

            if (text.equals("SixPowersShaveDisable")) {
                if (attackSpeedAttribute.hasModifier(attackSpeedMod)) {
                    attackSpeedAttribute.removeModifier(attackSpeedMod);
                }
                if (moveSpeedAttribute.hasModifier(moveSpeedMod)) {
                    moveSpeedAttribute.removeModifier(moveSpeedMod);
                }
            }

        }
    }


    static void createLightnings(EntityPlayer ep, int repeatTimes, double radius){

        double x = ep.posX;
        double z = ep.posZ;

        Random r = new Random();
        World world = ep.getServer().getEntityWorld();
        List<Entity> entities = OPUtils.getNearbyEntitiesExcluding(ep, radius, ep);

        for(int i = 0; i < repeatTimes; i++){
            //Has 1/20 to hit an entity or player per loop. If 100 loops then the chance of hitting an entity is (1/20)*100 = 5 times.
            if(r.nextInt(20) == 1){
                if(r.nextBoolean()) {
                    int num = r.nextInt(entities.size());
                    if (entities.get(num) instanceof EntityLiving) {
                        EntityLiving entity = (EntityLiving)entities.get(num);
                        world.addWeatherEffect(new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ, false));
                    }
                }
                continue;
            }

            double finalX = x + (r.nextDouble() * (radius * 2D) - radius);
            double finalZ = z + (r.nextDouble() * (radius * 2D) - radius);
            double finalY = world.getTopSolidOrLiquidBlock(new BlockPos(finalX, 255, finalZ)).getY();

             world.addWeatherEffect(new EntityLightningBolt(world, finalX, finalY, finalZ, false));
        }
    }

    static void damageNearbyPlayers(EntityPlayer ep, int range, float damage, float velMul){
        List<Entity> entities = OPUtils.getNearbyEntitiesExcluding(ep, range, ep);

        for(int i = 0; i < entities.size(); i++) {
            if (entities.get(i) != null) {
                if (entities.get(i) instanceof EntityPlayer) {
                    EntityPlayer entityPlayer = (EntityPlayer) entities.get(i);
                    entityPlayer.attackEntityFrom(DamageSource.causePlayerDamage(ep), OPUtils.damageCalculation(entityPlayer, damage, true));

                }

                else if(entities.get(i) instanceof EntityLiving){
                    Entity e = entities.get(i);
                    e.attackEntityFrom(DamageSource.causePlayerDamage(ep), damage);
                }

                if(entities.get(i) instanceof EntityLiving || entities.get(i) instanceof EntityPlayer) {

                    double distanceX = entities.get(i).posX - ep.posX;
                    double distanceY = entities.get(i).posY - ep.posY;
                    double distanceZ = entities.get(i).posZ - ep.posZ;

                    double velocityX = (range / distanceX) * velMul;
                    double velocityY = (range / distanceY) * velMul;
                    double velocityZ = (range / distanceZ) * velMul;

                    if (distanceX == 0D) {
                        velocityX = 0;
                    }
                    if (distanceY == 0D) {
                        velocityY = 0;
                    }
                    if (distanceZ == 0D) {
                        velocityZ = 0;
                    }
                    if (velocityX > range) {
                        velocityX = range;
                    }
                    if (velocityX < -range) {
                        velocityX = -range;
                    }
                    if (velocityY > range) {
                        velocityY = range;
                    }
                    if (velocityY < -range) {
                        velocityY = -range;
                    }
                    if (velocityZ > range) {
                        velocityZ = range;
                    }
                    if (velocityZ < -range) {
                        velocityZ = -range;
                    }

                    entities.get(i).addVelocity(velocityX, velocityY, velocityZ);

                }
            }
        }
     }

    static void createIceSeaRoad(EnumFacing ef, BlockPos startPoint, EntityPlayer ep){

        Block roadMat = OPBlocks.BlockIceAge;
        World world = ep.world;

        switch(ef){
            case EAST:
                for(int i = 0; i < 200; i++){
                    for(int h = -2; h<2; h++) {
                        if (isBlockWater(ep, startPoint.add(i, h, 0))) {
                            world.setBlockState(startPoint.add(i, h, 0), roadMat.getDefaultState());
                        }
                        if(isBlockWater(ep, startPoint.add(i, h, 1))) {
                            world.setBlockState(startPoint.add(i, h, 1), roadMat.getDefaultState());
                        }
                        if (isBlockWater(ep, startPoint.add(i, h, -1))) {
                            world.setBlockState(startPoint.add(i, h, -1), roadMat.getDefaultState());
                        }
                    }
                }
                break;
            case WEST:
                for(int i = 0; i < 200; i++){
                    for(int h = -2; h<2; h++) {
                        if (isBlockWater(ep, startPoint.add(-i, h, 0))) {
                            world.setBlockState(startPoint.add(-i, h, 0), roadMat.getDefaultState());
                        }
                        if (isBlockWater(ep, startPoint.add(-i, h, 1))) {
                            world.setBlockState(startPoint.add(-i, h, 1), roadMat.getDefaultState());
                        }
                        if (isBlockWater(ep, startPoint.add(-i, h, -1))) {
                            world.setBlockState(startPoint.add(-i, h, -1), roadMat.getDefaultState());
                        }
                    }
                }
                break;

            case SOUTH:
                for(int i = 0; i < 200; i++){
                    for(int h = -2; h<2; h++) {
                        if (isBlockWater(ep, startPoint.add(0, h, i))) {
                            world.setBlockState(startPoint.add(0, h, i), roadMat.getDefaultState());
                        }
                        if (isBlockWater(ep, startPoint.add(1, h, i))) {
                            world.setBlockState(startPoint.add(1, h, i), roadMat.getDefaultState());
                        }
                        if (isBlockWater(ep, startPoint.add(-1, h, i))) {
                            world.setBlockState(startPoint.add(-1, h, i), roadMat.getDefaultState());
                        }
                    }
                }
                break;

            case NORTH:
                for(int i = 0; i < 200; i++){
                    for(int h = -2; h<2; h++) {
                        if (isBlockWater(ep, startPoint.add(0, h, -i))) {
                            world.setBlockState(startPoint.add(0, h, -i), roadMat.getDefaultState());
                        }
                        if (isBlockWater(ep, startPoint.add(1, h, -i))) {
                            world.setBlockState(startPoint.add(1, h, -i), roadMat.getDefaultState());
                        }
                        if (isBlockWater(ep, startPoint.add(-1, h, -i))) {
                            world.setBlockState(startPoint.add(-1, h, -i), roadMat.getDefaultState());
                        }
                    }
                }
                break;
        }

    }

    private static boolean isBlockWater(EntityPlayer ep, BlockPos blockPos) {
        World world = ep.getServer().getEntityWorld();
        if(world.getBlockState(blockPos).getBlock() == Blocks.WATER || world.getBlockState(blockPos).getBlock() == Blocks.FLOWING_WATER) return true;
        return false;
    }

    static void destroyNearbyCropsAndGrass(EntityPlayer ep, int radius){
        BlockPos playerPos = new BlockPos(ep.posX, ep.posY, ep.posZ);
        World world = ep.getServer().getEntityWorld();

        for(int x = -radius; x < radius; x++){
            for(int y = -radius; y < radius; y++){
                for(int z = -radius; z < radius; z++){
                    BlockPos pos = playerPos.add(x, y, z);
                    Block blockFound = world.getBlockState(pos).getBlock();
                    if(blockFound == Blocks.DEADBUSH || blockFound == Blocks.DOUBLE_PLANT || blockFound == Blocks.YELLOW_FLOWER || blockFound == Blocks.RED_FLOWER
                            || blockFound == Blocks.SAPLING || blockFound == Blocks.WHEAT || blockFound == Blocks.CARROTS || blockFound == Blocks.POTATOES
                            || blockFound == Blocks.BEETROOTS || blockFound == Blocks.BROWN_MUSHROOM || blockFound == Blocks.RED_MUSHROOM || blockFound == Blocks.VINE
                            || blockFound == Blocks.WATERLILY){
                            world.destroyBlock(pos, false);
                    }
                }
            }
        }

     }

    static BlockPos findCenterOfDome(EntityPlayer ep) {
         BlockPos DomeCenter = new BlockPos(0, 0, 0);
         boolean foundCenter = false;

         BlockPos center = new BlockPos(ep.posX, ep.posY, ep.posZ);
         int x = center.getX();
         int y = center.getY();
         int z = center.getZ();

         int radius = 40;

         for (int i = x - radius; i < x + radius; i++) {
             for (int j = y - radius; j < y + radius; j++) {
                 for (int k = z - radius; k < z + radius; k++) {
                     if (ep.getServer().getEntityWorld().getBlockState(new BlockPos(i, j, k)).getBlock() == OPBlocks.BlockLawDomeCenter) {
                         foundCenter = true;
                         DomeCenter = new BlockPos(i, j, k);
                         break;
                     }
                 }
             }
         }
         if(foundCenter) return DomeCenter;
         else{
             return null;
         }

     }

    static void OPTakt(EntityPlayer ep){
		 BlockPos DomeCenter = findCenterOfDome(ep);
		 if(DomeCenter != null) {
			 Random r = new Random();
			 int radius = 6;
			 int x = DomeCenter.getX();
			 int y = DomeCenter.getY();
			 int z = DomeCenter.getZ();

             World world = ep.getServer().getEntityWorld();

			 for (int i = x - radius; i < x + radius; i++) {
				 for (int j = y - 4; j < y + 4; j++) {
                     for (int k = z - radius; k < z + radius; k++) {
                         Block blockFound = world.getBlockState(new BlockPos(i, j, k)).getBlock();
                         boolean allowed = true;
                         for (Block block : OPUtils.nonMovableBlocks) {
                             if (block == blockFound) allowed = false;
                         }
                         if (allowed) {
                             if (canBlockSeeSky(new BlockPos(i, j, k), ep)) {


                                 world.setBlockToAir(new BlockPos(i, j, k));
                                 EntityFallingBlock entityFallingBlock = new EntityFallingBlock(world, i + (double) r.nextInt(radius * 5) - (double) r.nextInt(radius * 5), y + (double) r.nextInt(30), k + (double) r.nextInt(radius * 5) - (double) r.nextInt(radius * 5), blockFound.getBlockState().getBaseState());
                                 entityFallingBlock.fallTime = 2;
                                 // entityFallingBlock.addVelocity((r.nextInt(100) - 50) / 100, (r.nextInt(100) - 50) / 100, (r.nextInt(100) - 50) / 100);

                                 world.spawnEntity(entityFallingBlock);

                             }
                         }
                     }
                 }
			 }

		 }
	 }

    static boolean canBlockSeeSky(BlockPos blockPos, EntityPlayer ep) {
         int y = blockPos.getY();
         int x = blockPos.getX();
         int z = blockPos.getZ();
         World world = ep.getServer().getEntityWorld();

         for(int i = 256; i > y; i--){
             if(world.getBlockState(new BlockPos(x, i, z)) != Blocks.AIR.getDefaultState() && world.getBlockState(new BlockPos(x, i, z)) != Blocks.FIRE.getDefaultState() && world.getBlockState(new BlockPos(x, i, z)) != OPBlocks.BlockLawDome.getDefaultState() && world.getBlockState(new BlockPos(x, i, z)) != OPBlocks.BlockLawDomeCenter.getDefaultState()){
                 return false;
             }
         }
         return true;
    }

    static void OPShambles(EntityPlayer ep){
        BlockPos DomeCenter = findCenterOfDome(ep);

		 if(DomeCenter != null){
             double distance = 19.0D;
             Random r = new Random();
			 List<Entity> entities = OPUtils.getNearbyEntitiesExcluding(ep, distance, ep);

			 for(Entity entity : entities){
                    int tpX = r.nextInt(37);
                    int tpZ = r.nextInt(37);
			        entity.setPosition(DomeCenter.getX() - distance + tpX, entity.getPosition().getY(), DomeCenter.getZ() - distance + tpZ);
			 }
		 }
	 }

    static void OPInjectionShot(EntityPlayer ep){

         BlockPos DomeCenter = findCenterOfDome(ep);
         if(DomeCenter != null){

             double distance = 19.0D;

             List<Entity> players = OPUtils.getNearbyEntities(ep, distance, EntityPlayer.class);

             if (!players.isEmpty()) {
                 for(Entity entity : players){
                     EntityPlayer target = (EntityPlayer) entity;
                     if(target != ep){
                         ep.attemptTeleport(target.posX, target.posY, target.posZ);
                         target.attackEntityFrom(DamageSource.causePlayerDamage(ep), OPUtils.damageCalculation(target, 12F, true));
                         break;
                     }
                 }
                 }
             }
         }

    }