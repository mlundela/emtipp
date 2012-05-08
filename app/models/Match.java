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

  public int homeTeamScore;
  public int awayTeamScore;

  public Match(Team homeTeam, Team awayTeam) {
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    homeTeamScore = 0;
    awayTeamScore = 0;
  }

  @Override
  public String toString() {
    return "Match{" + homeTeam.name + " - " + awayTeam.name + " " + homeTeamScore + "-" + awayTeamScore + '}';
  }
}
