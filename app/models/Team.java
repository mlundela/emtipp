package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Team extends Model {

  public String name;
  public Ranking ranking;

  public Team(String name) {
    this.name = name;
    this.ranking = Ranking.GROUP_PLAY;
  }

  public static Team getTeam(String name) {
    Team team = Team.find("name = ?", name).first();
    if (team == null) {
      team = new Team(name);
      team.save();
    }
    return team;
  }


}
