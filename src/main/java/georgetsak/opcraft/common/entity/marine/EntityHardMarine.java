package georgetsak.opcraft.common.entity.marine;

import georgetsak.opcraft.common.registry.OPItems;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class EntityHardMarine extends EntityMarine{

	public EntityHardMarine(World worldIn) {
		super(worldIn);
		setMarineType(new Random().nextInt(6) + 1);
		setBounty(200);
	}

    public EntityHardMarine(World worldIn, BlockPos position){
        this(worldIn);
        this.setPosition(position.getX(), position.getY(), position.getZ());
    }

	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
    }
	
    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        switch(getMarineType()) {
            case (2):
                this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(OPItems.SMOKER_JITTE));
                break;
        }
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }
}
