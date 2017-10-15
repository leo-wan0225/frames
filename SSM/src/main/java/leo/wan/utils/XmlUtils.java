package leo.wan.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * 这是个基于XPath解析xml的工具类，这段代码写的很差，只当做事xpath学习的成功即可
 *@Title  
 *@Description  随着经验和技术的增长,这些代码要用新的技术比如dom4j,xstream来重构，从而实现更加强大且通用的功能
 *@author leo
 *@date 2017年10月5日  下午7:08:32
 */
public class XmlUtils {
	private  String xmlPath;
	private Document document ; 
	private XPath xPath;
	private XmlUtils(String xmlPath){
		this.xmlPath = xmlPath;
	}
	public static XmlUtils  getXmlUtils(String xmlPath){
		return new XmlUtils(xmlPath);
	}
	/**
	 * 得到xml文件的Document对象
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public Document getDocument() throws ParserConfigurationException, SAXException, IOException{
		if (document==null) {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			return documentBuilder.parse(xmlPath);
		}
		return document;
		
	}
	/**
	 * 得到XPath对象
	 * @return
	 */
	public XPath getXPath(){
		if (xPath==null) {
			XPathFactory xPathFactory = XPathFactory.newInstance();
			return xPathFactory.newXPath();
		}
		return xPath;
	}
	/**
	 * 创建专门用来获取节点内容的xpath
	 * @param xPath 
	 * @return
	 */
	private String createXPathForContext(String xPath){
		return xPath+"/text()";
	}
	/**
	 * 创建专门用来获取属性值的xpath
	 * @param xPath 
	 * @return
	 */
	private String createXPathForAttr(String xPath,String attrName){
		return xPath+"/@"+attrName;
	}
	/**
	 * 对整个xml文档搜索，获得对应节点的文本内容
	 * @param xPath 查询节点用的xpath
	 * @return
	 * @throws XPathExpressionException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public String getElementContext(String xPath) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{
		return  (String) getXPath().evaluate(createXPathForContext(xPath), getDocument(), XPathConstants.STRING);
	}
	/**
	 * 对整个xml文档搜索，获得对应节点的给定的属性值
	 * @param xPath 查询节点用的xpath
	 * @param arrtNames 需要查询的属性名
	 * @return 
	 * @throws XPathExpressionException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public Map<String, String> getAttributeValue(String xPath,String... arrtNames) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{
		String[] values = new String[arrtNames.length];
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < arrtNames.length; i++) {
			map.put(arrtNames[i], (String) getXPath().evaluate(createXPathForAttr(xPath,arrtNames[i]), getDocument(), XPathConstants.STRING));
		}
		
	return map;
	}
	/**
	 * 对整个xml文档搜索，获取所有符合要求的节点集合
	 * @param xPath 查询节点的xpath
	 * @return
	 * @throws XPathExpressionException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public NodeList getElements(String xPath) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{
		return  (NodeList) getXPath().evaluate(createXPathForContext(xPath), getDocument(), XPathConstants.NODESET);

	}
	/**
	 * 获取节点的子节点的文本内容, 此方法的xpath必须写全
	 * @param xPath
	 * @param element
	 * @return
	 * @throws XPathExpressionException 
	 */
	public String getAttributeValue(String xPath,Object object) throws XPathExpressionException{
		return (String) getXPath().evaluate(xPath, object, XPathConstants.STRING);
	}
	
	
}
