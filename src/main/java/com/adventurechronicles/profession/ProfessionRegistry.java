package com.adventurechronicles.profession;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public final class ProfessionRegistry {

    private static final Map<String, Profession> PROFESSIONS =
            new LinkedHashMap<>();

    private ProfessionRegistry() {}

    public static void register(Profession profession) {
        String id = profession.getId();

        if (PROFESSIONS.containsKey(id)) {
            throw new IllegalArgumentException(
                    "Profession already registered: " + id
            );
        }

        PROFESSIONS.put(id, profession);
    }

    public static Optional<Profession> getById(String id) {
        return Optional.ofNullable(PROFESSIONS.get(id));
    }

    public static Collection<Profession> getAll() {
        return Collections.unmodifiableCollection(PROFESSIONS.values());
    }

    public static boolean contains(String id) {
        return PROFESSIONS.containsKey(id);
    }

    public static void clear() {
        PROFESSIONS.clear();
    }
}