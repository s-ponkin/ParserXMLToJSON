package JACKSON;

import JACKSON.model.UserProfiles;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.StringUtils;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ParserXMLToJSONMethodJACKSON {

    /**
     * Метод конвертирует файл формата XML в формат JSON методом JACKSON.
     *
     * @param inputPath  путь к файлу формата XML.
     * @param outputPath путь к файлу формата JSON.
     */

    public static void parseXMLToJSON(String inputPath, String outputPath) {

        if (!checkValidation(inputPath)) {
            System.out.println("Validity error.");
        }

        ObjectMapper objectMapper = new XmlMapper();
        UserProfiles userProfiles;
        String jsonObject = null;
        try {
            userProfiles = objectMapper.readValue(
                    StringUtils.toEncodedString(Files.readAllBytes(Paths.get(inputPath)), StandardCharsets.UTF_8
                    ), UserProfiles.class);

            DefaultPrettyPrinter defaultPrettyPrinter = new DefaultPrettyPrinter();
            defaultPrettyPrinter.indentArraysWith(new DefaultIndenter());
            ObjectWriter objectWriter = new ObjectMapper().writer().with(defaultPrettyPrinter);
            jsonObject = objectWriter.writeValueAsString(userProfiles);

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonObject == null) {
            return;
        }

        File file = new File(outputPath);
        try {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonObject);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkValidation(String path) {
        SchemaFactory factory = SchemaFactory.newDefaultInstance();
        Source schemaFile = new StreamSource(new File("xsd-schema/user_profile_roles.xsd"));
        Source schemaFile1 = new StreamSource(new File("xsd-schema/types.xsd"));
        try {
            Schema schema = factory.newSchema(new Source[]{schemaFile, schemaFile1});
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(path)));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
