package eu.mikart.paperenchant.paperenchants.enchantment;

import eu.mikart.paperenchant.paperenchants.enchantment.impl.SoulboundEnchant;
import eu.mikart.paperenchant.paperenchants.enchantment.impl.armor.SelfDestruction;
import eu.mikart.paperenchant.paperenchants.enchantment.impl.armor.SonicEnchant;
import eu.mikart.paperenchant.paperenchants.enchantment.impl.bow.HoverEnchant;
import eu.mikart.paperenchant.paperenchants.enchantment.impl.bow.SniperEnchant;
import eu.mikart.paperenchant.paperenchants.enchantment.impl.fishing.RiverMasterEnchant;
import eu.mikart.paperenchant.paperenchants.enchantment.impl.tool.VeinminerEnchant;
import eu.mikart.paperenchant.paperenchants.enchantment.impl.weapon.ThunderEnchant;
import lombok.Getter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class EnchantManager {
    @Getter
    private static final EnchantManager instance = new EnchantManager();

    @Getter
    private List<CustomEnchant> enchants = new ArrayList<>();

    public EnchantManager() {
        enchants.addAll(Arrays.asList(
                new HoverEnchant(),
                new SonicEnchant(),
                new SniperEnchant(),
                new RiverMasterEnchant(),
                new SoulboundEnchant(),
                new SelfDestruction(),
                new ThunderEnchant(),
                new VeinminerEnchant()
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

    public Map<CustomEnchant, Integer> getEnchantsFromItemStack(ItemStack itemStack) {
        Map<CustomEnchant, Integer> customEnchants = new HashMap<>();
        if (itemStack != null && itemStack.hasItemMeta()) {
            for (Map.Entry<Enchantment, Integer> entry : itemStack.getEnchantments().entrySet()) {
                CustomEnchant customEnchant = getEnchantById(entry.getKey().getKey().getKey());
                if (customEnchant != null) {
                    customEnchants.put(customEnchant, entry.getValue());
                }
            }
        }
        return customEnchants;
    }

}
