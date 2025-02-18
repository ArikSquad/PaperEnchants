package eu.mikart.paperenchant.paperenchants.enchantment;

import io.papermc.paper.registry.tag.TagKey;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.key.KeyPattern;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemType;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class CustomEnchant implements ICustomEnchant {
    @KeyPattern.Value private final @NotNull String id;
    private final Component name;
    private final EnchantRarity rarity;
    @Setter
    private int maxLevel = 3;
    @Setter
    private int weight = 1;
    public EquipmentSlotGroup slotGroups = EquipmentSlotGroup.ANY;
    private TagKey<ItemType> tagKey;

    public CustomEnchant(@KeyPattern.Value final @NotNull String id, Component name, EnchantRarity rarity, TagKey<ItemType> tagKey) {
        this.id = id;
        this.name = name;
        this.rarity = rarity;
        this.tagKey = tagKey;
    }

}
