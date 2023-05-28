package genmini.command;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;
import genmini.minigames.GamesBrain;

import java.util.*;


@Command(label = "tntrun", usage = "tntrun <Start|Stop>",
        description = "GenshinMiniGamesore command", aliases = {"tntrun"}, permission = "minigame.tntrun")

public class GenshinCommandTntRun implements CommandHandler {
	
	@Override
    public void execute(Player sender, Player targetPlayer, List<String> args) {
        if (args.size() < 1) {
        	sender.dropMessage("tntrun <Start|Stop>");
            return;
        }
        String action = args.get(0);
        switch (action) {
	        default:
	        	sender.dropMessage("tntrun <Start|Stop>");
	            return;
	        case "Start":
	        	GamesBrain.startgame(sender,targetPlayer, "TntRun");
	        	return;
	        case "Stop":
	        	GamesBrain.stopgame(sender,targetPlayer, "TntRun");
	        	return;
        }
	}
	
}
