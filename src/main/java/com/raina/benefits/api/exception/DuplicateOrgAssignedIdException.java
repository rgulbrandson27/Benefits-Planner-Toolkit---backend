package com.raina.benefits.api.exception;

public class DuplicateOrgAssignedIdException extends RuntimeException {
    public DuplicateOrgAssignedIdException(String orgAssignedId) {
        super("orgAssignedId already exists: " + orgAssignedId);
    }
}
