package hopf.algebraic.properties

// a o b = b o a
trait Commutativity

object Commutativity {
  abstract class Proof
  sealed case class Dummy extends Proof
}