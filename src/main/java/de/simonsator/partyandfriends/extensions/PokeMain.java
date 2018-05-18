package de.simonsator.partyandfriends.extensions;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.friends.commands.Friends;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * @author Simonsator
 * @version 1.0.0 25.01.17
 */
public class PokeMain extends PAFExtension {

	@Override
	public void onEnable() {
		try {
			Configuration config = new PokeConfig(new File(getConfigFolder(), "config.yml"), this).getCreatedConfiguration();
			Friends.getInstance().addCommand(new PokeCommand(config.getStringList("Commands.Poke.Names"), config.getInt("Commands.Poke.Priority"), config.getString("Messages.Help"), config, config.getString("Commands.Poke.Permission")));
			registerAsExtension();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
