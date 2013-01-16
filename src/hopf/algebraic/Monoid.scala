package hopf.algebraic

trait Monoid extends Semigroup {
  val id: Carrier
  // prop id
}

object Monoid {
  def apply[C](_1: C)(fn: (C, C) => C) = new Monoid {
    type Carrier = C
    val op = fn
    val id = _1
  }

  implicit class AsMonoid[C](f: (C, C) => C) {
    def asMonoid(_1: C) = Monoid[C](_1)(f)
  }
}

object monoid {
  object boolean {
    val and = Monoid[Boolean](true )(_ & _)
    val or  = Monoid[Boolean](false)(_ | _)
  }

  object int {
    val sum = Monoid[Int](0)(_ + _)
    val mul = Monoid[Int](1)(_ * _)
  }

  object list {
    def concat[T] = Monoid[List[T]](Nil)(_ ++ _)
  }

  object option {
    def first[T] = Monoid[Option[T]](None) {
      case (None, r) => r
      case (l,    _) => l
    }

    def last[T] = Monoid[Option[T]](None) {
      case (l, None) => l
      case (_, r   ) => r
    }
  }
}