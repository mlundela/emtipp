package models;

import play.data.validation.Email;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usr")
public class User extends Model {

  @Email
  public String email;


  @OneToMany
  List<MatchBet> matchBets = new ArrayList<MatchBet>();

  @OneToMany
  List<TeamBet> teamBets = new ArrayList<TeamBet>();

}
