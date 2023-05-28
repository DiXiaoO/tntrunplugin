package genmini.minigames;

import emu.grasscutter.game.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public final class GamesBrain {
    private Timer builderTimer;
    public static Map<Integer, GenshinTntRun> loadgames = new HashMap<Integer, GenshinTntRun>();

    
    private class builderTimerTask extends TimerTask {
        @Override
        public void run() {
            
        }
    };

    
    public void enable() {
        this.builderTimer = new Timer();
        this.builderTimer.schedule(new builderTimerTask(),0, 999);
    }

    public void disable() {
        this.builderTimer.cancel();
    }
    
    public static void startgame(Player sender, Player targetPlayer, String gametype) {
    	if (loadgames.containsKey(targetPlayer.getWorld().getLevelEntityId()))
    	{
    		sender.dropMessage("tntrun ��� ����������");
    	}
    	else 
    	{
    		GenshinTntRun tntrunner = new GenshinTntRun();
    		tntrunner.enable(1, targetPlayer);
        	loadgames.put(targetPlayer.getWorld().getLevelEntityId(), tntrunner);
        	sender.dropMessage("tntrun �����");
    	}
    }
    
    public static void stopgame(Player sender, Player targetPlayer, String gametype) {
    	if (loadgames.containsKey(targetPlayer.getWorld().getLevelEntityId()))
    	{
    		GenshinTntRun tntrunner = loadgames.get(targetPlayer.getWorld().getLevelEntityId());
    		tntrunner.disable();
    		tntrunner.disable_start();
    		sender.dropMessage("tntrun ����������");
    	}
    	else 
    	{
    		sender.dropMessage("tntrun �� �����");
    	}
    }

}