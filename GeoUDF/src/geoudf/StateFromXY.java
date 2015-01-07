/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geoudf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;

/**
 * A custom UDF for Apache Hive that returns a 2-character state code for a given x,y set
 * @author toddbodnar
 */
public class StateFromXY extends UDF{

    public Text evaluate(final Double x, final Double y)
    {
        int id = getStateFromCoord.idFromCoords( x,y);
        
        if(id == -1)
            return new Text("XX");
        
        return new Text(getStateFromCoord.abbrevFromId(id).toUpperCase());
    }
    
}
