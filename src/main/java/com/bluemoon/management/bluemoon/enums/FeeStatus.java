package com.bluemoon.management.bluemoon.enums;

public enum FeeStatus {
    Draft {
        @Override
        public String toString() {
            return "Draft";
        }
    },
    Active {
        @Override
        public String toString() {
            return "Active";
        }
    },
    Suspended {
        @Override
        public String toString() {
            return "Suspended";
        }
    },
    Ended {
        @Override
        public String toString() {
            return "Ended";
        }
    }
}
