package genmini.minigames;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.props.FightProperty;
import emu.grasscutter.game.world.Scene;

import emu.grasscutter.game.entity.*;
import emu.grasscutter.utils.Position;

public final class GenshinTntRun {
    private Timer minigamesTimer;
    private Timer minigameStarter;
    public static ArrayList<Integer> othersgadget = new ArrayList<Integer>();
    public static ArrayList<Integer> tnts = new ArrayList<Integer>(900);
    public static ArrayList<Integer> del_tnts = new ArrayList<Integer>(900);
    public static Map<Integer, Position> oldpositon = new HashMap<Integer, Position>();
    public static Map<Integer, Integer> oldscene = new HashMap<Integer, Integer>();
    public int TestInt = 0;
    public static Player sender;
    public static ArrayList<Player> Gamers = new ArrayList<Player>();
    public static ArrayList<Player> del_Gamers = new ArrayList<Player>();
    public static Player Last_player;
    public int onoff = 0;
    public int timer5sec = 16;
    
    private class minigamesTimerTask extends TimerTask {
		@Override
        public void run() {
        	if(onoff == 0) {
        		Gamers = new ArrayList<Player>();
        		tnts = new ArrayList<Integer>(1500);
        		othersgadget = new ArrayList<Integer>();
        		oldpositon = new HashMap<Integer, Position>();
        		oldscene = new HashMap<Integer, Integer>();
        		sender.dropMessage("Загрузка");
        		GameLoader(sender);
        		sender.dropMessage("Всё");
    			sender.getWorld().getPlayers().forEach((player) -> {
    				Gamers.add(player);
    			});
        		playerstp();
            	enable_start();
            	onoff = 1;
        	}
        	if(onoff == 2) {
        		onoff = 3;
	        	Gamers.forEach((player) -> {
	        		if(player.getPos().getY()>1000.5) {
	        			if(player.getPos().getY()<1001.6) {
	        				tnts.forEach((tnt_id) -> {
	        					GameEntity tnt_box = player.getScene().getEntityById(tnt_id);
	        					if (tnt_box != null) {
	        						if(Math.abs(tnt_box.getPosition().getX()-player.getPos().getX())<0.85) { 
			            				if(Math.abs(tnt_box.getPosition().getZ()-player.getPos().getZ())<0.85) {
			            					del_tnts.add(tnt_id);
				            			}
			            			}
			            		}
	        					else {
	        						del_tnts.add(tnt_id);
	        					}
	        				});
		        		}
	        		}
	        		else {
	        			del_Gamers.add(player);
	        	        if (oldpositon.containsKey(player.getUid()))
	        	        {
	        	        	player.getWorld().transferPlayerToScene(player, oldscene.get(player.getUid()), oldpositon.get(player.getUid()));
	        	        }
	        			Last_player = player;
	        		}
	            }); 	
	        	del_tnts.forEach((tnt_id) -> {
        			if (sender.getScene().getEntities().containsKey(tnt_id)){
						try {
							deltnt(sender.getScene(),sender.getScene().getEntityById(tnt_id));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

    				}
	        		tnts.remove(tnt_id);
                }); 
	        	
	        	del_Gamers.forEach((player) -> {
	        		Gamers.remove(player);
                }); 
	        	
	        	del_tnts = new ArrayList<Integer>(900);
	        	del_Gamers = new ArrayList<Player>();
	        	
	        	if (Gamers.size() < 1) {
        			disable();
	        	}
	        	else {
	        		onoff = 2;
	        	}
	        }
    	}
    };
    
    private class minigameStarterTimerTask extends TimerTask {
        @Override
        public void run() {
        	if(timer5sec > 0) {
        		timer5sec = timer5sec - 1;
        		sender.dropMessage("[TntRun] "+timer5sec);
        	}
        	else {
        		onoff = 2;
        		disable_start();
        	}
        }
    };

    
    public void playerstp() {
    	Gamers.forEach((player) -> {
    		player.dropMessage("[TntRun] Участвуют:");
        	Gamers.forEach((player_list) -> {
        		player.dropMessage(player_list.getNickname());
        		player.dropMessage(player_list.getWorld().getLevelEntityId());
        	});    
        	oldpositon.put(player.getUid(), player.getPos().clone());
        	oldscene.put(player.getUid(), player.getSceneId());
    		Random rand = new Random();
    	    Integer randomElement = tnts.get(rand.nextInt(tnts.size()));
    		Position pos = new Position((float)player.getScene().getEntityById(randomElement).getPosition().getX(),(float)1005,(float)player.getScene().getEntityById(randomElement).getPosition().getZ());
    		player.getWorld().transferPlayerToScene(player, sender.getSceneId(), pos);
        });        
    }
    
    public void deltnt(Scene scene,GameEntity entity) throws InterruptedException{
    	GamesEntityKill killer = new GamesEntityKill();
    	killer.deltnt(scene, entity);
    }
    
	public static void GameLoader (Player player){
		Scene scene = player.getScene();
        float x = 0;
        float y = 1000;
        float z = 0;
        Position pos;
        float rot_x = 0;
        float rot_y = 0;
        float rot_z = 0;
        Position rot;
        
    	pos = new Position((float) (x+20.25-1.5), y, (float) (z-1.5));
        rot = new Position(rot_x, 0, rot_z);
        othersgadget.add(CreateGadget(player,scene,70950150,pos, rot));
    	pos = new Position((float) (x+20.25-1.5), y, (float) (z+39));
        rot = new Position(rot_x, 0, rot_z);
        othersgadget.add(CreateGadget(player,scene,70950150,pos, rot));
    	pos = new Position((float) (x-1.5), y, (float) (z+20.25-1.5));
        rot = new Position(rot_x, 90, rot_z);
        othersgadget.add(CreateGadget(player,scene,70950150,pos, rot));
    	pos = new Position((float) (x+39), y, (float) (z+20.25-1.5));
        rot = new Position(rot_x, 90, rot_z);
        othersgadget.add(CreateGadget(player,scene,70950150,pos, rot));
        
        for (int i_x = 0; i_x < 26; i_x++) {
        	for (int i_z = 0; i_z < 26; i_z++) {
        		var random = new SecureRandom();
	        	pos = new Position((float) (x+(i_x*1.5)), y, (float) (z+(i_z*1.5)));
	        	rot = new Position(rot_x, 90*random.nextInt(3), rot_z);
	        	tnts.add(CreateGadget(player,scene,70310099,pos, rot)); //

        	}
        }

        
        for (int i_x = 0; i_x < 17; i_x++) {
        	for (int i_z = 0; i_z < 17; i_z++) {
        		if(i_x == 0 ||i_x == 1 || i_x == 15|| i_x == 16) {
		        	pos = new Position((float) (x+0.75-6+(i_x*3)), y, (float) (z+0.75-6+(i_z*3)));
		            rot = new Position(rot_x, rot_y, rot_z);
		        	othersgadget.add(CreateGadget(player,scene,70310039,pos, rot));
	        	}
        		else{
        			if(i_z == 0 ||i_z == 1 || i_z == 15|| i_z == 16) {
			        	pos = new Position((float) (x+0.75-6+(i_x*3)), y, (float) (z+0.75-6+(i_z*3)));
			            rot = new Position(rot_x, rot_y, rot_z);
			        	othersgadget.add(CreateGadget(player,scene,70310039,pos, rot));
		        	}
        		}
        	}
        }
	}
    
    
    
    private static Integer CreateGadget (Player player, Scene scene,int id, Position pos, Position rot){
    	
    	GameEntity entity = new EntityVehicle(scene, player.getSession().getPlayer(), id, 0, pos, rot);  // TODO: does targetPlayer.getSession().getPlayer() have some meaning?
        switch (id) {
            // TODO: Not hardcode this. Waverider (skiff)
            case 45001001, 45001002 -> {
                entity.addFightProperty(FightProperty.FIGHT_PROP_BASE_HP, 10000);
                entity.addFightProperty(FightProperty.FIGHT_PROP_BASE_ATTACK, 100);
                entity.addFightProperty(FightProperty.FIGHT_PROP_CUR_ATTACK, 100);
                entity.addFightProperty(FightProperty.FIGHT_PROP_CUR_HP, 10000);
                entity.addFightProperty(FightProperty.FIGHT_PROP_CUR_DEFENSE, 0);
                entity.addFightProperty(FightProperty.FIGHT_PROP_CUR_SPEED, 0);
                entity.addFightProperty(FightProperty.FIGHT_PROP_CHARGE_EFFICIENCY, 0);
                entity.addFightProperty(FightProperty.FIGHT_PROP_MAX_HP, 10000);
            }
            default -> {
            entity.addFightProperty(FightProperty.FIGHT_PROP_BASE_HP, 10000);
            entity.addFightProperty(FightProperty.FIGHT_PROP_BASE_ATTACK, 100);
            entity.addFightProperty(FightProperty.FIGHT_PROP_CUR_ATTACK, 100);
            entity.addFightProperty(FightProperty.FIGHT_PROP_CUR_HP, 10000);
            entity.addFightProperty(FightProperty.FIGHT_PROP_CUR_DEFENSE, 0);
            entity.addFightProperty(FightProperty.FIGHT_PROP_CUR_SPEED, 0);
            entity.addFightProperty(FightProperty.FIGHT_PROP_CHARGE_EFFICIENCY, 0);
            entity.addFightProperty(FightProperty.FIGHT_PROP_MAX_HP, 10000);
            }
        }
        scene.addEntity(entity);
        return entity.getId();
    }
    
    public void enable(int interval, Player player) {
    	sender = player;
        minigamesTimer = new Timer();
        minigamesTimer.schedule(new minigamesTimerTask(),0, interval);
    }

    public void disable() {
        minigamesTimer.cancel();
		tnts.forEach((tnt_id) -> {
			if (sender.getScene().getEntities().containsKey(tnt_id)){
				sender.getScene().killEntity(sender.getScene().getEntityById(tnt_id), tnt_id);
			}
        }); 
		othersgadget.forEach((id_gadget) -> {
			if (sender.getScene().getEntities().containsKey(id_gadget)){
				sender.getScene().killEntity(sender.getScene().getEntityById(id_gadget), id_gadget);
			}
        }); 
		
		Gamers.forEach((player) -> {
			player.getWorld().transferPlayerToScene(player, oldscene.get(player.getUid()), oldpositon.get(player.getUid()));
		});
		
		sender.getWorld().getPlayers().forEach((player) -> {
			if (Last_player == null) {
				player.dropMessage("[TntRun] Победил человек загадка");
			}
			else {
				player.dropMessage("[TntRun] Победил "+Last_player.getNickname());
			}
		});
		GamesBrain.loadgames.remove(sender.getWorld().getLevelEntityId());
    }
    
    public void enable_start() {
        minigameStarter = new Timer();
        minigameStarter.schedule(new minigameStarterTimerTask(),0, 1000);
    }

    public void disable_start() {
        minigameStarter.cancel();
    }

}
