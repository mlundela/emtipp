package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Match extends Model {

  @ManyToOne
  public Group group;

  @ManyToOne
  public Team homeTeam;

  @ManyToOne
  public Team awayTeam;

  public int homeTeamScore = 0;
  public int awayTeamScore = 0;

  public boolean isPlayed;
  public boolean ignore;

  public Match(Team homeTeam, Team awayTeam, Group group) {
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.group = group;
  }

  @Override
  public String toString() {
    return "GMatch{" + homeTeam.name + " - " + awayTeam.name + " " + homeTeamScore + "-" + awayTeamScore + '}';
  }
}
