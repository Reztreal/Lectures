package main.common;

import main.fhms.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class has tools commonly used in the package.
 *
 * @author Furkan Tokac
 * {@inheritDoc}
 */
public class Tools {
    private DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");

    /**
     * This function easly convert String date to Date and return it if
     * pre-defined date format matches with String.
     *
     * @param date String version of the
     * @return Date This returns Date if there is no execption. If there is, returns null.
     */
    public Date stringToDate(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object saveDataToFile(String fileName, Object data) {
        Object result = null;
        try {
            FileOutputStream fo = new FileOutputStream(new File(fileName));
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject( data );
            oo.close();
            fo.close();
        } catch (Exception e) {
            System.out.println("Error while saving data to " + fileName);
        }
        return result;
    }

    public Object loadDataFromFile(String fileName) {
        Object result = null;
        try {
            FileInputStream fo = new FileInputStream(new File(fileName));
            ObjectInputStream oo = new ObjectInputStream(fo);
            result = oo.readObject();
            oo.close();
            fo.close();
        } catch (Exception e) {
            //System.out.println("Error while retrieving data from " + fileName);
        }
        return result;
    }

    /**
     * @return DateFormat Returns default date format.
     */
    public DateFormat getDateFormat() {
        return dateFormat;
    }

    /**
     * Changes default dateFormat. Be carefull when changing this dynamically!
     * Change affects all the date variable stored in the program before.
     *
     * @param dateFormat
     */
    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }
}