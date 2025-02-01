name := "shopping-basket"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.9" % Test
)

// Custom task to run tests before running the main application
Compile / run := (Compile / run).dependsOn(Test / test).evaluated

mainClass in assembly := Some("PriceBasket")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x                             => MergeStrategy.first
}
