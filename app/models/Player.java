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


  public Player(String name, int playerID, String posision, String nationality, String club) {
    this.playerID = playerID;
    this.name = name;
    this.posision = posision;
    this.nationality = nationality;
    this.club = club;
    int appearances = 0;
    int goals = 0;
    int assists = 0;

  }


  @Override
  public String toString() {
    return name + " - " + posision + " - " + nationality;
  }
}