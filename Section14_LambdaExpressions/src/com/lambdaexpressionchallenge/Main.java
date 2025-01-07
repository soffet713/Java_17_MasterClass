package com.lambdaexpressionchallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    private static Random random = new Random();

    record Person (String firstName, String lastName) {
        @Override
        public String toString() {
            return firstName + " " + lastName;
        }
    }

    public static void main(String[] args) {


        String[] firstNames = {"JoHn","DaVid","MatThEw","PaUl","SaRah","EmIlY","BriTtnEy","MaRtIn","CoNnOr","SAm",
                "MicHelLe","AbiGaIl","JeSsIca","MaRk","FreDdIe","LaUra","AlEx","ScOtT","MeGaN","NaTasHa","BoB","AnNa",
                "HaNnAh","AvA","EmMe","EvE","OtTo"};

        //List<Person> myPeople = getPeopleList(15);
        //List<String> myFirstNames = getFirstNameList(firstNames);

        //myFirstNames.forEach(s -> System.out.println(s));

        Arrays.setAll(firstNames,(i) -> firstNames[i].toUpperCase());
        //myFirstNames.replaceAll(s -> s.toUpperCase());
        List<String> myFirstNames = Arrays.asList(firstNames);
        System.out.println(myFirstNames);

        System.out.println("-".repeat(15));

        myFirstNames.replaceAll(s -> s + " " + (char) ('A' + random.nextInt(26)) + ".");
        myFirstNames.forEach(s -> System.out.println(s));

        System.out.println();
        System.out.println("-".repeat(35));
        myFirstNames.replaceAll(s -> s + " " + new StringBuilder(s.substring(0,s.indexOf(" "))).reverse());
        Arrays.asList(firstNames).forEach(s -> System.out.println(s));

        List<String> removeList = new ArrayList<>(List.of(firstNames));
        removeList.removeIf(s -> s.substring(0, s.indexOf(" ")).equalsIgnoreCase(s.substring(s.lastIndexOf(" ")+1)));
        System.out.println();
        System.out.println("-".repeat(35));
        removeList.forEach(s -> System.out.println(s));
        System.out.println();
        System.out.println("-".repeat(35));

        //Solution from Course//
        String[] firstNames1 = {"JoHn","DaVid","MatThEw","PaUl","SaRah","EmIlY","BriTtnEy","MaRtIn","CoNnOr","SAm",
                "MicHelLe","AbiGaIl","JeSsIca","MaRk","FreDdIe","LaUra","AlEx","ScOtT","MeGaN","NaTasHa","BoB","AnNa",
                "HaNnAh","AvA","EmMe","EvE","OtTo"};

        List<String> backedByArray = Arrays.asList(firstNames1);
        backedByArray.replaceAll(s -> s = s.toUpperCase() + " " + getRandomChar('A', 'H') + ".");
        System.out.println("-".repeat(25) + "Solution from Course" + "-".repeat(25));
        System.out.println("Adding middle initial");
        myFirstNames.forEach(s -> System.out.println(s));
        backedByArray.replaceAll(s -> s += " " + getReversedName(s.split(" ")[0]));
        System.out.println("--> Add reversed name as last name...");
        Arrays.asList(firstNames1).forEach(s -> System.out.println(s));

        List<String> newList = new ArrayList<>(List.of(firstNames1));
        newList.removeIf(s -> s.substring(0, s.indexOf(" ")).equals(s.substring(s.lastIndexOf(" ") + 1)));
        System.out.println("-".repeat(25) + "Remove names where last = first" + "-".repeat(25));
        newList.forEach(s -> System.out.println(s));


    }

    public static List<Person> getPeopleList(int size) {
        String[] firstNames = {"JoHn","DaVid","MatThEw","PaUl","SaRah","EmIlY","BriTtnEy","MaRtIn","CoNnOr","SAm",
                "MicHelLe","AbiGaIl","JeSsIca","MaRk","FreDdIe","LaUra","AlEx","ScOtT","MeGaN","NaTasHa","BoB","AnNa",
                "HaNnAh","AvA","EmMe","EvE","OtTo"};

        String[] lastNames = {"JeNkIns","SmItH","JoHnsOn","ScHmIdt","YaMamOto","JacKsoN","SiMpsOn","SaMpSon","TuLlU",
                "BeNsOn","ChrIstIansEn","GaRrIsOn","HaRrIsOn","WiLlIamS","ThOmAs","TaYlOr","MoOrE","KeLlY","GrAnGer"};

        List<Person> returnList = new ArrayList<>(size);
        Random namePicker = new Random();

        for(int i=0; i < size; i++) {
            returnList.add(new Person(firstNames[namePicker.nextInt(firstNames.length)],
                    lastNames[namePicker.nextInt(lastNames.length)]));
        }
        return returnList;
    }

    public static List<String> getFirstNameList(String[] list) {
        List<String> returnList = new ArrayList<>();

        for(int i=0; i<list.length; i++) {
            returnList.add(i,list[i].toUpperCase());
        }

        return returnList;
    }

    public static char getRandomChar(char startChar, char endChar) {
        return (char) random.nextInt((int) startChar, (int)endChar+1);
    }

    private static String getReversedName(String firstName) {
        return new StringBuilder(firstName).reverse().toString().toUpperCase();
    }
}
