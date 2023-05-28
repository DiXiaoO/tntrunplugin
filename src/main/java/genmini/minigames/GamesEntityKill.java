package genmini.minigames;

import java.util.Timer;
import java.util.TimerTask;


import emu.grasscutter.game.world.Scene;

import emu.grasscutter.game.entity.*;

public final class GamesEntityKill {
    private Timer minigamesTimer;
    private int Timer = 2;
    private Scene scene;
    private GameEntity entity;
    
    private class minigamesTimerTask extends TimerTask {
        @Override
        public void run() {
        	Timer = Timer - 1;
        	if (Timer == 0) {
        		scene.killEntity(entity, 1);
        		
        		disable();
        	}
        }
    }
    
    
    public void deltnt(Scene scenenew,GameEntity entitynew) {
    	scene = scenenew;
    	entity = entitynew;
        minigamesTimer = new Timer();
        minigamesTimer.schedule(new minigamesTimerTask(),0, 250);
    }
    
    public void disable() {
        minigamesTimer.cancel();
    }
    
}
