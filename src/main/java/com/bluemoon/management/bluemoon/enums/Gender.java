package com.bluemoon.management.bluemoon.enums;

public enum Gender {
    Male{
        @Override
        public String toString() {
            return "Male";
        }
    },
    Female{
        @Override
        public String toString() {
            return "Female";
        }
    },
    Other
        {
            @Override
            public String toString() {
                return "Other";
            }
        },
}
