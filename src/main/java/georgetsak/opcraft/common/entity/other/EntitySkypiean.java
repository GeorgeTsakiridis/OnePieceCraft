package georgetsak.opcraft.common.entity.other;

import georgetsak.opcraft.common.network.proxy.CommonProxy;
import georgetsak.opcraft.common.registry.OPBlocks;
import georgetsak.opcraft.common.registry.OPMerchantTrades;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class EntitySkypiean extends EntityCreature implements IMerchant {

    private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntitySkypiean.class, DataSerializers.VARINT);
    private MerchantRecipeList buyingList;
    private MerchantRecipeList fullList;
    private EntityPlayer buyingPlayer;


    public EntitySkypiean(World worldIn) {
        super(worldIn);
        setSize(0.8f,1.9f);
        int i = new Random().nextInt(5);
        this.getDataManager().register(TYPE, i);

    }

    public EntitySkypiean(World world, BlockPos pos){
        this(world);
        setPosition(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));

    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }

    }

    public void setType(int type)
    {
        this.getDataManager().set(TYPE, type);
    }

    public int getType()
    {
        return this.getDataManager().get(TYPE);
    }

    @Override
    public boolean canBeLeashedTo(EntityPlayer player) {
        return false;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {

    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack itemStack = player.getHeldItem(hand);

        boolean flag = itemStack != null && itemStack.getItem() == Items.SPAWN_EGG;

        if (!flag && this.isEntityAlive() && !this.isTrading() && !player.isSneaking()) {
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

    public boolean isTrading()
    {
        return this.buyingPlayer != null;
    }

    @Override
    public void setCustomer(@Nullable EntityPlayer player) {
            this.buyingPlayer = player;
    }

    @Override
    public EntityPlayer getCustomer() {
        return this.buyingPlayer;
    }
    
    @Override
    public MerchantRecipeList getRecipes(EntityPlayer player) {
        if(getType() == 5) {
            fullList = OPMerchantTrades.skypieanBankerRecipes;
        }else{
            fullList = OPMerchantTrades.skypieanRecipes;
        }
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
        compound.setInteger("type", this.getDataManager().get(TYPE));
        if (this.buyingList != null)
        {
            compound.setTag("offers", this.buyingList.getRecipiesAsTags());
        }
    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        setType(compound.getInteger("type"));

        if (compound.hasKey("offers", 10))
        {
            NBTTagCompound nbttagcompound = compound.getCompoundTag("offers");
            this.buyingList = new MerchantRecipeList(nbttagcompound);
        }
    }
}
