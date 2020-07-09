package ua.sasparser.parser;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import ua.sasparser.domain.Category;
import ua.sasparser.domain.Offer;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Component
public class XMLParserSAX {
    public static List<Offer> xmlParserSAX() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try (FileWriter fileWriter = new FileWriter("parsing.txt")) {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyHandler handler = new MyHandler();
            File file = new File("c:\\1\\kievopt.xml");
            System.out.println("" + file.getAbsolutePath());
            System.out.println("---------------------");
            saxParser.parse(file, handler);
            //Get Employees list
            List<Offer> offersList = handler.getOffersList();
            //print employee information
            for (Offer offers : offersList) {
                System.out.println(offers);
                fileWriter.write(offers.toString() + '\n');
            }
            return offersList;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<Category> xmlParserSAXCategory() {
        SAXParserFactory saxParserFactory2 = SAXParserFactory.newInstance();
        try (FileWriter fileWriter2 = new FileWriter("category.txt")) {
            SAXParser saxParser2 = saxParserFactory2.newSAXParser();
            CategoryHandler categoryHandler = new CategoryHandler();
            File file2 = new File("d:\\category.xml");
            System.out.println("" + file2.getAbsolutePath());
            System.out.println("---------------------");
            saxParser2.parse(file2, categoryHandler);
            //Get Employees list
            List<Category> categories = categoryHandler.getCategoryList();
            //print employee information
            for (Category category : categories) {
                System.out.println(category);
                fileWriter2.write(category.toString() + '\n');
            }
            return categories;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    //для теста пробуем обработать Offer
    public static void main(String[] args) {

        String urlPath = "http://sat-ellite.net/yandex_market.xml?hash_tag=73151c839448b47c3b219d818efa07fb&sales_notes=&product_ids=&label_ids=&exclude_fields=&html_description=1&yandex_cpa=&process_presence_sure=&group_ids=6445166%2C6445163%2C44720119%2C6445170%2C6445167%2C6445169%2C6445178%2C6157855&nested_group_ids=44720119%2C6157855";

        try (FileWriter fileWriter = new FileWriter("parsingURL.txt")) {
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyHandler handler = new MyHandler();
            saxParser.parse(connection.getInputStream(), handler);
            //Get Employees list
            List<Offer> offersList = handler.getOffersList();
            //print employee information
            for(Offer offers : offersList) {
                System.out.println(offers);
                fileWriter.write(offers.toString()+'\n');
            }
            connection.disconnect();
            } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        /*
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try (FileWriter fileWriter = new FileWriter("parsing.txt")){
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyHandler handler = new MyHandler();
            File file = new File("c:\\1\\kievopt.xml");
            System.out.println(""+file.getAbsolutePath());
            System.out.println("---------------------");
            saxParser.parse(file, handler);
            //Get Employees list
            List<Offer> offersList = handler.getOffersList();
            //print employee information
            for(Offer offers : offersList) {
                System.out.println(offers);
                fileWriter.write(offers.toString()+'\n');
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
*/
    }
}
