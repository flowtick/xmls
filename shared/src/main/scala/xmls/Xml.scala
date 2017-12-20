package xmls

import org.parboiled2._

import scala.scalajs.js.annotation.JSExportAll
import scala.util.{ Failure, Success }

@JSExportAll
final case class Opening(name: String, attrs: Seq[Attribute], prefix: Option[String] = None)
@JSExportAll
final case class Closing(name: Option[String], prefix: Option[String] = None)
@JSExportAll
final case class Attribute(name: String, value: Option[String], prefix: Option[String] = None)

trait Element

trait XmlNode[ListType[_], AttributeType] extends Element {
  def name: String
  def attrs: ListType[AttributeType]
  def children: ListType[Element]
  def prefix: Option[String]
}

@JSExportAll
final case class Node(name: String, attrs: Seq[Attribute], children: Seq[Element], prefix: Option[String] = None) extends XmlNode[Seq, Attribute]
@JSExportAll
final case class Comment(text: String) extends Element
@JSExportAll
final case class CharacterData(text: String) extends Element
@JSExportAll
final case class Text(value: String) extends Element

@JSExportAll
class XmlParserError(errorMessage: String, cause: Option[Throwable] = None) extends RuntimeException(errorMessage, cause.orNull)

@JSExportAll
class Xml(val input: ParserInput) extends Parser {

  def parse: Either[XmlParserError, Node] =
    root.run() match {
      case Success(node) => Right(node)
      case Failure(error: XmlParserError) => Left(error)
      case Failure(parseError: ParseError) => Left(new XmlParserError(formatError(parseError, new ErrorFormatter(showTraces = true))))
      case Failure(error) => Left(new XmlParserError(error.getMessage, Some(error)))
    }

  def root: Rule1[Node] = rule {
    whitespace ~ optional(xmlHeader) ~ (nodeWithChildren | singleNode) ~ whitespace ~ EOI
  }

  def xmlHeader = rule {
    "<?" ~ whitespace ~ ("xml" | "XML") ~ oneOrMore((visibleCharacter -- "?") | " ") ~ "?>"
  }

  def nodeWithChildren: Rule1[Node] = rule {
    whitespace ~ opening ~ whitespace ~ zeroOrMore(textElement | comment | cdata | nodeWithChildren | singleNode) ~ whitespace ~ closing ~> ((opening: Opening, children: Seq[Element], closing: Closing) => {
      if (closing.name.exists(closeTag => !opening.name.equals(closeTag))) throw new XmlParserError(s"opening ($opening) and closing ($closing) tag dont match")
      else Node(opening.name, opening.attrs, children, opening.prefix)
    })
  }

  def cdata: Rule1[Element] = rule {
    whitespace ~ "<![" ~ ("CDATA" | "cdata" | "CData" | "cData") ~ "[" ~ capture(cdataContent) ~ "]]>" ~> ((text: String) => CharacterData(text))
  }

  def comment: Rule1[Element] = rule {
    whitespace ~ "<!--" ~ capture(commentContent) ~ "-->" ~> ((text: String) => Comment(text))
  }

  def textElement: Rule1[Text] = rule {
    capture(text) ~> ((value: String) => Text(value))
  }

  def prefix = rule {
    capture(identifier) ~ ":"
  }

  def tag = rule {
    optional(prefix) ~ capture(identifier) ~ zeroOrMore(attribute) ~ whitespace
  }

  def singleNode: Rule1[Node] = rule {
    whitespace ~ '<' ~ tag ~ "/>" ~> ((prefix: Option[String], name: String, attributes: Seq[Attribute]) => Node(name, attributes, Seq.empty, prefix))
  }

  def opening: Rule1[Opening] = rule {
    '<' ~ tag ~ '>' ~> ((prefix: Option[String], name: String, attributes: Seq[Attribute]) => Opening(name, attributes, prefix))
  }

  def closing = rule {
    "</" ~ optional(prefix) ~ capture(identifier) ~ '>' ~> ((prefix: Option[String], tagName: String) => Closing(Some(tagName), prefix))
  }

  def attribute: Rule1[Attribute] = rule {
    whitespace ~ optional(prefix) ~ capture(identifier) ~
      optional(whitespace ~ '=' ~ whitespace ~ ('"' ~ optional(capture(attrributeText)) ~ '"' | "'" ~ optional(capture(attrributeTextSingleQuoted)) ~ "'")) ~>
      ((prefix: Option[String], name: String, value: Option[Option[String]]) => Attribute(name, value.flatten, prefix))
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
