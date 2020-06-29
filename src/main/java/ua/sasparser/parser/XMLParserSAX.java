package ua.sasparser.parser;

import org.springframework.context.annotation.Bean;
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
import java.util.List;

@Component
public class XMLParserSAX {
    public static  List<Offer> xmlParserSAX() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try (FileWriter fileWriter = new FileWriter("parsing.txt")){
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyHandler handler = new MyHandler();
            File file = new File("d:\\dom.xml");
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
            return offersList;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }






    public static  List<Category> xmlParserSAXCategory() {
           SAXParserFactory saxParserFactory2 = SAXParserFactory.newInstance();
        try (FileWriter fileWriter2 = new FileWriter("category.txt")){
            SAXParser saxParser2 = saxParserFactory2.newSAXParser();
            CategoryHandler categoryHandler = new CategoryHandler();
            File file2 = new File("d:\\category.xml");
            System.out.println(""+file2.getAbsolutePath());
            System.out.println("---------------------");
            saxParser2.parse(file2, categoryHandler);
            //Get Employees list
            List<Category> categories = categoryHandler.getCategoryList();
            //print employee information
            for(Category category : categories) {
                System.out.println(category);
                fileWriter2.write(category.toString()+'\n');
            }
            return categories;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }








/*
    public static void main(String[] args) {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try (FileWriter fileWriter = new FileWriter("parsing.txt")){
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyHandler handler = new MyHandler();
            File file = new File("d:\\dom.xml");
            System.out.println(""+file.getAbsolutePath());
            System.out.println("---------------------");
            saxParser.parse(file, handler);
            //Get Employees list
            List<Offers> offersList = handler.getOffersList();
            //print employee information
            for(Offers offers : offersList) {
                System.out.println(offers);
                fileWriter.write(offers.toString()+'\n');
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

*/
}
