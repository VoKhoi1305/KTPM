package com.bluemoon.management.bluemoon.enums;

public enum VehicleStatus {
    PendingApproval{
        @Override
        public String toString() {
            return "PendingApproval";
        }
    }, Registered{
        @Override
        public String toString() {
            return "Registered";
        }
    }, Deregistered{
        @Override
        public String toString() {
            return "Deregistered";
        }
    }, Suspended{
        @Override
        public String toString() {
            return "Suspended";
        }
    }
}
