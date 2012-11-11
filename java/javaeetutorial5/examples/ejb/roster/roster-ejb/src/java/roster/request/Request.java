/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package roster.request;

import java.util.List;
import javax.ejb.Remote;
import roster.util.LeagueDetails;
import roster.util.PlayerDetails;
import roster.util.TeamDetails;


@Remote
public interface Request {
    void addPlayer(
        String playerId,
        String teamId);

    void createLeague(LeagueDetails leagueDetails);

    void createPlayer(
        String id,
        String name,
        String position,
        double salary);

    void createTeamInLeague(
        TeamDetails teamDetails,
        String leagueId);

    void dropPlayer(
        String playerId,
        String teamId);

    List<PlayerDetails> getAllPlayers();

    LeagueDetails getLeague(String leagueId);

    List<LeagueDetails> getLeaguesOfPlayer(String playerId);

    PlayerDetails getPlayer(String playerId);

    List<PlayerDetails> getPlayersByCity(String city);

    List<PlayerDetails> getPlayersByHigherSalary(String name);

    List<PlayerDetails> getPlayersByLeagueId(String leagueId);

    List<PlayerDetails> getPlayersByPosition(String position);

    List<PlayerDetails> getPlayersByPositionAndName(
        String position,
        String name);

    List<PlayerDetails> getPlayersBySalaryRange(
        double low,
        double high);

    List<PlayerDetails> getPlayersBySport(String sport);

    List<PlayerDetails> getPlayersNotOnTeam();

    List<PlayerDetails> getPlayersOfTeam(String teamId);

    List<String> getSportsOfPlayer(String playerId);

    TeamDetails getTeam(String teamId);

    List<TeamDetails> getTeamsOfLeague(String leagueId);

    void removeLeague(String leagueId);

    void removePlayer(String playerId);

    void removeTeam(String teamId);
}
