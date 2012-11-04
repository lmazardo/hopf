package hopf

import hopf.util.types._

package object categorical {
  implicit class ComposeEnriched[->[_, _], A, B](val f: A -> B)(implicit C: Category[->]) {
    def >>[C](g: B -> C): A -> C = C.compose(f)(g)
  }
    
  implicit class FmapEnriched1[F[_], A](x: F[A])(implicit F: Functor[F]) {
    def fmap[B](f: A => B) = F.fmap(f)(x)
  }
  
  implicit class FmapEnriched2[F[_, _], P, A](x: F[P, A])(implicit F: Functor[(F $ P)# ?]) {
    def fmap[B](f: A => B) = F.fmap(f)(x)
  }
  
  implicit class ApplicativeAppEnriched[A[_], X, Y](f: A[X => Y])(implicit A: Applicative[A]) {
    def <*>(x: A[X]) = A.apply(f)(x)
  }
  
  implicit class ApplicativeSeqEnriched1[A[_], T](a: A[T])(implicit A: Applicative[A]) {
    def *>[B](b: A[B]) = A.seqDropLeft (a, b)
    def <*[B](b: A[B]) = A.seqDropRight(a, b)    
  }
  
  implicit class ApplicativeSeqEnriched2[A[_, _], P, T](a: A[P, T])(implicit A: Applicative[(A $ P)# ?]) {
    def *>[B](b: A[P, B]) = A.seqDropLeft (a, b)
    def <*[B](b: A[P, B]) = A.seqDropRight(a, b)    
  }
  
  implicit class BindEnriched1[M[_], A](a: M[A])(implicit M: Monad[M]) {
    def >>=[B](f: A => M[B]) = M.bind(a)(f)
  }
  
  implicit class BindEnriched2[M[_, _], P, A](a: M[P, A])(implicit M: Monad[(M $ P)# ?]) {
    def >>=[B](f: A => M[P, B]) = M.bind(a)(f)
  }
}