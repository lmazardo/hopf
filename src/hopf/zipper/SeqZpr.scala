package hopf.zipper

trait SeqZpr[Elem] extends HZpr {
  type This <: SeqZpr[Elem] { type This = SeqZpr.this.This }
  type Repr

  def prefix: Repr
  def elem:   Elem
  def suffix: Repr

  /// / //             ////          //////
  // Element-dependent movement
  //
  def skipPrevUntilE(pred: Elem => Boolean): Option[This] = self.skipPrevUntil(z => pred(z.elem))
  def skipNextUntilE(pred: Elem => Boolean): Option[This] = self.skipNextUntil(z => pred(z.elem))

  def skipPrevWhileE(pred: Elem => Boolean): Option[This] = skipPrevUntilE(!pred(_))
  def skipNextWhileE(pred: Elem => Boolean): Option[This] = skipNextUntilE(!pred(_))

  /// / //             ////          //////
  // Wrappings
  //
  def wrapPrefix(f: Repr => Elem): This
  def wrapSuffix(f: Repr => Elem): This

  def wrapPrefixInclusive(f: Elem => Repr => Elem): This
  def wrapSuffixInclusive(f: Elem => Repr => Elem): This

  /// / //             ////          //////
  // Mappings
  //
  def mapElem  (f: Elem => Elem): This
  def mapPrefix(f: Elem => Elem): This
  def mapSuffix(f: Elem => Elem): This

  /// / //             ////          //////
  // Insertions
  //
  def insertPrev(x: Elem): This
  def insertNext(x: Elem): This

  def insertPrevMany(xs: Repr): This
  def insertNextMany(xs: Repr): This
}

object SeqZpr {
  trait Functor[Elem] {
    type ThisPoly[X] <: SeqZpr[X]
    def map[X](f: Elem => X): ThisPoly[X]
  }
}