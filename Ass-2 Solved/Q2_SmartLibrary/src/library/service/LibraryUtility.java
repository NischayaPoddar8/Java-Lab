package library.service;

import library.model.LibraryResource;
import library.util.InputValidator;

public class LibraryUtility {

    public static double calculateFine(LibraryResource resource, int overdueDays) {
        if (!InputValidator.validateFineDays(overdueDays)) {
            return 0;
        }
        return resource.calculateFine(overdueDays);
    }
}
