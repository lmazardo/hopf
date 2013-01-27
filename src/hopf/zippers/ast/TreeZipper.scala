package hopf.zippers.ast

import scala.reflect.macros.Context

abstract class TreeZipper[C <: Context](val context: C) {
  import context.universe._

  type This  <: TreeZipper[C] { type This = TreeZipper.this.This }
  type Focus <: Tree

  /// / //             ////          //////
  // Deep search
  //
  def findPrev(cond: Tree => Boolean): This
  def findNext(cond: Tree => Boolean): This

  def findPrevOf[T <: Tree]: This { type Focus = T }
  def findNextOf[T <: Tree]: This { type Focus = T }

  /// / //             ////          //////
  // Shallow search
  //
  def findShallowlyPrev(cond: Tree => Boolean): This
  def findShallowlyNext(cond: Tree => Boolean): This

  def findShallowlyPrevOf[T <: Tree]: This { type Focus = T }
  def findShallowlyNextOf[T <: Tree]: This { type Focus = T }
}