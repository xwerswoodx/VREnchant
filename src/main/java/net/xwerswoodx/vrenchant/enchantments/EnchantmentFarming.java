package net.xwerswoodx.vrenchant.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.chunk.Chunk.EnumCreateEntityType;

public class EnchantmentFarming extends Enchantment {

	//TestEnchantments(Rarity.UNCOMMON, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND}, "smelting");
	public EnchantmentFarming(Enchantment.Rarity rarity, EntityEquipmentSlot... slots) {
		super(rarity, EnumEnchantmentType.BREAKABLE, slots);
		this.setRegistryName("farming");
	}
	
	@Override
	public int getMaxLevel() {
		return 3;
	}
	
	@Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 10 + 25 * (enchantmentLevel - 1);
    }

	@Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return super.getMinEnchantability(enchantmentLevel) + 50;
    }
	
	@Override
	public String getName() {
		return "Farming";
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
        return stack.getItem() instanceof ItemHoe ? true : super.canApply(stack);
    }
	
	@Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return stack.getItem() instanceof ItemHoe ? true : super.canApplyAtEnchantingTable(stack);
    }
}
