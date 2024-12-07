package com.olivaw.codegraph.scraper.utils;

import com.olivaw.codegraph.scraper.model.GitActionConfig;
import com.olivaw.codegraph.scraper.service.GitAction;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

public class GitUtils {

    public static void performGitAction(GitActionConfig config, GitAction action) {
        CloneCommand cloneCommand = getCloneCommand(config);

        try (Git git = cloneCommand.call()) {
            action.execute(git);
        } catch (GitAPIException e) {
            throw new RuntimeException("Failed to perform Git operation: " + e.getMessage(), e);
        }
    }

    public static void performGitAction(GitActionConfig config) {
        CloneCommand cloneCommand = getCloneCommand(config);

        try (Git git = cloneCommand.call()) {
            System.out.println("Repository cloned successfully to: " + config.getTargetDirectory().getAbsolutePath());
        } catch (GitAPIException e) {
            throw new RuntimeException("Failed to perform Git operation: " + e.getMessage(), e);
        }
    }

    private static CloneCommand getCloneCommand(GitActionConfig config) {
        CloneCommand cloneCommand = Git.cloneRepository()
                .setURI(config.getRepoLocation())
                .setDirectory(config.getTargetDirectory());

        if (config.getDepth() > 0) {
            cloneCommand.setDepth(config.getDepth());
        }
        return cloneCommand;
    }
}
