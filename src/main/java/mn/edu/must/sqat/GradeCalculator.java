package mn.edu.must.sqat;

public class GradeCalculator {

    public int totalScore(int assignment, int midterm, int finalExam) {
        int total = assignment + midterm + finalExam;

        if (total < 0 || total > 100) {
            throw new IllegalArgumentException("Total score must be between 0 and 100");
        }

        return total;
    }

    public String letterGrade(int score) {
        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}

