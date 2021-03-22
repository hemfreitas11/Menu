package me.bkrmt.bkcore.menu;

import me.bkrmt.bkcore.BkPlugin;
import me.bkrmt.bkcore.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class MenuListener implements Listener {

    BkPlugin plugin;
    String titleKey;

    public MenuListener(BkPlugin plugin, String titleKey) {
        this.plugin = plugin;
        this.titleKey = titleKey;
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (Utils.cleanName(event.getView().getTitle()).equalsIgnoreCase(Utils.cleanName(plugin.getLangFile().get(titleKey)))) {
            cancel();
        }
    }

    public void cancel() {
        HandlerList.unregisterAll(this);
    }
}
