// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: im.proto

package com.yjlan.im.common.proto;

public interface GroupMessageSendRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:GroupMessageSendRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional int64 senderId = 1;</code>
   */
  long getSenderId();

  /**
   * <code>optional int64 groupId = 2;</code>
   */
  long getGroupId();

  /**
   * <code>optional string sendContent = 3;</code>
   */
  java.lang.String getSendContent();
  /**
   * <code>optional string sendContent = 3;</code>
   */
  com.google.protobuf.ByteString
      getSendContentBytes();

  /**
   * <code>optional int64 timeStamp = 4;</code>
   */
  long getTimeStamp();
}
