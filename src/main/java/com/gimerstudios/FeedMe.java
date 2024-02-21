package com.gimerstudios;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bstats.bukkit.Metrics;

public final class FeedMe extends JavaPlugin implements Listener {

    private boolean feedingCooldown = false;
    @Override
    public void onEnable() {
        int pluginId = 21074;
        Metrics metrics = new Metrics(this, pluginId);
        getLogger().info("Thanks for choosing Gimer Studios. It means a lot to me!");
        getLogger().info("Feed Me Plugin has been enabled.");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Thanks for choosing Gimer Studios. It means a lot to me!");
        getLogger().info("Feed Me Plugin has been disabled.");
    }
    @EventHandler
    public void onPlayerFeed(PlayerInteractEntityEvent event) {
        Player feeder = event.getPlayer();
        if (!(event.getRightClicked() instanceof Player))
            return;

        Player receiver = (Player) event.getRightClicked();
        ItemStack itemInHand = feeder.getInventory().getItemInMainHand();

        // Check if the player is on cooldown.
        if (feedingCooldown) {
            feeder.sendMessage("You are on cooldown.");
            return;
        }

        // Check if the item is food and is not harmful.
        if (isFood(itemInHand.getType()) && !isHarmfulFood(itemInHand.getType())) {
            // Check if the other player is hungry.
            if (receiver.getFoodLevel() < 20) {
                // Give feeding effect.
                int foodLevel = getFoodLevel(itemInHand.getType());
                int saturation = getSaturationLevel(itemInHand.getType());

                receiver.setFoodLevel(Math.min(receiver.getFoodLevel() + foodLevel, 20));
                receiver.setSaturation(Math.min(receiver.getSaturation() + saturation, receiver.getFoodLevel()));
                receiver.sendMessage("You have been fed by " + feeder.getName());
                // If it's a golden apple add whatever effects.
                if (itemInHand.getType() == Material.GOLDEN_APPLE) {
                    receiver.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 10, 1));
                    receiver.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20 * 60, 2));
                }
                // Remove one item from the feeder's hand
                itemInHand.setAmount(itemInHand.getAmount() - 1);
                // Start cooldown
                startCooldown();
            } else {
                feeder.sendMessage(receiver.getName() + " is not hungry!");
            }
        } else {
            feeder.sendMessage("You can't feed " + receiver.getName() + " that!");
        }
    }

    private boolean isFood(Material material) {
        return material.isEdible();
    }

    private boolean isHarmfulFood(Material material) {
        switch (material) {
            case ROTTEN_FLESH:
            case POISONOUS_POTATO:
            case PUFFERFISH:
            case SPIDER_EYE:
            case CHICKEN:
                return true;
            default:
                return false;
        }
    }

    private int getFoodLevel(Material material) {
        switch (material) {
            case APPLE:
                return 4;
            case BAKED_POTATO:
                return 5;
            case COOKED_PORKCHOP:
                return 12;
            case BREAD:
                return 5;
            case CARROT:
                return 3;
            case CHORUS_FRUIT:
                return 4;
            case COOKED_BEEF:
                return 8;
            case COOKED_CHICKEN:
                return 6;
            case COOKED_COD:
                return 5;
            case COOKED_MUTTON:
                return 6;
            case COOKED_RABBIT:
                return 5;
            case COOKED_SALMON:
                return 6;
            case COOKIE:
                return 2;
            case DRIED_KELP:
                return 1;
            case GOLDEN_CARROT:
                return 6;
            case ENCHANTED_GOLDEN_APPLE:
                return 4;
            case GOLDEN_APPLE:
                return 4;
            case GLOW_BERRIES:
                return 2;
            case HONEY_BOTTLE:
                return 6;
            case MELON_SLICE:
                return 2;
            case MUSHROOM_STEW:
                return 6;
            case POTATO:
                return 1;
            case PUFFERFISH:
                return 1;
            case PUMPKIN_PIE:
                return 8;
            case RABBIT_STEW:
                return 10;
            case ROTTEN_FLESH:
                return 4;
            case SALMON:
                return 2;
            case SPIDER_EYE:
                return 2;
            case BEEF:
                return 8;
            case SUSPICIOUS_STEW:
                return 6;
            case SWEET_BERRIES:
                return 2;
            case TROPICAL_FISH:
                return 1;
            case BEETROOT_SOUP:
                return 6;
            case PORKCHOP:
                return 3;
            case MUTTON:
                return 2;
            default:
                return 0;
        }
    }

    private int getSaturationLevel(Material material) {
        switch (material) {
            case APPLE:
                return 2;
            case BAKED_POTATO:
                return 6;
            case COOKED_PORKCHOP:
                return 12;
            case BREAD:
                return 6;
            case CARROT:
                return 3;
            case CHORUS_FRUIT:
                return 2;
            case COOKED_BEEF:
                return 9;
            case COOKED_CHICKEN:
                return 6;
            case COOKED_COD:
                return 6;
            case COOKED_MUTTON:
                return 6;
            case COOKED_RABBIT:
                return 6;
            case COOKED_SALMON:
                return 9;
            case COOKIE:
                return 2;
            case DRIED_KELP:
                return (int) 0.6;
            case GOLDEN_CARROT:
                return 14;
            case ENCHANTED_GOLDEN_APPLE:
                return 18;
            case GOLDEN_APPLE:
                return (int) 9.6;
            case GLOW_BERRIES:
                return 2;
            case HONEY_BOTTLE:
                return 6;
            case MELON_SLICE:
                return 2;
            case MUSHROOM_STEW:
                return (int) 7.2;
            case POTATO:
                return (int) 0.6;
            case PUFFERFISH:
                return (int) 1.2;
            case PUMPKIN_PIE:
                return (int) 4.8;
            case RABBIT_STEW:
                return 12;
            case ROTTEN_FLESH:
                return 4;
            case SALMON:
                return 6;
            case SPIDER_EYE:
                return (int) 3.2;
            case BEEF:
                return 12;
            case SUSPICIOUS_STEW:
                return 12;
            case SWEET_BERRIES:
                return 2;
            case TROPICAL_FISH:
                return (int) 1.2;
            case BEETROOT_SOUP:
                return 7;
            case PORKCHOP:
                return (int) 1.2;
            case MUTTON:
                return (int) 1.2;
            default:
                return 0;
        }
    }


    private void startCooldown() {
        feedingCooldown = true;
        new BukkitRunnable() {
            @Override
            public void run() {
                feedingCooldown = false;
            }
        }.runTaskLater(this, 20 * 5); // 5 seconds cooldown
    }
}
