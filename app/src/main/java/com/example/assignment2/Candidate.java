package com.example.assignment2;

import java.util.ArrayList;
import java.util.List;

public class Candidate {
    private String name;
    private List<Voter> voters = new ArrayList<Voter>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Voter> getVoters() {
        return voters;
    }

    public void setVoters(List<Voter> voters) {
        this.voters = voters;
    }

    public int getVoteNum(){
        return this.voters.size();
    }

    public void addVoter(String name, String id){
        this.voters.add(new Voter(name, id));
    }

    public Candidate(String name) {
        this.name = name;
    }
}
