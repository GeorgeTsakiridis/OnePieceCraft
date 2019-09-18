package georgetsak.opcraft.common.entity.other;

import georgetsak.opcraft.common.registry.OPItems;
import georgetsak.opcraft.common.registry.OPMerchantTrades;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityOPVillager extends EntityCreature implements IMerchant
{

    private EntityPlayer buyingPlayer;
    private MerchantRecipeList buyingList;
    private MerchantRecipeList fullList;

    public EntityOPVillager(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
    }

    public boolean canBeLeashedTo(EntityPlayer player)
    {
        return false;
    }

    protected void initEntityAI()
    {

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(9, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.38D);
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if(isTrading()){
            this.motionX = 0;
            this.motionY = 0;
            this.motionZ = 0;

        }

    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_VILLAGER_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_VILLAGER_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_VILLAGER_DEATH;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        SoundEvent soundevent = SoundEvents.ENTITY_ZOMBIE_STEP;
        this.playSound(soundevent, 0.15F, 1.0F);
    }


    public float getEyeHeight()
    {
        return 1.74F;
    }

    protected boolean canEquipItem(ItemStack stack)
    {
        return (stack.getItem() != Items.EGG || !this.isChild() || !this.isRiding()) && super.canEquipItem(stack);
    }

    protected boolean canDespawn()
    {
        return false;
    }


    public double getYOffset()
    {
        return this.isChild() ? 0.0D : -0.35D;
    }

    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);
        if(!world.isRemote) {
            this.entityDropItem(new ItemStack(OPItems.ItemBerryCoin, rand.nextInt(10)), 0.0F);
        }
    }

    public boolean isTrading()
    {
        return this.buyingPlayer != null;
    }


    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack itemStack = player.getHeldItem(hand);

        boolean flag = itemStack != null && itemStack.getItem() == Items.SPAWN_EGG;

        if (!flag && this.isEntityAlive() && !this.isTrading() && !this.isChild() && !player.isSneaking()) {
            if (!this.world.isRemote && (this.buyingList == null || !this.buyingList.isEmpty())) {
                this.setCustomer(player);
                player.displayVillagerTradeGui(this);
            }

            player.addStat(StatList.TALKED_TO_VILLAGER);
            return true;
        } else {
            return super.processInteract(player, hand);
        }
    }


    @Override
    public void setCustomer(EntityPlayer player) {
        this.buyingPlayer = player;
    }

    @Override
    public EntityPlayer getCustomer() {
        return this.buyingPlayer;
    }

    @Override
    public MerchantRecipeList getRecipes(EntityPlayer player) {

        fullList = OPMerchantTrades.villagerRecipes;

        if (this.buyingList == null)
        {
            this.buyingList = new MerchantRecipeList();
            for(int i = 0; i < 5; i++){
                int random = rand.nextInt(fullList.size());
                if(!this.buyingList.contains(fullList.get(random))){
                    this.buyingList.add(fullList.get(random));
                }
            }
        }

        return this.buyingList;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setRecipes(MerchantRecipeList recipeList) {

    }

    @Override
    public void useRecipe(MerchantRecipe recipe) {
        boolean selected = false;
        if(rand.nextInt(6) == 0 && this.buyingList.size() < 8){
            while(!selected){
                int random = rand.nextInt(fullList.size());
                if(!this.buyingList.contains(fullList.get(random))){
                    this.buyingList.add(fullList.get(random));
                    selected = true;
                }
            }
        }
    }

    @Override
    public void verifySellingItem(ItemStack stack) {
        if (!this.world.isRemote && this.livingSoundTime > -this.getTalkInterval() + 20)
        {
            this.livingSoundTime = -this.getTalkInterval();

            if (stack != null)
            {
                this.playSound(SoundEvents.ENTITY_VILLAGER_YES, this.getSoundVolume(), this.getSoundPitch());
            }
            else
            {
                this.playSound(SoundEvents.ENTITY_VILLAGER_NO, this.getSoundVolume(), this.getSoundPitch());
            }
        }

    }

    public World getWorld()
    {
        return this.world;
    }

    public BlockPos getPos()
    {
        return new BlockPos(this);
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        if (this.buyingList != null)
        {
            compound.setTag("offers", this.buyingList.getRecipiesAsTags());
        }
    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("offers", 10))
        {
            NBTTagCompound nbttagcompound = compound.getCompoundTag("offers");
            this.buyingList = new MerchantRecipeList(nbttagcompound);
        }
    }




}