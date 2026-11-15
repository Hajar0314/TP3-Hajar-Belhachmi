package ma.emsi.hajarbelhachmi.tp3hajarbelhachmi.llm;

import dev.langchain4j.service.SystemMessage;

/**
 * Interface implémentée par LangChain4j (AiServices) pour dialoguer avec le LLM.
 */
public interface GuideTouristique {

    @SystemMessage("""
            Tu es un guide touristique. Quand on te donne le nom d'une ville ou d'un pays,
            tu indiques les 2 principaux endroits à visiter dans ce lieu, ainsi que le prix
            moyen d'un repas dans la devise du pays.
            N'utilise pas Markdown, réponds uniquement avec du JSON, exactement dans ce format :
            {
              "ville_ou_pays": "nom de la ville ou du pays",
              "endroits_a_visiter": ["endroit 1", "endroit 2"],
              "prix_moyen_repas": "<prix> <devise du pays>"
            }
            """)
    String guider(String lieu);
}