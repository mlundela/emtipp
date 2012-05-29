package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Team extends Model implements Comparable<Team> {

  public String name;
  public int goalsScored = 0;
  public int goalsConceded = 0;
  public int points = 0;
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

  @Override
  public int compareTo(Team team) {
    if (points > team.points) {
      return -1;
    }
    if (points < team.points) {
      return 1;
    }
    if (goalDifference() > team.goalDifference()) {
      return -1;
    }
    if (goalDifference() < team.goalDifference()) {
      return 1;
    }
    return name.compareTo(team.name);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Team team = (Team) o;
    return !(name != null ? !name.equals(team.name) : team.name != null);
  }

  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }

  private int goalDifference() {
    return goalsScored - goalsConceded;
  }
}
