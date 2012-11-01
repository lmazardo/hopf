package hopf.common

object IntShorthands {
  type I = Int
  def add(x:I) = (y:I) => x + y
  def mul(x:I) = (y:I) => x * y
}
  
object FloatShorthands {
  type F = Float
  def add(x:F) = (y:F) => x + y
  def mul(x:F) = (y:F) => x * y
}

object NumericShorthands {
  type I = Int
  def iadd = IntShorthands.add(_)
  def imul = IntShorthands.mul(_)
  
  type F = Float
  def fadd = FloatShorthands.add(_)
  def fmul = FloatShorthands.mul(_)  
}