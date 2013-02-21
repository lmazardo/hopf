package hopf

import scala.collection.generic.FilterMonadic

package object categorical {
  type Functorial[E, R[_]] = FilterMonadic[E, R[E]]
}