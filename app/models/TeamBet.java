package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class TeamBet extends Model {

  @ManyToOne
  public UserBet user;

  @ManyToOne
  public Team team;

  public Ranking ranking;

  public int getScore() {
    if (team.ranking == ranking) {
      return ranking.getValue();
    }
    return 0;
  }

}
