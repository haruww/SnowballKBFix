package me.haru301.snowballkbfix;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class PluginEventHandler implements Listener
{

    @EventHandler
    public void onEntityDamageEntity(ProjectileHitEvent event) {
        final double verticalModifier = SnowballKBFix.config.getDouble("kbModifier.vertical");
        final double horizontalModifier = SnowballKBFix.config.getDouble("kbModifier.horizontal");
        if (event.getEntityType() == EntityType.SNOWBALL && event.getHitEntity() != null && event.getHitEntity().getType() == EntityType.PLAYER)
        {
            Bukkit.getScheduler().scheduleSyncDelayedTask(SnowballKBFix.plugin, () -> {
                final Player player = (Player) event.getHitEntity();
                final Vector plrV = player.getVelocity();
                final Vector velocity = new Vector(plrV.getX() * horizontalModifier, plrV.getY() * verticalModifier, plrV.getZ() * horizontalModifier);
                player.setVelocity(velocity);
            }, 0L);

        }
    }
}
