import io.github.forexrateapi.client.ForexRateApiClient;
import org.json.JSONObject;

import java.util.List;

public class Example {
    public static void main(String[] args) throws Exception {
        String apiKey = "REPLACE_ME";
        ForexRateApiClient client = new ForexRateApiClient(apiKey);

        // Or use EU server:
        // ForexRateApiClient client = new ForexRateApiClient(apiKey, "eu");

        JSONObject result;

        result = client.fetchSymbols();
        System.out.println(result);

        result = client.fetchLive("USD", List.of("AUD", "CAD", "GBP", "JPY"), "");
        System.out.println(result);

        result = client.fetchHistorical("2024-02-05", "USD", List.of("AUD", "CAD", "GBP", "JPY"));
        System.out.println(result);

        result = client.hourly("USD", "EUR", "2024-02-05", "2024-02-05", "", "");
        System.out.println(result);

        result = client.fetchOHLC("USD", "EUR", "2024-02-05", "");
        System.out.println(result);

        result = client.convert("USD", "EUR", 100, "2024-02-05");
        System.out.println(result);

        result = client.timeframe("2024-02-05", "2024-02-06", "USD", List.of("AUD", "CAD", "GBP", "JPY"));
        System.out.println(result);

        result = client.change("2024-02-05", "2024-02-06", "USD", List.of("AUD", "CAD", "GBP", "JPY"), "");
        System.out.println(result);

        result = client.usage();
        System.out.println(result);
    }
}
