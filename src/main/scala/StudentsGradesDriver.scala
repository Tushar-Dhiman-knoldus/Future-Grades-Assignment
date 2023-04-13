package com.knoldus.futureassignment

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

object StudentsGradesDriver extends App {

  private val students = new StudentsGrades

  val grades = students.calculateGrades("/home/knoldus/Scala assignments/KUP-Session 4/FutureAssignment/src/main/scala/gradeSheet/grades.csv")
  grades.onComplete {
    case Success(value) => println("Grade:" + value)
    case Failure(exception) => println(exception)
  }
}