package xmls

import org.scalatest.{ FlatSpec, Matchers }

class JsXmlSpec extends FlatSpec with Matchers {
  "XML" should "parse in a JS environment" in {
    XMLS.parse("<root/>") should be(Right(<root/>))
  }
}
