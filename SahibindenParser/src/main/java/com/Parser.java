package com;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;


public class Parser {

	public static void main(String[] args) throws Exception{
		parseFromFile();
	}
	
	public static void parseFromFile() throws Exception{
		String fileName = "D:/calismalar/webcrawlers/sahibinden-web/ilan/emlak-konut-satilik-alemdag-da-orman-manzarali-sitede-kiralik-daire-125592165/detay.html";
		File file = new File(fileName);
		if(!file.exists()){
			System.out.println("dosya eksik");
			return ;
		}
		Document doc = Jsoup.parse(file,"ISO-8859-9");
		parse(doc);
	}
	
	
	public static void parseFromUrl(String string) throws Exception{
		Document doc = Jsoup.parse(string,"ISO-8859-9");
		parse(doc);
	}
	
	public static void parse(Document doc) throws Exception{
	String endPoint = "http://www.sahibinden.com/satilik-daire";
	
	
	
	String fiyat ="";
	String sehir="";
	String ilce ="";
	String mah ="";
	
	String ilanNo="";
	String ilanTarihi="";
	String emlakTipi="";
	String m2 = "";
	String odaSayisi="";
	String banyoSayisi="";
	String binaYasi="";
	String binaKatSayisi="";
	String bulunduguKat="";
	String isitma="";
	String kullanimDurumu="";
	String krediyeUygun="";
	String kimden ="";
	String takas="";
	
	
	
	
	
	
	
	//Document doc = Jsoup.parse(new URL(endPoint),100000);
	//Document doc = Jsoup.parse(downloadHtml(endPoint));
	
	
	Element impress = doc.getElementById("classifiedInfo");
	Elements elements = doc.getElementsByClass("classifiedInfo");
	
	Element cost=elements.get(0);
	Elements costElements = cost.getElementsByTag("h3");
	if(costElements.size()>0){
		Element a = costElements.get(0);
		fiyat = a.html();
	}
	
	
	Elements locationElements = cost.getElementsByTag("h2");
	if(locationElements.size()>0){
		Element a = locationElements.get(0);
		Elements b = a.getElementsByTag("a");
		if(b.size()>0){
			sehir  =b.get(0).html();
			ilce = b.get(1).html();
			mah = b.get(2).html();
			
		}
		
	}
	
	Elements informations = cost.getElementsByClass("classifiedInfoList"); 
	if(informations.size()>0){
		Element a = informations.get(0);
		Elements b  =a.getElementsByTag("li");
		
		Element ilanNoElement = b.get(0);
		ilanNo  = ilanNoElement.child(1).html();
		
		Element tarihElement = b.get(1);
		ilanTarihi  = tarihElement.child(1).html();
		
		Element m2Element = b.get(3);
		m2  = m2Element.child(1).html();
		
		Element odaSayisiElement = b.get(4);
		odaSayisi = odaSayisiElement.child(1).html();
			
		Element banyoSayisiElement = b.get(5);
		banyoSayisi = banyoSayisiElement.child(1).html();
		
		Element binaYasiElement = b.get(6);
		binaYasi  = binaYasiElement.child(1).html();
				
		Element binaKatSayisiElement = b.get(7);
		binaKatSayisi  = binaKatSayisiElement.child(1).html();
				
		Element bulunduguKatElement = b.get(8);
		bulunduguKat  = bulunduguKatElement.child(1).html();
				
		Element isitmaElement = b.get(9);
		isitma  = isitmaElement.child(1).html();
		
		Element kullanimDurumuElement = b.get(10);
		kullanimDurumu  = kullanimDurumuElement.child(1).html();
				
		Element krediyeUyunElement = b.get(11);
		krediyeUygun  = krediyeUyunElement.child(1).html();
				
		Element kimdenElement = b.get(12);
		kimden  = kimdenElement.child(1).html();
				
		Element takasElement = b.get(13);
		takas  = takasElement.child(1).html();
		
	}
	

	
	System.out.println(fiyat+","+sehir+","+ilce+","+mah+","+ilanNo+","+ilanTarihi+","+emlakTipi+","+m2+","+odaSayisi+","+banyoSayisi+","+binaYasi+","+binaKatSayisi+","+bulunduguKat+","+isitma+","+kullanimDurumu+","+krediyeUygun+","+kimden+","+","+takas);
}

private static String downloadHtml(String path) {
    InputStream is = null;
    try {
        String result = "";
        String line;

        URL url = new URL(path);
        is = url.openStream();  // throws an IOException
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        while ((line = br.readLine()) != null) {
            result += line;
            System.out.println(new Date());
        }
        return result;
    } catch (IOException ioe) {
        ioe.printStackTrace();
    } finally {
        try {
            if (is != null) is.close();
        } catch (IOException ioe) {
            System.out.println("burda");
        }
    }
    return "";
}
}
