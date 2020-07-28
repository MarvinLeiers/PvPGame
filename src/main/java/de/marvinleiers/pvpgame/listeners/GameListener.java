package de.marvinleiers.pvpgame.listeners;

import de.marvinleiers.gameapi.events.GameStartEvent;
import de.marvinleiers.gameapi.game.Game;
import de.marvinleiers.pvpgame.PvPGame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class GameListener implements Listener
{
    @EventHandler
    public void onDamage(EntityDamageEvent event)
    {
        if (!(event.getEntity() instanceof Player))
        {
            return;
        }

        Player player = (Player) event.getEntity();

        if (PvPGame.getPlugin().getGameApi().inGame(player) && !PvPGame.getPlugin().getGameApi().gameplayers.get(player).getGame().hasStarted())
            event.setCancelled(true);
    }

    @EventHandler
    public void onGameStart(GameStartEvent event)
    {
        if (event.getPlugin() != PvPGame.getPlugin())
            return;

        Game game = event.getGame();

        game.players.get(0).teleport(PvPGame.getPlugin().getGameApi().gamesConfig.getLocation("games." + game.getName() + ".spawn-1"));
        game.players.get(1).teleport(PvPGame.getPlugin().getGameApi().gamesConfig.getLocation("games." + game.getName() + ".spawn-2"));
    }
}
