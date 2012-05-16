package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class TGroup  extends Model {

  public String name;

  @OneToMany
  public Set<Team> teams = new HashSet<Team>();

  @OneToMany
  public List<Match> matches = new ArrayList<Match>();

  public TGroup(String name) {
    this.name = name;
  }

  public static TGroup findOrCreateGroup(String name) {
    TGroup group = find("name = ?", name).first();
    if (group == null) {
      group = new TGroup(name);
      group.save();
    }
    return group;
  }
}
