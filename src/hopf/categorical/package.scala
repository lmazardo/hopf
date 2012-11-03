package hopf

package object categorical {
  implicit class ComposeEnriched[->[_, _], A, B](val f: A -> B)(implicit C: Category[->]) {
    def >>[C](g: B -> C): A -> C = C.compose(f)(g)
  }
    
  implicit class FmapEnriched[F[_], A](x: F[A])(implicit F: Functor[F]) {
    def fmap[B](f: A => B) = F.fmap(f)(x)
  }
  
  implicit class ApplicativeAppEnriched[A[_], X, Y](f: A[X => Y])(implicit A: Applicative[A]) {
    def <*>(x: A[X]) = A.apply(f)(x)
  }
  
  implicit class ApplicativeSeqEnriched[A[_], T](a: A[T])(implicit A: Applicative[A]) {
    def *>[B](b: A[B]) = A.seqDropLeft (a, b)
    def <*[B](b: A[B]) = A.seqDropRight(a, b)    
  }
  
  implicit class BindEnriched[M[_], A](a: M[A])(implicit M: Monad[M]) {
    def >>=[B](f: A => M[B]) = M.bind(a)(f)
  }
}