package hopf.util.types.unapply

import hopf.util.types._

abstract class Unapply[TC[_[_]], Applied] {
  type Con[_]
  type Arg
  
  def tc: TC[Con]
  def apply(x: Applied): Con[Arg]
}

object Unapply {
  
  implicit def unapplyC1[TC[_[_]], C[_], A](implicit TC: TC[C]) = new Unapply[TC, C[A]] {
    type Con[X] = C[X]
    type Arg = A
  
    def tc = TC
    def apply(x: C[A]) = x
  }
  
  implicit def unapplyC2[TC[_[_]], C[_, _], A, B](implicit TC: TC[Pa1[C, A]# ?]) = new Unapply[TC, C[A, B]] {
    type Con[X] = C[A, X]
    type Arg = B
  
    def tc = TC
    def apply(x: C[A, B]) = x
  }
}