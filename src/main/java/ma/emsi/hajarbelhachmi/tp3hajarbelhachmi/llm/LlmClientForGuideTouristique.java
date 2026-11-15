package ma.emsi.hajarbelhachmi.tp3hajarbelhachmi.llm;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;

/**
 * Gère l'interface avec l'API de Gemini via LangChain4j.
 * Portée application : un seul service partagé par toutes les requêtes REST.
 */
@ApplicationScoped
public class LlmClientForGuideTouristique implements Serializable {

    private GuideTouristique guideTouristique;

    @PostConstruct
    public void init() {
        // Clé secrète dans une variable d'environnement
        String key = System.getenv("GEMINI_KEY");

        ChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(key)
                .modelName("gemini-3.5-flash-lite")
                .temperature(0.3)
                .responseFormat(ResponseFormat.JSON)
                .logRequestsAndResponses(true)
                .build();

        this.guideTouristique = AiServices.create(GuideTouristique.class, model);
    }

    /**
     * Demande au LLM les informations touristiques sur un lieu.
     * @param lieu ville ou pays
     * @return la réponse du LLM (JSON sous forme de String)
     */
    public String guider(String lieu, int nb) {
        return guideTouristique.guider(lieu, nb);
    }
}