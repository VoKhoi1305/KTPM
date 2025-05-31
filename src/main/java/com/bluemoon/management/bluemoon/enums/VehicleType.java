package com.bluemoon.management.bluemoon.enums;

public enum VehicleType {
    Motorbike{
        @Override
        public String toString() {
            return "Motorbike";
        }
    },
    Car{
        @Override
        public String toString() {
            return "Car";
        }
    }
}
