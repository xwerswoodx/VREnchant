package net.xwerswoodx.vrenchant;

import java.util.Random;

import javax.swing.text.html.HTML.Tag;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentAutoJump;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentAutoRun;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentBeheading;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentDragonSlayer;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentLifeSteal;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentLucky;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentNightVision;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentReBirth;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentSilkSpawners;
import net.xwerswoodx.vrenchant.enchantments.EnchantmentWaterBreathing;

public class EnchantmentEvent {
	
	private EntityEquipmentSlot[] slotsArmor = new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};
	private EntityEquipmentSlot[] slotsHand = new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND};
	public Enchantment autoRun = new EnchantmentAutoRun(Enchantment.Rarity.VERY_RARE, slotsArmor);
	public Enchantment autoJump = new EnchantmentAutoJump(Enchantment.Rarity.VERY_RARE, slotsArmor);
	public Enchantment nightVision = new EnchantmentNightVision(Enchantment.Rarity.RARE, slotsArmor);
	public Enchantment waterBreathing = new EnchantmentWaterBreathing(Enchantment.Rarity.VERY_RARE, slotsArmor);
	public Enchantment beheading = new EnchantmentBeheading(Enchantment.Rarity.RARE, slotsHand);
	public Enchantment lucky = new EnchantmentLucky(Enchantment.Rarity.VERY_RARE, slotsHand);
	public Enchantment reBirth = new EnchantmentReBirth(Enchantment.Rarity.VERY_RARE, slotsHand);
	public Enchantment silkSpawner = new EnchantmentSilkSpawners(Enchantment.Rarity.VERY_RARE, slotsHand);
	public Enchantment lifeSteal = new EnchantmentLifeSteal(Enchantment.Rarity.VERY_RARE, slotsHand);
	public Enchantment dragonSlayer = new EnchantmentDragonSlayer(Enchantment.Rarity.VERY_RARE, slotsHand);
	
	@SubscribeEvent
	public void onEnchantmentRegister(RegistryEvent.Register<Enchantment> event) {
		IForgeRegistry<Enchantment> registry = event.getRegistry();
		registry.register(autoRun);
		registry.register(autoJump);
		registry.register(nightVision);
		registry.register(waterBreathing);
		registry.register(beheading);
		registry.register(lucky);
		registry.register(reBirth);
		registry.register(silkSpawner);
		registry.register(lifeSteal);
		registry.register(dragonSlayer);
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
			        egg.setStackDisplayName(TextFormatting.RESET + "Monster Spawner [" + name + "]");
			       	event.getPlayer().dropItem(egg, true);
	    		}
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
    				
    				if (beheadingLevel > 0) {
    					int random = rand.nextInt(beheadingLevel);
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
	
	/*
	@SubscribeEvent
	public void onEquip(LivingEquipmentChangeEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.getEntity();
			if ((event.getSlot() == EntityEquipmentSlot.FEET) && (event.getTo().isItemEnchanted())) {
				ItemStack stack = event.getTo();
				int autoRunLevel = EnchantmentHelper.getEnchantmentLevel(autoRun, stack);
				if (autoRunLevel > 0)
					player.addPotionEffect(new PotionEffect(Potion.getPotionById(1)));
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
				int autoRunLevel = EnchantmentHelper.getEnchantmentLevel(autoRun, stack);
				int autoJumpLevel = EnchantmentHelper.getEnchantmentLevel(autoJump, stack);
				
				if (autoRunLevel > 0)
					player.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 5, autoRunLevel - 1));
				
				if (autoJumpLevel > 0)
					player.addPotionEffect(new PotionEffect(Potion.getPotionById(8), 5, autoJumpLevel - 1));
			}
		}
		
		if (player.getItemStackFromSlot(EntityEquipmentSlot.HEAD) != null) {
			ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
			if (stack.isItemEnchanted()) {
				int nightVisionLevel = EnchantmentHelper.getEnchantmentLevel(nightVision, stack);
				int waterBreathingLevel = EnchantmentHelper.getEnchantmentLevel(waterBreathing, stack);
				
				if (nightVisionLevel > 0)
					player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 5, 0));
				
				if (waterBreathingLevel > 0)
					player.addPotionEffect(new PotionEffect(Potion.getPotionById(13), 5, 0));	
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
}
