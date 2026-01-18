package com.dsplugin.gitcelebration;

import com.intellij.openapi.project.Project;
import git4idea.commands.GitCommand;
import git4idea.commands.GitCommandResult;
import git4idea.repo.GitRepository;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Intercetta i comandi Git e attiva le celebrazioni
 */
public class GitActionInterceptor {
    
    private final Project project;
    private final CelebrationManager celebrationManager;
    
    public GitActionInterceptor(@NotNull Project project) {
        this.project = project;
        this.celebrationManager = CelebrationManager.getInstance(project);
    }
    
    public void register() {
        // Intercetta i comandi Git attraverso il GitCommandHandler
        GitCommandHandlerInterceptor interceptor = new GitCommandHandlerInterceptor(project, celebrationManager);
        interceptor.register();
    }
    
    /**
     * Intercetta il risultato di un comando Git
     */
    public void interceptCommand(@NotNull GitCommand command, @NotNull GitCommandResult result, 
                                 @NotNull List<GitRepository> repositories) {
        if (result.success()) {
            String actionType = mapCommandToActionType(command);
            if (actionType != null) {
                celebrationManager.celebrate(actionType);
            }
        }
    }
    
    private String mapCommandToActionType(@NotNull GitCommand command) {
        String commandName = command.name().toLowerCase();
        
        if (commandName.contains("commit")) {
            return "commit";
        } else if (commandName.contains("merge")) {
            return "merge";
        } else if (commandName.contains("push")) {
            return "push";
        } else if (commandName.contains("pull")) {
            return "pull";
        } else if (commandName.contains("rebase")) {
            return "rebase";
        } else if (commandName.contains("checkout")) {
            return "checkout";
        } else if (commandName.contains("branch")) {
            return "branch";
        }
        
        return null;
    }
}

