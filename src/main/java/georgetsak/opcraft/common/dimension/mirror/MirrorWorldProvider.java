package georgetsak.opcraft.common.dimension.mirror;

import georgetsak.opcraft.client.OPSoundEvent;
import georgetsak.opcraft.common.registry.OPDimensions;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nullable;

public class MirrorWorldProvider extends WorldProvider {

    @Override
    public DimensionType getDimensionType() {
        return OPDimensions.MIRROR;
    }

    @Nullable
    @Override
    public String getSaveFolder() {
        return "DIM_MIRROR";
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new MirrorChunkGenerator(world);
    }

    @Override
    public boolean canDoRainSnowIce(Chunk chunk) {
        return false;
    }

    @Override
    public boolean canBlockFreeze(BlockPos pos, boolean byWater) {
        return false;
    }

    @Override
    public Biome getBiomeForCoords(BlockPos pos) {
        return Biomes.VOID;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public boolean hasSkyLight() {
        return false;
    }

    @Override
    public boolean isSkyColored() {
        return false;
    }

    public float calculateCelestialAngle(long worldTime, float partialTicks)
    {
        return 0.5F;
    }

    @Override
    protected void generateLightBrightnessTable() {
        for (int i = 0; i <= 15; ++i)
        {
            this.lightBrightnessTable[i] = 0.7f;
        }
    }

    @Nullable
    @Override
    public MusicTicker.MusicType getMusicType() {
        return super.getMusicType();
    }

    @Override
    public void updateWeather() {
    }
}
