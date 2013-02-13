package hopf.zipper.generic

abstract sealed class RSib[+Provides, +Parent] {
  def unpack(f: Any): Parent
}

case class NullRS[P] extends RSib[P, P] {
  def unpack(f: Any) = f.asInstanceOf[P]
}

case class ConsRS[I, O, T](x: I, r: RSib[O, T]) extends RSib[I => O, T] {
  def unpack(f: Any) = r.unpack(f.asInstanceOf[I => O](x))
}