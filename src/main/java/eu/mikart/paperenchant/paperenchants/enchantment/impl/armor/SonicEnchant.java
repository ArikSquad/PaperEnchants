package eu.mikart.paperenchant.paperenchants.enchantment.impl.armor;

import eu.mikart.paperenchant.paperenchants.enchantment.CustomEnchant;
import eu.mikart.paperenchant.paperenchants.enchantment.EnchantRarity;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SonicEnchant extends CustomEnchant {
	public SonicEnchant() {
		super("sonic", Component.text("Sonic"), EnchantRarity.RARE, ItemTypeTagKeys.FOOT_ARMOR);
	}

	@Override
	public void currentlyWorn(Player player, int level) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 10, level));
	}
}