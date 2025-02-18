package eu.mikart.paperenchant.paperenchants.enchantment.impl;

import eu.mikart.paperenchant.paperenchants.enchantment.CustomEnchant;
import eu.mikart.paperenchant.paperenchants.enchantment.EnchantRarity;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LevitationEnchant extends CustomEnchant {

    public LevitationEnchant() {
        super("levitation", Component.text("Levitation"), EnchantRarity.RARE, ItemTypeTagKeys.SWORDS);
    }

    @Override
    public void onDamage(Player player, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, 1));
        }
    }

}
