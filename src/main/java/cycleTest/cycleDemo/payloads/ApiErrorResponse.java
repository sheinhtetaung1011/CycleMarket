package cycleTest.cycleDemo.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ApiErrorResponse implements Serializable {

    private boolean error;
    private String message;
    private HashMap<String, String> fieldErrors;

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("error", this.error);
            jsonObject.put("message", this.message);
        } catch (JSONException e) {
        }
        return jsonObject.toString();
    }


}
