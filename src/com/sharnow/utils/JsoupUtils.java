package com.sharnow.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtils {

	public static Document getDoc(File f) throws IOException{
		
		return Jsoup.parse(f, "UTF-8");
	}
	//获取lable/h/a/span/p/img标签
	public static Iterator<Element> getTags(Document doc, String tag) throws IOException {
		
		Elements tags = doc.select(tag);
		Iterator<Element> it = tags.iterator();
		return it;
		/*while(it.hasNext()){
			Element el = it.next();
			el.addClass("jsoup-update");
			el.appendText("test");
			System.out.println(el.className());
			System.out.println(el.text());
		}
		System.out.println(doc.html());*/
	}
	
	//获取直接包含文字的div标签
	public static List<Element> getSpDivs(Document doc)	{
		List<Element> spDivList = new ArrayList<Element>();
		Elements tags = doc.select("div");
		Iterator<Element> it = tags.iterator();
		System.err.println(tags.size());
		while(it.hasNext()){
			Element el = it.next();
			
			if(el.childNodeSize()==1 || el.select("br")!=null){
				spDivList.add(el);			
			}
		}
		return spDivList;				
	}
	
	
	public static void addClass(Iterator<Element> it, String className){
		
		while(it.hasNext()){
			Element el = it.next();
			el.addClass(className);
//			el.appendText("test");
//			System.out.println(el.className());
//			System.out.println(el.text());
		}
		
	}
	
	public static void addClass(List<Element> li, String className){			
		for (Element el : li) {
			el.addClass(className);
		}			
	}
	
	public static void removeClass(Iterator<Element> it, String className){			
		while(it.hasNext()){
			Element el = it.next();
			el.removeClass(className);
		}		
	}
	
	public static void modifyText(Iterator<Element> it, String content){
		while(it.hasNext()){
			Element el = it.next();
			el.html(content);
		}
	}
	
	//获取div标签内style属性的css为background-image值的img路径
	public static String getStyleAttr(Document doc, String attr){
		Elements tags = doc.select("div[style]");
		Iterator<Element> it = tags.iterator();
		while(it.hasNext()){
			Element el = it.next();
			el.addClass("jsoup-update");
			String[] styles = el.attr("style").split(":");
			for (int i=0; i<styles.length; i++) {
				if(styles[i].trim().contains("background-image")){
					return styles[i+1].split("[(|)]")[1];
				}
			}
						
			//System.out.println(el.attr("style").split(":").[1].trim());
			//System.out.println(el.className());
			//System.out.println(el.html());
		}
		return null;
	}
	
	//获取修改后的html
	public static void getModifiedHtml(){
		
	}
	
	public static void main(String[] args) throws IOException {
		//getATag(null);
		
		//System.out.println(getStyleAttr(getDoc(null),null));
		//getSpDivs(getDoc(null));
		
		Elements els = getDoc(new File("D:/workspace/sharnow/WebContent/index.html")).getElementsContainingOwnText("Lorem Ipsum Maecenas");
		System.out.println("大小:"+els.size());
		Iterator<Element> it = els.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		
	}
	
}
