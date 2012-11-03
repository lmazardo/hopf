package hopf.common

package object numeric {
  implicit class CurriedArithEnrichedNumeric[T](x: T)(implicit N: Numeric[T]) {
    import N._
    def add : T => T = x + _
    def sub : T => T = x - _
    def mul : T => T = x * _
  }
  
  implicit class CurriedArithEnrichedFractional[T](x: T)(implicit F: Fractional[T]) {
    import F._
    def div : T => T = x / _
  }
}