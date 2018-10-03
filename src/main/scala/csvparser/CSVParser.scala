package csvparser

sealed trait CSVParserError extends RuntimeException
import Result.Result
import cats.data.ValidatedNel
import csvparser.CSVParser.CSVContent

case class Id[A](value: Int) extends AnyVal

/**
  * @param id an valid id should be > 0
  * @param name a valid name should be alphabetic only
  * @param project a valid project name should be alphanumeric
  * @param role a valid role
  */
case class User(id: Id[User], name: String, project: String, role: UserRole)

sealed trait UserRole
case object Developer extends UserRole
case object Administrator extends UserRole
case object Guest extends UserRole

object Result {
  type Result[A] = Either[CSVParserError, A]

  def success[A](value: A): Result[A] =
    Right(value)

  def failure[A](error: CSVParserError): Result[A] =
    Left(error)
}


object CSVParser {
  type CSVContent = Seq[Map[String, String]]

  def parse(source: String): Result[CSVContent] = ???
}

object UserParser {
  sealed trait UserParseError

  type ValidationResult[A] = ValidatedNel[UserParseError, A]

  def fromCSV(content: CSVContent): ValidationResult[Seq[User]] = ???
}
