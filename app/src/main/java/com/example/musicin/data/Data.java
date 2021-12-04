package com.example.musicin.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Data {

    Map<String, Musician> musicianMap;
    Map<String, Event> musicianEventsMap;
    Map<String, Request> musicianRequests;

    public static final String[] genres = new String[] {"Blues", "ClassicRock", "Country", "Dance", "Disco", "Funk", "Grunge", "HipHop", "Jazz", "Metal", "NewAge", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "DeathMetal", "Pranks", "Soundtrack", "EuroTechno", "Ambient", "TripHop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "SoundClip", "Gospel", "Noise", "AlternativeRock", "Bass", "Soul", "Punk", "Space", "Meditative", "InstrumentalPop", "InstrumentalRock", "Ethnic", "Gothic", "Darkwave", "TechnoIndustrial", "Electronic", "PopFolk", "Eurodance", "Dream", "SouthernRock", "Comedy", "Cult", "Gangsta", "Top", "ChristianRap", "Pop/Funk", "Jungle", "NativeUS", "Cabaret", "NewWave", "Psychadelic", "Rave", "Showtunes", "Trailer", "LoFi", "Tribal", "AcidPunk", "AcidJazz", "Polka", "Retro", "Musical", "Rock&Roll", "HardRock", "Folk", "FolkRock", "NationalFolk", "Swing", "FastFusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "GothicRock", "ProgressiveRock", "PsychedelicRock", "SymphonicRock", "SlowRock", "BigBand", "Chorus", "EasyListening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "ChamberMusic", "Sonata", "Symphony", "BootyBass", "Primus", "PornGroove", "Satire", "SlowJam", "Club", "Tango", "Samba", "Folklore", "Ballad", "PowerBallad", "RhythmicSoul", "Freestyle", "Duet", "PunkRock", "DrumSolo", "Acapella", "EuroHouse", "DanceHall", "Goa", "Drum&Bass", "ClubHouse", "Hardcore", "Terror", "Indie", "BritPop", "Negerpunk", "PolskPunk", "Beat", "ChristianGangstaRap", "HeavyMetal", "BlackMetal", "Crossover", "ContemporaryChristian", "ChristianRock", "Merengue", "Salsa", "ThrashMetal", "Anime", "JPop", "Synthpop"};

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
