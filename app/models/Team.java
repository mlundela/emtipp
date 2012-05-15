package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Team extends Model {

  public String name;
  public String tGroup;
  public Ranking ranking;

  public Team(String name, String tGroup) {
    this.name = name;
    this.tGroup = tGroup;
    this.ranking = Ranking.GROUP_PLAY;
  }

  public static Team getTeam(String name, String group) {
    Team team = Team.find("name = ?", name).first();
    if (team == null) {
      team = new Team(name, group);
      team.save();
    }
    return team;
  }
}
