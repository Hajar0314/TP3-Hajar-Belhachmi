package ma.emsi.hajarbelhachmi.tp3hajarbelhachmi.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.emsi.hajarbelhachmi.tp3hajarbelhachmi.llm.LlmClientForGuideTouristique;

/**
 * Ressource REST du guide touristique.
 */
@Path("/guide")
public class GuideTouristiqueResource {

    @Inject
    private LlmClientForGuideTouristique llmClient;

    /**
     * Retourne les principaux endroits à visiter dans une ville ou un pays,
     * ainsi que le prix moyen d'un repas, en JSON.
     *
     * @param villeOuPays le nom de la ville ou du pays
     * @return une réponse HTTP contenant le document JSON du LLM
     */
    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response endroitsDeVisite(@PathParam("ville_ou_pays") String villeOuPays) {
        String reponseJson = llmClient.guider(villeOuPays);
        return Response.ok(reponseJson).build();
    }
}