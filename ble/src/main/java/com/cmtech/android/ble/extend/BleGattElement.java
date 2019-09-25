package com.cmtech.android.ble.extend;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;

import com.cmtech.android.ble.utils.UuidUtil;

import java.util.UUID;


/**
  *
  * ClassName:      BleGattElement
  * Description:    表示Gatt的一个基本单元，可以是三种类型：SERVICE, CHARACTERISTIC, DESCRIPTOR
  * Author:         chenm
  * CreateDate:     2018-03-01 16:58
  * UpdateUser:     chenm
  * UpdateDate:     2019-06-22 16:58
  * UpdateRemark:   更新说明
  * Version:        1.0
 */

public class BleGattElement {
    private static final int TYPE_NULL = 0;                // 空ELement 类型
    private static final int TYPE_SERVICE = 1;             // service element类型
    private static final int TYPE_CHARACTERISTIC = 2;      // characteristic element类型
    private static final int TYPE_DESCRIPTOR = 3;          // descriptor element类型

    private final UUID serviceUuid; // 服务UUID
    private final UUID characteristicUuid; // 特征UUID
    private final UUID descriptorUuid; // 描述符UUID
    private final String description; // element的描述

    // 用短的UUID字符串构建Element
    public BleGattElement(String serviceString, String characteristicString, String descriptorString, String baseUuidString, String description) {
        this(UuidUtil.stringToUuid(serviceString, baseUuidString),
             UuidUtil.stringToUuid(characteristicString, baseUuidString),
             UuidUtil.stringToUuid(descriptorString, baseUuidString), description);
    }

    // 用UUID构建Element
    private BleGattElement(UUID serviceUuid, UUID characteristicUuid, UUID descriptorUuid, String description) {
        this.serviceUuid = serviceUuid;
        this.characteristicUuid = characteristicUuid;
        this.descriptorUuid = descriptorUuid;

        String servStr = (serviceUuid == null) ? null : UuidUtil.longToShortString(serviceUuid.toString());
        String charaStr = (characteristicUuid == null) ? null : UuidUtil.longToShortString(characteristicUuid.toString());
        String descStr = (descriptorUuid == null) ? null : UuidUtil.longToShortString(descriptorUuid.toString());
        this.description = description + "[" + servStr + "-" + charaStr + "-" + descStr + "]";
    }

    UUID getServiceUUID() {
        return serviceUuid;
    }

    UUID getCharacteristicUUID() {
        return characteristicUuid;
    }

    UUID getDescriptorUUID() {
        return descriptorUuid;
    }

    // 从设备中搜寻element对应的Gatt Object，可用于验证Element是否存在于设备中
    Object retrieveGattObject(BleDevice device) {
        if(device == null) return null;

        return retrieveGattObject(device.getBleDeviceGatt().getBluetoothGatt());
    }

    // 从设备中搜寻element对应的Gatt Object，可用于验证Element是否存在于设备中
    private Object retrieveGattObject(BluetoothGatt gatt) {
        if(gatt == null) return null;

        BluetoothGattService service;
        BluetoothGattCharacteristic characteristic;
        BluetoothGattDescriptor descriptor;
        Object element = null;
        if( (service = gatt.getService(serviceUuid)) != null) {
            element = service;
            if( (characteristic = service.getCharacteristic(characteristicUuid)) != null ) {
                element = characteristic;
                if( (descriptor = characteristic.getDescriptor(descriptorUuid)) != null ) {
                    element = descriptor;
                }
            }
        }
        return element;
    }

    BluetoothGattCharacteristic getCharacteristic(BluetoothGatt gatt) {
        BluetoothGattService service;
        if( (service = gatt.getService(serviceUuid)) != null) {
            return service.getCharacteristic(characteristicUuid);
        }
        return null;
    }

    BluetoothGattDescriptor getDescriptor(BluetoothGatt gatt) {
        Object element = retrieveGattObject(gatt);
        if(element instanceof BluetoothGattDescriptor)
            return (BluetoothGattDescriptor)element;
        return null;
    }

    // element的类型
    public int getType() {
        if(descriptorUuid != null) return TYPE_DESCRIPTOR;

        if(characteristicUuid != null) return TYPE_CHARACTERISTIC;

        if(serviceUuid != null) return TYPE_SERVICE;

        return TYPE_NULL;
    }

    @Override
    public String toString() {
        return description;
    }
}
