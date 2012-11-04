package hopf.util

package object functional {
  implicit class FlipEnriched[A, B, C](f: A => B => C) {
    def flip: B => A => C =
      a => b => f(b)(a)
  }
  
  implicit class CurryEnriched[A, B, C](f: (A, B) => C) {
    def curry: A => B => C =
      a => b => f(a, b)
  }
  
  implicit class UncurryEnriched[A, B, C](f: A => B => C) {
    def uncurry: (A, B) => C = f(_)(_)
  }
  
  implicit class IterateEnriched[A](x: A) {
    def iterate(f: A => A): Stream[A] =
      x #:: f(x).iterate(f)
  }
}