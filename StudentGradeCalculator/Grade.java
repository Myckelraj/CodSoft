package StudentGradeCalculator;

import java.util.Scanner;

public class Grade {

  public static void main(String[] args) {
    // Input number of subjects
    try (Scanner scanner = new Scanner(System.in)) {
      // Input number of subjects
      System.out.print("Enter the number of subjects: ");
      int numSubjects = scanner.nextInt();
      // Array to store marks for each subject
      int[] marks = new int[numSubjects];
      int totalMarks = 0;
      // Input marks for each subject
      for (int i = 0; i < numSubjects; i++) {
        System.out.print("Enter marks obtained in subject " + (i + 1) + " (out of 100): ");
        marks[i] = scanner.nextInt();
        totalMarks += marks[i];
      } // Calculate average percentage
      double averagePercentage = (double) totalMarks / numSubjects;
      // Determine grade based on average percentage
      String grade = getGrade(averagePercentage);
      // Display results
      System.out.println("Total Marks: " + totalMarks);
      System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
      System.out.println("Grade: " + grade);
      // Close the scanner
    }
  }

  // Method to determine grade based on average percentage
  public static String getGrade(double averagePercentage) {
    if (averagePercentage >= 90) {
      return "A";
    } else if (averagePercentage >= 80) {
      return "B";
    } else if (averagePercentage >= 70) {
      return "C";
    } else if (averagePercentage >= 60) {
      return "D";
    } else {
      return "F";
    }
  }
}