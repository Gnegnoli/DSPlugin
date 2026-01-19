package com.dsplugin.gitcelebration;

import com.intellij.openapi.vcs.CheckinProjectPanel;
import com.intellij.openapi.vcs.checkin.CheckinHandler;
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
    
    public void checkinSuccessful() {
        // Dopo un commit riuscito, celebra!
        celebrationManager.celebrate("commit");
    }
    
    public void checkinFailed() {
        // Commit fallito, nessuna celebrazione
    }
}

