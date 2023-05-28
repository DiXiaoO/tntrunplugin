package genmini;

import java.io.File;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.CommandMap;
import emu.grasscutter.plugin.Plugin;
import genmini.minigames.GamesBrain;
import genmini.command.GenshinCommandTntRun;

public class GenshinMiniGamesCore extends Plugin {

    private static GenshinMiniGamesCore instance;
    private GamesBrain brain;
    File Path;
    
    @Override
    public void onLoad() {
        Grasscutter.getLogger().info("[GenshinMiniGamesCore] Loading...");
        instance = this;
        Path = this.getDataFolder();   
        Grasscutter.getLogger().info("[GenshinMiniGamesCore] Loaded!");
    }

	@Override
    public void onEnable() {
		CommandMap.getInstance().registerCommand("genshinminigames", new GenshinCommandTntRun());
    	Path = this.getDataFolder();
    	brain.enable();
    }

    @Override
    public void onDisable() {
        CommandMap.getInstance().unregisterCommand("genshinminigames");
        brain.disable();
    }

    public static GenshinMiniGamesCore getInstance() {
        return instance;
    }
}
