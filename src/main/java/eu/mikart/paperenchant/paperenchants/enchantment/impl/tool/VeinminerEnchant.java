package eu.mikart.paperenchant.paperenchants.enchantment.impl.tool;

import eu.mikart.paperenchant.paperenchants.enchantment.CustomEnchant;
import eu.mikart.paperenchant.paperenchants.enchantment.EnchantRarity;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VeinminerEnchant extends CustomEnchant {

	public static Set<Material> set = new HashSet<>();

	static {
		set.addAll(Tag.COAL_ORES.getValues());
		set.addAll(Tag.COPPER_ORES.getValues());
		set.addAll(Tag.DIAMOND_ORES.getValues());
		set.addAll(Tag.EMERALD_ORES.getValues());
		set.addAll(Tag.GOLD_ORES.getValues());
		set.addAll(Tag.IRON_ORES.getValues());
		set.addAll(Tag.LAPIS_ORES.getValues());
		set.addAll(Tag.REDSTONE_ORES.getValues());
		set.add(Material.NETHER_GOLD_ORE);
		set.add(Material.NETHER_QUARTZ_ORE);
	}

	private static boolean busyBreak;

	private static final BlockFace[] AREA = {
			BlockFace.UP, BlockFace.DOWN, BlockFace.EAST,
			BlockFace.WEST, BlockFace.SOUTH, BlockFace.NORTH
	};

	public VeinminerEnchant() {
		super("veinminer", Component.text("Veinminer"), 4, EnchantRarity.RARE, ItemTypeTagKeys.PICKAXES);
	}

	public int getBlocksLimit(int level) {
		return 4 + (2 * level);
	}

	@NotNull
	private Set<Block> getNearby(@NotNull Block block) {
		return Stream.of(AREA).map(block::getRelative)
				.filter(blockAdded -> blockAdded.getType() == block.getType()).collect(Collectors.toSet());
	}

	public static boolean isBusyByOthers() {
		return false;
	}

	public static boolean isBusyByEnchant() {
		return busyBreak;
	}

	public static boolean isBusy() {
		return isBusyByEnchant() || isBusyByOthers();
	}

	public static void busyBreak(@NotNull Player player, @NotNull Block block) {
		busyBreak = true;
		player.breakBlock(block);
		busyBreak = false;
	}

	public static void safeBusyBreak(@NotNull Player player, @NotNull Block block) {
		if (!isBusy()) {
			busyBreak(player, block);
		}
	}

	private void vein(@NotNull Player player, @NotNull Block source, int level) {
		Set<Block> ores = new HashSet<>();
		Set<Block> prepare = new HashSet<>(this.getNearby(source));

		int limit = Math.min(this.getBlocksLimit(level), 30);
		if (limit < 0) return;

		while (ores.addAll(prepare) && ores.size() < limit) {
			Set<Block> nearby = new HashSet<>();
			prepare.forEach(prepared -> nearby.addAll(this.getNearby(prepared)));
			prepare.clear();
			prepare.addAll(nearby);
		}
		ores.remove(source);
		ores.forEach(ore -> safeBusyBreak(player, ore));
	}

	@Override
	public void onBlockBreak(@NotNull Player player, @NotNull Block source, int level) {
		this.vein(player, source, level);
	}

}
