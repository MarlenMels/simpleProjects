import java.util.Random;
import java.util.Scanner;

public class Main{
    public static Scanner sc = new Scanner(System.in);
    public static Random random = new Random();
    public static String[] choices = {"rock", "paper", "scissors"};

    public static void main(String[] args){
        System.out.println("Welcome to the RockPaperScissors!");
        String playAgain;
        int compScore = 0;
        int playerScore = 0;
        do{
            String player = playerMove();
            String comp = compMove();
            System.out.println("Computer move: " + comp);
            String res = isWin(player, comp);

            if(res.equals("You win!")){
                playerScore++;
            } else if(res.equals("You lose!")){
                compScore++;
            } else{
                playerScore++;
                compScore++;
            }
            System.out.println(res);
            System.out.println("Total score(player/comp): " + playerScore + " - " + compScore);
            System.out.print("Play again?(yes/no): ");
            playAgain = sc.nextLine();
            while(!playAgain.equals("yes") && !playAgain.equals("no")){
                System.out.println("Invalid choice!");
                System.out.print("Play again?(yes/no): ");
                playAgain = sc.nextLine();
            }
        }while(playAgain.equals("yes"));
        System.out.println("Thanks for playing!");
        sc.close();
    }

    public static String compMove(){
        return choices[random.nextInt(3)];
    }

    public static String playerMove(){
        System.out.print("Enter you move(rock, paper, scissors): ");
        String move = sc.nextLine();
        while(!move.equals("rock") && !move.equals("paper") && !move.equals("scissors")){
            System.out.println("Invalid choice!");
            System.out.print("Enter you move(rock, paper, scissors): ");
            move = sc.nextLine();
        }
        return move;
    }

    public static String isWin(String playerMove,  String compMove){
        if(playerMove.equals(compMove)){
            return "It's tie";
        } else if((playerMove.equals("scissors") && compMove.equals("paper")) ||
                  (playerMove.equals("paper") && compMove.equals("rock")) ||
                  (playerMove.equals("rock") && compMove.equals("scissors"))){
            return "You win!";
        } else{
            return "You lose!";
        }
    }


}