package com.olivaw.codegraph.scraper.service;

import com.olivaw.codegraph.scraper.model.GitActionResult;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

@FunctionalInterface
public interface GitAction<T> {
    GitActionResult<T> execute(Git git) throws GitAPIException;
}
