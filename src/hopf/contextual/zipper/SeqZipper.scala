package hopf.contextual.zipper

trait SeqZipper[Z <: SeqZipper[Z]] {

  type PredicateDomain

  def start : Z
  def end   : Z

  def isStart : Boolean
  def isEnd   : Boolean

  def prev : Z
  def next : Z

  def skipPrev(n: Int): Z
  def skipNext(n: Int): Z

  private type P = PredicateDomain

  def skipPrevWhile(pred: P => Boolean): Z
  def skipNextWhile(pred: P => Boolean): Z

  def skipPrevUntil(pred: P => Boolean): Z = skipPrevWhile(!pred(_))
  def skipNextUntil(pred: P => Boolean): Z = skipNextWhile(!pred(_))
}

