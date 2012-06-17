package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Player extends Model {

  public String name;
  public int playerID;
  public String posision;
  public String nationality;
  public String club;

  public int goals;
  public int assists;


  public Player(String name, int playerID, String posision, String nationality, String club) {
    this.playerID = playerID;
    this.name = name;
    this.posision = posision;
    this.nationality = nationality;
    this.club = club;
  }

  public boolean isTopScorer() {
    Player first = Player.find("order by goals desc").first();
    return goals == first.goals;
  }

  public boolean isTopAssist() {
    Player first = Player.find("order by assists desc").first();
    return assists == first.assists;
  }

  @Override
  public String toString() {
    return name + " - " + posision + " - " + nationality;
  }
}