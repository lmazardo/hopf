package hopf.util

package object types {
  type Fun[-A, +B] = A => B  
  type Tup[A, B] = (A, B)
  
  trait $[T[_, _], A] {
    type ?[B] = T[A, B]
  }
  
  
  // type Lol = (Fun $ Int)# ?
  
  // ?@ (Fun $ Int)
  //def f: Lol = (x: Int) => x + 1
  
}