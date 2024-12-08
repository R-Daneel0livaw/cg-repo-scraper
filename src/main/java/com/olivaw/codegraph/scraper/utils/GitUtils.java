package com.olivaw.codegraph.scraper.utils;

import com.olivaw.codegraph.scraper.model.GitActionConfig;
import com.olivaw.codegraph.scraper.service.GitAction;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class GitUtils {

    public static void performGitAction(GitActionConfig config, GitAction action) {
        CloneCommand cloneCommand = getCloneCommand(config);

        try (Git git = cloneCommand.call()) {
            action.execute(git);
        } catch (GitAPIException e) {
            throw new RuntimeException("Failed to perform Git operation: " + e.getMessage(), e);
        } finally {
            deleteDirectory(config.getTargetDirectory());
        }
    }

    public static void performGitAction(GitActionConfig config) {
        CloneCommand cloneCommand = getCloneCommand(config);

        try (Git git = cloneCommand.call()) {
            System.out.println("Repository cloned successfully to: " + config.getTargetDirectory().getAbsolutePath());
        } catch (GitAPIException e) {
            throw new RuntimeException("Failed to perform Git operation: " + e.getMessage(), e);
        } finally {
            deleteDirectory(config.getTargetDirectory());
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

    private static void deleteDirectory(File directory) {
        if (directory.exists()) {
            try (var paths = Files.walk(directory.toPath())) {
                paths.sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
                System.out.println("Deleted temporary directory: " + directory.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Failed to delete temporary directory: " + directory.getAbsolutePath());
            }
        }
    }
}
