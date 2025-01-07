package com.treesetchallenge;

import java.util.*;

public class Theatre {

    class Seat implements Comparable<Seat> {
        private String seatValue;
        private char rowNumber;
        private int seatNumber;
        private boolean reserved = false;

        protected Seat(char rowNumber, int seatNumber) {
            if(rowNumber>96) { rowNumber-=32; }
            this.rowNumber = rowNumber;
            this.seatNumber = seatNumber;
            this.seatValue = (rowNumber + String.format("%03d", seatNumber));
        }

        public void reaserveSeat() {
            reserved = true;
        }

        public int getSeatNumber() {
            return seatNumber;
        }

        public String getSeatValue() {
            return seatValue;
        }

        public char getRowNumber() {
            return rowNumber;
        }

        public boolean isReserved() {
            return reserved;
        }

        @Override
        public String toString() {
            //return "%s (%s)".formatted(seatValue, isReserved() ? "Reserved" : "Available");
            return "%s %-3s".formatted(seatValue, isReserved() ? "(\u25CF)" : "");
        }

        @Override
        public int compareTo(Seat o) {
            return seatValue.compareTo(o.seatValue);
        }
    }

    private String theatreName;
    private int seatsInRow;
    private TreeSet<Seat> seats;

    public Theatre(String theatreName, int rows, int totalSeats) {
        this.theatreName = theatreName;
        if(rows>26) { rows = 26; }
        if(rows<=0) { rows = 1;}
        this.seatsInRow = totalSeats / rows;
        char maxRow = (char)('A' + rows);
        List<Seat> seatList = new ArrayList<Seat>(totalSeats);
        for(char rowLetter = 'A'; rowLetter < maxRow; rowLetter++) {
            for(int s = 1; s <= seatsInRow; s++) {
                seatList.add(new Seat(rowLetter,s));
            }
        }
        seats = new TreeSet<>(seatList);
    }

    public String reserveSeat(char row, int seat) {
        Seat requestedSeat = new Seat(row, seat);
        Seat requested = seats.ceiling(requestedSeat);

        if(requested==null || !requested.seatValue.equals(requestedSeat.seatValue)) {
            System.out.print("--> No such seat: " + requestedSeat);
            System.out.printf(": Seat must be between %s and %s%n", seats.first().seatValue, seats.last().seatValue);
        } else {
            if(!requested.isReserved()) {
                requested.reaserveSeat();
                System.out.println("Reserving seat: " + requested);
                return requested.seatValue;
            } else {
                System.out.println("Seat's already reserved.");
            }
        }
        return null;
    }

    private boolean validate(int count, char first, char last, int min, int max) {

        boolean result = (min > 0 || seatsInRow >= count || (max - min + 1) >= count);
        result = result && seats.contains(new Seat(first, min));
        if(!result) {
            System.out.printf("Invalid! %1$d seats between %2$c[%3$d-%4$d]-%5$c[%3$d-%4$d] Try again", count,
                    first, min, max, last);
            System.out.printf(": Seat must be between %s and %s%n", seats.first().seatValue, seats.last().seatValue);
        }
        return result;
    }

    public Set<Seat> reserveSeats(int count, char minRow, char maxRow, int minSeat, int maxSeat) {
        char lastValid = seats.last().seatValue.charAt(0);
        maxRow = (maxRow < lastValid) ? maxRow : lastValid;

        if(!validate(count, minRow, maxRow, minSeat, maxSeat)) {
            return null;
        }

        NavigableSet<Seat> selected = null;

        for(char rowLetter = minRow; rowLetter <= maxRow; rowLetter++) {
            NavigableSet<Seat> adjacentSeats = seats.subSet(new Seat(rowLetter, minSeat), true,
                    new Seat(rowLetter, maxSeat), true);

            int index = 0;
            Seat first = null;
            for(Seat s : adjacentSeats) {
                if (s.reserved) {
                    index = 0;
                    continue;
                }
                first = (index==0) ? s : first;
                if(++index == count) {
                    selected = adjacentSeats.subSet(first, true, s, true);
                    break;
                }
            }
            if(selected != null) {
                break;
            }
        }

        Set<Seat> reservedSeats = null;
        if(selected != null) {
            selected.forEach(Seat::reaserveSeat);
            reservedSeats = new TreeSet<>(selected);
        }
        return reservedSeats;
    }

    public void printSeatMap() {
        char prevSeatRow = 0;
        String separator = "-".repeat(100);
        System.out.printf("%1$s%n%2$s Seat Map%n%1$s%n", separator,theatreName);
        for(Seat s : seats) {
            char currSeatRow = s.getRowNumber();
            if(prevSeatRow!=currSeatRow&&prevSeatRow!=0) { System.out.println(); }
            System.out.print(s);
            System.out.print((s.getSeatNumber() < seatsInRow) ? " | " : "");
            prevSeatRow = currSeatRow;
        }
        System.out.println("\n" + separator);
    }

    //function from course
    public void printSeatMap1() {
        String separator = "-".repeat(100);
        System.out.printf("%1$s%n%2$s Seat Map%n%1$s%n", separator,theatreName);
        int index = 0;

        for(Seat s : seats) {
            System.out.printf("%-16s | %s", s, ((index++ + 1) % seatsInRow ==0) ? "\n" : "");
        }
        System.out.println(separator);
    }
}
