package com.sefiktemel.jiradashboard.resource;

import com.atlassian.jira.rest.client.api.AuthenticationHandler;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueType;
import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import io.atlassian.util.concurrent.Promise;
import io.quarkus.runtime.StartupEvent;
import org.apache.camel.component.jira.oauth.JiraOAuthAuthenticationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class JiraResource {
    private final static Logger applicationLogger = LoggerFactory.getLogger(JiraResource.class);

    JiraRestClient jiraRestClient;

    void onStart(@Observes StartupEvent ev) throws URISyntaxException {
        applicationLogger.info("JiraResource Startup!");
        BasicHttpAuthenticationHandler autHandler = new BasicHttpAuthenticationHandler("sefiktemel@gmail.com", "password");
        AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        //jiraRestClient = factory.createWithBasicHttpAuthentication(new URI("https://stemel.atlassian.net/"), "sefiktemel@gmail.com", "password");

        AuthenticationHandler handler = new JiraOAuthAuthenticationHandler("consumerKey,","verificationCode","privateKey","accessToken","jiraUrl");

        jiraRestClient = factory.createWithAuthenticationHandler(new URI("https://stemel.atlassian.net/"), handler);

        applicationLogger.info("JiraResource Startup end!");
    }

    public Promise<Issue> getIssue(String s){
        return jiraRestClient.getIssueClient().getIssue(s);
    }

    public Promise<Iterable<BasicProject>> getAllProjects(){
        return jiraRestClient.getProjectClient().getAllProjects();
    }

    public List<IssueType> getIssueList(){
        return new ArrayList<>();
    }

}
