package br.com.sennatech.sddo.claims.util;

public class TransformationUtil {

    private TransformationUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Long claimIdToLong(String claimId) {
        return Long
                .valueOf((claimId.contains("CLA-")) ? claimId.replace("CLA-", "") : claimId);
    }
}
