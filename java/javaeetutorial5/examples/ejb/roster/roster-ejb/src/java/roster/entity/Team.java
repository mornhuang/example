/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package roster.entity;

import java.util.Collection;
import static javax.persistence.CascadeType.REMOVE;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "EJB_ROSTER_TEAM")
public class Team implements java.io.Serializable {
    private Collection<Player> players;
    private League league;
    private String city;
    private String id;
    private String name;

    /** Creates a new instance of Team */
    public Team() {
    }

    public Team(
        String id,
        String name,
        String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @ManyToMany
    @JoinTable(name = "EJB_ROSTER_TEAM_PLAYER", joinColumns = @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID")
    , inverseJoinColumns = @JoinColumn(name = "PLAYER_ID", referencedColumnName = "ID")
    )
    public Collection<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Collection<Player> players) {
        this.players = players;
    }

    @ManyToOne
    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public void addPlayer(Player player) {
        this.getPlayers()
            .add(player);
    }

    public void dropPlayer(Player player) {
        this.getPlayers()
            .remove(player);
    }
}
