package net.xwerswoodx.homeset.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.chunk.Chunk.EnumCreateEntityType;

public class EnchantmentAutoJump extends Enchantment {

	//TestEnchantments(Rarity.UNCOMMON, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND}, "smelting");
	public EnchantmentAutoJump(Enchantment.Rarity rarity, EntityEquipmentSlot... slots) {
		super(rarity, EnumEnchantmentType.ARMOR_FEET, slots);
		this.setRegistryName("autoJump");
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
		return "Auto Jump";
	}

	/*
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return stack.getItem() instanceof ItemArmor ? true : false;
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
