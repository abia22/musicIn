package com.example.musicin.data;

import com.example.musicin.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Data {

    Map<String, Musician> musicianMap;
    Map<String, List<Event>> musicianEventsMap;
    Map<String, List<Notification>> musicianNotificationsMap;
    Map<String, MusicianProfile> musicianProfileMap;
    Map<String, BandMember> bandMemberMap;
    List<Event> eventsList;
    List<BandRequest> bandRequestList;
    Musician musician1, musician2,musician3,musician4,musician5,musician6,musician7;

    public static final String[] genres = new String[] {"Blues", "ClassicRock", "Country", "Dance", "Disco", "Funk", "Grunge", "HipHop", "Jazz", "Metal", "NewAge", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "DeathMetal", "Pranks", "Soundtrack", "EuroTechno", "Ambient", "TripHop", "Vocal", "Jazz+Funk", "World Music", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "SoundClip", "Gospel", "Noise", "AlternativeRock", "Bass", "Soul", "Punk", "Space", "Meditative", "InstrumentalPop", "InstrumentalRock", "Ethnic", "Gothic", "Darkwave", "TechnoIndustrial", "Electronic", "PopFolk", "Eurodance", "Dream", "SouthernRock", "Comedy", "Cult", "Gangsta", "Top", "ChristianRap", "Pop/Funk", "Jungle", "NativeUS", "Cabaret", "NewWave", "Psychadelic", "Rave", "Showtunes", "Trailer", "LoFi", "Tribal", "AcidPunk", "AcidJazz", "Polka", "Retro", "Musical", "Rock&Roll", "HardRock", "Folk", "FolkRock", "NationalFolk", "Swing", "FastFusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "GothicRock", "ProgressiveRock", "PsychedelicRock", "SymphonicRock", "SlowRock", "BigBand", "Chorus", "EasyListening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "ChamberMusic", "Sonata", "Symphony", "BootyBass", "Primus", "PornGroove", "Satire", "SlowJam", "Club", "Tango", "Samba", "Folklore", "Ballad", "PowerBallad", "RhythmicSoul", "Freestyle", "Duet", "PunkRock", "DrumSolo", "Acapella", "EuroHouse", "DanceHall", "Goa", "Drum&Bass", "ClubHouse", "Hardcore", "Terror", "Indie", "BritPop", "Negerpunk", "PolskPunk", "Beat", "ChristianGangstaRap", "HeavyMetal", "BlackMetal", "Crossover", "ContemporaryChristian", "ChristianRock", "Merengue", "Salsa", "ThrashMetal", "Anime", "JPop", "Synthpop"};

    public static final String[] instruments = new String[] {"Singer","Accordion","Banjo","Bass guitar","Bongo","Cello","Clarinet","Cymbal","Drums","Euphonium","Flute","Harmonica","Gong","Guitar","Harmonica","Harp","Hammered dulcimer","Lute","Lyre","Mandolin","Marimba","Piano","Piccolo","Saxophone","Tambourine","Trumpet","Tuba","Ukulele","Viola","Violin","Xylophone"};

    public static final String[] appUsers = new String[] {"Kara Woolley", "Dollie Moody", "Braiden Cherry", "Bradley Tucker", "Ray Scott"};

    public static Data getInstance(){ return  instance;}

    private  static Data instance = new Data();

    private Data() {
        this.musicianMap = new HashMap<>();
        this.musicianEventsMap = new HashMap<>();
        this.musicianNotificationsMap = new HashMap<>();
        this.musicianProfileMap = new HashMap<>();
        this.bandMemberMap = new HashMap<>();
        this.eventsList = new ArrayList<>();
        this.bandRequestList = new ArrayList<>();

        musician1 =  new Musician("Jorge Daniel", "Guitar", "03/10/1995",
                "Rock", "John Mayer", "musician@mail.com", "musician1");

        musician2 = new Musician("Kara Woolley", "Piano", "14/08/1996",
                "", "", "musician2@mail.com", "musician2");

        musician3 = new Musician("Dollie Moody","Guitar","13/09/1998","","","dolly@mail.com","");
        musician4 = new Musician("Braiden Cherry", "Drums","15/06/2000","","","bc@mail.com","");
        musician5 = new Musician("Bradley Tucker", "Singer","18/01/1998","","","bt@mail.com","");
        musician6 = new Musician("Ray Scott", "Piano", "20/02/1985","","","ray@mail.com","");
        musician7 = new Musician("Bob Stuart","Guitar", "27/04/1995", "", "", "bob@mail.com", "");
        MusicianProfile musicianProfile2 = new MusicianProfile(3, "various cafes and small venues", "Liberty Music School", "https://www.youtube.com/", "https://image.freepik.com/free-photo/young-woman-playing-piano-indoors_23-2149140646.jpg");
        MusicianProfile musicianProfile3 = new MusicianProfile(2,"various gigs","Rock school","https://www.spotify.com","https://image.freepik.com/free-photo/cheerful-ginger-teenage-girl-stretches-arm-making-selfie-smiles-gladfully-with-teeth-glad-buy-new-white-electric-guitar-wears-casual-clothes-going-practice-playing-musical-instrument_273609-50910.jpg");
        MusicianProfile musicianProfile4 = new MusicianProfile(4,"concerts around the world","jazz music school","https://www.tidal.com","https://image.freepik.com/free-photo/drummer-playing-drum-set_107420-96124.jpg");
        MusicianProfile musicianProfile5 = new MusicianProfile(5,"with a church choir","church choir and private lessons ","https://www.facebook.com","https://media.istockphoto.com/photos/man-singing-on-microphone-picture-id852214814?k=20&m=852214814&s=612x612&w=0&h=pZuG6MvdNla_NEE8iV5lQMUFLnhErh1rK7TnjYzDBbA=");
        MusicianProfile musicianProfile6 = new MusicianProfile(10,"at weddings and formal restaurants","arts college","https://www.myspace.com","https://image.freepik.com/free-photo/elegant-dressed-musician-playing-keyboards-front-view_23-2148673548.jpg");
        MusicianProfile musicianProfile7 = new MusicianProfile(8,"at cruises","arts college","https://www.myspace.com","https://image.freepik.com/free-photo/guitarist-plays-electric-guitar-with-bright-emotions-grey-background_186202-4755.jpg");

        musicianProfileMap.put("musician2@mail.com", musicianProfile2);
        musicianProfileMap.put(musician3.getEmail(),musicianProfile3);
        musicianProfileMap.put(musician4.getEmail(),musicianProfile4);
        musicianProfileMap.put(musician5.getEmail(),musicianProfile5);
        musicianProfileMap.put(musician6.getEmail(),musicianProfile6);
        musicianProfileMap.put(musician7.getEmail(),musicianProfile7);

        BandMember member1 = new BandMember("Dollie Moody", "Guitar", "https://image.freepik.com/free-photo/cheerful-ginger-teenage-girl-stretches-arm-making-selfie-smiles-gladfully-with-teeth-glad-buy-new-white-electric-guitar-wears-casual-clothes-going-practice-playing-musical-instrument_273609-50910.jpg",musician3.getEmail());
        BandMember member2 = new BandMember("Braiden Cherry", "Drums", "https://image.freepik.com/free-photo/drummer-playing-drum-set_107420-96124.jpg",musician4.getEmail());
        BandMember member3 = new BandMember("Bradley Tucker", "Singer", "https://media.istockphoto.com/photos/man-singing-on-microphone-picture-id852214814?k=20&m=852214814&s=612x612&w=0&h=pZuG6MvdNla_NEE8iV5lQMUFLnhErh1rK7TnjYzDBbA=",musician5.getEmail());
        ArrayList<BandMember> memberList = new ArrayList<>();
        bandMemberMap.put(member1.getName(), member1);
        bandMemberMap.put(member2.getName(), member2);
        bandMemberMap.put(member3.getName(), member3);
        memberList.add(member1);
        memberList.add(member2);
        memberList.add(member3);

        Band band = new Band("King Red", memberList);
        musician1.addBand(band);

        BandMember member4 = new BandMember("Ray Scott", "Piano", "https://image.freepik.com/free-photo/elegant-dressed-musician-playing-keyboards-front-view_23-2148673548.jpg",musician6.getEmail());
        bandMemberMap.put(member4.getName(), member4);

        musicianMap.put("musician@mail.com", musician1);
        musicianMap.put("musician2@mail.com", musician2);
        musicianMap.put(musician3.getEmail(),musician3);
        musicianMap.put(musician4.getEmail(),musician4);
        musicianMap.put(musician5.getEmail(),musician5);
        musicianMap.put(musician6.getEmail(),musician6);
        musicianMap.put(musician7.getEmail(),musician7);


        Event event1 = new Event("https://image.freepik.com/free-vector/rock-n-roll-poster-with-words-long-live-rock-n-roll-design-t-shirt_1284-49229.jpg", "Long Live Rock'n Roll", "Cafe Central", "14/01/22, 21:30",200,"Rock","Rock concert open air","910234212",12345);
        List<Event> eventList1 = new ArrayList<>();
        eventList1.add(event1);
        musicianEventsMap.put("musician@mail.com", eventList1);

        Notification notification1 = new Notification(R.drawable.check_circle, "Cafe Central accepted your request to perform for Long Live Rock'n Roll.");
        Notification notification2 = new Request(R.drawable.priority_high, "Kara Woolley wants to join your band King Red.", "musician2@mail.com", "King Red");
        List<Notification> notificationList1 = new ArrayList<>();
        notificationList1.add(notification1);
        notificationList1.add(notification2);
        musicianNotificationsMap.put("musician@mail.com", notificationList1);
        Event newEvent = new Event("https://i.ibb.co/PmqsXr7/Screenshot-2021-12-06-at-13-34-58.png","Jazz concert","New York","09/08/2022, 19:00",50,"Jazz","Jazz concert, all musicians or bands can apply","+121223454321",213412);
        Event newEvent2 = new Event("https://image.freepik.com/free-vector/abstract-geometric-shapes-music-festival-vertical-poster-template_23-2148984513.jpg","Music Fest","Lisbon","30/11/2022, 19:00",200,"World Music","Minimal Music fest, if you wanna join, please contact us","+351123456789",122334);
        Event newEvent3 = new Event("https://image.freepik.com/free-vector/music-festival-invitation-design-with-notes_1017-9868.jpg","Electronic music festival","Moscow","1/12/2022, 21:00",0,"electronic","Djs who want to mix, can apply to us","+74992343234",1234566);
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
        Event newEvent = new Event("https://i.ibb.co/PmqsXr7/Screenshot-2021-12-06-at-13-34-58.png","Jazz concert","New York","9/08/2022, 19:00",50,"Jazz","Jazz concert, all musicians or bands can apply","+121223454321",5420);
        Event newEvent2 = new Event("https://image.freepik.com/free-vector/abstract-geometric-shapes-music-festival-vertical-poster-template_23-2148984513.jpg","Music Fest","Lisbon","30/11/2022, 19:00",200,"World Music","Minimal Music fest, if you wanna join, please contact us","+351123456789",30);
        Event newEvent3 = new Event("https://image.freepik.com/free-vector/music-festival-invitation-design-with-notes_1017-9868.jpg","Electronic music festival","Moscow","1/12/2022, 21:00",0,"electronic","djs who want to mix, can apply to us","+74992343234",4565);
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

        if(location){
            filteredEvents.sort(Comparator.comparing(Event::getDistance));
        }
        return filteredEvents;

    }

    public List<BandRequest> applyFilterMembers(String genre, String instrument){
        List<BandRequest> filteredBandRequests = new ArrayList<>();
        filteredBandRequests.addAll(getAllRequests());

        if(genre != null){
            filteredBandRequests = filteredBandRequests.stream().filter(bandRequest -> bandRequest.getGenre().equalsIgnoreCase(genre)).collect(Collectors.toList());
        }

        if(instrument != null){
            filteredBandRequests = filteredBandRequests.stream().filter(bandRequest -> bandRequest.getInstruments().contains(instrument)).collect(Collectors.toList());
        }

        return filteredBandRequests;
    }

    public List<BandRequest> getAllRequests() {
        bandRequestList.clear();
        List<String> bandRequestInstruments = new ArrayList<>();
        bandRequestInstruments.add("Guitar");
        bandRequestInstruments.add("Drums");
        bandRequestInstruments.add("Singer");
        List<BandMember> bandMembers = new ArrayList<>();
        BandMember member1 = new BandMember("Bob Stuart","Guitar","https://image.freepik.com/free-photo/guitarist-plays-electric-guitar-with-bright-emotions-grey-background_186202-4755.jpg","bob@mail.com");
        bandMembers.add(member1);
        BandMember member2 = new BandMember(musician4.getName(),musician4.getInstruments(),musicianProfileMap.get(musician4.getEmail()).getPhoto(),musician4.getEmail());
        List<BandMember> metalMember = new ArrayList<>();
        metalMember.add(member2);
        BandRequest bandRequest1 = new BandRequest(bandRequestInstruments, "Rock",bandMembers, "The Firestorm", "https://image.freepik.com/fotos-gratis/inspiracao-banda-de-musicos-tocando-juntos-no-local-de-trabalho-de-arte-com-instrumentos_155003-16972.jpg");
        BandRequest bandRequest2 = new BandRequest(bandRequestInstruments, "Metal",metalMember,"Doom","https://static.vecteezy.com/system/resources/previews/003/516/797/non_2x/fire-doom-rain-free-photo.jpeg");
        bandRequestList.add(bandRequest1);
        bandRequestList.add(bandRequest2);

        return bandRequestList;
    }

    public void setMusicianProfile(String email, MusicianProfile musicianProfile) {
        musicianProfileMap.put(email, musicianProfile);
    }

    public BandMember getBandMember(String name) {
        return bandMemberMap.get(name);
    }


}
