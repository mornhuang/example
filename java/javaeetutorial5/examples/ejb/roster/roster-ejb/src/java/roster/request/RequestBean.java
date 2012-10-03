/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package roster.request;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import roster.entity.League;
import roster.entity.Player;
import roster.entity.SummerLeague;
import roster.entity.Team;
import roster.entity.WinterLeague;
import roster.util.IncorrectSportException;
import roster.util.LeagueDetails;
import roster.util.PlayerDetails;
import roster.util.TeamDetails;


/**
 * This is the bean class for the RequestBean enterprise bean.
 * Created Feb 13, 2006 8:18:47 PM
 * @author ian
 */
@Stateful
public class RequestBean implements Request {
    private static final Logger logger = Logger.getLogger(
                "roster.request.RequestBean");
    @PersistenceContext
    private EntityManager em;

    public void createPlayer(
        String id,
        String name,
        String position,
        double salary) {
        logger.info("createPlayer");

        try {
            Player player = new Player(id, name, position, salary);
            em.persist(player);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void addPlayer(
        String playerId,
        String teamId) {
        logger.info("addPlayer");

        try {
            Player player = em.find(Player.class, playerId);
            Team team = em.find(Team.class, teamId);

            team.addPlayer(player);
            player.addTeam(team);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void removePlayer(String playerId) {
        logger.info("removePlayer");

        try {
            Player player = em.find(Player.class, playerId);

            Collection<Team> teams = player.getTeams();
            Iterator<Team> i = teams.iterator();

            while (i.hasNext()) {
                Team team = i.next();
                team.dropPlayer(player);
            }

            em.remove(player);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void dropPlayer(
        String playerId,
        String teamId) {
        logger.info("dropPlayer");

        try {
            Player player = em.find(Player.class, playerId);
            Team team = em.find(Team.class, teamId);

            team.dropPlayer(player);
            player.dropTeam(team);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public PlayerDetails getPlayer(String playerId) {
        logger.info("getPlayerDetails");

        PlayerDetails playerDetails = null;

        try {
            Player player = em.find(Player.class, playerId);
            playerDetails = new PlayerDetails(
                        player.getId(),
                        player.getName(),
                        player.getPosition(),
                        player.getSalary());

            return playerDetails;
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public List<PlayerDetails> getPlayersOfTeam(String teamId) {
        logger.info("getPlayersOfTeam");

        List<PlayerDetails> playerList = null;

        try {
            Team team = em.find(Team.class, teamId);
            playerList = this.copyPlayersToDetails(
                        (List<Player>) team.getPlayers());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }

        return playerList;
    }

    public List<TeamDetails> getTeamsOfLeague(String leagueId) {
        logger.info("getTeamsOfLeague");

        List<TeamDetails> detailsList = new ArrayList<TeamDetails>();
        Collection<Team> teams = null;

        try {
            League league = em.find(League.class, leagueId);
            teams = league.getTeams();
        } catch (Exception ex) {
            throw new EJBException(ex);
        }

        Iterator<Team> i = teams.iterator();

        while (i.hasNext()) {
            Team team = (Team) i.next();
            TeamDetails teamDetails = new TeamDetails(
                        team.getId(),
                        team.getName(),
                        team.getCity());
            detailsList.add(teamDetails);
        }

        return detailsList;
    }

    @SuppressWarnings("unchecked")
    public List<PlayerDetails> getPlayersByPosition(String position) {
        logger.info("getPlayersByPosition");

        List<Player> players = null;

        try {
            players = (List<Player>) em.createNamedQuery(
                        "roster.entity.Player.findPlayersByPosition")
                                       .setParameter("position", position)
                                       .getResultList();

            return copyPlayersToDetails(players);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public List<PlayerDetails> getPlayersByHigherSalary(String name) {
        logger.info("getPlayersByHigherSalary");

        List<Player> players = null;

        try {
            players = (List<Player>) em.createNamedQuery(
                        "roster.entity.Player.findPlayersByHigherSalary")
                                       .setParameter("name", name)
                                       .getResultList();

            return copyPlayersToDetails(players);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public List<PlayerDetails> getPlayersBySalaryRange(
        double low,
        double high) {
        logger.info("getPlayersBySalaryRange");

        List<Player> players = null;

        try {
            players = (List<Player>) em.createNamedQuery(
                        "roster.entity.Player.findPlayersBySalaryRange")
                                       .setParameter("lowerSalary", low)
                                       .setParameter("higherSalary", high)
                                       .getResultList();

            return copyPlayersToDetails(players);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public List<PlayerDetails> getPlayersByLeagueId(String leagueId) {
        logger.info("getPlayersByLeagueId");

        List<Player> players = null;

        try {
            League league = em.find(League.class, leagueId);
            players = (List<Player>) em.createNamedQuery(
                        "roster.entity.Player.findPlayersByLeague")
                                       .setParameter("league", league)
                                       .getResultList();

            return copyPlayersToDetails(players);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public List<PlayerDetails> getPlayersBySport(String sport) {
        logger.info("getPlayersByLeagueId");

        List<Player> players = null;

        try {
            players = (List<Player>) em.createNamedQuery(
                        "roster.entity.Player.findPlayersBySport")
                                       .setParameter("sport", sport)
                                       .getResultList();

            return copyPlayersToDetails(players);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public List<PlayerDetails> getPlayersByCity(String city) {
        logger.info("getPlayersByCity");

        List<Player> players = null;

        try {
            players = (List<Player>) em.createNamedQuery(
                        "roster.entity.Player.findPlayersByCity")
                                       .setParameter("city", city)
                                       .getResultList();

            return copyPlayersToDetails(players);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public List<PlayerDetails> getAllPlayers() {
        logger.info("getAllPlayers");

        List<Player> players = null;

        try {
            players = (List<Player>) em.createNamedQuery(
                        "roster.entity.Player.findAllPlayers")
                                       .getResultList();

            return copyPlayersToDetails(players);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public List<PlayerDetails> getPlayersNotOnTeam() {
        logger.info("getPlayersNotOnTeam");

        List<Player> players = null;

        try {
            players = (List<Player>) em.createNamedQuery(
                        "roster.entity.Player.findPlayersNotOnTeam")
                                       .getResultList();

            return copyPlayersToDetails(players);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public List<PlayerDetails> getPlayersByPositionAndName(
        String position,
        String name) {
        logger.info("getPlayersByPositionAndName");

        List<Player> players = null;

        try {
            players = (List<Player>) em.createNamedQuery(
                        "roster.entity.Player.findPlayersByPositionAndName")
                                       .setParameter("name", name)
                                       .setParameter("position", position)
                                       .getResultList();

            return copyPlayersToDetails(players);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public List<LeagueDetails> getLeaguesOfPlayer(String playerId) {
        logger.info("getLeaguesOfPlayer");

        List<LeagueDetails> detailsList = new ArrayList<LeagueDetails>();
        List<League> leagues = null;

        try {
            Player player = em.find(Player.class, playerId);
            leagues = (List<League>) em.createNamedQuery(
                        "roster.entity.Player.findLeaguesByPlayer")
                                       .setParameter("player", player)
                                       .getResultList();
        } catch (Exception ex) {
            throw new EJBException(ex);
        }

        Iterator<League> i = leagues.iterator();

        while (i.hasNext()) {
            League league = (League) i.next();
            LeagueDetails leagueDetails = new LeagueDetails(
                        league.getId(),
                        league.getName(),
                        league.getSport());
            detailsList.add(leagueDetails);
        }

        return detailsList;
    }

    @SuppressWarnings("unchecked")
    public List<String> getSportsOfPlayer(String playerId) {
        logger.info("getSportsOfPlayer");

        List<String> sportsList = new ArrayList<String>();
        List<String> sports = null;

        try {
            Player player = em.find(Player.class, playerId);
            logger.info(
                    "RequestBean.getSportsOfPlayer: finding sports of "
                    + player.getName());
            sports = (List<String>) em.createNamedQuery(
                        "roster.entity.Player.findSportsByPlayer")
                                      .setParameter("playerId", playerId)
                                      .getResultList();

            if (sports.isEmpty()) {
                logger.info(
                        "RequestBean.getSportsOfPlayer: no sports returned");
            }
        } catch (Exception ex) {
            throw new EJBException(ex);
        }

        Iterator<String> i = sports.iterator();

        while (i.hasNext()) {
            String sport = (String) i.next();
            sportsList.add(sport);
        }

        return sportsList;
    }

    public void createTeamInLeague(
        TeamDetails teamDetails,
        String leagueId) {
        logger.info("createTeamInLeague");

        try {
            League league = em.find(League.class, leagueId);
            Team team = new Team(
                        teamDetails.getId(),
                        teamDetails.getName(),
                        teamDetails.getCity());
            em.persist(team);
            team.setLeague(league);
            league.addTeam(team);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void removeTeam(String teamId) {
        logger.info("removeTeam");

        try {
            Team team = em.find(Team.class, teamId);

            Collection<Player> players = team.getPlayers();
            Iterator<Player> i = players.iterator();

            while (i.hasNext()) {
                Player player = (Player) i.next();
                player.dropTeam(team);
            }

            em.remove(team);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public TeamDetails getTeam(String teamId) {
        logger.info("getTeam");

        TeamDetails teamDetails = null;

        try {
            Team team = em.find(Team.class, teamId);
            teamDetails = new TeamDetails(
                        team.getId(),
                        team.getName(),
                        team.getCity());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }

        return teamDetails;
    }

    public void createLeague(LeagueDetails leagueDetails) {
        logger.info("createLeague");

        try {
            if (leagueDetails.getSport()
                                 .equalsIgnoreCase("soccer")
                    || leagueDetails.getSport()
                                        .equalsIgnoreCase("swimming")
                    || leagueDetails.getSport()
                                        .equalsIgnoreCase("basketball")
                    || leagueDetails.getSport()
                                        .equalsIgnoreCase("baseball")) {
                SummerLeague league = new SummerLeague(
                            leagueDetails.getId(),
                            leagueDetails.getName(),
                            leagueDetails.getSport());
                em.persist(league);
            } else if (leagueDetails.getSport()
                                        .equalsIgnoreCase("hockey")
                    || leagueDetails.getSport()
                                        .equalsIgnoreCase("skiing")
                    || leagueDetails.getSport()
                                        .equalsIgnoreCase("snowboarding")) {
                WinterLeague league = new WinterLeague(
                            leagueDetails.getId(),
                            leagueDetails.getName(),
                            leagueDetails.getSport());
                em.persist(league);
            } else {
                throw new IncorrectSportException(
                        "The specified sport is not valid.");
            }
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void removeLeague(String leagueId) {
        logger.info("removeLeague");

        try {
            League league = em.find(League.class, leagueId);
            em.remove(league);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public LeagueDetails getLeague(String leagueId) {
        logger.info("getLeague");

        LeagueDetails leagueDetails = null;

        try {
            League league = em.find(League.class, leagueId);
            leagueDetails = new LeagueDetails(
                        league.getId(),
                        league.getName(),
                        league.getSport());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }

        return leagueDetails;
    }

    private List<PlayerDetails> copyPlayersToDetails(List<Player> players) {
        List<PlayerDetails> detailsList = new ArrayList<PlayerDetails>();
        Iterator<Player> i = players.iterator();

        while (i.hasNext()) {
            Player player = (Player) i.next();
            PlayerDetails playerDetails = new PlayerDetails(
                        player.getId(),
                        player.getName(),
                        player.getPosition(),
                        player.getSalary());
            detailsList.add(playerDetails);
        }

        return detailsList;
    }
}
