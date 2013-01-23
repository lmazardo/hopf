package hopf.zippers.list
/*
import hopf.zipper.seq.SeqZipperElems

trait ListZipperElemsTpl[Elem, This <: ListZipperTpl[Elem, This]]
extends SeqZipperElems[Elem, This] {
  this: This =>

  type Repr[X] = List[X]

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
}*/