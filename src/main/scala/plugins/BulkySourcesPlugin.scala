package plugins

import sbt._
import sbt.Keys._

object BulkySourcesPlugin extends AutoPlugin {
  override def trigger = allRequirements

  def getBulkySources(files: Seq[File], lines: Int): Seq[(Int, File)] = for {
    file <- files
    fileLines = IO.readLines(file).length
    if fileLines >= lines
  } yield (fileLines, file)

  object autoImport {
    val bulkyThresholdInLines = settingKey[Int]("Number of rows of bulky sources")
    val bulkySources = taskKey[Seq[(Int, File)]]("Show bulky sources")

    lazy val baseBulkySources: Seq[Setting[_]] = Seq(
      bulkySources := {
        getBulkySources(sources.value, bulkyThresholdInLines.value)
      }
    )
  }

  import autoImport._

  override lazy val globalSettings: Seq[Setting[_]] = Seq(
    bulkyThresholdInLines := 100
  )

  override val projectSettings =
    inConfig(Compile)(baseBulkySources) ++
    inConfig(Test)(baseBulkySources)
}
