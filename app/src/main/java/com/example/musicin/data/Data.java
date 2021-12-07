package com.example.musicin.data;

import com.example.musicin.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Data {

    Map<String, Musician> musicianMap;
    Map<String, List<Event>> musicianEventsMap;
    Map<String, List<Notification>> musicianNotificationsMap;
    Map<String, MusicianProfile> musicianProfileMap;
    List<Event> eventsList;
    Musician musician1, musician2;

    public static final String[] genres = new String[] {"Blues", "ClassicRock", "Country", "Dance", "Disco", "Funk", "Grunge", "HipHop", "Jazz", "Metal", "NewAge", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "DeathMetal", "Pranks", "Soundtrack", "EuroTechno", "Ambient", "TripHop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "SoundClip", "Gospel", "Noise", "AlternativeRock", "Bass", "Soul", "Punk", "Space", "Meditative", "InstrumentalPop", "InstrumentalRock", "Ethnic", "Gothic", "Darkwave", "TechnoIndustrial", "Electronic", "PopFolk", "Eurodance", "Dream", "SouthernRock", "Comedy", "Cult", "Gangsta", "Top", "ChristianRap", "Pop/Funk", "Jungle", "NativeUS", "Cabaret", "NewWave", "Psychadelic", "Rave", "Showtunes", "Trailer", "LoFi", "Tribal", "AcidPunk", "AcidJazz", "Polka", "Retro", "Musical", "Rock&Roll", "HardRock", "Folk", "FolkRock", "NationalFolk", "Swing", "FastFusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "GothicRock", "ProgressiveRock", "PsychedelicRock", "SymphonicRock", "SlowRock", "BigBand", "Chorus", "EasyListening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "ChamberMusic", "Sonata", "Symphony", "BootyBass", "Primus", "PornGroove", "Satire", "SlowJam", "Club", "Tango", "Samba", "Folklore", "Ballad", "PowerBallad", "RhythmicSoul", "Freestyle", "Duet", "PunkRock", "DrumSolo", "Acapella", "EuroHouse", "DanceHall", "Goa", "Drum&Bass", "ClubHouse", "Hardcore", "Terror", "Indie", "BritPop", "Negerpunk", "PolskPunk", "Beat", "ChristianGangstaRap", "HeavyMetal", "BlackMetal", "Crossover", "ContemporaryChristian", "ChristianRock", "Merengue", "Salsa", "ThrashMetal", "Anime", "JPop", "Synthpop"};

    public static Data getInstance(){ return  instance;}

    private  static Data instance = new Data();

    private Data() {
        this.musicianMap = new HashMap<>();
        this.musicianEventsMap = new HashMap<>();
        this.musicianNotificationsMap = new HashMap<>();
        this.musicianProfileMap = new HashMap<>();
        this.eventsList = new ArrayList<>();

        musician1 =  new Musician("Jorge Daniel", "Guitar", "03/10/1995",
                "Rock", "John Mayer", "musician@mail.com", "musician1");

        musician2 = new Musician("Kara Woolley", "Piano", "14/08/1996",
                "", "", "musician2@mail.com", "musician2");

        MusicianProfile musicianProfile2 = new MusicianProfile(3, "various cafes and small venues", "Liberty Music School", "https://www.youtube.com/", "https://image.freepik.com/free-photo/young-woman-playing-piano-indoors_23-2149140646.jpg");
        musicianProfileMap.put("musician2@mail.com", musicianProfile2);

        BandMember member1 = new BandMember("Dollie Moody", "Guitar", "https://image.freepik.com/free-photo/cheerful-ginger-teenage-girl-stretches-arm-making-selfie-smiles-gladfully-with-teeth-glad-buy-new-white-electric-guitar-wears-casual-clothes-going-practice-playing-musical-instrument_273609-50910.jpg");
        BandMember member2 = new BandMember("Braiden Cherry", "Drums", "https://image.freepik.com/free-photo/drummer-playing-drum-set_107420-96124.jpg");
        BandMember member3 = new BandMember("Bradley Tucker", "Singer", "https://media.istockphoto.com/photos/man-singing-on-microphone-picture-id852214814?k=20&m=852214814&s=612x612&w=0&h=pZuG6MvdNla_NEE8iV5lQMUFLnhErh1rK7TnjYzDBbA=");
        ArrayList<BandMember> memberList = new ArrayList<>();
        memberList.add(member1);
        memberList.add(member2);
        memberList.add(member3);

        Band band = new Band("King Red", memberList);
        musician1.addBand(band);

        List<String> bandRequestInstruments = new ArrayList<>();
        bandRequestInstruments.add("Guitar");
        bandRequestInstruments.add("Drums");
        bandRequestInstruments.add("Singer");
        BandRequest bandRequest = new BandRequest(bandRequestInstruments, "Rock",null);

        musicianMap.put("musician@mail.com", musician1);
        musicianMap.put("musician2@mail.com", musician2);

        Event event1 = new Event("https://image.freepik.com/free-vector/rock-n-roll-poster-with-words-long-live-rock-n-roll-design-t-shirt_1284-49229.jpg", "Long Live Rock'n Roll", "Cafe Central", "14/01/22, 21:30");
        List<Event> eventList1 = new ArrayList<>();
        eventList1.add(event1);
        musicianEventsMap.put("musician@mail.com", eventList1);

        Notification notification1 = new Notification(R.drawable.check_circle, "Cafe Central accepted your request to perform for Long Live Rock'n Roll.");
        Notification notification2 = new Request(R.drawable.priority_high, "Kara Woolley wants to join your band King Red.", "musician2@mail.com", "King Red");
        List<Notification> notificationList1 = new ArrayList<>();
        notificationList1.add(notification1);
        notificationList1.add(notification2);
        musicianNotificationsMap.put("musician@mail.com", notificationList1);
        Event newEvent = new Event("https://i.ibb.co/PmqsXr7/Screenshot-2021-12-06-at-13-34-58.png","Jazz concert","New York","09/08/2022, 19:00",50,"Jazz","Jazz concert, all musicians or bands can apply","+121223454321");
        Event newEvent2 = new Event("https://image.freepik.com/free-vector/abstract-geometric-shapes-music-festival-vertical-poster-template_23-2148984513.jpg","Music Fest","Lisbon","30/11/2022, 19:00",200,"World Music","Minimal Music fest, if you wanna join, please contact us","+351123456789");
        Event newEvent3 = new Event("https://image.freepik.com/free-vector/music-festival-invitation-design-with-notes_1017-9868.jpg","Electronic music festival","Moscow","1/12/2022, 21:00",0,"electronic","djs who want to mix, can apply to us","+74992343234");
        eventsList.add(newEvent);
        eventsList.add(newEvent2);
        eventsList.add(newEvent3);


    }

    public boolean checkIfUserExists(String email){
        return musicianMap.containsKey(email);
    }

    public boolean verifyLogin(String email, String password) {
        return musicianMap.get(email).getPassword().equals(password);
    }

    public MusicianProfile getMusicianProfile(String email) {
        return musicianProfileMap.get(email);
    }

    public Musician getMusician(String email){
        return musicianMap.get(email);
    }

    public List<Event> getMusicianEvents(String email) {
        return musicianEventsMap.get(email);
    }

    public List<Notification> getMusicianRequests(String email) {
        return musicianNotificationsMap.get(email);
    }

    public List<Event> getPaidEvents(){
        List<Event> paidEvents = new ArrayList<>();
        eventsList.forEach(e -> {if(e.getPayment() != 0 ) paidEvents.add(e);});
        return paidEvents;
    }

    public List<Event>getAllEvents(){
        eventsList.clear();
        Event newEvent = new Event("https://i.ibb.co/PmqsXr7/Screenshot-2021-12-06-at-13-34-58.png","Jazz concert","New York","9/08/2022, 19:00",50,"Jazz","Jazz concert, all musicians or bands can apply","+121223454321");
        Event newEvent2 = new Event("https://image.freepik.com/free-vector/abstract-geometric-shapes-music-festival-vertical-poster-template_23-2148984513.jpg","Music Fest","Lisbon","30/11/2022, 19:00",200,"World Music","Minimal Music fest, if you wanna join, please contact us","+351123456789");
        Event newEvent3 = new Event("https://image.freepik.com/free-vector/music-festival-invitation-design-with-notes_1017-9868.jpg","Electronic music festival","Moscow","1/12/2022, 21:00",0,"electronic","djs who want to mix, can apply to us","+74992343234");
        eventsList.add(newEvent);
        eventsList.add(newEvent2);
        eventsList.add(newEvent3);
        return eventsList;
    }

    public List<Event> applyFilter(boolean payment,boolean location,String genre,String date){
        List<Event> filteredEvents = new ArrayList<>();
        filteredEvents.addAll(getAllEvents());
        if(payment){
            filteredEvents = filteredEvents.stream().filter(event -> event.getPayment() > 0 ).collect(Collectors.toList());
        }

        if(genre != null){
            filteredEvents = filteredEvents.stream().filter(event -> event.getGenre().equalsIgnoreCase(genre)).collect(Collectors.toList());
        }

        if(date != null){
            filteredEvents = filteredEvents.stream().filter(event -> event.getDate().split(", ")[0].equalsIgnoreCase(date)).collect(Collectors.toList());
        }
        return filteredEvents;

    }



}
