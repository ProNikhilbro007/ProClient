public class DiscordWebhook {

    private static final String WEBHOOK_URL = "https://discord.com/api/webhooks/your-webhook-url";

    public static void sendMessage(String message) {
        client.async(() -> {
            Request request = new Request("POST", WEBHOOK_URL);
            request.setContent("{\"content\":\"" + message + "\"}");
            request.setUserAgent("Raven B4");
            request.fetch();
        });
    }
}
