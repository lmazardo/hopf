package hopf

package object structural {
  implicit class PointEnriched[A](x: A) {
    def point[P[_]: Pointable] =
      implicitly[Pointable[P]].point(x)  
  }
  
  implicit class JoinEnriched[S[_], A](x: S[S[A]])(implicit J: Joinable[S]) {
    def join = J.join(x)  
  }
}