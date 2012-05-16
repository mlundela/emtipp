package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Team extends Model {

  public String name;
  public Ranking ranking = Ranking.GROUP_PLAY;

  public Team(String name) {
    this.name = name;
  }

  public static Team findOrCreateTeam(String name) {
    Team team = find("name = ?", name).first();
    if (team == null) {
      team = new Team(name);
      team.save();
    }
    return team;
  }
}
