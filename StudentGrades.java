import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StudentGrades {
    private double participation;
    private double midterm;
    private double finalExam;

    private double participationWeight;
    private double readingsWeight;
    private double labsWeight;
    private double exercisesWeight;
    private double projectsWeight;
    private double midtermWeight;
    private double finalExamWeight;
    private String studentName;
    private String gNumber;

    private Collection<Double> labs;
    private Collection<Double> exercises;
    private Collection<Double> projects;
    private List<Double> readings;

    /**
     * @param name    of the student
     * @param gNumber of the student
     * @param weights for the students grades
     */
    public StudentGrades(String name, String gNumber, double[] weights) {
        setStudentName(name);
        setGNumber(gNumber);
        setWeights(weights);
        this.labs = new ArrayList<>();
        this.exercises = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.readings = new ArrayList<>();
    }

    /**
     * @return the unweighted reading score, with the lowest 15 subsections dropped
     */
    public double unweightedReadingsScore() {
        if (readings.isEmpty() || readings.size() <= 15) { // 15 lowest are dropeped
            return 100.0;
        }

        List<Double> sortedReadings = new ArrayList<>(readings);

        // Sort the readings in ascending order so then we can start without including
        // the lowest 15 values
        Collections.sort(sortedReadings);

        double total = 0.0;

        // Start the loop from the 16th element to exclude the lowest 15
        // average
        for (int i = 15; i < sortedReadings.size(); i++) {
            total += sortedReadings.get(i);
        }

        return total / (sortedReadings.size() - 15);
    }

    /**
     * @return the unweighted lab score
     */
    public double unweightedLabsScore() {
        if (labs.isEmpty()) {
            return 100.0; // Full credit
        }

        // average
        double total = 0.0;
        for (Double score : labs) {
            total += score;
        }
        return total / labs.size();
    }

    /**
     * @return the unweighted excercise score
     */
    public double unweightedExercisesScore() {
        if (exercises.isEmpty()) {
            return 100.0; // Full credit
        }

        // average
        double total = 0.0;
        for (Double score : exercises) {
            total += score;
        }
        return total / exercises.size();
    }

    /**
     * @return the unweighted project score
     */
    public double unweightedProjectsScore() {
        if (projects.isEmpty()) {
            return 100.0; // Full credit
        }

        // average
        double total = 0.0;
        for (Double score : projects) {
            total += score;
        }
        return total / projects.size();
    }

    /**
     * @return true if final score is more than midterm, false otherwise
     */
    public boolean finalReplacesMidterm() {
        return finalExam > midterm;
    }

    /**
     * @return true if final score is passing, false otherwise
     */
    public boolean finalIsPassing() {
        return finalExam > 60.0;
    }

    /**
     * @return the sum of all scores and their respective weights
     */
    public double totalScore() {
        double total = participation * participationWeight
                + unweightedReadingsScore() * readingsWeight
                + unweightedLabsScore() * labsWeight
                + unweightedExercisesScore() * exercisesWeight
                + unweightedProjectsScore() * projectsWeight
                + finalExam * finalExamWeight;

        // checks if final is higher than midterm score
        if (finalReplacesMidterm()) {
            total += finalExam * midtermWeight;
        } else {
            total += midterm * midtermWeight;
        }

        return total;
    }

    /**
     * @return letter grade
     */
    public String letterGrade() {
        double score = totalScore();
        if (!finalIsPassing()) {
            return "F";
        } else if (score >= 98.0) {
            return "A+";
        } else if (score >= 92.0) {
            return "A";
        } else if (score >= 90.0) {
            return "A-";
        } else if (score >= 88.0) {
            return "B+";
        } else if (score >= 82.0) {
            return "B";
        } else if (score >= 80.0) {
            return "B-";
        } else if (score >= 78.0) {
            return "C+";
        } else if (score >= 72.0) {
            return "C";
        } else if (score >= 70.0) {
            return "C-";
        } else if (score >= 60.0) {
            return "D";
        } else {
            return "F";
        }
    }

    /**
     * @param weights an array of weights to be set to the corresponding weight
     */
    public void setWeights(double[] weights) {
        if (weights.length == 7) {
            this.participationWeight = weights[0];
            this.readingsWeight = weights[1];
            this.labsWeight = weights[2];
            this.exercisesWeight = weights[3];
            this.projectsWeight = weights[4];
            this.midtermWeight = weights[5];
            this.finalExamWeight = weights[6];
        }
    }

    // Add methods

    /**
     * @param d score
     */
    public void addReading(double d) {
        readings.add(d);
    }

    /**
     * @param d score
     */
    public void addLab(double d) {
        labs.add(d);
    }

    /**
     * @param d score
     */
    public void addExercise(double d) {
        exercises.add(d);
    }

    /**
     * @param d score
     */
    public void addProject(double d) {
        projects.add(d);
    }

    /**
     * @return a formatted string
     */
    @Override
    public String toString() {
        String rv = "Name: " + getStudentName() + "\n";
        rv += "G#: " + getGNumber() + "\n";
        rv += "Participation: " + getParticipation() + "\n";
        rv += "Readings: " + unweightedReadingsScore() + ", " + readings + "\n";
        rv += "Labs: " + unweightedLabsScore() + ", " + labs + "\n";
        rv += "Exercises: " + unweightedExercisesScore() + ", " + exercises + "\n";
        rv += "Projects: " + unweightedProjectsScore() + ", " + projects + "\n";
        rv += "Midterm: " + getMidterm() + "\n";
        rv += "Final Exam: " + getFinalExam() + "\n";
        rv += totalScore() + ", " + letterGrade() + "\n";
        return rv;
    }

    // Getters and Setters
    /**
     * @return participation score
     */
    public double getParticipation() {
        return participation;
    }

    /**
     * @param participation score to set
     */
    public void setParticipation(double participation) {
        this.participation = participation;
    }

    /**
     * @return midterm score
     */
    public double getMidterm() {
        return midterm;
    }

    /**
     * @param midterm score to set
     */
    public void setMidterm(double midterm) {
        this.midterm = midterm;
    }

    /**
     * @return final exam score
     */
    public double getFinalExam() {
        return finalExam;
    }

    /**
     * @param finalExam score to set
     */
    public void setFinalExam(double finalExam) {
        this.finalExam = finalExam;
    }

    /**
     * @return the students name
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * @param studentName to set
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * @return G Number
     */
    public String getGNumber() {
        return gNumber;
    }

    /**
     * @param gNumber G Number to set
     */
    public void setGNumber(String gNumber) {
        this.gNumber = gNumber;
    }

}
