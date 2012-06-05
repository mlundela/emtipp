package view;

import models.MatchBet;
import models.Team;

import java.util.*;

public class GroupBet {

  public List<MatchBet> matchBets;

  public GroupBet(List<MatchBet> matchBets) {
    this.matchBets = matchBets;
  }

  public GroupBet() {
    matchBets = new ArrayList<MatchBet>();
  }

  private List<Team> getTable() {
    Set<Team> teams = new HashSet<Team>();
    for (MatchBet matchBet : matchBets) {
      teams.add(matchBet.match.homeTeam);
      teams.add(matchBet.match.awayTeam);
    }
    List<Team> out = new ArrayList<Team>(teams);
    Collections.sort(out);
    return out;
  }
}
