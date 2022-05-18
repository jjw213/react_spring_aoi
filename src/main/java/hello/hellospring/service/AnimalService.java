package hello.hellospring.service;

import hello.hellospring.domain.Animal;
import hello.hellospring.domain.Member;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class AnimalService {

    public static List<Animal> allAnimals(NodeList nList) {
        List<Animal> result = new ArrayList<>();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                // 동물하나
                Animal animal = new Animal();
                Element eElement = (Element) nNode;
                System.out.println("전체 리스트 :: "+eElement.getElementsByTagName("kindCd").item(0).getTextContent());
                animal.setDesertionNo(Double.parseDouble(eElement.getElementsByTagName("desertionNo").item(0).getTextContent()));
                animal.setKindCd(eElement.getElementsByTagName("kindCd").item(0).getTextContent());
                animal.setAge(eElement.getElementsByTagName("age").item(0).getTextContent());
                animal.setSpecialMark(eElement.getElementsByTagName("specialMark").item(0).getTextContent());
                animal.setProcessState(eElement.getElementsByTagName("processState").item(0).getTextContent());
                animal.setCareAddr(eElement.getElementsByTagName("careAddr").item(0).getTextContent());
                animal.setCareNm(eElement.getElementsByTagName("careNm").item(0).getTextContent());
                animal.setWeight(eElement.getElementsByTagName("weight").item(0).getTextContent());
                animal.setPopfile(eElement.getElementsByTagName("popfile").item(0).getTextContent());
                animal.setFilename(eElement.getElementsByTagName("filename").item(0).getTextContent());
                animal.setSexCd(eElement.getElementsByTagName("sexCd").item(0).getTextContent());
                animal.setCareTel(eElement.getElementsByTagName("careTel").item(0).getTextContent());

//                System.out.println("protected Num : " + eElement.getElementsByTagName("desertionNo").item(0).getTextContent());
//                System.out.println("variety : " + eElement.getElementsByTagName("kindCd").item(0).getTextContent());
//                System.out.println("care Address : " + eElement.getElementsByTagName("careAddr").item(0).getTextContent());
//                System.out.println("age : " + eElement.getElementsByTagName("age").item(0).getTextContent());
                result.add(animal);
            }
        }
        return result;
    }
    public static List<Animal> countAnimals(NodeList nList) {
        List<Animal> result = new ArrayList<>();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                // 동물하나
                Animal animal = new Animal();
                Element eElement = (Element) nNode;
                System.out.println("전체 리스트 :: "+eElement.getElementsByTagName("kindCd").item(0).getTextContent());
                animal.setDesertionNo(Double.parseDouble(eElement.getElementsByTagName("desertionNo").item(0).getTextContent()));
                animal.setKindCd(eElement.getElementsByTagName("kindCd").item(0).getTextContent());
                animal.setAge(eElement.getElementsByTagName("age").item(0).getTextContent());
                animal.setSpecialMark(eElement.getElementsByTagName("specialMark").item(0).getTextContent());
                animal.setProcessState(eElement.getElementsByTagName("processState").item(0).getTextContent());
                String careAddr = eElement.getElementsByTagName("careAddr").item(0).getTextContent();
                String[] arr = careAddr.split(" ");
                animal.setCareAddr(arr[0]);

                animal.setCareNm(eElement.getElementsByTagName("careNm").item(0).getTextContent());
                animal.setWeight(eElement.getElementsByTagName("weight").item(0).getTextContent());
                animal.setPopfile(eElement.getElementsByTagName("popfile").item(0).getTextContent());
                animal.setFilename(eElement.getElementsByTagName("filename").item(0).getTextContent());
                animal.setSexCd(eElement.getElementsByTagName("sexCd").item(0).getTextContent());
                animal.setCareTel(eElement.getElementsByTagName("careTel").item(0).getTextContent());

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
