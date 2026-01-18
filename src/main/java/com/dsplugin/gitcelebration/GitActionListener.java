package com.dsplugin.gitcelebration;

import com.intellij.openapi.project.Project;
import git4idea.repo.GitRepository;
import git4idea.repo.GitRepositoryChangeListener;
import org.jetbrains.annotations.NotNull;

/**
 * Listener che intercetta i cambiamenti nei repository Git
 */
public class GitActionListener implements GitRepositoryChangeListener {
    
    private final Project project;
    private final CelebrationManager celebrationManager;
    private String lastState = "";
    
    public GitActionListener(@NotNull Project project) {
        this.project = project;
        this.celebrationManager = CelebrationManager.getInstance(project);
    }
    
    @Override
    public void repositoryChanged(@NotNull GitRepository repository) {
        // Questo viene chiamato quando il repository cambia
        // Possiamo usarlo per rilevare alcune operazioni Git
        String currentState = repository.getState().toString();
        
        // Rileva merge se ci sono merge heads
        if (repository.getMergeHeads().size() > 0 && !lastState.contains("MERGE")) {
            celebrationManager.celebrate("merge");
        }
        
        lastState = currentState;
    }
}

