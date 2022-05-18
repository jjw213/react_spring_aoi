package hello.hellospring.controller;


import hello.hellospring.domain.Animal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AnimalListController  {
    @PostMapping("/animal/animalList")
    @ResponseBody
    public List<Animal> alist(AnimalForm form) {
        System.out.println(form.getNumOfRows());
        List<Animal> result = new ArrayList<>();
        String file ="";
        if(form.getKindcd().equals("개")) {
            file = new String("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?bgnde=20200301&endde=20220430&upkind=417000&pageNo=1&numOfRows="
                    + form.getNumOfRows() + "&ServiceKey=d7DXF5UusAcJ7jFQYs3HTZ4c%2FrU7kRtgZOq6EIVTNyL5VJ%2B6Lu9Wp0ge6uWOxn2XbPuKuB42fiGPe4U1bfmWtA%3D%3D");
        }
        else if(form.getKindcd().equals("고양이")){
            file = new String("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?bgnde=20200301&endde=20220430&upkind=422400&pageNo=1&numOfRows="
                    + form.getNumOfRows() + "&ServiceKey=d7DXF5UusAcJ7jFQYs3HTZ4c%2FrU7kRtgZOq6EIVTNyL5VJ%2B6Lu9Wp0ge6uWOxn2XbPuKuB42fiGPe4U1bfmWtA%3D%3D");
        }
        else{
            file = new String("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?bgnde=20200301&endde=20220430&upkind=429900&pageNo=1&numOfRows="
                    + form.getNumOfRows() + "&ServiceKey=d7DXF5UusAcJ7jFQYs3HTZ4c%2FrU7kRtgZOq6EIVTNyL5VJ%2B6Lu9Wp0ge6uWOxn2XbPuKuB42fiGPe4U1bfmWtA%3D%3D");
        }
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
        Document document = null;
        try {
            document = db.parse(file);
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        document.getDocumentElement().normalize();
        System.out.println("Root Element :" + document.getDocumentElement().getNodeName());
        NodeList nList = document.getElementsByTagName("item");
        System.out.println("----------------------------");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                // 동물하나
                Animal animal = new Animal();
                Element eElement = (Element) nNode;
                animal.setDesertionNo(Double.parseDouble(eElement.getElementsByTagName("desertionNo").item(0).getTextContent()));
                animal.setKindCd(eElement.getElementsByTagName("kindCd").item(0).getTextContent());
                animal.setAge(eElement.getElementsByTagName("age").item(0).getTextContent());
                animal.setSpecialMark(eElement.getElementsByTagName("specialMark").item(0).getTextContent());
                animal.setProcessState(eElement.getElementsByTagName("processState").item(0).getTextContent());
                animal.setCareAddr(eElement.getElementsByTagName("careAddr").item(0).getTextContent());
                animal.setCareNm(eElement.getElementsByTagName("careNm").item(0).getTextContent());
                animal.setWeight(eElement.getElementsByTagName("weight").item(0).getTextContent());
                animal.setFilename(eElement.getElementsByTagName("filename").item(0).getTextContent());
                animal.setSexCd(eElement.getElementsByTagName("sexCd").item(0).getTextContent());
                animal.setCareTel(eElement.getElementsByTagName("careTel").item(0).getTextContent());
                animal.setPopfile(eElement.getElementsByTagName("popfile").item(0).getTextContent());
//                System.out.println("protected Num : " + eElement.getElementsByTagName("desertionNo").item(0).getTextContent());
//                System.out.println("variety : " + eElement.getElementsByTagName("kindCd").item(0).getTextContent());
//                System.out.println("care Address : " + eElement.getElementsByTagName("careAddr").item(0).getTextContent());
//                System.out.println("age : " + eElement.getElementsByTagName("age").item(0).getTextContent());
                result.add(animal);
            }
        }
        return result;
    }
}
