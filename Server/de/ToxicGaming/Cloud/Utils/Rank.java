package de.ToxicGaming.Cloud.Utils;

public enum Rank {
	SPIELER(10, "Spieler", "§7", ""),
	PREMIUM(20, "Premium", "§6", ""),
	VIP(30, "VIP", "§5", "YouTube"),
	DESIGNER(40, "Designer", "§8", "Design"),
	BUILDER(50, "Builder", "§e", "Builder"),
	HEADBUILDER(60, "Head-Builder", "§e", "H-Builder"),
	SUPPORTER(70, "Supporter", "§a", "Sup"),
	MODERATOR(80, "Moderator", "§c", "Mod"),
	SRMODERATOR(90, "Moderator", "§c", "SrMod"),
	ENTWICKLER(100, "Entwickler", "§b", "Dev"),
	COMMUNITY_ADMIN(100, "Community-Admin", "§c", "C-Admin"),
	ADMINISTRATOR(110, "Administrator", "§4", "Admin");
	private int access_level;
	private String rankName;
	private String rankColor;
	private String shortName;
	
	Rank(int access_level, String rankName, String rankColor, String shortName) {
		this.access_level = access_level;
		this.rankName = rankName;
		this.rankColor = rankColor;
		this.shortName = shortName;
	}
	public int getAccesslevel() {
		return access_level;
	}
	public void setAccesslevel(int access_level) {
		this.access_level = access_level;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	public String getRankColor() {
		return rankColor;
	}
	public void setRankColor(String rankColor) {
		this.rankColor = rankColor;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public boolean isInTeam() {
		return getAccesslevel() >=40;
	}
	public static String getRanks() {
        StringBuilder stringBuilder = new StringBuilder();
        int current = 1;
        for (Rank rank : values()) {
            if(current == values().length) {
                stringBuilder.append(rank.getRankColor() + rank.getRankName());
            } else {
                stringBuilder.append( rank.getRankColor() + rank.getRankName() + ", ");
            }
            current++;
        }
        return stringBuilder.toString();
    }

    public static Rank fromStringExact( String rank ) {
        return fromString( rank );
    }
    public static Rank fromString(String getRank) {
        for (Rank rank : values()) {
            if(rank.name().equalsIgnoreCase(getRank)) {
                return rank;
            }
        }
        return Rank.SPIELER;
    }
    public boolean isHigherLevel(Rank r) {
        return getAccesslevel() > r.getAccesslevel();
    }
    public boolean isHigherEqualsLevel(Rank r) {
        return getAccesslevel() >= r.getAccesslevel();
    }
    public boolean equalsLevel(Rank r) {
        return getAccesslevel() == r.getAccesslevel();
    }
    public boolean isLowerEqualsLevel(Rank r) {
        return getAccesslevel() <= r.getAccesslevel();
    }
    public boolean isLowerLevel(Rank r) {
        return getAccesslevel() < r.getAccesslevel();
    }
}
