package hopf.zippers.list

class ListZipper[Elem] (
  prefix: List[Elem],
  elem:   Elem,
  suffix: List[Elem]
)
extends ListZipperTpl    [Elem] (prefix, elem, suffix)
   with ListZipperMapTpl [Elem] {

  type This = this.type
  type ThisG[X] = ListZipper[X]

  def mkG[E](prefix: List[E], elem: E, suffix: List[E]) =
    new ListZipper[E](prefix, elem, suffix)
}

object ListZipper {
  val lz = new ListZipper[Int](???,???,???)
  val r = lz.next
}