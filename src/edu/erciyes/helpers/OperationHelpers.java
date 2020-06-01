package edu.erciyes.helpers;

import edu.erciyes.models.RecordModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OperationHelpers {
    public static InputStream getHttpResponse(String urlString){
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            return con.getInputStream();
        }catch (Exception ex){
            System.out.println(ex);
        }
        return new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };
    }
    public static ArrayList<RecordModel> parseXmlString(InputStream xml){
        ArrayList<RecordModel> allCases = new ArrayList<RecordModel>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xml);
            NodeList recordList = doc.getElementsByTagName("record");
            for (int i = 0; i < recordList.getLength(); i++ ){
                RecordModel covidCase = new RecordModel();
                Node rec = recordList.item(i);
                if (rec.getNodeType() == Node.ELEMENT_NODE){
                    Element record = (Element) rec;
                    NodeList childList = record.getChildNodes();

                    NodeList dateRep = record.getElementsByTagName("dateRep");
                    covidCase.dateRep = new SimpleDateFormat("dd/MM/yyyy").parse(dateRep.item(0).getTextContent());
                    NodeList cases = record.getElementsByTagName("cases");
                    covidCase.cases = Integer.parseInt(cases.item(0).getTextContent());
                    NodeList deaths = record.getElementsByTagName("deaths");
                    covidCase.deaths = Integer.parseInt(deaths.item(0).getTextContent());
                    NodeList countriesAndTerritories = record.getElementsByTagName("countriesAndTerritories");
                    covidCase.countriesAndTerritories = countriesAndTerritories.item(0).getTextContent();
                    NodeList geoId = record.getElementsByTagName("geoId");
                    covidCase.geoId = geoId.item(0).getTextContent();
                    NodeList countryterritoryCode = record.getElementsByTagName("countryterritoryCode");
                    covidCase.countryterritoryCode = countryterritoryCode.item(0).getTextContent();
                    NodeList popData = record.getElementsByTagName("popData2018");
                    String population = popData.item(0).getTextContent();
                    if (!population.equals(""))   //There is no population data for Anguilla (AI) and Western_Sahara (EH): https://prnt.sc/seup80"
                    {
                        System.out.println("pop = " + population);
                        covidCase.popData2018 = Integer.parseInt(population);
                        NodeList continentExp = record.getElementsByTagName("continentExp");
                        covidCase.continentExp = continentExp.item(0).getTextContent();

                        allCases.add(covidCase);
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return allCases;
    }
    public static Boolean isXmlValid(String xml){
        String regex = "^https?:\\/\\/(w{3}\\.)?[a-zA-Z0-9-]+\\.[a-zA-Z]+.*xml\\/?$"; //Check the url if it is right format.
        return xml.matches(regex);
    }
    public static Boolean isDateValid(String date){
        String regex = "^[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}$"; //Check the date if it is right format.
        System.out.println("Regex " + date.matches(regex) );
        return date.matches(regex);
    }
}
