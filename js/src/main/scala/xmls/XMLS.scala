package xmls

import scala.scalajs.js
import scala.scalajs.js.annotation.{ JSExportAll, JSExportTopLevel }

import js.JSConverters._

@JSExportTopLevel("XMLS")
@JSExportAll
object XMLS {

  @JSExportAll
  final case class JsNode(wrapped: Node) extends XmlNode[js.Array, JsAttribute] {
    override def children: js.Array[Element] = wrapped.children.map {
      case node: Node => JsNode(node)
      case element: Element => element
    }.toJSArray

    override def name: String = wrapped.name

    override def prefix: Option[String] = wrapped.prefix

    override def attrs: js.Array[JsAttribute] = wrapped.attrs.map(JsAttribute).toJSArray

    def attrsMap: js.Dictionary[_] = wrapped.attrs
      .groupBy(attr => attr.prefix.map(_ + ":").getOrElse("") + attr.name)
      .mapValues(_.toJSArray).toJSDictionary
  }

  @JSExportAll
  final case class JsAttribute(wrapped: Attribute) {
    def name: String = wrapped.name
    def value: String = wrapped.value.orNull
    def prefix: String = wrapped.prefix.orNull
  }

  def parse(
    string: String,
    success: js.Function1[JsNode, _],
    error: js.Function1[XmlParserError, _]): Unit = new Xml(string).parse.fold(error(_), node => success(JsNode(node)))
}
