package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class MatchBet extends Model {

  @ManyToOne
  public UserBet user;

  @ManyToOne
  public Match match;

  public int homeTeamScore = 0;
  public int awayTeamScore = 0;

  public MatchBet(UserBet user, Match match) {
    this.match = match;
    this.user = user;
  }


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
