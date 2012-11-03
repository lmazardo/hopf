package hopf

package object categorical {
  implicit class FmapEnriched[F[_], A](x: F[A])(implicit F: Functor[F]) {
    def fmap[B](f: A => B) = F.fmap(f)(x)
  }
  
  implicit class ComposeEnriched[->[_, _], A, B](val f: A -> B)(implicit cat: Category[->]) {
    def >>[C](g: B -> C): A -> C = cat.compose(f, g)
  }
}