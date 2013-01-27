package hopf.zipper.seq

import hopf.functional.EndoOptional

trait SeqZipper[Elem] {
  // this is actually ok, just abstype variant of F-bound
  // with type-arg it becomes SeqZipper[This <: SeqZipper[This]],
  // which looks a bit cuter, but much harder to handle in downward hierarchy
  type This <: SeqZipper[Elem] { type This = SeqZipper.this.This }
  type Repr[X]

  def self: This

  def prefix: Repr[Elem]
  def elem:   Elem
  def suffix: Repr[Elem]

  /// / //             ////          //////
  // Movement
  //
  def start: This
  def end:   This

  def prev: Option[This]
  def next: Option[This]

  def nthPrev(n: Int): Option[This] = EndoOptional[This](_.prev).times(n)(self)
  def nthNext(n: Int): Option[This] = EndoOptional[This](_.next).times(n)(self)

  def skipPrevUntil(pred: This => Boolean): Option[This] = EndoOptional[This](_.prev).until(pred)(self)
  def skipNextUntil(pred: This => Boolean): Option[This] = EndoOptional[This](_.next).until(pred)(self)

  def skipPrevWhile(pred: This => Boolean): Option[This] = skipPrevUntil(!pred(_))
  def skipNextWhile(pred: This => Boolean): Option[This] = skipNextUntil(!pred(_))

  /// / //             ////          //////
  // Bounded movement
  //
  def prevBounded: This = prev.getOrElse(self)
  def nextBounded: This = next.getOrElse(self)

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
  def wrapPrefix(f: Repr[Elem] => Elem)/*(implicit P: Point[Repr])*/: This
  def wrapSuffix(f: Repr[Elem] => Elem)/*(implicit P: Point[Repr])*/: This

  def wrapPrefixInclusive(f: Elem => Repr[Elem] => Elem): This
  def wrapSuffixInclusive(f: Elem => Repr[Elem] => Elem): This

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

  def insertPrevMany(xs: Repr[Elem]): This
  def insertNextMany(xs: Repr[Elem]): This

  /// / //             ////          //////
  // Other
  //
  def isStart: Boolean
  def isEnd:   Boolean

  def guarded(f: This => Option[This]) = f(self).getOrElse(self)

  def loop(f: This => Option[This]): This = f(self).map(_.loop(f)).getOrElse(self)
}

object SeqZipper {
  trait Functoriality[Elem] {
    type ThisPoly[X] <: SeqZipper[X]
    def map[X](f: Elem => X): ThisPoly[X]
  }
}