package hopf.contextual.zipper

abstract class ListZipperTemplate[T, Z <: ListZipperTemplate[T, Z]]
    (prefix: List[T], elem: T, suffix: List[T])
extends SeqZipper[Z] {

  type PredicateDomain = T

  def mk(prefix: List[T], elem: T, suffix: List[T]): Z

  def start = toList match { case x :: xs => mk(Nil, x,       xs     ) }
  def end   = toList match { case   xs    => mk(Nil, xs.last, xs.init) }

  def isStart = prefix.isEmpty
  def isEnd   = suffix.isEmpty

  def prev = mk(prefix.tail, prefix.head, elem :: suffix)
  def next = mk(elem :: prefix, suffix.head, suffix.tail)

  def prevWithSplitter(f: List[T] => (List[T], List[T])) = {
    val (s, newPrefix) = f(prefix)
    val sr = s.reverse
    mk(newPrefix, sr.head, sr.tail ++ (elem :: suffix))
  }

  def nextWithSplitter(f: List[T] => (List[T], List[T])) = {
    val (s, newSuffix) = f(suffix)
    val sr = s.reverse
    mk(sr.tail ++ (elem :: prefix), sr.head, newSuffix)
  }

  def skipPrev(n: Int) = prevWithSplitter(_.splitAt(n))
  def skipNext(n: Int) = nextWithSplitter(_.splitAt(n))

  def skipPrevWhile(pred: T => Boolean) = prevWithSplitter(_.span(pred))
  def skipNextWhile(pred: T => Boolean) = nextWithSplitter(_.span(pred))

  def toList = prefix.reverse ++ (elem :: suffix)
}