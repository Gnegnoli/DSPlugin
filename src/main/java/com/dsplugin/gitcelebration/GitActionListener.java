package com.dsplugin.gitcelebration;

import com.intellij.openapi.project.Project;
import git4idea.repo.GitRepository;
import git4idea.repo.GitRepositoryChangeListener;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Listener che intercetta i cambiamenti nei repository Git
 */
public class GitActionListener implements GitRepositoryChangeListener {
    
    private final Project project;
    private final CelebrationManager celebrationManager;
    private final Set<String> mergeStates = new HashSet<>();
    
    public GitActionListener(@NotNull Project project) {
        this.project = project;
        this.celebrationManager = CelebrationManager.getInstance(project);
    }
    
    @Override
    public void repositoryChanged(@NotNull GitRepository repository) {
        // Questo viene chiamato quando il repository cambia
        // Possiamo usarlo per rilevare alcune operazioni Git
        
        // Rileva merge completato: se prima c'erano merge heads e ora non ci sono più
        boolean hasMergeHeads = repository.getMergeHeads().size() > 0;
        String repoPath = repository.getRoot().getPath();
        
        if (hasMergeHeads) {
            // Merge in corso - salva lo stato
            mergeStates.add(repoPath);
        } else if (mergeStates.contains(repoPath)) {
            // Merge completato! Prima c'erano merge heads, ora non ci sono più
            mergeStates.remove(repoPath);
            celebrationManager.celebrate("merge");
        }
        
        // Rileva anche altri stati del repository per altre operazioni
        git4idea.repo.GitRepository.State state = repository.getState();
        
        // Rileva rebase completato
        if (state == git4idea.repo.GitRepository.State.NORMAL && 
            mergeStates.contains(repoPath + "_rebase")) {
            mergeStates.remove(repoPath + "_rebase");
            celebrationManager.celebrate("rebase");
        } else if (state == git4idea.repo.GitRepository.State.REBASING) {
            mergeStates.add(repoPath + "_rebase");
        }
    }
}

