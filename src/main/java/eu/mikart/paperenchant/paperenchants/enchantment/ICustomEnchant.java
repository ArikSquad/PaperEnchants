package eu.mikart.paperenchant.paperenchants.enchantment;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public interface ICustomEnchant {
    default void onFish(Player player) {}
    default void onDamage(Player player, Entity entity) {}
}
