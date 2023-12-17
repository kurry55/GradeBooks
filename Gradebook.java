import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Gradebook implements Comparator<StudentGrades> {

	private Collection<StudentGrades> grades;

	/**
	 * @return a number based on which should come first
	 */
	public int compare(StudentGrades left, StudentGrades right) {
		return (int) (left.totalScore() - right.totalScore());
	}

	/**
	 * Constructor to initlize grades
	 */
	public Gradebook() {
		grades = new ArrayList<StudentGrades>();
	}

	/**
	 * @param sg grades to be added to the collection
	 */
	public void addGrade(StudentGrades sg) {
		grades.add(sg);
	}

	/**
	 * @return the average of the collection
	 */
	public double average() {
		if (grades.isEmpty()) {
			return 0; // no one there
		}

		double totalScoreSum = 0.0;
		for (StudentGrades sg : grades) {
			totalScoreSum += sg.totalScore();
		}
		return totalScoreSum / grades.size(); // average
	}

	/**
	 * @return studentGrades with the lowest grade
	 */
	public StudentGrades min() {
		if (grades.isEmpty()) {
			return null; // no one there
		}
		// sorts using the collections then grabs the minimum
		return Collections.min(grades, this);
	}

	/**
	 * @return studentGrades with the highest grade
	 */
	public StudentGrades max() {
		if (grades.isEmpty()) {
			return null; // no one there
		}
		// sorts using the collections then grabs the maximum
		return Collections.max(grades, this);
	}

	/**
	 * @return studentGrades with the median grade
	 */
	public StudentGrades median() {
		if (grades.isEmpty() || grades == null) {
			return null;
		}

		// sort the array so then we can grab the middle index
		List<StudentGrades> sortedGrades = new ArrayList<>(grades);
		Collections.sort(sortedGrades, this);

		// Find the middle index
		int middleIndex = (grades.size() - 1) / 2;

		return sortedGrades.get(middleIndex);
	}

	/**
	 * @return formatted to string
	 */
	@Override
	public String toString() {
		String rv = "Grades: [ ";
		for (StudentGrades sg : grades) {
			rv += "(" + sg.getStudentName() + ": " + sg.letterGrade() + "), ";
		}
		rv += "]\n";
		rv += "Max: " + max() + ", Median: " + median() + ", Average: " + average() + ", Min: " + min();
		return rv;
	}
}