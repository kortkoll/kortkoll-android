package nu.kortkoll.android.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by henrik on 9/29/13.
 */
public class Card implements Parcelable {
  public static final String PARCEL_KEY = "kortkoll.card";
  public static final String PARCEL_KEYS = "kortkoll.cards";

  public String name;
  public String purse;
  public String owner;
  public Product[] products;

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
    public Card createFromParcel(Parcel parcel) {
      return new Card(parcel);
    }

    public Card[] newArray(int size) {
      return new Card[size];
    }
  };


  public Card(Parcel parcel) {
    name = parcel.readString();
    purse = parcel.readString();
    owner = parcel.readString();
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(name);
    parcel.writeString(purse);
    parcel.writeString(owner);
  }
}

class Product {

}
