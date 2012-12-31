package hopf.categorical

trait Functor {
  type Type
  type Hole
  type Fill[X]
  
  def forgetStructure: Type
  
  val prop_solidarity: Type =:= Fill[Hole]
}

object functor {
  
  type FunctorialOf[T] = Functor { type Type = T }
  
  def withRefinement[T, R <: Functor](obj: T)
  (implicit ev: T =:= R#Fill[R#Hole]) = new Functor {
    type Type    = T    
    type Hole    = R#Hole
    type Fill[X] = R#Fill[X]
    
    def forgetStructure = obj
      
    val prop_solidarity = ev
  }
  
  object tuple2 {    
    type In1[A, B] = FunctorialOf[(A, B)] {
      type Hole    = A
      type Fill[X] = (X, B)
    }
    
    def in1[A, B](tup: (A, B)): In1[A, B] =
      withRefinement[(A, B), In1[A, B]](tup)
    
    type In2[A, B] = FunctorialOf[(A, B)] {
      type Hole    = B
      type Fill[X] = Tuple2[A, X]
    }
    
    def in2[A, B](tup: (A, B)): In2[A, B] =
      withRefinement[(A, B), In2[A, B]](tup)
    
    implicit class FunctorialEnriched[A, B](tup: (A, B)) {
      def functorialIn1 = in1(tup)
      def functorialIn2 = in2(tup)
    }
  }
  
  object list {    
    type InElem[T] = FunctorialOf[List[T]] {
      type Hole    = T
      type Fill[X] = List[X]
    }
  
    def inElem[T](list: List[T]): InElem[T] =
      withRefinement[List[T], InElem[T]](list)
  }  
}