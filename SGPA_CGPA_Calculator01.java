import java.util.*;

public class SGPA_CGPA_Calculator {
    
    public static int getGradePoints(String grade) {
        switch (grade.toUpperCase()) {
            case "O": return 10;
            case "A+": return 9;
            case "A": return 8;
            case "B+": return 7;
            case "B": return 6;
            case "C": return 5;
            case "F": return 0;
            default: return -1; // Invalid grade
        }
    }
    
    public static double[] calculateSGPA(Scanner scanner, int semester) {
        System.out.println("\n Enter details for Semester " + semester);
        System.out.print("Enter number of subjects: ");
        int numSubjects = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        double totalGradePoints = 0;
        int totalCredits = 0;
        
        for (int i = 1; i <= numSubjects; i++) {
            System.out.print("\nEnter subject " + i + " name: ");
            String subject = scanner.nextLine();
      
            System.out.print("Enter grade obtained (O, A+, A, B+, B, C, F): ");
            String grade = scanner.next().toUpperCase();
            
            System.out.print("Enter credits assigned: ");
            int credits = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            if (credits <= 0) {
                System.out.println("❌ Credits must be greater than zero. Try again.");
                i--;
                continue;
            }

            int gradePoint = getGradePoints(grade);
            if (gradePoint == -1) {
                System.out.println("❌ Invalid grade entered! Please try again.");
                i--;
                continue;
            }
            
            totalGradePoints += gradePoint * credits;
            totalCredits += credits;
        }
        
        double sgpa;
        if (totalCredits == 0) {
            sgpa = 0; // Prevent division by zero
        } else {
            sgpa = totalGradePoints / totalCredits; // Normal SGPA calculation
        }
        System.out.printf("\n🎓 SGPA for Semester %d: %.2f 🎓\n", semester, sgpa);
        
        return new double[]{sgpa, totalCredits}; // Return both SGPA and total credits
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=======================================");
        System.out.println("      🎓 SGPA & CGPA Calculator       ");
        System.out.println("=======================================");
        
        // Semester 1
        double[] sem1Data = calculateSGPA(scanner, 1);
        double sgpa1 = sem1Data[0];
        int credits1 = (int) sem1Data[1];
        
        // Semester 2
        double[] sem2Data = calculateSGPA(scanner, 2);
        double sgpa2 = sem2Data[0];
        int credits2 = (int) sem2Data[1];
        
        // Calculate CGPA
        double cgpa = ((sgpa1 * credits1) + (sgpa2 * credits2)) / (credits1 + credits2);
        
        System.out.println("\n=======================================");
        System.out.printf("🌟 Final CGPA: %.2f 🌟\n", cgpa);
        System.out.println("=======================================");
        
        scanner.close();
    }
}


