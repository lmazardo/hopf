package hopf

import hopf.util.types._
import hopf.util.types.unapply._

package object categorical {
  
  implicit class ComposeEnriched[->[_, _], A, B](val f: A -> B)(implicit C: Category[->]) {
    def >>[C](g: B -> C): A -> C = C.compose(f)(g)
  }
  
  class FmapEnriched[F[_], A](F: Functor[F], x: F[A]) {
    def fmap[B](f: A => B) = F.fmap(f)(x)
  }
  
  implicit def fmapEnriched[FA](x: FA)(implicit U: Unapply[Functor, FA]) =
    new FmapEnriched[U.Con, U.Arg](U.tc, U(x))
  
  class ApplicativeAppEnriched[A[_], L, R](A: Applicative[A], f: A[L => R]) {
    def <*>(x: A[L]) = A.apply(f)(x)
  }
  
  implicit def applicativeAppEnriched[AF](f: AF)(implicit U: UnapplyF[Applicative, AF]) =
    new ApplicativeAppEnriched[U.Con, U.L, U.R](U.tc, U(f))
    
  class ApplicativeSeqEnriched[A[_], L](A: Applicative[A], l: A[L]) {
    def *>[R](r: A[R]) = A.seqDropLeft (l, r)
    def <*[R](r: A[R]) = A.seqDropRight(l, r)
  }
  
  implicit def applicativeSeqEnriched[AL](l: AL)(implicit U: Unapply[Applicative, AL]) =
    new ApplicativeSeqEnriched[U.Con, U.Arg](U.tc, U(l))
  
  class BindEnriched[M[_], A](M: Monad[M], a: M[A]) {  
    def >>=[B](f: A => M[B]) = M.bind(a)(f)
  }
  
  implicit def bindEnriched[MA](a: MA)(implicit U: Unapply[Monad, MA]) =
    new BindEnriched[U.Con, U.Arg](U.tc, U(a))
}