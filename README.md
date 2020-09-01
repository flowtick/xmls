xmls
====

[![travis](https://travis-ci.org/flowtick/xmls.svg?branch=master)](https://travis-ci.org/flowtick/xmls)
[![latest release for 2.12](https://img.shields.io/maven-central/v/com.flowtick/xmls_2.12.svg?label=Scala+2.12)](http://mvnrepository.com/artifact/com.flowtick/xmls_2.12)

A simple xml parser for Scala(.js) based on [parboiled2](https://github.com/sirthias/parboiled2).

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
import xmls._

val parsed: Either[XmlParserError, scala.xml.Node] = XMLS.parse("<root><child/><child/></root>")
```

see [dist/example.html](dist/example.html) for usage in the browser.

License
=======

Apache License Version 2.0, see [LICENSE](LICENSE)
