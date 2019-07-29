package georgetsak.opcraft.common.network.packets.server;


import georgetsak.opcraft.OPCraft;
import georgetsak.opcraft.client.OPSoundEvent;
import georgetsak.opcraft.common.entity.devilfruit.*;
import georgetsak.opcraft.common.entity.other.EntityStormLeg;
import georgetsak.opcraft.common.network.packetsdispacher.AbstractMessage;
import georgetsak.opcraft.common.registry.OPBlocks;
import georgetsak.opcraft.common.util.MathUtils;
import georgetsak.opcraft.common.util.OPUtils;
import georgetsak.opcraft.common.util.PowerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;

import javax.vecmath.Point3d;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class OPServerMessage extends AbstractMessage.AbstractServerMessage<OPServerMessage> {

    private String text = null;

    public OPServerMessage() {
    }

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
            World world = ep.world;

            switch (text) {
                case "GomuPistolA": {
                    EntityGomuPistol elp = new EntityGomuPistol(world, ep.posX, ep.posY + 0.6f, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep, false);
                    world.spawnEntity(elp);
                    world.playSound(null, ep.posX, ep.posY, ep.posZ, OPSoundEvent.gomu_stretch, SoundCategory.NEUTRAL, 10.0F, 1.0F);
                    break;
                }
                case "GomuGear2A": {
                    world.playSound(null, ep.getPosition(), OPSoundEvent.gomu_gear2, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    ep.addPotionEffect(new PotionEffect(MobEffects.SPEED, 160, 2));
                    ep.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 160, 1));
                    ep.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 160, 1));
                    ep.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 160, 1));
                    for (int i = 0; i < 50; i++) {
                        world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ep.posX, ep.posY + 1, ep.posZ, (Math.random() - 0.5) * 0.2, (Math.random() - 0.5) * 0.5, (Math.random() - 0.5) * 0.2);
                    }
                    break;
                }
                case "GomuGear3A": {
                    EntityGomuPistol elp = new EntityGomuPistol(world, ep.posX, ep.posY + 1.1, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep, true);
                    world.spawnEntity(elp);
                    world.playSound(null, ep.getPosition(), OPSoundEvent.gomu_stretch, SoundCategory.NEUTRAL, 10.0F, 1.0F);
                    break;
                }
                case "GomuGear4A": {
                    ep.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 400, 2));
                    ep.addPotionEffect(new PotionEffect(MobEffects.SPEED, 400, 2));
                    ep.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 400, 2));
                    break;
                }
                case "MeraHiganA": {
                    EntityFirePunch eff = new EntityFirePunch(world, ep.posX, ep.posY + 0.6f, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep, 1);
                    world.spawnEntity(eff);
                    world.playSound(null, ep.getPosition(), OPSoundEvent.fire_fist, SoundCategory.NEUTRAL, 10.0F, 1.0F);
                    break;
                }
                case "MeraShinkaA": {
                    world.spawnEntity(new EntityFirePunch(world, ep.posX, ep.posY + 2, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep, 2));
                    world.spawnEntity(new EntityFirePunch(world, ep.posX + 0.5, ep.posY + 2.5, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep, 2));
                    world.spawnEntity(new EntityFirePunch(world, ep.posX, ep.posY + 2.5, ep.posZ + 1, ep.rotationYaw, ep.rotationPitch, ep, 2));
                    world.spawnEntity(new EntityFirePunch(world, ep.posX - 0.5, ep.posY + 1.5, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep, 2));
                    world.spawnEntity(new EntityFirePunch(world, ep.posX, ep.posY + 1.5, ep.posZ - 1, ep.rotationYaw, ep.rotationPitch, ep, 2));
                    world.playSound(null, ep.getPosition(), OPSoundEvent.fire_fist, SoundCategory.NEUTRAL, 10.0F, 1.0F);
                    world.playSound(null, ep.getPosition(), OPSoundEvent.fire_fist, SoundCategory.NEUTRAL, 10.0F, 1.0F);
                    world.playSound(null, ep.getPosition(), OPSoundEvent.fire_fist, SoundCategory.NEUTRAL, 10.0F, 1.0F);
                    world.playSound(null, ep.getPosition(), OPSoundEvent.fire_fist, SoundCategory.NEUTRAL, 10.0F, 1.0F);
                    world.playSound(null, ep.getPosition(), OPSoundEvent.fire_fist, SoundCategory.NEUTRAL, 10.0F, 1.0F);
                    break;
                }
                case "MeraHikenA": {
                    EntityFirePunch eff = new EntityFirePunch(world, ep.posX, ep.posY + 0.6f, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep, 4);
                    world.spawnEntity(eff);
                    break;
                }
                case "MeraEnteiA": {
                    EntityEntei ee = new EntityEntei(world, ep.posX, ep.posY + 5, ep.posZ, ep);
                    world.spawnEntity(ee);
                    world.playSound(null, ep.posX, ep.posY + 5, ep.posZ, OPSoundEvent.entei_charge, SoundCategory.NEUTRAL, 15.0F, 1.0F);
                    break;
                }
                case "SlowBeamA": {
                    EntitySlowBeam esb = new EntitySlowBeam(world, ep.posX, ep.posY + 0.6f, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep);

                    world.playSound(null, ep.getPosition(), OPSoundEvent.slow_beam, SoundCategory.NEUTRAL, 10.0F, 1.0F);
                    world.spawnEntity(esb);
                    break;
                }
                case "SlowBallA": {
                    EntitySlowBeamSpawner esb = new EntitySlowBeamSpawner(world, ep.posX, ep.posY + 0.6f, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep, true, 60, 20);
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
                    break;
                }
                case "SlowMashiA": {
                    EntitySlowBeamSpawner esb = new EntitySlowBeamSpawner(world, ep.posX, ep.posY + 0.6f, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep, false, 120, 10);
                    world.spawnEntity(esb);
                    break;
                }
                case "ClearSkatingA": {
                    ep.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 500, 0));
                    break;
                }
                case "RoomA": {
                    world.playSound(null, ep.getPosition(), OPSoundEvent.dome_appear, SoundCategory.NEUTRAL, 40.0F, 1.0F);
                    world.setBlockState(new BlockPos(ep.getPosition().getX(), ep.getPosition().getY(), ep.getPosition().getZ()), OPBlocks.BlockLawDomeCenter.getDefaultState());
                    break;
                }
                case "Shambles": {
                    world.playSound(null, ep.getPosition(), OPSoundEvent.shambles, SoundCategory.NEUTRAL, 40.0F, 1.0F);
                    PowerUtils.OPShambles(ep);
                    break;
                }
                case "InjectionShot": {
                    world.playSound(null, ep.getPosition(), OPSoundEvent.shambles, SoundCategory.NEUTRAL, 40.0F, 1.0F);
                    PowerUtils.OPInjectionShot(ep);
                    break;
                }
                case "Takt": {
                    world.playSound(null, ep.getPosition(), OPSoundEvent.takt, SoundCategory.NEUTRAL, 40.0F, 1.0F);
                    if (!OPCraft.config.disableGriefing.getCurrentValue()) {//if griefing is disabled do not execute this power.
                        PowerUtils.OPTakt(ep);
                    }
                    break;
                }
                case "IceSaberA": {
                    PowerUtils.destroyNearbyCropsAndGrass(ep, 5);
                    world.playSound(null, ep.getPosition(), OPSoundEvent.ice_saber, SoundCategory.NEUTRAL, 20, 1.0F);
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
                    break;
                }
                case "IceAgeA": {
                    if (!OPCraft.config.disableGriefing.getCurrentValue()) {
                        PowerUtils.createIceSeaRoad(ep.getHorizontalFacing(), new BlockPos(ep.posX, ep.posY, ep.posZ), ep);
                    }
                    world.playSound(null, ep.getPosition(), OPSoundEvent.ice_age, SoundCategory.NEUTRAL, 50.0F, 1.0F);
                    break;
                }
                case "IceBlockPhBeakA": {
                    world.playSound(null, ep.getPosition(), OPSoundEvent.ice_phoenix, SoundCategory.NEUTRAL, 20.0F, 1.0F);
                    EntityIcePhoenix e = new EntityIcePhoenix(world, ep.posX, ep.posY + 1, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep);
                    world.spawnEntity(e);
                    break;
                }
                case "PadHoA": {
                    world.playSound(null, ep.getPosition(), OPSoundEvent.pad_ho, SoundCategory.NEUTRAL, 15, 1.0F);
                    OPUtils.damageNearbyPlayers(ep, 15, 6F, 0.2F);
                    break;
                }
                case "TsuppariPadHoA": {
                    world.playSound(null, ep.getPosition(), OPSoundEvent.pad_ho, SoundCategory.NEUTRAL, 30, 1.0F);
                    OPUtils.damageNearbyPlayers(ep, 30, 12F, 0.5F);
                    break;
                }
                case "UrsusShockA": {
                    EntityUrsusBubble e = new EntityUrsusBubble(world, ep.posX, ep.posY + 1, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep);
                    world.playSound(null, ep.getPosition(), OPSoundEvent.ursus_shock, SoundCategory.NEUTRAL, 90, 1.0F);
                    world.spawnEntity(e);
                    break;
                }
                case "SangoA": {
                    PowerUtils.createLightnings(ep, 150, 80D);
                    break;
                }
                case "DeathpieaA": {
                    PowerUtils.createLightnings(ep, 400, 100D);
                    break;
                }
                case "WhiteBlowA": {
                    EntitySmokePunch smokePunch = new EntitySmokePunch(world, ep.posX, ep.posY + 0.6, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep);
                    world.spawnEntity(smokePunch);
                    world.playSound(null, ep.getPosition(), OPSoundEvent.smoke_whoosh, SoundCategory.NEUTRAL, 10.0F, 1.0F);
                    break;
                }
                case "WhiteOutA": {
                    world.setBlockState(ep.getPosition(), OPBlocks.BlockSmokeCloud.getDefaultState());
                    world.playSound(null, ep.getPosition(), OPSoundEvent.smoke_ambient, SoundCategory.NEUTRAL, 20.0F, 1.0F);
                    break;
                }
                case "BlackHoleA":
                case "LiberationA":
                    world.playSound(null, ep.getPosition(), OPSoundEvent.dark, SoundCategory.NEUTRAL, 30f, 1f);
                    break;
                case "TamaitoA": {
                    EntityTamaito et = new EntityTamaito(world, ep.posX, ep.posY + 1.35f, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep);
                    world.spawnEntity(et);
                    world.playSound(null, ep.posX, ep.posY, ep.posZ, OPSoundEvent.tamaito, SoundCategory.NEUTRAL, 20.0F, 1.0F);
                    break;
                }
                case "OverheatA": {
                    EntityOverheat overheat = new EntityOverheat(world, ep.posX, ep.posY, ep.posZ, ep.rotationYaw, ep.rotationPitch, true, ep);
                    world.spawnEntity(overheat);
                    world.playSound(null,ep.posX,ep.posY,ep.posZ,OPSoundEvent.fire_fist,SoundCategory.NEUTRAL, 300f, 1f);
                    break;
                }
            }

            //OTHERS

            if (text.equals("DISABLEDAMAGE")) {
                ep.setEntityInvulnerable(true);
                if (!OPCraft.IS_RELEASE_VERSION) {
                    ep.sendMessage(new TextComponentString("DISABLED DAMAGE"));
                }
            } else if (text.equals("ENABLEDAMAGE")) {
                ep.setEntityInvulnerable(false);
                if (!OPCraft.IS_RELEASE_VERSION) {
                    ep.sendMessage(new TextComponentString("ENABLED DAMAGE"));
                }
            } else if (text.equals("KairosekiItem")) {
                ep.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 3));
                ep.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 40, 1));
            } else if (text.equals("StormLeg")) {
                EntityStormLeg entityStormLeg = new EntityStormLeg(world, ep.posX, ep.posY, ep.posZ, ep.rotationYaw, ep.rotationPitch, ep);
                world.spawnEntity(entityStormLeg);
                world.playSound(null, ep.getPosition(), OPSoundEvent.shambles, SoundCategory.PLAYERS, 1f, 1f);
            } else if (text.equals("KingGun")) {
                world.playSound(null, ep.getPosition(), OPSoundEvent.air_blast, SoundCategory.NEUTRAL, 50f, 1f);
                //50 blocks range
                Vec3d vec3d = ep.getPositionEyes(0);
                Vec3d vec3d1 = ep.getLook(0);
                Vec3d vec3d2 = vec3d.addVector(vec3d1.x * 50, vec3d1.y * 50, vec3d1.z * 50);
                int l = MathHelper.floor(vec3d2.x);
                int i1 = MathHelper.floor(vec3d2.y);
                int j1 = MathHelper.floor(vec3d2.z);

                ArrayList<Point3d> points = MathUtils.getIntermediatePoints(new Vec3d(ep.posX, ep.posY + 1d, ep.posZ), new Vec3d(l, i1, j1), 100);
                Random r = new Random();
                for (Point3d point : points) {
                    List<Entity> players = OPUtils.getNearbyEntities(ep, new BlockPos(point.x, point.y, point.z), 2d, EntityPlayer.class);
                    List<Entity> living = OPUtils.getNearbyEntities(ep, new BlockPos(point.x, point.y, point.z), 2d, EntityLiving.class);
                    for (Entity player : players) {
                        if (player != ep) {
                            player.attackEntityFrom(DamageSource.causePlayerDamage(ep), MathUtils.calculateDamage(ep, 32, false));
                        }
                    }
                    for (Entity entity : living) {
                        entity.attackEntityFrom(DamageSource.causePlayerDamage(ep), 32);
                    }
                    for (int i = 0; i < 5; i++) {
                        double offsetX = r.nextDouble() / 2d - 0.5d;
                        double offsetY = r.nextDouble() / 2d - 0.5d;
                        double offsetZ = r.nextDouble() / 2d - 0.5d;
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
            } else if (text.equals("SixPowersShaveDisable")) {
                if (attackSpeedAttribute.hasModifier(attackSpeedMod)) {
                    attackSpeedAttribute.removeModifier(attackSpeedMod);
                }
                if (moveSpeedAttribute.hasModifier(moveSpeedMod)) {
                    moveSpeedAttribute.removeModifier(moveSpeedMod);
                }
            }

        }
    }
}