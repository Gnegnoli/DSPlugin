package com.dsplugin.gitcelebration;

import com.intellij.openapi.project.Project;
import git4idea.commands.Git;
import git4idea.commands.GitCommand;
import git4idea.commands.GitCommandResult;
import git4idea.commands.GitLineHandler;
import git4idea.repo.GitRepository;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Intercetta i comandi Git a livello di handler
 */
public class GitCommandHandlerInterceptor {
    
    private final Project project;
    private final CelebrationManager celebrationManager;
    
    public GitCommandHandlerInterceptor(@NotNull Project project, 
                                       @NotNull CelebrationManager celebrationManager) {
        this.project = project;
        this.celebrationManager = celebrationManager;
    }
    
    public void register() {
        // Usa un approccio alternativo: intercetta attraverso VcsActions
        // Questo viene fatto attraverso l'intercettazione delle azioni VCS
        GitActionInterceptorV2 interceptor = new GitActionInterceptorV2(project, celebrationManager);
        interceptor.register();
    }
}

