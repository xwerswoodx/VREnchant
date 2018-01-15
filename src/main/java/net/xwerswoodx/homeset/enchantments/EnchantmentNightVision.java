package net.xwerswoodx.homeset.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.chunk.Chunk.EnumCreateEntityType;

public class EnchantmentNightVision extends Enchantment {

	//TestEnchantments(Rarity.UNCOMMON, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND}, "smelting");
	public EnchantmentNightVision(Enchantment.Rarity rarity, EntityEquipmentSlot... slots) {
		super(rarity, EnumEnchantmentType.ARMOR_HEAD, slots);
		this.setRegistryName("nightVision");
	}
	
	@Override
	public int getMaxLevel() {
		return 1;
	}
	
	@Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 30 * (enchantmentLevel - 1);
    }

	@Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return super.getMinEnchantability(enchantmentLevel) + 30;
    }
	
	@Override
	public String getName() {
		return "Night Vision";
	}

	/*
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return true;
	}
	*/

	@Override
	public boolean isAllowedOnBooks() {
		return true;
	}

	@Override
    public boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench);
    }
	
	@Override
    public boolean isTreasureEnchantment() {
		return false;
    }
}
