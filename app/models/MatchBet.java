package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class MatchBet extends Model implements Comparable<MatchBet> {


  @ManyToOne
  public Match match;

  public int homeTeamScore = 0;
  public int awayTeamScore = 0;

  public MatchBet(Match match) {
    this.match = match;
  }


  public int getScore() {
    if (homeTeamScore == match.homeTeamScore && awayTeamScore == match.awayTeamScore) {
      return 3;
    } else if (homeTeamScore == match.homeTeamScore || awayTeamScore == match.awayTeamScore) {
      return 1;
    }
    return 0;
  }

  @Override
  public int compareTo(MatchBet matchBet) {
    return getSortIndex() - matchBet.getSortIndex();
  }

  private int getSortIndex() {
    return (int) (match.group.id * 1000 + match.id);
  }
}
