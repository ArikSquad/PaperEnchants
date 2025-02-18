package eu.mikart.paperenchant.paperenchants.enchantment;

import eu.mikart.paperenchant.paperenchants.enchantment.impl.LevitationEnchant;
import lombok.Getter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class EnchantManager {
    @Getter
    private static final EnchantManager instance = new EnchantManager();

    @Getter
    private List<CustomEnchant> enchants = new ArrayList<>();

    public EnchantManager() {
        enchants.addAll(Arrays.asList(
                new LevitationEnchant()
        ));
    }

    public CustomEnchant getEnchantByName(String name) {
        for (CustomEnchant enchant : enchants) {
            if (enchant.getName().toString().equalsIgnoreCase(name)) {
                return enchant;
            }
        }
        return null;
    }


    public CustomEnchant getEnchantById(String id) {
        for (CustomEnchant enchant : enchants) {
            if (enchant.getId().equalsIgnoreCase(id)) {
                return enchant;
            }
        }
        return null;
    }

    public List<CustomEnchant> getEnchantsFromItemStack(ItemStack itemStack) {
        List<CustomEnchant> customEnchants = new ArrayList<>();
        if (itemStack != null && itemStack.hasItemMeta()) {
            for (Enchantment enchantment : itemStack.getEnchantments().keySet()) {
                CustomEnchant customEnchant = getEnchantById(enchantment.getKey().getKey());
                if (customEnchant != null) {
                    customEnchants.add(customEnchant);
                }
            }
        }
        return customEnchants;
    }

}
