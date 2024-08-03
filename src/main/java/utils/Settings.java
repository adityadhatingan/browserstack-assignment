package utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;

public class Settings {
    @JsonProperty
    private String browser;

    private static Settings currentSettings;

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public static Settings getSettings() {
        if (Settings.currentSettings == null) {
            Settings.currentSettings = loadSettings();
        }
        return Settings.currentSettings;
    }

    private static Settings loadSettings() {
        try {
            Settings current = Settings.fromFromYmlString(Resources.toString(
                    Resources.getResource("settings.yml"), Charsets.UTF_8));
            return current;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Settings fromFromYmlString(String ymlStr) throws IOException {
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(ymlStr, Settings.class);
    }
}
