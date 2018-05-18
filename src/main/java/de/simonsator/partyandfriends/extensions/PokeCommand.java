package de.simonsator.partyandfriends.extensions;

import de.simonsator.partyandfriends.api.friends.abstractcommands.FriendSubCommand;
import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.main.Main;
import de.simonsator.partyandfriends.utilities.PatterCollection;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.config.Configuration;

import java.util.List;
import java.util.regex.Matcher;

/**
 * @author simonbrungs
 * @version 1.0.0 25.01.17
 */
public class PokeCommand extends FriendSubCommand {
	private final Matcher POKE_MESSAGE;
	private final Matcher POKED_MESSAGE;

	protected PokeCommand(List<String> pCommands, int pPriority, String pHelp, Configuration pMessages, String pPermission) {
		super(pCommands, pPriority, pHelp, pPermission);
		POKE_MESSAGE = PatterCollection.PLAYER_PATTERN.matcher(pMessages.getString("Messages.Poke"));
		POKED_MESSAGE = PatterCollection.PLAYER_PATTERN.matcher(pMessages.getString("Messages.Poked"));
	}

	@Override
	public void onCommand(OnlinePAFPlayer pPlayer, String[] args) {
		if (!isPlayerGiven(pPlayer, args))
			return;
		PAFPlayer playerQuery = PAFPlayerManager.getInstance().getPlayer(args[1]);
		if (!isPlayerOnline(pPlayer, playerQuery))
			return;
		if (!isAFriendOf(pPlayer, playerQuery, args))
			return;
		if (!allowsWriteTo(pPlayer, playerQuery))
			return;
		pPlayer.sendMessage(PREFIX + POKED_MESSAGE.replaceFirst(playerQuery.getDisplayName()));
		playerQuery.sendMessage(PREFIX + POKE_MESSAGE.replaceFirst(pPlayer.getDisplayName()));
	}

	private boolean isPlayerOnline(OnlinePAFPlayer pSender, PAFPlayer pQueryPlayer) {
		if (!pQueryPlayer.isOnline()) {
			sendError(pSender, "Friends.General.PlayerIsOffline");
			return false;
		}
		return true;
	}

	private boolean allowsWriteTo(OnlinePAFPlayer pPlayer, PAFPlayer pQueryPlayer) {
		if (pQueryPlayer.getSettingsWorth(2) == 1) {
			sendError(pPlayer, new TextComponent(PREFIX
					+ Main.getInstance().getMessages().getString("Friends.Command.MSG.CanNotWriteToHim")));
			return false;
		}
		return true;
	}

}
