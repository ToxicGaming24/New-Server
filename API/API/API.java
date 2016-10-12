package API;

import MySQL.MySQL;
import lombok.Getter;
import lombok.Setter;

public class API {
	@Getter
	@Setter
	private static MySQL sqlPool;
}
