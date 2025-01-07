package com.genericschallenge;

public class Main {

    public static void main(String[] args) {

        var nationalMonuments = new NationalMonument[]{
                new NationalMonument("Mount Rushmore","Sculpture","43.88097,-103.45386",
                        "United States","1941"),
                new NationalMonument("Statue of Liberty","Sculpture","40.69005,-7404446",
                        "United States","1924"),
                new NationalMonument("Eiffel Tower","Tower","48.85850,2.29448","France",
                        "1889"),
                new NationalMonument("Taj Mahal", "Mausoleum","27.17534,7804213",
                        "India","1648"),
                new NationalMonument("Parthenon", "Temple","37.97169,23.72671","Greece",
                        "447 BC"),
                new NationalMonument("Stone Henge", "Megalithic Structure","51.17900,-1.82622",
                        "England","Bronze Age (3300 to 1200 BC)"),
                new NationalMonument("Golden Gate Bridge","Bridge","37.82006,-122.47857",
                        "United States","1937"),
                new NationalMonument("Angkor Wat", "Temple", "13.41270,103.86695",
                        "Cambodia","1150"),
                new NationalMonument("Machu Picchu","Inca Citadel", "-13.16222,-72.54526",
                        "Peru","1450"),
                new NationalMonument("Great Pyramid of Giza", "Egyptian Pyramid",
                        "29.97937,31.13422","Egypt","2600 BC"),
                new NationalMonument("Leaning Tower of Pisa", "Bell Tower","43.72314,10.39659",
                        "Italy","1372"),
                new NationalMonument("Kinkaku-ji","Temple","35.03960,135.72919",
                        "Japan","1955"),
                new NationalMonument("Hachiko Memorial Statue","Statue","35.65926,139.70061",
                        "Japan","1948"),
                new NationalMonument("Fushimi Inari Taisha", "Shrine", "34.96792,135.77920",
                        "Japan","711 AD")
        };

        System.out.println("\n\tNational Monuments Around the World: \n");
        Layer<NationalMonument> monumentLayer = new Layer<>(nationalMonuments);
        monumentLayer.renderLayer();

        var nationalParks = new Park[] {
                new Park("Yellowstone","National Park","44.4882,-110.5916",
                    "United States","1872","2,221,766","acres"),
                new Park("Grand Canyon","National Park","36.0636,-112.1079",
                        "United States","1919","1902","mi^2"),
                new Park("Yosemite","National Park","37.8855,-119.5360",
                        "United States","1890","1169","mi^2"),
                new Park("Kruger National Park","National Park","-23.98815,31.55473",
                        "South Africa","1926","7523","mi^2"),
                new Park("Torres del Paine National Park","National Park","-50.94216,-73.40678",
                        "Chile","1959","927","mi^2"),
                new Park("Serengeti National Park","National Park","-2.33311,34.83333",
                        "Tanzania","1951","14,763","km^2"),
                new Park("Fuji-Hakone-Izu National Park","National Park","35.42555,138.67898",
                        "Japan","1936","1227","km^2"),
                new Park("Vatnajökull National Park","National Park","64.81909,-17.21357",
                        "Iceland","2008","14,141","km^2"),
                new Park("Triglav National Park","National Park","46.36891,14.11889",
                        "Slovenia","1981","340","mi^2"),
                new Park("Kakadu National Park","National Park","-13.09205,132.39377",
                        "Australia","1979","19,804","km^2"),
                new Park("Killarney National Park","National Park","52.02165,-9.50677",
                        "Ireland","1932","25,425","acres")
        };

        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println("\n\tNational Parks Around the World: \n");
        Layer<Park> parkLayer = new Layer<>(nationalParks);
        parkLayer.renderLayer();

        var majorRivers = new River[] {
                new River("Mississippi","United States",2340.0,"mi",
                        "47.2160,-95.2348","35.1556,-90.0659","29.1566, -89.2495"),
                new River("Missouri","United States",2341.0,"mi",
                        "45.9239,-111.4983","38.8146,-90.1218"),
                new River("Colorado","United States",1450.0,"mi",
                        "40.4708,-105.8286","36.1015,-112.0892","34.2964,-114.1148","31.7811,-114.7724"),
                new River("Delaware","United States",282.0,"mi",
                        "42.2026,-75.00836","39.4955,-75.5592"),
                new River("Nile","Africa",6650,"km","31.24755,29.92022",
                        "24.35409,32.84258","14.04431,32.32987","0.41818,33.19505","-2.28202,29.33138"),
                new River("Amazon","South America",3977,"mi",
                        "0.40615,-50.05085","-2.40864,-54.72626","-3.27388,-60.66581","-4.52312,-73.45631"),
                new River("Yangtze","China",3915.0,"mi",
                        "31.40695,121.88619","32.05932,118.69668","30.58501,114.30578","30.82616,111.00347",
                        "28.76936,104.64940"),
                new River("Congo","Africa",4700,"km","-6.05337,12.36333",
                        "-4.28292,15.30804","-0.55259,17.76591","0.50214,25.20631"),
                new River("Mekong","Asian",4909.0,"km","21.77332,101.12513",
                        "17.90809,102.77149","11.56361,104.94577","9.78584,106.56797"),
                new River("Parana","South America",4880, "km",
                        "-20.06437,-50.99921","-31.69611,-60.55762","-32.95817,-60.61321","-34.00200,-58.39647")
        };

        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println("\n\tMajor Rivers Around the World: \n");
        Layer<River> riverLayer = new Layer<>(majorRivers);
        riverLayer.renderLayer();
    }
}
