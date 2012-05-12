package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Players extends Model {

    public String name;

    public Players(String name){
        name = this.name;
    }



    public static Players getPlayer(String name) {
        Players player = Players.find("name = ?", name).first();

        if (player == null){
           player = new Players(name);
           player.save();
        }
        return player;
    }
    @Override
    public String toString(){
    return name + " - ";
    }
}