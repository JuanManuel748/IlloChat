package com.github.JuanManuel.model.utils;

import com.github.JuanManuel.view.WelcomeController;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.Paths;


public class XMLManager {
    public static <T> boolean writeXML(T c, String filename) {
        boolean result = false;
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(c.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            m.marshal(c, Paths.get(filename).toFile());
            result = true;
        } catch (JAXBException e) {
            e.printStackTrace(); // mode development
        }
        return result;
    }

    public static <T> T readXML(T c, String filename) {
        T result = c;
        JAXBContext context;
        try {
            File file = Paths.get(filename).toFile();
            if (!file.exists() || file.length() == 0) {
                return result; // Return empty object if file does not exist or is empty
            }
            context = JAXBContext.newInstance(c.getClass());
            Unmarshaller um = context.createUnmarshaller();
            result = (T) um.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }
}