package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "Grp")
public class Group extends Model {

  public String name;

  @OneToMany
  public List<Team> teams = new ArrayList<Team>();

  @OneToMany
  public List<Match> matches = new ArrayList<Match>();

  public Group(String name) {
    this.name = name;
  }

  public static Group findOrCreateGroup(String name) {
    Group group = find("name = ?", name).first();
    if (group == null) {
      group = new Group(name);
      group.save();
    }
    return group;
  }

  public List<Team> table() {
    Collections.sort(teams);
    return teams;
  }
}
