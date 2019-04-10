import java.util.*;
import java.io.*;
/**
 *Class QuizMarker, loads the data, processes it and prints the information
 *
 * @author  Qurrat-al-Ain Siddiqui
 * @instructor  Yumol
 * @date due February 2nd, 2018
 */
public class QuizMarker {
    // Array of Strings that store the student information:
    private String [] names;
    // Array of char to store the correct answers to quiz:
    private char [] corrAnswers;
    // 2-D array of char to store the student responses to the quiz:
    private char [] [] studentResponse;
    // Number of questions on quiz:
    private int numQuestions;
    // Number of students:
    private int numStudents;
    // Marks of each student:
    private int [] marks;
    // An array to store the number of students who answered the 
    // question(s) correctly:
    private int [] quizStats;
    final int NAMES_SIZE = 100;
    final int ANSWERS_SIZE = 100;
    final int RESPONSES_SIZE = 200;

    /**
     * The default constructor.
     */
    public QuizMarker() {
        this.names = new String [NAMES_SIZE];
        this.corrAnswers = new char[ANSWERS_SIZE];
        this.studentResponse = new char[RESPONSES_SIZE] [RESPONSES_SIZE];
        this.numQuestions = 0;
        this.numStudents = 0;
    }

    /**
     * A method to load the correct answers data from correct answers file.
     * 
     * @param filename The name of the file
     * 
     */
    public void loadCorrectAnswers(String filename) throws FileNotFoundException {
        // Create a Scanner object that reads in from file:
        Scanner fileIn = new Scanner(new File(filename));
        // Keep reading until lines to read:
        while (fileIn.hasNext()) {
            // Read in the line and turn to a String:
            String answers = fileIn.next();
            // Traverse the String and store each character to the corrAnswers array.
            // Also, update the numQuestions based on numner of characters/answers read:
            for (int i = 0; i < answers.length(); i++) {
                corrAnswers[i] = Character.toUpperCase(answers.charAt(i));
                numQuestions++;
            }
        }
    }

    /**
     * A method to print the correct answers data.
     */
    public void printCorrectAnswers() {
        // Print correct answers:
        System.out.println("Correct Answers:");
        // Print the index for every question, 
        // and have them separated by a tab:
        for (int i = 0; i < numQuestions; i++) {
            System.out.print(i+1 + "\t");
        }
        // Print new line:
        System.out.println();
        // Loop through the numQuestions to print the correct answers,
        // and have them separated by a tab:
        for (int i = 0; i < numQuestions; i++) {
            System.out.print(corrAnswers[i] + "\t");
        }
        // Print new line:
        System.out.println();
    }

    /**
     * A method to load the studentResponses array 
     * and numStudents variable from the appropriae data file.
     * 
     * @param filename2 The name of the student response file
     */
    public void loadStudentResponses(String filename2) throws FileNotFoundException {
        // Create a scanner object that reads in from file:
        Scanner fileIn = new Scanner(new File(filename2));
        // Initialize the index that corresponds to row number/student number:
        int index = 0;
        // Keep reading until lines to read:
        while (fileIn.hasNext()) {
            // Read two lines at a time, and note that the
            // first line corresponds to student name, while
            // the second line corresponds to student responses.
            // Read the first line and store it in String name:
            String name = fileIn.nextLine();
            // Store the name in names array:
            names[index] = name;
            // Read in second line which is the student response: 
            String response = fileIn.nextLine();
            // Try storing all the columns of the corresponding row in studentResponse 2-D array.
            // Loop through the number of questions in the quiz:
            for (int i = 0; i < numQuestions; i++) {
                // If column number is greater than the response String length - 1,
                // then just put a space. The space symbolizes that the student 
                // didn't answer the question:
                if (i > response.length() -1 ) {
                    studentResponse[index][i] = ' ';
                } else {
                    // Store upper case character at row index and column i:
                    studentResponse[index] [i] = Character.toUpperCase(response.charAt(i));
                }
            }
            // Increment the index: 
            index++;
        }
        numStudents = index;
    }

    /**
     * A method that checks the student responses and correct answers
     * and provide marks and question statistics.
     * 
     */
    public void markStudentAnswers() {
        // Allocate the marks and quizStats arrays:
        marks = new int[numStudents];
        quizStats = new int [numQuestions];
        // Initialize both arrays to zero:
        for (int i=0;i<numStudents;i++) {
            marks[i] = 0;
        }
        for (int i=0;i<numQuestions;i++) {
            quizStats[i] = 0;
        }
        // Loop through one student at a time:
        for (int i=0;i<numStudents;i++) {
            // Loop through the questions in quiz:
            for(int j=0;j<numQuestions;j++) {
                if (studentResponse[i][j] == corrAnswers[j]) {
                    // Increment marks and also quizStats:
                    marks[i]++;
                    quizStats[j]++;
                }
            }
        }
        // Print a message saying it has been marked.
        System.out.println("The student responses have been marked.");
    }

    /**
     * A method to produce quiz statistics.
     */
    public void printQuizStats() {
        // Check if quiz has been marked:
        if (marks == null) {
            this.markStudentAnswers();
        }
        // Initialize the variables for average, max, and min:
        double average = 0.0;
        int max = marks[0];
        int min = marks[0];
        // Print header:
        System.out.println("Student" + "\t" + "Marks" + "\t" + "Average");
        for (int i=0;i<numStudents;i++) {
            System.out.println(names[i]+"\t"+marks[i]+"\t"+ marks[i]/(double) numQuestions);
            // Add marks to the average:
            average += marks[i];
            // Verify if new marks is the local max:
            if (marks[i] > max) {
                max = marks[i];
            }
            // Verify if new marks is the local min:
            if (marks[i] < min) {
                min = marks[i];
            }
        }
        // Print average, max, and min:
        System.out.printf("The quiz average is %.2f%n", average/(double) numStudents);
        System.out.println("The highest mark is " + max);
        System.out.println("The lowest mark is " + min);
    }

    /**
     * A method to print Question Statistics.
     */
    public void printQuestionStats() {
        // Check if quiz has been marked:
        if (quizStats == null) {
            this.markStudentAnswers();
        }
        // Initialize the variables for max and min:
        int max = quizStats[0];
        int min = quizStats[0];
        System.out.println("Question\t\tCorrect\tAverage");
        // Loop through all the questions:
        for (int i=0;i<numQuestions;i++) {
            // Print Question number, correct, and average:
            System.out.printf("Question %d\t\t%d\t%.2f%n", i+1, quizStats[i], quizStats[i]/(double) numStudents);
            // Set the max:
            if (max < quizStats[i]) {
                max = quizStats[i];
            }
            // Set the min:
            if (min > quizStats[i]) {
                min = quizStats[i];
            }
        }
        // Print the question details:
        System.out.println("The highest number of correct answers is " + max);
        System.out.println("The following questions are the easiest:");
        // Print every question with the max marks:
        for (int i=0;i<numQuestions;i++) {
            if (max == quizStats[i]) {
                System.out.println("Question " + (i+1));
            }
        }
        System.out.println("The lowest number of correct answers is " + min);
        System.out.println("The following questions are the hardest:");
        // Print every question with the min mark:
        for (int i=0;i<numQuestions;i++) {
            if (min == quizStats[i]) {
                System.out.println("Question " + (i+1));
            }
        }
    }

    /**
     * A method to print studentResponses.
     */
    public void printStudentResponses() {
        // Print title:
        System.out.println("Student Response:");
        // Print Name and question number (the header):
        int i = 1;
        System.out.print("Name" + "\t");
        while (i <= numQuestions) {
            System.out.print(i + "\t");
            i++;
        }
        System.out.println();
        // Print student names and corresponding responses.
        // Loop through each row/student:
        for (int i2 = 0; i2 < numStudents; i2++) {
            // Print corresponding name followed by a tab:
            System.out.print(names[i2] + "\t");
            // Loop through each question:
            for (int j = 0; j < numQuestions; j++) {
                // Print response for each question and student-i2:
                System.out.print(studentResponse[i2][j] + "\t");
            }
            // Print new line:
            System.out.println();
        }
    }
}