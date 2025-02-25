package eu.mikart.paperenchant.paperenchants.enchantment.impl.bow;

import eu.mikart.paperenchant.paperenchants.enchantment.CustomEnchant;
import eu.mikart.paperenchant.paperenchants.enchantment.EnchantRarity;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HoverEnchant extends CustomEnchant {

    public HoverEnchant() {
        super("hover", Component.text("Hover"), EnchantRarity.RARE, ItemTypeTagKeys.ENCHANTABLE_BOW);
    }

    @Override
    public void onArrowHit(Player attacker, Entity target, int level) {
        if (target instanceof LivingEntity livingEntity) {
            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, 1));
        }
    }

}
