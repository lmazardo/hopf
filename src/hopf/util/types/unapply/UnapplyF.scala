package hopf.util.types.unapply

import hopf.util.types._

abstract class UnapplyF[TC[_[_]], Applied] extends Unapply[TC, Applied] {
  type L
  type R
  
  type Arg = L => R
}

object UnapplyF {
  
  implicit def unapplyF1[TC[_[_]], C[_], X, Y](implicit TC: TC[C]) = new UnapplyF[TC, C[X => Y]] {
    type Con[X] = C[X]
    type L = X
    type R = Y
  
    def tc = TC
    def apply(x: C[X => Y]) = x
  }
  
  implicit def unapplyF2[TC[_[_]], C[_, _], A, X, Y](implicit TC: TC[Pa1[C, A]# ?]) = new UnapplyF[TC, C[A, X => Y]] {
    type Con[X] = C[A, X]
    type L = X
    type R = Y
  
    def tc = TC
    def apply(x: C[A, X => Y]) = x
  }
}