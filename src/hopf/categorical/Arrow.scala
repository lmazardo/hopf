package hopf.categorical

import hopf.common.TypeSynonyms._

abstract class Arrow[->[_, _], x[_, _]] {  
  def left [A, B, C](f: A -> B): (A x C) -> (B x C)
  def right[A, B, C](f: A -> B): (C x A) -> (C x B)
}

trait Lift[->[_, _]] {
  def lift[A, B](f: A => B): A -> B
  
  implicit class LiftEnriched[A, B](val f: A => B) {
    def asArrow = lift(f)
  }
}

trait Split[->[_, _], x[_, _]] {
  def split[A, B, C, D](f: A -> B, g: C -> D): (A x C) -> (B x D)
  
  implicit class SplitEnriched[A, B](val f: A -> B) {
    def ***[C, D](g: C -> D) = split(f, g)
  }
}

trait Fanout[->[_, _], x[_, _]] {
  def fanout[A, B, C](f: A -> B, g: A -> C): A -> (B x C)
  
  implicit class FanoutEnriched[A, B](val f: A -> B) {
    def &&&[C](g: A -> C) = fanout(f, g)
  }
}

trait Cancel[->[_, _], x[_, _], U] {
  def cancelLeft [A]: (U x A) -> A
  def cancelRight[A]: (A x U) -> A
}

trait Uncancel[->[_, _], x[_, _], U] {
  def uncancelLeft [A]: A -> (U x A)
  def uncancelRight[A]: A -> (A x U)
}

trait Assoc[->[_, _], x[_, _]] {
  def assoc  [A, B, C]: ((A x B) x C) -> (A x (B x C)) 
  def unassoc[A, B, C]: (A x (B x C)) -> ((A x B) x C)
}

trait Drop[->[_, _], U] {
  def drop[A]: A -> U
}

trait Copy[->[_, _], x[_, _]] {
  def copy[A]: A -> (A x A)
}

trait Swap[->[_, _], x[_, _]] {
  def swap[A, B]: (A x B) -> (B x A)
}

trait Loop[->[_, _], x[_, _]] {
  def loop[A, B, C](f: (A x C) -> (B x C)): A -> B
}

object Arrow {  
  implicit val funArrowWithLift = new Arrow[Fun, Tup] with Lift[Fun] {
    def left [A, B, C](f: A => B) = { case (a, b) => (f(a), b) }
    def right[A, B, C](f: A => B) = { case (a, b) => (a, f(b)) }
    
    def lift[A, B](f: A => B) = f
  }
  
  implicit def oxyen4ik[->[_, _]](implicit arr: Arrow[->, Tup] with Lift[->], cat: Category[->]) = new Arrow[->, Tup]
      with Lift[->]
      
      with Split [->, Tup]
      with Fanout[->, Tup]  
  
      with Cancel  [->, Tup, Unit]
      with Uncancel[->, Tup, Unit]
  
      with Assoc[->, Tup]
      with Drop [->, Unit]
      with Copy [->, Tup]
      with Swap [->, Tup]
  {
    import cat._
    
    def left [A, B, C](f: A -> B) = arr.left(f)
    def right[A, B, C](f: A -> B) = arr.right(f)
    
    def lift[A, B](f: A => B) = arr.lift(f)
    
    def split[A, B, C, D](f: A -> B, g: C -> D) = left(f) >> right(g)
    
    def fanout[A, B, C](f: A -> B, g: A -> C) = copy >> (f *** g)
    
    def cancelLeft [A] = lift { case ((), x) => x }
    def cancelRight[A] = lift { case (x, ()) => x }
    
    def uncancelLeft [A] = lift { x => ((), x) }
    def uncancelRight[A] = lift { x => (x, ()) }
    
    def assoc  [A, B, C] = lift { case ((a, b), c) => (a, (b, c)) } 
    def unassoc[A, B, C] = lift { case (a, (b, c)) => ((a, b), c) }
    
    def drop[A] = lift { _ => () }
    def copy[A] = lift { x => (x, x) }
    
    def swap[A, B] = lift { case (a, b) => (b, a) }
  }

}