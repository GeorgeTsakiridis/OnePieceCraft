package georgetsak.opcraft.client;

import georgetsak.opcraft.OPCraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class OPSoundEvent {

	public static SoundEvent clima_ice;
	public static SoundEvent clima_fire;
	public static SoundEvent kabuto_load;
	public static SoundEvent kabuto_release;
	public static SoundEvent flintlock_fire;
	public static SoundEvent senriku_fire;
	public static SoundEvent bazooka_fire;
	public static SoundEvent gun_empty;
	public static SoundEvent gomu_stretch;
	public static SoundEvent gomu_gear2;
	public static SoundEvent fire_fist;
	public static SoundEvent entei_charge;
	public static SoundEvent slow_beam;
	public static SoundEvent dome_appear;
	public static SoundEvent dome_disappear;
	public static SoundEvent shambles;
	public static SoundEvent takt;
	public static SoundEvent ice_saber;
	public static SoundEvent ice_ball;
	public static SoundEvent ice_age;
	public static SoundEvent ice_phoenix;
	public static SoundEvent pad_ho;
	public static SoundEvent ursus_shock;
	public static SoundEvent we_are;
	public static SoundEvent smoke_whoosh;
	public static SoundEvent smoke_ambient;
	public static SoundEvent emperor_haki;
	public static SoundEvent crocodile_laugh;
	public static SoundEvent dark;
	public static SoundEvent air_blast;

	public static void registerSounds() {
		clima_ice = registerSound("effects.iceeffect");
		clima_fire = registerSound("effects.fireeffect");
		kabuto_load = registerSound("effects.ussopkabutoload");
		kabuto_release = registerSound("effects.ussopkabutorelease");
		flintlock_fire = registerSound("effects.weapons.flintlockfire");
		senriku_fire = registerSound("effects.weapons.senrikufire");
		bazooka_fire = registerSound("effects.weapons.bazookafire");
		gun_empty = registerSound("effects.weapons.gunempty");
		gomu_stretch = registerSound("devilfruit.gomu.stretch");
		gomu_gear2 = registerSound("devilfruit.gomu.gear2");
		fire_fist = registerSound("devilfruit.mera.firefist");
		entei_charge = registerSound("devilfruit.mera.enteicharge");
		slow_beam = registerSound("devilfruit.noro.slowbeam");
		dome_appear = registerSound("devilfruit.op.domeappear");
		dome_disappear = registerSound("devilfruit.op.domedisappear");
		shambles = registerSound("devilfruit.op.shambles");
		takt = registerSound("devilfruit.op.takt");
		ice_saber = registerSound("devilfruit.hie.icesaber");
		ice_ball = registerSound("devilfruit.hie.iceball");
		ice_age = registerSound("devilfruit.hie.iceage");
		ice_phoenix = registerSound("devilfruit.hie.icephoenix");
		pad_ho = registerSound("devilfruit.nikyu.padho");
		ursus_shock = registerSound("devilfruit.nikyu.ursusshock");
		we_are = registerSound("misc.weare");
		smoke_whoosh = registerSound("devilfruit.moku.smokewhoosh");
		smoke_ambient = registerSound("devilfruit.moku.smokeambient");
		emperor_haki = registerSound("effects.emperorhaki");
		crocodile_laugh = registerSound("effects.crocodile.crocodilelaugh");
		dark = registerSound("devilfruit.yami.dark");
		air_blast = registerSound("effects.airblast");
	}

	private static SoundEvent registerSound(String soundName) {
		final ResourceLocation soundID = new ResourceLocation(OPCraft.MODID, soundName);
		SoundEvent soundEvent = new SoundEvent(soundID).setRegistryName(soundID);
		ForgeRegistries.SOUND_EVENTS.register(soundEvent);
		return soundEvent;

	}
}
