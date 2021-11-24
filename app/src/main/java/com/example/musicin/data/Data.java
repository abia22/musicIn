package com.example.musicin.data;

import java.util.HashMap;
import java.util.Map;

public class Data {

    Map<String, Musician> musicianMap;

    public Data() {
        this.musicianMap = new HashMap<>();
        musicianMap.put("musician@mail.com",
                new Musician("Jorge Daniel", "Guitar", "3/10/1995",
                        "Rock", "John Mayer", "musician@mail.com", "musician1"));
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
