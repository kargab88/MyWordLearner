package rest.wordservice;

import dao.WordDAO;
import entity.Word;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Gábor
 */
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class WordREST {

    @Context
    private UriInfo uriInfo;

    private WordDAO wordDao;
    private String source;

    public WordREST() {

    }

    public WordREST(WordDAO wordDao, String source) {
        this.wordDao = wordDao;
        this.source = source;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getTranslations() {
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        Word word = wordDao.findBySource(source);
        List<String> translations = word.getTrans();
        for (String string : translations) {
            arrBuilder.add(string);
        }

        return Response.ok(arrBuilder.build())
                .type(MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8"))
                .build();
    }

    @GET
    @Path("statistics")
    @Produces({MediaType.APPLICATION_XML})
    public WordStatResource getStat() {
        return new WordStatResource(wordDao.findBySource(source));
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(JsonArray translations) {
        Word w = wordDao.findBySource(source);
        List<String> list = new ArrayList<>();
        for (JsonValue jsonvalue : translations) {
            list.add(jsonvalue.toString());
        }
        wordDao.updateTranslations(list, w);
    }

    @DELETE
    public void deleteWord() {
        Word word = wordDao.findBySource(source);
        wordDao.removeById(word.getId());
    }

}
