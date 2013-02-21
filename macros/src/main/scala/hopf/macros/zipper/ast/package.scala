package hopf.macros.zipper

import language.experimental.macros
import scala.reflect.macros._
// import hopf.zipper.generic._

package object ast {
  /*
  implicit val select = new MkLSib[Select] {
    def apply(s: Select) = ConsLS(ConsLS(
      UnitLS{q: Tree => nm: Name => tc.Select(s, q, nm)},
      s.qualifier),
      s.name)
  }
   * */

/*
            DefDef(Modifiers(), TermName("createEntity"), List(), List(List(ValDef(Modifiers(PARAM), TermName("rs"), resultSet, EmptyTree))), TypeTree(), Block(List(), createEntity)),
            DefDef(Modifiers(), TermName("entityId"), List(), List(List(ValDef(Modifiers(PARAM), TermName("entity"), Ident(caseClass.name), EmptyTree))), TypeTree(), Block(List(), Select(Ident(TermName("entity")), TermName("id")))),
            DefDef(Modifiers(), TermName("insert"), List(), List(insertParams), TypeTree(), Block(List(), Apply(Select(Super(This(tpnme.EMPTY), tpnme.EMPTY), TermName("insertImpl")), insertArgs)))
 */

  def declsOf[T]: String = macro declsOf_impl[T]

  def declsOf_impl[T: c.WeakTypeTag](c: Context): c.Expr[String] = {
    val sym = c.weakTypeOf[T].declarations.toList(4)
    // c.literal(sym.asMethod.name.toString) "<init>"
    // c.literal(sym.asMethod.returnType.toString) // T
    c.literal(sym.asMethod.paramss.toString)
  }

  // case class MkLSib[T](x: Int)

  def test: String = macro test_impl

  /*println(hopf.macros.zipper.ast.showRawTree(new MkLSib[T] {
    def apply(t: T) = ConsLS(ConsLS(
      UnitLS{a: Int => b: Int => T(a, b)},
      12),
      24)
  }))*/

  def test_impl(c: Context): c.Expr[String] = {
    import c.universe._
    val treeType = c.typeOf[c.universe.type].member(newTypeName("Tree")).allOverriddenSymbols.head.asType.toType
    val members = c.typeOf[c.universe.type].members
    val meths = members.filter { sym =>
      sym.isMethod && sym.asMethod.isGetter
    } map { sym =>
      (sym, sym.typeSignature.member(newTermName("apply")))
    } collect { case (sym, appSym) if appSym != NoSymbol && appSym.isMethod =>
      (sym, appSym.asMethod)
    } filter { case (_, meth) =>
      meth.returnType.typeSymbol.asType.toType <:< treeType &&
      meth.paramss.head.exists(_.typeSignature.typeSymbol.asType.toType <:< treeType)
    } map { case (sym, meth) =>
      val treeParams = meth.paramss.head.filter { p =>
        val ty = p.typeSignature.typeSymbol.asType.toType
        ty <:< treeType ||
        ty <:< typeOf[List[Any]] // &&
        // ty.exists(_ <:< treeType)
      }
      val lam = treeParams.foldRight(q"()": Tree) { case (arg, term) =>
        // val ty = arg.typeSignature
        Function(List(ValDef(Modifiers(Flag.PARAM), TermName(arg.name.toString),
            TypeTree().setType(arg.typeSignature), EmptyTree)), term)
      }
      val name = TermName("mk" + sym.name + "LSib")
      q"""
      implicit val $name = new MkLSib[T] {
      	def apply(t: T) = UnitLS($lam)
      }"""
     lam
    }

    // c.literal(vals.toList.toString)
    // c.literal(showRaw(reify { implicit val lol = new MkLSib[Select](123) }))
    List("2","3")
    c.literal(meths.mkString("\n"))
  }

  def showRawTree(x: Any): String = macro showRawTree_impl

  def showRawTree_impl(c: Context)(x: c.Tree): c.Expr[String] = {
    c.literal(c.universe.showRaw(x))
  }

  def fff: String = macro fff_impl
  def fff_impl(c: Context): c.Expr[String] = {
    import c.universe._
    val t = Apply(q"treeCopy", List(Ident("x")))
    c.literal(t.toString)
  }
// ValDef(Modifiers(Modifiers.IMPLICIT), TermName("mk" + sym.name + "LSib"), TypeTree(),)
//Expr(Block(List(ValDef(Modifiers(IMPLICIT), TermName("lol"), TypeTree(), Apply(Select(New(AppliedTypeTree(Select(This(tpnme.PACKAG
// E), TypeName("MkLSib")), List(TypeTree()))), nme.CONSTRUCTOR), List(Literal(Constant(123)))))), Literal(Constant(()))))

  /*
  def test: String = macro test_impl

  def test_impl(c: Context): c.Expr[String] = {
    val treeType = c.typeOf[c.universe.Tree]
    val members  = c.typeOf[c.universe.type].members

    val xs = members.collect { case s if s.isTerm =>
      s.typeSignature.member(c.universe.newTermName("apply"))
    } collect { case appSym if appSym != c.universe.NoSymbol && appSym.isMethod =>
      appSym.asMethod.returnType.typeSymbol.typeSignatureIn(c.typeOf[c.universe.type]) <:< treeType
    }

    c.literal(xs.toList.toString)
  }*/

  def genMkLSib(c: Context): c.Tree = {
    import c.universe._
    // val t = c.typeOf[this.type]

    // t.members.head.asType.toTypeConstructor
    // val x = DefDef(Modifiers(), TermName)
    ???
  }
}