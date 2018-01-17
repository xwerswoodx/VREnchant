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
	blaze(3, new int[] {120, 100, 80}),
	cavespider(3, new int[] {120, 100, 80}, "Cave Spider", "MHF_CaveSpider"),
	chicken(3, new int[] {100, 80, 60}),
	creeper(4, new int[] {80, 60, 40}),
	cow(3, new int[] {100, 80, 60}),
	elderguardian(3, new int[] {6, 4, 2}, "Elder Guardian", "ElderGuardian"),
	enderman(3, new int[] {70, 50, 30}),
	endermite(3, new int[] {50, 40, 30}),
	evoker(3, new int[] {50, 40, 30}),
	ghast(3, new int[] {30, 20, 10}),
	guardian(3, new int[] {80, 60, 40}),
	irongolem(3, new int[] {120, 100, 80}, "Iron Golem", "MHF_Golem"),
	magmacube(3, new int[] {80, 60, 40}, "Magma Cube", "MHF_LavaSlime"),
	mooshroom(3, new int[] {120, 100, 80}, "Mooshroom", "MHF_MushroomCow"),
	ocelot(3, new int[] {100, 80, 60}),
	parrot(3, new int[] {120, 100, 80}),
	pig(3, new int[] {120, 100, 80}),
	player(3, new int[] {30, 20, 10}),
	rabbit(3, new int[] {100, 80, 60}),
	sheep(3, new int[] {120, 100, 80}),
	shulker(3, new int[] {80, 60, 40}),
	skeleton(0, new int[] {80, 60, 40}),
	silverfish(3, new int[] {40, 30, 20}),
	slime(3, new int[] {150, 125, 100}),
	snowgolem(3, new int[] {120, 100, 80}, "Snow Golem", "SnowGolem"),
	spider(3, new int[] {120, 100, 80}),
	squid(3, new int[] {120, 100, 80}),
	stray(3, new int[] {80, 60, 40}),
	vex(3, new int[] {80, 60, 40}),
	villager(3, new int[] {140, 120, 100}),
	witch(3, new int[] {80, 60, 40}),
	wither(3, new int[] {30, 20, 10}),
	witherskeleton(1, new int[] {100, 90, 80}),
	wolf(3, new int[] {120, 100, 80}),
	zombie(2, new int[] {120, 100, 80}),
	zombiepigman(3, new int[] {80, 60, 40}, "Zombie Pigman", "MHF_PigZombie");
	
	private int type;
	private int[] chance = new int[3];
	private String nbt;
	private String name;
	
	HeadEnums(int type, int[] chance, String name, String nbt) {
		this.type = type;
		this.chance = chance;
		this.name = name;
		this.nbt = nbt;
	}
	
	HeadEnums(int type, int[] chance, String name) {
		this.type = type;
		this.chance = chance;
		this.name = "null";
		this.nbt = "MHF";
	}
	
	HeadEnums(int type, int[] chance) {
		this.type = type;
		this.chance = chance;
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
	
	/*
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
	*/
	
	public static ItemStack getItem(Entity entity) {
		ItemStack skull = new ItemStack(Items.AIR);
		String name = entity.getName().toLowerCase().replace(" ", "");
		int type = -1;
		
		if (entity instanceof EntityPlayer) {
			type = HeadEnums.player.type;
		} else if (hasHead(name)) {
			for (HeadEnums head : HeadEnums.values()) {
				if (head.toString().equalsIgnoreCase(name))
					type = head.type;
			}
		}
		
		
		if (type >= 0) {
			skull = new ItemStack(Items.SKULL, 1, type);
			NBTTagCompound tag = skull.hasTagCompound() ? skull.getTagCompound() : new NBTTagCompound();
			tag.setTag("SkullOwner", new NBTTagString(name));
			skull.setTagCompound(tag);
			if ((type == 3) && (!(entity instanceof EntityPlayer)))
				skull.setStackDisplayName(TextFormatting.RESET + getName(entity));
		}
		
		return skull;
	}
	
	public static int getChance(Entity entity, int level) {
		String name = entity.getName().toLowerCase().replace(" ", "");
		if (entity instanceof EntityPlayer)
			return HeadEnums.player.chance[level - 1];
		
		for (HeadEnums head : HeadEnums.values()) {
			if (head.toString().equalsIgnoreCase(name))
				return head.chance[level -1];
		}
		return 0;
	}
}