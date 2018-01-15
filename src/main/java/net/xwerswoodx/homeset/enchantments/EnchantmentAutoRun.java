package net.xwerswoodx.homeset.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.chunk.Chunk.EnumCreateEntityType;

public class EnchantmentAutoRun extends Enchantment {

	//TestEnchantments(Rarity.UNCOMMON, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND}, "smelting");
	public EnchantmentAutoRun(Enchantment.Rarity rarity, EntityEquipmentSlot... slots) {
		super(rarity, EnumEnchantmentType.ARMOR_FEET, slots);
		this.setRegistryName("autoRun");
	}
	
	@Override
	public int getMaxLevel() {
		return 3;
	}
	
	@Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 20 * (enchantmentLevel - 1);
    }

	@Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return super.getMinEnchantability(enchantmentLevel) + 30;
    }
	
	@Override
	public String getName() {
		return "Auto Run";
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
        return super.canApplyTogether(ench) && ench != Enchantments.FROST_WALKER;
    }
	
	@Override
    public boolean isTreasureEnchantment() {
		return false;
    }
}
