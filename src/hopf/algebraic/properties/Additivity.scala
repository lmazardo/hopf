package hopf.algebraic.properties

import hopf.algebraic.properties._

trait Additivity[T] {
  def add: (T, T) => T
}