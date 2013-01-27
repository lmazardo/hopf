package hopf.zippers.list

import hopf.zipper.seq.SeqZipper

final class ListZipper[Elem] (
  val prefix: List[Elem],
  val elem:   Elem,
  val suffix: List[Elem]
)
extends ListZipperTemplate[Elem] with SeqZipper.Functoriality[Elem] {
  type ThisPoly[X] = ListZipper[X]
  type This        = ThisPoly[Elem]

  def self = this

  def map[X](f: Elem => X) = mkPoly[X](prefix.map(f), f(elem), suffix.map(f))

  def mkPoly[E](prefix: List[E], elem: E, suffix: List[E]) =
    new ListZipper[E](prefix, elem, suffix)

  def mk(prefix: List[Elem], elem: Elem, suffix: List[Elem]) =
    mkPoly(prefix, elem, suffix)
}