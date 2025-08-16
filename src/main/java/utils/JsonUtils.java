package utils;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonUtils {
    private static final String JSON_FILE_PATH = "src/test/resources/";
    private String jsonReader;
    private String jsonFileName;

    public JsonUtils(String jsonFileName) {
        this.jsonFileName = jsonFileName;
        try {
            JSONObject data = (JSONObject) new JSONParser()
                    .parse(new FileReader(JSON_FILE_PATH + jsonFileName + ".json"));
            jsonReader = data.toJSONString();
        } catch (Exception e) {
            LogsUtil.error(e.getMessage());
        }
    }

    public String getJsonData(String jsonPath) {
        String testData = "";
        try {
            testData = JsonPath.read(jsonReader, jsonPath);
        } catch (Exception e) {
            LogsUtil.error(e.getMessage(),
                    "No results for json path: '" + jsonPath + "' in the json file: " + jsonFileName);
        }
        return testData;
    }
}
