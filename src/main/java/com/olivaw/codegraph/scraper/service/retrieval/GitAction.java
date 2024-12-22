package com.olivaw.codegraph.scraper.service.retrieval;

import com.olivaw.codegraph.scraper.exception.GitActionException;
import com.olivaw.codegraph.scraper.model.GitActionResult;
import org.eclipse.jgit.api.Git;

@FunctionalInterface
public interface GitAction<T> {
    GitActionResult<T> execute(Git git) throws GitActionException;
}
