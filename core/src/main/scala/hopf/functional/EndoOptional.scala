package hopf.functional

import scala.annotation.tailrec

final case class EndoOptional[T](fun: T => Option[T]) {
  def apply(x: T) = fun(x)

  @tailrec def times(n: Int)(x: T): Option[T] =
    if (n == 0) Some(x) else fun(x) match {
      case None    => None
      case Some(x) => times(n - 1)(x)
    }

  @tailrec def until(cond: T => Boolean)(x: T): Option[T] =
    if (cond(x)) Some(x) else fun(x) match {
      case None    => None
      case Some(x) => until(cond)(x)
    }
}

object EndoOptional {
  def id[T] = EndoOptional[T](x => Some(x))

  /*
  def monoid[T] = Monoid(id[T]) { case (Endo(f), Endo(g)) =>
    Endo(g andThen f)
  }*/
}