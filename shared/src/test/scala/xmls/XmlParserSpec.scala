package xmls

import org.scalatest.{ FlatSpec, Matchers }

class XmlParserSpec extends FlatSpec with Matchers {
  "A xml parser" should "parse single root" in {
    val node = new Xml("<a></a>").parse.right.get

    node.name should equal("a")
    node.children should be(empty)
  }

  it should "parse closed root" in {
    val node = new Xml("<a/>").parse.right.get

    node.name should equal("a")
    node.children should be(empty)
  }

  it should "parse element with prefix" in {
    val node = new Xml("<prefix:a></prefix:a>").parse.right.get

    node.name should equal("a")
    node.prefix should be(Some("prefix"))
    node.children should be(empty)
  }

  it should "parse closed element with prefix" in {
    val node = new Xml("<prefix:a/>").parse.right.get

    node.name should equal("a")
    node.prefix should be(Some("prefix"))
    node.children should be(empty)
  }

  it should "parse children" in {
    val node = new Xml("<root> <child></child> </root>").parse.right.get

    node.name should equal("root")
    node.children should contain(Node(name = "child", children = Seq(), attrs = Seq()))
  }

  it should "parse self-closing children" in {
    val node = new Xml("<root><child/><child/></root>").parse.right.get

    node.name should equal("root")
    node.children.head equals Node(name = "child", children = Seq(), attrs = Seq())
    node.children(1) equals Node(name = "child", children = Seq(), attrs = Seq())
  }

  it should "parse attributes" in {
    val node = new Xml(s"""<element attr1 attr2="value" attr3 = "anotherValue" attr4='singleQuoted'></element>""").parse.right.get

    node.name should equal("element")
    node.attrs should equal(Seq(
      Attribute(name = "attr1", value = None),
      Attribute(name = "attr2", value = Some("value")),
      Attribute(name = "attr3", value = Some("anotherValue")),
      Attribute(name = "attr4", value = Some("singleQuoted"))))
  }

  it should "parse text" in {
    val node = new Xml(s"""<text>some text</text>""").parse.right.get

    node.name should equal("text")
    node.children should contain(Text("some text"))
  }

  it should "parse multiple text elements" in {
    val node = new Xml(s"""<text>some text <i>italic</i> another text</text>""").parse.right.get

    node.name should equal("text")
    node.children should contain(Text("some text "))
    node.children should contain(Text(" another text"))
  }

  it should "parse CDATA" in {
    val node = new Xml(s"""<text><![CDATA[some text]]></text>""").parse.right.get

    node.name should equal("text")
    node.children.head should be(CharacterData("some text"))
  }

  it should "parse multiline CDATA" in {
    val node = new Xml(
      s"""<text><![CDATA[
         |some more
         |text]]></text>""".stripMargin).parse.right.get

    node.name should equal("text")
    node.children.head should be(CharacterData("\nsome more\ntext"))
  }

  it should "throw an error on tag mismatch" in {
    new Xml(s"""<foo></bar>""").parse.left.get should be(a[XmlParserError])
  }

  it should "parse a Wikipedia XML" in {
    val node = new Xml(XmlExamples.wikipediaXml).parse
    node.isRight should be(true)
  }

  it should "parse a GraphML XML" in {
    val node = new Xml(XmlExamples.graphMl).parse
    node.isRight should be(true)
  }

  it should "parse simple BPMN 2.0 XML" in {
    val node = new Xml(XmlExamples.bpmnProcess).parse.right.get

    node.name should equal("definitions")
    node.children should have size 4
    node.children.head.asInstanceOf[Node].children.find({
      case n: Node => n.attrs.exists(_.value.contains("Task_1bv0mxh"))
      case _ => false
    }) should be(defined)
  }
}
