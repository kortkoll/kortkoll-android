package nu.kortkoll.android.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henrik on 9/29/13.
 */
public class Card implements Parcelable {
  public static final String PARCEL_KEY = "kortkoll.card";
  public static final String PARCEL_KEYS = "kortkoll.cards";

  public String name;
  public double purse;
  public String owner;
  public List<Product> products = new ArrayList<Product>();

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
    purse = parcel.readDouble();
    owner = parcel.readString();
    parcel.readTypedList(products, Product.CREATOR);

  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(name);
    parcel.writeDouble(purse);
    parcel.writeString(owner);
    parcel.writeTypedList(products);
  }
}
