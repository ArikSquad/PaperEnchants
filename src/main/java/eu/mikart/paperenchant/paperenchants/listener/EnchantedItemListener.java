package eu.mikart.paperenchant.paperenchants.listener;

import eu.mikart.paperenchant.paperenchants.EnchantsPlugin;
import eu.mikart.paperenchant.paperenchants.enchantment.CustomEnchant;
import eu.mikart.paperenchant.paperenchants.enchantment.EnchantManager;
import eu.mikart.paperenchant.paperenchants.enchantment.impl.tool.VeinminerEnchant;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Map;

public class EnchantedItemListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onFishing(PlayerFishEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        Map<CustomEnchant, Integer> enchants = EnchantManager.getInstance().getEnchantsFromItemStack(itemStack);
        for (Map.Entry<CustomEnchant, Integer> entry : enchants.entrySet()) {
            entry.getKey().onFish(player, entry.getValue());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity target = event.getEntity();

        if (!(damager instanceof Player player)) {
            return;
        }

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        Map<CustomEnchant, Integer> enchants = EnchantManager.getInstance().getEnchantsFromItemStack(itemStack);
        for (Map.Entry<CustomEnchant, Integer> entry : enchants.entrySet()) {
            entry.getKey().onDamage(player, target, entry.getValue());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onArrowShoot(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        ItemStack bow = event.getBow();
        Map<CustomEnchant, Integer> enchants = EnchantManager.getInstance().getEnchantsFromItemStack(bow);
        if (enchants.isEmpty()) {
            return;
        }

        for (Map.Entry<CustomEnchant, Integer> entry : enchants.entrySet()) {
            entry.getKey().onArrowShoot(player, event.getProjectile(), entry.getValue());
        }

        if (!(event.getProjectile() instanceof Arrow arrow)) {
            return;
        }

        StringBuilder enchantIds = new StringBuilder();
        for (Map.Entry<CustomEnchant, Integer> entry : enchants.entrySet()) {
            enchantIds.append(entry.getKey().getId()).append(":").append(entry.getValue()).append(",");
        }

        if (!enchantIds.isEmpty()) {
            enchantIds.deleteCharAt(enchantIds.length() - 1);
        }

        NamespacedKey key = new NamespacedKey(EnchantsPlugin.instance, "enchants");
        arrow.getPersistentDataContainer().set(key, PersistentDataType.STRING, enchantIds.toString());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onProjectileHit(ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof Arrow arrow)) {
            return;
        }

        if (!(arrow.getShooter() instanceof Player player)) {
            return;
        }

        if (event.getHitEntity() == null) {
            return;
        }

        NamespacedKey key = new NamespacedKey(EnchantsPlugin.instance, "enchants");
        if (!arrow.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
            return;
        }
        String enchantIdsString = arrow.getPersistentDataContainer().get(key, PersistentDataType.STRING);
        if (enchantIdsString == null || enchantIdsString.isEmpty()) {
            return;
        }
        String[] enchantPairs = enchantIdsString.split(",");
        for (String pair : enchantPairs) {
            String[] parts = pair.split(":");
            CustomEnchant enchant = EnchantManager.getInstance().getEnchantById(parts[0]);
            int level = Integer.parseInt(parts[1]);
            if (enchant != null) {
                enchant.onArrowHit(player, event.getHitEntity(), level);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        ItemStack[] drops = event.getDrops().toArray(new ItemStack[0]);
        for (ItemStack drop : drops) {
            Map<CustomEnchant, Integer> enchants = EnchantManager.getInstance().getEnchantsFromItemStack(drop);
            for (Map.Entry<CustomEnchant, Integer> entry : enchants.entrySet()) {
                entry.getKey().onDeath(event);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onHookLaunch(ProjectileLaunchEvent event) {
        if(!(event.getEntity() instanceof FishHook hook)) {
            return;
        }
        if (!(hook.getShooter() instanceof Player player)) {
            return;
        }
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        Map<CustomEnchant, Integer> enchants = EnchantManager.getInstance().getEnchantsFromItemStack(itemStack);
        for (Map.Entry<CustomEnchant, Integer> entry : enchants.entrySet()) {
            entry.getKey().onFishHookLaunch(player, hook, entry.getValue());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(BlockBreakEvent event) {
        if(VeinminerEnchant.isBusy()) return;

        Player player = event.getPlayer();

        if(player.isSneaking()) return;

        Block block = event.getBlock();
        ItemStack itemStack = player.getInventory().getItemInMainHand();

        if(block.getDrops(itemStack, player).isEmpty()) return;
        if(!VeinminerEnchant.set.contains(block.getType())) return;

        Map<CustomEnchant, Integer> enchants = EnchantManager.getInstance().getEnchantsFromItemStack(itemStack);
        for (Map.Entry<CustomEnchant, Integer> entry : enchants.entrySet()) {
            entry.getKey().onBlockBreak(player, block, entry.getValue());
        }
    }

}