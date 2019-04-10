import java.util.*;
import java.io.*;
/**
 * Add a brief description of the program
 * 
 * @author  Qurrat-al-Ain Siddiqui
 * @instructor  Yumol
 * @date due February 2nd, 2018
 */

public class MarkerMenu
{
    public static void main(String []args) throws FileNotFoundException {
        // Create QuizMarker class object:
        QuizMarker q = new QuizMarker();
        // Create Scanner class object:
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the Quiz marker system");
        // Ask the user for correct answers file and store it in filename:
        System.out.print("Enter the filename for the correct answers: ");
        String filename = in.next();
        // Load the data from the correct answers data file:
        q.loadCorrectAnswers(filename);
        // Ask the user for the student responses file and store it in filename2:
        System.out.print("Enter the file that contains the student responses: ");
        String filename2 = in.next();
        // Load the data from the student response data file:
        q.loadStudentResponses(filename2);
        // Ask user for the menu option:
        char menuChoice = menu();
        // Exit only when user chooses E:
        while (menuChoice != 'E') {
            // Handle menu options:
            if (menuChoice == 'C') {
                q.printCorrectAnswers();
            } else {
                if(menuChoice == 'A') {
                    q.printStudentResponses();
                }else {
                    if (menuChoice == 'M') {
                        q.markStudentAnswers();
                    }else{
                        if (menuChoice == 'S') {
                            q.printQuizStats();
                        } else {
                            if (menuChoice == 'Q') {
                                q.printQuestionStats();
                            } else  {
                                System.out.println("ERROR: Invalid option chosen, please choose again.");
                            }
                        }
                    }
                } 
            }
            // Prompt the user for next input
            menuChoice = menu();
        }
    }

    /**
     *Presents the menu options to the user in order to run the program.
     *
     * @returns the uppercase character
     *
     */

    public static char menu() {

        // Create new scanner objects:
        Scanner in = new Scanner(System.in);
        // Print the menu options:
        System.out.println("Please enter your choice \n" +
            "   C - Print Correct Answers \n" +
            "   A - Print Student Answers \n" +
            "   M - Mark the Student Answers \n" +
            "   S - Produce the Quiz Statistics \n" +
            "   Q - Produce Question Statistics \n" +
            "   E - Exit the System");
        // Extract the choice from user input:
        char choice = in.next().charAt(0);
        // Return the choice to uppercase:
        return Character.toUpperCase(choice);
    }

}
