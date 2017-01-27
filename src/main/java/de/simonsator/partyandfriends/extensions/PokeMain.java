package de.simonsator.partyandfriends.extensions;

import de.simonsator.partyandfriends.friends.commands.Friends;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * @author Simonsator
 * @version 1.0.0 25.01.17
 */
public class PokeMain extends Plugin {

	@Override
	public void onEnable() {
		try {
			Configuration config = new PokeConfig(new File(getDataFolder(), "config.yml")).getCreatedConfiguration();
			Friends.getInstance().addCommand(new PokeCommand(config.getStringList("Commands.Poke.Names").toArray(new String[0]), config.getInt("Commands.Poke.Priority"), config.getString("Messages.Help"), config));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
