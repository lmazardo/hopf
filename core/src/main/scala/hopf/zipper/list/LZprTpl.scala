package hopf.zipper.list

import hopf.zipper.SeqZpr

trait LZprTpl[Elem] extends SeqZpr[Elem] {
  type Repr = List[Elem]

  def mk(prefix: List[Elem], elem: Elem, suffix: List[Elem]): This

  def toList = prefix.reverse ++ (elem :: suffix)

  def isStart = prefix.isEmpty
  def isEnd   = suffix.isEmpty

  /// / //             ////          //////
  // Movement
  //
  def start = toList match { case x :: xs => mk(Nil, x,       xs     ) }
  def end   = toList match { case   xs    => mk(Nil, xs.last, xs.init) }

  def prev = if (isStart) None else Some(mk(prefix.tail, prefix.head, elem :: suffix))
  def next = if (isEnd  ) None else Some(mk(elem :: prefix, suffix.head, suffix.tail))

  /// / //             ////          //////
  // Wrappings
  //
  def wrapPrefix(f: Repr => Elem) = mk(List(f(prefix)), elem, suffix)
  def wrapSuffix(f: Repr => Elem) = mk(prefix, elem, List(f(suffix)))

  def wrapPrefixInclusive(f: Elem => Repr => Elem) = mk(Nil, f(elem)(prefix), suffix)
  def wrapSuffixInclusive(f: Elem => Repr => Elem) = mk(prefix, f(elem)(suffix), Nil)

  /// / //             ////          //////
  // Mappings
  //
  def mapElem  (f: Elem => Elem) = mk(prefix, f(elem), suffix)
  def mapPrefix(f: Elem => Elem) = mk(prefix.map(f), elem, suffix)
  def mapSuffix(f: Elem => Elem) = mk(prefix, elem, suffix.map(f))

  /// / //             ////          //////
  // Insertions
  //
  def insertPrev(x: Elem) = mk(x :: prefix, elem, suffix)
  def insertNext(x: Elem) = mk(prefix, elem, x :: suffix)

  def insertPrevMany(xs: Repr) = mk(xs ++ prefix, elem, suffix)
  def insertNextMany(xs: Repr) = mk(prefix, elem, xs ++ suffix)
}