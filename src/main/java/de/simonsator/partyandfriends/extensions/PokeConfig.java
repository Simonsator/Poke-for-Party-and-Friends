package de.simonsator.partyandfriends.extensions;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

/**
 * @author simonbrungs
 * @version 1.0.0 25.01.17
 */
public class PokeConfig extends ConfigurationCreator {

	public PokeConfig(File pFile, PAFExtension pPlugin) throws IOException {
		super(pFile, pPlugin);
		readFile();
		loadDefaults();
		saveFile();
		process(configuration);
	}

	private void loadDefaults() {
		set("Commands.Poke.Names", "poke", "pokefriend");
		set("Commands.Poke.Priority", 20);
		set("Commands.Poke.Permission", "");
		set("Messages.Help", "&8/&5friend poke [Friend] &r &8- &7Pokes the given friend");
		set("Messages.Poke", " &7You were poked by [PLAYER].");
		set("Messages.Poked", " &7You poked [PLAYER].");
	}
}
