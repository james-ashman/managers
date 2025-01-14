/*
 * Copyright contributors to the Galasa project
 */
package dev.galasa.githubissue.internal.properties;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import dev.galasa.framework.spi.IConfigurationPropertyStoreService;
import dev.galasa.githubissue.GitHubIssueManagerException;

@Component(service = GitHubIssuePropertiesSingleton.class, immediate = true)
public class GitHubIssuePropertiesSingleton {
	
	private static GitHubIssuePropertiesSingleton singletonInstance;

    private static void setInstance(GitHubIssuePropertiesSingleton instance) {
        singletonInstance = instance;
    }
    
    private IConfigurationPropertyStoreService cps;
    
    @Activate
    public void activate() {
        setInstance(this);
    }
    
    @Deactivate
    public void deacivate() {
        setInstance(null);
    }
    
    public static IConfigurationPropertyStoreService cps() throws GitHubIssueManagerException {
        if (singletonInstance != null) {
            return singletonInstance.cps;
        }
        
        throw new GitHubIssueManagerException("Attempt to access manager CPS before it has been initialised");
    }
    
    public static void setCps(IConfigurationPropertyStoreService cps) throws GitHubIssueManagerException {
        if (singletonInstance != null) {
            singletonInstance.cps = cps;
            return;
        }
        
        throw new GitHubIssueManagerException("Attempt to set manager CPS before instance created");
    }

}
