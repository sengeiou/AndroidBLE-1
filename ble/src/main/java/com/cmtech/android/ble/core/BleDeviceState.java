package com.cmtech.android.ble.core;

import com.cmtech.android.ble.R;

/**
  *
  * ClassName:      BleDeviceState
  * Description:    BleDevice设备状态类
  * Author:         chenm
  * CreateDate:     2018/4/21 下午4:47
  * UpdateUser:     chenm
  * UpdateDate:     2019/4/19 下午4:47
  * UpdateRemark:   更新说明
  * Version:        1.0
 */

public class BleDeviceState {
    private static final int DEVICE_CLOSED_CODE = 0x00; // 已关闭
    private static final int DEVICE_SCANNING_CODE = 0x01; // 正在扫描
    private static final int DEVICE_CONNECTING_CODE = 0x02; // 正在连接
    private static final int DEVICE_DISCONNECTING_CODE = 0x03; // 正在断开
    private static final int CONNECT_SUCCESS_CODE = 0x04; // 连接成功
    private static final int CONNECT_FAILURE_CODE = 0x05;// 连接失败
    private static final int CONNECT_DISCONNECT_CODE = 0x06; // 连接断开

    public static final BleDeviceState DEVICE_CLOSED = new BleDeviceState(DEVICE_CLOSED_CODE, "已关闭", R.mipmap.ic_disconnect_32px);
    public static final BleDeviceState DEVICE_SCANNING = new BleDeviceState(DEVICE_SCANNING_CODE, "正在扫描", R.mipmap.ic_scanning_32px);
    public static final BleDeviceState DEVICE_CONNECTING = new BleDeviceState(DEVICE_CONNECTING_CODE, "正在连接", R.mipmap.ic_connecting_32px);
    public static final BleDeviceState DEVICE_DISCONNECTING = new BleDeviceState(DEVICE_DISCONNECTING_CODE, "正在断开", R.mipmap.ic_connecting_32px);
    public static final BleDeviceState CONNECT_SUCCESS = new BleDeviceState(CONNECT_SUCCESS_CODE, "已连接", R.mipmap.ic_connected_32px);
    public static final BleDeviceState CONNECT_FAILURE = new BleDeviceState(CONNECT_FAILURE_CODE, "连接失败", R.mipmap.ic_disconnect_32px);
    public static final BleDeviceState CONNECT_DISCONNECT = new BleDeviceState(CONNECT_DISCONNECT_CODE, "已断开", R.mipmap.ic_disconnect_32px);

    private final int code; // 状态码
    private String description; // 状态描述
    private int icon; // 状态图标

    private BleDeviceState(int code, String description, int icon) {
        this.code = code;
        this.description = description;
        this.icon = icon;
    }

    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getIcon() {
        return icon;
    }
    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return description;
    }
}