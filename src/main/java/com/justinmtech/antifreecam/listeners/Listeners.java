package com.justinmtech.antifreecam.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Listeners implements Listener {
    private List<Material> materials;

    public Listeners() {
        setInventoryBlocks();
    }

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = e.getClickedBlock();
            if (isInventoryBlock(block)) {

                Player player = e.getPlayer();
                if (!blockAccessible(player, block)) {
                    e.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "Freecamming is not allowed!");
                }
            }
        }
    }

    private boolean blockAccessible(Player player, Block block) {
        //max dist set to 5 to account for latency
        List<Block> blocks = player.getLineOfSight((Set<Material>) null, 5);
        int blocksSize = blocks.size();
        for (Block b : blocks) {
            if (b.equals(block)) {
                return true;
            }
        }
        return false;
    }

    private void setInventoryBlocks() {
        materials = new ArrayList<>();
        materials.add(Material.CHEST);
        materials.add(Material.TRAPPED_CHEST);
        materials.add(Material.HOPPER);
        materials.add(Material.FURNACE);
        materials.add(Material.ANVIL);
        materials.add(Material.DISPENSER);
    }

    private boolean isInventoryBlock(Block block) {
        for (Material material : materials) {
            if (material.equals(block.getType())) {
                return true;
            }
        }
        return false;
    }

}
