package com.sefiktemel.jiradashboard.resource;

import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.IssueType;
import com.sefiktemel.jiradashboard.model.api.IssueDto;
import com.sefiktemel.jiradashboard.model.api.ProjectDto;
import com.sefiktemel.jiradashboard.model.api.res.ListIssueRes;
import com.sefiktemel.jiradashboard.model.api.res.ListProjectRes;
import io.atlassian.util.concurrent.Promise;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/dashboard")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DashboardResource {
    private final static Logger applicationLogger = LoggerFactory.getLogger(DashboardResource.class);

    @Inject
    JiraResource jiraResource;

    void onStart(@Observes StartupEvent ev) {
        applicationLogger.info("The application has started");
    }
    void onStop(@Observes ShutdownEvent ev) {
        applicationLogger.info("The application is stopping...");
    }

    @Path("listIssues")
    @POST
    public ListIssueRes listIssues(){//@HeaderParam(HEADER_PARAM_SESSION_ID) String sessionId){
        ListIssueRes res = new ListIssueRes();
        List<IssueType> issueTypeList = jiraResource.getIssueList();
        for(IssueType it : issueTypeList){
            IssueDto i = new IssueDto();
            i.setId(it.getId());
            i.setName(it.getName());
            i.setDescription(it.getDescription());
            i.setPath(it.getSelf().getPath());
            res.getIssues().add(i);
        }
        return res;
    }

    @Path("listProjects")
    @POST
    public ListProjectRes listProjects() throws ExecutionException, InterruptedException {//@HeaderParam(HEADER_PARAM_SESSION_ID) String sessionId){
        applicationLogger.info("listProjects start");
        ListProjectRes res = new ListProjectRes();
        Promise<Iterable<BasicProject>> allProjects = jiraResource.getAllProjects();
        Iterable<BasicProject> basicProjects = allProjects.get();

        for(BasicProject bp : basicProjects){
            ProjectDto p = new ProjectDto();
            p.setId(bp.getId());
            p.setName(bp.getName());
            p.setKey(bp.getKey());
            p.setPath(bp.getSelf().getPath());
            res.getProjects().add(p);
        }
        applicationLogger.info("basicProjects size: {}",res.getProjects().size());
        applicationLogger.info("listProjects end");
        return res;
    }

}
