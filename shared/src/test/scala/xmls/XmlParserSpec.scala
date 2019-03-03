package xmls

import org.scalatest.{ FlatSpec, Matchers }

import scala.xml.{ PCData, Text }

class XmlParserSpec extends FlatSpec with Matchers {
  "A xml parser" should "parse single root" in {
    val parsed = new XmlParser("<a></a>").parse
    val node = parsed.right.get

    node.label should equal("a")
    node.child should be(empty)
  }

  it should "parse closed root" in {
    val node = new XmlParser("<a/>").parse.right.get

    node.label should equal("a")
    node.child should be(empty)
  }

  it should "parse element with prefix" in {
    val node = new XmlParser("<prefix:a></prefix:a>").parse.right.get

    node.label should equal("a")
    node.prefix should be("prefix")
    node.child should be(empty)
  }

  it should "parse closed element with prefix" in {
    val node = new XmlParser("<prefix:a/>").parse.right.get

    node.label should equal("a")
    node.prefix should be("prefix")
    node.child should be(empty)
  }

  it should "parse children" in {
    val node = new XmlParser("<root> <child></child> </root>").parse.right.get

    node.label should equal("root")
    node.child should contain(<child></child>)
  }

  it should "parse self-closing children" in {
    val node = new XmlParser("<root><child/><child/></root>").parse.right.get

    node.label should equal("root")
    node.child.head equals <child/>
    node.child.tail.head equals <child/>
  }

  it should "parse attributes" in {
    val node = new XmlParser(s"""<element attr1 attr_underscore attr-dash attr2="value" attr3 = "anotherValue" attr4='singleQuoted'></element>""").parse.right.get

    node.label should equal("element")

    node.attribute("notanattribute") should be(None)
    node.attribute("attr_underscore") should be(Some(Seq.empty))
    node.attribute("attr-dash") should be(Some(Seq.empty))
    node.attribute("attr1") should be(Some(Seq.empty))
    node.attribute("attr2") should be(Some(Seq(Text("value"))))
    node.attribute("attr3") should be(Some(Seq(Text("anotherValue"))))
    node.attribute("attr4") should be(Some(Seq(Text("singleQuoted"))))
  }

  it should "parse text" in {
    val node = new XmlParser(s"""<text>some text</text>""").parse.right.get

    node.label should equal("text")
    node.child should contain(scala.xml.Text("some text"))
  }

  it should "parse multiple text elements" in {
    val node = new XmlParser(s"""<text>some text <i>italic</i> another text</text>""").parse.right.get

    node.label should equal("text")
    node.child should contain(scala.xml.Text("some text "))
    node.child should contain(scala.xml.Text(" another text"))
  }

  it should "parse CDATA" in {
    val node = new XmlParser(s"""<text><![CDATA[some text]]></text>""").parse.right.get

    node.label should equal("text")
    node.child.head should be(PCData("some text"))
  }

  it should "parse multiline CDATA" in {
    val node = new XmlParser(
      s"""<text><![CDATA[
         |some more
         |text]]></text>""".stripMargin).parse.right.get

    node.label should equal("text")
    node.child.head should be(PCData("\nsome more\ntext"))
  }

  it should "throw an error on tag mismatch" in {
    new XmlParser(s"""<foo></bar>""").parse.left.get should be(a[XmlParserError])
  }

  it should "parse a Wikipedia XML" in {
    val node = new XmlParser(XmlExamples.wikipediaXml).parse
    node.isRight should be(true)
  }

  it should "parse a GraphML XML" in {
    val node = new XmlParser(XmlExamples.graphMl).parse
    node.isRight should be(true)
  }

  it should "parse simple BPMN 2.0 XML" in {
    val node = new XmlParser(XmlExamples.bpmnProcess).parse.right.get

    node.label should equal("definitions")
    node.child should have size 4
    node.child.head.child.find {
      case n: scala.xml.Node => n.attributes.exists(_.value.contains(Text("Task_1bv0mxh")))
      case _ => false
    } should be(defined)
  }
}
