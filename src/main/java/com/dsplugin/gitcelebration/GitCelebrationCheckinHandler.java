package com.dsplugin.gitcelebration;

import com.intellij.openapi.vcs.CheckinProjectPanel;
import com.intellij.openapi.vcs.checkin.CheckinHandler;
import com.intellij.openapi.vcs.ui.Refreshable;
import org.jetbrains.annotations.NotNull;

/**
 * Handler per il check-in che attiva la celebrazione
 */
public class GitCelebrationCheckinHandler extends CheckinHandler {
    
    private final CheckinProjectPanel panel;
    private final CelebrationManager celebrationManager;
    
    public GitCelebrationCheckinHandler(@NotNull CheckinProjectPanel panel,
                                       @NotNull CelebrationManager celebrationManager) {
        this.panel = panel;
        this.celebrationManager = celebrationManager;
    }
    
    @Override
    public ReturnResult beforeCheckin(CommitExecutor executor, Refreshable refreshable) {
        // Prima del commit, non facciamo nulla
        return ReturnResult.COMMIT;
    }
    
    @Override
    public void checkinSuccessful() {
        // Dopo un commit riuscito, celebra!
        celebrationManager.celebrate("commit");
    }
    
    @Override
    public void checkinFailed() {
        // Commit fallito, nessuna celebrazione
    }
}

