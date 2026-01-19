package com.dsplugin.gitcelebration;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.ex.AnActionListener;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * Intercetta le azioni (menu/shortcut) di Git/VCS usando gli actionId.
 * Funziona senza importare classi git4idea.*.
 */
public class GitActionAnActionListener implements AnActionListener {

    private final Project project;
    private final CelebrationManager celebrationManager;

    public GitActionAnActionListener(@NotNull Project project) {
        this.project = project;
        this.celebrationManager = CelebrationManager.getInstance(project);
    }

    public void afterActionPerformed(@NotNull AnAction action,
                                     @NotNull DataContext dataContext,
                                     @NotNull AnActionEvent event) {
        String actionId = ActionManagerCompat.getActionId(action, event);
        if (actionId == null) return;

        String type = GitActionIds.toCelebrationType(actionId);
        if (type == null) return;

        // Evita doppio trigger: il commit lo prendiamo dal CheckinHandler (success/fail reale).
        if ("commit".equals(type)) return;

        celebrationManager.celebrate(type);
    }

    public void beforeActionPerformed(@NotNull AnAction action,
                                      @NotNull DataContext dataContext,
                                      @NotNull AnActionEvent event) {
        // niente
    }

}

