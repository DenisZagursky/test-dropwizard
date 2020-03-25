package com.wizard.web.resource;

import io.dropwizard.auth.Auth;
import io.swagger.annotations.Api;
import org.dhatim.dropwizard.jwt.cookie.authentication.DefaultJwtCookiePrincipal;
import org.dhatim.dropwizard.jwt.cookie.authentication.DontRefreshSession;
import org.dhatim.dropwizard.jwt.cookie.authentication.JwtCookiePrincipal;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("v1/auth/")
@Api
public class AuthApi {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DefaultJwtCookiePrincipal login(@Context ContainerRequestContext requestContext, String name) {
        DefaultJwtCookiePrincipal principal = new DefaultJwtCookiePrincipal(name);
        principal.addInContext(requestContext);
        return principal;
    }

    @GET
    @Path("logout")
    public void logout(@Context ContainerRequestContext requestContext) {
        JwtCookiePrincipal.removeFromContext(requestContext);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DefaultJwtCookiePrincipal getPrincipal(@Auth DefaultJwtCookiePrincipal principal) {
        return principal;
    }

    @GET
    @Path("idempotent")
    @Produces(MediaType.APPLICATION_JSON)
    @DontRefreshSession
    public DefaultJwtCookiePrincipal getSubjectWithoutRefreshingSession(@Auth DefaultJwtCookiePrincipal principal) {
        return principal;
    }
}
