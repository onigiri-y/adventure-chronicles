
package com.adventurechronicles.profession;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;

import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class ProfessionLoader
        implements SimpleSynchronousResourceReloadListener {

    private static final String FOLDER = "professions";

    private ProfessionLoader() {}

    @Override
    public Identifier getFabricId() {
        return Identifier.of(
                "adventure_chronicles",
                "profession_loader"
        );
    }


    @Override
    public void reload(ResourceManager manager) {

        ProfessionRegistry.clear();

        Map<Identifier, Resource> resources =
                manager.findResources(
                        FOLDER,
                        id -> id.getPath().endsWith(".json")
                );

        for (var entry : resources.entrySet()) {

            Identifier fileId = entry.getKey();
            Resource resource = entry.getValue();

            try (Reader reader = resource.getReader()) {

                JsonObject json =
                        JsonParser.parseReader(reader).getAsJsonObject();

                Profession profession =
                        parseProfession(fileId, json);

                ProfessionRegistry.register(profession);

                System.out.println(
                        "[Adventure Chronicles] Loaded profession: "
                                + profession.getId()
                );

            } catch (Exception exception) {

                System.err.println(
                        "[Adventure Chronicles] Failed to load: "
                                + fileId
                );

                exception.printStackTrace();
            }
        }
    }


    private static Profession parseProfession(
            Identifier fileId,
            JsonObject json
    ) {

        String path = fileId.getPath();

        String id = fileId.getNamespace() + ":"
                + path.substring(
                (FOLDER + "/").length(),
                path.length() - ".json".length()
        );

        Profession.Type type = Profession.Type.valueOf(
                getString(json, "type").toUpperCase(Locale.ROOT)
        );

        String category = getString(json, "category");
        String name = getString(json, "name");
        String description = getString(json, "description");

        List<ProfessionLevel> levels = new ArrayList<>();

        if (type == Profession.Type.NORMAL) {

            JsonArray levelArray = json.getAsJsonArray("levels");

            for (JsonElement element : levelArray) {

                JsonObject levelJson = element.getAsJsonObject();

                int level = levelJson.get("level").getAsInt();
                String levelDescription =
                        getString(levelJson, "description");

                levels.add(
                        new ProfessionLevel(level, levelDescription)
                );
            }
        }

        return new Profession(
                id,
                type,
                category,
                name,
                description,
                levels
        );
    }


    private static String getString(
            JsonObject json,
            String key
    ) {

        JsonElement element = json.get(key);

        if (element == null || !element.isJsonPrimitive()
                || !element.getAsJsonPrimitive().isString()) {

            throw new IllegalArgumentException(
                    "Missing or invalid JSON field: " + key
            );
        }

        return element.getAsString();
    }


    public static void register() {

        ResourceManagerHelper
                .get(ResourceType.SERVER_DATA)
                .registerReloadListener(new ProfessionLoader());
    }
}
