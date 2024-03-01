package org.hyperskill.CinemaRoomManager;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Theater {
    private String[][] seat;
    private int row;
    private int col;
    private int purchasedTickets;
    Scanner in = new Scanner(System.in);

    Theater () {
        setRow();
        setCol();
        this.purchasedTickets = 0;
        seat = new String[this.row][this.col];
        for (String[] strings : seat) {
            Arrays.fill(strings, "S");
        }

    }
    public void showSeats() {
        System.out.print(" ");
        for (int i=0;i<seat[0].length;i++) {
            System.out.print(" " + ((int)i+1));
        }
        System.out.println("");
        for(int i=0;i<seat.length;i++) {
            System.out.print(i + 1);
            for (int j = 0; j < seat[i].length; j++) {
                System.out.print(" " + seat[i][j]);
            }
            System.out.println("");
        }
    }
    public void setRow() {
        System.out.println("Enter the number of rows:");
        this.row = inputIntegers();
    }
    public void setCol() {
        System.out.println("Enter the number of seats in each row:");
        this.col = inputIntegers();
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    public int inputIntegers() {
        int x;
        x = in.nextInt();
        return x;
    }
    public void totalEstimatedIncome() {
        System.out.print("Total income:  ");
        int income;
        if(row * col <=60)  income = 10*row*col;
        else {
            income = ((row/2)*col*10) + ((row - (row/2))*col*8);
        }
        System.out.println(income);
    }
    public int getTicketPrice(int selectedRow) {
        if (row * col <= 60) {
            return 10;
        } else {
            return selectedRow <= row / 2 ? 10 : 8;
        }
    }
    public void buyTicket() {
        while(true) {
            int r, c;
            System.out.println("Enter a row number: ");
            r = in.nextInt();
            System.out.println("Enter a seat number in that row: ");
            c = in.nextInt();
            if(r<=this.row && c<=this.col)
            {
                if (seat[r - 1][c - 1].equals("S")) {
                    seat[r - 1][c - 1] = "B";
                    this.purchasedTickets++;
                    return ;
                } else {
                    System.out.println("That ticket has already been purchased!");
                }
            }
            else {
                System.out.println("Wrong Input");
            }

        }
    }

    public void printTicketPrice(int ticketPrice) {
        System.out.println("Ticket price: $" + ticketPrice);
    }

    public void currentIncome() {
        int sum=0;
        for(int i=0;i<seat.length;i++) {
            for (int j = 0; j < seat[i].length; j++) {
                if(seat[i][j].equals("B")) {
                    sum+=getTicketPrice(i+1);
                }
            }
        }
        System.out.println("Current income: $" + sum);
    }
    public void getStatistics() {
        System.out.printf("Percentage: %.2f%%\n",((double)purchasedTickets/(this.row*this.col))*100);
        currentIncome();
        totalEstimatedIncome();
    }
    public void menu() {
        int option;
        do {
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3: Statistics");
            System.out.println("0. Exit");

            option = inputIntegers();

            switch (option) {
                case 1:
                    showSeats();
                    break;
                case 2:
                    buyTicket();
                break;
                case 3:
                    getStatistics();
                    break;
                case 0:
                    System.out.println("Exiting program. Thank you!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option!=0);
    }

    public static void main(String[] args) {
        Theater ob = new Theater();
        ob.menu();


    }
}