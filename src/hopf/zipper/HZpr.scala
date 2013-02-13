package hopf.zipper

import hopf.functional.EndoOptional

trait HZpr {
  type This <: HZpr { type This = HZpr.this.This }
  def self: This

  /// / //             ////          //////
  // Movement
  //
  def start: This
  def end:   This

  def isStart: Boolean
  def isEnd:   Boolean

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
  // Other
  //
  def guarded(f: This => Option[This]) = f(self).getOrElse(self)
  def loop(f: This => Option[This]): This = f(self).map(_.loop(f)).getOrElse(self)
}