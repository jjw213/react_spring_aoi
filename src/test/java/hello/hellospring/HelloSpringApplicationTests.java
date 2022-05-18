package hello.hellospring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@SpringBootTest
class HelloSpringApplicationTests {

	@Test
	void contextLoads() {
//		String file = new String("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?bgnde=20140301&endde=20220430&pageNo=1&numOfRows=10&ServiceKey=d7DXF5UusAcJ7jFQYs3HTZ4c%2FrU7kRtgZOq6EIVTNyL5VJ%2B6Lu9Wp0ge6uWOxn2XbPuKuB42fiGPe4U1bfmWtA%3D%3D");
//		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//		DocumentBuilder db = null;
//		try {
//			db = dbf.newDocumentBuilder();
//		} catch (ParserConfigurationException ex) {
//			ex.printStackTrace();
//		}
//		Document document = null;
//		try {
//			document = db.parse(file);
//		} catch (SAXException ex) {
//			ex.printStackTrace();
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//		document.getDocumentElement().normalize();
//		System.out.println("Root Element :" + document.getDocumentElement().getNodeName());
//		NodeList nList = document.getElementsByTagName("item");
//		System.out.println("----------------------------");
//		for (int temp = 0; temp < nList.getLength(); temp++) {
//			Node nNode = nList.item(temp);
//			System.out.println("\nCurrent Element :" + nNode.getNodeName());
//			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//				Element eElement = (Element) nNode;
//				System.out.println("protected Num : " + eElement.getElementsByTagName("desertionNo").item(0).getTextContent());
//				System.out.println("variety : " + eElement.getElementsByTagName("kindCd").item(0).getTextContent());
//				System.out.println("care Address : " + eElement.getElementsByTagName("careAddr").item(0).getTextContent());
//				System.out.println("age : " + eElement.getElementsByTagName("age").item(0).getTextContent());
//			}
//		}
		System.out.println("가나나나나");
	}

}
