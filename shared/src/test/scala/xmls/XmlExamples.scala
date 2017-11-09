package xmls

object XmlExamples {
  val wikipediaXml =
    s"""
       |<?xml version="1.0" encoding="UTF-8"?>
       |<article>
       |  <meta>
       |    <title interwiki="en" article="Example"/>
       |    <interwiki language="de" article="Beispiel"/>
       |  </meta>
       |  <text>
       |    Hello <link article="World"/>!
       |    <pbr/>
       |    How <i>are <b>you</b></i>?
       |    Just <u>tell <link article="Example" talk="talk">me</link></u>!
       |  </text>
       |</article>
     """.stripMargin

  val bpmnProcess =
    s"""
       |<?xml version="1.0" encoding="UTF-8"?>
       |<bpmn2:definitions xmlns:bpmn2="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:di="http://www.omg.org/spec/DD/20100524/DI" xmlns:dc="http://www.omg.org/spec/DD/20100524/DC" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" id="sample-diagram" targetNamespace="http://bpmn.io/schema/bpmn" xsi:schemaLocation="http://www.omg.org/spec/BPMN/20100524/MODEL BPMN20.xsd">
       |  <bpmn2:process id="Process_1" isExecutable="false">
       |    <bpmn2:endEvent id="EndEvent_0lnqdy1" name="End">
       |      <bpmn2:incoming>SequenceFlow_0p84skl</bpmn2:incoming>
       |    </bpmn2:endEvent>
       |    <bpmn2:exclusiveGateway id="ExclusiveGateway_16x7k3y" name="User exists?">
       |      <bpmn2:incoming>SequenceFlow_0eldbbo</bpmn2:incoming>
       |      <bpmn2:outgoing>SequenceFlow_0p84skl</bpmn2:outgoing>
       |      <bpmn2:outgoing>SequenceFlow_0gibxwp</bpmn2:outgoing>
       |    </bpmn2:exclusiveGateway>
       |    <bpmn2:sequenceFlow id="SequenceFlow_0p84skl" name="no" sourceRef="ExclusiveGateway_16x7k3y" targetRef="EndEvent_0lnqdy1" />
       |    <bpmn2:endEvent id="EndEvent_1l0dmcg" name="Password reset">
       |      <bpmn2:incoming>SequenceFlow_0dnltiy</bpmn2:incoming>
       |    </bpmn2:endEvent>
       |    <bpmn2:task id="Task_1bv0mxh" name="Reset Password">
       |      <bpmn2:incoming><![CDATA[SequenceFlow_12t6xte]]></bpmn2:incoming>
       |      <bpmn2:outgoing>SequenceFlow_0rf27s5</bpmn2:outgoing>
       |    </bpmn2:task>
       |    <bpmn2:task id="Task_1w1lnv4" name="Send Mail with new password">
       |      <bpmn2:incoming>SequenceFlow_0rf27s5</bpmn2:incoming>
       |      <bpmn2:outgoing>SequenceFlow_0dnltiy</bpmn2:outgoing>
       |    </bpmn2:task>
       |    <bpmn2:sequenceFlow id="SequenceFlow_0dnltiy" sourceRef="Task_1w1lnv4" targetRef="EndEvent_1l0dmcg" />
       |    <bpmn2:sequenceFlow id="SequenceFlow_0rf27s5" sourceRef="Task_1bv0mxh" targetRef="Task_1w1lnv4" />
       |    <bpmn2:intermediateCatchEvent id="IntermediateCatchEvent_0xa14d8" name="Confirmation Received">
       |      <bpmn2:incoming>SequenceFlow_08ux6q2</bpmn2:incoming>
       |      <bpmn2:outgoing>SequenceFlow_12t6xte</bpmn2:outgoing>
       |      <bpmn2:conditionalEventDefinition />
       |    </bpmn2:intermediateCatchEvent>
       |    <bpmn2:sequenceFlow id="SequenceFlow_12t6xte" sourceRef="IntermediateCatchEvent_0xa14d8" targetRef="Task_1bv0mxh" />
       |    <bpmn2:task id="Task_0n7z9ej" name="Send Mail with confirmation Link">
       |      <bpmn2:incoming>SequenceFlow_0gibxwp</bpmn2:incoming>
       |      <bpmn2:outgoing>SequenceFlow_08ux6q2</bpmn2:outgoing>
       |    </bpmn2:task>
       |    <bpmn2:sequenceFlow id="SequenceFlow_08ux6q2" sourceRef="Task_0n7z9ej" targetRef="IntermediateCatchEvent_0xa14d8" />
       |    <bpmn2:sequenceFlow id="SequenceFlow_0gibxwp" name="yes" sourceRef="ExclusiveGateway_16x7k3y" targetRef="Task_0n7z9ej" />
       |    <bpmn2:startEvent id="StartEvent_01rgcmh" name="Password Request Received">
       |      <bpmn2:outgoing>SequenceFlow_0eldbbo</bpmn2:outgoing>
       |      <bpmn2:messageEventDefinition />
       |    </bpmn2:startEvent>
       |    <bpmn2:sequenceFlow id="SequenceFlow_0eldbbo" sourceRef="StartEvent_01rgcmh" targetRef="ExclusiveGateway_16x7k3y" />
       |  </bpmn2:process>
       |  <bpmndi:BPMNDiagram id="BPMNDiagram_1">
       |    <bpmndi:BPMNPlane id="BPMNPlane_1" bpmnElement="Process_1">
       |      <bpmndi:BPMNShape id="EndEvent_0lnqdy1_di" bpmnElement="EndEvent_0lnqdy1">
       |        <dc:Bounds x="538" y="355" width="36" height="36" />
       |        <bpmndi:BPMNLabel>
       |          <dc:Bounds x="511" y="391" width="90" height="20" />
       |        </bpmndi:BPMNLabel>
       |      </bpmndi:BPMNShape>
       |      <bpmndi:BPMNShape id="ExclusiveGateway_16x7k3y_di" bpmnElement="ExclusiveGateway_16x7k3y" isMarkerVisible="true">
       |        <dc:Bounds x="383" y="233" width="50" height="50" />
       |        <bpmndi:BPMNLabel>
       |          <dc:Bounds x="407" y="274" width="90" height="20" />
       |        </bpmndi:BPMNLabel>
       |      </bpmndi:BPMNShape>
       |      <bpmndi:BPMNEdge id="SequenceFlow_0p84skl_di" bpmnElement="SequenceFlow_0p84skl">
       |        <di:waypoint xsi:type="dc:Point" x="408" y="283" />
       |        <di:waypoint xsi:type="dc:Point" x="408" y="373" />
       |        <di:waypoint xsi:type="dc:Point" x="538" y="373" />
       |        <bpmndi:BPMNLabel>
       |          <dc:Bounds x="427" y="347" width="90" height="20" />
       |        </bpmndi:BPMNLabel>
       |      </bpmndi:BPMNEdge>
       |      <bpmndi:BPMNShape id="EndEvent_1l0dmcg_di" bpmnElement="EndEvent_1l0dmcg">
       |        <dc:Bounds x="1038" y="240" width="36" height="36" />
       |        <bpmndi:BPMNLabel>
       |          <dc:Bounds x="1011" y="276" width="90" height="20" />
       |        </bpmndi:BPMNLabel>
       |      </bpmndi:BPMNShape>
       |      <bpmndi:BPMNShape id="Task_1bv0mxh_di" bpmnElement="Task_1bv0mxh">
       |        <dc:Bounds x="738" y="218" width="100" height="80" />
       |      </bpmndi:BPMNShape>
       |      <bpmndi:BPMNShape id="Task_1w1lnv4_di" bpmnElement="Task_1w1lnv4">
       |        <dc:Bounds x="891" y="218" width="100" height="80" />
       |      </bpmndi:BPMNShape>
       |      <bpmndi:BPMNEdge id="SequenceFlow_0dnltiy_di" bpmnElement="SequenceFlow_0dnltiy">
       |        <di:waypoint xsi:type="dc:Point" x="991" y="258" />
       |        <di:waypoint xsi:type="dc:Point" x="1038" y="258" />
       |        <bpmndi:BPMNLabel>
       |          <dc:Bounds x="966.5" y="248" width="90" height="20" />
       |        </bpmndi:BPMNLabel>
       |      </bpmndi:BPMNEdge>
       |      <bpmndi:BPMNEdge id="SequenceFlow_0rf27s5_di" bpmnElement="SequenceFlow_0rf27s5">
       |        <di:waypoint xsi:type="dc:Point" x="838" y="258" />
       |        <di:waypoint xsi:type="dc:Point" x="891" y="258" />
       |        <bpmndi:BPMNLabel>
       |          <dc:Bounds x="819.5" y="248" width="90" height="20" />
       |        </bpmndi:BPMNLabel>
       |      </bpmndi:BPMNEdge>
       |      <bpmndi:BPMNShape id="IntermediateCatchEvent_0xa14d8_di" bpmnElement="IntermediateCatchEvent_0xa14d8">
       |        <dc:Bounds x="661" y="240" width="36" height="36" />
       |        <bpmndi:BPMNLabel>
       |          <dc:Bounds x="634" y="276" width="90" height="20" />
       |        </bpmndi:BPMNLabel>
       |      </bpmndi:BPMNShape>
       |      <bpmndi:BPMNEdge id="SequenceFlow_12t6xte_di" bpmnElement="SequenceFlow_12t6xte">
       |        <di:waypoint xsi:type="dc:Point" x="697" y="258" />
       |        <di:waypoint xsi:type="dc:Point" x="738" y="258" />
       |        <bpmndi:BPMNLabel>
       |          <dc:Bounds x="662" y="248" width="90" height="20" />
       |        </bpmndi:BPMNLabel>
       |      </bpmndi:BPMNEdge>
       |      <bpmndi:BPMNShape id="Task_0n7z9ej_di" bpmnElement="Task_0n7z9ej">
       |        <dc:Bounds x="506" y="218" width="100" height="80" />
       |      </bpmndi:BPMNShape>
       |      <bpmndi:BPMNEdge id="SequenceFlow_08ux6q2_di" bpmnElement="SequenceFlow_08ux6q2">
       |        <di:waypoint xsi:type="dc:Point" x="606" y="258" />
       |        <di:waypoint xsi:type="dc:Point" x="661" y="258" />
       |        <bpmndi:BPMNLabel>
       |          <dc:Bounds x="588.5" y="248" width="90" height="20" />
       |        </bpmndi:BPMNLabel>
       |      </bpmndi:BPMNEdge>
       |      <bpmndi:BPMNEdge id="SequenceFlow_0gibxwp_di" bpmnElement="SequenceFlow_0gibxwp">
       |        <di:waypoint xsi:type="dc:Point" x="433" y="258" />
       |        <di:waypoint xsi:type="dc:Point" x="506" y="258" />
       |        <bpmndi:BPMNLabel>
       |          <dc:Bounds x="426" y="232" width="90" height="20" />
       |        </bpmndi:BPMNLabel>
       |      </bpmndi:BPMNEdge>
       |      <bpmndi:BPMNShape id="StartEvent_01rgcmh_di" bpmnElement="StartEvent_01rgcmh">
       |        <dc:Bounds x="269" y="240" width="36" height="36" />
       |        <bpmndi:BPMNLabel>
       |          <dc:Bounds x="242" y="276" width="90" height="20" />
       |        </bpmndi:BPMNLabel>
       |      </bpmndi:BPMNShape>
       |      <bpmndi:BPMNEdge id="SequenceFlow_0eldbbo_di" bpmnElement="SequenceFlow_0eldbbo">
       |        <di:waypoint xsi:type="dc:Point" x="305" y="258" />
       |        <di:waypoint xsi:type="dc:Point" x="383" y="258" />
       |        <bpmndi:BPMNLabel>
       |          <dc:Bounds x="288.5" y="248" width="90" height="20" />
       |        </bpmndi:BPMNLabel>
       |      </bpmndi:BPMNEdge>
       |    </bpmndi:BPMNPlane>
       |  </bpmndi:BPMNDiagram>
       |</bpmn2:definitions>
     """.stripMargin

  val graphMl =
    s"""
       |<?xml version="1.0" encoding="UTF-8" standalone="no"?>
       |<graphml xmlns="http://graphml.graphdrawing.org/xmlns" xmlns:java="http://www.yworks.com/xml/yfiles-common/1.0/java" xmlns:sys="http://www.yworks.com/xml/yfiles-common/markup/primitives/2.0" xmlns:x="http://www.yworks.com/xml/yfiles-common/markup/2.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:y="http://www.yworks.com/xml/graphml" xmlns:yed="http://www.yworks.com/xml/yed/3" xsi:schemaLocation="http://graphml.graphdrawing.org/xmlns http://www.yworks.com/xml/schema/graphml/1.1/ygraphml.xsd">
       |  <!--Created by yEd 3.17-->
       |  <key for="port" id="d0" yfiles.type="portgraphics"/>
       |  <key for="port" id="d1" yfiles.type="portgeometry"/>
       |  <key for="port" id="d2" yfiles.type="portuserdata"/>
       |  <key attr.name="url" attr.type="string" for="node" id="d3"/>
       |  <key attr.name="description" attr.type="string" for="node" id="d4"/>
       |  <key for="node" id="d5" yfiles.type="nodegraphics"/>
       |  <key for="graphml" id="d6" yfiles.type="resources"/>
       |  <key attr.name="url" attr.type="string" for="edge" id="d7"/>
       |  <key attr.name="description" attr.type="string" for="edge" id="d8"/>
       |  <key for="edge" id="d9" yfiles.type="edgegraphics"/>
       |  <graph edgedefault="directed" id="G">
       |    <node id="n0">
       |      <data key="d5">
       |        <y:ShapeNode>
       |          <y:Geometry height="30.0" width="30.0" x="33.27738095238095" y="142.94900512695312"/>
       |          <y:Fill color="#FFFFFF" transparent="false"/>
       |          <y:BorderStyle color="#000000" raised="false" type="line" width="1.0"/>
       |          <y:NodeLabel alignment="center" autoSizePolicy="content" fontFamily="Dialog" fontSize="12" fontStyle="plain" hasBackgroundColor="false" hasLineColor="false" height="17.96875" horizontalTextPosition="center" iconTextGap="4" modelName="custom" textColor="#000000" verticalTextPosition="bottom" visible="true" width="12.232421875" x="8.8837890625" y="6.015625">B<y:LabelModel>
       |              <y:SmartNodeLabelModel distance="4.0"/>
       |            </y:LabelModel>
       |            <y:ModelParameter>
       |              <y:SmartNodeLabelModelParameter labelRatioX="0.0" labelRatioY="0.0" nodeRatioX="0.0" nodeRatioY="0.0" offsetX="0.0" offsetY="0.0" upX="0.0" upY="-1.0"/>
       |            </y:ModelParameter>
       |          </y:NodeLabel>
       |          <y:Shape type="rectangle"/>
       |        </y:ShapeNode>
       |      </data>
       |    </node>
       |    <node id="n1">
       |      <data key="d5">
       |        <y:ShapeNode>
       |          <y:Geometry height="30.0" width="30.0" x="33.27738095238095" y="92.94900512695312"/>
       |          <y:Fill color="#FFFFFF" transparent="false"/>
       |          <y:BorderStyle color="#000000" raised="false" type="line" width="1.0"/>
       |          <y:NodeLabel alignment="center" autoSizePolicy="content" fontFamily="Dialog" fontSize="12" fontStyle="plain" hasBackgroundColor="false" hasLineColor="false" height="17.96875" horizontalTextPosition="center" iconTextGap="4" modelName="custom" textColor="#000000" verticalTextPosition="bottom" visible="true" width="12.208984375" x="8.8955078125" y="6.015625">A<y:LabelModel>
       |              <y:SmartNodeLabelModel distance="4.0"/>
       |            </y:LabelModel>
       |            <y:ModelParameter>
       |              <y:SmartNodeLabelModelParameter labelRatioX="0.0" labelRatioY="0.0" nodeRatioX="0.0" nodeRatioY="0.0" offsetX="0.0" offsetY="0.0" upX="0.0" upY="-1.0"/>
       |            </y:ModelParameter>
       |          </y:NodeLabel>
       |          <y:Shape type="rectangle"/>
       |        </y:ShapeNode>
       |      </data>
       |    </node>
       |    <node id="n2">
       |      <data key="d4"/>
       |      <data key="d5">
       |        <y:SVGNode>
       |          <y:Geometry height="69.94900512695312" width="56.554100036621094" x="20.000330934070405" y="0.0"/>
       |          <y:Fill color="#CCCCFF" transparent="false"/>
       |          <y:BorderStyle color="#000000" type="line" width="1.0"/>
       |          <y:NodeLabel alignment="center" autoSizePolicy="content" fontFamily="Dialog" fontSize="12" fontStyle="plain" hasBackgroundColor="false" hasLineColor="false" hasText="false" height="4.0" horizontalTextPosition="center" iconTextGap="4" modelName="custom" textColor="#000000" verticalTextPosition="bottom" visible="true" width="4.0" x="26.277050018310547" y="73.94900512695312">
       |            <y:LabelModel>
       |              <y:SmartNodeLabelModel distance="4.0"/>
       |            </y:LabelModel>
       |            <y:ModelParameter>
       |              <y:SmartNodeLabelModelParameter labelRatioX="0.0" labelRatioY="-0.5" nodeRatioX="0.0" nodeRatioY="0.5" offsetX="0.0" offsetY="4.0" upX="0.0" upY="-1.0"/>
       |            </y:ModelParameter>
       |          </y:NodeLabel>
       |          <y:SVGNodeProperties usingVisualBounds="true"/>
       |          <y:SVGModel svgBoundsPolicy="0">
       |            <y:SVGContent refid="1"/>
       |          </y:SVGModel>
       |        </y:SVGNode>
       |      </data>
       |    </node>
       |    <edge id="e0" source="n1" target="n0">
       |      <data key="d9">
       |        <y:PolyLineEdge>
       |          <y:Path sx="0.0" sy="15.0" tx="0.0" ty="-15.0"/>
       |          <y:LineStyle color="#000000" type="line" width="1.0"/>
       |          <y:Arrows source="none" target="standard"/>
       |          <y:BendStyle smoothed="false"/>
       |        </y:PolyLineEdge>
       |      </data>
       |    </edge>
       |    <edge id="e1" source="n2" target="n1">
       |      <data key="d8"/>
       |      <data key="d9">
       |        <y:PolyLineEdge>
       |          <y:Path sx="0.0" sy="34.97450256347656" tx="0.0" ty="-15.0"/>
       |          <y:LineStyle color="#000000" type="line" width="1.0"/>
       |          <y:Arrows source="none" target="standard"/>
       |          <y:BendStyle smoothed="false"/>
       |        </y:PolyLineEdge>
       |      </data>
       |    </edge>
       |  </graph>
       |  <data key="d6">
       |    <y:Resources>
       |      <y:Resource id="1">&lt;?xml version="1.0" encoding="utf-8"?&gt;
       |&lt;svg version="1.1" id="Ebene_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
       |	 width="57px" height="65px" viewBox="0 0 57 65" enable-background="new 0 0 57 65" xml:space="preserve"&gt;
       |&lt;g&gt;
       |
       |		&lt;linearGradient id="SVGID_1_" gradientUnits="userSpaceOnUse" x1="26.5396" y1="-732.5005" x2="27.7805" y2="-762.2984" gradientTransform="matrix(1 0 0 -1 0.1201 -708.5371)"&gt;
       |		&lt;stop  offset="0.2711" style="stop-color:#FFAB4F"/&gt;
       |		&lt;stop  offset="1" style="stop-color:#FFD28F"/&gt;
       |	&lt;/linearGradient&gt;
       |	&lt;path fill="url(#SVGID_1_)" stroke="#ED9135" stroke-miterlimit="10" d="M49.529,51.225c-4.396-4.396-10.951-5.884-12.063-6.109
       |		V37.8H19.278c0,0,0.038,6.903,0,6.868c0,0-6.874,0.997-12.308,6.432C1.378,56.691,0.5,62.77,0.5,62.77
       |		c0,1.938,1.575,3.492,3.523,3.492h48.51c1.947,0,3.521-1.558,3.521-3.492C56.055,62.768,54.211,55.906,49.529,51.225z"/&gt;
       |
       |		&lt;radialGradient id="face_x5F_white_1_" cx="27.7827" cy="-734.2632" r="23.424" fx="23.2131" fy="-736.753" gradientTransform="matrix(1 0 0 -1 0.1201 -708.5371)" gradientUnits="userSpaceOnUse"&gt;
       |		&lt;stop  offset="0" style="stop-color:#FFD28F"/&gt;
       |		&lt;stop  offset="1" style="stop-color:#FFAB4F"/&gt;
       |	&lt;/radialGradient&gt;
       |	&lt;path id="face_x5F_white_3_" fill="url(#face_x5F_white_1_)" stroke="#ED9135" stroke-miterlimit="10" d="M43.676,23.357
       |		c0.086,10.2-6.738,18.52-15.246,18.586c-8.503,0.068-15.467-8.146-15.553-18.344C12.794,13.4,19.618,5.079,28.123,5.012
       |		C36.627,4.945,43.59,13.158,43.676,23.357z"/&gt;
       |
       |		&lt;linearGradient id="face_highlight_1_" gradientUnits="userSpaceOnUse" x1="2941.4297" y1="5677.457" x2="2965.0596" y2="5770.9087" gradientTransform="matrix(0.275 0 0 0.2733 -783.3976 -1543.4047)"&gt;
       |		&lt;stop  offset="0" style="stop-color:#FFFFFF;stop-opacity:0.42"/&gt;
       |		&lt;stop  offset="1" style="stop-color:#FFFFFF;stop-opacity:0.2067"/&gt;
       |	&lt;/linearGradient&gt;
       |	&lt;path id="face_highlight_3_" fill="url(#face_highlight_1_)" d="M27.958,6.333c-6.035,0.047-10.747,4.493-12.787,10.386
       |		c-0.664,1.919-0.294,4.043,0.98,5.629c2.73,3.398,5.729,6.283,9.461,8.088c3.137,1.518,7.535,2.385,11.893,1.247
       |		c2.274-0.592,3.988-2.459,4.375-4.766c0.183-1.094,0.293-2.289,0.283-3.553C42.083,13.952,36.271,6.268,27.958,6.333z"/&gt;
       |	&lt;path fill="#CC9869" stroke="#99724F" stroke-linecap="round" stroke-linejoin="round" d="M32.215,9.938
       |		c0,0,5.688,2.75,7.688,8.125c2.104,5.652,4.123,8.232,4.188,8c1.875-6.794,1.063-21.438-10.17-21.587
       |		c-20.455-7.663-25.58,11.962-23.893,19.65c1.078,4.911,2.234,6.686,3.938,8.08C13.966,32.205,15.028,17.563,32.215,9.938z"/&gt;
       |
       |		&lt;radialGradient id="collar_x5F_body_2_" cx="15.1587" cy="-765.7056" r="32.4004" gradientTransform="matrix(1 0 0 -1 0.1201 -708.5371)" gradientUnits="userSpaceOnUse"&gt;
       |		&lt;stop  offset="0" style="stop-color:#B0E8FF"/&gt;
       |		&lt;stop  offset="1" style="stop-color:#74AEEE"/&gt;
       |	&lt;/radialGradient&gt;
       |	&lt;path id="collar_x5F_body_1_" fill="url(#collar_x5F_body_2_)" stroke="#5491CF" d="M0.5,62.768c0,1.938,1.575,3.494,3.523,3.494
       |		h48.51c1.947,0,3.521-1.559,3.521-3.494c0,0-1.844-6.861-6.525-11.543c-4.815-4.813-11.244-6.146-11.244-6.146
       |		c-1.771,1.655-5.61,2.802-10.063,2.802c-4.453,0-8.292-1.146-10.063-2.802c0,0-5.755,0.586-11.189,6.021
       |		C1.378,56.689,0.5,62.768,0.5,62.768z"/&gt;
       |
       |		&lt;radialGradient id="collar_x5F_r_2_" cx="31.5" cy="-755.832" r="9.2834" gradientTransform="matrix(1 0 0 -1 0.1201 -708.5371)" gradientUnits="userSpaceOnUse"&gt;
       |		&lt;stop  offset="0" style="stop-color:#80CCFF"/&gt;
       |		&lt;stop  offset="1" style="stop-color:#74AEEE"/&gt;
       |	&lt;/radialGradient&gt;
       |	&lt;path id="collar_x5F_r_1_" fill="url(#collar_x5F_r_2_)" stroke="#5491CF" d="M38.159,41.381c0,0-0.574,2.369-3.013,4.441
       |		c-2.108,1.795-5.783,2.072-5.783,2.072l3.974,6.217c0,0,2.957-1.637,5.009-3.848c1.922-2.072,1.37-5.479,1.37-5.479L38.159,41.381z
       |		"/&gt;
       |
       |		&lt;radialGradient id="collar_x5F_l_2_" cx="19.1377" cy="-755.873" r="9.2837" gradientTransform="matrix(1 0 0 -1 0.1201 -708.5371)" gradientUnits="userSpaceOnUse"&gt;
       |		&lt;stop  offset="0" style="stop-color:#80CCFF"/&gt;
       |		&lt;stop  offset="1" style="stop-color:#74AEEE"/&gt;
       |	&lt;/radialGradient&gt;
       |	&lt;path id="collar_x5F_l_1_" fill="url(#collar_x5F_l_2_)" stroke="#5491CF" d="M18.63,41.422c0,0,0.576,2.369,3.012,4.441
       |		c2.109,1.793,5.785,2.072,5.785,2.072l-3.974,6.217c0,0-2.957-1.637-5.007-3.85c-1.922-2.072-1.37-5.48-1.37-5.48L18.63,41.422z"/&gt;
       |
       |		&lt;radialGradient id="Knob2_2_" cx="27.8872" cy="7.9414" r="0.9669" gradientTransform="matrix(1 0 0 -1 0.04 64.1543)" gradientUnits="userSpaceOnUse"&gt;
       |		&lt;stop  offset="0" style="stop-color:#80CCFF"/&gt;
       |		&lt;stop  offset="1" style="stop-color:#74AEEE"/&gt;
       |	&lt;/radialGradient&gt;
       |	&lt;circle id="Knob2_1_" fill="url(#Knob2_2_)" stroke="#5491CF" cx="28.258" cy="56.254" r="0.584"/&gt;
       |
       |		&lt;radialGradient id="Knob1_2_" cx="27.9253" cy="1.6973" r="0.9669" gradientTransform="matrix(1 0 0 -1 0.04 64.1543)" gradientUnits="userSpaceOnUse"&gt;
       |		&lt;stop  offset="0" style="stop-color:#80CCFF"/&gt;
       |		&lt;stop  offset="1" style="stop-color:#74AEEE"/&gt;
       |	&lt;/radialGradient&gt;
       |	&lt;circle id="Knob1_1_" fill="url(#Knob1_2_)" stroke="#5491CF" cx="28.296" cy="62.499" r="0.584"/&gt;
       |&lt;/g&gt;
       |&lt;/svg&gt;
       |</y:Resource>
       |    </y:Resources>
       |  </data>
       |</graphml>
     """.stripMargin
}
