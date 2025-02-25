package eu.mikart.paperenchant.paperenchants;

import eu.mikart.paperenchant.paperenchants.enchantment.EnchantManager;
import eu.mikart.paperenchant.paperenchants.listener.EnchantedItemListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnchantsPlugin extends JavaPlugin {

	public static EnchantsPlugin instance;

	@Override
	public void onEnable() {
		instance = this;
		getServer().getPluginManager().registerEvents(new EnchantedItemListener(), this);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, ()
				-> getServer().getOnlinePlayers().forEach(this::checkCurrentlyWornEnchantments), 0L, 5L);
	}

	@Override
	public void onDisable() {
		instance = null;
		getComponentLogger().info(
				Component.text("PaperEnchants has been disabled.").color(NamedTextColor.AQUA)
		);
	}

	/**
	 * Checks and applies the currently worn enchantments for the given player.
	 *
	 * @param player the player whose equipment is being checked
	 */
	private void checkCurrentlyWornEnchantments(Player player) {
		EnchantManager enchantManager = EnchantManager.getInstance();
		enchantManager.getEnchantsFromItemStack(player.getEquipment().getHelmet()).forEach((enchantment, level) -> {
			enchantment.currentlyWorn(player, level);
		});
		enchantManager.getEnchantsFromItemStack(player.getEquipment().getChestplate()).forEach((enchantment, level) -> {
			enchantment.currentlyWorn(player, level);
		});
		enchantManager.getEnchantsFromItemStack(player.getEquipment().getLeggings()).forEach((enchantment, level) -> {
			enchantment.currentlyWorn(player, level);
		});
		enchantManager.getEnchantsFromItemStack(player.getEquipment().getBoots()).forEach((enchantment, level) -> {
			enchantment.currentlyWorn(player, level);
		});
	}
}
