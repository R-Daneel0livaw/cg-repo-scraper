package com.olivaw.codegraph.scraper.utils;

import com.olivaw.codegraph.scraper.model.GitActionConfig;
import com.olivaw.codegraph.scraper.model.GitActionResult;
import com.olivaw.codegraph.scraper.service.retrieval.FetchAllFilesAction;
import com.olivaw.codegraph.scraper.service.retrieval.GitAction;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class GitUtils {

    public static GitActionResult<List<File>> performGitAction(GitActionConfig config) {
        var cloneCommand = getCloneCommand(config);
        GitAction<List<File>> action = new FetchAllFilesAction(null);

        try (var git = cloneCommand.call()) {
            System.out.println("Repository cloned successfully to: " + config.getTargetDirectory().getAbsolutePath());
            return action.execute(git);
        } catch (GitAPIException e) {
            throw new RuntimeException("Failed to perform Git operation: " + e.getMessage(), e);
        } finally {
            deleteDirectory(config.getTargetDirectory());
        }
    }

    public static <T> GitActionResult<T> performGitAction(GitActionConfig config, GitAction<T> action) {
        var cloneCommand = getCloneCommand(config);

        try (var git = cloneCommand.call()) {
            return action.execute(git);
        } catch (GitAPIException e) {
            throw new RuntimeException("Failed to perform Git operation: " + e.getMessage(), e);
        } finally {
            deleteDirectory(config.getTargetDirectory());
        }
    }

    private static CloneCommand getCloneCommand(GitActionConfig config) {
        var cloneCommand = Git.cloneRepository()
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
