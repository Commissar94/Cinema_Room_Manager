package cinema;

import java.util.Scanner;

public class Cinema {

    public static class globalVar {
        public static int[][] cinema;
        public static int rows;
        public static int seatsInRow;
        public static int allSeats;
        public static int currentIncome;
        public static int totalIncome;
        public static double bookedSeats;
        public static Scanner sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        CinemaRoomCreation();
        ShowMenu();
    }

    public static void CinemaRoomCreation() {
        System.out.println("Enter the number of rows:");
        globalVar.rows = globalVar.sc.nextInt() + 1;
        System.out.println("Enter the number of seats in each row:");
        globalVar.seatsInRow = globalVar.sc.nextInt() + 1;

        globalVar.cinema = new int[globalVar.rows][globalVar.seatsInRow];

        for (int i = 0; i < globalVar.rows; i++) {
            for (int j = 0; j < globalVar.seatsInRow; j++) {
                if (i == 0 && j != 0) {
                    globalVar.cinema[i][j] = j;
                } else if (j == 0) {
                    globalVar.cinema[i][j] = i;
                }
            }
        }
        globalVar.allSeats = (globalVar.rows - 1) * (globalVar.seatsInRow - 1);

                for (int i = 0; i < globalVar.rows - 1; i++) {
            for (int j = 0; j < globalVar.seatsInRow - 1; j++) {

                if (globalVar.allSeats < 60) {
                    globalVar.totalIncome = globalVar.totalIncome + 10;
                } else if (j < (globalVar.rows - 1) / 2) {
                    globalVar.totalIncome = globalVar.totalIncome + 10;
                } else {
                    globalVar.totalIncome = globalVar.totalIncome + 8;
                }
            }
        }
    }

    public static void ShowCinemaRoom() {
        System.out.println();
        System.out.println("Cinema: ");
        for (int i = 0; i < globalVar.rows; i++) {
            for (int j = 0; j < globalVar.seatsInRow; j++) {

                if (i == 0 && j == 0) {
                    System.out.print(" " + " ");
                    continue;
                }

                if (globalVar.cinema[i][j] == 0) {
                    System.out.print("S" + " ");
                } else if (globalVar.cinema[i][j] == -1) {
                    System.out.print("B" + " ");
                } else {
                    System.out.print(globalVar.cinema[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
        ShowMenu();
    }

    public static void ShowMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int option = globalVar.sc.nextInt();

        MenuHandler(option);
    }

    public static void MenuHandler(int option) {
        switch (option) {
            case 0:
                break;
            case 1:
                ShowCinemaRoom();
                break;
            case 2:
                BuyTicket(globalVar.sc);
                break;
            case 3:
                ShowStats();
                break;
        }
    }

    public static void BuyTicket(Scanner sc) {

        System.out.printf("%nEnter a row number:%n");
        int rowForTicket = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatForTicket = sc.nextInt();
        System.out.println();

        if (rowForTicket >= globalVar.rows || seatForTicket >= globalVar.seatsInRow ||
                rowForTicket <= 0 || seatForTicket <= 0) {
            System.out.println("Wrong input!");
            BuyTicket(sc);
        } else if (globalVar.cinema[rowForTicket][seatForTicket] == -1) {
            System.out.println("That ticket has already been purchased!");
            BuyTicket(sc);
        } else {
            globalVar.cinema[rowForTicket][seatForTicket] = -1;

            if (globalVar.allSeats < 60) {
                System.out.println("Ticket price: " + "$" + 10);
                globalVar.currentIncome = globalVar.currentIncome + 10;
                globalVar.bookedSeats++;
            } else if (rowForTicket < globalVar.rows / 2) {
                System.out.println("Ticket price: " + "$" + 10);
                globalVar.currentIncome = globalVar.currentIncome + 10;
                globalVar.bookedSeats++;
            } else {
                System.out.println("Ticket price: " + "$" + 8);
                globalVar.currentIncome = globalVar.currentIncome + 8;
                globalVar.bookedSeats++;
            }
        }
        ShowMenu();
    }

    public static void ShowStats() {

        double percent = globalVar.bookedSeats * 100 / globalVar.allSeats;

        System.out.printf("Number of purchased tickets: %.0f%n", globalVar.bookedSeats);
        System.out.printf("Percentage: %.2f%%%n", percent);
        System.out.printf("Current income: $%d%n", globalVar.currentIncome);
        System.out.printf("Total income: $%d%n%n", globalVar.totalIncome);

        ShowMenu();
    }
}