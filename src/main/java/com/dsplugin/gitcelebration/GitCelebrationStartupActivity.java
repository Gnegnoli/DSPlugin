package com.dsplugin.gitcelebration;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import org.jetbrains.annotations.NotNull;

/**
 * Attività di startup che registra i listener per le azioni Git
 */
public class GitCelebrationStartupActivity implements StartupActivity {
    
    @Override
    public void runActivity(@NotNull Project project) {
        // Registra i listener per le azioni Git
        ApplicationManager.getApplication().executeOnPooledThread(() -> {
            GitActionListenerManager.getInstance(project).registerListeners();
        });
    }
}

