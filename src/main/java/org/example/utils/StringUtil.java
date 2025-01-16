package org.example.utils;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class StringUtil {
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String convertToSnakeCase(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        // Normalize the string (remove diacritics)
        String normalized = Normalizer.normalize(input.trim(), Normalizer.Form.NFD);

        // Replace whitespace with underscores
        String noWhitespace = WHITESPACE.matcher(normalized).replaceAll("_");

        // Remove all non-latin characters
        String slug = NONLATIN.matcher(noWhitespace).replaceAll("");

        // Clean up multiple underscores and trim leading/trailing underscores
        slug = slug.toLowerCase(Locale.ENGLISH)
                .replaceAll("_+", "_")
                .replaceAll("^_|_$", "");

        // Check for empty result
        if (slug.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be converted to a valid snake_case string");
        }

        return slug;
    }

}
