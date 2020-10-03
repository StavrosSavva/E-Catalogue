package com.e_catalogue;

public class Restaurants {
    String VenueId, City, Name,Venue;
    byte[] Logo;

    public Restaurants(String venueId,String venue, String city, String name, byte[] logo) {
        VenueId = venueId;
        City = city;
        Name = name;
        Logo = logo;
        Venue = venue;
    }

    public Restaurants(){

    }

    public String getVenue() {
        return Venue;
    }

    public void setVenue(String venue) {
        Venue = venue;
    }

    public String getVenueId() {
        return VenueId;
    }

    public void setVenueId(String venueId) {
        VenueId = venueId;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public byte[] getLogo() {
        return Logo;
    }

    public void setLogo(byte[] logo) {
        Logo = logo;
    }
}
