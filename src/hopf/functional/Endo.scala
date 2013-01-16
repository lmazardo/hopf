package hopf.functional

import scala.annotation.tailrec
import hopf.algebraic.Monoid

case class Endo[T](fun: T => T) {
  def apply(x: T) = fun(x)

  @tailrec final def times(n: Int)(x: T): T =
    if (n == 1) fun(x) else times(n - 1)(fun(x))

  @tailrec final def until(cond: T => Boolean)(x: T): T =
    if (cond(x)) x else until(cond)(fun(x))
}

object Endo {
  def id[T] = Endo[T](x => x)

  def monoid[T] = Monoid(id[T]) { case (Endo(f), Endo(g)) =>
    Endo(g andThen f)
  }
}