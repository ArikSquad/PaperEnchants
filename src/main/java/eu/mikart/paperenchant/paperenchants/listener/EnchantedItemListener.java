package eu.mikart.paperenchant.paperenchants.listener;

import eu.mikart.paperenchant.paperenchants.enchantment.CustomEnchant;
import eu.mikart.paperenchant.paperenchants.enchantment.EnchantManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EnchantedItemListener implements Listener {

    @EventHandler
    public void onFishing(PlayerFishEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        List<CustomEnchant> enchants = EnchantManager.getInstance().getEnchantsFromItemStack(itemStack);
        for (CustomEnchant enchant : enchants) {
            enchant.onFish(player);
        }
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity target = event.getEntity();

        if (!(damager instanceof Player player)) {
            return;
        }

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        List<CustomEnchant> enchants = EnchantManager.getInstance().getEnchantsFromItemStack(itemStack);
        for (CustomEnchant enchant : enchants) {
            enchant.onDamage(player, target);
        }
    }
}
