package xmls

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class JsXmlSpec extends AnyFlatSpec with Matchers {
  "XML" should "parse in a JS environment" in {
    XMLS.parse("<root/>") should be(Right(<root/>))
  }
}
