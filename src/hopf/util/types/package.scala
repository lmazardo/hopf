package hopf.util

package object types {
  type Fun[-A, +B] = A => B  
  type Tup[A, B] = (A, B)
  
  trait $1[T[_, _], A] {
    type ?[B] = T[A, B]
  }
  
  trait $2[T[_, _, _], A] {
    type ?[B, C] = T[A, B, C]
  }
  
  trait $3[T[_, _, _, _], A] {
    type ?[B, C, D] = T[A, B, C, D]
  }
  
  trait $4[T[_, _, _, _, _], A] {
    type ?[B, C, D, E] = T[A, B, C, D, E]
  }
  
  type Pa1[T[_, _], A] = T $1 A
  
  type Pa2[T[_, _, _], A, B] = (T $2 A)# ? $1 B
  
  type Pa3[T[_, _, _, _], A, B, C] = ((T $3 A)# ? $2 B)# ? $1 C
  
  type Pa4[T[_, _, _, _, _], A, B, C, D] = (((T $4 A)# ? $3 B)# ? $2 C)# ? $1 D
  
  // implicit def ff[C[_,_], A, B](x: C[A,B]): Pa1[C,A] = x
  
  // type Lol = (Fun $ Int)# ?
  
  // ?@ (Fun $ Int)
  //def f: Lol = (x: Int) => x + 1
  
}