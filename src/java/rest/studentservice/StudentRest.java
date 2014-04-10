/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rest.studentservice;

import dao.StudentDAO;
import entity.Student;
import java.net.URI;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Gábor
 */
@Path("users")
@Stateless
public class StudentRest {
    
    @Inject
    StudentDAO studentDao;
    
    @Context
    private UriInfo uriInfo;
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public StudentListResource getStudent(){
        return new StudentListResource(studentDao.getStudentsList());
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response persist(StudentPostResource spr) {
            studentDao.registerStudent(spr.getUsername());
            Student student= studentDao.findByUsername(spr.getUsername());
            URI createUserURI = uriInfo.getBaseUriBuilder()
                    .path(StudentRest.class)
                    .path("{userId}")
                    .build(student.getId());

            return Response.created(createUserURI)
                    .entity("Successfully created user")
                    .build();
    }
    
    @DELETE
    @Path("{username}")
    public void removeStudent(@PathParam("username") String username){
        Student s = studentDao.findByUsername(username);
        studentDao.removeById(s.getId());
    }
    
}
