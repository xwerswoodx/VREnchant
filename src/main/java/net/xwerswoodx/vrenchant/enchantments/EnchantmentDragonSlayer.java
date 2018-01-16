package net.xwerswoodx.vrenchant.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.chunk.Chunk.EnumCreateEntityType;

public class EnchantmentDragonSlayer extends Enchantment {

	public EnchantmentDragonSlayer(Enchantment.Rarity rarity, EntityEquipmentSlot... slots) {
		super(rarity, EnumEnchantmentType.BOW, slots);
		this.setRegistryName("dragonSlayer");
	}
	
	@Override
	public int getMaxLevel() {
		return 5;
	}
	
	@Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 15 + 30 * (enchantmentLevel - 1);
    }

	@Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return super.getMinEnchantability(enchantmentLevel) + 70;
    }
	
	@Override
	public String getName() {
		return "Dragon Slayer";
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
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {
        if (target instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase)target;

            if (entity instanceof EntityDragon)
            	entity.attackEntityFrom(entity.getLastDamageSource(), ((float)0.5*level));
        }
    }
}
