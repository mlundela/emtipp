package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Players extends Model {

    public String name;
    public int playerID;

    public Players(String name, int playerID) {
        playerID = this.playerID;
        name = this.name;


    }


    public static Players getPlayer(String name, int playerID) {
        Players player = Players.find("playerID =", playerID).first();

        if (player == null) {
            player = new Players(name, playerID);
            player.save();
        }
        return player;
    }

    @Override
    public String toString() {
        return playerID + " -- " + name;
    }
}