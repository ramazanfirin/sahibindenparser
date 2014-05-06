/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.uci.ics.crawler4j.examples.basic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLConnection;
import java.util.List;
import java.util.regex.Pattern;

import com.Parser;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author Yasser Ganjisaffar <lastname at gmail dot com>
 */
public class BasicCrawler extends WebCrawler {

	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g" + "|png|tiff?|mid|mp2|mp3|mp4"
			+ "|wav|avi|mov|mpeg|ram|m4v|pdf" + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
	
//	private final static Pattern FILTERS = Pattern.compile();

	/**
	 * You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic).
	 */
	@Override
	public boolean shouldVisit(WebURL url) {
		String href = url.getURL().toLowerCase();
		boolean result = !FILTERS.matcher(href).matches() && href.startsWith("http://www.sahibinden.com/") && 
				href.contains("emlak-konut-satilik") && (href.contains("detay") || href.contains("pagingOff"));
//		if(result)
//			System.out.println("true");
//		else
//			System.out.println("false");
		return result;
	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	@Override
	public void visit(Page page) {
		int docid = page.getWebURL().getDocid();
		String url = page.getWebURL().getURL();
		String domain = page.getWebURL().getDomain();
		String path = page.getWebURL().getPath();
		String subDomain = page.getWebURL().getSubDomain();
		String parentUrl = page.getWebURL().getParentUrl();
		String anchor = page.getWebURL().getAnchor();

//		System.out.println("Docid: " + docid);
//		System.out.println("URL: " + url);
//		System.out.println("Domain: '" + domain + "'");
//		System.out.println("Sub-domain: '" + subDomain + "'");
//		System.out.println("Path: '" + path + "'");
//		System.out.println("Parent page: " + parentUrl);
//		System.out.println("Anchor text: " + anchor);
		String html="";
		
		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String text = htmlParseData.getText();
			html = htmlParseData.getHtml();
			List<WebURL> links = htmlParseData.getOutgoingUrls();

//			System.out.println("Text length: " + text.length());
//			System.out.println("Html length: " + html.length());
//			System.out.println("Number of outgoing links: " + links.size());
			
		}

//		Header[] responseHeaders = page.getFetchResponseHeaders();
//		if (responseHeaders != null) {
//			System.out.println("Response headers:");
//			for (Header header : responseHeaders) {
//				System.out.println("\t" + header.getName() + ": " + header.getValue());
//			}
//		}
		
		try {
			 if(url.contains("pagingOff") || url.contains("detay")){
			String content = html;
 
//			String  crawlStorageFolder = "D:/calismalar/webcrawlers/sahibinden-web";
//			File aa= new File(crawlStorageFolder);
//			if(!aa.exists())
//				aa.mkdir();
//			
//			String  crawlStorageFolderIlan = crawlStorageFolder+"\\ilan";
//			File alana= new File(crawlStorageFolderIlan);
//			if(!alana.exists())
//				alana.mkdir();
//			
//			String[] paths = path.split("/");
//			
//			File directory = new File(crawlStorageFolderIlan+"\\"+paths[2]);
//			directory.mkdir();
//			
//			File file = new File(directory+"\\"+"detay");
//			// if file doesnt exists, then create it
//			if (!file.exists()) {
//				file.createNewFile();
//			}
// 
//			FileWriter fw = new FileWriter(file.getAbsoluteFile());
//			BufferedWriter bw = new BufferedWriter(fw);
//			bw.write(content);
//			bw.close();
// 
//			System.out.println("Done");
			Parser.parseFromUrl(html);;
 
		} 
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//System.out.println("=============");
	}
}
