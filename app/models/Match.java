package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Match extends Model {

  @ManyToOne
  public Team homeTeam;

  @ManyToOne
  public Team awayTeam;

  public int homeTeamScore = 0;
  public int awayTeamScore = 0;

  public Match(Team homeTeam, Team awayTeam) {
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
  }

  @Override
  public String toString() {
    return "Match{" + homeTeam.name + " - " + awayTeam.name + " " + homeTeamScore + "-" + awayTeamScore + '}';
  }
}
