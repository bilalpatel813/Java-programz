public class GradeCard {
    public static void main(String[] args) {
        final String STUDENT_NAME = "Bilal";
        final int MAX_MARKS = 100;

        var mathMarks    = 88;
        var scienceMarks = 92;
        var englishMarks = 79;

        int total = mathMarks + scienceMarks + englishMarks;
        double percentage = (double) total / (MAX_MARKS * 3) * 100;
        char grade = percentage >= 90 ? 'A' : percentage >= 75 ? 'B' : 'C';

        System.out.println("=== " + STUDENT_NAME + "'s Report Card ===");
        System.out.println("Math:    " + mathMarks);
        System.out.println("Science: " + scienceMarks);
        System.out.println("English: " + englishMarks);
        System.out.println("Total:   " + total + "/" + (MAX_MARKS * 3));
        System.out.printf("Percent: %.2f%%%n", percentage);
        System.out.println("Grade:   " + grade);
    }
}