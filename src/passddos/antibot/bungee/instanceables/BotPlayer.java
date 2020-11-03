package twolovers.antibot.bungee.instanceables;

import java.util.Collection;
import java.util.HashSet;

public class BotPlayer {
    private final Collection<String> accounts = new HashSet<>();
    private final Collection<String> totalAccounts = new HashSet<>();
    private final String hostString;
    private String lastNickname = "";
    private long lastPing = 0, lastConnection = 0, lastTimeZeroPPS = System.currentTimeMillis(),
            lastTimeZeroCPS = System.currentTimeMillis(), lastTimeZeroJPS = System.currentTimeMillis();
    private int pps = 0, cps = 0, jps = 0, repings = 0, reconnects = 0, switchs = 0;
    private boolean settings = true;

    public BotPlayer(final String hostString) {
        this.hostString = hostString;
    }

    public boolean isSettings() {
        return settings;
    }

    public int getJPS() {
        final long currentTimeMillis = System.currentTimeMillis();

        if (this.jps == 0) {
            this.lastTimeZeroJPS = currentTimeMillis;
        } else if (currentTimeMillis - this.lastTimeZeroJPS >= 1000) {
            this.jps = 0;
        }

        return jps;
    }

    public void setJPS(final int jps) {
        this.jps = jps;
    }

    public int getCPS() {
        final long currentTimeMillis = System.currentTimeMillis();

        if (this.cps == 0) {
            this.lastTimeZeroCPS = currentTimeMillis;
        } else if (currentTimeMillis - this.lastTimeZeroCPS >= 1000) {
            this.cps = 0;
        }

        return cps;
    }

    public void setCPS(final int cps) {
        this.cps = cps;
    }

    public int getPPS() {
        final long currentTimeMillis = System.currentTimeMillis();

        if (this.pps == 0) {
            this.lastTimeZeroPPS = currentTimeMillis;
        } else if (currentTimeMillis - this.lastTimeZeroPPS >= 1000) {
            this.pps = 0;
        }

        return pps;
    }

    public void setPPS(final int pps) {
        this.pps = pps;
    }

    public long getLastConnection() {
        return lastConnection;
    }

    public void setLastConnection(final long lastConnection) {
        this.lastConnection = lastConnection;
    }

    public long getLastPing() {
        return lastPing;
    }

    public void setLastPing(final long lastPing) {
        this.lastPing = lastPing;
    }

    public Collection<String> getAccounts() {
        return accounts;
    }

    public int getTotalAccounts() {
        return totalAccounts.size();
    }

    public void addAccount(final String playerName) {
        if (!accounts.contains(playerName)) {
            accounts.add(playerName);
            totalAccounts.add(playerName);
        }
    }

    public void removeAccount(final String playerName) {
        accounts.remove(playerName);
    }

    public void setSettings(final boolean settings) {
        this.settings = settings;
    }

    public int getRepings() {
        return this.repings;
    }

    public void setRepings(final int repings) {
        this.repings = repings;
    }

    public int getReconnects() {
        return this.reconnects;
    }

    public void setReconnects(final int reconnects) {
        this.reconnects = reconnects;
    }

    public int getSwitchs() {
        return this.switchs;
    }

    public void addSwitch() {
        this.switchs += 1;
    }

    public void resetSwitchs() {
        this.switchs = 0;
    }

    public String getHostAddress() {
        return hostString;
    }

    public String getLastNickname() {
        return lastNickname;
    }

    public void setLastNickname(final String nickname) {
        if (nickname == null) {
            this.lastNickname = "";
        } else {
            this.lastNickname = nickname;
        }
    }
}