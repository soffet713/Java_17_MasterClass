package com.generics;

interface Player {
    String name();
    String position();
}

record BaseballPlayer(String name, String position) implements Player {}
record FootballPlayer(String name, String position) implements Player {}
record HockeyPlayer(String name, String position) implements Player {}
record BasketballPlayer(String name, String position) implements Player {}

public class Main {

    public static void main(String[] args) {

        BaseballTeam phillies1 = new BaseballTeam("Philadelphia Phillies");
        BaseballTeam astros1 = new BaseballTeam("Houston Astros");
        BaseballTeam cubs1 = new BaseballTeam("Chicaco Cubs");
        BaseballTeam giants1 = new BaseballTeam("San Francisco Giants");
        BaseballTeam dodgers1 = new BaseballTeam("Los Angeles Dodgers");
        BaseballTeam braves1 = new BaseballTeam("Atlanta Braves");

        SportsTeam phillies2 = new SportsTeam("Philadelphia Phillies");
        SportsTeam astros2 = new SportsTeam("Houston Astros");
        SportsTeam cubs2 = new SportsTeam("Chicaco Cubs");
        SportsTeam giants2 = new SportsTeam("San Francisco Giants");
        SportsTeam dodgers2 = new SportsTeam("Los Angeles Dodgers");
        SportsTeam braves2 = new SportsTeam("Atlanta Braves");

        var philly = new Affiliation("Philadelphia, PA", "city", "US");
        var houston = new Affiliation("Houston, TX", "city", "US");
        var chicago = new Affiliation("Chicago, IL", "city", "US");
        var sanfran = new Affiliation("San Francisco, CA", "city", "US");
        var la = new Affiliation("Los Angeles, CA", "city", "US");
        var atl = new Affiliation("Atlanta, GA", "city", "US");
        Team<BaseballPlayer, Affiliation> phillies = new Team<>("Philadelphia Phillies", philly);
        Team<BaseballPlayer, Affiliation> astros = new Team<>("Houston Astros", houston);
        Team<BaseballPlayer, Affiliation> cubs = new Team<>("Chicaco Cubs", chicago);
        Team<BaseballPlayer, Affiliation> giants = new Team<>("San Francisco Giants", sanfran);
        Team<BaseballPlayer, Affiliation> dodgers = new Team<>("Los Angeles Dodgers", la);
        Team<BaseballPlayer, Affiliation> braves = new Team<>("Atlanta Braves", atl);

        scoreResult(phillies,3,astros,5);
        scoreResult(cubs,7,braves,2);
        scoreResult(giants,4,dodgers,3);
        scoreResult(braves,3,dodgers,5);
        scoreResult(astros,5,cubs,6);
        scoreResult(braves,3,phillies,4);
        scoreResult(giants,5,braves,1);

        var harper = new BaseballPlayer("B Harper", "First Base");
        var dahl = new BaseballPlayer("D Dahl", "Left Fielder");
        var pache = new BaseballPlayer("C Pache", "Center Field");
        var bohm = new BaseballPlayer("A Bohm", "Third Base");
        var castellanos = new BaseballPlayer("N Castellanos", "Right Fielder");
        var stott = new BaseballPlayer("B Stott", "Second Base");
        var sosa = new BaseballPlayer("E Sosa", "Short Stop");
        var marchan = new BaseballPlayer("R Marchan", "Catcher");
        var wheeler = new BaseballPlayer("Z Wheeler", "Pitcher");
        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println();
        BaseballPlayer[] phillyPlayers = new BaseballPlayer[] {harper,dahl,pache,bohm,castellanos,stott,sosa,
                marchan,wheeler};
        addPlayers(phillies,phillyPlayers);
        phillies.listTeamMembers();

        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println();

        var singleton = new BaseballPlayer("J Singleton", "First Base");
        var alvarez = new BaseballPlayer("Y Alvarez", "Left Fielder");
        var meyers = new BaseballPlayer("J Meyers", "Center Field");
        var bregman = new BaseballPlayer("A Bregman", "Third Base");
        var tucker = new BaseballPlayer("K Tucker", "Right Fielder");
        var altuve = new BaseballPlayer("J Altuve", "Second Base");
        var pena = new BaseballPlayer("J Pena", "Short Stop");
        var diaz = new BaseballPlayer("Y Diaz", "Catcher");
        var bloss = new BaseballPlayer("J Bloss", "Pitcher");
        BaseballPlayer[] astrosPlayers = new BaseballPlayer[] {singleton,alvarez,meyers,bregman,tucker,altuve,
                pena,diaz,bloss};
        addPlayers(astros,astrosPlayers);
        astros.listTeamMembers();

        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println();

        var wisdom = new BaseballPlayer("P Wisdom", "First Base");
        var mastrobuoni = new BaseballPlayer("M Mastrobuoni", "Second Base");
        var morel = new BaseballPlayer("C Morel", "Third Base");
        var happ = new BaseballPlayer("I Happ", "Left Fielder");
        var bellinger = new BaseballPlayer("C Bellinger", "Center Field");
        var suzuki = new BaseballPlayer("S Suzuki", "Right Fielder");
        var swanson = new BaseballPlayer("D Swanson", "Short Stop");
        var amaya = new BaseballPlayer("M Amaya", "Catcher");
        var imanaga = new BaseballPlayer("S Imanaga", "Pitcher");
        BaseballPlayer[] cubsPlayers = new BaseballPlayer[] {wisdom,mastrobuoni,morel,happ,bellinger,suzuki,
                swanson,amaya,imanaga};
        addPlayers(cubs,cubsPlayers);
        cubs.listTeamMembers();

        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println();

        var brooks = new BaseballPlayer("T Brooks", "First Base");
        var villar = new BaseballPlayer("D Villar", "Second Base");
        var chapman = new BaseballPlayer("M Chapman", "Third Base");
        var ramos = new BaseballPlayer("H Ramos", "Left Fielder");
        var matos = new BaseballPlayer("L Matos", "Center Field");
        var slater = new BaseballPlayer("A Slater", "Right Fielder");
        var wisely = new BaseballPlayer("B Wisely", "Short Stop");
        var bailey = new BaseballPlayer("P Bailey", "Catcher");
        var hicks = new BaseballPlayer("J Hicks", "Pitcher");
        BaseballPlayer[] giantsPlayers = new BaseballPlayer[] {brooks,villar,chapman,ramos,matos,slater,
                wisely,bailey,hicks};
        addPlayers(giants,giantsPlayers);
        giants.listTeamMembers();

        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println();

        var freeman = new BaseballPlayer("F Freeman", "First Base");
        var biggio = new BaseballPlayer("C Biggio", "Second Base");
        var hernandez = new BaseballPlayer("E Hernandez", "Third Base");
        var taylor = new BaseballPlayer("C Taylor", "Left Fielder");
        var pages = new BaseballPlayer("A Pages", "Center Field");
        var heyward = new BaseballPlayer("J Heyward", "Right Fielder");
        var rojas = new BaseballPlayer("M Rojas", "Short Stop");
        var smith = new BaseballPlayer("W Smith", "Catcher");
        var miller = new BaseballPlayer("B Miller", "Pitcher");
        BaseballPlayer[] dodgersPlayers = new BaseballPlayer[] {freeman,biggio,hernandez,taylor,pages,heyward,
                rojas,smith,miller};
        addPlayers(dodgers,dodgersPlayers);
        dodgers.listTeamMembers();

        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println();

        var olson = new BaseballPlayer("M Olson", "First Base");
        var albies = new BaseballPlayer("O Albies", "Second Base");
        var riley = new BaseballPlayer("A Riley", "Third Base");
        var duvall = new BaseballPlayer("A Duvall", "Left Fielder");
        var kelenic = new BaseballPlayer("J Kelenic", "Center Field");
        var wall = new BaseballPlayer("F Wall", "Right Fielder");
        var arcia = new BaseballPlayer("O Arcia", "Short Stop");
        var darnaud = new BaseballPlayer("T d'Arnaud", "Catcher");
        var fried = new BaseballPlayer("M Fried", "Pitcher");
        BaseballPlayer[] bravesPlayers = new BaseballPlayer[] {olson,albies,riley,duvall,kelenic,wall,
                arcia,darnaud,fried};
        addPlayers(braves,bravesPlayers);
        braves.listTeamMembers();

        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println();

        SportsTeam chiefs1 = new SportsTeam("Kansas City Chiefs");
        SportsTeam niners1 = new SportsTeam("San Francisco 49ers");
        SportsTeam texans1 = new SportsTeam("Houston Texans");
        SportsTeam bucs1 = new SportsTeam("Tampa Bay Buccaneers");
        SportsTeam bears1 = new SportsTeam("Chicago Bears");
        SportsTeam bengals1 = new SportsTeam("Cincinnati Bengals");

        var kc = new Affiliation("Kansas City, MO", "city", "US");
        var tampa = new Affiliation("Tampa Bay, FL", "city", "US");
        var cinci = new Affiliation("Cincinnati, OH", "city", "US");
        Team<FootballPlayer, Affiliation> chiefs = new Team<>("Kansas City Chiefs", kc);
        Team<FootballPlayer, Affiliation> niners = new Team<>("San Francisco 49ers", sanfran);
        Team<FootballPlayer, Affiliation> texans = new Team<>("Houston Texans", houston);
        Team<FootballPlayer, Affiliation> bucs = new Team<>("Tampa Bay Buccaneers", tampa);
        Team<FootballPlayer, Affiliation> bears = new Team<>("Chicago Bears", chicago);
        Team<FootballPlayer, Affiliation> bengals = new Team<>("Cincinnati Bengals", cinci);

        var mahomes = new FootballPlayer("P Mahomes", "Quarterback");
        var pacheco = new FootballPlayer("I Pacheco", "Runningback");
        var rice = new FootballPlayer("R Rice", "Wide Receiver");
        var kelce = new FootballPlayer("T Kelce", "Tight End");
        var jtaylor = new FootballPlayer("J Taylor", "Offensive Tackle");
        var omenihu = new FootballPlayer("C Omenihu", "Defensive Tackle");
        var jones = new FootballPlayer("C Jones", "Defensive End");
        var bolton = new FootballPlayer("N Bolton", "Linebacker");
        var watson = new FootballPlayer("J Watson", "Cornerback");
        var reid = new FootballPlayer("J Reid", "Safety");
        var butker = new FootballPlayer("H Butker", "Kicker");
        var araiza = new FootballPlayer("M Araiza", "Punter");
        FootballPlayer[] chiefsPlayers = new FootballPlayer[] {mahomes,pacheco,rice,kelce,jtaylor,omenihu,
                jones,bolton,watson,reid,butker,araiza};
        addPlayers(chiefs,chiefsPlayers);
        chiefs.listTeamMembers();

        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println();

        var purdy = new FootballPlayer("B Purdy", "Quarterback");
        var mccaffrey = new FootballPlayer("C McCaffrey", "Running Back");
        var samuel = new FootballPlayer("D Samuel Sr", "Wide Receiver");
        var kittle = new FootballPlayer("G Kittle", "Tight End");
        var williams = new FootballPlayer("T Williams", "Offensive Tackle");
        var bosa = new FootballPlayer("N Bosa", "Defensive Tackle");
        var hargrave = new FootballPlayer("J Hargrave", "Defensive End");
        var warner = new FootballPlayer("F Warner", "Linebacker");
        var ward = new FootballPlayer("C Ward", "Cornerback");
        var hufanga = new FootballPlayer("T Hufanga", "Safety");
        var moody = new FootballPlayer("J Moody", "Kicker");
        var wishnowsky = new FootballPlayer("M Wishnowsky", "Punter");
        FootballPlayer[] ninersPlayers = new FootballPlayer[] {purdy,mccaffrey,samuel,kittle,williams,
                bosa,hargrave,warner,ward,hufanga,moody,wishnowsky};
        addPlayers(niners,ninersPlayers);
        niners.listTeamMembers();

        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println();

        var stroud = new FootballPlayer("CJ Stroud", "Quarterback");
        var mixon = new FootballPlayer("J Mixon", "Runningback");
        var collins = new FootballPlayer("N Collins", "Wide Receiver");
        var schultz = new FootballPlayer("D Schultz", "Tight End");
        var tunsil = new FootballPlayer("L Tunsil", "Offensive Tackle");
        var autry = new FootballPlayer("D Autry", "Defensive Tackle");
        var hinish = new FootballPlayer("K Hinish", "Defensive End");
        var alshaair = new FootballPlayer("A Al-Shaair", "Linebacker");
        var henderson = new FootballPlayer("CJ Henderson", "Cornerback");
        var jward = new FootballPlayer("J Ward", "Safety");
        var fairbairn = new FootballPlayer("K Faibairn", "Kicker");
        var townsend = new FootballPlayer("T Townsend", "Punter");
        FootballPlayer[] texansPlayers = new FootballPlayer[] {stroud,mixon,collins,schultz,tunsil,autry,
                hinish,alshaair,henderson,jward,fairbairn,townsend};
        addPlayers(texans,texansPlayers);
        texans.listTeamMembers();

        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println();

        var mayfield = new FootballPlayer("B Mayfield", "Quarterback");
        var edmonds = new FootballPlayer("C Edmonds", "Runningback");
        var evans = new FootballPlayer("M Evans", "Wide Receiver");
        var otton = new FootballPlayer("C Otton", "Tight End");
        var wirfs = new FootballPlayer("T Wirfs", "Offensive Tackle");
        var gholston = new FootballPlayer("W Gholston", "Defensive Tackle");
        var vea = new FootballPlayer("V Vea", "Defensive End");
        var david = new FootballPlayer("L David", "Linebacker");
        var hall = new FootballPlayer("B Hall", "Cornerback");
        var whitehead = new FootballPlayer("J Whitehead", "Safety");
        var mclaughlin = new FootballPlayer("C McLaughlin", "Kicker");
        var camarda = new FootballPlayer("J Camarda", "Punter");
        FootballPlayer[] bucsPlayers = new FootballPlayer[] {mayfield,edmonds,evans,otton,wirfs,gholston,
                vea,david,hall,whitehead,mclaughlin,camarda};
        addPlayers(bucs,bucsPlayers);
        bucs.listTeamMembers();

        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println();

        var cwilliams = new FootballPlayer("C Williams", "Quarterback");
        var swift = new FootballPlayer("D Swift", "Runningback");
        var moore = new FootballPlayer("DJ Moore", "Wide Receiver");
        var kmet = new FootballPlayer("C Kmet", "Tight End");
        var pryor = new FootballPlayer("M Pryor", "Offensive Tackle");
        var sweat = new FootballPlayer("M Sweat", "Defensive Tackle");
        var billings = new FootballPlayer("A Billings", "Defensive End");
        var edwards = new FootballPlayer("TJ Edwards", "Linebacker");
        var johnson = new FootballPlayer("J Johnson", "Cornerback");
        var owens = new FootballPlayer("J Owens", "Safety");
        var santos = new FootballPlayer("C Santos", "Kicker");
        var waitman = new FootballPlayer("C Waitman", "Punter");
        FootballPlayer[] bearsPlayers = new FootballPlayer[] {cwilliams,swift,moore,kmet,pryor,sweat,
                billings,edwards,johnson,owens,santos,waitman};
        addPlayers(bears,bearsPlayers);
        bears.listTeamMembers();

        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println();

        var burrow = new FootballPlayer("J Burrow", "Quarterback");
        var moss = new FootballPlayer("Z Moss", "Runningback");
        var chase = new FootballPlayer("J Chase", "Wide Receiver");
        var hudson = new FootballPlayer("T Hudson", "Tight End");
        var brown = new FootballPlayer("T Brown", "Offensive Tackle");
        var hendrickson = new FootballPlayer("T Hendrickson", "Defensive Tackle");
        var rankins = new FootballPlayer("S Rankins", "Defensive End");
        var wilson = new FootballPlayer("L Wilson", "Linebacker");
        var hilton = new FootballPlayer("M Hilton", "Cornerback");
        var bell = new FootballPlayer("V Bell", "Safety");
        var mcpherson = new FootballPlayer("E McPherson", "Kicker");
        var robbins = new FootballPlayer("B Robbins", "Punter");
        FootballPlayer[] bengalsPlayers = new FootballPlayer[] {burrow,moss,chase,hudson,brown,hendrickson,
                rankins,wilson,hilton,bell,mcpherson,robbins};
        addPlayers(bengals,bengalsPlayers);
        bengals.listTeamMembers();

        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println();
    }

    public static void scoreResult(BaseballTeam team1, int t1_score, BaseballTeam team2, int t2_score) {
        String message = team1.setScore(t1_score,t2_score);
        team2.setScore(t2_score,t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }

    public static void scoreResult(SportsTeam team1, int t1_score, SportsTeam team2, int t2_score) {
        String message = team1.setScore(t1_score,t2_score);
        team2.setScore(t2_score,t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }

    public static void scoreResult(Team team1, int t1_score, Team team2, int t2_score) {
        String message = team1.setScore(t1_score,t2_score);
        team2.setScore(t2_score,t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }

    public static void addPlayers(BaseballTeam team, BaseballPlayer[] players) {
        for(BaseballPlayer player : players) {
            team.addTeamMember(player);
        }
    }

    public static void addPlayers(SportsTeam team, Player[] players) {
        for(Player player : players) {
            team.addTeamMember(player);
        }
    }

    public static void addPlayers(Team team, Player[] players) {
        for(Player player : players) {
            team.addTeamMember(player);
        }
    }
}
