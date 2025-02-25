package eu.mikart.paperenchant.paperenchants.enchantment.impl.armor;

import eu.mikart.paperenchant.paperenchants.enchantment.CustomEnchant;
import eu.mikart.paperenchant.paperenchants.enchantment.EnchantRarity;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

public class SelfDestruction extends CustomEnchant {

	public SelfDestruction() {
		super("self_destruction", Component.text("Self Destruction"), 1, EnchantRarity.VERY_RARE, ItemTypeTagKeys.CHEST_ARMOR);
	}

	@Override
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getPlayer();
		player.getWorld().createExplosion(player.getLocation(), 4.0f, true, true, player);
	}

}
