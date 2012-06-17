package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Bet extends Model implements Comparable<Bet> {

  @ManyToOne
  public User user;

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

  @ManyToOne
  public Player topScorer;

  @ManyToOne
  public Player topAssist;

  @Transient
  public List<Group> tables;

  public int getPoints() {
    int out = 0;
    for (MatchBet matchBet : matchBets) {
      out += matchBet.getScore();
    }
    if (topScorer.isTopScorer()) {
      out += 10;
    }
    if (topAssist.isTopAssist()) {
      out += 10;
    }
    return out;
  }

  public List<MatchBet> matchBetsSorted() {
    Collections.sort(matchBets);
    return matchBets;
  }

  public List<Player> allPlayers() {
    return Player.findAll();
  }

  public void init() {
    for (Match match : Match.<Match>findAll()) {
      matchBets.add(new MatchBet(match));
    }

    updateTables();
    updateFinals();

    topScorer = Player.<Player>findAll().get(0);
    topAssist = Player.<Player>findAll().get(0);
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

  public boolean tablesChanged() {

    updateTables();

    Team gg1 = tables.get(0).table().get(0);
    Team qq2 = tables.get(1).table().get(1);
    Team qq3 = tables.get(1).table().get(0);
    Team qq4 = tables.get(0).table().get(1);
    Team qq5 = tables.get(2).table().get(0);
    Team qq6 = tables.get(3).table().get(1);
    Team qq7 = tables.get(3).table().get(0);
    Team qq8 = tables.get(2).table().get(1);

    return !q1.id.equals(gg1.id) ||
        !q2.id.equals(qq2.id) ||
        !q3.id.equals(qq3.id) ||
        !q4.id.equals(qq4.id) ||
        !q5.id.equals(qq5.id) ||
        !q6.id.equals(qq6.id) ||
        !q7.id.equals(qq7.id) ||
        !q8.id.equals(qq8.id);
  }

  @Override
  public int compareTo(Bet bet) {
    return bet.getPoints() - getPoints();
  }
}
