package com.bluemoon.management.bluemoon.enums;

public enum CalculationMethod {
    ByArea {
        @Override
        public String toString() {
            return "ByArea";
        }
    },
    PerApartment {
        @Override
        public String toString() {
            return "PerApartment";
        }
    },
    PerVehicle {
        @Override
        public String toString() {
            return "PerVehicle";
        }
    },
    PerConsumption {
        @Override
        public String toString() {
            return "PerConsumption";
        }
    },
    Fixed {
        @Override
        public String toString() {
            return "Fixed";
        }
    },
    Manual {
        @Override
        public String toString() {
            return "Manual";
        }
    }
}
