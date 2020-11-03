package twolovers.antibot.bungee.module;

import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Pattern;

import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;
import twolovers.antibot.bungee.utils.ConfigUtil;
import twolovers.antibot.shared.extendables.PunishableModule;

public class NicknameModule extends PunishableModule {
	private Collection<String> blacklist = new HashSet<>();
	private Pattern pattern = Pattern.compile(
			"^(Craft|Beach|Actor|Games|Tower|Elder|Mine|Nitro|Worms|Build|Plays|Hyper|Crazy|Super|_Itz|Slime)(Craft|Beach|Actor|Games|Tower|Elder|Mine|Nitro|Worms|Build|Plays|Hyper|Crazy|Super|_Itz|Slime)(11|50|69|99|88|HD|LP|XD|YT)");
	private String lastNickname = "A";

	@Override
	public final void reload(final ConfigUtil configUtil) {
		super.name = "nickname";
		super.reload(configUtil);

		final Configuration configYml = configUtil.getConfiguration("%datafolder%/config.yml");

		punishCommands.clear();
		punishCommands.addAll(configYml.getStringList(name + ".commands"));
		blacklist.clear();
		blacklist.addAll(configYml.getStringList(name + ".blacklist"));
	}

	@Override
	public final boolean meet(final int pps, final int cps, final int jps, final int lastPps, final int lastCps,
			final int lastJps) {
		return this.enabled && (thresholds.meet(pps, cps, jps, lastPps, lastCps, lastJps));
	}

	public boolean check(final Connection connection) {
		if (connection instanceof ProxiedPlayer) {
			final String name = ((ProxiedPlayer) connection).getName();

			if (!name.equals(lastNickname) && name.length() == lastNickname.length()) {
				return true;
			} else {
				final String lowerName = name.toLowerCase();

				for (final String blacklisted : blacklist) {
					if (lowerName.contains(blacklisted)) {
						return true;
					}
				}

				return pattern.matcher(name).find();
			}
		}

		return false;
	}

	public final String getLastNickname() {
		return lastNickname;
	}

	public final void setLastNickname(String nickname) {
		lastNickname = nickname;
	}
}
