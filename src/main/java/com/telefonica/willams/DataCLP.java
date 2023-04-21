package com.telefonica.willams;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class DataCLP {
    public byte bitSet;
    public short sensor1;
    public float sensor2;

    public static DataCLP fromByteArray(byte[] byteArray) {
        if (byteArray == null || byteArray.length < 7) {
            throw new IllegalArgumentException("Invalid byte array");
        }

        DataCLP dataCLP = new DataCLP();
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        buffer.order(ByteOrder.LITTLE_ENDIAN); // Caso os dados estejam em Little Endian, senão use ByteOrder.BIG_ENDIAN

        dataCLP.bitSet = buffer.get();
        dataCLP.sensor1 = buffer.getShort();
        dataCLP.sensor2 = buffer.getFloat();

        return dataCLP;
    }

    public byte[] toByteArray() {
        ByteBuffer buffer = ByteBuffer.allocate(7);
        buffer.order(ByteOrder.LITTLE_ENDIAN); // Caso os dados estejam em Little Endian, senão use ByteOrder.BIG_ENDIAN

        buffer.put(bitSet);
        buffer.putShort(sensor1);
        buffer.putFloat(sensor2);

        return buffer.array();
    }

    @Override
    public String toString() {
        return "DataCLP{" +
                "bitSet=" + bitSet +
                ", sensor1=" + sensor1 +
                ", sensor2=" + sensor2 +
                '}';
    }
}