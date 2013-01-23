package hopf.zippers.list

class ListZipper[Elem] (
  val prefix: List[Elem],
  val elem:   Elem,
  val suffix: List[Elem]
)
extends ListZipperTemplate[Elem] with ListZipperFunctoriality[Elem] {
  type ThisPoly[X] = ListZipper[X]
  type This        = ThisPoly[Elem]

  def self = this

  def mkPoly[E](prefix: List[E], elem: E, suffix: List[E]) =
    new ListZipper[E](prefix, elem, suffix)
}

object ListZipper {
  val lz = new ListZipper[Int](???,???,???)
  val r = lz.next
}