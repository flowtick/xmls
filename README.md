xmls
====

[![Scala.js](https://img.shields.io/badge/scala.js-0.6.20%2B-blue.svg)](https://www.scala-js.org)

A simple xml parser for Scala(.js) based on parboiled2.

Why?
===

While [scala-xml](https://github.com/scala/scala-xml) supports Scala.js now, the loading of XML is still relying
on the JVM parser implementations. xmls aims to provide a _good enough_ XML parser for usage in cross-platform
builds (web-only applications could just use the XML parsing of the browser).

See the spec for supported XML elements.

Non-Goals
=========

* A XML complete parser
* XSD validation

Build
=====

    sbt package

Use
===

```scala
import xmls.Xml

val node = new Xml("<root><child/><child/></root>").parse
```

see [dist/example.html](dist/example.html) for usage in the browser.

License
=======

Apache License Version 2.0, see [LICENSE](LICENSE)