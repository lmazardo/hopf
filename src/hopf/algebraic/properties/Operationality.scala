package hopf.algebraic.properties

trait Operationality[T] {
  def op: (T, T) => T
}