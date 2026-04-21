package io.github.aldengregory.greenspaces.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.cloud.translate.Translate.TranslateOption;

@Service
public class TranslationService {
    private final Translate translator;

    public TranslationService(
        @Value("${app.translate_service_credentials}") String credentialsBase64
    ) {
        byte[] decodedCredentials = Base64.getDecoder().decode(credentialsBase64);
        ByteArrayInputStream credentialsStream = new ByteArrayInputStream(decodedCredentials);
        GoogleCredentials credentials;

        // Decode byte stream into credentials.
        try {
            credentials = GoogleCredentials.fromStream(credentialsStream);
        } catch (IOException e) {
            throw new RuntimeException(
                "Failed to process input credentials. These should " + 
                "be provided through the TRANSLATE_SERVICE_CREDENTIALS " + 
                "environment variable in production.");
        }

        // Build translator.
        translator = TranslateOptions.newBuilder()
            .setCredentials(credentials)
            .build()
            .getService();
    }

    public String translateString(String input, String targetLanguage) {
        // All translations are from English for this app.
        Translation result = translator.translate(
            input, 
            TranslateOption.sourceLanguage("en"),
            TranslateOption.targetLanguage(targetLanguage),
            // Prevents HTML encodings for symbols.
            TranslateOption.format("text")
        );

        return result.getTranslatedText();
    }

    public List<String> translateStrings(List<String> inputs, String targetLanguage) {
        List<Translation> translations = translator.translate(
            inputs, 
            TranslateOption.sourceLanguage("en"),
            TranslateOption.targetLanguage(targetLanguage),
            // Prevents HTML encodings for symbols.
            TranslateOption.format("text")
        );

        // Convert Translations to Strings.
        List<String> results = new ArrayList<>();

        for (Translation translation : translations) {
            results.add(translation.getTranslatedText());
        }

        return results;
    }
}
