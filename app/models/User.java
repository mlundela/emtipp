package models;

import play.data.validation.Email;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "usr")
public class User extends Model {

  @Email
  public String email;
  public String password;
  public String name;

  public List<Bet> getBets() {
    return Bet.find("user = ?", this).fetch();
  }

}
