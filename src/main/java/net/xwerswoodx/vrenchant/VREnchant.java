package net.xwerswoodx.vrenchant;

import javax.annotation.Nullable;

import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = VREnchant.MODID, version = VREnchant.VERSION)
public class VREnchant
{
    public static final String MODID = "vrenchant";
    public static final String VERSION = "0.1";
    
    public static boolean rootsMod = false;
    public static boolean babyMobsMod = false;
    
    @Config.Comment("Test Comment")
    @Config.Name("Test")
    public static boolean test = false;
    
    
    @EventHandler
    public void onPreInitialization(FMLPreInitializationEvent event) {
        rootsMod = Loader.isModLoaded("roots"); //Roots 2 support.
        if (rootsMod)
        	System.out.println("Roots 2 Module hooked on VREnchant.");
        babyMobsMod = Loader.isModLoaded("babymobs"); //Baby Mobs support.
        if (babyMobsMod)
        	System.out.println("Baby Mobs Module hooked on VREnchant.");
        MinecraftForge.EVENT_BUS.register(new EnchantmentEvent());
    }
    
    @EventHandler
    public void onInitialization(FMLInitializationEvent event) {
    	EntityEnums.modRegister();
    }
    
	public static String getTypeFromEntity(TileEntityMobSpawner entity) {
		if (!entity.getUpdateTag().hasKey("SpawnData"))
			return "minecraft:pig";
		else if (!entity.getUpdateTag().getCompoundTag("SpawnData").hasKey("id"))
			return "minecraft:pig";
		
		String tag = entity.getUpdateTag().getCompoundTag("SpawnData").getString("id");
		return tag;
	}
	
    @Nullable
    public static ResourceLocation getNamedIdFromString(String type) {
    	ResourceLocation loc = new ResourceLocation(type);
    	return loc;
    	//return new ResourceLocation(type);
    }
    
    /*
    public boolean checkFreeSlot(EntityPlayer player, ItemStack stack) {
    	if (player.inventory.getFirstEmptyStack() >= 0)
    		return true;
    	else if (stack.getMaxStackSize() > 1) {
    		for (int slot = 0; slot < player.inventory.getSizeInventory(); slot++) {
    			if (player.inventory.getStackInSlot(slot).getItem() == stack.getItem() && player.inventory.getStackInSlot(slot).getMetadata() == stack.getMetadata() && player.inventory.getStackInSlot(slot).getCount() + stack.getCount() <= stack.getMaxStackSize())
    				return true;
    		}
    	}
    	return false;
    }
    */
}
