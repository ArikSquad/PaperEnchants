package eu.mikart.paperenchant.paperenchants.enchantment;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

public interface ICustomEnchant {
    default void onFish(Player player, int level) {}
    default void onDamage(Player attacker, Entity target, int level) {}
    default void onArrowHit(Player attacker, Entity target, int level) {}
    default void onArrowShoot(Player player, Entity projectile, int level) {}
    default void onDeath(PlayerDeathEvent event) {}
    default void onFishHookLaunch(Player player, FishHook fishHook, int level) {}
    default void onBlockBreak(Player player, Block source, int level) {}

    // This method will be called every 5 seconds if the player has the enchantment equipped
    default void currentlyWorn(Player player, int level) {}
}
