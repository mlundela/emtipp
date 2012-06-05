package view;

import models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditBet {

  public Long id;
  public List<GroupBet> groupBets = new ArrayList<GroupBet>();
  public Team q1;
  public Team q2;
  public Team q3;
  public Team q4;
  public Team q5;
  public Team q6;
  public Team q7;
  public Team q8;
  public Team s1;
  public Team s2;
  public Team s3;
  public Team s4;
  public Team f1;
  public Team f2;
  public Team winner;

  public EditBet() {
    for (Group group : Group.<Group>findAll()) {
      GroupBet groupBet = new GroupBet();
      for (Match match : group.matches) {
        groupBet.matchBets.add(new MatchBet(match));
      }
      groupBets.add(groupBet);
    }

    updateTables();
    updateFinals();
  }

  private void updateFinals() {
    throw new RuntimeException("Not implemented yet!");
  }

  private void updateTables() {
    throw new RuntimeException("Not implemented yet!");
  }

  public EditBet(Bet bet) {
    
    id = bet.id;
    
    Map<Long, List<MatchBet>> groupMatches = getGroupMatches(bet);
    for (Group group : Group.<Group>findAll()) {
      groupBets.add(new GroupBet(groupMatches.get(group.id)));
    }
    
    q1 = bet.q1;
    q2 = bet.q2;
    q3 = bet.q3;
    q4 = bet.q4;
    q5 = bet.q5;
    q6 = bet.q6;
    q7 = bet.q7;
    q8 = bet.q8;
    s1 = bet.s1;
    s2 = bet.s2;
    s3 = bet.s3;
    s4 = bet.s4;
    f1 = bet.f1;
    f2 = bet.f2;
    winner = bet.winner;
  }
  
  public Bet toBet() {
    
    Bet bet = id == null ? new Bet() : Bet.<Bet>findById(id);

    for (GroupBet groupBet : groupBets) {
      for (MatchBet matchBet : groupBet.matchBets) {
        bet.matchBets.add(matchBet);
      }
    }

    bet.q1 = q1;
    bet.q2 = q2;
    bet.q3 = q3;
    bet.q4 = q4;
    bet.q5 = q5;
    bet.q6 = q6;
    bet.q7 = q7;
    bet.q8 = q8;
    bet.s1 = s1;
    bet.s2 = s2;
    bet.s3 = s3;
    bet.s4 = s4;
    bet.f1 = f1;
    bet.f2 = f2;
    bet.winner = winner;

    bet.save();
    return bet;
  }

  private Map<Long, List<MatchBet>> getGroupMatches(Bet bet) {
    Map<Long, List<MatchBet>> groupMatches = new HashMap<Long, List<MatchBet>>();
    for (MatchBet matchBet : bet.matchBets) {
      Long group_id = matchBet.match.group.id;
      if (!groupMatches.containsKey(group_id)) {
        groupMatches.put(group_id, new ArrayList<MatchBet>());
      }
      groupMatches.get(group_id).add(matchBet);
    }
    return groupMatches;
  }
}
