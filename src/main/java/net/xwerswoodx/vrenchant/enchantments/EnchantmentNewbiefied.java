package net.xwerswoodx.vrenchant.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.chunk.Chunk.EnumCreateEntityType;

public class EnchantmentNewbiefied extends Enchantment {

	public EnchantmentNewbiefied(Enchantment.Rarity rarity, EntityEquipmentSlot... slots) {
		super(rarity, EnumEnchantmentType.ALL, slots);
		this.setRegistryName("newbiefied");
	}
	
	@Override
	public int getMaxLevel() {
		return 1;
	}
	
	@Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 30;
    }

	@Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return super.getMinEnchantability(enchantmentLevel) + 50;
    }
	
	@Override
	public String getName() {
		return "Newbiefied";
	}

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
	
	@Override
    public boolean canApply(ItemStack stack) {
        return super.canApply(stack);
    }
	
	@Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return super.canApplyAtEnchantingTable(stack);
    }
}
