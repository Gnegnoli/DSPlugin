package com.dsplugin.gitcelebration;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.CheckinProjectPanel;
import com.intellij.openapi.vcs.changes.CommitContext;
import com.intellij.openapi.vcs.checkin.CheckinHandler;
import com.intellij.openapi.vcs.checkin.CheckinHandlerFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Factory per creare CheckinHandler che intercetta i commit Git
 */
public class GitCelebrationCheckinHandlerFactory extends CheckinHandlerFactory {
    
    @NotNull
    @Override
    public CheckinHandler createHandler(@NotNull CheckinProjectPanel panel, @NotNull CommitContext commitContext) {
        Project project = panel.getProject();
        CelebrationManager celebrationManager = CelebrationManager.getInstance(project);
        return new GitCelebrationCheckinHandler(panel, celebrationManager);
    }
}

