/**
 * SYST 17796 Intermission Uno Project
 * Brandon Lamarre 2-12-2025
 */
package ca.sheridancollege.project;
import java.util.Scanner;

public class InputChecker {
    private static Scanner scanOBJ = new Scanner(System.in);  //import scanner
    public int intCheck(){ 
        int input;
        do{ 
            try {
                String s = scanOBJ.nextLine(); //attempt to parse input into INT
                input = Integer.parseInt(s);
                return input;
            }
            catch (Exception e)
            {
                System.out.println("Please Try Again"); //in failure, tell user to try again
            }
        }
        while (true);
    }
    public boolean isValidColor(String color) {
    return color.equals("RED") || color.equals("BLUE") ||
           color.equals("GREEN") || color.equals("YELLOW");
}



}
