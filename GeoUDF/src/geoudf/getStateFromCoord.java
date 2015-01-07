/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geoudf;

import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Finds which state in the usa that a given longitude/latitude is in
 * @author toddbodnar
 * 
 * Polygons from https://github.com/kjhsoftware/us-state-polygons/blob/master/states.xml, mit license
 */
public class getStateFromCoord {
    public static int idFromCoords(double longitude, double latitude)
    {
        if(states == null)
            initStates();
        
        Point2D loc = new Point2D.Double(1000*longitude,1000*latitude);
        
        for(int ct=0;ct<states.length;ct++)
            if(states[ct].contains(loc))
                return ct;
        
        return -1;
    }
    public static String nameFromId(int id)
    {
        return names[id];
    }
    public static String abbrevFromId(int id)
    {
        return abbreviations[id];
    }
    
    public static int getNumberOfPlaces()
    {
        return names.length;
    }
    
    static Polygon states[] = null;
    static String names[] = {"samoa","guam","northern mariana islands","virgin islands","puerto rico","hawaii","alaska","connecticut","maine","massachusetts","new hampshire","rhode island","vermont","new jersey","new york","delaware", "district of columbia","maryland","pennsylvania","virginia","west virginia","alabama","florida","georgia","kentucky","mississippi","north carolina","south carolina","tennessee","illinois","indiana","michigan","minnesota","ohio","wisconsin","arkansas","louisiana","new mexico","oklahoma","texas","iowa","kansas","missouri","nebraska","colorado","montana","north dakota","south dakota","utah","wyoming","arizona","california","nevada","idaho","oregon","washington"};
    static String abbreviations[] = {"as","gu","mp","vi","pr","hi","ak","ct","me","ma","nh","ri","vt","nj","ny","de","dc","md","pa","va","wv","al","fl","ga","ky","ms","nc","sc","tn","il","in","mi","mn","oh","wi","ar","la","nm","ok","tx","ia","ks","mo","ne","co","mt","nd","sd","ut","wy","az","ca","nv","id","or","wa"};
    
    private static void initStates() {
        try {
            states = new Polygon[names.length];
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = (Document) dBuilder.parse(getStateFromCoord.class.getResourceAsStream("/states.xml"));
            
            NodeList statesnode = doc.getElementsByTagName("state");
            for(int ct=0;ct<statesnode.getLength();ct++)
            {
                String name = statesnode.item(ct).getAttributes().getNamedItem("name").getNodeValue().toLowerCase();
                int id = -1;
                for(int c=0;c<names.length;c++)
                    if(name.equals(names[c]))
                        id = c;
                
                if(id < 0)
                    throw new IOException("Error, state name not recognized \""+name+"\"");
                
                
                Polygon p = new Polygon();
                NodeList points = statesnode.item(ct).getChildNodes();
                for(int ct2=0;ct2<points.getLength();ct2++)
                {
                   // System.out.println(points.item(ct2).getAttributes().getNamedItem("lat").getNodeValue());
                    if(points.item(ct2).getAttributes() == null)
                        continue;
                    
                    //System.out.println(points.item(ct2).getAttributes().getNamedItem("lat"));
                    
               //     String l = points.item(ct2).getAttributes().getNamedItem("lat").;
                    int lat = (int)(1000*((Double.parseDouble(points.item(ct2).getAttributes().getNamedItem("lat").getNodeValue()))));
                    int lng = (int)(1000*((Double.parseDouble(points.item(ct2).getAttributes().getNamedItem("lng").getNodeValue()))));
                     
                    p.addPoint(lng, lat);
                    
                }
                
                states[id] = p;
            }
            
        } catch (SAXException ex) {
            Logger.getLogger(getStateFromCoord.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getStateFromCoord.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(getStateFromCoord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String args[])
    {
        for(int i=0;i<names.length;i++)
            System.out.println(i+"\t"+abbrevFromId(i)+"\t"+nameFromId(i));
        
        System.out.println(nameFromId(idFromCoords(-77.673340,40.780541)));
        System.out.println(idFromCoords(0,0));
        System.out.println(getNumberOfPlaces());
    }
}
