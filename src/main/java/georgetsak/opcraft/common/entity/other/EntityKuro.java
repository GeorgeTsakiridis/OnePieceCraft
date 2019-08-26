package georgetsak.opcraft.common.entity.other;

import georgetsak.opcraft.common.entity.EntityBounty;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityKuro extends EntityBounty {

    public EntityKuro(World worldIn) {
        super(worldIn);
        setSize(0.8f,1.9f);
        setBounty(-4000);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

}
