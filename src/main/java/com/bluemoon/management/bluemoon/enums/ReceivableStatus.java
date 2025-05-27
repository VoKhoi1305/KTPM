package com.bluemoon.management.bluemoon.enums;

public enum ReceivableStatus {
    Unissued {
        @Override
        public String toString() {
            return "Unissued";
        }
    },
    Issued {
        @Override
        public String toString() {
            return "Issued";
        }
    },
    PaidInFull {
        @Override
        public String toString() {
            return "PaidInFull";
        }
    },
    Overdue {
        @Override
        public String toString() {
            return "Overdue";
        }
    },
    Cancelled {
        @Override
        public String toString() {
            return "Cancelled";
        }
    },
    Waived {
        @Override
        public String toString() {
            return "Waived";
        }
    }

}
