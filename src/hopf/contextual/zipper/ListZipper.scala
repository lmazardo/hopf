package hopf.contextual.zipper

class ListZipper[T](val prefix: List[T], val elem: T, val suffix: List[T]) extends ListZipperTemplate[T, ListZipper[T]](prefix, elem, suffix) {
  def mk(prefix: List[T], elem: T, suffix: List[T]) = new ListZipper(prefix, elem, suffix)
}

object ListZipper {
  val lz = new ListZipper[Int](???,???,???)
}