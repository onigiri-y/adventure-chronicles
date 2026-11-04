
package com.adventurechronicles.profession;

import java.util.List;
import java.util.Objects;

public final class Profession {

    public enum Type {
        NORMAL,
        SPECIAL
    }

    private final String id;
    private final Type type;
    private final String category;
    private final String name;
    private final String description;
    private final List<ProfessionLevel> levels;

    public Profession(
            String id,
            Type type,
            String category,
            String name,
            String description,
            List<ProfessionLevel> levels
    ) {
        this.id = Objects.requireNonNull(id);
        this.type = Objects.requireNonNull(type);
        this.category = Objects.requireNonNull(category);
        this.name = Objects.requireNonNull(name);
        this.description = Objects.requireNonNull(description);
        this.levels = List.copyOf(levels);

        if (type == Type.NORMAL && this.levels.size() != 3) {
            throw new IllegalArgumentException(
                    "Normal professions must have exactly 3 levels."
            );
        }

        if (type == Type.SPECIAL && !this.levels.isEmpty()) {
            throw new IllegalArgumentException(
                    "Special professions cannot have levels."
            );
        }
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<ProfessionLevel> getLevels() {
        return levels;
    }

    public boolean isSpecial() {
        return type == Type.SPECIAL;
    }

    public boolean isNormal() {
        return type == Type.NORMAL;
    }
}
