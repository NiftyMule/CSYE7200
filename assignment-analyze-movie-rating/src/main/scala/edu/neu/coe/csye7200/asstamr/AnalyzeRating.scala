package edu.neu.coe.csye7200.asstamr

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

import scala.util.{Success, Try}

object AnalyzeRating extends App {

  def calcMeanAndStddev(df: DataFrame, colName: String): DataFrame = {
    df.select(
      mean(df(colName)).alias("mean"),
      stddev(df(colName)).alias("std_dev")
    )
  }

  val spark: SparkSession = SparkSession
    .builder()
    .appName("RatingAnalyzer")
    .master("local[*]")
    .getOrCreate()

  spark.sparkContext.setLogLevel("ERROR")

  // get full filepath as required by Spark
  val rAbsolute = """(/.*)""".r
  val filename = args.headOption.getOrElse("movie_metadata.csv")
  val path: String = filename match {
    case rAbsolute(fullPath) => fullPath
    case _ =>
      (for (uo <- Try(Option(getClass.getResource(filename)))) yield for (u <- uo) yield u) match {
        case Success(Some(p)) => p.getPath
        case _ => throw new Exception(s"cannot get resource for class AnalyzeRating: $filename")
      }
  }

  val colName = args.drop(1).headOption.getOrElse("imdb_score")

  val df = spark.read
    .option("header", "true")
    .csv(path)

  val processedDf = calcMeanAndStddev(df, colName)

  processedDf.show()
}
