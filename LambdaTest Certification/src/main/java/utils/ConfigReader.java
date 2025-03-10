package utils;

import java.io.InputStream;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

public class ConfigReader {
    private static Map<String, Object> config;

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(".gitpod.yml")) {
            Yaml yaml = new Yaml();
            config = yaml.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.yml", e);
        }
    }

    public static String get(String key) {
        String[] keys = key.split("\\.");
        Map<String, Object> data = config;
        for (int i = 0; i < keys.length - 1; i++) {
            data = (Map<String, Object>) data.get(keys[i]);
        }
        return data.get(keys[keys.length - 1]).toString();
    }
}
