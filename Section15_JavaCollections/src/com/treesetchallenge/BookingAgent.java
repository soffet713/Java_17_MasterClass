package com.treesetchallenge;

public class BookingAgent {

    public static void main(String[] args) {
        int rows = 26;
        int totalSeats = 832;
        Theatre chineseLA = new Theatre("Chinese Theatre",
                rows, totalSeats);

        chineseLA.printSeatMap();

        bookSeat(chineseLA, 'A', 3);
        bookSeat(chineseLA, 'A', 3);

        bookSeat(chineseLA, 'B', 1);
        bookSeat(chineseLA, 'B', 11);
        bookSeat(chineseLA, 'M', 1);

        bookSeats(chineseLA, 4, 'B', 3, 10);
        bookSeats(chineseLA, 6, 'B', 'C', 3, 10);
        bookSeats(chineseLA, 4, 'B', 1, 10);
        bookSeats(chineseLA, 4, 'B', 'C', 1, 10);
        bookSeats(chineseLA, 1, 'B', 'C', 1, 10);
        bookSeats(chineseLA, 4, 'M', 'Z', 1, 10);
        bookSeats(chineseLA, 10, 'A', 'E', 1, 10);
    }

    private static void bookSeat(Theatre theatre, char row, int seatNo) {
        String seat = theatre.reserveSeat(row, seatNo);
        if(seat != null) {
            System.out.println("Congratulations! Your reserved seat is " + seat);
            theatre.printSeatMap();
        } else {
            System.out.println("Sorry! Unable to reserve " + row + String.format("%03d", seatNo));
        }
    }

    private static void bookSeats(Theatre theatre, int tickets, char minRow, int minSeat, int maxSeat) {
        bookSeats(theatre, tickets, minRow, minRow, minSeat, maxSeat);
    }

    private static void bookSeats(Theatre theatre, int tickets, char minRow, char maxRow, int minSeat, int maxSeat) {
        var seats = theatre.reserveSeats(tickets,minRow,maxRow,minSeat,maxSeat);
        if(seats != null) {
            System.out.println("Congratulations! Your reserved seats are: " + seats);
            theatre.printSeatMap();
        } else {
            System.out.println("Sorry! No matching adjacent seats in rows: " + minRow + " - " + maxRow);
        }
    }
}
