import javax.sound.sampled.*;
import java.io.*;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        String filePath = "src/MusicPlayer/musicWav/Eminem - Superman.wav";
        File file = new File(filePath);

        try(Scanner sc = new Scanner(System.in);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file)){


            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            String response = "";
            while(!response.equals("Q")){
                System.out.println("P = play");
                System.out.println("S = stop");
                System.out.println("R = reset");
                System.out.println("Q = quit");
                System.out.print("Enter your choice: ");
                response = sc.next().toUpperCase().trim();

                switch (response){
                    case "P" -> clip.start();
                    case "S" -> clip.stop();
                    case "R" -> clip.setMicrosecondPosition(0);
                    case "Q" -> clip.close();
                    default -> System.out.println("Invalid choice");
                }
            }
        }catch(UnsupportedAudioFileException e){
            System.out.println("Audio file is not supported");
        }catch(LineUnavailableException e){
            System.out.println("Unable to access audion resource");
        }catch(FileNotFoundException e){
            System.out.println("Could not locate file");
        }catch(IOException e){
            System.out.println("Something went wrong");
        }finally {
            System.out.println("Bye");
        }
    }
}