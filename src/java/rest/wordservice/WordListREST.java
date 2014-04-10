package rest.wordservice;

import dao.StudentDAO;
import dao.WordDAO;
import entity.Student;
import java.net.URI;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Payload;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Gábor
 */
@Path("words")
@Stateless
public class WordListREST {

    @Inject
    private WordDAO wordDao;
    
    @Inject
    private StudentDAO studentDao;
    
    @Context
    private UriInfo uriInfo;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public WordListResource findAll(@QueryParam("search") String part) {
        if(part==null){
            return new WordListResource(wordDao.getWordList());
        }
        else{
            return new WordListResource(wordDao.searchWord(part));
        }
    }
    
    @Path("{word}")
    public WordREST getUserRest(@PathParam("word") String source) {
        return new WordREST(wordDao, source);
    }
    
    
    
}
