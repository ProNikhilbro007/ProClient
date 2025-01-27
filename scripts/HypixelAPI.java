public class HypixelAPI {

    private static final String API_KEY = "your-api-key";
    private static final String UUID = "your-uuid";

    public static void fetchPlayerData() {
        client.async(() -> {
            Request request = new Request("GET", "https://api.hypixel.net/v2/player?uuid=" + UUID);
            request.addHeader("API-Key", API_KEY);

            Response response = request.fetch();
            int code = response.code();

            if (code == 200) {
                Json json = response.json();
                client.print("Hypixel Data: " + json.string());
            } else {
                client.print("Hypixel API Error: " + code);
            }
        });
    }
}
