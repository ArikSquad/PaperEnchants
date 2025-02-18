package eu.mikart.paperenchant.paperenchants;

import eu.mikart.paperenchant.paperenchants.listener.EnchantedItemListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnchantsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EnchantedItemListener(), this);
    }

    @Override
    public void onDisable() {
        getComponentLogger().info(
                Component.text("PaperEnchants has been disabled.").color(NamedTextColor.AQUA)
        );
    }
}
