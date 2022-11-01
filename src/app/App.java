package app;

import java.util.Scanner;

public class App {

    public static void start(){
        menu();
    }

    private static void menu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true){
            System.out.println("--------------------MENU----------------------");
            System.out.println();
            System.out.println("                1. ADD A SONG");
            System.out.println("                2. DELETE A SONG");
            System.out.println("                3. VIEW SONGS");
            System.out.println("                4. SHOW PREDICTION");
            System.out.println("                5. SAVE TO FILE");
            System.out.println("                6. EXIT");
            System.out.println();
            System.out.print("Enter your choice (1-6): ");

            try {
                choice = scanner.nextInt();
                switch(choice){
                    case 1:  Prediction.addSong(); break;
                    case 2:  Prediction.deleteSong(); break;
                    case 3:  Prediction.viewSongs(); break;
                    case 4:  Prediction.makePrediction(); break;
                    case 5:  Prediction.saveToFile(); break;
                    case 6: System.exit(0); break;
                    default:
                        System.out.println("Please enter a valid choice!");
                        break;
                }
            } catch (Exception e){
                System.out.println("Only numbers 1-6 are allowed!");
                menu();
            }
        }
    }
}
