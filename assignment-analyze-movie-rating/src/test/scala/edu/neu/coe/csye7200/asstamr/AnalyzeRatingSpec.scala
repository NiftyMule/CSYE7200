package edu.neu.coe.csye7200.asstamr

import org.apache.spark.sql.SparkSession
import org.scalatest.matchers.should.Matchers
import org.scalatest.tagobjects.Slow
import org.scalatest.{BeforeAndAfter, flatspec}

import scala.util.Try

class AnalyzeRatingSpec extends flatspec.AnyFlatSpec with Matchers with BeforeAndAfter  {

  implicit var spark: SparkSession = _

  before {
    spark = SparkSession
      .builder()
      .appName("RatingAnalyzer")
      .master("local[*]")
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
  }

  after {
    if (spark != null) {
      spark.stop()
    }
  }

  behavior of "Spark"

  it should "correctly open the movie DB file" taggedAs Slow in {
    val triedPath = Try(getClass.getResource("movie_metadata.csv").getPath)
    triedPath.isSuccess shouldBe true
    for (path <- triedPath) {
      spark.read.option("header", "true").csv(path).count() shouldBe 1609
    }
  }

  it should "calculate movie rating mean & standard deviation" taggedAs Slow in {
    val triedPath = Try(getClass.getResource("movie_metadata.csv").getPath)
    val colName = "imdb_score"
    triedPath.isSuccess shouldBe true
    for (path <- triedPath) {
      val df = AnalyzeRating.calcMeanAndStddev(spark.read.option("header", "true").csv(path), colName)
      df.count() shouldBe 1
      // mean value
      df.first().getDouble(0) shouldBe 6.4532 +- 0.0001
      // sample standard deviation value
      df.first().getDouble(1) shouldBe 0.9988 +- 0.0001
    }
  }

  it should "calculate test dataset mean & standard deviation" taggedAs Slow in {
    val triedPath = Try(getClass.getResource("test_dataset.csv").getPath)
    val colName = "nums"
    triedPath.isSuccess shouldBe true
    for (path <- triedPath) {
      val df = AnalyzeRating.calcMeanAndStddev(spark.read.option("header", "true").csv(path), colName)
      df.count() shouldBe 1
      // mean value
      df.first().getDouble(0) shouldBe 86.6666 +- 0.0001
      // sample standard deviation value
      df.first().getDouble(1) shouldBe 245.4355 +- 0.0001
    }
  }
}
