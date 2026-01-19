package com.dsplugin.gitcelebration;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import org.jetbrains.annotations.NotNull;

/**
 * Attività di startup che registra i listener per le azioni Git
 */
public class GitCelebrationStartupActivity implements StartupActivity {
    
    @Override
    public void runActivity(@NotNull Project project) {
        // Registra listener per intercettare azioni Git/VCS (senza dipendenze git4idea.*)
        ApplicationManager.getApplication().invokeLater(() -> {
            GitActionAnActionListener listener = new GitActionAnActionListener(project);
            // Il Disposable (project) gestisce la rimozione automatica
            ActionManager.getInstance().addAnActionListener(listener, project);
        });
    }
}

