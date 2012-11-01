package hopf.common

object TypeSynonyms {
  
  type Fun[-A, +B] = A => B
  
  type Tup[A, B] = (A, B)
}