package com.e_catalogue;
import android.os.Parcel;
import android.os.Parcelable;


public class Items implements Parcelable {
    int id;
    String name, description, venue_id,type;
    byte[] photo;
    double price;
    String SecType,comment;
    int quantity;

    public Items(int id, String name, String description, String venue_id, byte[] photo, String type,String SecType, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.venue_id = venue_id;
        this.photo = photo;
        this.type = type;
        this.price = price;
        this.SecType = SecType;
    }
    public Items(){

    }
    private Items(Parcel in){
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.venue_id = in.readString();

        if(photo != null){
            photo = new byte[in.readInt()];
            in.readByteArray(photo);
        }

        this.type = in.readString();
        this.price = in.readDouble();
        this.SecType = in.readString();
        this.comment = in.readString();
        this.quantity = in.readInt();
    }

    public static final Creator<Items> CREATOR = new Creator<Items>() {
        @Override
        public Items createFromParcel(Parcel in) {
            return new Items(in);
        }

        @Override
        public Items[] newArray(int size) {
            return new Items[size];
        }
    };

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSecType() {
        return SecType;
    }

    public void setSecType(String secType) {
        SecType = secType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(venue_id);
        dest.writeString(type);

        if(photo != null){
            dest.writeInt(photo.length);
            dest.writeByteArray(photo);
        }

        dest.writeDouble(price);
        dest.writeString(SecType);
        dest.writeString(comment);
        dest.writeInt(quantity);
    }
}
