package georgetsak.opcraft.common.generator.structures.village.handlers;

import georgetsak.opcraft.common.generator.structures.village.components.Bar1Component;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.List;
import java.util.Random;


public class Bar1Handler implements VillagerRegistry.IVillageCreationHandler {

    @Override
    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int size){
        return new StructureVillagePieces.PieceWeight(Bar1Component.class, 20, MathHelper.getInt(random, 0 + size, 1 + size));
    }

    @Override
    public Class<?> getComponentClass(){
        return Bar1Component.class;
    }

    @Override
    public StructureVillagePieces.Village buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List<StructureComponent> pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5){
        return Bar1Component.buildComponent(pieces, p1, p2, p3, facing);
    }
}
