// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: im.proto

package com.yjlan.im.common.proto;

public interface MessagePushRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:MessagePushRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional int64 messageId = 1;</code>
   */
  long getMessageId();

  /**
   * <code>optional string senderId = 2;</code>
   */
  java.lang.String getSenderId();
  /**
   * <code>optional string senderId = 2;</code>
   */
  com.google.protobuf.ByteString
      getSenderIdBytes();

  /**
   * <code>optional string receiverId = 3;</code>
   */
  java.lang.String getReceiverId();
  /**
   * <code>optional string receiverId = 3;</code>
   */
  com.google.protobuf.ByteString
      getReceiverIdBytes();

  /**
   * <code>optional string sendContent = 4;</code>
   */
  java.lang.String getSendContent();
  /**
   * <code>optional string sendContent = 4;</code>
   */
  com.google.protobuf.ByteString
      getSendContentBytes();

  /**
   * <code>optional int64 timestamp = 5;</code>
   */
  long getTimestamp();
}
