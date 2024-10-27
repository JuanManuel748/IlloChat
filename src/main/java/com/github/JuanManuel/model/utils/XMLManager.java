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
            // Crea un contexto JAXB para la clase del objeto
            context = JAXBContext.newInstance(c.getClass());
            // Crea un marshaller para convertir el objeto a XML
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            // Escribe el objeto en el archivo especificado
            m.marshal(c, Paths.get(filename).toFile());
            result = true;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <T> T readXML(T c, String filename) {
        T result = c;
        JAXBContext context;
        try {
            File file = Paths.get(filename).toFile();
            // Verifica si el archivo existe y tiene contenido
            if (!file.exists() || file.length() == 0) {
                return result;
            }
            context = JAXBContext.newInstance(c.getClass());
            // Crea un unmarshaller para leer el XML y convertirlo a un objeto
            Unmarshaller um = context.createUnmarshaller();
            result = (T) um.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }
}
