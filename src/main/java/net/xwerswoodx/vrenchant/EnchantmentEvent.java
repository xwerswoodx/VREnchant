package net.xwerswoodx.vrenchant;

import java.util.List;
import java.util.Random;

import net.xwerswoodx.vrenchant.enchantments.EnchantmentJumpBoost;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentRunBoost;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.GuiScreenEvent.PotionShiftEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentArmorPenetration;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentBeheading;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentFarming;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentArrowDragonSlayer;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentLifeSteal;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentLucky;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentNewbiefied;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentNightVision;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentPoisonAspect;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentReBirth;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentSilkSpawners;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentWaterBreathing;

public class EnchantmentEvent {
	
	private EntityEquipmentSlot[] slotsArmor = new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};
	private EntityEquipmentSlot[] slotsHand = new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND};
	public Enchantment runBoost = new EnchantmentRunBoost(Enchantment.Rarity.VERY_RARE, slotsArmor);
	public Enchantment jumpBoost = new EnchantmentJumpBoost(Enchantment.Rarity.VERY_RARE, slotsArmor);
	public Enchantment nightVision = new EnchantmentNightVision(Enchantment.Rarity.RARE, slotsArmor);
	public Enchantment waterBreathing = new EnchantmentWaterBreathing(Enchantment.Rarity.VERY_RARE, slotsArmor);
	public Enchantment beheading = new EnchantmentBeheading(Enchantment.Rarity.RARE, slotsHand);
	public Enchantment lucky = new EnchantmentLucky(Enchantment.Rarity.VERY_RARE, slotsHand);
	public Enchantment reBirth = new EnchantmentReBirth(Enchantment.Rarity.VERY_RARE, slotsHand);
	public Enchantment silkSpawner = new EnchantmentSilkSpawners(Enchantment.Rarity.VERY_RARE, slotsHand);
	public Enchantment lifeSteal = new EnchantmentLifeSteal(Enchantment.Rarity.VERY_RARE, slotsHand);
	public Enchantment arrowDragonSlayer = new EnchantmentArrowDragonSlayer(Enchantment.Rarity.VERY_RARE, slotsHand);
	public Enchantment armorPenetration = new EnchantmentArmorPenetration(Enchantment.Rarity.VERY_RARE, slotsHand);
	public Enchantment poisonAspect = new EnchantmentPoisonAspect(Enchantment.Rarity.RARE, slotsHand);
	public Enchantment farming = new EnchantmentFarming(Enchantment.Rarity.RARE, slotsHand);
	public Enchantment newbiefied = new EnchantmentNewbiefied(Enchantment.Rarity.VERY_RARE, slotsHand);
	
	@SubscribeEvent
	public void onEnchantmentRegister(RegistryEvent.Register<Enchantment> event) {
		IForgeRegistry<Enchantment> registry = event.getRegistry();
		registry.register(runBoost);
		registry.register(jumpBoost);
		registry.register(nightVision);
		registry.register(waterBreathing);
		registry.register(beheading);
		registry.register(lucky);
		registry.register(reBirth);
		registry.register(silkSpawner);
		registry.register(lifeSteal);
		registry.register(arrowDragonSlayer);
		registry.register(armorPenetration);
		registry.register(poisonAspect);
		registry.register(farming);
		registry.register(newbiefied);
	}
	
    @SubscribeEvent
    public void onSpawnerBreak(BreakEvent event) {
	    if (event.getPlayer() instanceof EntityPlayer) {
	    	int silk = 0;
	    	if (!event.getPlayer().getHeldItemMainhand().isEmpty()) {
		    	if (event.getPlayer().getHeldItemMainhand().getItem() instanceof ItemPickaxe) {
		    		silk = EnchantmentHelper.getEnchantmentLevel(silkSpawner, event.getPlayer().getHeldItemMainhand());
		    	}
	    	}
	    	
	    	if ((event.getState().getBlock() == Blocks.MOB_SPAWNER) && (silk > 0)) {
	    		if (event.getWorld().getTileEntity(event.getPos()) instanceof TileEntityMobSpawner) {
			       	TileEntityMobSpawner entity = (TileEntityMobSpawner) event.getWorld().getTileEntity(event.getPos());
			       	ItemStack egg = new ItemStack(Blocks.MOB_SPAWNER);
			       	
			        NBTTagCompound nbttagcompound = egg.hasTagCompound() ? egg.getTagCompound() : new NBTTagCompound();
			        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
			        nbttagcompound1.setString("id", VREnchant.getTypeFromEntity(entity));
			        nbttagcompound.setTag("EntityTag", nbttagcompound1);
			        egg.setTagCompound(nbttagcompound);
			        
			        String[] nameSplit = VREnchant.getTypeFromEntity(entity).split(":");
			        String curName = nameSplit[1].toLowerCase(); //nameSplit[1].substring(0, 1).toUpperCase() + nameSplit[1].substring(1).toLowerCase();
			        String name = EntityEnums.getName(curName);
			        /*
			        if (name.contains("_")) {
			        	String[] nameFix = name.split("_");
			        	name = nameFix[0];
			        	for (int i = 1; i < nameFix.length; i++) {
			        		name = name + " " + nameFix[i].substring(0, 1).toUpperCase() + nameFix[i].substring(1).toLowerCase();
			        	}
			        }
			        */
//			        event.getState().getBlock().getDrops(event.getWorld(), event.getPos(), event.getState(), 0).add(egg);
			        egg.setStackDisplayName(TextFormatting.RESET + "Monster Spawner [" + name + "]");
//			       	event.getPlayer().dropItem(egg, true);
    				event.getWorld().spawnEntity(new EntityItem(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), egg));
	    		}
//		        System.out.println(event.getState().getPropertyKeys().toString() + " -- " + event.getState().getProperties().toString());
		   	}
    	}
    }
    
    @SubscribeEvent
    public void onCropBreak(BreakEvent event) {
    	if (event.getState().getBlock() instanceof BlockCrops) {
    		if ((event.getPlayer().getHeldItemMainhand() != null) && (event.getPlayer().getHeldItemMainhand().getItem() instanceof ItemHoe)) {
	    		BlockCrops crop = (BlockCrops) event.getState().getBlock();
	    		if (crop.isMaxAge(event.getState())) {
	    			Random random = new Random();
	    			Item drop = crop.getItemDropped(event.getState(), random, 0);
	    			ItemStack base = crop.getItem(event.getWorld(), event.getPos(), event.getState());
	    			int farmingLevel = EnchantmentHelper.getEnchantmentLevel(farming, event.getPlayer().getHeldItemMainhand());
	    			
	    			if (farmingLevel > 0) {
	    				int amount = random.nextInt(farmingLevel) + 1;
	    				ItemStack stack = new ItemStack(drop, amount);
	    				event.getWorld().spawnEntity(new EntityItem(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), stack));

	    				amount = random.nextInt(farmingLevel) + 1;
	    				stack = new ItemStack(base.getItem(), amount);
	    				event.getWorld().spawnEntity(new EntityItem(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), stack));
	    			}
	    		}
	    	}
		}    	
    }
    
    @SubscribeEvent
    public void onHoeRightClick(PlayerInteractEvent event) {
    	if ((event.getItemStack().getItem() instanceof ItemHoe) && (EnchantmentHelper.getEnchantmentLevel(farming, event.getItemStack()) > 0)) {
        	int moreL = event.getItemStack().getItemDamage();
//       	int moreH = event.getItemStack().getMaxDamage();
        	/*
        	if (moreL < moreH) {
        		event.getItemStack().setItemDamage(moreL + 1);
        	}
        	*/
        	Block block = event.getWorld().getBlockState(event.getPos()).getBlock();
        	if ((block instanceof BlockGrass) || (block instanceof BlockDirt)) {
        		if (moreL > 0) {
        			int min = EnchantmentHelper.getEnchantmentLevel(farming, event.getItemStack());
        			int max = moreL;
        			min = Math.min(min, max);
        			event.getItemStack().setItemDamage(moreL - min);
        		}
//            	System.out.println(String.valueOf(moreL) + " " + String.valueOf(moreH) + " " + event.getWorld().getBlockState(event.getPos()).getBlock().getUnlocalizedName());        		
        	}
    	}
    }
    
    @SubscribeEvent
    public void onSpawnerPlace(PlaceEvent event) {
    	if (event.getPlayer() instanceof EntityPlayer) {
    		if (event.getPlacedBlock().getBlock() == Blocks.MOB_SPAWNER) {
    			if (event.getWorld().getTileEntity(event.getPos()) instanceof TileEntityMobSpawner) {
	    			TileEntityMobSpawner entity = (TileEntityMobSpawner) event.getWorld().getTileEntity(event.getPos());
	    			String type = "minecraft:pig";
	    			if ((event.getPlayer().getHeldItemMainhand() != null) && (event.getPlayer().getHeldItemMainhand().hasTagCompound())) {
	    				if (event.getPlayer().getHeldItemMainhand().getTagCompound().hasKey("EntityTag"))
	    					type = event.getPlayer().getHeldItemMainhand().getTagCompound().getCompoundTag("EntityTag").getString("id");
	    			}
	    			entity.getSpawnerBaseLogic().setEntityId(VREnchant.getNamedIdFromString(type));
	                entity.markDirty();
	                event.getWorld().notifyBlockUpdate(event.getPos(), event.getPlacedBlock(), event.getPlacedBlock(), 3);
    			}
            }
    	}
    }
    
    @SubscribeEvent
    public void onEggDrop(LivingDropsEvent event) {
//    	if (!(event.getEntity() instanceof EntityPlayer)) {
    	Entity entity = event.getEntity();
    	EntityLivingBase entityLiving = event.getEntityLiving();
//    	ItemStack item = new ItemStack(Items.SPAWN_EGG);
    	Random rand = new Random();
    	
    	if (event.getSource().getTrueSource() instanceof EntityPlayer) {
    		EntityPlayer player = (EntityPlayer)event.getSource().getTrueSource();
    		
    		if ((player.getHeldItemMainhand().getItem() instanceof ItemSword) || (player.getHeldItemMainhand().getItem() instanceof ItemAxe)) {
    			ItemStack stack = player.getHeldItemMainhand();
    			if (stack.isItemEnchanted()) {
    				int beheadingLevel = EnchantmentHelper.getEnchantmentLevel(beheading, stack);
    				int reBirthLevel = EnchantmentHelper.getEnchantmentLevel(reBirth, stack);
    				int luckyLevel = EnchantmentHelper.getEnchantmentLevel(lucky, stack);
    				
    				if ((beheadingLevel > 0) && HeadEnums.getChance(entity, beheadingLevel) > 0) {
//    					int random = rand.nextInt(beheadingLevel);
    					int random = rand.nextInt(HeadEnums.getChance(entity, beheadingLevel));
    					if (random == 0) {
    						/*
    						int type = 10;
    						String name = entity.getName();
    						if (entity instanceof EntityZombie)
    							type = 2;
    						else if (entity instanceof EntitySkeleton)
    							type = 0;
    						else if (entity instanceof EntityCreeper)
    							type = 4;
    						else if (entity instanceof EntityPlayer)
    							type = 3;    						
    						
    						if (type < 10) {
    							ItemStack skull = new ItemStack(Items.SKULL, 1, type);
    							NBTTagCompound tag = skull.hasTagCompound() ? skull.getTagCompound() : new NBTTagCompound();
    							tag.setTag("SkullOwner", new NBTTagString(name));
    							skull.setTagCompound(tag);
        			           	event.getDrops().add(new EntityItem(entity.getEntityWorld(), entity.getPosition().getX(), entity.getPosition().getY(), entity.getPosition().getZ(), skull));
    						}
    						*/
    						ItemStack skull = HeadEnums.getItem(entity);
    						if (skull.getItem() != Items.AIR)
    							event.getDrops().add(new EntityItem(entity.getEntityWorld(), entity.getPosition().getX(), entity.getPosition().getY(), entity.getPosition().getZ(), skull));
	    				}
	    			}
    				
    				if (reBirthLevel > 0) {
    					if (!(entity instanceof EntityPlayer)) {
        					int random = rand.nextInt(getChance(reBirthLevel));
        					if (random == 0) {
        						System.out.println(entity.getName());
        						String id = EntityEnums.getLocation(entity);
        						if (!id.equalsIgnoreCase("null")) {
            						ItemStack spawnEgg = new ItemStack(Items.SPAWN_EGG);
	        						NBTTagCompound tag = spawnEgg.hasTagCompound() ? spawnEgg.getTagCompound() : new NBTTagCompound();
	        						NBTTagCompound tagNew = new NBTTagCompound();
	        						tagNew.setString("id", id);
	        						tag.setTag("EntityTag", tagNew);
	        						spawnEgg.setTagCompound(tag);
	        			           	event.getDrops().add(new EntityItem(entity.getEntityWorld(), entity.getPosition().getX(), entity.getPosition().getY(), entity.getPosition().getZ(), spawnEgg));
	        			       }
        					}
    					}
    				}
    				
    				if (luckyLevel > 0) {
    					int random = rand.nextInt(luckyLevel * 5) + 1;
    					player.addExperience(luckyLevel * random);
    				}
    			}
	    	}
    	}
    }
    
    @SubscribeEvent
    public void onPlayerDead(LivingDropsEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			List<EntityItem> items = event.getDrops();
			System.out.println("Start: " + items.toString());
			EntityPlayer player = (EntityPlayer)event.getEntity();
			
			for (int i = 0; i < items.size(); i++) {
				ItemStack item = items.get(i).getItem();
				if (EnchantmentHelper.getEnchantmentLevel(newbiefied, item) > 0) {
    				player.inventory.addItemStackToInventory(item);
    				event.getDrops().remove(i);
    				i--;
    			}
    		}
    	}
    }
    
    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.Clone event) {
    	event.getEntityPlayer().inventory.copyInventory(event.getOriginal().inventory);
    }
	
	/*
	@SubscribeEvent
	public void onEquip(LivingEquipmentChangeEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.getEntity();
			if ((event.getSlot() == EntityEquipmentSlot.FEET) && (event.getTo().isItemEnchanted())) {
				ItemStack stack = event.getTo();
				int autoRunLevel = EnchantmentHelper.getEnchantmentLevel(autoRun, stack);
				if (autoRunLevel > 0)
					player.addPotionEffect(new PotionEffect(MobEffects.SPEED));
			}
		}
	}
	*/
	
	@SubscribeEvent
	public void onTick(TickEvent.PlayerTickEvent event) {
		EntityPlayer player = (EntityPlayer)event.player;
		if (player.getItemStackFromSlot(EntityEquipmentSlot.FEET) != null) {
			ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
			if (stack.isItemEnchanted()) {
				int runBoostLevel = EnchantmentHelper.getEnchantmentLevel(runBoost, stack);
				int jumpBoostLevel = EnchantmentHelper.getEnchantmentLevel(jumpBoost, stack);
				
				if (runBoostLevel > 0)
					player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 5, runBoostLevel - 1));
				
				if (jumpBoostLevel > 0)
					player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 5, jumpBoostLevel - 1));
			}
		}
		
		if (player.getItemStackFromSlot(EntityEquipmentSlot.HEAD) != null) {
			ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
			if (stack.isItemEnchanted()) {
				int nightVisionLevel = EnchantmentHelper.getEnchantmentLevel(nightVision, stack);
				int waterBreathingLevel = EnchantmentHelper.getEnchantmentLevel(waterBreathing, stack);
				
				if (nightVisionLevel > 0)
					player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 5, 0));
				
				if (waterBreathingLevel > 0)
					player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 5, 0));	
			}
		}
	}
	
	public static int getChance(int level) {
		if (level == 1)
			return 90;
		else if (level == 2)
			return 70;
		else if (level == 3)
			return 45;
		return 100;
	}
	
	@SubscribeEvent
	public void onEntityHurt(LivingHurtEvent event) {
		/*
		 * event.getEntityLiving() = target;
		 * event.getSource().getTrueSource() = attacker;
		 * event.getAmount() = (float) amount of damage;
		 */
		if (event.getSource().getTrueSource() instanceof EntityPlayer) {
			ItemStack weapon = ((EntityPlayer)event.getSource().getTrueSource()).getHeldItemMainhand();
			if (EnchantmentHelper.getEnchantmentLevel(armorPenetration, weapon) > 0)
				event.getSource().setDamageBypassesArmor();
			
			if ((event.getEntityLiving() instanceof EntityDragon) && (EnchantmentHelper.getEnchantmentLevel(arrowDragonSlayer, weapon) > 0))
				event.setAmount(event.getAmount() + event.getAmount());
			
			if (EnchantmentHelper.getEnchantmentLevel(poisonAspect, weapon) > 0) {
				int level = EnchantmentHelper.getEnchantmentLevel(poisonAspect, weapon);
				event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.POISON, level * 20, level));
			}
		}
	}
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equalsIgnoreCase(VREnchant.MODID))
			ConfigManager.sync(VREnchant.MODID, Config.Type.INSTANCE);
	}
}
