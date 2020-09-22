package com.example.junitfile;

import com.example.SAX.XMLContentHandler;
import com.example.vo.Person;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws FileNotFoundException {
        List<Person> persons = new ArrayList<Person>();
        FileInputStream file = new FileInputStream("src/test/java/com/example/xmltest/persons.xml");
        InputStream file1 = new FileInputStream("src/test/java/com/example/xmltest/test.xml");
        persons = readxmlbyDOM(file);
        for (Person temp : persons){
            System.out.println(temp);
        }
        System.out.println("----c语言平均成绩--------"+c_avg(persons));
        System.out.println("----sql语言平均成绩--------"+sql_avg(persons));
        System.out.println("----java语言平均成绩--------"+java_avg(persons));
        System.out.println("---------------------------------------------------------");
        System.out.println("----c语言最高成绩--------"+max_c(persons).getC_goal());
        System.out.println("----sql语言最高成绩--------"+max_sql(persons).getSql_goal());
        System.out.println("----java语言最高成绩--------"+max_java(persons).getJava_goal());

        Map<Integer,Integer> ss =new HashMap<Integer,Integer>();
        ss = max_class(persons);
        System.out.println("--------每个班的所有成绩之和----------------");
        System.out.println(ss);
        System.out.println("------- 各科的优秀率-----------------------");
        System.out.println("------c语言优秀率:"+ExcellenceRate_c(persons));
        System.out.println("------sql优秀率:"+ExcellenceRate_sql(persons));
        System.out.println("------java优秀率:"+ExcellenceRate_java(persons));
        Map<Integer,Float> ee =new HashMap<Integer,Float>();
        ee = ExcellenceRate_class(persons);
        System.out.println("---------各个班的优秀率------------------");
        System.out.println(ee);
    }

    public static float c_avg(List<Person> list){
        float sum=0;
        for(int i=0;i<list.size();++i){
            sum+=(float)list.get(i).getC_goal();
        }
        return sum/list.size();
    }
    public static float sql_avg(List<Person> list){
        float sum=0;
        for(int i=0;i<list.size();++i){
            sum+=(float)list.get(i).getSql_goal();
        }
        return sum/list.size();
    }
    public static float java_avg(List<Person> list){
        float sum=0;
        for(int i=0;i<list.size();++i){
            sum+=(float)list.get(i).getJava_goal();
        }
        return sum/list.size();
    }

    public static Person max_c(List<Person> list){
        int p=0,result=0;
        for(int i=0;i<list.size();++i) {
            if(list.get(i).getC_goal()>p){
                p = list.get(i).getC_goal();
                result=i;
            }
        }
        return list.get(result);
    }
    public static Person max_sql(List<Person> list){
        int p=0,result=0;
        for(int i=0;i<list.size();++i) {
            if(list.get(i).getSql_goal()>p){
                p = list.get(i).getSql_goal();
                result=i;
            }
        }
        return list.get(result);
    }
    public static Person max_java(List<Person> list){
        int p=0,result=0;
        for(int i=0;i<list.size();++i) {
            if(list.get(i).getJava_goal()>p){
                p = list.get(i).getJava_goal();
                result=i;
            }
        }
        return list.get(result);
    }
    public static Map<Integer,Integer> max_class(List<Person> list){
        int class1=0,class2=0,class3=0;
        for(int i=0;i<list.size();++i) {
            if(list.get(i).getId()==1){
                int temp = 0;
                temp+=list.get(i).getC_goal();
                temp+=list.get(i).getSql_goal();
                temp+=list.get(i).getJava_goal();
                class1+=temp;
            }else if(list.get(i).getId()==2){
                int temp = 0;
                temp+=list.get(i).getC_goal();
                temp+=list.get(i).getSql_goal();
                temp+=list.get(i).getJava_goal();
                class2+=temp;
            }else if(list.get(i).getId()==3){
                int temp = 0;
                temp+=list.get(i).getC_goal();
                temp+=list.get(i).getSql_goal();
                temp+=list.get(i).getJava_goal();
                class3+=temp;
            }
        }
        Map<Integer,Integer> result =new HashMap<Integer,Integer>();
        result.put(1,class1);
        result.put(2,class2);
        result.put(3,class3);
        return result;
    }
    public static float ExcellenceRate_c(List<Person> list){
        float sum=0;
        for(int i=0;i<list.size();++i){
            if(list.get(i).getC_goal()>90){
                sum++;
            }
        }
        return sum/list.size();
    }
    public static float ExcellenceRate_sql(List<Person> list){
        float sum=0;
        for(int i=0;i<list.size();++i){
            if(list.get(i).getSql_goal()>90){
                sum++;
            }
        }
        return sum/list.size();
    }
    public static float ExcellenceRate_java(List<Person> list){
        float sum=0;
        for(int i=0;i<list.size();++i){
            if(list.get(i).getJava_goal()>90){
                sum++;
            }
        }
        return sum/list.size();
    }
    public static Map<Integer,Float> ExcellenceRate_class(List<Person> list){
        int class1_Sum=0,class2_Sum=0,class3_Sum=0;
        float class1_Excell=0,class2_Excell=0,class3_Excell=0;
        for(int i=0;i<list.size();++i){
            int person_sum=0;
            person_sum+=list.get(i).getC_goal();
            person_sum+=list.get(i).getSql_goal();
            person_sum+=list.get(i).getJava_goal();
            if(list.get(i).getId()==1){
                if(person_sum>=270){
                    class1_Excell++;
                }
                class1_Sum++;
            }else if(list.get(i).getId()==2){
                if(person_sum>=270){
                    class2_Excell++;
                }
                class2_Sum++;
            }else if(list.get(i).getId()==3){
                if(person_sum>=270){
                    class3_Excell++;
                }
                class3_Sum++;
            }
        }
        Map<Integer,Float> result = new HashMap<Integer,Float>();
        result.put(1,class1_Excell/class1_Sum);
        result.put(2,class2_Excell/class2_Sum);
        result.put(3,class3_Excell/class3_Sum);
        return result;
    }


    /**【SAX解析XML文件】**/
    public static List<Person> readXmlBySAX(InputStream inputStream) {
        try {
            /**【创建解析器】**/
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser saxParser = spf.newSAXParser();
            XMLContentHandler handler = new XMLContentHandler();
            saxParser.parse(inputStream, handler);
            inputStream.close();
            return handler.getPersons();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Person> readxmlbyDOM(InputStream inputStream){
        List<Person> persons = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(inputStream);

            Element root = dom.getDocumentElement();
            /**【查找所有person节点】**/
            NodeList items = root.getElementsByTagName("student");
            for (int i = 0; i < items.getLength(); i++) {
                Person person = new Person();

                /**【得到第一个person的节点】**/
                Element personNode = (Element) items.item(i);

                /**【获取person节点的id和sid属性】**/
                person.setId(new Integer(personNode.getAttribute("id")));
                person.setSid(new Integer(personNode.getAttribute("sid")));

                /**【获取person节点下的所有子节点（标签之间的空白节点和name/age节点）】**/
                NodeList childsNodes = personNode.getChildNodes();

                /**【遍历所有子节点】**/
                for (int j = 0; j < childsNodes.getLength(); j++) {
                    Node node = (Node) childsNodes.item(j);

                    /**【判断是否为元素类型】**/
                    if(node.getNodeType() == Node.ELEMENT_NODE){
                        Element childNode = (Element) node;
                        /**【判断是否是name元素】**/
                        if ("name".equals(childNode.getNodeName())) {
                            /**【获取name元素下的text节点，然后从text节点获取数据】**/
                            person.setName(childNode.getFirstChild().getNodeValue());
                            /**【判断是否是age元素】**/
                        }else if("age".equals(childNode.getNodeName())){
                            /**【获取age元素下的text节点，然后从text节点获取数据】**/
                            person.setAge(new Short(childNode.getFirstChild().getNodeValue()));
                        }else if("project".equals(childNode.getNodeName())){
                            /**【获取age元素下的text节点，然后从text节点获取数据】**/
                            NodeList projectNodes = childNode.getChildNodes();
                            for (int z = 0; z < projectNodes.getLength(); z++) {
                                Node projectNode = (Node) projectNodes.item(z);
                                if(projectNode.getNodeType() == Node.ELEMENT_NODE){
                                    Element ProjectChildNode = (Element) projectNode;
                                    if ("c".equals(ProjectChildNode.getNodeName())) {
                                        person.setC_goal(new Short(ProjectChildNode.getFirstChild().getNodeValue()));
                                    }else if("sql".equals(ProjectChildNode.getNodeName())) {
                                        person.setSql_goal(new Short(ProjectChildNode.getFirstChild().getNodeValue()));
                                    }else if("java".equals(ProjectChildNode.getNodeName())) {
                                        person.setJava_goal(new Short(ProjectChildNode.getFirstChild().getNodeValue()));
                                    }
                                }
                            }
                        }
                    }
                }
                persons.add(person);
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persons;
    }
}