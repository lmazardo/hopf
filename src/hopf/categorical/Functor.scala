package hopf.categorical

trait Functor[F <: Functorial] {
  
  protected def fmap[X]: (F#Hole => X) => F#Type => F#Fill[X]
  
  implicit class FmapEnriched(x: F) {
    def fmap[X](f: F#Hole => X) = Functor.this.fmap(f)(x.forgetStructure)
  }
}

object functor {
  
  object tuple2 {
    import functorial.tuple2._
    
    def in1[A, B] = new Functor[In1[A, B]] {
      def fmap[X] = f => { case (a, b) => (f(a), b) }
    }
    
    def in2[A, B] = new Functor[In2[A, B]] {
      def fmap[X] = f => { case (a, b) => (a, f(b)) }
    }
  }
  
  object list {
    import functorial.list._
    
    def inElem[T] = new Functor[InElem[T]] {
      def fmap[X] = f => xs => xs.map(f)
    }    
  }
}