package hopf.zipper.generic

abstract sealed class LSib[+Expects] {
  def unpack: Expects = this match {
    case UnitLS(x)    => x
    case ConsLS(f, a) => f.unpack(a)
  }
}

case class UnitLS[E](x: E)                     extends LSib[E]
case class ConsLS[X, E](l: LSib[X => E], x: X) extends LSib[E]