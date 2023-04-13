package com.knoldus.futureassignment

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

class StudentsGradesSpec extends AnyFlatSpec with Matchers {

  "StudentsGrades" should "calculate class average correctly" in {
    val studentsGrades = new StudentsGrades()
    val filePath = "/home/knoldus/Scala assignments/KUP-Session 4/FutureAssignment/src/main/scala/gradeSheet/grades.csv"
    val classAverageFuture = studentsGrades.calculateGrades(filePath)

    val classAverage = Await.result(classAverageFuture, 5.seconds)

    classAverage shouldEqual 79.5}

  it should "throw an exception when file does not exist" in {
    val studentsGrades = new StudentsGrades()
    val filePath = "nonexistentfile.csv"

    an[IllegalArgumentException] should be thrownBy {
      Await.result(studentsGrades.calculateGrades(filePath), 5.seconds)
    }
  }
}
