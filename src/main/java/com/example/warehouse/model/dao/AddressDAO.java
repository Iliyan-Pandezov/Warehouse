package com.example.warehouse.model.dao;

import com.example.warehouse.model.entity.Profile;


public record AddressDAO(
        Long id,

        Profile profile,

        String name,

        String town,

        String neighbourhood,

        String streetName,

        String streetNumber,

        String buildingNumber,

        String entrance,

        String floor,

        String apartment
) {
    @Override
    public String toString() {

        StringBuilder address = new StringBuilder();

        address.append(this.name).append("\n");
        address.append(this.town).append("\n");
        address.append(this.neighbourhood).append("\n");

        if (this.streetName != null) {
            address.append(this.streetName).append("\n");
        }

        if (this.streetNumber != null) {
            address.append(this.streetNumber).append("\n");
        }

        if (this.buildingNumber != null) {
            address.append(this.buildingNumber).append("\n");
        }

        if (this.entrance != null) {
            address.append(this.entrance).append("\n");
        }

        if (this.floor != null) {
            address.append(this.floor).append("\n");
        }

        if (this.apartment != null) {
            address.append(this.apartment).append("\n");
        }

        return address.toString();

//        return "AddressDAO{" +
//                "name='" + name + '\'' +
//                ", town='" + town + '\'' +
//                ", neighbourhood='" + neighbourhood + '\'' +
//                ", streetName='" + streetName + '\'' +
//                ", streetNumber='" + streetNumber + '\'' +
//                ", buildingNumber='" + buildingNumber + '\'' +
//                ", entrance='" + entrance + '\'' +
//                ", floor='" + floor + '\'' +
//                ", apartment='" + apartment + '\'' +
//                '}';
    }
}
