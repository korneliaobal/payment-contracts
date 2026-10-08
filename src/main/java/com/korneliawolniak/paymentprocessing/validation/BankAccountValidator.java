package com.korneliawolniak.paymentprocessing.validation;

import java.util.Locale;

public final class BankAccountValidator {
  private BankAccountValidator() {}

  public static String normalize(String value) {
    if (value == null) return "";
    String normalized = value.replaceAll("(?U)\\s", "").toUpperCase(Locale.ROOT);
    return normalized.matches("[0-9]{26}") ? "PL" + normalized : normalized;
  }

  public static boolean isValid(String value) {
    String account = normalize(value);
    if (!account.matches("PL[0-9]{26}")) return false;
    String digits = account.substring(4) + "2521" + account.substring(2, 4);
    int remainder = 0;
    for (int i = 0; i < digits.length(); i++) {
      remainder = (remainder * 10 + digits.charAt(i) - '0') % 97;
    }
    return remainder == 1;
  }
}
