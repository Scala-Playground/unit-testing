package utils

import cats.Show
import cats.implicits.toFoldableOps

object PrettyPrinting {

  implicit def showList[A](implicit
      showA: Show[A] = Show.fromToString[A]
  ): Show[List[A]] =
    Show.show(l => "[\n  " + l.map(showA.show).intercalate(",\n  ") + "\n]")

  def indented(s: String): String =
    "  " + s.replace("\n", "\n  ")

}
