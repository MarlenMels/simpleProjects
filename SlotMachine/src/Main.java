import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args){
        String playAgain = "Y";
        System.out.println("Welcome to Java Slots");
        System.out.println("Symbols: 🍒 🍉 🍋 🔔 ⭐");

        int balance = readValidBalance();
        int bet;
        int payout;
        int netProfit = 0;

        while(playAgain.equals("Y")){
            bet = readValidBet(balance);
            String[] row = spinRow();
            printRow(row);
            payout = getPayout(row, bet);
            if(payout > 0){
                System.out.println("You won $" + payout);
                balance += payout;
                netProfit += (payout - bet);
            } else {
                System.out.println("Sorry you lost this round! ");
                netProfit -= bet;
            }
            balance -= bet;
            System.out.println("Balance: " + balance);
            System.out.print("Do you want to play again?(Y/N): ");
            playAgain = sc.nextLine().trim().toUpperCase();
            if(!playAgain.equals("Y")){
                break;
            }
        }
        System.out.println("Total balance: " + balance);
        System.out.println("Total net profit: " + netProfit);
        sc.close();

    }

    public static int readValidBalance(){
        int balance = 0;
        boolean isValid = false;
        System.out.print("Please enter a valid balance (> 0): ");
        while(!isValid) {
            try {
                balance = sc.nextInt();
                sc.nextLine();
                if (balance <= 0) {
                    System.out.println("Invalid balance!");
                    System.out.print("Please enter the valid balance: ");
                    continue;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid balance!");
                System.out.print("Please enter the valid balance: ");
                sc.nextLine();
                continue;
            }
            isValid = true;
        }
        return balance;
    }

    public static int readValidBet(int balance){
        boolean isValid = false;
        int bet = 0;
        System.out.print("Please enter a valid bet (> 0): ");
        while(!isValid){
            try{
                bet = sc.nextInt();
                sc.nextLine();
                if(bet > balance || bet <= 0){
                    System.out.println("Invalid bet amount!");
                    System.out.print("Please re-enter the bet amount: ");
                    continue;
                }
            } catch(InputMismatchException e){
                System.out.println("Invalid bet amount!");
                System.out.print("Please re-enter the bet amount: ");
                sc.nextLine();
                continue;
            }
            isValid = true;
        }
        return bet;
    }

    public static String[]  spinRow(){
        String[] slots = {"🍒", "🍉", "🍋", "🔔", "⭐"};
        String slot1 = slots[random.nextInt(5)];
        String slot2 = slots[random.nextInt(5)];
        String slot3 = slots[random.nextInt(5)];

        return new String[]{slot1, slot2, slot3};

    }

    public static void printRow(String[] row){
        System.out.println("**************");
        System.out.println(" " + String.join(" | ", row));
        System.out.println("**************");
    }

    static int getPayout(String[] row, int bet){
        if(row[0].equals(row[1]) && row[1].equals(row[2])){
            return switch(row[0]){
                case "🍒" -> bet * 3;
                case "🍉" -> bet * 4;
                case "🍋" -> bet * 5;
                case "🔔" -> bet * 10;
                case "⭐" -> bet * 20;
                default -> 0;
            };
        }
        else if(row[0].equals(row[1])){
            return switch(row[0]){
                case "🍒" -> bet * 2;
                case "🍉" -> bet * 3;
                case "🍋" -> bet * 4;
                case "🔔" -> bet * 5;
                case "⭐" -> bet * 10;
                default -> 0;
            };
        }
        else if(row[1].equals(row[2])){
            return switch(row[1]){
                case "🍒" -> bet * 2;
                case "🍉" -> bet * 3;
                case "🍋" -> bet * 4;
                case "🔔" -> bet * 5;
                case "⭐" -> bet * 10;
                default -> 0;
            };
        }
        else if(row[2].equals(row[0])){
            return switch(row[0]){
                case "🍒" -> bet * 2;
                case "🍉" -> bet * 3;
                case "🍋" -> bet * 4;
                case "🔔" -> bet * 5;
                case "⭐" -> bet * 10;
                default -> 0;
            };
        }
        return 0;
    }
}