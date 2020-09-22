package com.example.SAX;

import com.example.vo.Person;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XMLContentHandler extends DefaultHandler {

    private List<Person> persons = null;
    private Person currentPerson;
    private String tagName = null;//当前解析的元素标签

    public List<Person> getPersons() {
        return persons;
    }

    @Override/**【文档开始时，调用此方法】**/
    public void startDocument() throws SAXException {
        persons = new ArrayList<>();
    }

    @Override/**【标签开始时，调用此方法】**/
    /**【uri是命名空间|localName是不带命名空间前缀的标签名|qName是带命名空间前缀的标签名|attributes可以得到所有的属性名和对应的值】**/
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("person")) {
            currentPerson = new Person();
            currentPerson.setId(Integer.parseInt(attributes.getValue("id")));
        }
        this.tagName = qName;
    }

    @Override/**【接收标签中字符数据时，调用此方法】**/
    /**【ch存放标签中的内容，start是起始位置，length是内容长度】**/
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (tagName != null) {
            String data = new String(ch, start, length);
            if (tagName.equals("name")) {
                this.currentPerson.setName(data);
            } else if (tagName.equals("age")) {
                this.currentPerson.setAge(Short.parseShort(data));
            }
        }
    }

    @Override/**【标签结束时，调用此方法】**/
    /**【localName表示元素本地名称（不带前缀），qName表示元素的限定名（带前缀）】**/
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("person")) {
            persons.add(currentPerson);
            currentPerson = null;
        }
        this.tagName = null;
    }

}
