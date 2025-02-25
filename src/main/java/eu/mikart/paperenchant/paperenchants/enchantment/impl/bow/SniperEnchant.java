package eu.mikart.paperenchant.paperenchants.enchantment.impl.bow;

import eu.mikart.paperenchant.paperenchants.enchantment.CustomEnchant;
import eu.mikart.paperenchant.paperenchants.enchantment.EnchantRarity;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class SniperEnchant extends CustomEnchant {

    public SniperEnchant() {
        super("sniper", Component.text("Sniper"), EnchantRarity.RARE, ItemTypeTagKeys.ENCHANTABLE_BOW);
    }

    @Override
    public void onArrowShoot(Player attacker, Entity projectile, int level) {
        projectile.setVelocity(projectile.getVelocity().multiply(1.5));
    }

}
