package com.github.gobotan;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TNT extends JavaPlugin implements Listener {

	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("TNTdeleteプラグインが正常に動作しました。");

		// config.ymlが存在しない場合はファイルに出力します。
		saveDefaultConfig();
	}


	@EventHandler
	public void a(BlockPlaceEvent e) {
		if (e.getBlock().getType() == Material.TNT) {
		e.setBuild(false);
		//コンフィグファイルロード
		FileConfiguration config = getConfig();
		config.getString("Message");

		String Message = config.getString("Message");

		Bukkit.getServer().broadcastMessage(ChatColor.BLUE + e.getPlayer().getName() + Message);
		}
	}

	@EventHandler
	public void a(ExplosionPrimeEvent e) {
		if (e.getEntity().getType() == EntityType.PRIMED_TNT) {

			e.setCancelled(true);

		}
		if (e.getEntity().getType() == EntityType.MINECART_TNT) {

			e.setCancelled(true);
		}
			//コンフィグファイルロード
			FileConfiguration config = getConfig();
			config.getString("Message2");

			String Message2 = config.getString("Message2");

			Bukkit.getServer().broadcastMessage(ChatColor.RED + Message2);

			}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		reloadConfig();
		// config.ymlが存在しなかったら出力
		saveDefaultConfig();
		sender.sendMessage(ChatColor.GRAY + "設定ファイルがリロードされました。");

		return false;
	}

}




