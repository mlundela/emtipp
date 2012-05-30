package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bet extends Model {

  @OneToMany(cascade = CascadeType.ALL)
  public List<MatchBet> matchBets = new ArrayList<MatchBet>();
  @OneToOne
  public Team q1;
  @OneToOne
  public Team q2;
  @OneToOne
  public Team q3;
  @OneToOne
  public Team q4;
  @OneToOne
  public Team q5;
  @OneToOne
  public Team q6;
  @OneToOne
  public Team q7;
  @OneToOne
  public Team q8;
  @OneToOne
  public Team s1;
  @OneToOne
  public Team s2;
  @OneToOne
  public Team s3;
  @OneToOne
  public Team s4;
  @OneToOne
  public Team f1;
  @OneToOne
  public Team f2;
  @OneToOne
  public Team winner;

  @Transient
  public List<Group> tables;

  public void init() {
    for (Match match : Match.<Match>findAll()) {
      matchBets.add(new MatchBet(match));
    }

    updateTables();
    updateFinals();
  }

  public void updateTables() {
    List<Group> out = Group.findAll();
    for (MatchBet matchBet : matchBets) {
      for (Group group : out) {
        for (Match match : group.matches) {
          if (match.equals(matchBet.match)) {
            match.homeTeamScore = matchBet.homeTeamScore;
            match.awayTeamScore = matchBet.awayTeamScore;

            match.homeTeam.goalsScored += matchBet.homeTeamScore;
            match.homeTeam.goalsConceded += matchBet.awayTeamScore;
            match.awayTeam.goalsScored += matchBet.awayTeamScore;
            match.awayTeam.goalsConceded += matchBet.homeTeamScore;
            match.homeTeam.points += matchBet.homeTeamScore > matchBet.awayTeamScore ? 3 : matchBet.homeTeamScore == matchBet.awayTeamScore ? 1 : 0;
            match.awayTeam.points += matchBet.awayTeamScore > matchBet.homeTeamScore ? 3 : matchBet.awayTeamScore == matchBet.homeTeamScore ? 1 : 0;
          }
        }
      }
    }
    tables = new ArrayList<Group>(out);
  }


  public void updateFinals() {

    q1 = tables.get(0).table().get(0);
    q2 = tables.get(1).table().get(1);
    q3 = tables.get(1).table().get(0);
    q4 = tables.get(0).table().get(1);
    q5 = tables.get(2).table().get(0);
    q6 = tables.get(3).table().get(1);
    q7 = tables.get(3).table().get(0);
    q8 = tables.get(2).table().get(1);

    s1 = q1;
    s2 = q5;
    s3 = q3;
    s4 = q7;

    f1 = s1;
    f2 = s3;

    winner = s1;
  }
}
