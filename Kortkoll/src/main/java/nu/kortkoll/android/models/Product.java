package nu.kortkoll.android.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by henrik on 11/24/13.
 */
public class Product implements Parcelable {
  public String type;
  public String startDate;
  public String endDate;
  public String price;
  public boolean active;

  private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  public Product(){}

  public Product(Parcel parcel) {
    type = parcel.readString();
    startDate = parcel.readString();
    endDate = parcel.readString();
    price = parcel.readString();
    active = parcel.readInt() == 1;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(type);
    parcel.writeString(startDate);
    parcel.writeString(endDate);
    parcel.writeString(price);
    parcel.writeInt(active ? 1 : 0);
  }

  public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
    public Product createFromParcel(Parcel parcel) {
      return new Product(parcel);
    }

    public Product[] newArray(int size) {
      return new Product[size];
    }
  };

  public String getDateRange() {
    try {
      Date start = dateFormat.parse(startDate);
      Date end = dateFormat.parse(endDate);
      DateFormat outFormat = DateFormat.getDateInstance();

      return outFormat.format(start) + " - " + outFormat.format(end);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return "";
  }

  public static Product getMockProduct() {
    Product product = new Product();
    product.active = true;
    product.startDate = "1988-02-12";
    product.endDate = "1988-03-15";
    product.type = "En biljett för biljetterna";
    product.price = "55";

    return product;
  }
}
