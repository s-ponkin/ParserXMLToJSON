package JACKSON;

import JACKSON.model.UserProfiles;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ParserXMLToJSONMethodJACKSON {

    public static void parseXMLToJSON(String inputPath, String outputPath){
        ObjectMapper objectMapper = new XmlMapper();
        UserProfiles userProfiles = null;
        try {
            userProfiles = objectMapper.readValue(
                    StringUtils.toEncodedString(Files.readAllBytes(Paths.get(inputPath)), StandardCharsets.UTF_8
                    ), UserProfiles.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(userProfiles);
    }

}
