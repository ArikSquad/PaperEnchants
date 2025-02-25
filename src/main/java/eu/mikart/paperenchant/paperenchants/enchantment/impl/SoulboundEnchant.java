package eu.mikart.paperenchant.paperenchants.enchantment.impl;

import eu.mikart.paperenchant.paperenchants.EnchantsPlugin;
import eu.mikart.paperenchant.paperenchants.enchantment.CustomEnchant;
import eu.mikart.paperenchant.paperenchants.enchantment.EnchantManager;
import eu.mikart.paperenchant.paperenchants.enchantment.EnchantRarity;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SoulboundEnchant extends CustomEnchant {
	public SoulboundEnchant() {
		super("soulbound", Component.text("Soulbound"), 1, EnchantRarity.VERY_RARE, ItemTypeTagKeys.ENCHANTABLE_ARMOR);
	}

	@Override
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getPlayer();
		if (event.getKeepInventory()) return;
		List<ItemStack> itemsToSave = new ArrayList<>();
		Location location = player.getLocation();
		World world = player.getWorld();

		event.getDrops().removeIf(drop -> {
			if (EnchantManager.getInstance().getEnchantsFromItemStack(drop).containsKey(SoulboundEnchant.class)) {
				itemsToSave.add(drop);
				return true;
			}
			return false;
		});

		if (itemsToSave.isEmpty()) return;

		EnchantsPlugin.instance.getServer().getScheduler().runTask(EnchantsPlugin.instance, task -> {
			itemsToSave.forEach(item -> {
				if (player.getInventory().firstEmpty() == -1) {
					world.dropItemNaturally(location, item);
				} else {
					player.getInventory().addItem(item);
				}
			});
		});
	}
}