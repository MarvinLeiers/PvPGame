package de.marvinleiers.pvpgame;

import de.marvinleiers.gameapi.game.Game;
import org.bukkit.entity.Player;

public class Challenge
{
    private boolean accepted;
    private Player challenger;
    private Player challenged;

    public Challenge(Player challenger, Player challenged)
    {
        this.accepted = false;
        this.challenger = challenger;
        this.challenged = challenged;

        challenger.sendMessage("§7Du hast §2" + challenged.getName() + " §7herausgefordert.");
        challenged.sendMessage("§2" + challenger.getName() + " §7möchte dich zu einem Kampf herausfordern! Benutze §2/challenge acccept §7, um den Kampf anzunehmen.");
    }

    public void accept()
    {
        this.accepted = true;

        for (Game game : PvPGame.getPlugin().getGameApi().games)
        {
            if (!game.hasStarted())
            {
                game.join(getChallenged());
                game.join(getChallenger());
                return;
            }
        }

        getChallenged().sendMessage("§eZurzeit ist leider keine Arena verfügbar!");
        getChallenger().sendMessage("§eZurzeit ist leider keine Arena verfügbar!");
    }

    public boolean isAccepted()
    {
        return accepted;
    }

    public Player getChallenger()
    {
        return challenger;
    }

    public Player getChallenged()
    {
        return challenged;
    }
}
