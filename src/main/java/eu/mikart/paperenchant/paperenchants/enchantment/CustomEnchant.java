package eu.mikart.paperenchant.paperenchants.enchantment;

import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.tag.TagKey;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.key.KeyPattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemType;
import org.jetbrains.annotations.NotNull;

@Getter
@SuppressWarnings("UnstableApiUsage")
public abstract class CustomEnchant implements ICustomEnchant {
	@KeyPattern.Value
	private final @NotNull String id;
	private final Component name;
	private final EnchantRarity rarity;
	private final int maxLevel;
	@Setter
	private int weight = 1;
	public EquipmentSlotGroup slotGroups = EquipmentSlotGroup.ANY;
	private TagKey<ItemType> itemTagKey;
	private final TypedKey<Enchantment>[] conflicts;

	@SafeVarargs
	public CustomEnchant(@KeyPattern.Value final @NotNull String id, Component name, EnchantRarity rarity, int maxLevel, TagKey<ItemType> tagKey, TypedKey<Enchantment>... conflictKeys) {
		Component nameComponent;
		this.id = id;
		nameComponent = name;
		this.rarity = rarity;
		this.itemTagKey = tagKey;
		this.conflicts = conflictKeys;
		this.maxLevel = maxLevel;

		if (rarity == EnchantRarity.COMMON) {
			nameComponent = nameComponent.color(NamedTextColor.GRAY);
		} else if (rarity == EnchantRarity.UNCOMMON) {
			nameComponent = nameComponent.color(NamedTextColor.GREEN);
		} else if (rarity == EnchantRarity.RARE) {
			nameComponent = nameComponent.color(NamedTextColor.YELLOW);
		} else if (rarity == EnchantRarity.VERY_RARE) {
			nameComponent = nameComponent.color(NamedTextColor.LIGHT_PURPLE);
		}

		this.name = nameComponent;
	}

	public CustomEnchant(@KeyPattern.Value final @NotNull String id, Component name, int maxLevel, EnchantRarity rarity, TagKey<ItemType> tagKey) {
		this(id, name, rarity, maxLevel, tagKey, (TypedKey<Enchantment>[]) null);
	}

	public CustomEnchant(@KeyPattern.Value final @NotNull String id, Component name, EnchantRarity rarity, TagKey<ItemType> tagKey) {
		this(id, name, rarity, 3, tagKey, (TypedKey<Enchantment>[]) null);
	}

}
