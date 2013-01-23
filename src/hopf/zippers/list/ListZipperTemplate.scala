package hopf.zippers.list

import hopf.zipper.seq._
import hopf.categorical.Point

trait ListZipperTemplate[Elem] extends SeqZipper with SeqZipperElems[Elem] {
  type Repr[X] = List[X]

  def mk(prefix: List[Elem], elem: Elem, suffix: List[Elem]): This

  def toList = prefix.reverse ++ (elem :: suffix)

  def start = toList match { case x :: xs => mk(Nil, x,       xs     ) }
  def end   = toList match { case   xs    => mk(Nil, xs.last, xs.init) }

  def isStart = prefix.isEmpty
  def isEnd   = suffix.isEmpty

  def prev = mk(prefix.tail, prefix.head, elem :: suffix)
  def next = mk(elem :: prefix, suffix.head, suffix.tail)

  def prevWithSplitter(f: List[Elem] => (List[Elem], List[Elem])) = {
    val (s, newPrefix) = f(prefix)
    val sr = s.reverse
    mk(newPrefix, sr.head, sr.tail ++ (elem :: suffix))
  }

  def nextWithSplitter(f: List[Elem] => (List[Elem], List[Elem])) = {
    val (s, newSuffix) = f(suffix)
    val sr = s.reverse
    mk(sr.tail ++ (elem :: prefix), sr.head, newSuffix)
  }

  def skipPrev(n: Int) = prevWithSplitter(_.splitAt(n))
  def skipNext(n: Int) = nextWithSplitter(_.splitAt(n))

  override def skipPrevWhileE(pred: Elem => Boolean) = prevWithSplitter(_.span(pred))
  override def skipNextWhileE(pred: Elem => Boolean) = nextWithSplitter(_.span(pred))

  def wrapPrefix(f: Repr[Elem] => Elem) = mk(List(f(prefix)), elem, suffix)
  def wrapSuffix(f: Repr[Elem] => Elem) = mk(prefix, elem, List(f(suffix)))

  def wrapPrefixInclusive(f: Elem => Repr[Elem] => Elem) = mk(Nil, f(elem)(prefix), suffix)
  def wrapSuffixInclusive(f: Elem => Repr[Elem] => Elem) = mk(prefix, f(elem)(suffix), Nil)

  def mapElem  (f: Elem => Elem) = mk(prefix, f(elem), suffix)
  def mapPrefix(f: Elem => Elem) = mk(prefix.map(f), elem, suffix)
  def mapSuffix(f: Elem => Elem) = mk(prefix, elem, suffix.map(f))
}