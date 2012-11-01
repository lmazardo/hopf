package hopf.categorical

import hopf.structural._
import hopf.common.TypeSynonyms._

abstract class Monad[M[_]] {
  def ret[A](x: A): M[A]
  def bind[A, B](a: M[A], f: A => M[B]): M[B]
  
  implicit class BindEnriched[A](a: M[A]) {
    def >>=[B](f: A => M[B]) = Monad.this.bind(a, f)
  }
  
  implicit class JoinEnriched[A](a: M[M[A]]) {
    def join = bind(a, identity[M[A]])
  }
  
  implicit lazy val asPoint = new Point[M] {
    def point[A](x: A) = ret(x)
  }
  
  implicit lazy val asApply = new Apply[M] {
    def apply[A, B](mf: M[A => B], mx: M[A]) = mf >>= (f => mx >>= (x => ret(f(x))))
  }
  
  implicit lazy val asFunctor = Functor.fromPointApply(asPoint, asApply)
  
  implicit lazy val asApplicative = Applicative.fromPointApplyFunctor(asPoint, asApply, asFunctor)
}