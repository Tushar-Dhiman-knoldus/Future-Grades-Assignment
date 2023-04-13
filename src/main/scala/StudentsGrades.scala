package com.knoldus.futureassignment

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.io.Source
import scala.util.{Failure, Success, Try}

class StudentsGrades {

  // Function to find the grades of the students.
  def calculateGrades(filePath: String): Future[Double] = {
    val grades = for {
      parsed <- parseCsv(filePath)
      studentAverage <- calculateStudentAverage(parsed)
      classAverage <- calculateClassAverage(studentAverage)
    } yield classAverage
    Await.ready(grades, Duration.Inf)
  }

  // Function to parse the csv file.
  private def parseCsv(filePath: String): Future[List[Map[String, String]]] = Future {
    val source = Try(Source.fromFile(filePath)) match {
      case Failure(exception) => throw new IllegalArgumentException(s"Cannot read file at path: $filePath", exception)
      case Success(source) => source
    }

    val lines = source.getLines().toList
    val keys: List[String] = lines.headOption match {
      case Some(line) => line.split(",").map(_.trim).toList
      case None => List.empty
    }
    val listOfAllValues = lines.drop(1).map(line => line.split(",").map(_.trim).toList)
    listOfAllValues.map(values => (keys zip values).toMap)
  }

  // Function to calculate the average marks of the students.
  private def calculateStudentAverage(studentDetails: List[Map[String, String]]): Future[List[(String, Double)]] = {
    Future {
      Try {
        studentDetails.map { mapValue =>
          val id = mapValue("StudentID")
          val grades = List(mapValue("English"), mapValue("Chemistry"), mapValue("Physics"), mapValue("Maths"))
          val gradeInDouble = grades.map(_.toDouble)
          val average = gradeInDouble.sum / gradeInDouble.size
          (id, average)
        }
      } match {
        case Success(result) => result
        case Failure(ex) => throw new Exception(ex.getMessage)
      }
    }
  }

 // Function to find the class average.
  private def calculateClassAverage(studentsAverage: List[(String, Double)]): Future[Double] = {
    Future {
      Try {
        val fetchingStudentAverage = studentsAverage.map { case (_, average) => average }
        fetchingStudentAverage.sum / fetchingStudentAverage.size
      } match {
        case Success(result) => result
        case Failure(ex) => throw new Exception(ex.getMessage)
      }
    }
  }
}
