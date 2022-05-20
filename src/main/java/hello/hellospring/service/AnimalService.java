package hello.hellospring.service;

import hello.hellospring.domain.Animal;
import hello.hellospring.domain.AnimalCount;
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
                System.out.println("전체 리스트 :: " + eElement.getElementsByTagName("kindCd").item(0).getTextContent());
                animal.setDesertionNo(Double.parseDouble(eElement.getElementsByTagName("desertionNo").item(0).getTextContent()));
                animal.setKindCd(eElement.getElementsByTagName("kindCd").item(0).getTextContent());
                animal.setAge(eElement.getElementsByTagName("age").item(0).getTextContent());
                animal.setSpecialMark(eElement.getElementsByTagName("specialMark").item(0).getTextContent());
                String processState = eElement.getElementsByTagName("processState").item(0).getTextContent();
                String[] arr = processState.split("\\(");
                animal.setProcessState(arr[0]);

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

    public static List<Animal> recommendAnimals(NodeList nList, String specialMark) {
        List<Animal> result = new ArrayList<>();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                // 동물하나
                Animal animal = new Animal();
                Element eElement = (Element) nNode;
                System.out.println("전체 리스트 :: " + eElement.getElementsByTagName("kindCd").item(0).getTextContent());
                if(eElement.getElementsByTagName("specialMark").item(0).getTextContent().indexOf(specialMark)==-1){
                    continue;
                }
                animal.setDesertionNo(Double.parseDouble(eElement.getElementsByTagName("desertionNo").item(0).getTextContent()));
                animal.setKindCd(eElement.getElementsByTagName("kindCd").item(0).getTextContent());
                animal.setAge(eElement.getElementsByTagName("age").item(0).getTextContent());
                animal.setSpecialMark(eElement.getElementsByTagName("specialMark").item(0).getTextContent());
                String processState = eElement.getElementsByTagName("processState").item(0).getTextContent();
                String[] arr = processState.split("\\(");
                animal.setProcessState(arr[0]);

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

    public static List<AnimalCount> countAnimals(NodeList nList) {

        int[] count = new int[10];
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                // 동물하나
//                AnimalCount animal = new AnimalCount();
                Element eElement = (Element) nNode;
                System.out.println("전체 리스트 :: " + eElement.getElementsByTagName("kindCd").item(0).getTextContent());
                String careAddr = eElement.getElementsByTagName("careAddr").item(0).getTextContent();
                String[] arr = careAddr.split(" ");
                switch (arr[0]) {
                    case "강원도":
                        count[0] += 1;
                        break;
                    case "경기도":
                        count[1] += 1;
                        break;
                    case "경상북도":
                    case "대구광역시":
                        count[2] += 1;
                        break;
                    case "경상남도":
                    case "부산광역시":
                    case "울산광역시":
                        count[3] += 1;
                        break;
                    case "광주광역시":
                    case "전라남도":
                        count[4] += 1;
                        break;
                    case "전라북도":
                        count[5] += 1;
                        break;
                    case "대전광역시":
                    case "충청남도":
                    case "세종특별자치시":
                        count[6] += 1;
                        break;
                    case "충청북도":
                        count[7] += 1;
                        break;
                    case "서울특별시":
                    case "인천광역시":
                        count[8] += 1;
                        break;
                    case "제주특별자치도":
                        count[9] += 1;
                        break;
                }
            }
        }
        String[] place = new String[]{"강원도", "경기도", "경상남도", "경상북도", "서울&인천",
                "전라남도", "전라북도", "제주특별자치도", "충청남도", "충청북도"};
        List<AnimalCount> counts = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            AnimalCount animal = new AnimalCount();
            animal.setId(place[i]);
            animal.setValue(count[i]);
            counts.add(animal);
        }
        return counts;
    }
}
