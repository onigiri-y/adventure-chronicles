package com.adventurechronicles.profession;

public record ProfessionLevel(
        int level,
        String description
) {
    public ProfessionLevel {
        if (level < 1 || level > 3) {
            throw new IllegalArgumentException(
                    "Profession level must be between 1 and 3."
            );
        }
    }
}
