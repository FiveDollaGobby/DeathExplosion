package com.outlook.fivedollagobby;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;


public final class DeathExplosion extends JavaPlugin implements Listener, CommandExecutor {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("deselectparticle").setExecutor(this);
        getLogger().info("DeathExplosion Plugin has been loaded successfully!");
    }


    public void openParticleSelection(Player player) {
        if (player.hasPermission("dexplode.gui.access")) {
            Inventory inv = Bukkit.createInventory(null, 27, "Select Your Particle");


            addParticleItem(inv, player, Material.TNT, Particle.EXPLOSION_HUGE, "Creates a massive explosion effect.");
            addParticleItem(inv, player, Material.BLAZE_POWDER, Particle.FLAME, "Emits a continuous stream of flames.");
            addParticleItem(inv, player, Material.COAL, Particle.SMOKE_LARGE, "Generates a large cloud of smoke.");
            addParticleItem(inv, player, Material.LAVA_BUCKET, Particle.LAVA, "Simulates a burst of lava.");
            addParticleItem(inv, player, Material.REDSTONE, Particle.HEART, "Spawns cute heart particles.");
            addParticleItem(inv, player, Material.WATER_BUCKET, Particle.WATER_DROP, "Drops clear water droplets.");
            addParticleItem(inv, player, Material.EMERALD, Particle.VILLAGER_HAPPY, "Shows happy villager green stars.");
            addParticleItem(inv, player, Material.FIREWORK_ROCKET, Particle.FIREWORKS_SPARK, "Creates sparkles from fireworks.");
            addParticleItem(inv, player, Material.ENCHANTED_BOOK, Particle.CRIT_MAGIC, "Displays magical critical effect sparks.");


            ItemStack exitButton = new ItemStack(Material.BARRIER);
            ItemMeta exitMeta = exitButton.getItemMeta();
            exitMeta.setDisplayName("Exit Menu");
            List<String> exitLore = new ArrayList<>();
            exitLore.add("Click here to close the menu.");
            exitMeta.setLore(exitLore);
            exitButton.setItemMeta(exitMeta);
            inv.setItem(26, exitButton);

            player.openInventory(inv);
        } else {
            player.sendMessage("You do not have permission to open this GUI.");
        }
    }

    private void addParticleItem(Inventory inv, Player player, Material material, Particle particle, String loreDescription) {
        if (player.hasPermission("dexplode.explode." + particle.name())) {
            ItemStack item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(particle.name());
            List<String> lore = new ArrayList<>();
            lore.add(loreDescription);
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.addItem(item);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("deselectparticle") && sender instanceof Player) {
            openParticleSelection((Player) sender);
            return true;
        }
        return false;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Select Your Particle") && event.getWhoClicked() instanceof Player) {
            event.setCancelled(true);
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null && clickedItem.hasItemMeta()) {
                Player player = (Player) event.getWhoClicked();
                String itemName = clickedItem.getItemMeta().getDisplayName();
                if (itemName.equals("Exit Menu")) {
                    player.closeInventory();
                } else {
                    handleParticleSelection(player, clickedItem);
                }
            }
        }
    }

    private void handleParticleSelection(Player player, ItemStack clickedItem) {
        String particleName = clickedItem.getItemMeta().getDisplayName();
        if (player.hasPermission("dexplode.explode." + particleName)) {
            getConfig().set("players." + player.getUniqueId() + ".particle", particleName);
            saveConfig();
            player.sendMessage("Your explosion particle has been set to " + particleName);
            player.closeInventory();
        } else {
            player.sendMessage("You do not have permission to use this particle type.");
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (player.hasPermission("dexplode.explode")) {
            Location loc = player.getLocation();
            World world = loc.getWorld();
            double power = getConfig().getDouble("explosionPower");
            boolean breakBlocks = getConfig().getBoolean("breakBlocks");
            int particleAmount = getConfig().getInt("particleAmount", 100);

            // Particle Spawning Code
            String particleName = getConfig().getString("players." + player.getUniqueId().toString() + ".particle", getConfig().getString("defaultParticle"));
            if (player.hasPermission("dexplode.explode." + particleName)) {
                world.createExplosion(loc, (float) power, false, breakBlocks);
                Particle particle = Particle.valueOf(particleName);
                world.spawnParticle(particle, loc, particleAmount);
            }
        }
    }




}
