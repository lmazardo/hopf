package hopf.zipper.seq

import hopf.categorical._

trait SeqZipperFunctoriality[Elem] {
  type ThisPoly[X] <: SeqZipper

  def map[X](f: Elem => X): ThisPoly[X]
}

/*
trait SeqZipperMap[Elem] {
  this: SeqZipperElems[Elem] {
    type Repr[X] <: Functorial[X, Repr]
  } =>

  def map[X](f: Elem => X): This[X]
}
*/