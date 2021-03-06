// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: google/protobuf/descriptor.proto at 219:1
package com.google.protobuf;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import java.io.IOException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.util.List;
import okio.ByteString;

/**
 * Describes a service.
 */
public final class ServiceDescriptorProto extends Message<ServiceDescriptorProto, ServiceDescriptorProto.Builder> {
  public static final ProtoAdapter<ServiceDescriptorProto> ADAPTER = new ProtoAdapter<ServiceDescriptorProto>(FieldEncoding.LENGTH_DELIMITED, ServiceDescriptorProto.class) {
    @Override
    public int encodedSize(ServiceDescriptorProto value) {
      return (value.name != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, value.name) : 0)
          + MethodDescriptorProto.ADAPTER.asRepeated().encodedSizeWithTag(2, value.method)
          + (value.options != null ? ServiceOptions.ADAPTER.encodedSizeWithTag(3, value.options) : 0)
          + value.unknownFields().size();
    }

    @Override
    public void encode(ProtoWriter writer, ServiceDescriptorProto value) throws IOException {
      if (value.name != null) ProtoAdapter.STRING.encodeWithTag(writer, 1, value.name);
      if (value.method != null) MethodDescriptorProto.ADAPTER.asRepeated().encodeWithTag(writer, 2, value.method);
      if (value.options != null) ServiceOptions.ADAPTER.encodeWithTag(writer, 3, value.options);
      writer.writeBytes(value.unknownFields());
    }

    @Override
    public ServiceDescriptorProto decode(ProtoReader reader) throws IOException {
      Builder builder = new Builder();
      long token = reader.beginMessage();
      for (int tag; (tag = reader.nextTag()) != -1;) {
        switch (tag) {
          case 1: builder.name(ProtoAdapter.STRING.decode(reader)); break;
          case 2: builder.method.add(MethodDescriptorProto.ADAPTER.decode(reader)); break;
          case 3: builder.options(ServiceOptions.ADAPTER.decode(reader)); break;
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
    public ServiceDescriptorProto redact(ServiceDescriptorProto value) {
      Builder builder = value.newBuilder();
      redactElements(builder.method, MethodDescriptorProto.ADAPTER);
      if (builder.options != null) builder.options = ServiceOptions.ADAPTER.redact(builder.options);
      builder.clearUnknownFields();
      return builder.build();
    }
  };

  private static final long serialVersionUID = 0L;

  public static final String DEFAULT_NAME = "";

  public final String name;

  public final List<MethodDescriptorProto> method;

  public final ServiceOptions options;

  public ServiceDescriptorProto(String name, List<MethodDescriptorProto> method, ServiceOptions options) {
    this(name, method, options, ByteString.EMPTY);
  }

  public ServiceDescriptorProto(String name, List<MethodDescriptorProto> method, ServiceOptions options, ByteString unknownFields) {
    super(unknownFields);
    this.name = name;
    this.method = immutableCopyOf("method", method);
    this.options = options;
  }

  @Override
  public Builder newBuilder() {
    Builder builder = new Builder();
    builder.name = name;
    builder.method = copyOf("method", method);
    builder.options = options;
    builder.addUnknownFields(unknownFields());
    return builder;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof ServiceDescriptorProto)) return false;
    ServiceDescriptorProto o = (ServiceDescriptorProto) other;
    return equals(unknownFields(), o.unknownFields())
        && equals(name, o.name)
        && equals(method, o.method)
        && equals(options, o.options);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode;
    if (result == 0) {
      result = unknownFields().hashCode();
      result = result * 37 + (name != null ? name.hashCode() : 0);
      result = result * 37 + (method != null ? method.hashCode() : 1);
      result = result * 37 + (options != null ? options.hashCode() : 0);
      super.hashCode = result;
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (name != null) builder.append(", name=").append(name);
    if (method != null) builder.append(", method=").append(method);
    if (options != null) builder.append(", options=").append(options);
    return builder.replace(0, 2, "ServiceDescriptorProto{").append('}').toString();
  }

  public static final class Builder extends com.squareup.wire.Message.Builder<ServiceDescriptorProto, Builder> {
    public String name;

    public List<MethodDescriptorProto> method;

    public ServiceOptions options;

    public Builder() {
      method = newMutableList();
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder method(List<MethodDescriptorProto> method) {
      checkElementsNotNull(method);
      this.method = method;
      return this;
    }

    public Builder options(ServiceOptions options) {
      this.options = options;
      return this;
    }

    @Override
    public ServiceDescriptorProto build() {
      return new ServiceDescriptorProto(name, method, options, buildUnknownFields());
    }
  }
}
