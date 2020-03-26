package com.wizard.web.resource;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.wizard.action.ProjectActionList;
import com.wizard.dao.hibernate.ProjectHibernateDAO;
import com.wizard.domain.Project;
import com.wizard.web.pagination.Page;
import com.wizard.web.pagination.Pageable;
import com.wizard.web.request.ProjectFilterRequest;
import com.wizard.web.request.ProjectRequest;
import com.wizard.web.response.ShortProjectResponse;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import org.dhatim.dropwizard.jwt.cookie.authentication.DefaultJwtCookiePrincipal;
import org.jooq.DSLContext;

import javax.annotation.security.PermitAll;
import javax.ws.rs.BeanParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Path("api/v1/project/")
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public class ProjectApi {


    private final ProjectActionList projectActionList;
    private final ProjectHibernateDAO projectHibernateDAO;

    @Inject
    public ProjectApi(Provider<ProjectActionList> projectActionListProvider,
                      Provider<ProjectHibernateDAO> projectHibernateDAO) {
        this.projectActionList = projectActionListProvider.get();
        this.projectHibernateDAO = projectHibernateDAO.get();
    }

    @GET
    @Path("/all")
    public Page getListOfProject(
            @BeanParam ProjectFilterRequest filter,
            @BeanParam Pageable pageable,
            @Context DSLContext db) {
        return projectActionList.getProjectsByFilter(filter, pageable, db);
    }


    @POST
    @Path("create")
    @UnitOfWork
    public ShortProjectResponse createProject(@BeanParam ProjectRequest source,
                                              @Auth DefaultJwtCookiePrincipal principal) {
        var target = Project.create(source, principal.getName());
        projectHibernateDAO.create(target);
        return ShortProjectResponse.from(target);
    }

    @GET()
    @Path("{id}")
    @UnitOfWork
    public ShortProjectResponse getProject(@PathParam("id") Long id,
                                           @Auth DefaultJwtCookiePrincipal principal) {
        var target = projectHibernateDAO.findById(id)
                .orElseThrow(() -> new WebApplicationException("could not find project by id", BAD_REQUEST));
        projectHibernateDAO.create(target);
        return ShortProjectResponse.from(target);
    }


    @DELETE
    @Path("{id}")
    @UnitOfWork
    public ShortProjectResponse deleteProject(@PathParam("id") Long id,
                                              @Auth DefaultJwtCookiePrincipal principal) {
        var target = projectHibernateDAO.findById(id)
                .orElseThrow(() -> new WebApplicationException("could not find project by id", BAD_REQUEST));
        projectHibernateDAO.delete(target);
        return ShortProjectResponse.from(target);
    }


}
