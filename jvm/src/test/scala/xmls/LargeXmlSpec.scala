package xmls

import java.io.InputStream

import org.scalatest.{ FlatSpec, Matchers }

import scala.io.Source.fromInputStream

class LargeXmlSpec extends FlatSpec with Matchers with BenchmarkSupport {
  it should "parse large BPMN 2.0 XML" in {
    val node = logMinTime("large bpmn xml", 50, println)(() => new XmlParser(fromInputStream(classpathFile("large-bpmn.xml")).getLines().mkString("\n")).parse)

    node.get.isRight should be(true)
  }

  def classpathFile(path: String): InputStream = getClass.getClassLoader.getResourceAsStream("large-bpmn.xml")
}
