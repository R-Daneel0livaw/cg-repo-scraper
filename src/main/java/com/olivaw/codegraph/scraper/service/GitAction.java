package com.olivaw.codegraph.scraper.service;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

@FunctionalInterface
public interface GitAction {
    void execute(Git git) throws GitAPIException;
}
