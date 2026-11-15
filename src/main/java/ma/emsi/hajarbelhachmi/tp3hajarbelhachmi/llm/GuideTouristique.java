package ma.emsi.hajarbelhachmi.tp3hajarbelhachmi.llm;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface GuideTouristique {

    @SystemMessage("""
            Tu es un guide touristique. Quand on te donne le nom d'une ville ou d'un pays
            et un nombre, tu indiques ce nombre de principaux endroits à visiter dans ce lieu,
            ainsi que le prix moyen d'un repas dans la devise du pays.
            N'utilise pas Markdown, réponds uniquement avec du JSON, exactement dans ce format :
            {
              "ville_ou_pays": "nom de la ville ou du pays",
              "endroits_a_visiter": ["endroit 1", "endroit 2"],
              "prix_moyen_repas": "<prix> <devise du pays>"
            }
            La liste "endroits_a_visiter" doit contenir exactement le nombre d'endroits demandé.
            """)
    @UserMessage("Lieu : {{lieu}}. Nombre d'endroits à visiter : {{nb}}.")
    String guider(@V("lieu") String lieu, @V("nb") int nb);
}