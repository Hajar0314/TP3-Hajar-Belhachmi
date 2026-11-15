
package ma.emsi.hajarbelhachmi.tp3hajarbelhachmi.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.DefaultValue;
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
     * @param nb le nombre d'endroits à visiter (2 par défaut)
     * @return une réponse HTTP contenant le document JSON du LLM
     */
    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response endroitsDeVisite(
            @PathParam("ville_ou_pays") String villeOuPays,
            @QueryParam("nb") @DefaultValue("2") int nb) {

        String reponseJson = llmClient.guider(villeOuPays, nb);

        Response.ResponseBuilder responseBuilder = Response.ok(reponseJson);

        responseBuilder.header("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        responseBuilder.header("Pragma", "no-cache");
        responseBuilder.header("Expires", "0");
        responseBuilder.header("Access-Control-Allow-Origin", "*");

        return responseBuilder.build();
    }
}
