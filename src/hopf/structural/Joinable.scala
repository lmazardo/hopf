package hopf.structural

trait Joinable[S[_]] {
  def join[A]: S[S[A]] => S[A]
}

trait Cojoinable[S[_]] {
  def cojoin[A]: S[A] => S[S[A]]
}