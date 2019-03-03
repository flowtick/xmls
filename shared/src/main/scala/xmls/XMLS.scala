package xmls

import scala.scalajs.js.annotation.{ JSExportAll, JSExportTopLevel }
import scala.xml.Node

@JSExportTopLevel("XMLS")
@JSExportAll
object XMLS {
  def parse(string: String): Either[XmlParserError, Node] = new XmlParser(string).parse

  def onParse(
    string: String,
    success: scala.xml.Node => _,
    error: XmlParserError => _): Unit = parse(string).fold(error(_), node => success(node))
}
