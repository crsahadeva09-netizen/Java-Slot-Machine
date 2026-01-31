import java.util.*;
public class SlotMachine {
    public static void main(String[] args){
        //  JAVA SLOT MACHINE
        //  DECLARE VARIABLES
        Scanner sc = new Scanner(System.in);
        int balance = 100;
        int bet;
        int payout;
        String[] row;
        String playAgain;

        //  DISPLAY WELCOME MESSAGE

        System.out.println("*********************");
        System.out.println("Welcome to Java Slots  ");
        System.out.println("Symbols: A B C D E  ");
        System.out.println("*********************");

        //  PLAY IN BALANCE > 0
        while(balance>0){
            System.out.println("Current balance: $" + balance);
            System.out.println("Place your bet amount: ");

            //  ENTER BET AMOUNT
            bet = sc.nextInt();
            sc.nextLine();

            //  VERIFY IF BET > BALANCE

            if(bet>balance){
                System.out.println("Insufficient FUNDS");
                continue;
            }   //      VERIFY IF BET > 0;
            else if(bet <= 0){
                System.out.println("Bet must be greater than 0");
            }
            else{   //      SUBTRACT BET FROM BALANCE
                balance -= bet;
            }
            //  SPIN ROW
            System.out.println("Spinning...");
            row = spinRow();
            printRow(row);      //  PRINT ROW
            payout = getPayout(row, bet);       //  GET PAYOUT

            if(payout > 0){
                System.out.println("You won $" + payout);
                balance += payout;
            }
            else{
                System.out.println("Sorry you lost this round");
            }
            //  ASK TO PLAY AGAIN
            System.out.println("Do you want to play Again? (Y/N):");
            playAgain = sc.nextLine().toUpperCase();

            if(!playAgain.equals("Y")){
                break;
            }
        }
        //  DISPLAY EXIT MESSAGE

        sc.close();
    }

    static String[] spinRow(){

        String[] symbols = {"A", "B", "C", "D", "E"};
        String[] row = new String[3];
        Random random = new Random();


        for(int i=0; i<3; i++){
            row[i] = symbols[random.nextInt(0,symbols.length)];
        }

        System.out.println(row[0] + row[1] + row[2]);

        return row;
    }

    static void printRow(String[] row){
        System.out.println("***************");
        System.out.println(" " + String.join(" | ", row));
        System.out.println("***************");
    }

    static int getPayout(String[] row, int bet){

        if(row[0].equals(row[1]) && row[1].equals(row[2])){
            return switch(row[0]){
                case "A" -> bet*3;
                case "B" -> bet*4;
                case "C" -> bet*5;
                case "D" -> bet*10;
                case "E" -> bet*20;
                default -> 0;
            };
        }
        else if(row[0].equals(row[1])){
            return switch(row[0]){
                case "A" -> bet*2;
                case "B" -> bet*3;
                case "C" -> bet*4;
                case "D" -> bet*5;
                case "E" -> bet*20;
                default -> 0;
            };
        }
        else if(row[1].equals(row[2])){
            return switch(row[0]){
                case "A" -> bet*2;
                case "B" -> bet*3;
                case "C" -> bet*4;
                case "D" -> bet*5;
                case "E" -> bet*10;
                default -> 0;
            };
        }

        return 0;
    }
}
