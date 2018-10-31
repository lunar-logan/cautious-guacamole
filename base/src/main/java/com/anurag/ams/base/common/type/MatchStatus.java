package com.anurag.ams.base.common.type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA on 10/28/18
 *
 * @author Anurag Gautam
 */
public enum MatchStatus {
    COMPLETED(true),
    DRAW(true),
    CANCELLED(true),
    IN_PROGRESS(false, MatchStatus.COMPLETED, MatchStatus.DRAW),
    SCHEDULED(false, MatchStatus.IN_PROGRESS, MatchStatus.CANCELLED);

    private final boolean terminal;
    private final Set<MatchStatus> next;

    MatchStatus(MatchStatus... next) {
        this(false, next);
    }

    MatchStatus(boolean terminal, MatchStatus... next) {
        this.terminal = terminal;
        this.next = new HashSet<>(Arrays.asList(next));
    }

    public boolean isTerminal() {
        return terminal;
    }

    public Set<MatchStatus> getNext() {
        return next;
    }
}
