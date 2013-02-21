package hopf.zipper.generic

abstract class MkLSib[T] {
  def apply(x: T): LSib[T]
}