package net.xwerswoodx.vrenchant;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.text.TextFormatting;

public enum HeadEnums {
	blaze,
	cavespider("Cave Spider", "MHF_CaveSpider"),
	chicken,
	cow,
	elderguardian("Elder Guardian", "ElderGuardian"),
	enderman,
	endermite,
	evoker,
	ghast,
	guardian,
	irongolem("Iron Golem", "MHF_Golem"),
	magmacube("Magma Cube", "MHF_LavaSlime"),
	mooshroom("Mooshroom", "MHF_MushroomCow"),
	ocelot,
	parrot,
	pig,
	rabbit,	
	sheep,
	shulker,
	silverfish,
	slime,
	snowgolem("Snow Golem", "SnowGolem"),
	spider,
	squid,
	stray,
	vex,
	villager,
	witch,
	wither,
	wolf,
	zombiepigman("Zombie Pigman", "MHF_PigZombie");
	
	private String nbt;
	private String name;
	
	HeadEnums(String name, String nbt) {
		this.name = name;
		this.nbt = nbt;
	}
	
	HeadEnums(String name) {
		this.name = "null";
		this.nbt = "MHF";
	}
	
	HeadEnums() {
		this.name = "null";
		this.nbt = "MHF";
	}
	
	public static boolean hasHead(String name) {
		for (HeadEnums head : HeadEnums.values()) {
			if (head.toString().equalsIgnoreCase(name))
				return true;
		}
		return false;
	}
	
	public static String getHead(String name) {
		for (HeadEnums head : HeadEnums.values()) {
			if (head.toString().equalsIgnoreCase(name)) {
				if (!head.nbt.equalsIgnoreCase("mhf"))
					return head.nbt;
			}
		}
		return "MHF_" + name;
	}
	
	public static String getName(Entity entity) {
		String name = entity.getName().toLowerCase().replace(" ", "");

		for (HeadEnums head : HeadEnums.values()) {
			if (head.toString().equalsIgnoreCase(name)) {
				if (!head.name.equalsIgnoreCase("null"))
					return head.name;
			}
		}
		return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase() + " Skull";
	}
	
	public static ItemStack getItem(Entity entity) {
		int type = -1;
		String name = entity.getName().toLowerCase().replace(" ", "");
		
		if (entity instanceof EntitySkeleton)
			type = 0;
		else if (entity instanceof EntityWitherSkeleton)
			type = 1;
		else if (entity instanceof EntityZombie)
			type = 2;
		else if (entity instanceof EntityPlayer)
			type = 3;
		else if (entity instanceof EntityCreeper)
			type = 4;
		else if (hasHead(name)) {
			type = 3;
			name = getHead(name);
		}
		
		if (type >= 0) {
			ItemStack skull = new ItemStack(Items.SKULL, 1, type);
			NBTTagCompound tag = skull.hasTagCompound() ? skull.getTagCompound() : new NBTTagCompound();
			tag.setTag("SkullOwner", new NBTTagString(name));
			skull.setTagCompound(tag);
			if ((type == 3) && (!(entity instanceof EntityPlayer)))
				skull.setStackDisplayName(TextFormatting.RESET + getName(entity));
			return skull;
		}
		return new ItemStack(Items.AIR);
	}
}