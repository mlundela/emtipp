package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class MatchBet extends Model {

  @ManyToOne
  public User user;

  @ManyToOne
  public Match match;

  public int homeTeamScore;
  public int awayTeamScore;


  public int getScore() {
    if (homeTeamScore == match.homeTeamScore && awayTeamScore == match.awayTeamScore) {
      return 3;
    }
    else if (homeTeamScore == match.homeTeamScore || awayTeamScore == match.awayTeamScore) {
      return 1;
    }
    return 0;
  }

}
