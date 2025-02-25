package eu.mikart.paperenchant.paperenchants.enchantment.impl.weapon;

import eu.mikart.paperenchant.paperenchants.enchantment.CustomEnchant;
import eu.mikart.paperenchant.paperenchants.enchantment.EnchantRarity;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class ThunderEnchant extends CustomEnchant {

	public ThunderEnchant() {
		super("thunder", Component.text("Thunder"), 1, EnchantRarity.VERY_RARE, ItemTypeTagKeys.ENCHANTABLE_WEAPON);
	}

	@Override
	public void onDamage(Player attacker, Entity target, int level) {
		if (target instanceof LivingEntity livingEntity) {
			livingEntity.getWorld().strikeLightning(target.getLocation());
		}
	}

}
