package io.github.forexrateapi.client;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;

public class ForexRateApiClient {

    private static final Map<String, String> SERVERS = Map.of(
        "us", "https://api.forexrateapi.com/v1",
        "eu", "https://api-eu.forexrateapi.com/v1"
    );

    private final String apiKey;
    private final HttpClient httpClient;
    private String baseUrl;

    public ForexRateApiClient(String apiKey) {
        this(apiKey, "us");
    }

    public ForexRateApiClient(String apiKey, String server) {
        this.apiKey = apiKey;
        this.baseUrl = SERVERS.getOrDefault(server, SERVERS.get("us"));
        this.httpClient = HttpClient.newHttpClient();
    }

    public void setServer(String server) {
        this.baseUrl = SERVERS.getOrDefault(server, SERVERS.get("us"));
    }

    public JSONObject fetchSymbols() throws IOException, InterruptedException {
        return get("/symbols", Map.of());
    }

    public JSONObject fetchLive() throws IOException, InterruptedException {
        return fetchLive("", null, "");
    }

    public JSONObject fetchLive(String base, List<String> currencies, String math)
            throws IOException, InterruptedException {
        Map<String, String> params = removeEmpty(new LinkedHashMap<>() {{
            put("base", base);
            put("currencies", currencies != null ? String.join(",", currencies) : "");
            put("math", math);
        }});
        return get("/latest", params);
    }

    public JSONObject fetchHistorical(String date) throws IOException, InterruptedException {
        return fetchHistorical(date, "", null);
    }

    public JSONObject fetchHistorical(String date, String base, List<String> currencies)
            throws IOException, InterruptedException {
        Map<String, String> params = removeEmpty(new LinkedHashMap<>() {{
            put("base", base);
            put("currencies", currencies != null ? String.join(",", currencies) : "");
        }});
        return get("/" + date, params);
    }

    public JSONObject hourly(String base, String currency, String startDate, String endDate,
                             String math, String dateType) throws IOException, InterruptedException {
        Map<String, String> params = removeEmpty(new LinkedHashMap<>() {{
            put("base", base);
            put("currency", currency);
            put("start_date", startDate);
            put("end_date", endDate);
            put("math", math);
            put("date_type", dateType);
        }});
        return get("/hourly", params);
    }

    public JSONObject fetchOHLC(String base, String currency, String date, String dateType)
            throws IOException, InterruptedException {
        Map<String, String> params = removeEmpty(new LinkedHashMap<>() {{
            put("base", base);
            put("currency", currency);
            put("date", date);
            put("date_type", dateType);
        }});
        return get("/ohlc", params);
    }

    public JSONObject convert(String fromCurrency, String toCurrency, Object amount, String date)
            throws IOException, InterruptedException {
        Map<String, String> params = removeEmpty(new LinkedHashMap<>() {{
            put("from", fromCurrency);
            put("to", toCurrency);
            put("amount", amount != null ? amount.toString() : "");
            put("date", date);
        }});
        return get("/convert", params);
    }

    public JSONObject timeframe(String startDate, String endDate, String base, List<String> currencies)
            throws IOException, InterruptedException {
        Map<String, String> params = removeEmpty(new LinkedHashMap<>() {{
            put("start_date", startDate);
            put("end_date", endDate);
            put("base", base);
            put("currencies", currencies != null ? String.join(",", currencies) : "");
        }});
        return get("/timeframe", params);
    }

    public JSONObject change(String startDate, String endDate, String base, List<String> currencies, String dateType)
            throws IOException, InterruptedException {
        Map<String, String> params = removeEmpty(new LinkedHashMap<>() {{
            put("start_date", startDate);
            put("end_date", endDate);
            put("base", base);
            put("currencies", currencies != null ? String.join(",", currencies) : "");
            put("date_type", dateType);
        }});
        return get("/change", params);
    }

    public JSONObject usage() throws IOException, InterruptedException {
        return get("/usage", Map.of());
    }

    private Map<String, String> removeEmpty(Map<String, String> params) {
        return params.entrySet().stream()
                .filter(e -> e.getValue() != null && !e.getValue().isEmpty())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
    }

    private JSONObject get(String endpoint, Map<String, String> params) throws IOException, InterruptedException {
        Map<String, String> allParams = new LinkedHashMap<>(params);
        allParams.put("api_key", apiKey);

        String query = allParams.entrySet().stream()
                .map(e -> URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8) + "="
                        + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint + "?" + query))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body());
    }
}
