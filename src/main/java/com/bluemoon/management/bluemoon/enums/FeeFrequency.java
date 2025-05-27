package com.bluemoon.management.bluemoon.enums;

public enum FeeFrequency {
    Monthly {
        @Override
        public String toString() {
            return "Monthly";
        }
    },
    Quarterly {
        @Override
        public String toString() {
            return "Quarterly";
        }
    },
    Annually {
        @Override
        public String toString() {
            return "Annually";
        }
    },
    OneTime {
        @Override
        public String toString() {
            return "OneTime";
        }
    }
}
