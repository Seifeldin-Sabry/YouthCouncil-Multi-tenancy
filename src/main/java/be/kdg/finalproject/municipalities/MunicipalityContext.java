package be.kdg.finalproject.municipalities;

import be.kdg.finalproject.domain.platform.Municipality;

public class MunicipalityContext {
	private static final ThreadLocal<String> currentMunicipalityName = new ThreadLocal<>();
	private static final ThreadLocal<Long> currentMunicipalityId = new ThreadLocal<>();
	private static final ThreadLocal<Municipality> currentMunicipality = new ThreadLocal<>();

	public static String getCurrentMunicipalityName() {
		return currentMunicipalityName.get();
	}

	public static void setCurrentMunicipalityName(String tenant) {
		currentMunicipalityName.set(tenant);
	}

	public static Long getCurrentMunicipalityId() {
		return currentMunicipalityId.get();
	}

	public static void setCurrentMunicipalityId(Long tenantId) {
		currentMunicipalityId.set(tenantId);
	}

	public static void clear() {
		currentMunicipalityId.set(null);
		currentMunicipalityName.set(null);
		currentMunicipality.set(null);
	}

	public static Municipality getCurrentMunicipality() {
		return currentMunicipality.get();
	}

	public static void setCurrentMunicipality(Municipality municipality) {
		currentMunicipality.set(municipality);
	}
}

