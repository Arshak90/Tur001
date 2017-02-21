package Core;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Properties;

/**
 * Created by gev on 08.02.2017.
 */
public class FileUpload {

    public String upload(Part file) throws IOException {
        if (file != null) {
            String img = getRandomName() + file.getContentType().replace("image/", ".");
            try {
                file.write(getFileUploadUrl() + img);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return img;
        }
        return null;
    }

//    private static String getFilename(UploadedFile part) {
//        for (String cd : part.getHeader("content-disposition").split(";")) {
//            if (cd.trim().startsWith("filename")) {
//                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
//                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
//            }
//        }
//        return null;
//    }

    private String getFileUploadUrl() {
        Properties prop = new Properties();
        String url = "";
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
            prop.load(input);
            url = prop.getProperty("fileUploadUrl");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return url;
    }

    private String getRandomName() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

}

