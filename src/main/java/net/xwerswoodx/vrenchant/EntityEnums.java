package net.xwerswoodx.vrenchant;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public enum EntityEnums {
	bat,
	blaze,
	cave_spider,
	chicken,
	cow,
	creeper,
	donkey,
	elder_guardian,
	enderman,
	endermite,
	evoker,
	ghast,
	guardian,
	horse,
	husk,
	iron_golem,
	llama,
	magma_cube,
	mooshroom,
	mule,
	ocelot,
	parrot,
	pig,
	polar_bear,
	rabbit,	
	sheep,
	shulker,
	silverfish,
	skeleton,
	skeleton_horse,
	slime,
	spider,
	squid,
	stray,
	vex,
	villager,
	vindicator,
	witch,
	wither_skeleton,
	wolf,
	zombie,
	zombie_horse,
	zombie_pigman,
	zombie_villager,
	
	//Roots module!
	deer("roots"),
	sprout("roots"),
	fairy("roots"),
	
	//BabyMobs module!
	babyspider("babymobs", "Baby Spider"),
	babyskeleton("babymobs", "Baby Skeleton"),
	babycreeper("babymobs", "Baby Creeper"),
	babywitherskeleton("babymobs", "Baby Wither Skeleton"),
	babyenderman("babymobs", "Baby Enderman"),
	babyblaze("babymobs", "Baby Blaze"),
	babywitch("babymobs", "Baby Witch"),
	babyguardian("babymobs", "Baby Guardian"),
	babysquid("babymobs", "Baby Squid"),
	babycavespider("babymobs", "Baby Cave Spider"),
	babyzombie("babymobs", "Baby Zombie"),
	babypigzombie("babymobs", "Baby Zombie Pigman"),
	babyzombiepigman("babymobs", "Baby Zombie Pigman", "babypigzombie"),
	babyghast("babymobs", "Baby Ghast"),
	babysnowman("babymobs", "Baby Snowman"),
	babyirongolem("babymobs", "Baby Iron Golem"),
	babywither("babymobs", "Baby Wither"),
	babyshulker("babymobs", "Baby Shulker"),
//	babydragon("babymobs", "Baby Dragon", "dragonegg"),
	
	zombiepig("babymobs", "Zombie Pig"),
	cat("babymobs", "Baby Ocelot", "babyocelot"),
	zombiechicken("babymobs", "Zombie Chicken");
	
	private String loc;
	private String name;
	private String key;
	
	EntityEnums() {
		this.loc = "minecraft";
		this.name = "null";
		this.key = "null";
	}
	
	EntityEnums(String loc) {
		this.loc = loc;
		this.name = "null";
		this.key = "null";
	}
	
	EntityEnums(String loc, String name) {
		this.loc = loc;
		this.name = name;
		this.key = "null";
	}
	
	EntityEnums(String loc, String name, String key) {
		this.loc = loc;
		this.name = name;
		this.key = key;
	}
	
	/*
	public static boolean isVanillaMob(String name) {
		for (EntityEnums e : EntityEnums.values()) {
			if (e.toString().equalsIgnoreCase(name))
				return e.loc.equalsIgnoreCase("minecraft");
		}
		return false;
	}
	
	public static boolean isRootsMob(String name) {
		for (EntityEnums e : EntityEnums.values()) {
			if (e.toString().equalsIgnoreCase(name))
				return e.loc.equalsIgnoreCase("roots");
		}
		return false;
	}
	
	public static boolean isBabyMob(String name) {
		for (EntityEnums e: EntityEnums.values()) {
			if (e.toString().equalsIgnoreCase(name))
				return e.loc.equalsIgnoreCase("babymobs");
		}
		return false;
	}
	*/
	
	public static boolean isModuleMob(String name, String module) {
		for (EntityEnums e : EntityEnums.values()) {
			if (e.toString().equalsIgnoreCase(name))
				return e.loc.equalsIgnoreCase(module);
		}
		return false;
	}
	
	public static String getLocation(Entity entity) {
		String name = getLocation(entity, "_");
		if (name.equalsIgnoreCase("null"))
			name = getLocation(entity, "");
		
		if (!name.equalsIgnoreCase("null")) {
			for (EntityEnums e : EntityEnums.values()) {
				if (e.toString().equalsIgnoreCase(name))
					return e.loc + ":" + (e.key.equalsIgnoreCase("null") ? e.toString() : e.key);
			}
		}
		return "minecraft:pig";
		
		/*
		String name = entity.getName().toLowerCase().replace(" ", "_");
		boolean cont = false;
		if (isVanillaMob(name))
			cont = true;
		else if ((isRootsMob(name)) && (VREnchant.rootsMod))
			cont = true;
		else if ((isBabyMob(name)) && (VREnchant.babyMobsMod))
			cont = true;
		*/
	}
	
	public static String getLocation(Entity entity, String key) {
		String name = entity.getName().toLowerCase().replace(" ", key);
//		if (isVanillaMob(name))
		if (isModuleMob(name ,"minecraft"))
			return name;
//		else if ((isRootsMob(name)) && (VREnchant.rootsMod))
		else if ((isModuleMob(name, "roots")) && (VREnchant.rootsMod))
			return name;
//		else if ((isBabyMob(name)) && (VREnchant.babyMobsMod))
		else if ((isModuleMob(name, "babymobs")) && (VREnchant.babyMobsMod))
			return name;
		return "null";
	}
	
	/*
	public static String getName(String curName) {
		String name = getNameString(curName);
		name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		if (name.contains("_")) {
			String[] fixer = name.split("_");
			//name = fixer[0];
			for (int i = 1; i < fixer.length; i++)
				name = name + " " + fixer[i].substring(0, 1).toUpperCase() + fixer[i].substring(1).toLowerCase();
		}
		return name;
	}
	*/
	
	public static String getName(String curName) {
		curName = curName.substring(0, 1).toUpperCase() + curName.substring(1).toLowerCase();
		for (EntityEnums e : EntityEnums.values()) {
			if (e.toString().equalsIgnoreCase(curName)) {
				if (e.name.equalsIgnoreCase("null")) {
					String name = curName;
					if (name.contains("_")) {
						String[] nameFix = name.split("_");
						name = nameFix[0];
						for (int i = 1; i < nameFix.length; i++)
							name = name + " " + nameFix[i].substring(0, 1).toUpperCase() + nameFix[i].substring(1).toLowerCase();
						return name;
					}
				} else
					return e.name;
			}
		}
		return curName;
	}
	
	/*
 	public static String getNameString(String name) {
		for (EntityEnums e : EntityEnums.values()) {
			if (e.toString().equalsIgnoreCase(name)) {
				if (e.name.equalsIgnoreCase("null"))
					return name;
				else
					return e.name;
			}
		}
		return name;
	}
	*/
	
	public static void modRegister() {
		if (VREnchant.babyMobsMod) {
			EntityRegistry.registerEgg(new ResourceLocation("babymobs:zombiepig"), 220*65536 + 171*256 + 163, 243*65536 + 227*256 + 224);
			EntityRegistry.registerEgg(new ResourceLocation("babymobs:zombiechicken"), 28*65536 + 108*256 + 13, 224*65536 + 251*256 + 219);
			EntityRegistry.registerEgg(new ResourceLocation("babymobs:babyocelot"), 42*65536 + 42*256 + 42, 229*65536 + 229*256 + 229);
		}
		EntityRegistry.registerEgg(new ResourceLocation("minecraft:villager_golem"), 136*65536 + 136*256 + 136, 235*65536 + 235*256 + 235);
		EntityRegistry.registerEgg(new ResourceLocation("minecraft:snowman"), 255*65536 + 255*256 + 255, 235*65536 + 235*256 + 235);
	}
}
