# Future-Grades-Assignment

This is a Scala project that reads student grades from a CSV file, calculates the average marks of the students, and then calculates the average marks of the class. The project makes use of Scala's Future API for asynchronous processing.

## Implementation

- **The StudentsGrades class** reads the student grades from a CSV file and calculates the average marks of the students and the class. 
- **The parseCsv** method reads the CSV file and returns a list of maps containing the grades of each student. 
- **The calculateStudentAverage** method calculates the average marks of each student and returns a list of tuples containing the student ID and their average marks. 
- **The calculateClassAverage** method calculates the average marks of the class and returns the result as a Future[Double].
