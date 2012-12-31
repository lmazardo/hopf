package hopf.algebraic

case class Property(formula: Any) {
  // type Signature
  // val formula: Any
}

object prop {
  private implicit class Op[T](l: T)(implicit f: (T, T) => T) {
    def <>(r: T) = f(l, r)
  }
    
  def assoc[T](f: (T, T) => T) = {
    implicit val _ = f
    Property { x:T => y:T => z:T =>
      (x <> y) <> z == x <> (y <> z)
    }
  }
}