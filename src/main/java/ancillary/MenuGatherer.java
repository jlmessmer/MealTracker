package ancillary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.commons.lang3.time.DateUtils;

public class MenuGatherer {
	private static final String BAKERY = "Bakery";
	private static final String DINER_GRILL = "Diner Grill";
	private static final String INTERNATIONAL_FLAVORS_AND_SOUPS = "International Flavors and Soups";
	private static final String PIZZA_AND_PASTA_BAR = "Pizza and Pasta Bar";
	private static final String THE_GRILL = "The Grill";
	private static final String THE_KITCHEN_TABLE = "The Kitchen Table";
	private static final String THE_SMOKER = "The Smoker";
	private static final String VEG_OUT = "Veg Out";
	
	private static String urlBase = "http://menus.dining.unc.edu/rams-head-dining-hall?date=";
	
	public static void main(String[] args){		
		try{
			getAllData();
		} catch(MalformedURLException murl){
			murl.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		} catch(ParseException pe){
			pe.printStackTrace();
		}
	}
	
	public static void getAllData() throws MalformedURLException, IOException, ParseException{
		String firstDate = "2016-01-08";
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = fmt.parse(firstDate);
		Date today = fmt.parse("2016-02-18");
		String home = System.getProperty("user.home");
		File output = new File(home + "/data.txt");
		if(!output.exists()){
			output.createNewFile();
		}
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), "utf-8"));
		
		while(!DateUtils.isSameDay(today, date)){
			System.out.println(date);
			writer.write(getDataForDate(date));
			date = DateUtils.addDays(date, 1);
		}
		writer.close();
	}
	
	public static String getDataForDate(Date d) throws MalformedURLException, IOException{
		Menu menu = new Menu();

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		URL url = new URL(urlBase + fmt.format(d));
		InputStream is = url.openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String html, line;
		html = "";
		while((line = br.readLine()) != null){
			html += line;
		}
		Document doc = Jsoup.parse(html);
		Element dinner = doc.getElementById("Hours-Dinner");
		Document dinnerDoc = Jsoup.parse(dinner.html());
		Elements stations = dinnerDoc.getElementsByClass("station");
		for(Element e : stations){
			switch(e.text()){
			case BAKERY:
			case DINER_GRILL:
			case INTERNATIONAL_FLAVORS_AND_SOUPS:
			case PIZZA_AND_PASTA_BAR:
			case THE_GRILL:	
			case THE_KITCHEN_TABLE:
			case THE_SMOKER:
			case VEG_OUT:
				Elements sibs = e.siblingElements();
				Element sib = sibs.get(0);
				Document mealDoc = Jsoup.parse(sib.html());
				Elements meals = mealDoc.getElementsByClass("recipe");
				for(Element m : meals){
					menu.add(new MenuItem(m.text()));
				}
				break;
			default:
				break;
			}
		}
		return menu.toString();
	}
}
