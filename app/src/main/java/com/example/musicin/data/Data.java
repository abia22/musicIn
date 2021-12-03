package com.example.musicin.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Data {

    Map<String, Musician> musicianMap;
    Map<String, Event> musicianEventsMap;
    Map<String, Request> musicianRequests;

    public Data() {
        this.musicianMap = new HashMap<>();
        Musician musician1 =  new Musician("Jorge Daniel", "Guitar", "3/10/1995",
                "Rock", "John Mayer", "musician@mail.com", "musician1");
        ArrayList<Band> musician1Bands = new ArrayList<>();

        //musician1Bands.add(new Band())
        //musician1.setBands();
        musicianMap.put("musician@mail.com", musician1);

    }

    public boolean checkIfUserExists(String email){
        return musicianMap.containsKey(email);
    }

    public boolean verifyLogin(String email, String password) {
        return musicianMap.get(email).getPassword().equals(password);
    }

    public Musician getMusician(String email){
        return musicianMap.get(email);
    }
}
