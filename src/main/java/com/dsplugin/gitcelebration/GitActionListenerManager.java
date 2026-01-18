package com.dsplugin.gitcelebration;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import git4idea.GitVcs;
import git4idea.repo.GitRepository;
import git4idea.repo.GitRepositoryManager;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Manager che gestisce i listener per le azioni Git
 */
public class GitActionListenerManager implements ProjectManagerListener {
    
    private final Project project;
    private final Map<GitRepository, GitActionListener> listeners = new HashMap<>();
    
    private static final Map<Project, GitActionListenerManager> instances = new HashMap<>();
    
    public GitActionListenerManager(@NotNull Project project) {
        this.project = project;
    }
    
    public static GitActionListenerManager getInstance(@NotNull Project project) {
        return instances.computeIfAbsent(project, GitActionListenerManager::new);
    }
    
    public void registerListeners() {
        // Registra listener per repository Git
        GitVcs vcs = GitVcs.getInstance(project);
        if (vcs != null) {
            GitRepositoryManager repoManager = vcs.getRepositoryManager();
            if (repoManager != null) {
                for (GitRepository repo : repoManager.getRepositories()) {
                    GitActionListener listener = new GitActionListener(project);
                    repoManager.addListener(listener);
                    listeners.put(repo, listener);
                }
            }
        }
    }
    
    @Override
    public void projectClosing(@NotNull Project project) {
        if (this.project.equals(project)) {
            // Rimuovi listener
            GitVcs vcs = GitVcs.getInstance(project);
            if (vcs != null) {
                GitRepositoryManager repoManager = vcs.getRepositoryManager();
                if (repoManager != null) {
                    listeners.forEach((repo, listener) -> {
                        repoManager.removeListener(listener);
                    });
                }
            }
            listeners.clear();
            instances.remove(project);
        }
    }
}

