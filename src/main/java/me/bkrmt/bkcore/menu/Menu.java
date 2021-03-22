package me.bkrmt.bkcore.menu;

import me.bkrmt.bkcore.BkPlugin;
import me.bkrmt.bkcore.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class Menu {

    private final Inventory menu;
    private final String cleanTitle;
    private MenuListener listener;
    BkPlugin plugin;

    public Menu(BkPlugin plugin, String titleKey) {
        this.plugin = plugin;
        String title = plugin.getLangFile().get(titleKey);
        cleanTitle = Utils.cleanName(title);
        menu = plugin.getServer().createInventory(null, 54, title);
    }

    public MenuListener getListener() {
        return listener;
    }

    public void setListener(MenuListener listener) {
        this.listener = listener;
    }

    public boolean isValidClick(InventoryClickEvent event) {
        String inventoryName = ChatColor.stripColor(event.getView().getTitle()).trim();
        boolean returnValue = false;
        if (Utils.cleanName(inventoryName).equalsIgnoreCase(getCleanTitle())) {
            event.setCancelled(true);
            if (event.getSlotType().equals(InventoryType.SlotType.CONTAINER)) {
                ItemStack button = event.getCurrentItem();
                if (button != null && !button.getType().equals(Material.AIR)) {
                    returnValue = true;
                }
            }
        }
        return returnValue;
    }

    public String getCleanTitle() {
        return cleanTitle;
    }

    public Inventory getMenu() {
        return menu;
    }

    public void openMenu(CommandSender player) {
        ((Player) player).openInventory(menu);
    }

    public void setOnCoordinate(ItemStack item, int line, int colum) {
        Utils.setOnCoordinate(getMenu(), item, line, colum);
    }
}
