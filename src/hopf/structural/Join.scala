package hopf.structural

trait Join[S[_]] {
  def join[A](x: S[S[A]]): S[A]
}

trait Cojoin[S[_]] {
  def cojoin[A](x: S[A]): S[S[A]]
}