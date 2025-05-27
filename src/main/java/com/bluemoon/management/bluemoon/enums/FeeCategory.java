package com.bluemoon.management.bluemoon.enums;

public enum FeeCategory {
    ManagementService {
        @Override
        public String toString() {
            return "ManagementService";
        }
    },
    Parking {
        @Override
        public String toString() {
            return "Parking";
        }
    },
    Utility {
        @Override
        public String toString() {
            return "Utility";
        }
    },
    Contribution {
        @Override
        public String toString() {
            return "Contribution";
        }
    },
    Other {
        @Override
        public String toString() {
            return "Other";
        }
    }
}
