package br.com.sennatech.sddo.claims.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TransformationUtil {

    public Long claimIdToLong(String claimId) {
        return Long
                .valueOf((claimId.contains("CLA-")) ? claimId.replace("CLA-", "") : claimId);
    }
}
