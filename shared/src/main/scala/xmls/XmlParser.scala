package xmls

import org.parboiled2._

import scala.scalajs.js.annotation.JSExportAll
import scala.util.{ Failure, Success }
import scala.xml._

@JSExportAll
final case class Opening(name: String, attrs: Seq[MetaData], prefix: Option[String] = None)
@JSExportAll
final case class Closing(name: Option[String], prefix: Option[String] = None)

@JSExportAll
class XmlParserError(errorMessage: String, cause: Option[Throwable] = None) extends RuntimeException(errorMessage, cause.orNull)

@JSExportAll
class XmlParser(val input: ParserInput) extends Parser {

  def parse: Either[XmlParserError, scala.xml.Node] =
    root.run() match {
      case Success(node) => Right(node)
      case Failure(error: XmlParserError) => Left(error)
      case Failure(parseError: ParseError) => Left(new XmlParserError(formatError(parseError, new ErrorFormatter(showTraces = true))))
      case Failure(error) => Left(new XmlParserError(error.getMessage, Some(error)))
    }

  def root: Rule1[scala.xml.Node] = rule {
    whitespace ~ optional(xmlHeader) ~ (nodeWithChildren | singleNode) ~ whitespace ~ EOI
  }

  def xmlHeader = rule {
    "<?" ~ whitespace ~ ("xml" | "XML") ~ oneOrMore((visibleCharacter -- "?") | " ") ~ "?>"
  }

  def nodeWithChildren: Rule1[scala.xml.Node] = rule {
    whitespace ~ opening ~ whitespace ~ zeroOrMore(textElement | comment | cdata | nodeWithChildren | singleNode) ~ whitespace ~ closing ~> ((opening: Opening, children: Seq[scala.xml.Node], closing: Closing) => {
      if (closing.name.exists(closeTag => !opening.name.equals(closeTag))) throw new XmlParserError(s"opening ($opening) and closing ($closing) tag dont match")
      else scala.xml.Elem(opening.prefix.orNull, opening.name, opening.attrs.headOption.map(first => opening.attrs.tail.foldLeft(first)(_ append _)).getOrElse(Null), TopScope, children: _*)
    })
  }

  def cdata: Rule1[scala.xml.PCData] = rule {
    whitespace ~ "<![" ~ ("CDATA" | "cdata" | "CData" | "cData") ~ "[" ~ capture(cdataContent) ~ "]]>" ~> ((text: String) => PCData(text))
  }

  def comment: Rule1[scala.xml.Comment] = rule {
    whitespace ~ "<!--" ~ capture(commentContent) ~ "-->" ~> ((text: String) => scala.xml.Comment(text))
  }

  def textElement: Rule1[scala.xml.Text] = rule {
    capture(text) ~> ((value: String) => scala.xml.Text(value))
  }

  def prefix = rule {
    capture(identifier) ~ ":"
  }

  def tag = rule {
    optional(prefix) ~ capture(identifier) ~ zeroOrMore(attribute) ~ whitespace
  }

  def singleNode: Rule1[scala.xml.Node] = rule {
    whitespace ~ '<' ~ tag ~ "/>" ~> ((prefix: Option[String], name: String, attributes: Seq[MetaData]) => scala.xml.Elem(prefix.orNull, name, Null, TopScope, false, Seq.empty[scala.xml.Node]: _*))
  }

  def opening: Rule1[Opening] = rule {
    '<' ~ tag ~ '>' ~> ((prefix: Option[String], name: String, attributes: Seq[MetaData]) => Opening(name, attributes, prefix))
  }

  def closing = rule {
    "</" ~ optional(prefix) ~ capture(identifier) ~ '>' ~> ((prefix: Option[String], tagName: String) => Closing(Some(tagName), prefix))
  }

  def attribute: Rule1[MetaData] = rule {
    whitespace ~ optional(prefix) ~ capture(identifier) ~
      optional(whitespace ~ '=' ~ whitespace ~ ('"' ~ optional(capture(attrributeText)) ~ '"' | "'" ~ optional(capture(attrributeTextSingleQuoted)) ~ "'")) ~>
      ((prefix: Option[String], name: String, value: Option[Option[String]]) => Attribute.apply(prefix, name, value.flatten.toSeq.map(Text.apply), Null))
  }

  def visibleCharacter = CharPredicate.All

  def text = rule {
    oneOrMore((visibleCharacter -- '<' -- '>') | whiteSpaceChar)
  }

  def attrributeText = rule {
    oneOrMore((visibleCharacter -- '"' -- '<') | whiteSpaceChar)
  }

  def attrributeTextSingleQuoted = rule {
    oneOrMore((visibleCharacter -- "'" -- '<') | whiteSpaceChar)
  }

  def commentContent = rule {
    oneOrMore(!"-->" ~ visibleCharacter | !"-->" ~ whiteSpaceChar)
  }

  def cdataContent = rule {
    oneOrMore(!"]]>" ~ visibleCharacter | !"]]>" ~ whiteSpaceChar)
  }

  def identifier = rule {
    oneOrMore(CharPredicate.AlphaNum ++ '-' ++ '.' ++ '_')
  }

  def whitespace = rule {
    zeroOrMore(whiteSpaceChar)
  }

  def whiteSpaceChar = rule {
    " " | "\t" | "\n"
  }

}
