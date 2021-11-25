package com.example.musicin.data;

import java.util.List;

public class Band {
    private String name;
    private List<BandMember> members;

    public Band(String name, List<BandMember> members) {
        this.name = name;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BandMember> getMembers() {
        return members;
    }

    public void setMembers(List<BandMember> members) {
        this.members = members;
    }

    public void addMember(BandMember member) {
        members.add(member);
    }
}
