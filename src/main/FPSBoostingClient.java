import java.awt.Color;

public class FPSBoostingClient {

    public void onLoad() {
        modules.registerButton("Show FPS", true);
        modules.registerSlider("Motion Optimization", 1, 0, 10, 1);
        modules.registerButton("Enable Discord Webhook", false);
    }

    public void onEnable() {
        client.print("FPS Boosting Client Enabled!");
        HypixelAPI.fetchPlayerData();
    }

    public void onRenderTick(float partialTicks) {
        if (modules.getButton(scriptName, "Show FPS") && client.getScreen().isEmpty()) {
            client.render.text("FPS:", 15, 15, 1, Color.orange.getRGB(), true);
            client.render.text(String.valueOf(client.getFPS()), 38, 15, 1, getChroma(2), true);
        }
    }

    public void onPostMotion() {
        double optimizationLevel = modules.getSlider(scriptName, "Motion Optimization") / 10.0;
        Vec3 motion = client.getMotion();
        client.setMotion(new Vec3(
            motion.x * optimizationLevel, 
            motion.y, 
            motion.z * optimizationLevel
        ));
    }

    public void onWorldJoin(Entity entity) {
        client.print("Player Joined: " + entity.getDisplayName());
    }

    public void onChat(String msg) {
        if (modules.getButton(scriptName, "Enable Discord Webhook")) {
            DiscordWebhook.sendMessage("User Chat: " + msg);
        }
    }

    private int getChroma(long speed) {
        float hue = client.time() % (15000L / speed) / (15000f / speed);
        return Color.getHSBColor(hue, 1f, 1f).getRGB();
    }
}
