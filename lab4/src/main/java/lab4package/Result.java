package lab4package;

import java.util.HashMap;
import java.util.Map;

public class Result {
    public Map<String,Boolean> results;

    public Result() {
        results = new HashMap<String,Boolean>();
    }

    public String toJSON() {
        StringBuilder res = new StringBuilder("(");
        for (Map.Entry<String, Boolean> result : results.entrySet()) {
            res.append(String.format("\"%s\" - \"%s\"", result.getKey(), result.getValue() ? "true" : "false"));
        }
        res.append(")");
        return res.toString();
    }

}