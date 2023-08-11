package com.example.carservice.specifications;

import com.example.carservice.model.Cars;
import org.springframework.data.jpa.domain.Specification;

import java.time.Year;

public class CarSpecifications {

    public static Specification<Cars> byVin(String vin) {
        return (root, query, criteriaBuilder) -> {
            if (vin == null || vin.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("vin"), vin);
        };
    }

    public static Specification<Cars> byManufacturer(String manufacturer) {
        return (root, query, criteriaBuilder) -> {
            if (manufacturer == null || manufacturer.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("manufacturer"), manufacturer);
        };
    }

    public static Specification<Cars> byBrand(String brand) {
        return (root, query, criteriaBuilder) -> {
            if (brand == null || brand.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("brand"), brand);
        };
    }

    public static Specification<Cars> byYear(Year year) {
        return (root, query, criteriaBuilder) -> {
            if (year == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("yearRelease"), year);
        };
    }

    public static Specification<Cars> byStateNumber(String stateNumber) {
        return (root, query, criteriaBuilder) -> {
            if (stateNumber == null || stateNumber.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("stateNumber"), stateNumber);
        };
    }
}
