package net.xwerswoodx.vrenchant.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.chunk.Chunk.EnumCreateEntityType;

public class EnchantmentReBirth extends Enchantment {

	//TestEnchantments(Rarity.UNCOMMON, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND}, "smelting");
	public EnchantmentReBirth(Enchantment.Rarity rarity, EntityEquipmentSlot... slots) {
		super(rarity, EnumEnchantmentType.WEAPON, slots);
		this.setRegistryName("reBirth");
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
        return super.getMinEnchantability(enchantmentLevel) + 55;
    }
	
	@Override
	public String getName() {
		return "Rebirth";
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
        return stack.getItem() instanceof ItemAxe ? true : super.canApply(stack);
    }
}
