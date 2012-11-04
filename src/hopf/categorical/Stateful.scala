package hopf.categorical

import hopf.util.types._

case class Stateful[S, A](val run: S => (A, S))

object Stateful {  
  def get[S] = Stateful{s: S => (s, s)}
  def put[S](s: S) = Stateful{_: S => ((), s)}

  implicit def monad[S] = new Monad[(Stateful $ S)# ?] {
    override def ret[A] = x => Stateful(s => (x, s)) 
    override def bind[A, B] = stComp => f =>
      Stateful { state =>
        val (result,newState) = stComp.run(state)
        f(result).run(newState)
      }
  }      
  
  // def bind[A] = get >>= put
  
  // val f = get[S forSome {type S}].>>=
  implicit def joinable[S]    = monad[S].joinable
  implicit def applicative[S] = monad[S].applicative
  implicit def pointable[S]   = applicative[S].pointable
  implicit def functor[S]     = applicative[S].functor
}