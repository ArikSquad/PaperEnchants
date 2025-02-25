package eu.mikart.paperenchant.paperenchants.enchantment.impl.fishing;

import eu.mikart.paperenchant.paperenchants.enchantment.CustomEnchant;
import eu.mikart.paperenchant.paperenchants.enchantment.EnchantRarity;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;

public class RiverMasterEnchant extends CustomEnchant {

	public RiverMasterEnchant() {
		super("river_master", Component.text("River Master"), 1, EnchantRarity.VERY_RARE, ItemTypeTagKeys.ENCHANTABLE_FISHING);
	}

	@Override
	public void onFishHookLaunch(Player player, FishHook hook, int level) {
		hook.setVelocity(hook.getVelocity().multiply(1.5));
	}

}
