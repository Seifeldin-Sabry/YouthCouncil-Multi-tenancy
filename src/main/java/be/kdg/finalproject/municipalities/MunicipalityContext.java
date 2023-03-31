package be.kdg.finalproject.municipalities;

public class MunicipalityContext {
	private static final ThreadLocal<String> currentMunicipality = new ThreadLocal<>();
	private static final ThreadLocal<Long> currentMunicipalityId = new ThreadLocal<>();

	public static String getCurrentMunicipality() {
		return currentMunicipality.get();
	}

	public static void setCurrentMunicipality(String tenant) {
		currentMunicipality.set(tenant);
	}

	public static Long getCurrentMunicipalityId() {
		return currentMunicipalityId.get();
	}

	public static void setCurrentMunicipalityId(Long tenantId) {
		currentMunicipalityId.set(tenantId);
	}

	public static void clear() {
		currentMunicipalityId.set(null);
		currentMunicipality.set(null);
	}
}

