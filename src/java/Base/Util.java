package Base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

public class Util {

    public static String getJson(InputStream jsonOb) {
        try {
            String json = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(jsonOb));
            if (br.ready()) {
                json = br.readLine();
            }
            return json;
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }

    }

    public static String generarToken() {
        SecureRandom random = new SecureRandom();
        random.nextInt(Integer.MAX_VALUE);
        byte seed[] = new byte[30];
        random.nextBytes(seed);
        String encodeSeed = DatatypeConverter.printBase64Binary(seed);
        return encodeSeed;
    }

}
