// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: Ping.proto at 6:1
package name.peterbukhal.android.taxi.protocol;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import java.io.IOException;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import okio.ByteString;

public final class Ping extends Message<Ping, Ping.Builder> {
  public static final ProtoAdapter<Ping> ADAPTER = new ProtoAdapter<Ping>(FieldEncoding.LENGTH_DELIMITED, Ping.class) {
    @Override
    public int encodedSize(Ping value) {
      return ProtoAdapter.STRING.encodedSizeWithTag(1, value.provider)
          + ProtoAdapter.INT64.encodedSizeWithTag(2, value.time)
          + ProtoAdapter.FLOAT.encodedSizeWithTag(3, value.latitude)
          + ProtoAdapter.FLOAT.encodedSizeWithTag(4, value.longitude)
          + ProtoAdapter.INT32.encodedSizeWithTag(5, value.accuracy)
          + ProtoAdapter.INT32.encodedSizeWithTag(6, value.bearing)
          + ProtoAdapter.INT32.encodedSizeWithTag(7, value.speed)
          + value.unknownFields().size();
    }

    @Override
    public void encode(ProtoWriter writer, Ping value) throws IOException {
      ProtoAdapter.STRING.encodeWithTag(writer, 1, value.provider);
      ProtoAdapter.INT64.encodeWithTag(writer, 2, value.time);
      ProtoAdapter.FLOAT.encodeWithTag(writer, 3, value.latitude);
      ProtoAdapter.FLOAT.encodeWithTag(writer, 4, value.longitude);
      ProtoAdapter.INT32.encodeWithTag(writer, 5, value.accuracy);
      ProtoAdapter.INT32.encodeWithTag(writer, 6, value.bearing);
      ProtoAdapter.INT32.encodeWithTag(writer, 7, value.speed);
      writer.writeBytes(value.unknownFields());
    }

    @Override
    public Ping decode(ProtoReader reader) throws IOException {
      Builder builder = new Builder();
      long token = reader.beginMessage();
      for (int tag; (tag = reader.nextTag()) != -1;) {
        switch (tag) {
          case 1: builder.provider(ProtoAdapter.STRING.decode(reader)); break;
          case 2: builder.time(ProtoAdapter.INT64.decode(reader)); break;
          case 3: builder.latitude(ProtoAdapter.FLOAT.decode(reader)); break;
          case 4: builder.longitude(ProtoAdapter.FLOAT.decode(reader)); break;
          case 5: builder.accuracy(ProtoAdapter.INT32.decode(reader)); break;
          case 6: builder.bearing(ProtoAdapter.INT32.decode(reader)); break;
          case 7: builder.speed(ProtoAdapter.INT32.decode(reader)); break;
          default: {
            FieldEncoding fieldEncoding = reader.peekFieldEncoding();
            Object value = fieldEncoding.rawProtoAdapter().decode(reader);
            builder.addUnknownField(tag, fieldEncoding, value);
          }
        }
      }
      reader.endMessage(token);
      return builder.build();
    }

    @Override
    public Ping redact(Ping value) {
      Builder builder = value.newBuilder();
      builder.clearUnknownFields();
      return builder.build();
    }
  };

  private static final long serialVersionUID = 0L;

  public static final String DEFAULT_PROVIDER = "";

  public static final Long DEFAULT_TIME = 0L;

  public static final Float DEFAULT_LATITUDE = 0.0f;

  public static final Float DEFAULT_LONGITUDE = 0.0f;

  public static final Integer DEFAULT_ACCURACY = 0;

  public static final Integer DEFAULT_BEARING = 0;

  public static final Integer DEFAULT_SPEED = 0;

  public final String provider;

  public final Long time;

  public final Float latitude;

  public final Float longitude;

  public final Integer accuracy;

  public final Integer bearing;

  public final Integer speed;

  public Ping(String provider, Long time, Float latitude, Float longitude, Integer accuracy, Integer bearing, Integer speed) {
    this(provider, time, latitude, longitude, accuracy, bearing, speed, ByteString.EMPTY);
  }

  public Ping(String provider, Long time, Float latitude, Float longitude, Integer accuracy, Integer bearing, Integer speed, ByteString unknownFields) {
    super(unknownFields);
    this.provider = provider;
    this.time = time;
    this.latitude = latitude;
    this.longitude = longitude;
    this.accuracy = accuracy;
    this.bearing = bearing;
    this.speed = speed;
  }

  @Override
  public Builder newBuilder() {
    Builder builder = new Builder();
    builder.provider = provider;
    builder.time = time;
    builder.latitude = latitude;
    builder.longitude = longitude;
    builder.accuracy = accuracy;
    builder.bearing = bearing;
    builder.speed = speed;
    builder.addUnknownFields(unknownFields());
    return builder;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof Ping)) return false;
    Ping o = (Ping) other;
    return equals(unknownFields(), o.unknownFields())
        && equals(provider, o.provider)
        && equals(time, o.time)
        && equals(latitude, o.latitude)
        && equals(longitude, o.longitude)
        && equals(accuracy, o.accuracy)
        && equals(bearing, o.bearing)
        && equals(speed, o.speed);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode;
    if (result == 0) {
      result = unknownFields().hashCode();
      result = result * 37 + (provider != null ? provider.hashCode() : 0);
      result = result * 37 + (time != null ? time.hashCode() : 0);
      result = result * 37 + (latitude != null ? latitude.hashCode() : 0);
      result = result * 37 + (longitude != null ? longitude.hashCode() : 0);
      result = result * 37 + (accuracy != null ? accuracy.hashCode() : 0);
      result = result * 37 + (bearing != null ? bearing.hashCode() : 0);
      result = result * 37 + (speed != null ? speed.hashCode() : 0);
      super.hashCode = result;
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (provider != null) builder.append(", provider=").append(provider);
    if (time != null) builder.append(", time=").append(time);
    if (latitude != null) builder.append(", latitude=").append(latitude);
    if (longitude != null) builder.append(", longitude=").append(longitude);
    if (accuracy != null) builder.append(", accuracy=").append(accuracy);
    if (bearing != null) builder.append(", bearing=").append(bearing);
    if (speed != null) builder.append(", speed=").append(speed);
    return builder.replace(0, 2, "Ping{").append('}').toString();
  }

  public static final class Builder extends com.squareup.wire.Message.Builder<Ping, Builder> {
    public String provider;

    public Long time;

    public Float latitude;

    public Float longitude;

    public Integer accuracy;

    public Integer bearing;

    public Integer speed;

    public Builder() {
    }

    public Builder provider(String provider) {
      this.provider = provider;
      return this;
    }

    public Builder time(Long time) {
      this.time = time;
      return this;
    }

    public Builder latitude(Float latitude) {
      this.latitude = latitude;
      return this;
    }

    public Builder longitude(Float longitude) {
      this.longitude = longitude;
      return this;
    }

    public Builder accuracy(Integer accuracy) {
      this.accuracy = accuracy;
      return this;
    }

    public Builder bearing(Integer bearing) {
      this.bearing = bearing;
      return this;
    }

    public Builder speed(Integer speed) {
      this.speed = speed;
      return this;
    }

    @Override
    public Ping build() {
      if (provider == null
          || time == null
          || latitude == null
          || longitude == null
          || accuracy == null
          || bearing == null
          || speed == null) {
        throw missingRequiredFields(provider, "provider",
            time, "time",
            latitude, "latitude",
            longitude, "longitude",
            accuracy, "accuracy",
            bearing, "bearing",
            speed, "speed");
      }
      return new Ping(provider, time, latitude, longitude, accuracy, bearing, speed, buildUnknownFields());
    }
  }
}