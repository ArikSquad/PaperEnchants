package eu.mikart.paperenchant.paperenchants;

import eu.mikart.paperenchant.paperenchants.enchantment.CustomEnchant;
import eu.mikart.paperenchant.paperenchants.enchantment.EnchantManager;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.event.RegistryEvents;
import io.papermc.paper.registry.keys.EnchantmentKeys;
import net.kyori.adventure.key.Key;

@SuppressWarnings("UnstableApiUsage")
public class EnchantsBootstrap implements PluginBootstrap {

    @Override
    public void bootstrap(BootstrapContext context) {
        context.getLogger().info("PaperEnchants bootstrapper is running.");
        context.getLifecycleManager().registerEventHandler(RegistryEvents.ENCHANTMENT.freeze().newHandler(event -> {
            for (CustomEnchant enchant : EnchantManager.getInstance().getEnchants()) {
				context.getLogger().info("Registering enchantment: {}", enchant.getId());
                event.registry().register(
                        EnchantmentKeys.create(Key.key("paperenchants", enchant.getId())),
                        b -> b.description(enchant.getName())
                                .supportedItems(event.getOrCreateTag(enchant.getTagKey()))
                                .anvilCost(enchant.getWeight())
                                .maxLevel(enchant.getMaxLevel())
                                .weight(enchant.getWeight())
                                .minimumCost(EnchantmentRegistryEntry.EnchantmentCost.of(1, 1))
                                .maximumCost(EnchantmentRegistryEntry.EnchantmentCost.of(3, 1))
                                .activeSlots(enchant.getSlotGroups())
                );
            }
        }));
    }
}