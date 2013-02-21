package hopf.zipper.ast

import scala.reflect.macros.Context

abstract class TreeZipper[C <: Context](val context: C) {
  import context.universe._

  type This  <: TreeZipper[C] { type This = TreeZipper.this.This }
  type Focus <: Tree

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

  /// / //             ////          //////
  // Walks
  //
  def walkBwUntil(cond: Tree => Boolean): Option[This]
  def walkFwUntil(cond: Tree => Boolean): Option[This]

  def walkBwUntilOf[T <: Tree](cond: T => Boolean = ktrue[T]): Option[This { type Focus = T }]
  def walkFwUntilOf[T <: Tree](cond: T => Boolean = ktrue[T]): Option[This { type Focus = T }]

  /// / //             ////          //////
  // Private stuff
  //
  private def ktrue[T] = { _: T => true }
}

object TreeZipper