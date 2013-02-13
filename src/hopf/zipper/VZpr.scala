package hopf.zipper

trait VZpr {
  type This <: VZpr { type This = VZpr.this.This }
  def self: This

  /// / //             ////          //////
  // Movement
  //
  def top: This

  def leftBottom:  This
  def rightBottom: This
  def bottoms:     Seq[This]

  def isTop:    Boolean
  def isBottom: Boolean

  def up:   Option[This]
  def down: Option[This]
}