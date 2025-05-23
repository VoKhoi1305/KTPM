package com.bluemoon.management.bluemoon.enums;

import jakarta.persistence.EnumType;

public enum ApartmentUsageStatus {
    Occupied {
        @Override
        public String toString() {
            return "Occupied";
        }
    },
    Vacant {
        @Override
        public String toString() {
            return "Vacant";
        }
    },
    UnderRenovation {
        @Override
        public String toString() {
            return "UnderRenovation";
        }
    },

}